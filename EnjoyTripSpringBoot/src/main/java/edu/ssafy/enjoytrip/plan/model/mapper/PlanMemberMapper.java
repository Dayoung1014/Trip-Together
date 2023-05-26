package edu.ssafy.enjoytrip.plan.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import edu.ssafy.enjoytrip.plan.model.PlanDto;
import edu.ssafy.enjoytrip.plan.model.PlanMemberDto;


@Mapper
public interface PlanMemberMapper {

	// 플랜에 멤버 추가 (승인 전) 
	void regiMember(PlanMemberDto planMemberDto) throws SQLException;
	
	// 플랜에 멤버 권한 수정 (승인 후)
	void modiMember(PlanMemberDto planMemberDto) throws SQLException;
	
	// 플랜에서 멤버 삭제 (승인 거절) 
	void delMember(Map<String, Object> params) throws SQLException;
	 
	// 플랜 id로 멤버 불러오기 
	List<PlanMemberDto> listPlanMember(String planId) throws SQLException;
	
	// 멤버 id로 플랜 불러오기
	List<PlanDto> listPlanByMember(String userId) throws SQLException;
	 


}