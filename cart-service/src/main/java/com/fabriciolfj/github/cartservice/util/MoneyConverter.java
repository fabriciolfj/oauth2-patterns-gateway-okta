package com.fabriciolfj.github.cartservice.util;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.persistence.AttributeConverter;
import java.math.BigDecimal;

public class MoneyConverter implements AttributeConverter<MonetaryAmount, BigDecimal> {

    private final CurrencyUnit RS = Monetary.getCurrency("BRL");

    @Override
    public BigDecimal convertToDatabaseColumn(MonetaryAmount attribute) {
        if (attribute == null) {
            return null;
        }

        return attribute.getNumber().numberValue(BigDecimal.class);
    }

    @Override
    public MonetaryAmount convertToEntityAttribute(BigDecimal dbData) {
        if (dbData == null) {
            return null;
        }

        return Monetary.getDefaultAmountFactory()
                .setCurrency(RS)
                .setNumber(dbData.doubleValue())
                .create();
    }
}
