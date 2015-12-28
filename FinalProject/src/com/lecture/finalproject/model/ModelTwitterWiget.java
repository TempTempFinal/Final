package com.lecture.finalproject.model;

public class ModelTwitterWiget {
	
	private String userId;
	private String mediaId;
	private String createTime;
	private String mediaType;
	private int commentCount;
	private int likeCount;
	public ModelTwitterWiget(String userId, String mediaId, String createTime, String mediaType, int commentCount,
			int likeCount) {
		super();
		this.userId = userId;
		this.mediaId = mediaId;
		this.createTime = createTime;
		this.mediaType = mediaType;
		this.commentCount = commentCount;
		this.likeCount = likeCount;
	}
	public ModelTwitterWiget() {
		super();
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getMediaType() {
		return mediaType;
	}
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	@Override
	public String toString() {
		return "ModelTwitterWiget [userId=" + userId + ", mediaId=" + mediaId + ", createTime=" + createTime
				+ ", mediaType=" + mediaType + ", commentCount=" + commentCount + ", likeCount=" + likeCount + "]";
	}
}
