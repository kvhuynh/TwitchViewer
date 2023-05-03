package com.kevinhuynh.twitchviewer.repositories;

import org.springframework.data.repository.CrudRepository;

import com.kevinhuynh.twitchviewer.models.Like;

public interface LikeRepository extends CrudRepository<Like, Long> {
    
}
