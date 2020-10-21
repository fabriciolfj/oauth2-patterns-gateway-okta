package com.fabriciolfj.github.cartservice.domain.service;

import com.fabriciolfj.github.cartservice.api.exceptions.PricingException;
import com.fabriciolfj.github.cartservice.domain.entity.Cart;
import com.fabriciolfj.github.cartservice.domain.entity.LineItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.math.BigDecimal;

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

    private Cart updatePrice(final Cart price, final Cart cart) {
        price.getLineItems().stream().forEach(value -> updateLineItem(cart, value));
        return cart;
    }

    private LineItem updateLineItem(Cart cart, LineItem value) {
        return cart.getLineItems()
                .stream()
                .filter(c -> c.equals(value)).map(found -> {
                    found.setPrice(value.getPrice());
                    return found;
                }).findFirst()
                .orElseGet(() -> lineItemDefault());
    }

    private LineItem lineItemDefault() {
        return LineItem.builder()
                .id(0)
                .price(BigDecimal.ZERO)
                .quantity(0)
                .build();
    }

}
