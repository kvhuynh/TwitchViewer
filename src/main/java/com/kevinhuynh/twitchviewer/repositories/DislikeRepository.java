package com.kevinhuynh.twitchviewer.repositories;

import org.springframework.data.repository.CrudRepository;

import com.kevinhuynh.twitchviewer.models.Dislike;

public interface DislikeRepository extends CrudRepository<Dislike, Long> {
    
}
