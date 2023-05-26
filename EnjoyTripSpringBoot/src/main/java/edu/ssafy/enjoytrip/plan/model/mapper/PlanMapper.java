package edu.ssafy.enjoytrip.plan.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import edu.ssafy.enjoytrip.plan.model.PlanDto;


@Mapper
public interface PlanMapper {

	void writeArticle(PlanDto planDto) throws SQLException;
 

	List<PlanDto> listArticleMy(String userId) throws SQLException;

	List<PlanDto> listArticleAll() throws SQLException; //공개인 것들만 다 가져오기 
	 

	PlanDto getArticle(int articleNo) throws SQLException;
 
	void modifyArticle(PlanDto planDto) throws SQLException;
 

	void deleteArticle(int articleNo) throws SQLException;


}