package edu.ssafy.enjoytrip.board.controller;

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

import edu.ssafy.enjoytrip.board.model.BoardDto;
import edu.ssafy.enjoytrip.board.model.FileInfoDto;
import edu.ssafy.enjoytrip.board.model.service.BoardService;
import edu.ssafy.enjoytrip.user.model.UserDto;
import edu.ssafy.enjoytrip.util.PageNavigation;





@RestController
@RequestMapping("/board")
@CrossOrigin("*") // 모든 요청 허용
public class RestBoardController {

	private final Logger logger = LoggerFactory.getLogger(RestBoardController.class);
	//private final String UPLOAD_PATH = "/upload";
	@Value("${file.path}")
	private String uploadPath;

	@Autowired
	private ServletContext servletContext;

	private BoardService service;

	public RestBoardController(BoardService boardService) {
		super();
		this.service = boardService;
	}
	
	
	// http://localhost:8080/board
	@PostMapping  
	public ResponseEntity<?> boardRegister(@RequestBody BoardDto boardDto,
			@RequestParam Map<String, String> map) {
		try {
			service.writeArticle(boardDto);
			System.out.println(boardDto);
			List<BoardDto> list = service.listArticle(map);
			return new ResponseEntity<List<BoardDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	
	// http://localhost:8080/board
	@GetMapping  
	public ResponseEntity<?> boardList(@RequestParam Map<String, String> map) {
		try {
			List<BoardDto> list = service.listArticle(map);
			System.out.println(list.size());
			if(list != null && !list.isEmpty()) {
				return new ResponseEntity<List<BoardDto>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	// http://localhost:8080/board/아이디
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> boardInfo(@PathVariable("id") int id) {
		try {
			BoardDto boardDto = service.getArticle(id);
			if(boardDto != null)
				return new ResponseEntity<BoardDto>(boardDto, HttpStatus.OK);
			else
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	//http://localhost:8080/board
	@PutMapping 
	public ResponseEntity<?> boardModify(@RequestBody BoardDto boardDto,
			@RequestParam Map<String, String> map) {
		try {
			service.modifyArticle(boardDto);
			List<BoardDto> list = service.listArticle(map);
			return new ResponseEntity<List<BoardDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	// http://localhost:8080/board/아이디
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> boardDelete(@PathVariable("id") int id,
			@RequestParam Map<String, String> map) {
		try {
			service.deleteArticle(id, null);
			List<BoardDto> list = service.listArticle(map);
			return new ResponseEntity<List<BoardDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
