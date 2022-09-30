package com.kevinhuynh.twitchviewer.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.kevinhuynh.twitchviewer.models.Channel;
import com.kevinhuynh.twitchviewer.repositories.ChannelRepository;

@Service
public class ChannelService {
	@Autowired
	private ChannelRepository channelRepository;

	// ========== Create / Update ==========
	public Channel createClass(Channel channel) {
		return channelRepository.save(channel);
	}
	
	// ========== Read ==========
	public List<Channel> getAll() {
		return channelRepository.findAll();
	}
	
	public Channel getOne(Long id) {
		return channelRepository.findById(id).orElse(null);
	}
	
	// ========== Delete ==========

	public void delete(Long id) {
		channelRepository.deleteById(id);
	}




	
}