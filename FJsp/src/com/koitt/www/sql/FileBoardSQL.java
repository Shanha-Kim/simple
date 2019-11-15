package com.koitt.www.sql;

public class FileBoardSQL {
	// 코드를 만들고
	public final int SEL_ALL = 1001;
	
	public final int DEL_CONTENT = 2001;
	public final int EDIT_CONTENT = 2002;
			
	public final int ADD_CONTENT = 3001;
	
	public final int SEL_FNO = 5001;
	
	public final int ADD_FILEINFO = 7001;
	
	// 코드를 입력해서 실행하면 질의명령을 보내주는 함수
	public String getSql(int code) {
		StringBuffer buff = new StringBuffer();
		
		// 요청되는 코드에 따라서 질의명령 만들어서
		switch(code) {
		case SEL_ALL:
			buff.append("SELECT ");
			buff.append("	fb_no bno, ");
			buff.append("	( ");
			buff.append("		SELECT ");
			buff.append("			m_id ");
			buff.append("		FROM ");
			buff.append("			member ");
			buff.append("		WHERE ");
			buff.append("			m_no = fb_writer ");
			buff.append("	) writer, ");
			buff.append("	fb_wdate wdate, fb_title title ");
			buff.append("FROM ");
			buff.append("	fileboard ");
			buff.append("WHERE ");
			buff.append("	fb_isshow = 'Y' ");
			break;
		case ADD_FILEINFO:
			buff.append("INSERT INTO ");
			buff.append("	fileinfo( ");
			buff.append("	f_no, f_oname, f_sname, f_dir, f_len ");
			buff.append("	) ");
			buff.append("VALUES( ");
			buff.append("	( ");
			buff.append("		SELECT ");
			buff.append("			NVL(MAX(f_no) + 1, 10001) ");
			buff.append("		FROM ");
			buff.append("			fileinfo ");
			buff.append("	), ");
			buff.append("	?, ?, ?, ? ");
			buff.append(") ");
			break;
		case SEL_FNO:
			buff.append("SELECT ");
			buff.append("	f_no fno ");
			buff.append("FROM ");
			buff.append("	fileinfo ");
			buff.append("WHERE ");
			buff.append("	f_sname = ? ");
			break;
		}
		
		return buff.toString();
	}
}
