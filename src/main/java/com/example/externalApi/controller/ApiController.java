package com.example.externalApi.controller;

import com.example.externalApi.dto.UserDto;
import com.example.externalApi.entity.User;
import com.example.externalApi.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    private UserService userService;

    public ApiController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/user")
    public List<User> getApiUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable Integer id){
        return userService.getUserById(id);
    }

    @GetMapping("/company/{name}")
    public User getUserByComapny(@PathVariable String name){
        return userService.getUserByCompany(name);
    }

    @GetMapping("/restict")
    public List<UserDto> getRestictData(){
        return userService.getRestictData();
    }

    @GetMapping("/sync-users")
    public void syncUsers(){
        userService.syncData();

    }

}
