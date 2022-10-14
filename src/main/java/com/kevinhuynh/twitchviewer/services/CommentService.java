package com.kevinhuynh.twitchviewer.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kevinhuynh.twitchviewer.models.Comment;
import com.kevinhuynh.twitchviewer.models.User;
import com.kevinhuynh.twitchviewer.repositories.CommentRepository;

@Service
public class CommentService {
    
    @Autowired
    private CommentRepository commentRepository;
    	// ========== Create / Update ==========
	// public Comment createComment(Comment comment) {
	// 	return commentRepository.save(comment);
	// }

	// ========== Many to Many ==========
	public void joinTable() {

	}

	// ========== Read ==========
	public List<Comment> getAll() {
		return commentRepository.findAll();
	}
	
	public Comment getOne(Long id) {
		return commentRepository.findById(id).orElse(null);
	}
	
	// ========== Delete ==========

	public void delete(Long id) {
		commentRepository.deleteById(id);
	}
	
	public Comment saveComment(Comment comment) {
		return commentRepository.save(comment);
	}

    public Comment constructComment(Comment comment, User user) {
        comment.setCommenter(user.getTwitchUserName());
        return comment;
    }

}
