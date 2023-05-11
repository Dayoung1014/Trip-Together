import java.util.Date;

public class CommentDto {
	private String userId;
	private int boardShareTripId;
	private int id;
	private String content;
	private String writeTime;
	private int like;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getBoardShareTripId() {
		return boardShareTripId;
	}
	public void setBoardShareTripId(int boardShareTripId) {
		this.boardShareTripId = boardShareTripId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriteTime() {
		return writeTime;
	}
	public void setWriteTime(String writeTime) {
		this.writeTime = writeTime;
	}
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	@Override
	public String toString() {
		return "CommentDto [userId=" + userId + ", boardShareTripId=" + boardShareTripId + ", id=" + id + ", content="
				+ content + ", writeTime=" + writeTime + ", like=" + like + "]";
	}
	
	
	
}
