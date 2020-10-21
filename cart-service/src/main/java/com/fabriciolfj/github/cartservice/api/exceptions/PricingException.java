package com.fabriciolfj.github.cartservice.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class PricingException extends RuntimeException {

    public PricingException(final String msg) {
        super(msg);
    }
}

