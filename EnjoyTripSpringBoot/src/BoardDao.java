import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface BoardDao {
	// 등록 
	void writeArticle(BoardDto boardDto) throws SQLException;
	
	// 조회 
	List<BoardDto> listArticle(Map<String, Object> param) throws SQLException;
	
	// 수정 
	void modifyArticle(BoardDto boardDto) throws SQLException;
	
	// 삭제 
	void deleteArticle(int id) throws SQLException;
	
	// 글 번호로 글 가져오기 
	BoardDto getArticle(int id) throws SQLException;
	
	// 조회수 증가 
	void updateHit(int id) throws SQLException;
	
}

 
 