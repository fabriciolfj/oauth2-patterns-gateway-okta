package com.fabriciolfj.github.cartservice.domain.entity;

import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String customerId;

    private BigDecimal total;

    @OneToMany(cascade = CascadeType.ALL)
    private List<LineItem> lineItems = new ArrayList<>();

    public void addLineItem(final LineItem lineItem) {
        this.lineItems.add(lineItem);
    }
}
