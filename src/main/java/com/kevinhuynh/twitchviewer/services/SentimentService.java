package com.kevinhuynh.twitchviewer.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kevinhuynh.twitchviewer.models.Comment;
import com.kevinhuynh.twitchviewer.models.Dislike;
import com.kevinhuynh.twitchviewer.models.Like;
import com.kevinhuynh.twitchviewer.models.User;
import com.kevinhuynh.twitchviewer.repositories.DislikeRepository;
import com.kevinhuynh.twitchviewer.repositories.LikeRepository;

@Service
public class SentimentService {

  @Autowired
  private LikeRepository likeRepository;

  @Autowired
  private DislikeRepository dislikeRepository;

  // public Like retrieveData(User user, Comment comment, String operation) {
  //   if (operation == "like") {
  //     List<Like> userLiked = comment.getLikedBy();
  //   } else {
  //     // List<Dislike> userDisliked = comment.getDislikedBy();
  //   }
  //   return new Like();
  // }

  // Like service

  public Like saveLike(Like like) {
      return likeRepository.save(like);
  }

  public Like getOneLike(Long id) {
    return likeRepository.findById(id).orElse(null);

  }

  public void deleteLike(Like like) {
    likeRepository.delete(like);
  }
   
  // Dislike service
  public Dislike saveDislike(Dislike dislike) {
    return dislikeRepository.save(dislike);
  }

  public Dislike getOneDislike(Long id) {
    return dislikeRepository.findById(id).orElse(null);

  }

  public void deleteDislike(Dislike dislike) {
    dislikeRepository.delete(dislike);
  }

}
