package org.company.feignclientdemo.controller;

import lombok.RequiredArgsConstructor;
import org.company.feignclientdemo.exception.UserAlreadyExistsException;
import org.company.feignclientdemo.model.request.AccountRequest;
import org.company.feignclientdemo.service.AccountService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/validate")
    public void validateAccount(@RequestBody AccountRequest request) {
        accountService.validateAccount(request);
    }
}
