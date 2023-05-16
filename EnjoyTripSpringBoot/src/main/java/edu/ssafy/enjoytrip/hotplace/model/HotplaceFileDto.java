package edu.ssafy.enjoytrip.hotplace.model;

import org.springframework.web.multipart.MultipartFile;

public class HotplaceFileDto {
	private String hotplaceId;
	private String fid;
	private String name;
	private String path;
 
	public HotplaceFileDto(String hotplaceId, String name, String path) {
		super();
		this.hotplaceId = hotplaceId;
 
		this.name = name;
		this.path = path;
	}
	public String getHotplaceId() {
		return hotplaceId;
	}
	public void setHotplaceId(String hotplaceId) {
		this.hotplaceId = hotplaceId;
	}
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	@Override
	public String toString() {
		return "HotplaceFileDto [hotplaceId=" + hotplaceId + ", fid=" + fid + ", name=" + name + ", path=" + path + "]";
	}
 
	
	
	
}
