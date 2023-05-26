package edu.ssafy.enjoytrip.plan.model.service;

import java.util.List;
import java.util.Map;

import edu.ssafy.enjoytrip.plan.model.PlanDto;
import edu.ssafy.enjoytrip.plan.model.PlanMemberDto;
import edu.ssafy.enjoytrip.util.PageNavigation;

import java.lang.Exception;
import java.sql.SQLException;


public interface PlanMemberService {

	// 플랜에 멤버 추가 (승인 전) 
	void regiMember(PlanMemberDto planMemberDto) throws Exception;
	
	// 플랜에 멤버 권한 수정 (승인 후)
	void modiMember(PlanMemberDto planMemberDto) throws Exception;
	
	// 플랜에서 멤버 삭제 (승인 거절) 
	void delMember(String planId, String userId) throws Exception;
	 
	// 플랜 id로 멤버 불러오기 
	List<PlanMemberDto> listPlanMember(String planId) throws Exception;
	
	// 멤버 id로 플랜 불러오기
	List<PlanDto> listPlanByMember(String userId) throws Exception;
}
