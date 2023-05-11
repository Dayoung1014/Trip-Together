package edu.ssafy.enjoytrip.hotplace.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.ssafy.enjoytrip.attraction.model.AttractionDto;
import edu.ssafy.enjoytrip.attraction.model.service.AttractionService;
import edu.ssafy.enjoytrip.board.model.BoardDto;
import edu.ssafy.enjoytrip.hotplace.model.HotplaceDto;
import edu.ssafy.enjoytrip.hotplace.model.service.HotplaceService;
import edu.ssafy.enjoytrip.user.model.UserDto; 

@RestController
@RequestMapping(value = "/hotplace")
@CrossOrigin("*") // 모든 요청 허용
public class HotplaceController {
 
	@Autowired
	private HotplaceService hotplaceService;
	
	@Autowired
	private AttractionService attractionService;
	 
	public HotplaceController(HotplaceService hotplaceService, AttractionService attractionService) {
		super();
		this.hotplaceService = hotplaceService;
		this.attractionService = attractionService;
	}


	// 핫플레이스 전체 리스트 가져오기
	// (get) http://localhost:8080/hotplace/
	@GetMapping("/")
	public Map<String,Object> list() { 
		Map<String, Object> map = new HashMap(); 
		List<HotplaceDto> list = null;
		try {
			list = hotplaceService.listHotplace();
			map.put("msg", "핫플레이스 리스트 조회 성공");
			map.put("data", list);
		}
		catch (Exception e) {  
			e.printStackTrace(); 
			map.put("resmsg", "핫플레이스 리스트 조회 실패");
		}
		return map;
	} 
	
	
	
	// (post) http://localhost:8080/hotplace/
	/*
	 * {"userId" : "ssafy", 
		"name": "test_name",
		"content": "test_content",
		"visitTime": "2023-05-02 00:00:00", 
		"img": "",
		"dataNum": "134007"}
	 * */
	@PostMapping(headers = "content-type=application/json")  
	public Map<String, String> writeHotplace(@RequestBody HotplaceDto hotplaceDto)  {
		Map<String, String> map = new HashMap();
		try {
			hotplaceService.writeHotplace(hotplaceDto);  
			map.put("msg", "입력성공");
		} catch (Exception e) { 
			e.printStackTrace();
			map.put("msg", "입력실패");
		}
		return map;
	}

	//(put) http://localhost:8080/hotplace/
	/*
	 * {"userId" : "ssafy", 
		"id": 6,
		"name": "modi",
		"content": "modi",
		"visitTime": "2023-05-03 00:00:00", 
		"img": "",
		"dataNum": "134007"}
	 * */
	@PutMapping("/") 
	public Map<String, String> update(@RequestBody HotplaceDto hotplaceDto) {
		Map<String, String> map = new HashMap();
		try {
			hotplaceService.modifyHotplace(hotplaceDto); 
			map.put("resmsg", "수정성공");
		} catch (Exception e) { 
			e.printStackTrace();
			map.put("resmsg", "수정실패");
		}
		return map;
	}
	
	// (get) http://localhost:8080/hotplace/6
	@GetMapping("/{hotplaceId}") 
	public Map<String,Object> view(@PathVariable("hotplaceId") String hotplaceId) {
		
		Map<String,Object> map = new HashMap();
		try {
			HotplaceDto hotplaceDto = hotplaceService.getHotplace(hotplaceId);
			map.put("resmsg", "조회성공");
			map.put("resdata", hotplaceDto);
		} catch (Exception e) { 
			e.printStackTrace();
			map.put("resmsg", "조회실패");
		}
		return map; 
	}
	
	
	// (delete) http://localhost:8080/hotplace/6
	@DeleteMapping("/{hotplaceId}") 
	public Map<String, String> delete(@PathVariable("hotplaceId") String hotplaceId) {
		Map<String, String> map = new HashMap();
		try {
			hotplaceService.deleteHotplace(hotplaceId);
			map.put("resmsg", "삭제성공");
		} catch (Exception e) { 
			e.printStackTrace();
			map.put("resmsg", "삭제실패");
		}
		return map;
	} 
  
 

}
