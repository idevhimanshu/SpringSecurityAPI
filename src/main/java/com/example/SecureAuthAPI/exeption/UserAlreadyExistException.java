package com.example.SecureAuthAPI.exeption;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAlreadyExistException extends RuntimeException {
	
	public UserAlreadyExistException(String message) {
		super(message);
	}

}
