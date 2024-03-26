package com.profi.promoservice.exception.responce_message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DefaultResponseMessage {
    private String date;
    private String message;
    private String path;
}
