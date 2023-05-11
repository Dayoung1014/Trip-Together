package edu.ssafy.enjoytrip.plan.model.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.ssafy.enjoytrip.plan.model.PlanDto;
import edu.ssafy.enjoytrip.plan.model.mapper.PlanMapper;
import edu.ssafy.enjoytrip.util.PageNavigation;
import edu.ssafy.enjoytrip.util.SizeConstant;


@Service
public class PlanServiceImpl implements PlanService {
	
	private PlanMapper planMapper;

	public PlanServiceImpl(PlanMapper planMapper) {
		super();
		this.planMapper = planMapper;
	}

	@Override
	@Transactional
	public void writeArticle(PlanDto planDto) throws Exception {
		System.out.println("글입력 전 dto : " + planDto);
		planMapper.writeArticle(planDto);
		System.out.println("글입력 후 dto : " + planDto);
	}

	@Override
	public List<PlanDto> listArticle(Map<String, String> map) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		String key = map.get("key");
		if("userid".equals(key))
			key = "b.user_id";
		param.put("key", key == null ? "" : key);
		param.put("word", map.get("word") == null ? "" : map.get("word"));
		int pgNo = Integer.parseInt(map.get("pgno") == null ? "1" : map.get("pgno"));
		int start = pgNo * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
		param.put("start", start);
		param.put("listsize", SizeConstant.LIST_SIZE);
		
		return planMapper.listArticle(param);
	}
	
	@Override
	public PageNavigation makePageNavigation(Map<String, String> map) throws Exception {
		PageNavigation pageNavigation = new PageNavigation();

		int naviSize = SizeConstant.NAVIGATION_SIZE;
		int sizePerPage = SizeConstant.LIST_SIZE;
		int currentPage = Integer.parseInt(map.get("pgno"));

		pageNavigation.setCurrentPage(currentPage);
		pageNavigation.setNaviSize(naviSize);
		Map<String, Object> param = new HashMap<String, Object>();
		String key = map.get("key");
		if ("userid".equals(key))
			key = "user_id";
		param.put("key", key == null ? "" : key);
		param.put("word", map.get("word") == null ? "" : map.get("word"));
		int totalCount = planMapper.getTotalArticleCount(param);
		pageNavigation.setTotalCount(totalCount);
		int totalPageCount = (totalCount - 1) / sizePerPage + 1;
		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = currentPage <= naviSize;
		pageNavigation.setStartRange(startRange);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();

		return pageNavigation;
	}

	@Override
	public PlanDto getArticle(int articleNo) throws Exception {
		return planMapper.getArticle(articleNo);
	}

	@Override
	public void updateHit(int articleNo) throws Exception {
		planMapper.updateHit(articleNo);
	}

	@Override
	public void modifyArticle(PlanDto planDto) throws Exception {
		// TODO : PlanDaoImpl의 modifyArticle 호출
		planMapper.modifyArticle(planDto);
	}

	@Override
	@Transactional
	public void deleteArticle(int articleNo, String path) throws Exception {
		// TODO : PlanDaoImpl의 deleteArticle 호출
		planMapper.deleteArticle(articleNo);
	}

}
