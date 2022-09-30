package com.kevinhuynh.twitchviewer.controllers;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
	
	@GetMapping("/login_register")
	public String index(Model model, HttpSession session) {
		System.out.println("yo");
		if (session.getAttribute("uuid") != null) {
			return "redirect:/dashboard";
		}
		
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		return "login-register.jsp";
	}
	
	@PostMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("uuid");
		return "redirect:/";
	}
	
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model, HttpSession session) throws IOException {

		// should be its own method / in an api file
		JSONObject userData = apiService.getChannelData(newUser.getTwitchUserName());

        JSONArray getChannel = (JSONArray) userData.get("data");
        JSONObject getData = (JSONObject) getChannel.get(0);
        String twitchId = (String) getData.get("id");
		//

		


		newUser.setTwitchId(twitchId);
		User user = userService.register(newUser, result);


		if (result.hasErrors()) {
			model.addAttribute("newLogin", new LoginUser());
			return "index.jsp";
		}
		session.setAttribute("uuid", user.getId());
		

		return "redirect:/dashboard";
	}
	
	
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, Model model, HttpSession session) {

		User user = userService.login(newLogin, result);

		if (result.hasErrors()) {
			model.addAttribute("newUser", new User());
			return "index.jsp";
		}
		
		session.setAttribute("uuid", user.getId());
		
		return "redirect:/dashboard";
	}
}
