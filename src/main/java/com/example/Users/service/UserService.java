package com.example.Users.service;

import com.example.Users.dto.UserRequestDto;
import com.example.Users.dto.UserResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<UserResponseDto> list(Integer from, Integer to);

    UserResponseDto get(Long id);

    UserResponseDto create(UserRequestDto UserDto);

    UserResponseDto update(Long id, UserRequestDto UserDto);

    void delete(Long id);

}
