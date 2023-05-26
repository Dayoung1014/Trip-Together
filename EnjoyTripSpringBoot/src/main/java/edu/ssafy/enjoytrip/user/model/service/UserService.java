package edu.ssafy.enjoytrip.user.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import edu.ssafy.enjoytrip.user.model.UserDto;

public interface UserService {
	int idCheck(String userId) throws Exception;
	void joinMember(UserDto userDto) throws Exception;
	UserDto loginMember(UserDto userDto) throws Exception;
	void deleteMember(String userId) throws Exception;
	
	public UserDto userInfo(String userId) throws Exception;
	public void saveRefreshToken(String userid, String refreshToken) throws Exception;
	public Object getRefreshToken(String userid) throws Exception;
	public void deleRefreshToken(String userid) throws Exception;
	
	/* Admin */
	List<UserDto> listMember(Map<String, Object> map) throws Exception;
	UserDto getMember(String userId) throws Exception;
	void updateMember(UserDto userDto) throws Exception; 
}
