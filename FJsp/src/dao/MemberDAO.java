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
	
	public MemberDAO() {
		db = new DBCP();
	}
	
	// 로그인 처리 전담 함수
	public int getCnt(String sid, String spw) {
		int cnt = 0 ;
		
		return cnt;
	}

}
