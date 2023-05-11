package edu.ssafy.enjoytrip.attraction.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
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
import org.springframework.web.servlet.ModelAndView;

import edu.ssafy.enjoytrip.attraction.model.AttractionDto;
import edu.ssafy.enjoytrip.attraction.model.GugunDto;
import edu.ssafy.enjoytrip.attraction.model.SidoDto;
import edu.ssafy.enjoytrip.attraction.model.service.AttractionService;
import edu.ssafy.enjoytrip.attraction.model.AttractionDto;
import edu.ssafy.enjoytrip.attraction.model.service.AttractionService;
import edu.ssafy.enjoytrip.user.model.UserDto;
import edu.ssafy.enjoytrip.user.model.service.UserService;

@RestController
@RequestMapping("/attraction")
@CrossOrigin("*") // 모든 요청 허용
public class RestAttractionController {
private final Logger logger = LoggerFactory.getLogger(RestAttractionController.class);
	
@Autowired
private ServletContext servletContext;

private AttractionService service;

public RestAttractionController(AttractionService attractionService) {
	super();
	this.service = attractionService;
}



@GetMapping(value = "/getSido")
public ResponseEntity<?> getSido(@RequestParam Map<String, String> map) {
	try {
		List<SidoDto> list = service.listSido();
		if(list != null && !list.isEmpty()) {
			return new ResponseEntity<List<SidoDto>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	} catch (Exception e) {
		return exceptionHandling(e);
	}
}

@GetMapping(value = "/getGugun/{currAreaNum}")
public ResponseEntity<?> getGugun(@PathVariable("currAreaNum") String sidoCode) {
	try {
		List<GugunDto> list = service.listGugun(sidoCode);
		if(list != null && !list.isEmpty()) {
			return new ResponseEntity<List<GugunDto>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	} catch (Exception e) {
		return exceptionHandling(e);
	}
}

@GetMapping(value = "/getTrip/{type}/{currAreaNum}/{currSigunguNum}")
public ResponseEntity<?> getTrip(@PathVariable("type") String type, @PathVariable("currAreaNum") String sidoCode, @PathVariable("currSigunguNum") String gugunCode) {
	try {
		List<AttractionDto> list = service.listAttraction(type, sidoCode, gugunCode); 
		if(list != null && !list.isEmpty()) {
			return new ResponseEntity<List<AttractionDto>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	} catch (Exception e) {
		return exceptionHandling(e);
	}
}

@GetMapping(value = "/getAttraction/{contentId}")
public ResponseEntity<?> getAttraction(@PathVariable("contentId") String contentId) {
	try {
		AttractionDto attractionDto = service.getAttraction(contentId);
		if(attractionDto != null)
			return new ResponseEntity<AttractionDto>(attractionDto, HttpStatus.OK);
		else
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	} catch (Exception e) {
		return exceptionHandling(e);
	}
}



private ResponseEntity<String> exceptionHandling(Exception e) {
	e.printStackTrace();
	return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
}
}