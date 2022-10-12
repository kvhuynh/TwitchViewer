package com.kevinhuynh.twitchviewer.controllers;

import java.io.IOException;

import javax.servlet.http.HttpSession;

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
    public String viewChannel(@PathVariable("channelName") String channelName, Channel channel, Model model, HttpSession session) throws IOException {

        try {
            if (session.getAttribute("uuid") != null) {
                model.addAttribute("userName", userService.getOne((Long) session.getAttribute("uuid")));
            }
            JSONObject recieveChannelObject = apiService.queryWrapper("getId", channelName);
            JSONArray channelDataArray = (JSONArray) recieveChannelObject.get("data");
            JSONObject channelData = (JSONObject) channelDataArray.get(0);
            
            model.addAttribute("channelData", channelData);


            Boolean createChannel = channelService.isInDatabase(channelData);
            if (createChannel) {
                Channel newChannel = channelService.extractChannelData(channelData);
                channelService.createChannel(newChannel);
            } else {
                System.out.println("channel is already in database");
            }

        return "showOne.jsp";
        } catch (JSONException | NullPointerException error) {
            session.setAttribute("invalidChannel", "Channel cannot be found.");
            return "redirect:/";
        }

    }


    @PostMapping("/search/submit")
    public String getChannelInfo(@ModelAttribute("channel") Channel channel, BindingResult result, Model model, HttpSession session) throws IOException {
  
        session.removeAttribute("invalidChannel");
        return "redirect:/channels/" + channel.getDisplayName();
    }

    @GetMapping("/{channelName}/favorite")
    public void favoriteChannel(@PathVariable("channelName") String channelName, @ModelAttribute("channel") Channel channel, BindingResult result, Model model, HttpSession session) {
        // return "redirect:/channels/" + channelName;
        if (session.getAttribute("uuid") == null) {
            System.out.println("error here");
        } else {
            Channel favoritedChannel = channelService.getOneByLogin(channelName);
            // favoritedChannel.setUsers(userService.getOne((Long) session.getAttribute("uuid")));
            if (favoritedChannel.getUsers().contains(userService.getOne((Long) session.getAttribute("uuid")))) {
                favoritedChannel.getUsers().remove(userService.getOne((Long) session.getAttribute("uuid")));
            } else {
                favoritedChannel.getUsers().add(userService.getOne((Long) session.getAttribute("uuid")));
            }
            channelService.createChannel(favoritedChannel);
        }
    }

}

