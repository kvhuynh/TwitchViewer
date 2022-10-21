package com.kevinhuynh.twitchviewer.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kevinhuynh.twitchviewer.models.Channel;
import com.kevinhuynh.twitchviewer.repositories.UserRepository;

@Service
public class ApiService {
    
    private final String AUTHORIZATION_CODE = "Bearer k1nkffmlqxqo8wg5x9zucbsvzhs3lu";
    private final String CLIENT_ID = "qi7c5cag9fo5up9c8ssprveszclyah";

    // can modularize further by truncating after .tv/helix
	private final String GET_USER_ID = "https://api.twitch.tv/helix/users/?login=";
	private final String GET_FOLLOWS_BY_ID = "https://api.twitch.tv/helix/users/follows?from_id=";
    private final String GET_CHANNEL_EMOTES_BY_ID = "https://api.twitch.tv/helix/chat/emotes?broadcaster_id=";
    private final String GET_CURRENT_POPULAR = "https://api.twitch.tv/helix/streams?";

    private String tempTwitchId = "";

    @Autowired
	private UserRepository userRepository;

    private ArrayList<Objects> currentFollowing = new ArrayList<Objects>();

	public JSONObject queryWrapper(String operation, String queryData) throws IOException {
		switch (operation) {
			case "getId":
				return getTwitchData(GET_USER_ID + queryData);

            case "getLiveData":
                return getTwitchData(GET_CURRENT_POPULAR + queryData);

			case "getPopular":
				return getTwitchData(GET_CURRENT_POPULAR + "first=100");

			case "getEmotes":
				return getTwitchData(GET_CHANNEL_EMOTES_BY_ID + queryData);
		}

		return null;
	}


	// broad class that takes in an api request and returns the response as a JSONObject
	public JSONObject getTwitchData(String queryInformation) {
		try {
            
			URL url = new URL(queryInformation);
			HttpURLConnection http = (HttpURLConnection)url.openConnection();
			http.setRequestProperty("Authorization", AUTHORIZATION_CODE);
			http.setRequestProperty("Client-Id", CLIENT_ID);

			InputStream inputStream = http.getInputStream();

			BufferedReader in = new BufferedReader(
				new InputStreamReader(
					inputStream, "UTF-8")); // fixed utf-8 error here
		
			StringBuilder response = new StringBuilder();
			String currentLine;
		
			while ((currentLine = in.readLine()) != null) 
				response.append(currentLine);
		
			in.close();

			JSONObject obj = new JSONObject(response.toString());

			return obj;
		} catch (IOException error) {
			System.out.println(error);
		}

		return null;
	}


    public ArrayList<Object> parsePopularChannels(JSONObject popularChannels) {
        ArrayList<Object> listData = new ArrayList<Object>();
        JSONArray channelsData = (JSONArray) popularChannels.get("data");
        if (channelsData != null) {
            for (int i = 0; i < channelsData.length(); i++) {
                listData.add(channelsData.get(i));
            }
        }
        return listData;
    }

    public JSONObject getEmotes(String twitchId) throws IOException {
        String uri = GET_CHANNEL_EMOTES_BY_ID + twitchId;

		try {
			URL url = new URL(uri);
			HttpURLConnection http = (HttpURLConnection)url.openConnection();
			http.setRequestProperty("Authorization", AUTHORIZATION_CODE);
			http.setRequestProperty("Client-Id", CLIENT_ID);
			
			InputStream inputStream = http.getInputStream();
			BufferedReader in = new BufferedReader(
				new InputStreamReader(
					inputStream, "UTF-8")); // fixed utf-8 error here
		
			StringBuilder response = new StringBuilder();
			String currentLine;
		
			while ((currentLine = in.readLine()) != null) 
				response.append(currentLine);
		
			in.close();

			JSONObject obj = new JSONObject(response.toString());

			return obj;
        } catch (MalformedURLException error) {
                System.out.println(error);
            }

        return null;
    }

    // get followers endpoint is being deprecated soon - only moderators of a channel will be able to see
    public JSONObject getFollows(String userId, String pagination) throws IOException{
		String uri = GET_FOLLOWS_BY_ID + userId + "&first=100" + "&after="+ pagination;
		tempTwitchId = userId;
        System.out.println(uri);
		try {
			URL url = new URL(uri);
			HttpURLConnection http = (HttpURLConnection)url.openConnection();
			http.setRequestProperty("Authorization", AUTHORIZATION_CODE);
			http.setRequestProperty("Client-Id", CLIENT_ID);
			
			InputStream inputStream = http.getInputStream();
			BufferedReader in = new BufferedReader(
				new InputStreamReader(
					inputStream, "UTF-8")); // fixed utf-8 error here
		
			StringBuilder response = new StringBuilder();
			String currentLine;
		
			while ((currentLine = in.readLine()) != null) 
				response.append(currentLine);
		
			in.close();

			JSONObject obj = new JSONObject(response.toString());
			return obj;
		} catch (MalformedURLException error) {
			System.out.println(error);
		}
		return null;
	}

    public void parseEmotes(JSONObject channelEmotes) {
        JSONArray test = (JSONArray) channelEmotes.get("data");
        System.out.println(test);
    }


    // public void parseFollowers(JSONObject following) throws IOException {

    //     JSONObject paginationCursor;
    //     String pagination = "";
    //     JSONArray channelsArray;

    //     channelsArray = allChannels(following);
    //     // JSONObject channelObjects = (JSONObject) channelsArray.get(0);

    //     double loopCondition = Math.ceil((int) following.get("total") / 100.00);

    //     for (int i = 0; i < loopCondition; i++) {
    //         paginationCursor = (JSONObject) following.get("pagination");
    //         if (paginationCursor.has("cursor") != false ) {
    //             pagination = (String) paginationCursor.get("cursor");
    //         }

    //         for (Object obj : channelsArray) {
    //             JSONObject channel = (JSONObject) obj;
    //             // createChannel();
    //             JSONObject channelData = getChannelData((String) channel.get("to_login"));
    //             System.out.println(channelData);
    //             // System.out.println(channel.get("to_login")); // i should be creating the following channels here

    //         }

    //         following = getFollows(tempTwitchId, pagination);
    //         channelsArray = allChannels(following);

    //     }
    // }

    // public JSONArray allChannels(JSONObject followingObject) {
    //     JSONArray channelsArray = (JSONArray) followingObject.get("data");
    //     return channelsArray;
    // }

    // public Channel createChannel(
    //     String twitchId, String login, String displayName, String type, 
    //     String broadcasterType, String description, String profileImageUrl, 
    //     String offlineImageUrl, Integer viewCount, Boolean isFavorited, 
    //     String dateFollowed, String accountCreatedAt
    //     ) {

    //     Channel oneChannel = new Channel();
        
    //     oneChannel.setTwitchId(twitchId);
    //     oneChannel.setDisplayName(displayName);
    //     oneChannel.setType(broadcasterType);
    //     oneChannel.setBroadcasterType(broadcasterType);
    //     oneChannel.setDescription(description);
    //     oneChannel.setProfileImageUrl(profileImageUrl);
    //     oneChannel.setOfflineImageUrl(offlineImageUrl);
    //     oneChannel.setViewCount(viewCount);
    //     oneChannel.setIsFavorited(isFavorited);
    //     oneChannel.setDateFollowed(dateFollowed);
    //     oneChannel.setCreatedAccountAt(accountCreatedAt);


    //     return oneChannel;
    // }

}
