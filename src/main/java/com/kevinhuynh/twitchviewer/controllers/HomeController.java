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

@Controller
public class HomeController {
    @Autowired
    ApiService apiService;


    @GetMapping("/")
    public String index(@ModelAttribute("channel") Channel channel, Model model, HttpSession session) throws IOException {
        // JSONObject recieveChannels  = apiService.getPopularChannels();
        // ArrayList<Object> popularChannels = apiService.parsePopularChannels(recieveChannels);
        JSONObject recieveChannels = apiService.queryWrapper("getPopular" , "");
        ArrayList<Object> popularChannels = apiService.parsePopularChannels(recieveChannels);
        model.addAttribute("popularChannels", popularChannels);
        model.addAttribute("channel", channel);
        System.out.println(session.getAttribute("uuid"));
        return "index.jsp";
    }

    @GetMapping("/about")
    public String about(@ModelAttribute("channel") Channel channel, Model model) {
        model.addAttribute("channel", channel);
        return "about.jsp";
    }
}
