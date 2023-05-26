package edu.ssafy.enjoytrip.plan.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import edu.ssafy.enjoytrip.plan.model.PlanDto;
import edu.ssafy.enjoytrip.plan.model.PlanMemberDto;
import edu.ssafy.enjoytrip.plan.model.service.PlanMemberService;

@RestController
@RequestMapping("/planuser")
@CrossOrigin("*") // 모든 요청 허용
public class RestPlanMemberController {
	private PlanMemberService planMemberService;

	public RestPlanMemberController(PlanMemberService planMemberService) {
		super();
		this.planMemberService = planMemberService;
	}
	
	@PostMapping("/") 
	public ResponseEntity<Map<String, Object>> regiMember(@RequestBody PlanMemberDto planMemberDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		try {
			planMemberService.regiMember(planMemberDto); 
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@PutMapping("/")
	public ResponseEntity<?> modiMember(@RequestBody PlanMemberDto planMemberDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		try {
			planMemberService.modiMember(planMemberDto); 
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@DeleteMapping("/{plan_id}/{user_id}")
	public ResponseEntity<?> delMember(@PathVariable("plan_id") String planId, @PathVariable("user_id") String user_id) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		try {
			planMemberService.delMember(planId, user_id); 
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@GetMapping("/plan/{plan_id}")
	public ResponseEntity<?> listPlanMember(@PathVariable("plan_id") String planId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		try {
			List<PlanMemberDto> list = planMemberService.listPlanMember(planId); 
			if(list != null && !list.isEmpty()) {
				status = HttpStatus.ACCEPTED;
				resultMap.put("member", list); 
			}  
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@GetMapping("/user/{user_id}")
	public ResponseEntity<?> listPlanByMember(@PathVariable("user_id") String userId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		try {
			List<PlanDto> list = planMemberService.listPlanByMember(userId); 
			if(list != null && !list.isEmpty()) {
				return new ResponseEntity<List<PlanDto>>(list, HttpStatus.OK);
			}  
			else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);  
		} 
	}
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
