package edu.ssafy.enjoytrip.plan.controller;

import java.io.File;
import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.ssafy.enjoytrip.plan.model.PlanDto;
import edu.ssafy.enjoytrip.plan.model.service.PlanService;
import edu.ssafy.enjoytrip.user.model.UserDto;
import edu.ssafy.enjoytrip.util.PageNavigation;

 

@RestController
@RequestMapping("/plan")
@CrossOrigin("*") // 모든 요청 허용
public class RestPlanController {

	private final Logger logger = LoggerFactory.getLogger(RestPlanController.class);
	//private final String UPLOAD_PATH = "/upload";
	@Value("${file.path}")
	private String uploadPath;

	@Autowired
	private ServletContext servletContext;

	private PlanService service;

	public RestPlanController(PlanService planService) {
		super();
		this.service = planService;
	}
	
	@PostMapping(value = "/write")
	public ResponseEntity<?> planRegister(@RequestBody PlanDto planDto,
			@RequestParam Map<String, String> map) {
		try {
			service.writeArticle(planDto);
			List<PlanDto> list = service.listArticle(map);
			return new ResponseEntity<List<PlanDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	
	@GetMapping(value = "/list")
	public ResponseEntity<?> planList(@RequestParam Map<String, String> map) {
		try {
			List<PlanDto> list = service.listArticle(map);
			System.out.println(list.size());
			if(list != null && !list.isEmpty()) {
				return new ResponseEntity<List<PlanDto>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping(value = "/list/{id}")
	public ResponseEntity<?> planInfo(@PathVariable("id") int id) {
		try {
			PlanDto planDto = service.getArticle(id);
			if(planDto != null)
				return new ResponseEntity<PlanDto>(planDto, HttpStatus.OK);
			else
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@PutMapping(value = "/modify")
	public ResponseEntity<?> planModify(@RequestBody PlanDto planDto,
			@RequestParam Map<String, String> map) {
		try {
			service.modifyArticle(planDto);
			List<PlanDto> list = service.listArticle(map);
			return new ResponseEntity<List<PlanDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<?> planDelete(@PathVariable("id") int id,
			@RequestParam Map<String, String> map) {
		try {
			service.deleteArticle(id, null);
			List<PlanDto> list = service.listArticle(map);
			return new ResponseEntity<List<PlanDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
