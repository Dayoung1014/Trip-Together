package edu.ssafy.enjoytrip.board.model;

import java.util.List;

public class BoardDto {

	private int id;
	private String userId;
	private String userName;
	private String title;
	private String content;
	private int hit;
	private String writeTime;
	private List<FileInfoDto> fileInfos;
	
	@Override
	public String toString() {
		return "BoardDto [id=" + id + ", userId=" + userId + ", userName=" + userName + ", title=" + title
				+ ", content=" + content + ", hit=" + hit + ", writeTime=" + writeTime + ", fileInfos=" + fileInfos
				+ "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getWriteTime() {
		return writeTime;
	}
	public void setWriteTime(String writeTime) {
		this.writeTime = writeTime;
	}
	public List<FileInfoDto> getFileInfos() {
		return fileInfos;
	}
	public void setFileInfos(List<FileInfoDto> fileInfos) {
		this.fileInfos = fileInfos;
	}

	

}
