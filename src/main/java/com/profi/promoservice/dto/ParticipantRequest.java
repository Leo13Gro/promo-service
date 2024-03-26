package com.profi.promoservice.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParticipantRequest {
    @NotBlank
    private String name;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public ParticipantRequest(@NotBlank String name) {
        this.name = name;
    }
}
