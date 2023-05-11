package edu.ssafy.enjoytrip.attraction.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import edu.ssafy.enjoytrip.attraction.model.AttractionDto;
import edu.ssafy.enjoytrip.attraction.model.GugunDto;
import edu.ssafy.enjoytrip.attraction.model.SidoDto;
import edu.ssafy.enjoytrip.attraction.model.service.AttractionService;
import edu.ssafy.enjoytrip.user.model.UserDto;
import edu.ssafy.enjoytrip.user.model.service.UserService;

@Controller
@CrossOrigin("*")
@RequestMapping("/attraction2")
public class AttractionController {
private final Logger logger = LoggerFactory.getLogger(AttractionController.class);
	
	private AttractionService attractionService;
 

	public AttractionController(AttractionService attractionService) {
		super();
		this.attractionService = attractionService;
	} 
	
	@GetMapping("/search")
	public String search() {
		return "attraction/search";
	}

	@ResponseBody
	@GetMapping("/getSido") 
	public List<SidoDto> getSido() {
		List<SidoDto> list = attractionService.listSido();  
		return list;
	}
	
	@ResponseBody
	@GetMapping("/getGugun/{currAreaNum}") 
	public List<GugunDto> getGugun(@PathVariable("currAreaNum") String sidoCode) { 
		List<GugunDto> list = attractionService.listGugun(sidoCode);  
		return list;
	}
	
	
	@ResponseBody
	@GetMapping("/getTrip/{type}/{currAreaNum}/{currSigunguNum}") 
	public List<AttractionDto> getTrip(@PathVariable("type") String type, @PathVariable("currAreaNum") String sidoCode, @PathVariable("currSigunguNum") String gugunCode) throws SQLException {
		List<AttractionDto> list = attractionService.listAttraction(type, sidoCode, gugunCode); 
		return list;
	}
	
	@ResponseBody
	@GetMapping("/getAttraction/{contentId}") 
	public AttractionDto getAttraction(@PathVariable("contentId") String contentId) throws SQLException {
		AttractionDto attractionDto = attractionService.getAttraction(contentId);
		return attractionDto;
	}
}
