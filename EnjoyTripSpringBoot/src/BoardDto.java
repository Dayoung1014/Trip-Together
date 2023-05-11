
public class BoardDto {

	private String userId;
	private int id;
	private String title;
	private String content;
	private int hit;
	private String writetTime;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getWritetTime() {
		return writetTime;
	}
	public void setWritetTime(String writetTime) {
		this.writetTime = writetTime;
	}
	@Override
	public String toString() {
		return "BoardDto [userId=" + userId + ", id=" + id + ", title=" + title + ", content=" + content + ", hit="
				+ hit + ", writetTime=" + writetTime + "]";
	}
	
	
}
