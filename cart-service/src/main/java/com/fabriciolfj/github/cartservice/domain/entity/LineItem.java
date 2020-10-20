package com.fabriciolfj.github.cartservice.domain.entity;

import com.fabriciolfj.github.cartservice.infrastructure.util.MoneyConverter;
import lombok.Data;

import javax.money.MonetaryAmount;
import javax.persistence.*;

@Data
@Entity
public class LineItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String productName;

    private Integer quantity;

    @Convert(converter = MoneyConverter.class)
    private MonetaryAmount price;
}
