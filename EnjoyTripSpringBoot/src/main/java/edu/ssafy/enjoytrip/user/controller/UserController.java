package edu.ssafy.enjoytrip.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.ssafy.enjoytrip.user.model.UserDto;
import edu.ssafy.enjoytrip.user.model.service.UserService;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin("*") // 모든 요청 허용
public class UserController {
private final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService; 
	
	// (get) http://localhost:8080/user/가입할아이디
	@GetMapping("/{userid}") 
	public Map<String, String> idCheck(@PathVariable("userid") String userId) {
		Map<String, String> map = new HashMap();
		logger.debug("idCheck userid : {}", userId);
		try {
			int cnt = userService.idCheck(userId);
			map.put("msg", "아이디 중복 확인 성공 (사용 가능)");
		} catch (Exception e) { 
			e.printStackTrace();
			map.put("msg", "아이디 중복 확인 실패");
		} 
		return map;
	}
	
	
	// (post) http://localhost:8080/user/join
	/*
	 * {"id" : "test",  
		"name": "test",
		"pwd": "test",
		"email": "test",  
		"manager": "0"}
	 * */
	@PostMapping(value="/join", headers = "content-type=application/json")   
	public Map<String, String> join(@RequestBody UserDto userDto) {
		logger.debug("userDto info : {}", userDto);
		Map<String, String> map = new HashMap();
		try {
			userService.joinMember(userDto);  
			map.put("msg", "가입 성공");
		} catch (Exception e) { 
			e.printStackTrace();
			map.put("msg", "가입 실패");
		}
		return map;
	}
	
	
	// (post) http://localhost:8080/user/login
	/*
	 * {"name": "test",
		"pwd": "test" }
	 */
	@PostMapping(value="/login", headers = "content-type=application/json")   
	public Map<String, String> login(@RequestBody UserDto userDto) {
		logger.debug("login map : {}", userDto); 
		Map<String, String> map = new HashMap();
		try {
			userService.loginMember(userDto);  
			map.put("msg", "로그인 성공");
		} catch (Exception e) { 
			e.printStackTrace();
			map.put("msg", "로그인 실패");
		}
		return map;
	}
	
	
	// (put) http://localhost:8080/user/
	/*
	 * {"id" : "test",  
		"name": "modi",
		"pwd": "modi",
		"email": "modi",  
		"manager": "1"}
	 */
	@PutMapping("/")
	public  Map<String, String> userModify(@RequestBody UserDto userDto) {
		Map<String, String> map = new HashMap();
		try {
			userService.updateMember(userDto); 
			map.put("resmsg", "user 정보 수정 성공");
		} catch (Exception e) { 
			e.printStackTrace();
			map.put("resmsg", "user 정보 수정 실패");
		}
		return map;
	}
	
	// (delete) http://localhost:8080/user/지울아이디
	@DeleteMapping("/{userId}")
	public Map<String, String> delete(@PathVariable("userId") String userId) {
		Map<String, String> map = new HashMap();
		try {
			userService.deleteMember(userId);
			map.put("resmsg", "user 삭제 성공");
		} catch (Exception e) { 
			e.printStackTrace();
			map.put("resmsg", "user 삭제 실패");
		}
		return map;
	}
}
