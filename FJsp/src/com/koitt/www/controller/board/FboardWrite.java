package com.koitt.www.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koitt.www.controller.MainController;

public class FboardWrite implements MainController {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String view = "/com/koitt/www/board/fboardWrite.jsp";
		/*
			이 컨트롤러는 글쓰기 폼을 보여주는 컨트롤러이다.
			따라서 필요한 작성자 아이디는 세션에 저장이 되어 있으므로
			추가로 필요한 데이터가 없고
			뷰에서는 세션에서 필요한 데이터를 꺼내면 된다.
			따라서 뷰만 forward 시키면 된다.
		 */
		return view;
	}

}
