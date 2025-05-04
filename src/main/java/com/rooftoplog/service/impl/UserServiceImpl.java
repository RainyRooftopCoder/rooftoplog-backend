package com.rooftoplog.service.impl;

import com.rooftoplog.entity.UserEntity;
import com.rooftoplog.repository.UserRepository;
import com.rooftoplog.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Boolean adminLogin(String username, String password) {
        Boolean isLogin = false;

        UserEntity user = userRepository.findByUsername(username).orElseThrow(() -> {
            return new RuntimeException("사용자 정보가 없습니다.");
        });

        log.info("userPswd :: {}", password);
        log.info("getPassword :: {}", user.getPassword());

        if(user.getPassword().equals(password)) {
            isLogin = true;
        }

        return isLogin;
    }
}
