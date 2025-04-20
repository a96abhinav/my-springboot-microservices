package com.springboot.rest.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GlobalExceptionResponseModel {
    private String error;
}
