package com.example.handy_home.data.repositories;

import com.example.handy_home.data.data_sources.HomeDataSource;
import com.example.handy_home.data.data_sources.UserDataSource;
import com.example.handy_home.data.models.HomeModel;
import com.example.handy_home.data.models.UserModel;
import com.example.handy_home.domain.entities.UserEntity;
import org.python.util.PythonInterpreter;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

@Repository
public class HomeRepository {

    private final HomeDataSource homeDataSource;

    private final UserDataSource userDataSource;

    private final Environment env;

    public HomeRepository(Environment env, HomeDataSource homeDataSource, UserDataSource userDataSource) {
        this.env = env;
        this.homeDataSource = homeDataSource;
        this.userDataSource = userDataSource;
    }

    public HomeModel createHome(File image, Long userId) {
        try {
            final Optional<UserModel> user = userDataSource.findById(userId);
            if (user.isEmpty()) {
                throw new Exception("Not Found User");
            }

            final String pythonPath = env.getProperty("python.path");

            final String scriptPath = "./floor_plan_parser/spa_prediction.py";
            final String modelPath = "./floor_plan_parser/model/SPA_FP_best_model.pth";
            final String imagePath = STR."./\{image.getPath()}";
            final String saveJsonPath = "./floor_plan_parser/outputs";

            final ProcessBuilder processBuilder = new ProcessBuilder(
                    "bash", "-c",
                    STR."source ~/.bash_profile && \{pythonPath} \{scriptPath} -fmp \{modelPath} -dt \{imagePath} -rt \{saveJsonPath} -ui \{userId}"
            );

            processBuilder.directory(new File(System.getProperty("user.dir")));

            final Process process = processBuilder.start();
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));

            boolean successCreateHome = false;

            String line;
            while ((line = br.readLine()) != null) {
                if (line.equals(STR."success_\{userId}")) {
                    successCreateHome = true;
                    break;
                }
            }

            process.waitFor();

            if (successCreateHome) {
                String homeJson = new String(Files.readAllBytes(new File(STR."floor_plan_parser/outputs/\{userId}.json").toPath()));
                return homeDataSource.save(new HomeModel(null, user.get(), homeJson));
            } else {
                throw new Exception("Failed Save Home");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
