package com.gastelob.apiuser.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private UUID id;
    private String name;

    @Pattern(
            regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$",
            message = "El correo electrónico debe tener un formato válido (ej. aaaaaaa@dominio.cl)."
    )
    private String email;

    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d.*\\d)[A-Za-z\\d]{8,}$",
            message = "El password debe contener al menos una mayúscula, una minúscula y dos números."
    )
    private String password;
    private String token;
    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime lastLogin;
    private boolean isActive;
    private List<PhoneDto> phones;

}
