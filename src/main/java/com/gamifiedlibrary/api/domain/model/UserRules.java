package com.gamifiedlibrary.api.domain.model;

import java.util.ArrayList;
import java.util.List;

public class UserRules {
	
	static public List<String> checkAccountCreationInfo(String userName, String password) {
		
		List<String> messages = new ArrayList<String>();
		if(userName.length() < 6) {
			messages.add("User must be at least 6 characters long");
		}
		if(password.length() < 6) {
			messages.add("Passoword must be at least 6 characters long");
		}
		
		return messages;
	}
}
