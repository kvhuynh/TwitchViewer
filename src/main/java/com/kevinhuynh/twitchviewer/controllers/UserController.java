package com.kevinhuynh.twitchviewer.controllers;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kevinhuynh.twitchviewer.models.Channel;
import com.kevinhuynh.twitchviewer.models.LoginUser;
import com.kevinhuynh.twitchviewer.models.User;
import com.kevinhuynh.twitchviewer.services.ApiService;
import com.kevinhuynh.twitchviewer.services.ChannelService;
import com.kevinhuynh.twitchviewer.services.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private ChannelService channelService;

	@Autowired
	private ApiService apiService;
	
	@GetMapping("/login-register")
	public String index(Model model, HttpSession session, @ModelAttribute("channel") Channel channel) {
		System.out.println("yo");
		if (session.getAttribute("uuid") != null) {
			return "redirect:/";
		}
		
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		model.addAttribute("channel", channel);
		return "login-register.jsp";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("uuid");
		return "redirect:/";
	}
	
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model, HttpSession session, Channel channel) throws IOException {
		
		User user = userService.register(newUser, result);

		System.out.println("i am here register");

		if (result.hasErrors()) {
			model.addAttribute("newLogin", new LoginUser());
			model.addAttribute("hasRegistrationError", "hasRegistrationError");
			return "login-register.jsp";
		}
		session.setAttribute("uuid", user.getId());
		

		return "redirect:/";
	}
	
	
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, Model model, HttpSession session, Channel channel) {

		User user = userService.login(newLogin, result);

		if (result.hasErrors()) {
			model.addAttribute("newUser", new User());
			return "login-register.jsp";
		}
		
		session.setAttribute("uuid", user.getId());
		
		return "redirect:/";
	}
}
