import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface LikestampDao {
		// 등록 
		void writeLikestamp(LikestampDto likestampDto) throws SQLException;
		
		// 조회 
		List<LikestampDto> listLikestamp(Map<String, Object> param) throws SQLException;
		
		// 삭제 
		void deleteLikestamp(int userId, int hotplaceId) throws SQLException;
		
	
}
