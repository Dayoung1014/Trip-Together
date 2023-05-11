import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface HotplaceDao {
	// 등록 
	void writeHotplac(HotplaceDto hotplaceDto) throws SQLException;
	
	// 조회 
	List<HotplaceDto> listHotplace(Map<String, Object> param) throws SQLException;
	
	// 수정 
	void modifyHotplace(HotplaceDto hotplaceDto) throws SQLException;
	
	// 삭제 
	void deleteHotplace(int id) throws SQLException;
	
	// 글 번호로 글 가져오기 
	BoardDto getHotplace(int id) throws SQLException;
	
	// 좋아요 순 정렬 
	void orderByLike(int id) throws SQLException;
	
}
