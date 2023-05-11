package edu.ssafy.enjoytrip.plan.model;

import java.util.List;

public class PlanDto {

	private int id;
	private String userId;
	private String name;
	private String startDate;
	private String endDate;
	@Override
	public String toString() {
		return "PlanDto [id=" + id + ", userId=" + userId + ", name=" + name + ", startDate=" + startDate + ", endDate="
				+ endDate + "]";
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
	

	

}
