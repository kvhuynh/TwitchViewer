package com.kevinhuynh.twitchviewer.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kevinhuynh.twitchviewer.models.Channel;
import com.kevinhuynh.twitchviewer.models.Comment;
import com.kevinhuynh.twitchviewer.models.Like;
import com.kevinhuynh.twitchviewer.models.User;
import com.kevinhuynh.twitchviewer.services.ApiService;
import com.kevinhuynh.twitchviewer.services.ChannelService;
import com.kevinhuynh.twitchviewer.services.CommentService;
import com.kevinhuynh.twitchviewer.services.LikeService;
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

    @Autowired
    private LikeService likeService;

    // channel
    @GetMapping("/{channelName}")
    public String viewChannel(@PathVariable("channelName") String channelName, Channel channel, User user, Model model, HttpSession session) throws IOException {

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
            }

            JSONObject recieveChannelObject = apiService.queryWrapper("getId", channelName);
            JSONArray channelDataArray = (JSONArray) recieveChannelObject.get("data");
            JSONObject channelData = (JSONObject) channelDataArray.get(0);
            
            model.addAttribute("channelData", channelData);
            model.addAttribute("user", new User());
            model.addAttribute("comment", new Comment());

            Boolean createChannel = channelService.isInDatabase(channelData);
            if (createChannel) {
                Channel newChannel = channelService.extractChannelData(channelData);
                channelService.saveChannel(newChannel);
                System.out.println("channel added to database");
            } else {
                System.out.println("channel is already in database");
                // model.addAttribute("comments", channelService.getOneByLogin(channelName).getComments()); 
                for (Comment comment : channelService.getOneByLogin(channelName).getComments()) {
                    // comment.setIsEditing(false);
                }
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

    // channel comments

    @PostMapping("/{channelName}/comment")
    public void commentChannel(@PathVariable("channelName") String channelName, @Valid @ModelAttribute("comment") Comment comment, BindingResult result, @ModelAttribute("channel") Channel channel, Model model, HttpSession session) {
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
            if (result.hasErrors()) {
                System.out.println("error in submitting comment");
            }
        }
    }
    
    @DeleteMapping("/{channelName}/comment/{commentId}/delete")
    public void deleteComment(@PathVariable("channelName") String channelName, @PathVariable("commentId") Long commentId) {
        System.out.println("deleting comment in controller");
        commentService.delete(commentId);
    }

    @GetMapping("/{channelName}/comment/{commentId}/edit")
    public void editComment(@PathVariable("channelName") String channelName, @PathVariable("commentId") Long commentId) {
        System.out.println("tagging the comment to be edited to display text box");
        Comment comment = commentService.getOne(commentId);
        comment.setIsEditing(true);
        commentService.saveComment((comment));
    }

    @PutMapping("/{channelName}/comment/{commentId}/edit/submit")
    public void updateComment(@PathVariable("channelName") String channelName, @PathVariable("commentId") Long commentId, String commentBody) {
        System.out.println("submitting edited comment in controller");
        Comment comment = commentService.getOne(commentId);
        comment.setCommentBody(commentBody);
        comment.setIsEditing(false);
        commentService.saveComment(comment);
    }

    // channel comments likes
    @GetMapping("/{channelName}/comment/{commentId}/like")
    public Like likeComment(@PathVariable("channelName") String channelName, @PathVariable("commentId") Long commentId, HttpSession session) {
        // first check if the user has already liked this comment
        User currentUser = userService.getOne((Long) session.getAttribute("uuid"));
        Comment currentComment = commentService.getOne(commentId);
        List<Like> userLiked = currentComment.getLikedBy();
        
        for (Like like : userLiked) {
            System.out.println(like.getUser());
            if (like.getUser() == currentUser) {
                System.out.println("number of likes for this user is (before deletion): " + userLiked);
                userLiked.remove(like);
                likeService.deleteLike(like);
                System.out.println("number of likes for this comment is: " + currentComment.getLikedBy().size());
                System.out.println("number of likes for this user is (after deletion): " + userLiked);
                return null;

            }
        }

        Like like = new Like(); // create new like
        like.setComment(commentService.getOne(commentId));  // set the new like to the comment
        like.setUser(currentUser);
        likeService.saveLike(like); // save the new like
        currentUser.getLikes().add(like); // add the new like to the users list of likes
        currentComment.getLikedBy().add(like); // add the new like to comments list of likes
        System.out.println("number of likes for this comment is: " + currentComment.getLikedBy().size());
        return like;
    }
}

