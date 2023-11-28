package com.gastelob.apiuser.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gastelob.apiuser.dto.PhoneDto;
import com.gastelob.apiuser.dto.UserDto;
import com.gastelob.apiuser.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserRestController.class)
 class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IUserService userService;

    @Autowired
    private ObjectMapper objectMapper;



    @Test
    void testRegisterUser() throws Exception {

        UserDto user = new UserDto();
        user.setActive(true);
        user.setEmail("gastelo@gmail.com");
        user.setName("Alexander Gastelo");
        user.setPassword("MiPassword01");
        user.setId(UUID.fromString("550e8400-e29b-41d4-a716-446655440000"));
        user.setToken("oiweuiwuw-ewewew-ewew-ewe-wewe-we-we-we-weiwje");

        PhoneDto phoneDto = new PhoneDto();
        phoneDto.setNumber("930532414");
        phoneDto.setCityCode("01");
        phoneDto.setCountryCode("51");
        List<PhoneDto> phoneEntities = new ArrayList<>();
        phoneEntities.add(phoneDto);
        user.setPhones(phoneEntities);

        when(userService.save(user)).thenReturn(new ResponseEntity<>(user, HttpStatus.CREATED));

        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(user)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("email").value("gastelo@gmail.com"))
        .andExpect(jsonPath("name").value("Alexander Gastelo"))
        .andExpect(jsonPath("password").value("MiPassword01"))
        .andExpect(jsonPath("token").exists());

    }

}
