package com.fabriciolfj.github.pricingservice.api.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class LineItemRequest {

    private Integer id;
    private Integer quantity;
    private BigDecimal price;
    private String productName;

    public LineItemRequest() {
    }

    public LineItemRequest(Integer id, Integer quantity) {
        this.id = id;
        this.quantity = quantity;
    }
}
