public class PlanDetailDto {
	private int planId;
	private int detailId;
	private int dataNum;
	private int route;
	@Override
	public String toString() {
		return "planDetailDto [planId=" + planId + ", detailId=" + detailId + ", dataNum=" + dataNum + ", route="
				+ route + "]";
	}
	public int getPlanId() {
		return planId;
	}
	public void setPlanId(int planId) {
		this.planId = planId;
	}
	public int getDetailId() {
		return detailId;
	}
	public void setDetailId(int detailId) {
		this.detailId = detailId;
	}
	public int getDataNum() {
		return dataNum;
	}
	public void setDataNum(int dataNum) {
		this.dataNum = dataNum;
	}
	public int getRoute() {
		return route;
	}
	public void setRoute(int route) {
		this.route = route;
	}
}
