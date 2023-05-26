package edu.ssafy.enjoytrip.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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

import edu.ssafy.enjoytrip.user.EmailDto;
import edu.ssafy.enjoytrip.user.model.UserDto;
import edu.ssafy.enjoytrip.user.model.service.EmailService;
import edu.ssafy.enjoytrip.user.model.service.JwtServiceImpl;
import edu.ssafy.enjoytrip.user.model.service.UserService;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin("*") // 모든 요청 허용
public class UserController_token {
	private final Logger logger = LoggerFactory.getLogger(UserController_token.class);
	
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private UserService userService; 
	
	@Autowired
	private JwtServiceImpl jwtService;
	
	@Autowired
    private EmailService emailService;
	
	@Autowired
	private ResourceLoader resourceLoader;
	 
	@Autowired
	private JavaMailSender javaMailSender;

	private String loadEmailTemplate(String templateName) throws IOException {
	    Resource resource = resourceLoader.getResource("classpath:/static/html/" + templateName);
	    return new String(Files.readAllBytes(resource.getFile().toPath()));
	}
	
	@PostMapping("/sendEmail")
	public void sendEmail(@RequestBody EmailDto emailDto) throws IOException, MessagingException {
	    // 이메일 템플릿 로딩
	    String template = loadEmailTemplate("tempPasswordEmailTemplate.html");

	    // 템플릿에 필요한 정보 채우기
	    String emailContent = template.replace("{{ temporaryPassword }}", emailDto.getText());

	    // 이메일 전송
	    //emailService.sendEmail(emailDto.getTo(), emailDto.getSubject(), emailContent);
	    
	    // 이메일 전송
	    MimeMessage message = javaMailSender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message, true);
	    helper.setTo(emailDto.getTo());
	    helper.setSubject(emailDto.getSubject());
	    helper.setText(emailContent, true); // HTML 형식으로 설정

	    javaMailSender.send(message);
	}

	
//    @PostMapping("/sendEmail")
//    public void sendEmail(@RequestBody EmailDto emailDto) {
//    	System.out.println(emailDto);
//        emailService.sendEmail(emailDto.getTo(), emailDto.getSubject(), emailDto.getText());
//    }
    
	@GetMapping("/getUserInfo/{userid}")
	public ResponseEntity<Map<String, Object>> getUserInfo(
			@PathVariable("userid") String userId,
			HttpServletRequest request) { 
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		try {
//			로그인 사용자 정보.
			UserDto memberDto = userService.userInfo(userId);
			resultMap.put("userInfo", memberDto);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			logger.error("정보조회 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		} 
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
    
	@GetMapping("/info/{userid}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("userid") String userId,
			HttpServletRequest request) {
//		logger.debug("userid : {} ", userid);
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		try {
//			로그인 사용자 정보.
			UserDto memberDto = userService.userInfo(userId);
			resultMap.put("userInfo", memberDto);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			logger.error("정보조회 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		if (jwtService.checkToken(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");

		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	// (get) http://localhost:8080/user/가입할아이디
	@GetMapping("/{userid}") 
	public Map<String, String> idCheck(@PathVariable("userid") String userId) {
		Map<String, String> map = new HashMap();
		logger.debug("idCheck userid : {}", userId);
		try {
			int cnt = userService.idCheck(userId);
			if(cnt>0) {
				map.put("msg", "No");
			}
			else {
				map.put("msg", "Yes");
			}
			
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
	public Map<String, HttpStatus>  join(@RequestBody UserDto userDto) {
		logger.debug("userDto info : {}", userDto);
		Map<String, HttpStatus> map = new HashMap();
		try {
			userService.joinMember(userDto);  
			map.put("status", HttpStatus.OK); 
		} catch (Exception e) { 
			e.printStackTrace();
			map.put("status", HttpStatus.BAD_REQUEST);
		}
		return map;
	}
	
	
	// (post) http://localhost:8080/user/login
	/*
	 * {"id": "test",
		"pwd": "test" }
	 */
	@PostMapping(value="/login")    //, headers = "content-type=application/json"
	public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, Object> obj) {
		System.out.println("Login Controller 들어옴");
		UserDto inputDto = new UserDto();
		inputDto.setId(obj.get("userid").toString());
		inputDto.setPwd(obj.get("userpwd").toString()); 
		Map<String, Object> map = new HashMap();
		HttpStatus status = null;
		try {
			UserDto returnUser = userService.loginMember(inputDto);
			System.out.println(inputDto);
            if(returnUser != null) {  
				String accessToken = jwtService.createAccessToken("userid", returnUser.getId());// key, data
				System.out.println(accessToken);
				String refreshToken = jwtService.createRefreshToken("userid", returnUser.getId());// key, data
				System.out.println(refreshToken);
				userService.saveRefreshToken(inputDto.getId(), refreshToken);
				logger.debug("로그인 accessToken 정보 : {}", accessToken);
				logger.debug("로그인 refreshToken 정보 : {}", refreshToken);
				map.put("access-token", accessToken);
				map.put("refresh-token", refreshToken);
				map.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				map.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			map.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(map, status);
	}
	
	
	@GetMapping("/logout/{userid}")
	public ResponseEntity<?> removeToken(@PathVariable("userid") String userid) {
	    Map<String, Object> resultMap = new HashMap<>();
	    HttpStatus status = HttpStatus.ACCEPTED;
	    try {
	       userService.deleRefreshToken(userid);
	        resultMap.put("message", SUCCESS);
	        status = HttpStatus.ACCEPTED;
	    } catch (Exception e) {
	        logger.error("로그아웃 실패 : {}", e);
	        resultMap.put("message", e.getMessage());
	        status = HttpStatus.INTERNAL_SERVER_ERROR;
	    }
	    return new ResponseEntity<Map<String, Object>>(resultMap, status);

	}
	
	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody UserDto userDto, HttpServletRequest request)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String token = request.getHeader("refresh-token");
		logger.debug("token : {}, memberDto : {}", token, userDto);
		if (jwtService.checkToken(token)) { //db에서 rt 얻어옴
			if (token.equals(userService.getRefreshToken(userDto.getId()))) {
				String accessToken = jwtService.createAccessToken("userid", userDto.getId());
				logger.debug("token : {}", accessToken);
				logger.debug("정상적으로 액세스토큰 재발급!!!");
				resultMap.put("access-token", accessToken);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			}
		} else {
			logger.debug("리프레쉬토큰도 사용불가 !!!!!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
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
		System.out.println(userDto);
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
			map.put("status", "user 삭제 성공");
		} catch (Exception e) { 
			e.printStackTrace();
			map.put("status", "user 삭제 실패");
		}
		return map;
	}
}
