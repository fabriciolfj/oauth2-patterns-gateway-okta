package com.fabriciolfj.github.cartservice.domain.service;

import com.fabriciolfj.github.cartservice.api.exceptions.PricingException;
import com.fabriciolfj.github.cartservice.domain.entity.Cart;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import static java.util.Optional.ofNullable;

@Service
@Slf4j
@RequiredArgsConstructor
public class PricingService {

    private final WebClient webClient;

    public Cart price(final Cart cart) {
        return ofNullable(requestPricing(cart))
                .map(this::requestPricing)
                .orElseThrow(() -> new PricingException("Fail processo update price"));
    }

    private Cart requestPricing(final Cart cart) {
        final var response = webClient.post().bodyValue(cart).retrieve().bodyToMono(Cart.class);
        return response.block();
    }

}
