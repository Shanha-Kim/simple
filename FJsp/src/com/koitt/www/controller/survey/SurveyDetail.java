package com.koitt.www.controller.survey;

import java.util.*;
import javax.servlet.http.*;

import com.koitt.www.controller.*;
import com.koitt.www.dao.*;
import com.koitt.www.vo.*;


public class SurveyDetail implements MainController {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String view = "/com/koitt/www/survey/surveyDetail.jsp";
		// 데이터 받고
		String strsno = req.getParameter("sno");
		String title = req.getParameter("title");
		int sno = Integer.parseInt(strsno);
//		String sid = req.getParameter("sid");
		
		SurveyDAO sDAO = new SurveyDAO();
		ArrayList<SurveyVO> list = sDAO.getSDetailList(sno);
		
		req.setAttribute("LIST", list);
		req.setAttribute("SNO", sno);
		req.setAttribute("TITLE", title);
		
		return view;
	}

}
