package com.asseco.demo.user.generator;

import com.asseco.demo.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserProcessor {

    private final UserGenerator userGenerator;
    private final UserRepository userRepository;

    @Async
    public void processChunk() {
        var users = userGenerator.generateUsers();
        userRepository.saveUsers(users);
    }

}
