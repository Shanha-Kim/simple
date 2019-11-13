package com.koitt.www.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koitt.www.controller.MainController;

public class LoginForm implements MainController {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String view = "/com/koitt/www/member/login.jsp";
		/*
		boolean bool = (boolean) req.getAttribute("isRD");
		// 만약 이 요청의 결과가 리다이렉트인 경우는 
		// 요청객체에 저장된 "isRD" 의 데이터를 true 로 변경해주면 될 것이다.
		req.setAttribute("isRD", true);
		 * 
		 */
		
		return view;
	}

}
