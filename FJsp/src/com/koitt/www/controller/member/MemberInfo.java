package com.koitt.www.controller.member;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import com.koitt.www.dao.*;
import com.koitt.www.vo.*;

@WebServlet("/member/membInfo.ck")
public class MemberInfo extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 할일
		// 1. 데이터받고
		String sid = req.getParameter("mid");
		// 2. 데이터베이스 작업으로 데이터 가져오고
		MemberDAO mDAO = new MemberDAO();
		MemberVO vo = mDAO.getMemberInfo(sid);
		// 3. 응답 문서(json) 만들고
		// 응답객체에 한글이 포함되는 경우 처리를 해줘야 하기때문에
		resp.setCharacterEncoding("utf-8");
		PrintWriter pw = resp.getWriter();
		pw.println("{");
		pw.println("\"mno\": " + vo.getMno() + ",");
		pw.println("\"mid\": \"" + vo.getId() + "\",");
		pw.println("\"mname\": \"" + vo.getName() + "\",");
		pw.println("\"mmail\": \"" + vo.getMail() + "\",");
		pw.println("\"mtel\": \"" + vo.getTel() + "\",");
		pw.println("\"mdate\": \"" + vo.getsDate() + "  " + vo.getsTime() + "\"");
		pw.print("}");
		
	}
}
