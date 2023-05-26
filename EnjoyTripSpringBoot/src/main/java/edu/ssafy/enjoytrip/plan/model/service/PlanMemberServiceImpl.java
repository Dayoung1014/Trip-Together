package edu.ssafy.enjoytrip.plan.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import edu.ssafy.enjoytrip.plan.model.PlanDto;
import edu.ssafy.enjoytrip.plan.model.PlanMemberDto;
import edu.ssafy.enjoytrip.plan.model.mapper.PlanMemberMapper;

@Service
public class PlanMemberServiceImpl implements PlanMemberService{

	private PlanMemberMapper planMemberMapper;
	
	public PlanMemberServiceImpl(PlanMemberMapper planMemberMapper) {
		super();
		this.planMemberMapper = planMemberMapper;
	}

	@Override
	public void regiMember(PlanMemberDto planMemberDto) throws Exception {
		planMemberMapper.regiMember(planMemberDto);
	}

	@Override
	public void modiMember(PlanMemberDto planMemberDto) throws Exception {
		planMemberMapper.modiMember(planMemberDto);
	}

	@Override
	public void delMember(String planId, String userId) throws Exception {
		Map<String, Object> params = new HashMap<>();
		params.put("planId", planId);
		params.put("userId", userId);
		planMemberMapper.delMember(params);
	}

	@Override
	public List<PlanMemberDto> listPlanMember(String planId) throws Exception { 
		return planMemberMapper.listPlanMember(planId);
	}

	@Override
	public List<PlanDto> listPlanByMember(String userId) throws Exception { 
		return planMemberMapper.listPlanByMember(userId);
	}

}
