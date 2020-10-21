package com.fabriciolfj.github.cartservice.api.controller;

import com.fabriciolfj.github.cartservice.domain.entity.Cart;
import com.fabriciolfj.github.cartservice.domain.service.CartService;
import com.fabriciolfj.github.cartservice.domain.service.PricingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    
    private final CartService cartService;
    private final PricingService pricingService;
    
    @GetMapping("/{id}")
    public Cart findById(@PathVariable final Integer id) {
        return cartService.findById(id);
    }

    @PostMapping
    public Cart create(@RequestBody final Cart cart) {
        final var priced = pricingService.price(cart);
        return cartService.save(priced);
    }
}
