package com.example.SecureAuthAPI.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.SecureAuthAPI.entity.UserEntity;
import com.example.SecureAuthAPI.entity.UserEntityPrinciple;
import com.example.SecureAuthAPI.repository.UserRepository;

@Service
public class MyUserDetailService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<UserEntity> userEntity = userRepository.findByEmail(username);
		
		if (userEntity == null) {
			throw new UsernameNotFoundException("User name not found");
		} else {
			 return new UserEntityPrinciple();
		}
		
		
	}

}
