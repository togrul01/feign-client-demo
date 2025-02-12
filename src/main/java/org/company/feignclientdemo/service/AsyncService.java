package org.company.feignclientdemo.service;

import lombok.RequiredArgsConstructor;
import org.company.feignclientdemo.client.UserClient;
import org.company.feignclientdemo.model.client.UserRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import static org.company.feignclientdemo.mapper.UserMapper.builderUserRequest;

@RequiredArgsConstructor
@Service
public class AsyncService {
    private final UserClient userClient;

    @Async
    public void saveUser() {
        userClient.saveUser(builderUserRequest());
    }
}
