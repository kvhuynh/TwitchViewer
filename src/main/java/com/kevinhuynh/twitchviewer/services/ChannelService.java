package com.kevinhuynh.twitchviewer.services;

import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kevinhuynh.twitchviewer.models.Channel;
import com.kevinhuynh.twitchviewer.repositories.ChannelRepository;

@Service
public class ChannelService {
	@Autowired
	private ChannelRepository channelRepository;

	// ========== Create / Update ==========
	// public Channel createChannel(Channel channel) {
	// 	return channelRepository.save(channel);
	// }


	// ========== Many to Many ==========
	public void joinTable() {

	}

	// ========== Read ==========
	public List<Channel> getAll() {
		return channelRepository.findAll();
	}
	
	public Channel getOne(Long id) {
		return channelRepository.findById(id).orElse(null);
	}

	public Channel getOneByLogin(String name) {
		return channelRepository.findByLogin(name);
	}
	
	// ========== Delete ==========

	public void delete(Long id) {
		channelRepository.deleteById(id);
	}

	public Channel extractChannelData(JSONObject channel) {
		Channel potentialChannel = channelRepository.findByLogin((String) channel.get("login"));
		if (potentialChannel != null) {
			return null;
		}
		Channel newChannel = new Channel();
		newChannel.setTwitchId((String) channel.get("id"));
		newChannel.setLogin((String) channel.get("login"));
		newChannel.setDisplayName((String) channel.get("display_name"));
		newChannel.setType((String) channel.get("type"));
		newChannel.setBroadcasterType((String) channel.get("broadcaster_type"));
		newChannel.setDescription((String) channel.get("description"));
		newChannel.setProfileImageUrl((String) channel.get("profile_image_url"));
		newChannel.setOfflineImageUrl((String) channel.get("offline_image_url"));
		newChannel.setViewCount((Integer) channel.get("view_count"));
		newChannel.setCreatedAccountAt((String) channel.get("created_at"));
		return newChannel;
	}
	
	public Boolean isInDatabase(JSONObject channel) {
		Channel potentialChannel = channelRepository.findByLogin((String) channel.get("login"));
		return potentialChannel == null;
	}

	public Channel saveChannel(Channel channel) {
		return channelRepository.save(channel);
	}

}