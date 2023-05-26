package edu.ssafy.enjoytrip.user.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import edu.ssafy.enjoytrip.user.model.UserDto;
import edu.ssafy.enjoytrip.user.model.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	private UserMapper userMapper;

	public UserServiceImpl(UserMapper userMapper) {
		super();
		this.userMapper = userMapper;
	}

	@Override
	public int idCheck(String userId) throws Exception {
//		return sqlSession.getMapper(UserMapper.class).idCheck(userId);
		return userMapper.idCheck(userId);
	}

	@Override
	public void joinMember(UserDto userDto) throws Exception {
//		sqlSession.getMapper(UserMapper.class).joinMember(userDto);
		userMapper.joinUser(userDto);
	}

	@Override
	public UserDto loginMember(UserDto userDto) throws Exception { 
//		return sqlSession.getMapper(UserMapper.class).loginMember(map);
		return userMapper.loginUser(userDto);
	}
	
	/* ADMIN */
	@Override
	public List<UserDto> listMember(Map<String, Object> map) throws Exception {
		return userMapper.listUser(map);
	}

	@Override
	public UserDto getMember(String userId) throws Exception {
		return userMapper.getUser(userId);
	}

	@Override
	public void updateMember(UserDto userDto) throws Exception {
		userMapper.updateUser(userDto);
	}

	@Override
	public void deleteMember(String userId) throws Exception {
		userMapper.deleteUser(userId);		
	}
 
	@Override
	public void saveRefreshToken(String userId, String refreshToken) throws Exception {
	    Map<String, String> map = new HashMap<String, String>();
	    map.put("userid", userId);
	    map.put("token", refreshToken);  
		userMapper.saveRefreshToken(map);
	}

	@Override
	public Object getRefreshToken(String userId) throws Exception {
		return userMapper.getRefreshToken(userId);
	    //return sqlSession.getMapper(MemberMapper.class).getRefreshToken(userid);
	}

	@Override
	public void deleRefreshToken(String userId) throws Exception {
	    Map<String, String> map = new HashMap<String, String>();
	    map.put("userid", userId);
	    map.put("token", null);
	    userMapper.deleteRefreshToken(map);
//	    sqlSession.getMapper(MemberMapper.class).deleteRefreshToken(map);
	}
	
	@Override
	public UserDto userInfo(String userId) throws Exception {
	    return userMapper.userInfo(userId);
	}
		
}
