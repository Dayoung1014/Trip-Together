package edu.ssafy.enjoytrip.plan.model.service;

import java.util.List;
import java.util.Map;

import edu.ssafy.enjoytrip.plan.model.PlanDto;
import edu.ssafy.enjoytrip.util.PageNavigation;

import java.lang.Exception;


public interface PlanService {

	void writeArticle(PlanDto planDto) throws Exception;
	List<PlanDto> listArticle(Map<String, String> map) throws Exception;
	PageNavigation makePageNavigation(Map<String, String> map) throws Exception;
	PlanDto getArticle(int articleNo) throws Exception;
	void updateHit(int articleNo) throws Exception;
	
	void modifyArticle(PlanDto planDto) throws Exception;
	void deleteArticle(int articleNo, String path) throws Exception;
}
