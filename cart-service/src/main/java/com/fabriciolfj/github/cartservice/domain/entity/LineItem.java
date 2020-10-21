package com.fabriciolfj.github.cartservice.domain.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Builder
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class LineItem {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String productName;

    private Integer quantity;

    private BigDecimal price;
}
