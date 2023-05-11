package edu.ssafy.enjoytrip.plan.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import edu.ssafy.enjoytrip.plan.model.PlanDto;


@Mapper
public interface PlanMapper {

	void writeArticle(PlanDto planDto) throws SQLException;

	void registerFile(PlanDto planDto) throws Exception;

	List<PlanDto> listArticle(Map<String, Object> param) throws SQLException;

	int getTotalArticleCount(Map<String, Object> param) throws SQLException;

	PlanDto getArticle(int articleNo) throws SQLException;

	void updateHit(int articleNo) throws SQLException;

	void modifyArticle(PlanDto planDto) throws SQLException;

	void deleteFile(int articleNo) throws Exception;

	void deleteArticle(int articleNo) throws SQLException;


}