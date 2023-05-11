package edu.ssafy.enjoytrip.planDetail.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ssafy.enjoytrip.hotplace.model.HotplaceDto;
import edu.ssafy.enjoytrip.planDetail.model.PlanDetailDto;
import edu.ssafy.enjoytrip.planDetail.model.service.PlanDetailService;

@RestController
@RequestMapping(value = "/planDetail")
@CrossOrigin("*") // 모든 요청 허용
public class PlanDetailController {
	@Autowired
	private PlanDetailService planDetailService;
	
	//plan에서 여행지 조회하기
	// (get) http://localhost:8080/planDetail/1
	@GetMapping("/{planId}") 
	public Map<String, Object> listPlanDetail(@PathVariable("planId") String planId){
		Map<String, Object> map = new HashMap(); 
		List<PlanDetailDto> list = null;
		try {
			list = planDetailService.listPlanDetail(planId);
			map.put("msg", "리스트 조회 성공");
			map.put("data", list);
		}
		catch (Exception e) {  
			e.printStackTrace(); 
			map.put("resmsg", "리스트 조회 실패");
		}
		return map;
	}
	
	//plan에 여행지 추가하기 
	// route 가 null 인것 있는 경우 (null인 여행지에 현재 아이디 넣고, 현재는 Null로)
	// 첫 추가인 경우 null로 넣기 
	// (post) http://localhost:8080/planDetail
	/*
	 * {
	            "planId": 1, 
	            "dataNum": 125266
	}
	 * */
	@PostMapping(headers = "content-type=application/json")  
	public Map<String, String> writePlanDetail (@RequestBody PlanDetailDto planDetailDto)  {
		Map<String, String> map = new HashMap();
		try {
			PlanDetailDto lastDto = planDetailService.getRouteList(planDetailDto.getPlanId());
			if(lastDto == null) {
				// 해당 plan의 첫 여행지인 경우 
				planDetailDto.setRoute(null);
				planDetailService.writePlanDetai(planDetailDto);
			}
			else {
				// 해당 plan의 여행지들이 존재하는 경우 
				
				// 마지막 경로로 추가 
				planDetailDto.setRoute(null);
				planDetailService.writePlanDetai(planDetailDto);
				System.out.println(planDetailDto);
				
				// 추가한 dto 가져오기 
				PlanDetailDto writeDto = planDetailService.getPlanDetail(planDetailDto.getPlanId(), planDetailDto.getDataNum(), planDetailDto.getRoute());
				System.out.println(writeDto);
				// 원래 마지막 경로였던 곳 route에 추가한 여행지의 id 넣어주기 
				lastDto.setRoute(writeDto.getDetailId());
				planDetailService.modifyPlanDetail(lastDto);
			} 
			map.put("msg", "입력성공");
		} catch (Exception e) { 
			e.printStackTrace();
			map.put("msg", "입력실패");
		}
		return map;
	}

	

	
	//여행지 route 수정하기 
	//(post) http://localhost:8080/수정할 여행지의 원래앞자리id/수정할 여행지의 옮긴후앞자리 id
	@PostMapping(value="/{originPrevId}/{insertPrevId}", headers = "content-type=application/json")  
	public Map<String, String> modifyPlanDetail (@PathVariable("originPrevId") String originPrevId,@PathVariable("insertPrevId") String insertPrevId, @RequestBody PlanDetailDto planDetailDto)  {
		Map<String, String> map = new HashMap();
		try {
			// originPrevId route = planDetailDto route
			PlanDetailDto originPrevDto =  planDetailService.getPlanDetailById(originPrevId);
			originPrevDto.setRoute(planDetailDto.getRoute());
			planDetailService.modifyPlanDetail(originPrevDto);
			
			// planDetailDto route = insertPrevId route
			PlanDetailDto insertPrevDto =  planDetailService.getPlanDetailById(insertPrevId);
			planDetailDto.setRoute(insertPrevDto.getRoute());
			planDetailService.modifyPlanDetail(planDetailDto);
			
			// insertPrevId route = planDetailDto의 detailId
			insertPrevDto.setRoute(planDetailDto.getDetailId());
			planDetailService.modifyPlanDetail(insertPrevDto);
			map.put("msg", "입력성공");
		} catch (Exception e) { 
			e.printStackTrace();
			map.put("msg", "입력실패");
		}
		return map;
	}
	
	
	
	//여행지 삭제하기 
	// (delete) http://localhost:8080/planDetail/4
	@DeleteMapping("/{detailId}") 
	public Map<String, String> delete(@PathVariable("detailId") String detailId) {
		Map<String, String> map = new HashMap();
		try {
			planDetailService.deletePlanDetail(detailId);
			map.put("resmsg", "삭제성공");
		} catch (Exception e) { 
			e.printStackTrace();
			map.put("resmsg", "삭제실패");
		}
		return map;
	} 

}
