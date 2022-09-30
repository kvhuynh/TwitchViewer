package com.kevinhuynh.twitchviewer.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kevinhuynh.twitchviewer.models.Channel;

public interface ChannelRepository extends CrudRepository<Channel, Long>{
	List<Channel> findAll();

}
