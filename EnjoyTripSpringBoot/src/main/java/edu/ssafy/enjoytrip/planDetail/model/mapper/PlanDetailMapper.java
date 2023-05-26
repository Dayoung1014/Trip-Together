package edu.ssafy.enjoytrip.planDetail.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import edu.ssafy.enjoytrip.planDetail.model.PlanDetailDto;

@Mapper
public interface PlanDetailMapper { 
		// 여행지 등록 
		void writePlanDetail(PlanDetailDto planDetailDto) throws SQLException;
		
		// 플랜 번호로 경로 마지막 여행지 찾기 (추가 위해)
		PlanDetailDto getRouteList(String planId) throws SQLException;
		  
		// 등록된 특정 여행지 가져오기 (id없이) 
		PlanDetailDto getPlanDetail(String planId, String dataNum, String route) throws SQLException;
		
		// 등록된 특정 여행지 가져오기 (id로) 
		PlanDetailDto getPlanDetailById(String detailId) throws SQLException;
		
		// 플랜 번호로 여행지 조회  
		List<PlanDetailDto> listPlanDetail(String planId) throws SQLException;
			 
			
		// 여행지 수정 
		void modifyPlanDetail(PlanDetailDto planDetailDto) throws SQLException;
			 
			
		// 여행지 삭제  
		void deletePlanDetail(String detailId) throws SQLException; 

}
