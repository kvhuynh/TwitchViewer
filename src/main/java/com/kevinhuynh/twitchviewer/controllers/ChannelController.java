package com.kevinhuynh.twitchviewer.controllers;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kevinhuynh.twitchviewer.models.Channel;
import com.kevinhuynh.twitchviewer.services.ApiService;
import com.kevinhuynh.twitchviewer.services.ChannelService;
import com.kevinhuynh.twitchviewer.services.UserService;

@Controller
@RequestMapping("/channels")
public class ChannelController {
    @Autowired
    private ChannelService channelService;

    @Autowired
    private UserService userService;

    @Autowired
    private ApiService apiService;

    @GetMapping("/{channelName}")
    public String viewChannel(@PathVariable("channelName") String channelName, Channel channel, Model model) throws IOException {

        JSONObject recieveChannelObject = apiService.queryWrapper("getId", channelName);
        JSONArray channelDataArray = (JSONArray) recieveChannelObject.get("data");
        try {
            JSONObject channelData = (JSONObject) channelDataArray.get(0);
            
        model.addAttribute("channelData", channelData);
        // model.addAttribute("channel", channel)

        return "showOne.jsp";
        } catch (JSONException error) {
            return "error.jsp";
        }

    }


    @PostMapping("/search/submit")
    public String getChannelInfo(@ModelAttribute("channel") Channel channel, BindingResult result, Model model) throws IOException {
        System.out.println(channel);
        // apiService.getChannelData(channel.getDisplayName());

        
        return "redirect:/channels/" + channel.getDisplayName();
    }

    @GetMapping("/{channelName}/favorite")
    public String favoriteChannel(@PathVariable("channelName") String channelName, @ModelAttribute("channel") Channel channel, BindingResult result, Model model) {
        return "redirect:/channels/" + channelName;
    }
}

