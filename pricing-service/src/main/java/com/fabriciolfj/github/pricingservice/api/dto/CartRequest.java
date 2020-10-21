package com.fabriciolfj.github.pricingservice.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@JsonInclude(NON_NULL)
public class CartRequest {

    private Integer id;
    private String customerId;
    private List<LineItemRequest> lineItems = new ArrayList<>();
    private BigDecimal total;

    public void addLineItem(LineItemRequest lineItem){
        this.lineItems.add(lineItem);
    }
}
