package org.company.feignclientdemo.service;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.company.feignclientdemo.client.UserClient;
import org.company.feignclientdemo.constants.CurrencyConstants;
import org.company.feignclientdemo.exception.UnexpectedErrorException;
import org.company.feignclientdemo.exception.UnsupportedCurrencyException;
import org.company.feignclientdemo.exception.UserAlreadyExistsException;
import org.company.feignclientdemo.model.request.AccountRequest;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import static org.company.feignclientdemo.mapper.UserMapper.builderUserRequest;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {
    private  final AsyncService asyncService;


    public void validateAccount(AccountRequest request) {
        log.info("Validating account for user with ID: {}", request.getUserId());
        try {
            // Currency validation
            if (!CurrencyConstants.eligibleCurrencies.contains(request.getCurrency())) {
                String errorMessage = String.format("Currency '%s' is not supported. Supported currencies: %s",
                        request.getCurrency(), CurrencyConstants.eligibleCurrencies);
                log.error("Unsupported currency detected: {}. Error: {}", request.getCurrency(), errorMessage);
                throw new UnsupportedCurrencyException(errorMessage);
            }
            asyncService.saveUser();
            log.info("Account validated successfully for user: {}", request.getUserId());

        } catch (UserAlreadyExistsException ex) {
            // Handle username already exists error
            log.error("Username already exists error: {}", ex.getMessage());
            throw ex; // Re-throw to propagate the exception to Feign client

        } catch (UnsupportedCurrencyException ex) {
            log.error("Error validating account for user {}: {}", request.getUserId(), ex.getMessage());
            throw ex;

        } catch (FeignException ex) {
            log.error("Feign exception occurred while validating account for user {}: {}",
                    request.getUserId(), ex.getMessage(), ex);

            if (ex.status() == HttpStatus.CONFLICT.value()) {
                log.error("Conflict error: Username already exists.");
                throw new UserAlreadyExistsException("Username already exists.");
            }

        } catch (Exception ex) {
            // General exception handler
            log.error("Unexpected error occurred while validating account for user {}: {}", request.getUserId(),
                    ex.getMessage(), ex);
            throw new UnexpectedErrorException("Unexpected error occurred. Please try again later.");
        }
    }


}
