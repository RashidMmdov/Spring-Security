package com.example.Users.service;

import com.example.Users.domain.UserEntity;
import com.example.Users.dto.UserRequestDto;
import com.example.Users.dto.UserResponseDto;
import com.example.Users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final ModelMapper modelMapper;


    private final UserRepository userRepository;
    @Override
    public List<UserResponseDto> list(Integer from, Integer to) {
        return userRepository.findAll()
                .stream()
                .map(userEntity -> modelMapper.map(userEntity,UserResponseDto.class))
                .toList();
    }

    @Override
    public UserResponseDto get(Long id) {
        return userRepository.findById(id)
                .map(userEntity -> modelMapper.map(userEntity,UserResponseDto.class))
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public UserResponseDto create(UserRequestDto UserDto) {
        UserEntity user = modelMapper.map(UserDto,UserEntity.class);

        return modelMapper.map(userRepository.save(user),UserResponseDto.class);
    }

    @Override
    public UserResponseDto update(Long id, UserRequestDto UserDto) {
        final  UserEntity user = modelMapper.map(UserDto,UserEntity.class);
        user.setId(id);
        searchException(id);
        return modelMapper.map(userRepository.save(user),UserResponseDto.class);
    }

    @Override
    public void delete(Long id) {
        searchException(id);
        userRepository.deleteById(id);
    }


    public void searchException(Long id){
        userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Not Found: "+id+" this id"));
    }

}
