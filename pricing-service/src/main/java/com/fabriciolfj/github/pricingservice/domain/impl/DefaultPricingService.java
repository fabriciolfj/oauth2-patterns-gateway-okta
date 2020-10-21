package com.fabriciolfj.github.pricingservice.domain.impl;

import com.fabriciolfj.github.pricingservice.api.dto.CartRequest;
import com.fabriciolfj.github.pricingservice.domain.PricingService;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.Collectors;

@Service
public class DefaultPricingService implements PricingService {

    BigDecimal total;

    @Override
    public CartRequest price(@NonNull CartRequest request) {
         this.total = BigDecimal.ZERO;
        var items = request.getLineItems().stream()
                .map(v -> {
                    var valor = new BigDecimal(Math.random() * 100).setScale(2, RoundingMode.HALF_UP);
                    v.setPrice(valor);
                    total = total.add(v.getPrice().multiply(BigDecimal.valueOf(v.getQuantity())));
                    return v;
                }).collect(Collectors.toList());

        request.setLineItems(items);
        request.setTotal(total);
        return request;
    }
}
