package com.koitt.www.dao;


/**
 * 	이 클래스는 설문조사 관련 데이터베이스 작업 전담 처리 클래스
 * 
 * @author	전은석
 * @since	2019.12.05
 * @version	v.1.0
 * @see		DB.DBCP, com.koitt.www.survey.Survey
 * 
 * 			변경이력
 * 				2019.12.05		- 	클래스 제작		- 담당자 : 전은석
 *
 */

import java.util.*;
import java.sql.*;

import DB.*;
import com.koitt.www.sql.*;
import com.koitt.www.vo.*;

public class SurveyDAO {
	DBCP db = null;
	Connection con = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	SurveySQL sSQL;
	
	public SurveyDAO() {
		db = new DBCP();
		sSQL = new SurveySQL();
	}
	
	// 현재 진행중인 설문 데이터 조회 전담 처리함수
	public ArrayList<SurveyVO> getCurList(String id){
		ArrayList<SurveyVO> list = new ArrayList<SurveyVO>();
		// 1. 커넥션 얻어오고
		con = db.getCon();
		// 2. 질의명령 가져오고
		String sql = sSQL.getSQL(sSQL.SEL_SRV_CUR);
		// 3. PreparedStatement 얻어오고
		pstmt = db.getPSTMT(con, sql);
		try {
			// 4. 질의명령 완성하고 
			pstmt.setString(1, id);
			// 5. 질의명령 보내고 결과받고
			rs = pstmt.executeQuery();
			// 6. 데이터 뽑아서 vo만드록 vo에 담고
			while(rs.next()) {
				SurveyVO vo = new SurveyVO();
				vo.setSno(rs.getInt("sno"));
				vo.setTitle(rs.getString("title"));
				vo.setStartDate(rs.getDate("startDate"));
				vo.setEndDate(rs.getDate("endDate"));
				vo.setCheck(rs.getString("count"));
				vo.setId(id);
				// 7. vo list에 담고
				list.add(vo);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		
		// 8. 리스트 내보내고
		return list;
	}
}
