package edu.ssafy.enjoytrip.user.model.mapper;

import java.sql.SQLException;
import java.util.*;

import org.apache.ibatis.annotations.Mapper;

import edu.ssafy.enjoytrip.user.model.UserDto;

@Mapper
public interface UserMapper {
	int idCheck(String userId) throws SQLException;
	void joinUser(UserDto userDto) throws SQLException;
	UserDto loginUser(UserDto userDto) throws SQLException;
	
	/* Admin */
	List<UserDto> listUser(Map<String, Object> map) throws SQLException;
	UserDto getUser(String userId) throws SQLException;
	void updateUser(UserDto userDto) throws SQLException;
	void deleteUser(String userId) throws SQLException;
}
