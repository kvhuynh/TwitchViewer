package com.kevinhuynh.twitchviewer.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    // @GetMapping("/dashboard")
    // public String dashboard(HttpSession session, Model model) throws IOException {

    //     // populate fields with the users information
    //     User currentUser = userService.getOne((Long) session.getAttribute("uuid"));
    //     model.addAttribute("twitchName", currentUser.getTwitchUserName());
    //     model.addAttribute("twitchId", currentUser.getTwitchId());

    //     // JSONObject following = apiService.getFollows("57119302", "");
    //     // apiService.parseFollowers(following);
    //     JSONObject channelEmotes = apiService.getEmotes("94753024");
    //     apiService.parseEmotes(channelEmotes);
        


    //     // whenver the user logs in the app should update their follow llist
    //     return "dashboard.jsp";
    // }

    @GetMapping("/{channelName}")
    public String viewChannel(@PathVariable("channelName") String channelName) {
        System.out.println(channelName);
        return "showOne.jsp";
    }


    @PostMapping("/search/submit")
    public String getChannelInfo(@ModelAttribute("channel") Channel channel, Model model) throws IOException {

        apiService.getChannelData("");
        return "redirect:/channels/" + channel.getDisplayName();
    }

}

