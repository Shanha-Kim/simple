package com.koitt.www.dao;

/**
 * 		이 클래스는 회원에 관련된 데이터베이스 작업을 처리할 클래스
 * 
 * @author	전은석
 * @since	2019.11.13
 * @version	v.1.0
 * @see		
 * 			변경이력
 * 				2019.11.13	- MemberDAO 클래스 제작		- 담당자 : 전은석
 *
 */

import java.sql.*;
import java.util.*;

import DB.*;
import com.koitt.www.sql.*;
import com.koitt.www.vo.*;

public class MemberDAO {
	// DataSource 를 관리할 변수 선언
	DBCP db = null;
	Connection con = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	MemberSQL mSQL = null;
	MemberVO mVO = null;
	
	public MemberDAO() {
		// DBCP 초기화
		db = new DBCP();
		// MemberSQL 초기화
		mSQL = new MemberSQL();
	}
	
	// 로그인 데이터베이스 전담 처리 함수
	public int getLogin(String sid, String spw) {
		int cnt = 0;
		// 1. 커넥션 얻어오고
		con = db.getCon();
		// 2. sql 가져오고
		String sql = mSQL.getSQL(mSQL.GET_LOGIN);
		// 3. PreparedStatement 가져오고
		pstmt = db.getPSTMT(con, sql);
		
		try {
			// 4. 질의명령 완성하고
			pstmt.setString(1, sid);
			pstmt.setString(2, spw);
			// 5. 질의명령 보내고 결과받고
			rs = pstmt.executeQuery();
			// 6.0 한줄내리고
			rs.next();
			// 6. 데이터 꺼내고
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
}








