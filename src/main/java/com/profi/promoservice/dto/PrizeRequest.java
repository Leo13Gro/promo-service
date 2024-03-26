package com.profi.promoservice.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrizeRequest {
    @NotBlank
    private String description;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public PrizeRequest(@NotBlank String description) {
        this.description = description;
    }
}
