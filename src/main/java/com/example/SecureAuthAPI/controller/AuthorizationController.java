package com.example.SecureAuthAPI.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.SecureAuthAPI.dto.AuthRequest;
import com.example.SecureAuthAPI.dto.AuthResponse;
import com.example.SecureAuthAPI.dto.UserDTO;
import com.example.SecureAuthAPI.entity.UserEntity;
import com.example.SecureAuthAPI.exeption.UserAlreadyExistException;
import com.example.SecureAuthAPI.repository.UserRepository;
import com.example.SecureAuthAPI.service.UserService;

import jakarta.validation.Valid;

@RestController
public class AuthorizationController {
	
	@Autowired 
	private UserService userService;
	
	
	
	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> createNewAccount(@Valid @RequestBody UserDTO userDTO) {
			return new ResponseEntity<AuthResponse>( userService.createNewAccount(userDTO), HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest authRequest) {
		return new ResponseEntity<AuthResponse>( userService.login(authRequest), HttpStatus.ACCEPTED);
	}

}
