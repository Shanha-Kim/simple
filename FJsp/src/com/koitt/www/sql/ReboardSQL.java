package com.koitt.www.sql;

/**
 * 	이 클래스는 댓글게시판 질의명을 모아놓은 클래스
 * 
 * @author	전은석
 * @since	2019.12.03
 * @version	v.1.0
 * @see		
 * 			
 * 			변경이력
 * 				2019.12.03	-	클래스 제작	-	담당자 : 전은석
 *
 */
public class ReboardSQL {
	public final int SEL_ALL = 1001;
	
	// 코드를 입력해서 호출하면 질의명령을 반환해주는 함수
	public String getSQL(int code) {
		StringBuffer buff = new StringBuffer();
		
		switch(code) {
		case SEL_ALL:
			buff.append("SELECT ");
			buff.append("	m_id, avt, rb_no, rb_body, ");
			buff.append("	rb_date, rb_upno, (level - 1) lvl ");
			buff.append("FROM ");
			buff.append("	( ");
			buff.append("		SELECT ");
			buff.append("			m_id, avt, rb_no, rb_body, rb_date, rb_upno ");
			buff.append("		FROM ");
			buff.append("			reboard, ");
			buff.append("			(SELECT ");
			buff.append("				m_no, m_id, ");
			buff.append("				( ");
			buff.append("					SELECT ");
			buff.append("						a_img ");
			buff.append("					FROM ");
			buff.append("						avatar ");
			buff.append("					WHERE ");
			buff.append("						a_no = m_avt ");
			buff.append("				) avt ");
			buff.append("			FROM ");
			buff.append("				member ");
			buff.append("			) ");
			buff.append("		WHERE ");
			buff.append("			RB_ISSHOW = 'Y' ");
			buff.append("			AND m_no = rb_mno ");
			buff.append("	) ");
			buff.append("START WITH ");
			buff.append("	RB_UPNO IS NULL ");
			buff.append("CONNECT BY ");
			buff.append("	PRIOR RB_NO = RB_UPNO ");
			buff.append("ORDER SIBLINGS BY ");
			buff.append("	RB_DATE DESC");
			break;
		}
		
		return buff.toString();
	}
}
