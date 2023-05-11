
public class LikestampDto {
	private String userId;
	private int hotplaceId;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getHotplaceId() {
		return hotplaceId;
	}
	public void setHotplaceId(int hotplaceId) {
		this.hotplaceId = hotplaceId;
	}
	@Override
	public String toString() {
		return "LikestampDto [userId=" + userId + ", hotplaceId=" + hotplaceId + "]";
	}
	
	
}
