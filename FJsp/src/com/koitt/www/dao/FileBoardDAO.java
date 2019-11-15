package com.koitt.www.dao;

import java.util.*;
import java.sql.*;

import DB.*;
import com.koitt.www.sql.*;
import com.koitt.www.vo.*;

/**
 * 		이 클래스는 파일업로드게시판에 관련된 
 * 		데이터베이스 작업을 전담할 클래스
 *  
 * @author	전은석
 * @since	2019.11.15
 * @version	v.1.0
 * @see		DB.DBCP, com.koitt.www.sql.FileBoardSQL
 * 
 * 			변경이력
 * 				2019.11.15	-	FileBoardDAO 클래스 작성	-	담당자 : 전은석
 * 				2019.11.15	-	getFBList() 작성			-	담당자 : 전은석
 *
 */
public class FileBoardDAO {
	// 데이터베이스 사용준비
	DBCP db = null;
	Connection con = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	FileBoardSQL fbSQL;
	FileBoardVO fVO;
	
	public FileBoardDAO() {
		db = new DBCP();
		fbSQL = new FileBoardSQL();
	}
	
	// 파일 업로드게시판 리스트 가져오기 전담 처리 함수
	public ArrayList<FileBoardVO> getFBList() {
		ArrayList<FileBoardVO> list = new ArrayList<FileBoardVO>();
		// 1. 커넥션 얻어오고
		con = db.getCon();
		// 2. 질의명령 가져오고
		String sql = fbSQL.getSql(fbSQL.SEL_ALL);
		// 3. Statement 꺼내오고
		stmt = db.getSTMT(con);
		try{
			// 4. 질의명령 보내고 결과 받고
			rs = stmt.executeQuery(sql);
			// 5. 데이터 꺼내서 vo에 담아서 리스트에 담고
			while(rs.next()) {
				fVO = new FileBoardVO();
				fVO.setBno(rs.getInt("bno"));
				fVO.setId(rs.getString("writer"));
				fVO.setWdate(rs.getDate("wdate"));
				fVO.setWtime(rs.getTime("wdate"));
				fVO.setTitle(rs.getString("title"));
				// 게시글 하나의 데이터는 준비가 됬고
				// 게시글 하나를 리스트에 하나씩 추가하자.
				list.add(fVO);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(stmt);
			db.close(con);
		}
		
		// 6. 데이터 내보내고
		return list;
	}
	
	// 파일 정보 입력 전단 처리 함수
	public int addFileInfo(FileInfoVO fvo) {
		int cnt = 0;
		con = db.getCon();
		String sql = fbSQL.getSql(fbSQL.ADD_FILEINFO);
		String sql2 = fbSQL.getSql(fbSQL.SEL_FNO);
		pstmt = db.getPSTMT(con, sql);
		try {
			pstmt.setString(1, fvo.getOriname());
			pstmt.setString(2, fvo.getSavename());
			pstmt.setString(3, fvo.getDir());
			pstmt.setLong(4, fvo.getLen());
			
			cnt = pstmt.executeUpdate();
			
			if(cnt == 1) {
				db.close(pstmt);
				pstmt = db.getPSTMT(con, sql2);
				pstmt.setString(1, fvo.getSavename());
				rs = pstmt.executeQuery();
				rs.next();
				fvo.setFno(rs.getInt("fno"));
				db.close(rs);
			}
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(pstmt);
			db.close(con);
		}
		
		return cnt;
	}
}
