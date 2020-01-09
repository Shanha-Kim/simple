package com.koitt.www.controller.member;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import com.koitt.www.dao.*;
import com.koitt.www.vo.*;


@WebServlet("/member/infoEdit.ck")
public class InfoEdit extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse resp) 
									throws ServletException, IOException {
		// 할일
		// 1. 데이터 받고 vo에 담고
		String scode = req.getParameter("code");
		String sno = req.getParameter("mno");
		int mno = Integer.parseInt(sno);
		String smail = "";
		String stel = "";
		
		switch(scode) {
		case "1":
			smail = req.getParameter("mail");
			stel = req.getParameter("tel");
			break;
		case "2":
			smail = req.getParameter("mail");
			break;
		case "3":
			stel = req.getParameter("tel");
			break;
		}
		
		// 넘겨줄 데이터 만들고
		MemberVO vo = new MemberVO();
		vo.setMno(mno);
		vo.setMail(smail);
		vo.setTel(stel);
		
		// 2. 데이터 넘겨서 데이터 베이스 작업하고
		MemberDAO mDAO = new MemberDAO();
		int cnt = mDAO.memberEdit(vo, scode);
		// 3. 받은 결과 넘겨주고
		PrintWriter pw = resp.getWriter();
		pw.println("{");
		pw.println("	\"cnt\" : " + cnt);
		pw.print("}");
	}
}
