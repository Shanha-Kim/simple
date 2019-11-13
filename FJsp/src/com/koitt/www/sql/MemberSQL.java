package com.koitt.www.sql;

/**
 * 			이 클래스는 회원 정보 조회에 관련된 질의명령을 관리할 클래스
 * 
 * @author	전은석
 * @since	2019.11.13
 * @version	v.1.0
 * @see		com.koitt.www.MemberDAO
 * 
 * 			변경이력
 * 				2019.11.13	-	클래스 제작		- 담당자 : 전은석
 *
 */
public class MemberSQL {
	public final int GET_LOGIN			= 1001;
	public final int SEL_MEMB_INFO		= 1002;
	
	// 코드를 입력하면 질의명령을 반환해주는 함수
	public String getSQL(int code) {
		StringBuffer buff = new StringBuffer();
		
		switch(code) {
		case GET_LOGIN:
			buff.append("SELECT ");
			buff.append("	count(*) cnt ");
			buff.append("FROM ");
			buff.append("	member ");
			buff.append("WHERE ");
			buff.append("	m_id = ? ");
			buff.append("	AND m_pw = ? ");
			break;
		case SEL_MEMB_INFO:
			buff.append("SELECT ");
			buff.append("	m_no mno, m_id id, m_name name, ");
			buff.append("	m_mail mail, m_tel tel, m_join mdate ");
			buff.append("FROM ");
			buff.append("	member ");
			buff.append("WHERE ");
			buff.append("	m_id = ? ");
			break;
		}
		
		return buff.toString();
	}
}
