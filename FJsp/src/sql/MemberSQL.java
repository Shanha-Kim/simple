package sql;

public class MemberSQL {
	public final int SEL_MEMB_CNT 		= 1001;
	public final int SEL_ID_CK 			= 1002;
	public final int SEL_MEMB_ALL 		= 1003;
	public final int SEL_MEMB_DETAIL 	= 1004;
	
	public final int ADD_MEMB			= 3001;
	
	// 코드를 입력하고 실행하면 질의명령을 반환해주는 함수
	public String getSQL(int code) {
		StringBuffer buff = new StringBuffer();
		
		switch(code) {
		case SEL_MEMB_CNT:
			buff.append("SELECT ");
			buff.append("	count(*) cnt ");
			buff.append("FROM ");
			buff.append("	member ");
			buff.append("WHERE ");
			buff.append("	m_id = ? ");
			buff.append("	AND m_pw = ? ");
			break;
		case SEL_ID_CK:
			buff.append("SELECT ");
			buff.append("	count(*) cnt ");
			buff.append("FROM ");
			buff.append("	member ");
			buff.append("WHERE ");
			buff.append("	m_id = ? ");
			break;
		case SEL_MEMB_DETAIL:
			buff.append("SELECT ");
			buff.append("	m_no mno, m_id id, m_name name, ");
			buff.append("	m_mail mail, m_tel tel, m_join mdate ");
			buff.append("FROM ");
			buff.append("	member ");
			buff.append("WHERE ");
			buff.append("	m_no = ? ");
			break;
		case SEL_MEMB_ALL:
			buff.append("SELECT ");
			buff.append("	m_no mno, m_name name, m_tel tel ");
			buff.append("FROM ");
			buff.append("	member ");
			break;
		case ADD_MEMB:
			buff.append("INSERT INTO ");
			buff.append("	member ");
			buff.append("VALUES( ");
			buff.append("	(SELECT NVL(MAX(m_no) + 1, 1001) FROM member), ");
			buff.append("	?, ?, ?, ?, ?, sysdate ");
			buff.append(")");
			break;
		}
		
		return buff.toString();
	}
}
