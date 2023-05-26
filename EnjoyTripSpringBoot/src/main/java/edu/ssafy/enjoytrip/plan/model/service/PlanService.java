package edu.ssafy.enjoytrip.plan.model.service;

import java.util.List;
import java.util.Map;

import edu.ssafy.enjoytrip.plan.model.PlanDto;
import edu.ssafy.enjoytrip.util.PageNavigation;

import java.lang.Exception;
import java.sql.SQLException;


public interface PlanService {

	void writeArticle(PlanDto planDto) throws Exception;
	List<PlanDto> listArticleMy(String userId) throws Exception; 
	PlanDto getArticle(int articleNo) throws Exception; 
	List<PlanDto> listArticleAll() throws Exception; //공개인 것들만 다 가져오기 
	void modifyArticle(PlanDto planDto) throws Exception;
	void deleteArticle(int articleNo, String path) throws Exception;
}
