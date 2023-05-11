package edu.ssafy.enjoytrip.planDetail.model;

public class PlanDetailDto {
	private String planId;
	private String detailId;
	private String dataNum;
	private String route; //다음에 올 여행지의 id 저장 
	public String getPlanId() {
		return planId;
	}
	public void setPlanId(String planId) {
		this.planId = planId;
	}
	public String getDetailId() {
		return detailId;
	}
	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}
	public String getDataNum() {
		return dataNum;
	}
	public void setDataNum(String dataNum) {
		this.dataNum = dataNum;
	}
	public String getRoute() {
		return route;
	}
	public void setRoute(String route) {
		this.route = route;
	}
	@Override
	public String toString() {
		return "PlanDetailDto [planId=" + planId + ", detailId=" + detailId + ", dataNum=" + dataNum + ", route="
				+ route + "]";
	}

}
