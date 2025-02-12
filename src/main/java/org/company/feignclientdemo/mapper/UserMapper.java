package org.company.feignclientdemo.mapper;

import org.company.feignclientdemo.model.client.UserRequest;

public class UserMapper {
    public static UserRequest builderUserRequest() {
        return UserRequest.builder()
                .userName("Gunay")
                .age(76)
                .birthPlace("Istanbul")
                .build();
    }
}
