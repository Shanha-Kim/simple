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
	
	// 설문조사 상세내용 가져오기 전담 처리함수
	public ArrayList<SurveyVO> getSDetailList(int sno){
		ArrayList<SurveyVO> list = new ArrayList<SurveyVO>();
		// 1. 커넥션 얻어오고
		con = db.getCon();
		// 2. 질의명령 가져오고
		String sql = sSQL.getSQL(sSQL.SEL_QNO_LIST);
		// 3. pstmt 얻어오고
		pstmt = db.getPSTMT(con, sql);
		ArrayList<Integer> qnoList = new ArrayList<Integer>();
		try{
			// 4. 질의명령완성하고
			pstmt.setInt(1, sno);
			// 5. 질의명령 보내고 결과받고 리스트에 담고
			rs = pstmt.executeQuery();
			while(rs.next()) {
				qnoList.add(rs.getInt("qno"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		
		con = db.getCon();
		// 6. 질의명령 또 가져오고
		sql = sSQL.getSQL(sSQL.SEL_QUEST_LIST);
		pstmt = db.getPSTMT(con, sql);
		try{
			// 7. 질의명령 완성
			for(int i = 0 ; i < qnoList.size() ; i++ ) {
				pstmt.setInt(1, sno);
				pstmt.setInt(2, qnoList.get(i));
				
				// 8. 보내고 결과받고 
				rs = pstmt.executeQuery();
				
				// vo에 담고
				SurveyVO vo = new SurveyVO(); // 문항데이터 저장할 vo
				ArrayList<SurveyVO> sList = new ArrayList<SurveyVO>();
				int j = 0;
				while(rs.next()) {
					if(j++ == 0) {
						vo.setSno(rs.getInt("sno"));
						vo.setQno(rs.getInt("qno"));
						vo.setBody(rs.getString("body"));
					}
					SurveyVO svo = new SurveyVO(); // 보기데이터 저장할 vo
					svo.setSeno(rs.getInt("seno"));
					svo.setEbody(rs.getString("ebody"));
					// list 에 담고
					sList.add(svo);
				}
				vo.setList(sList);
				
				// list에 또 담고
				list.add(vo);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		
		return list;
	}
	
	// 설문보기 카운트 업데이트 전담 처리 함수
	public int updateCount(ArrayList<Integer> no, String str, int sno, String sid) {
		int cnt = 0;
		// 1. 커넥션 얻어오고
		con = db.getCon();
		// 2. 질의명령 얻어오고
		String sql = sSQL.getSQL(sSQL.UPDATE_CNT);
		// 3. 질의명령 수정하고
		sql = sql.replaceAll("###", str);
		// 4. PreparedStatement 가져오고
		pstmt = db.getPSTMT(con, sql);
		try{
			// 5. 질의명령 완성하고
			for(int i = 0 ; i < no.size(); i++ ) {
				pstmt.setInt(i + 1, no.get(i));
			}
			// 6. 질의명령 보내고
			cnt = pstmt.executeUpdate();
			if(cnt == no.size()) {
				cnt = addSrvMemb(sno, sid);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(pstmt);
			db.close(con);
		}
		
		return cnt;
	}
	
	// 설문 참여 데이터 저장 전담 처리함수
	public int addSrvMemb(int sno, String sid) {
		int cnt = 0;
		// 1. 커넥션 얻고
		con = db.getCon();
		// 2. 질의명령 가져오고
		String sql = sSQL.getSQL(sSQL.ADD_CHECK);
		// 3. PreparedStatement 얻어오고
		pstmt = db.getPSTMT(con, sql);
		try {
			// 4. 질의명령 완성하고
			pstmt.setInt(1, sno);
			pstmt.setString(2, sid);
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
	
	// 설문 데이터 가져오기 전담 처리함수
	public SurveyVO getSrvSno(int sno) {
		SurveyVO vo = new SurveyVO();
		// 1. 커넥션 얻어오고
		con = db.getCon();
		// 2. 질의명령 가져오고
		String sql = sSQL.getSQL(sSQL.SEL_SRV_SNO);
		// 3. PreparedStatement 얻어오고
		pstmt = db.getPSTMT(con, sql);
		try {
			// 4. 질의명령 완성하고
			pstmt.setInt(1, sno);
			// 5. 질의명령 보내고 결과 받고
			rs = pstmt.executeQuery();
			// 6. 결과 꺼내서 vo에 담고
			rs.next();
			vo.setSno(rs.getInt("sno"));
			vo.setTitle(rs.getString("title"));
			vo.setStartDate(rs.getDate("startDate"));
			vo.setEndDate(rs.getDate("endDate"));
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
	
	// 문항 데이터 조회 전담  처리함수
	public ArrayList<SurveyVO> getQuest(int sno){
		// sq_sno sno, sq_no qno, sq_cont body
		ArrayList<SurveyVO> list = new ArrayList<SurveyVO>();
		// 1. 커넥션 얻어오고
		con = db.getCon();
		// 2. 질의명령 가져오고
		String sql = sSQL.getSQL(sSQL.SEL_QUEST_SNO);
		// 3. PreparedStatement 얻어오고
		pstmt = db.getPSTMT(con, sql);
		
		try {
			// 4. 질의명령 완성하고
			pstmt.setInt(1, sno);
			// 5. 질의명령 보내고 결과 받고
			rs = pstmt.executeQuery();
			// 6. 결과에서 데이터 꺼내서 vo에 담고
			while(rs.next()) {
				SurveyVO vo = new SurveyVO();
				vo.setSno(rs.getInt("sno"));
				vo.setQno(rs.getInt("qno"));
				vo.setBody(rs.getString("body"));
				// 7. list 에 vo 담고
				list.add(vo);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		
		// 8. 데이터 반환해주고
		return list;
	}
	
	// 문항보기데이터 조회 전담 처리함수
	public ArrayList<ArrayList<SurveyVO>> getExData(ArrayList<SurveyVO> list){
		ArrayList<ArrayList<SurveyVO>> list1 = new ArrayList<ArrayList<SurveyVO>>();
		// 1. 커넥션 얻어오고
		con = db.getCon();
		// 2. 질의명령 가져오고
		String sql = sSQL.getSQL(sSQL.SEL_SRVEX_QNO);
		// 3. PreparedSatement 얻어오고
		pstmt = db.getPSTMT(con, sql);
		
		try{
			for(int i = 0 ; i < list.size(); i++ ) {
				ArrayList<SurveyVO> list2 = new ArrayList<SurveyVO>();
				// 4. 질의명령 완성하고 - 문항갯수만큼 반복해서 처리해야 한다.
				pstmt.setInt(1, list.get(i).getQno());
				// 5. 질의명령 보내고 결과를 받고 - 리스트를 먼저 만들고 VO채워준다.
				rs = pstmt.executeQuery();
				while(rs.next()) {
					SurveyVO vo = new SurveyVO();
					//seno, qno, ebody, se_count , total, per
					vo.setQno(rs.getInt("qno"));
					vo.setSeno(rs.getInt("seno"));
					vo.setEbody(rs.getString("ebody"));
					vo.setCount(rs.getInt("count"));
					vo.setTotal(rs.getInt("total"));
					vo.setPer(rs.getDouble("per"));
					// 6. 리스트에 담고
					list2.add(vo);
				}
				
				// 7. 리스트를 리스트에 담고
				list1.add(list2);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		// 8. 리스트 반환해주고
		return list1;
	}
}
