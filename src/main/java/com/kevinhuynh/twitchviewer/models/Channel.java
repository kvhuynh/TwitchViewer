package com.kevinhuynh.twitchviewer.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="channels")
public class Channel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String twitchId;


	private String login;
	
	private String displayName;
	
	private String type;
	
	private String broadcasterType;

	@Column(name = "description", columnDefinition = "LONGTEXT")
	private String description;

	private String profileImageUrl;

	private String offlineImageUrl;

	private Integer viewCount;

	private Boolean isFavorited;

	private String dateFollowed;

	private String createdAccountAt;

	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;

	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
	
	// @ManyToOne(fetch = FetchType.LAZY)
	// @JoinColumn(name="user_id")
	// private User user;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name = "users_channels",
		joinColumns = @JoinColumn(name = "channel_id"),
		inverseJoinColumns = @JoinColumn(name = "user_id")
	)
	private List<User> users;

	@OneToMany(mappedBy="channel", fetch = FetchType.LAZY)
	private List<Comment> comments;

	
    @PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
	
	public Channel() {}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTwitchId() {
		return twitchId;
	}

	public void setTwitchId(String twitchId) {
		this.twitchId = twitchId;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBroadcasterType() {
		return broadcasterType;
	}

	public void setBroadcasterType(String broadcasterType) {
		this.broadcasterType = broadcasterType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProfileImageUrl() {
		return profileImageUrl;
	}

	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}

	public String getOfflineImageUrl() {
		return offlineImageUrl;
	}

	public void setOfflineImageUrl(String offlineImageUrl) {
		this.offlineImageUrl = offlineImageUrl;
	}

	public Integer getViewCount() {
		return viewCount;
	}

	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}

	public String getDateFollowed() {
		return dateFollowed;
	}

	public void setDateFollowed(String dateFollowed) {
		this.dateFollowed = dateFollowed;
	}

	public String getCreatedAccountAt() {
		return createdAccountAt;
	}

	public void setCreatedAccountAt(String createdAccountAt) {
		this.createdAccountAt = createdAccountAt;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Boolean getIsFavorited() {
		return isFavorited;
	}

	public void setIsFavorited(Boolean isFavorited) {
		this.isFavorited = isFavorited;
	}
}