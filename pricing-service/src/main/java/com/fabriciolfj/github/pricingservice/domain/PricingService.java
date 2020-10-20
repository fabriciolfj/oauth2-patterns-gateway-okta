package com.fabriciolfj.github.pricingservice.domain;

import com.fabriciolfj.github.pricingservice.api.dto.CartRequest;
import lombok.NonNull;

public interface PricingService {

    CartRequest price(@NonNull final CartRequest request);
}
