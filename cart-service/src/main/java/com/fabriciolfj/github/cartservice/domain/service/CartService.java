package com.fabriciolfj.github.cartservice.domain.service;

import com.fabriciolfj.github.cartservice.api.exceptions.CartNotFoundException;
import com.fabriciolfj.github.cartservice.domain.entity.Cart;
import com.fabriciolfj.github.cartservice.domain.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    public Cart findById(final Integer id) {
        return cartRepository.findById(id).orElseThrow(() -> new CartNotFoundException("Cart not found: " + id));
    }

    public Cart save(final Cart cart) {
        return cartRepository.save(cart);
    }
}
