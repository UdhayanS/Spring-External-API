package com.example.externalApi.service;

import com.example.externalApi.dto.UserDto;
import com.example.externalApi.entity.User;
import com.example.externalApi.repository.UserRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    //Dependency Injections

    private RestTemplate restTemplate;
    private UserRepository userRepository;


    public UserService(RestTemplate restTemplate, UserRepository userRepository){
        this.restTemplate = restTemplate;
        this.userRepository = userRepository;
    }

    private final String url = "https://jsonplaceholder.typicode.com/users";

    private UserDto mapToDto(User user){
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        dto.setPhone(user.getPhone());
        dto.setCompanyName(user.getCompany().getName());
        return dto;

    }

    //Controller Logic

    public List<User> getAllUsers(){
        User[] users = restTemplate.getForObject(url, User[].class);
        return List.of(users);
    }

    public User getUserById(Integer id){
        User[] users = restTemplate.getForObject(url, User[].class);
        User currUser = Arrays.stream(users).filter(user -> user.getId().equals(id)).findFirst().orElse(null);
        return currUser;
    }

    public User getUserByCompany(String name){
        User[] users = restTemplate.getForObject(url, User[].class);
        User currUser = Arrays.stream(users).filter(user -> user.getCompany().getName().equals(name)).findFirst().orElse(null);
        return  currUser;
    }



    public List<UserDto> getRestictData(){
        User[] users = restTemplate.getForObject(url, User[].class);
        return Arrays.stream(users).map(this::mapToDto).toList();
    }


    //@Scheduled(fixedRate = 10000)
    public void syncData() {
        User[] users = restTemplate.getForObject(url, User[].class);
        userRepository.saveAll(Arrays.asList(users));
        System.out.println("Synced");
    }

    //DB Access
    public List<User> getAllUsersByDb(){
        return userRepository.findAll();
    }

}
