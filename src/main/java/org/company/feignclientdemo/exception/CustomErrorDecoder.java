package org.company.feignclientdemo.exception;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import static org.company.feignclientdemo.enums.ExceptionMessage.CONFLICT;


@Slf4j
public class CustomErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        log.debug("Feign response status: {}", response.status());
        log.debug("Feign response body: {}", response.body());

        if (response.status() == HttpStatus.BAD_REQUEST.value()) {
            return new UnsupportedCurrencyException("Unsupported currency error.");
        } else if (response.status() == HttpStatus.CONFLICT.value()) {
            log.error("Username already exists, 409 Conflict error.");
            return new UserAlreadyExistsException(CONFLICT.getMessage());
        }
        return defaultErrorDecoder.decode(methodKey, response);
    }
}


