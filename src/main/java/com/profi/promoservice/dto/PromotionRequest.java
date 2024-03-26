package com.profi.promoservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PromotionRequest {
    @NotBlank
    private String name;

    private String description;
}
