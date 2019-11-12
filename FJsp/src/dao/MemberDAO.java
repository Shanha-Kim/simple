package dao;

/**
 * 	이 클래스는 회원 관련 데이터베이스 처리를 위한 클래스이다.
 * 
 * @author 	전은석
 * @since	2019.11.11
 * @version	v.1.0
 * @see		DB.DBCP
 * 
 * 			작업이력
 * 				2019.11.11	-	클래스 제작 	- 담당자 : 전은석
 * 
 */

import DB.*;
import sql.*;
import vo.*;
import java.sql.*;
import java.util.*;

public class MemberDAO {
	/*
	 * 이 클래스는 회원에 관련된 데이터베이스 작업을 전담해서 처리하는 클래스이다.
	 * 따라서 서버가 기동하면서 확보해 놓은 커넥션을 하나 얻어와야 한다.
	 * 그런데 그 커넥션은 DB.DBCP로 관리하기로 약속 했으므로
	 * DBCP 객체를 만들어야겠다.
	 */
	
	DBCP db = null;
	Connection con = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	MemberSQL mSQL;
	
	public MemberDAO() {
		db = new DBCP();
		mSQL = new MemberSQL();
	}
	
	// 로그인 처리 전담 함수
	public int getCnt(String sid, String spw) {
		int cnt = 0 ;
		// 커넥션 얻어오고
		con = db.getCon();
		// 질의명령 가져오고
		String sql = mSQL.getSQL(mSQL.SEL_MEMB_CNT);
		
		// PreparedStatement 가져오고
		pstmt = db.getPSTMT(con, sql);
		
		try{
			// 데이터 채우고 질의명령 완성하고
			pstmt.setString(1, sid);
			pstmt.setString(2, spw);
			
			// 질의명령 보내고 결과받고
			rs = pstmt.executeQuery();
			// 데이터 뽑고
			rs.next();
			cnt = rs.getInt("cnt");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				db.close(rs);
				db.close(pstmt);
				db.close(con);
			} catch(Exception e) {}
		}
		return cnt;
	}
	
	// 아이디체크 전담 처리함수
	public int getIdCnt(String sid) {
		int cnt = 0 ;
		// 커넥션 얻어오고
		con = db.getCon();
		// 질의 명령 가져오고
		String sql = mSQL.getSQL(mSQL.SEL_ID_CK);
		// PreparedStatement 가져오고
		pstmt = db.getPSTMT(con, sql);
		
		try {
			// 질의명령 완성하고
			pstmt.setString(1, sid);
			// 질의명령 보내고 결과 받고
			rs = pstmt.executeQuery();
			// 데이터 꺼내고
			rs.next();
			cnt = rs.getInt("cnt");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		
		return cnt;
	}
	
	// 모든 회원의 회원번호, 이름, 전화번호를 조회 전담 처리함수
	public ArrayList<MemberVO> getMembList(){
		// 반환해줄 변수 만들고
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		// 커넥션얻고
		con = db.getCon();
		
		// 질의명령가져오고
		String sql = mSQL.getSQL(mSQL.SEL_MEMB_ALL);
		
		// Statement 가져오고
		stmt = db.getSTMT(con);
		
		// 데이터베이스에 질의 보내고 응답 받고
		try {
			rs = stmt.executeQuery(sql);
			
			// 데이터 뽑아서 리스트에 담고
			while(rs.next()) {
				// 매번 한사람의 데이터를 담아야 되므로
				// 한번 반복할 때 마다 VO 를 하나씩 만들어 준다.
				MemberVO vo = new MemberVO();
				vo.setMno(rs.getInt("mno"));
				vo.setName(rs.getString("name"));
				vo.setTel(rs.getString("tel"));
				// 이제 VO 는 데이터가 채워졌고
				// 리스트에 담자.
				list.add(vo);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(stmt);
			db.close(con);
		}
		return list;
	}
	
	// 회원 정보 조회 전담 처리 함수
	public MemberVO getDetail(int mno) {
		MemberVO vo = new MemberVO();
		// 할일
		// 1. 커넥션 얻어오고
		con = db.getCon();
		// 2. 질의명령 가져오고
		String sql = mSQL.getSQL(mSQL.SEL_MEMB_DETAIL);
		// 3. PreparedStatement 얻어오고
		pstmt = db.getPSTMT(con, sql);
		try {
			// 4. 질의명령 완성하고
			pstmt.setInt(1, mno);
			// 5. 질의명령 보내고 결과 받고
			rs = pstmt.executeQuery();
			// 데이터 뽑고
			rs.next();
			vo.setMno(rs.getInt("mno"));
			vo.setId(rs.getString("id"));
			vo.setName(rs.getString("name"));
			vo.setMail(rs.getString("mail"));
			vo.setTel(rs.getString("tel"));
			vo.setJoinDate(rs.getDate("mdate"));
			vo.setJoinTime(rs.getTime("mdate"));
			vo.setsDate();
			vo.setsTime();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		
		// 6. 데이터 반환하고
		return vo;
	}
	
	// 회원가입처리 전담함수
	public int addMemb(MemberVO vo) {
		int cnt = 0;
		// 할일
		// 커넥션 얻어오고
		con = db.getCon();
		// 질의명령 얻어오고
		String sql = mSQL.getSQL(mSQL.ADD_MEMB);
		// PreparedStatement 가져오고
		pstmt = db.getPSTMT(con, sql);
		try {
			// 질의명령 완성하고
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getMail());
			pstmt.setString(5, vo.getTel());
			
			// 질의명령 보내고 함수의 반환값 받고
			// executeUpdate() 는 실행되면 변경된 데이터의 수를 반환을 해준다.
			// Insert 명령에서는 데이터가 추가가되면 1을
			// 추가에 실패를 하면 0을 반환해준다.
			cnt = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(pstmt);
			db.close(con);
		}
		// 질의명령 보내고 반환값 받고
		
		// 반환값 내보내고...
		return cnt;
	}
}
