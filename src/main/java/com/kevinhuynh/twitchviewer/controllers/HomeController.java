package com.kevinhuynh.twitchviewer.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;
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
            // from here take each favorite from the database and query its name and get viewcoutn and display info

            // 1. user --> user.getChannels() --> for each channel run https://api.twitch.tv/helix/streams?user_login=${channel} -->
            // System.out.println(userService.getOne((Long) session.getAttribute("uuid")).getChannels());

            List<Channel> channels = userService.getOne((Long) session.getAttribute("uuid")).getChannels();
            for (int i = 0; i < channels.size(); i++) {
                // System.out.println(channels.get(i).getLogin());
                JSONObject liveInformation = apiService.queryWrapper("getLiveData", "user_login" + channels.get(i).getLogin());
                JSONArray test = (JSONArray) liveInformation.get("data");
                JSONObject yo = (JSONObject) test.get(0);
                // System.out.println(yo.get("type"));

            }
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
    public String about(@ModelAttribute("channel") Channel channel, Model model, HttpSession session) throws IOException{
        if (session.getAttribute("uuid") != null) {
            model.addAttribute("userName", userService.getOne((Long) session.getAttribute("uuid")));
            model.addAttribute("channel", channel);
                        // from here take each favorite from the database and query its name and get viewcoutn and display info

            // 1. user --> user.getChannels() --> for each channel run https://api.twitch.tv/helix/streams?user_login=${channel} -->
            // System.out.println(userService.getOne((Long) session.getAttribute("uuid")).getChannels());

            List<Channel> channels = userService.getOne((Long) session.getAttribute("uuid")).getChannels();
            for (int i = 0; i < channels.size(); i++) {
                // System.out.println(channels.get(i).getLogin());
                JSONObject liveInformation = apiService.queryWrapper("getLiveData", "user_login" + channels.get(i).getLogin());
                JSONArray test = (JSONArray) liveInformation.get("data");
                JSONObject yo = (JSONObject) test.get(0);
                // System.out.println(yo.get("type"));

            }
        }
        session.removeAttribute("invalidChannel");
        return "about.jsp";
    }

    @GetMapping("/profile")
    public String profile(@ModelAttribute("channel") Channel channel, Model model, HttpSession session) {
        if (session.getAttribute("uuid") != null) {
            model.addAttribute("userName", userService.getOne((Long) session.getAttribute("uuid")));
            session.removeAttribute("invalidChannel");
            model.addAttribute("channel", channel);
            return "profile.jsp"; 
        }
        session.removeAttribute("invalidChannel");
        return "redirect:/";

    }
}
