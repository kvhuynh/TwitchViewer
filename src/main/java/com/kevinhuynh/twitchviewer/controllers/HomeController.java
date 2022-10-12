package com.kevinhuynh.twitchviewer.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.kevinhuynh.twitchviewer.models.Channel;
import com.kevinhuynh.twitchviewer.services.ApiService;
import com.kevinhuynh.twitchviewer.services.UserService;

@Controller
public class HomeController {
    @Autowired
    ApiService apiService;

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String index(@ModelAttribute("channel") Channel channel, Model model, HttpSession session) throws IOException {

        if (session.getAttribute("uuid") != null) {
            model.addAttribute("userName", userService.getOne((Long) session.getAttribute("uuid")));
            System.out.println("favorites: " + userService.getOne((Long) session.getAttribute("uuid")).getChannels());
            System.out.println(userService.getOne((Long) session.getAttribute("uuid")));
            // session.removeAttribute("invalidChannel");
        }

        JSONObject recieveChannels = apiService.queryWrapper("getPopular" , "");
        ArrayList<Object> popularChannels = apiService.parsePopularChannels(recieveChannels);
        model.addAttribute("popularChannels", popularChannels);
        model.addAttribute("channel", channel);
        System.out.println(session.getAttribute("uuid"));
        System.out.println(session.getAttribute("invalidChannel"));
        return "index.jsp";
    }

    @GetMapping("/about")
    public String about(@ModelAttribute("channel") Channel channel, Model model, HttpSession session) {
        model.addAttribute("userName", userService.getOne((Long) session.getAttribute("uuid")));
        model.addAttribute("channel", channel);
        session.removeAttribute("invalidChannel");
        return "about.jsp";
    }

    @GetMapping("/profile")
    public String profile(@ModelAttribute("channel") Channel channel, Model model, HttpSession session) {
       return "profile.jsp"; 
    }
}
