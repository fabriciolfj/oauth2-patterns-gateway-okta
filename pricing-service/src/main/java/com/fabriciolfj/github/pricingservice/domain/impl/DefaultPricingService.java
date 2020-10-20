package com.fabriciolfj.github.pricingservice.domain.impl;

import com.fabriciolfj.github.pricingservice.api.dto.CartRequest;
import com.fabriciolfj.github.pricingservice.domain.PricingService;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.Collectors;

@Service
public class DefaultPricingService implements PricingService {

    private final CurrencyUnit RS = Monetary.getCurrency("BRL");

    @Override
    public CartRequest price(@NonNull CartRequest request) {
        final var total = createMonetary(BigDecimal.ZERO);
        var items = request.getLineItems().stream()
                .map(v -> {
                    var valor = new BigDecimal(Math.random() * 100).setScale(2, RoundingMode.HALF_UP);
                    v.setPrice(createMonetary(valor));
                    request.setTotal(total.add(v.getPrice().multiply(v.getQuantity())));
                    return v;
                }).collect(Collectors.toList());

        request.setLineItems(items);
        return request;
    }

    public MonetaryAmount createMonetary(final BigDecimal valor) {
        return Monetary.getDefaultAmountFactory()
                .setCurrency(RS)
                .setNumber(valor.compareTo(BigDecimal.ZERO) == 0 ? BigDecimal.ZERO : valor)
                .create();
    }
}
