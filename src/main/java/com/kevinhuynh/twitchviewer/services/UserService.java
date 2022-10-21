package com.kevinhuynh.twitchviewer.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

import org.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.client.RestTemplate;

import com.kevinhuynh.twitchviewer.models.LoginUser;
import com.kevinhuynh.twitchviewer.models.User;
import com.kevinhuynh.twitchviewer.repositories.UserRepository;

@Service
public class UserService {
	
	private final String GET_USER_ID = "https://api.twitch.tv/helix/users/?login=";
	private final String GET_FOLLOWS_BY_ID = "https://api.twitch.tv/helix/users/follows?from_id=";

	@Autowired
	private UserRepository userRepository;
	
	public User register(User newUser, BindingResult result) {
		// check if email is unique
		if (userRepository.findByEmail(newUser.getEmail()).isPresent()) {
			result.rejectValue("email", "Unique", "Email is already in use.");
		}
		
		// check if password matches confirm password
		if (!newUser.getPassword().equals(newUser.getConfirm())) {
			result.rejectValue("confirm", "Matches", "Passwords must match.");
		}
		
		// final check to see if there are errors
		if (result.hasErrors()) {
			return null;
		}
		
		String hashedPassword = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
		newUser.setPassword(hashedPassword);
		




		return userRepository.save(newUser);
	}
	
	public User login(LoginUser newLogin, BindingResult result) {
		if (result.hasErrors()) {
			return null;
		}
		
		// check if the email is present in the database
		Optional<User> potentialUser = userRepository.findByEmail(newLogin.getEmail());
		if (!potentialUser.isPresent()) {
			result.rejectValue("email", "Unique", "Invalid Credentials.");
			// if email isn't found don't bother checking password
			return null;
		}
		
		// gets User object from the Optional
		User user = potentialUser.get();
		if (!BCrypt.checkpw(newLogin.getPassword(), user.getPassword())) {
			result.rejectValue("password", "Matches", "Invalid Credentials.");
		}
		
		return user;
		
	}
	
	public User getOne(Long id) {
		System.out.println("I AM HERE WOOOOOOO: " + id.getClass());

		return userRepository.findById(id).orElse(null);
	}

}