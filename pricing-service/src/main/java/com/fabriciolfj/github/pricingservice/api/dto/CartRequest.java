package com.fabriciolfj.github.pricingservice.api.dto;

import lombok.Data;

import javax.money.MonetaryAmount;
import java.util.ArrayList;
import java.util.List;

@Data
public class CartRequest {

    private Integer id;
    private String customerId;
    private List<LineItemRequest> lineItems = new ArrayList<>();
    private MonetaryAmount total;

    public void addLineItem(LineItemRequest lineItem){
        this.lineItems.add(lineItem);
    }
}
