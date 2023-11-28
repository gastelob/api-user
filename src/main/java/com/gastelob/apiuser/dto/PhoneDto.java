package com.gastelob.apiuser.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PhoneDto {

    private String number;
    private String cityCode;
    private String countryCode;
}
