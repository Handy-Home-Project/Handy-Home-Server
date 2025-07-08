package com.example.handy_home.core.user.application;

import com.example.handy_home.core.user.application.dto.UserDTO;
import com.example.handy_home.core.user.application.exception.LoginFailException;
import com.example.handy_home.core.user.domain.User;
import com.example.handy_home.core.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor
public class UserUseCase implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDTO join(UserDTO userDTO) {
        User user = User.builder()
                .id(userDTO.id()).name(userDTO.name()).password(userDTO.password())
                .build();
        user.validUserJoinOrThrow();
        User joinUser = userRepository.createUser(user);
        return new UserDTO(joinUser.getId(), joinUser.getName(), "");
    }

    @Override
    public UserDTO login(String id, String password) {
        try {
            User loginUser = userRepository.getOrThrowById(id);
            loginUser.checkPasswordOrThrow(password);
            return new UserDTO(loginUser.getId(), loginUser.getName(), "");
        } catch(NoSuchElementException e) {
            throw new LoginFailException("일치하는 사용자가 없습니다.");
        } catch(IllegalArgumentException e) {
            throw new LoginFailException("패스워드가 일치하지 않습니다.");
        }
    }

}