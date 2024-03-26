package com.profi.promoservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PromotionResponse {
    private Long id;

    private String name;

    private String description;
}
