import java.sql.SQLException;
import java.util.List;
import java.util.Map; 

public interface PlanDao {
	
		// 플랜 등록 
		void writePlan(PlanDto planDto) throws SQLException;

		// 여행지 등록 
		void writePlanDetai(PlanDetailDto planDetailDto) throws SQLException;
		
		// 전체 플랜 조회 
		List<PlanDto> listPlan(Map<String, Object> param) throws SQLException;
		
		// 해당 플랜 내 여행지 조회  
		List<PlanDetailDto> listPlanDetail(Map<String, Object> param) throws SQLException;
		
		// 플랜 수정 
		void modifyPlan(PlanDto planDto) throws SQLException;
		
		// 여행지 수정 
		void modifyPlanDetail(PlanDetailDto planDetailDto) throws SQLException;
		
		// 플랜 삭제 
		void deletePlan(int id) throws SQLException;
		
		// 여행지 삭제  
		void deletePlanDetail(int detailId) throws SQLException;
 
		// 플랜 번호로 가져오기 
		PlanDto getPlan(int id) throws SQLException;
		 
}
