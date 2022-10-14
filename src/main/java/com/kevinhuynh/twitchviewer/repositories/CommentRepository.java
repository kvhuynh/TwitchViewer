package com.kevinhuynh.twitchviewer.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kevinhuynh.twitchviewer.models.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long>{
    List<Comment> findAll();
}
