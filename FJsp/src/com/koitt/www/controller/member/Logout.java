package com.koitt.www.controller.member;

import javax.servlet.http.*;

import com.koitt.www.controller.MainController;

public class Logout implements MainController {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String view = "/main";
		req.setAttribute("isRD", true);
		// 할일
		// 세션을 비운다.
		HttpSession session = req.getSession();
		session.setAttribute("SID", null);
		
		return view;
	}

}
