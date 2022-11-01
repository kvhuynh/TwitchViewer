package com.kevinhuynh.twitchviewer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kevinhuynh.twitchviewer.models.Like;
import com.kevinhuynh.twitchviewer.repositories.LikeRepository;

@Service
public class LikeService {
    @Autowired
    private LikeRepository likeRepository;

    public Like saveLike(Like like) {
        return likeRepository.save(like);
    }

    public Like getOne(Long id) {
		  return likeRepository.findById(id).orElse(null);

    }

    public void deleteLike(Like like) {
      likeRepository.delete(like);
    }
    
}
