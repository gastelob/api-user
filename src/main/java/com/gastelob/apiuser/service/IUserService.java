package com.gastelob.apiuser.service;

import com.gastelob.apiuser.dto.UserDto;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface IUserService {
    ResponseEntity<UserDto> save(UserDto userDto);

    ResponseEntity<List<UserDto>> listAll();
}
