package edu.ssafy.enjoytrip.plan.model;

import java.util.List;

public class PlanMemberDto {

	private int planId;
	private String userId;
	private int id;
	private boolean access;
	private boolean confirm;
	public int getPlanId() {
		return planId;
	}
	public void setPlanId(int planId) {
		this.planId = planId;
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
	public boolean isAccess() {
		return access;
	}
	public void setAccess(boolean access) {
		this.access = access;
	}
	public boolean isConfirm() {
		return confirm;
	}
	public void setConfirm(boolean confirm) {
		this.confirm = confirm;
	}
	@Override
	public String toString() {
		return "PlanMemberDto [planId=" + planId + ", userId=" + userId + ", id=" + id + ", access=" + access
				+ ", confirm=" + confirm + "]";
	}
	
	
	

}
