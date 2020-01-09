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
	
	// 회원 상세 정보 전담 처리 함수
	public MemberVO getMemberInfo(String sid) {
		MemberVO vo = new MemberVO();
		// 할일
		// 1. 커넥션 가져오고
		con = db.getCon();
		// 2. 질의명령 가져오고
		String sql = mSQL.getSQL(mSQL.SEL_MEMB_INFO);
		// 3. PreparedStatement 가져오고
		pstmt = db.getPSTMT(con, sql);
		try{
			// 4. 질의명령 완성하고
			pstmt.setString(1, sid);
			// 5. 질의명령 보내고 결과 받고
			rs = pstmt.executeQuery();
			// 6. 데이터 꺼내고 VO에 담고
			// 	한줄내리고
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
		
		// 7. 데이터 내보내고
		return vo;
	}
	
	// 회원정보 수정 전담 처리 함수
	public int memberEdit(MemberVO vo, String code) {
		int cnt = 0;
		// 할일
		// 1. 커넥션 얻어오고
		con = db.getCon();
		// 2. 질의명령 얻어오고
		String sql = mSQL.getSQL(mSQL.EDIT_MEMB_INFO);
		// 2-1. 질의명령 수정하고 
		// <-- 우리는 질의 명령중 변경되는 부분을 ### 으로 처리를 해놓았으므로... 
		String tmp = "";
		switch(code) {
		case "1":
			tmp = "m_mail = ?, m_tel = ?";
			break;
		case "2":
			tmp = "m_mail = ?";
			break;
		case "3":
			tmp = "m_tel = ?";
			break;
		}
		sql = sql.replace("###", tmp);
		
		// 3. PreparedStatement 가져오고
		pstmt = db.getPSTMT(con, sql);
		
		try {
			// 4. 질의 명령 완성하고
			if(code.equals("1")) {
				pstmt.setString(1, vo.getMail());
				pstmt.setString(2, vo.getTel());
				pstmt.setInt(3, vo.getMno());
			} else {
				String val = "";
				if(code.equals("2")) {
					val = vo.getMail();
				} else {
					val = vo.getTel();
				}
				
				pstmt.setString(1, val);
				pstmt.setInt(2, vo.getMno());
			}
			
			// 5. 질의명령 보내고 결과 받고
			cnt = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(pstmt);
			db.close(con);
		}
		
		// 6. 결과 내보내고
		return cnt;
	}
	
	// 회원가입 처리 전담 함수
	public int addMember(MemberVO vo) {
		int cnt = 0 ;
		// 할일
		// 1. 커넥션 얻어오고
		con = db.getCon();
		// 2. 질의 명령 가져오고
		String sql = mSQL.getSQL(mSQL.ADD_MEMB);
		// 3. PreparedStatement 가져오고
		pstmt = db.getPSTMT(con, sql);
		try {
			// 4. 질의명령 완성하고
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getMail());
			pstmt.setString(5, vo.getTel());
			pstmt.setInt(6, vo.getAvt());
			pstmt.setString(7, vo.getGen());
			
			// 5. 질의명령 보내고 결과받고
			cnt = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(pstmt);
			db.close(con);
		}
		
		// 6. 결과내보내고
		return cnt;
	}
	
	// 프로필사진정보 입력 전담 처리함수
	public int addPic(PhotoVO vo) {
		int cnt = 0;
		// 1. 커넥션 얻어오고
		con = db.getCon();
		// 2. 질의명령 가져오고
		String sql = mSQL.getSQL(mSQL.ADD_PIC);
		// 3. pstmt 얻어오고
		pstmt = db.getPSTMT(con, sql);
		try {
			// 4. 질의명령 완성하고
			pstmt.setInt(1, vo.getMno());
			pstmt.setString(2, vo.getOriname());
			pstmt.setString(3, vo.getSavename());
			pstmt.setLong(4, vo.getLen());
			pstmt.setString(5, vo.getDir());
			// 5. 질의명령 보내고 결과받고
			cnt = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(pstmt);
			db.close(con);
		}
		
		// 6. 결과 내보내고
		return cnt;
	}
	
	// 아이디를 입력하면 회원 번호를 가져오는 함수
	public int getMno(String id) {
		int mno = 0;
		con = db.getCon();
		String sql = mSQL.getSQL(mSQL.SEL_ID_TO_MNO);
		pstmt = db.getPSTMT(con, sql);
		
		try {
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			rs.next();
			mno = rs.getInt("mno");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		
		return mno;
	}
}








