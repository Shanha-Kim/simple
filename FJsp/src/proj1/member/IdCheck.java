package proj1.member;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import javax.servlet.annotation.*;

import dao.*;

/**
 * 	이 클래스는 아이디 체크 요청을 비동기 방식으로 처리할 클래스
 * 
 * @author 전은석
 * @since	2019.11.11
 * @version	v.1.0
 * @see		dao.MemerDAO, java.io.PrintWriter
 * 
 * 			변경이력
 * 				2019.11.11	-	클래스제작	-	담당자 : 전은석
 *
 */

@WebServlet("/member/idCheck.ck")
public class IdCheck extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse resp)
						throws ServletException, IOException{
		// 할일
		// 1. 데이터 받고
		String sid = req.getParameter("id");
		// 2. 데이터베이스에서 조회하고
		MemberDAO mDAO = new MemberDAO();
		int cnt = mDAO.getIdCnt(sid);
		// 3. 결과를 보내주고...
		PrintWriter pw = resp.getWriter();
		/*
			json 형식
				{
					"키값" : 데이터,
					"키값" : 데이터,
					...
				}
		 */
		pw.println("{");
		pw.println("	\"cnt\" : " + cnt);
		pw.println("}");
	}
}
