package com.example.SecureAuthAPI.exeption;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserNotFoundException extends RuntimeException {
	
	private String resourceName;
	private String feildName;
	private String feildValue;
	
	public UserNotFoundException(String resourceName, String feildName, String feildValue) {
		super(String.format("%s Not found with %s: %s", resourceName, feildName, feildValue));
		this.resourceName = resourceName;
		this.feildName = feildName;
		this.feildValue = feildValue;
	}
	
	

}
