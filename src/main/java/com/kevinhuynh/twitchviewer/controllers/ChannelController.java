package com.kevinhuynh.twitchviewer.controllers;

import java.io.IOException;
import java.util.List;

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
import com.kevinhuynh.twitchviewer.models.Comment;
import com.kevinhuynh.twitchviewer.services.ApiService;
import com.kevinhuynh.twitchviewer.services.ChannelService;
import com.kevinhuynh.twitchviewer.services.CommentService;
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

    @Autowired
    private CommentService commentService;

    @GetMapping("/{channelName}")
    public String viewChannel(@PathVariable("channelName") String channelName, Channel channel, Model model, HttpSession session) throws IOException {

        try {
            if (session.getAttribute("uuid") != null) {
                model.addAttribute("userName", userService.getOne((Long) session.getAttribute("uuid")));
                List<Channel> channels = userService.getOne((Long) session.getAttribute("uuid")).getChannels();
                for (int i = 0; i < channels.size(); i++) {
                    // System.out.println(channels.get(i).getLogin());
                    JSONObject liveInformation = apiService.queryWrapper("getLiveData", "user_login" + channels.get(i).getLogin());
                    JSONArray test = (JSONArray) liveInformation.get("data");
                    JSONObject yo = (JSONObject) test.get(0);
                    // System.out.println(yo.get("type"));
    
                }
                System.out.println(channelName);
                System.out.println(channelService.getOneByLogin(channelName));
            }

            JSONObject recieveChannelObject = apiService.queryWrapper("getId", channelName);
            JSONArray channelDataArray = (JSONArray) recieveChannelObject.get("data");
            JSONObject channelData = (JSONObject) channelDataArray.get(0);
            
            model.addAttribute("channelData", channelData);
            model.addAttribute("comment", new Comment());

            Boolean createChannel = channelService.isInDatabase(channelData);
            if (createChannel) {
                Channel newChannel = channelService.extractChannelData(channelData);
                channelService.saveChannel(newChannel);
                System.out.println("channel added to database");
            } else {
                System.out.println("channel is already in database");
                // model.addAttribute("comments", channelService.getOneByLogin(channelName).getComments()); 
                model.addAttribute("comments", channelService.getOneByLogin(channelName)); 

            }

        return "showOne.jsp";
        
        } catch (JSONException | NullPointerException error) {
            System.out.println("why is there an error here");
            System.out.println(channelName);
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
            if (favoritedChannel.getUsers().contains(userService.getOne((Long) session.getAttribute("uuid")))) {
                favoritedChannel.getUsers().remove(userService.getOne((Long) session.getAttribute("uuid")));
            } else {
                favoritedChannel.getUsers().add(userService.getOne((Long) session.getAttribute("uuid")));
            }
            channelService.saveChannel(favoritedChannel);
        }
    }

    @GetMapping("/{channelName}/comment")
    public void commentChannel(@PathVariable("channelName") String channelName, @ModelAttribute("comment") Comment comment, @ModelAttribute("channel") Channel channel, BindingResult result, Model model, HttpSession session) {
        if (session.getAttribute("uuid") == null) {
            System.out.println("comment error");
        } else {
            Comment completedComment = commentService.constructComment(comment, userService.getOne((Long) session.getAttribute("uuid")));
            completedComment.setChannel(channelService.getOneByLogin(channelName));
            commentService.saveComment(completedComment);
            System.out.println("comment is: " + comment);
            Channel targetChannel = channelService.getOneByLogin(channelName); // get channel --> attach comment to the channel
            targetChannel.getComments().add(completedComment);
            channelService.saveChannel(targetChannel);


            // model.addAttribute("userName", userService.getOne((Long) session.getAttribute("uuid")));

            // targetChannel.getComments().add(comment);
            // attach comment to the channel
        }

    }

}

