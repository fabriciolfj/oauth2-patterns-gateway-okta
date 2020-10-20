package com.fabriciolfj.github.pricingservice.api.controller;

import com.fabriciolfj.github.pricingservice.api.dto.CartRequest;
import com.fabriciolfj.github.pricingservice.domain.PricingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pricing")
@RequiredArgsConstructor
public class PricingController {

    private final PricingService pricingService;

    @PostMapping("/price")
    @ResponseStatus(HttpStatus.CREATED)
    public CartRequest price(@RequestBody CartRequest cart) {
        return pricingService.price(cart);
    }
}
