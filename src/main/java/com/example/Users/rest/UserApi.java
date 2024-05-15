package com.example.Users.rest;

import com.example.Users.dto.UserRequestDto;
import com.example.Users.dto.UserResponseDto;
import com.example.Users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserApi {
    private final UserService userService;


    @GetMapping("/public")
    public String getPublic(){
        return "Hello New Public";
    }
    @GetMapping("/role-user")
    public String getUser(){
        return "Hello  User";
    }
    @GetMapping("/role-admin")
    public String getUserAdmin(){
        return "Hello Admin";
    }



    @GetMapping("/list")
    public List<UserResponseDto> list(@RequestParam(value = "from",required = false)Integer from,
                                      @RequestParam(value = "to",required = false)Integer to){

        return userService.list(from,to);
    }
    @GetMapping("/{id}")
    public UserResponseDto get(@PathVariable Long id){
        return userService.get(id);
    }
    @PostMapping
    public UserResponseDto create(@RequestBody UserRequestDto UserDto){
        return userService.create(UserDto);
    }
    @PutMapping("/{id}")
    private UserResponseDto update(@PathVariable Long id,
                                   @RequestBody UserRequestDto UserDto){
        return userService.update(id,UserDto);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
            userService.delete(id);
    }
}
