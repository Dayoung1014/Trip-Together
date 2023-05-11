
public class HotplaceDto {
	private String userId;
	private int id;
	private String name;
	private String content;
	private String visitTime;
	private String writeTime;
	private String img;
	private int dataNum;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getVisitTime() {
		return visitTime;
	}
	public void setVisitTime(String visitTime) {
		this.visitTime = visitTime;
	}
	public String getWriteTime() {
		return writeTime;
	}
	public void setWriteTime(String writeTime) {
		this.writeTime = writeTime;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getDataNum() {
		return dataNum;
	}
	public void setDataNum(int dataNum) {
		this.dataNum = dataNum;
	}
	@Override
	public String toString() {
		return "HotplaceDto [userId=" + userId + ", id=" + id + ", name=" + name + ", content=" + content
				+ ", visitTime=" + visitTime + ", writeTime=" + writeTime + ", img=" + img + ", dataNum=" + dataNum
				+ "]";
	}
	
	
}
