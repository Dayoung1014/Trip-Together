package edu.ssafy.enjoytrip.user.model;

public class UserDto {
	private String id;
	private String name;
	private String pwd;
	private String email;
	private int manager;
	private String joindate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getManager() {
		return manager;
	}
	public void setManager(int manager) {
		this.manager = manager;
	}
	public String getJoindate() {
		return joindate;
	}
	public void setJoindate(String joindate) {
		this.joindate = joindate;
	}
	@Override
	public String toString() {
		return "UserDto [id=" + id + ", name=" + name + ", pwd=" + pwd + ", email=" + email + ", manager=" + manager
				+ ", joindate=" + joindate + "]";
	}
	

}
