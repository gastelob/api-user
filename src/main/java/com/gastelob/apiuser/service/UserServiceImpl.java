package com.gastelob.apiuser.service;

import com.gastelob.apiuser.dto.UserDto;
import com.gastelob.apiuser.entities.UserEntity;
import com.gastelob.apiuser.repository.UserRepository;

import com.gastelob.apiuser.util.JwtTokenUtil;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService{
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final JwtTokenUtil jwtTokenUtil;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, JwtTokenUtil jwtTokenUtil) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public ResponseEntity<List<UserDto>> listAll(){
        List<UserDto> users = userRepository.findAllWithPhones().orElse(new ArrayList<>())
                .stream().map(userEntity -> modelMapper.map(userEntity,UserDto.class)).collect(Collectors.toList());
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDto> save(UserDto userDto)  {
        UserEntity user = modelMapper.map(userDto,UserEntity.class);
        user.setToken(jwtTokenUtil.generateToken(user.getEmail()));
        UserEntity userSave =  userRepository.save(user);
        return new ResponseEntity<>(modelMapper.map(userSave, UserDto.class),HttpStatus.CREATED);
    }

}
