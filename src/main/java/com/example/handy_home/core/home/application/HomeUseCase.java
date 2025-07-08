package com.example.handy_home.core.home.application;

import com.example.handy_home.core.home.application.dto.HomeDTO;
import com.example.handy_home.core.home.domain.Home;
import com.example.handy_home.core.home.domain.HomeRepository;
import com.example.handy_home.core.user.domain.User;
import com.example.handy_home.core.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.file.Files;

@Service
@RequiredArgsConstructor
public class HomeUseCase implements HomeService{

    private final HomeRepository homeRepository;
    private final UserRepository userRepository;
    private final Environment env;

    @Override
    public HomeDTO createHome(String userId, File image) {
        try {
            User user = userRepository.getOrThrowById(userId);
            final String pythonPath = env.getProperty("python.path");
            final String scriptPath = "./floor_plan_parser/spa_prediction.py";
            final String modelPath = "./floor_plan_parser/model/SPA_FP_best_model.pth";
            final String imagePath = "./" + image.getPath();
            final String saveJsonPath = "./floor_plan_parser/outputs";
            final ProcessBuilder processBuilder = new ProcessBuilder(
                    "bash", "-c",
                    "source ~/.bash_profile && "+pythonPath+" "+scriptPath+" -fmp" +modelPath+ " -dt "+imagePath+" -rt "+saveJsonPath+" -ui " + userId
            );
            processBuilder.directory(new File(System.getProperty("user.dir")));
            final Process process = processBuilder.start();
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            boolean successCreateHome = false;
            String line;
            while ((line = br.readLine()) != null) {
                if (line.equals("success_"+userId)) {
                    successCreateHome = true;
                    break;
                }
            }

            process.waitFor();

            if (successCreateHome) {
                String homeJson = new String(Files.readAllBytes(new File("floor_plan_parser/outputs/"+userId+".json").toPath()));
                Home home = homeRepository.save(Home.builder().user(user).layoutData(homeJson).build());
                return HomeDTO.fromEntity(home);
            } else {
                throw new Exception("Failed Save Home");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
