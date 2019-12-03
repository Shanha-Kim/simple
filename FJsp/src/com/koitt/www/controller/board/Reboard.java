package com.koitt.www.controller.board;

import java.util.*;
import javax.servlet.http.*;

import com.koitt.www.controller.*;
import com.koitt.www.dao.*;
import com.koitt.www.vo.*;

public class Reboard implements MainController {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String view = "/com/koitt/www/board/reboard.jsp";
		// 댓글게시판 테이블의 모든 데이터를 가져오고
		ArrayList<ReboardVO> list = new ReboardDAO().reboardAllList();
		// 데이터 뷰에 넘기고
		req.setAttribute("LIST", list);
		// 뷰 부르고
		return view;
	}

}
