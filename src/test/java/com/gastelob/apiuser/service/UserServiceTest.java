package com.gastelob.apiuser.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import com.gastelob.apiuser.dto.PhoneDto;
import com.gastelob.apiuser.dto.UserDto;
import com.gastelob.apiuser.entities.PhoneEntity;
import com.gastelob.apiuser.entities.UserEntity;
import com.gastelob.apiuser.repository.UserRepository;
import com.gastelob.apiuser.util.JwtTokenUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.*;

@ExtendWith(MockitoExtension.class)
 class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private JwtTokenUtil jwtTokenUtil;

    private UserEntity user;

    private UserDto userDto;


    @BeforeEach
    void setUp() {
        user = new UserEntity();
        userDto = new UserDto();
        modelMapper = new ModelMapper();
        userService = new UserServiceImpl(userRepository, modelMapper, jwtTokenUtil);

        user.setActive(true);
        user.setEmail("gastelo@gmail.com");
        user.setName("Alexander Gastelo");
        user.setPassword("MiPassword01");
        user.setId(UUID.fromString("550e8400-e29b-41d4-a716-446655440000"));
        user.setToken("oiweuiwuw-ewewew-ewew-ewe-wewe-we-we-we-weiwje");

        PhoneEntity phoneEntity = new PhoneEntity();
        phoneEntity.setUser(user);
        phoneEntity.setNumber("930532414");
        phoneEntity.setCityCode("01");
        phoneEntity.setCountryCode("51");
        phoneEntity.setId(UUID.fromString("530e8400-e29b-41d4-a716-446655440020"));
        List<PhoneEntity> phoneEntities = new ArrayList<>();
        phoneEntities.add(phoneEntity);
        user.setPhones(phoneEntities);

        userDto = new UserDto();
        userDto.setActive(true);
        userDto.setEmail("gastelo@gmail.com");
        userDto.setName("Alexander Gastelo");
        userDto.setPassword("MiPassword01");
        userDto.setId(UUID.fromString("550e8400-e29b-41d4-a716-446655440000"));
        PhoneDto phoneDto = new PhoneDto();
        phoneDto.setNumber("930532414");
        phoneDto.setCityCode("01");
        phoneDto.setCountryCode("51");

        List<PhoneDto> phoneDtos = new ArrayList<>();
        phoneDtos.add(phoneDto);
        userDto.setPhones(phoneDtos);


    }


    @DisplayName("Test registro de usuario")
    @Test
    void testRegisterUser(){
        given(userRepository.save(user)).willReturn(user);
        UserDto userSave = userService.save(userDto).getBody();
        assertThat(userSave).isNotNull();
    }

    @DisplayName("Test registro de usuario: Exception DataIntegrityViolationException")
    @Test
    void testRegisterUserWithDataIntegrityViolationException() {
        String mensajeEsperado ="El usuario con el correo electrónico especificado, ya se encuentra registrado";

        given(userRepository.save(user)).willThrow(new DataIntegrityViolationException(mensajeEsperado));
        DataIntegrityViolationException exception =  assertThrows(DataIntegrityViolationException.class, () -> {
            userService.save(userDto);
        });
        assertNotNull(exception.getMessage()); // Asegurar que el mensaje no sea nulo
        assertTrue(exception.getMessage().contains(mensajeEsperado)); // Verificar la presencia de una cadena específica en el mensaje


    }




}
