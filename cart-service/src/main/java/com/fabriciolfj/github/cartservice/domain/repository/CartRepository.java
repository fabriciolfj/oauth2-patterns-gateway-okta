package com.fabriciolfj.github.cartservice.domain.repository;

import com.fabriciolfj.github.cartservice.domain.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
}
