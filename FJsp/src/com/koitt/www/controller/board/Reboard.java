package com.koitt.www.controller.board;

import java.util.*;
import javax.servlet.http.*;

import com.koitt.www.controller.*;
import com.koitt.www.dao.*;
import com.koitt.www.vo.*;
import com.koitt.www.util.*;

public class Reboard implements MainController {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String view = "/com/koitt/www/board/reboard.jsp";
		// 데이터 받고
		String sPage = req.getParameter("nowPage");
		int nowPage = 1;
		if(sPage != null) {
			nowPage = Integer.parseInt(sPage);
		}
		
		ReboardDAO rDAO = new ReboardDAO();
		int total = rDAO.getTotal();
		PageUtil pUtil = new PageUtil(nowPage, total); 
		// 댓글게시판 테이블의 모든 데이터를 가져오고
		ArrayList<ReboardVO> list = new ReboardDAO().reboardAllList(pUtil.getStartCont(), pUtil.getEndCont());
		// 데이터 뷰에 넘기고
		req.setAttribute("LIST", list);
		req.setAttribute("PAGE", pUtil);
		// 뷰 부르고
		return view;
	}

}
