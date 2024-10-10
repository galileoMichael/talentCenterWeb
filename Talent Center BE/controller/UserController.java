package com.tujuhsembilan.talentcenter.controller;

import org.springframework.web.bind.annotation.RestController;

import com.tujuhsembilan.talentcenter.dto.request.LoginRequest;
import com.tujuhsembilan.talentcenter.dto.request.RegisterRequest;
import com.tujuhsembilan.talentcenter.response.LoginResponse;
import com.tujuhsembilan.talentcenter.response.MessageResponse;
import com.tujuhsembilan.talentcenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("/api/user-management")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users/register")
    public ResponseEntity<MessageResponse> register(@RequestBody RegisterRequest request){
        return userService.registerUser(request);
    }

    @PostMapping("/users/sign-in")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
        return userService.loginUser(request);
    }
}