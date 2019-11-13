package com.koitt.www.controller.member;

import javax.servlet.http.*;

import com.koitt.www.controller.MainController;
import com.koitt.www.dao.*;

/**
 * 		이 클래스는 로그인 처리 요청이 왔을 때 요청을 처리할 클래스
 * 
 * @author	전은석
 * @since	2019.11.13
 * @version	v.1.0
 * @see		
 * 			변경이력
 * 				2019.11.13	-	LoginProc 클래스 제작	-	담당자 : 전은석
 *
 */
public class LoginProc implements MainController {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		// 이 함수는 반환해주는 뷰가 처리 결과가 성공이든 실패든 redirect 방식으로 처리 되어야 한다.
		// redirect 로 처리를 먼저 결정해준다.
		req.setAttribute("isRD", true);
		String view = "/main";
		/*
			할일
				1. 먼저 데이터 받고
				2. 받은 데이터로 데이터베이스에 맞는 회원이 있는지 조회
				3. 결과에 따라 처리해주고
					1 인 경우는 로그인처리해주고
					1이 아닌 경우는 다시 로그인페이지로 이동
		 */
		// 1.
		String sid = req.getParameter("id");
		String spw = req.getParameter("pw");
		
		// 2. 
		MemberDAO mDAO = new MemberDAO();
		int cnt = mDAO.getLogin(sid, spw);
		
		if(cnt == 1) {
			// 이경우는 로그인 처리를 해줘야 되는 경우이므로
			// 세션을 꺼내오고 세션에 아이디를 입력해 놓자.
			HttpSession session = req.getSession();
			session.setAttribute("SID", sid);
		} else {
			// 이 경우는 로그인에 실패한 경우이므로 로그인페이지로 돌려보내자.
			view = "login.cls";
		}
		
		return view;
	}

}
