

import java.util.List;

public class PlanDto {
	private String userId;
	private int id;
	private String name;
	private String startDate;
	private String endDate;
	private List<PlanDetailDto>list;
	@Override
	public String toString() {
		return "planDto [userId=" + userId + ", id=" + id + ", name=" + name + ", startDate=" + startDate + ", endDate="
				+ endDate + ", list=" + list + "]";
	}
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
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public List<PlanDetailDto> getList() {
		return list;
	}
	public void setList(List<PlanDetailDto> list) {
		this.list = list;
	}
}
