package edu.ssafy.enjoytrip.hotplace.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException; 
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
 
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
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

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.ssafy.enjoytrip.attraction.model.AttractionDto;
import edu.ssafy.enjoytrip.attraction.model.service.AttractionService;
import edu.ssafy.enjoytrip.board.model.BoardDto;
import edu.ssafy.enjoytrip.hotplace.model.HotplaceDto;
import edu.ssafy.enjoytrip.hotplace.model.HotplaceFileDto;
import edu.ssafy.enjoytrip.hotplace.model.service.HotplaceFileService;
import edu.ssafy.enjoytrip.hotplace.model.service.HotplaceService;
import edu.ssafy.enjoytrip.user.model.UserDto; 

@RestController
@RequestMapping(value = "/hotplace")
@CrossOrigin("*") // 모든 요청 허용
public class HotplaceController {
  
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private HotplaceService hotplaceService;
	
	@Autowired
	private AttractionService attractionService;
	
	@Autowired
	private HotplaceFileService hotplaceFileService;
	 
	public HotplaceController(HotplaceService hotplaceService, AttractionService attractionService,
			HotplaceFileService hotplaceFileService) {
		super();
		this.hotplaceService = hotplaceService;
		this.attractionService = attractionService;
		this.hotplaceFileService = hotplaceFileService;
	}
	
	
 
	// 핫플레이스 전체 리스트 가져오기
	// (get) http://localhost:8080/hotplace/
	@GetMapping("/")
	public Map<String,Object> list() { 
		Map<String, Object> map = new HashMap(); 
		List<HotplaceDto> list = null;
		try {
			list = hotplaceService.listHotplace();
			map.put("status", HttpStatus.OK);
			map.put("data", list);
		}
		catch (Exception e) {  
			e.printStackTrace(); 
			map.put("status", HttpStatus.NOT_FOUND);
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
	@PostMapping //(headers = "content-type=application/json")  
	public Map<String, Object> writeHotplace(@RequestParam("userId") String userId, 
            @RequestParam("name") String name, 
            @RequestParam("dataNum") String dataNum,
            @RequestParam("content") String content,
            @RequestParam("visitTime") String visitTime,
            @RequestParam("files") List<MultipartFile> files)  {    
		Map<String, Object> map = new HashMap<String, Object>();
		
		
		System.out.println(files);
		HotplaceDto hotplace = new HotplaceDto();
		hotplace.setUserId(userId);
		hotplace.setName(name);
		hotplace.setDataNum(dataNum);
		hotplace.setContent(content);
		hotplace.setVisitTime(visitTime);
		System.out.println(hotplace);
		String realPath = servletContext.getRealPath("/upload");
		String today = new SimpleDateFormat("yyMMdd").format(new Date());
		String saveFolder = realPath + File.separator + today;
		File folder = new File(saveFolder);
		if (!folder.exists()) {
			folder.mkdirs();
		}

		
		try { 
			hotplaceService.writeHotplace(hotplace);  
			String hotplaceId = hotplaceService.getHotplacId(hotplace);
			System.out.println("id : " + hotplaceId);
			
			for(MultipartFile file : files) { 
				String originalFileName = file.getOriginalFilename();
				
				if (!originalFileName.isEmpty()) {  
					HotplaceFileDto fileDTO = new HotplaceFileDto(hotplaceId, originalFileName, saveFolder);
					hotplaceFileService.registImg(fileDTO); 
					file.transferTo(new File(folder, originalFileName));
					System.out.println(originalFileName);
					System.out.println(saveFolder);
					System.out.println(folder.toString()+  File.separator + originalFileName);
					System.out.println("파일 업로드 성공");
				}
			}
			
			map.put("status", HttpStatus.OK);
		} catch (Exception e) { 
			e.printStackTrace(); 
			map.put("status", HttpStatus.NOT_FOUND);
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
	public Map<String, Object> update(@RequestBody HotplaceDto hotplaceDto) {
		Map<String, Object> map = new HashMap();
		try {
			hotplaceService.modifyHotplace(hotplaceDto); 
			map.put("status", HttpStatus.OK);
		} catch (Exception e) { 
			e.printStackTrace();
			map.put("status", HttpStatus.NOT_FOUND);
		}
		return map;
	}
	
	// (get) http://localhost:8080/hotplace/6
	@GetMapping("/{hotplaceId}") 
	public Map<String,Object> view(@PathVariable("hotplaceId") String hotplaceId) {
		System.out.println("get");
		
		Map<String,Object> map = new HashMap();
		try {
			HotplaceDto hotplaceDto = hotplaceService.getHotplace(hotplaceId);
			System.out.println(hotplaceDto); 
			List<HotplaceFileDto> hotplaceFileDto = hotplaceFileService.listImg(hotplaceId);
			System.out.println(hotplaceFileDto.toString());
			map.put("status", HttpStatus.OK);
			map.put("resdata", hotplaceDto);
			map.put("resImg", hotplaceFileDto);
		} catch (Exception e) { 
			e.printStackTrace();
			map.put("status", HttpStatus.NOT_FOUND);
		}
		return map; 
	}
	
	
	// (delete) http://localhost:8080/hotplace/6
	@DeleteMapping("/{hotplaceId}") 
	public Map<String, Object> delete(@PathVariable("hotplaceId") String hotplaceId) {
		Map<String, Object> map = new HashMap();
		try {
			hotplaceService.deleteHotplace(hotplaceId);
			map.put("status", HttpStatus.OK);
		} catch (Exception e) { 
			e.printStackTrace();
			map.put("status", HttpStatus.NOT_FOUND);
		}
		return map;
	} 
  
 

}
