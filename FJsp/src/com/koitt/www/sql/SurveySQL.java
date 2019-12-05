package com.koitt.www.sql;

/**
 * 	이 클래스는 설문조사에 관련된 질의명령을 저장하고
 * 	반환해주는 클래스
 * 
 * @author	전은석
 * @since	2019.12.05
 * @version	v.1.0
 * @see		com.koitt.www.dao.SurveyDAO
 * 
 * 			변경이력
 * 				2019.12.05		- 	클래스제작		- 담당자: 전은석
 * 	
 */

public class SurveySQL {
	public final int SEL_SRV_CUR 	= 1001;	// 현재 조사중인 설문 데이터 가져오는 질의명령
	
	// 질의명령 반환해주는 함수
	public String getSQL(int code) {
		StringBuffer buff = new StringBuffer();
		
		switch(code) {
		case SEL_SRV_CUR:
			buff.append("SELECT ");
			buff.append("	s_no sno, s_title title, s_start startDate, s_end endDate, ");
			buff.append("	decode( ");
			buff.append("        	( ");
			buff.append("            SELECT ");
			buff.append("                count(*) cnt ");
			buff.append("            FROM ");
			buff.append("                srv_ck ");
			buff.append("            WHERE ");
			buff.append("                sck_sno = s_no ");
			buff.append("                AND sck_mid = ? ");
			buff.append("        	), 0, 'N', ");
			buff.append("        	'Y') count ");
			buff.append("FROM ");
			buff.append("	survey ");
			buff.append("WHERE ");
			buff.append("	sysdate BETWEEN s_start AND s_end ");
			break;
		}
		
		return buff.toString();
	}
}
