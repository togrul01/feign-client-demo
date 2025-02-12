package org.company.feignclientdemo.client;

import org.company.feignclientdemo.config.FeignConfig;
import org.company.feignclientdemo.model.client.UserRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ms-user", url ="${client.urls.ms-user}", configuration = FeignConfig.class)
public interface UserClient {
    @PostMapping(path = "/v1/users")
    void saveUser(@RequestBody UserRequest userRequest);

}
