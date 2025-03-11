package com.example.SecureAuthAPI.service;


import java.util.Date;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.SecureAuthAPI.dto.AuthRequest;
import com.example.SecureAuthAPI.dto.AuthResponse;
import com.example.SecureAuthAPI.dto.UserDTO;
import com.example.SecureAuthAPI.entity.UserEntity;
import com.example.SecureAuthAPI.exeption.UserAlreadyExistException;
import com.example.SecureAuthAPI.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired 
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public AuthResponse createNewAccount(UserDTO userDTO) {
	    if (userRepository.existsByEmail(userDTO.getEmail())) {
	        throw new UserAlreadyExistException("User with email " + userDTO.getEmail() + " already exists");
	    }

	    UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class); 
	    userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
	    UserEntity savedUser = userRepository.save(userEntity);
	    String token = jwtService.generateToken(savedUser.getEmail());
	    Date expiresIn = jwtService.extractExpiration(token); // Example: 3600 seconds

	    // Build response
	    AuthResponse.AuthData authData = new AuthResponse.AuthData(savedUser.getEmail(), token, expiresIn, savedUser.getMobileNumber());
	    return new AuthResponse("success", "Account created successfully", authData);
	}

	
	public AuthResponse login(AuthRequest authRequest) {
		UserEntity userEntity = userRepository.findByEmail(authRequest.getEmail())
		        .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + authRequest.getEmail()));

		    if (!passwordEncoder.matches(authRequest.getPassword(), userEntity.getPassword())) {
		        throw new BadCredentialsException("Invalid email or password");
		    }

		    String token = jwtService.generateToken(userEntity.getEmail());
		    Date expiresIn = jwtService.extractExpiration(token);
		    
		    AuthResponse.AuthData authData = new AuthResponse.AuthData(userEntity.getEmail(), token, expiresIn, userEntity.getMobileNumber());
		     return new AuthResponse("success", "Account created successfully", authData);
	}
}
