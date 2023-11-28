package com.gastelob.apiuser.controller;

import com.gastelob.apiuser.dto.UserDto;
import com.gastelob.apiuser.service.IUserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserRestController {

    private final IUserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> listAll(){
        return userService.listAll();
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@Valid @RequestBody UserDto userDto){
        return  userService.save(userDto);
    }
}
