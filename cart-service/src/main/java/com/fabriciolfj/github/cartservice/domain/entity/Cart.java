package com.fabriciolfj.github.cartservice.domain.entity;

import com.fabriciolfj.github.cartservice.util.MoneyConverter;
import lombok.Data;

import javax.money.MonetaryAmount;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String customerId;

    @Convert(converter = MoneyConverter.class)
    private MonetaryAmount total;

    @OneToMany(cascade = CascadeType.ALL)
    private List<LineItem> lineItems = new ArrayList<>();

    public void addLineItem(final LineItem lineItem) {
        this.lineItems.add(lineItem);
    }
}
