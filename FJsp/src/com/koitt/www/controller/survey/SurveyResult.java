package com.koitt.www.controller.survey;

import java.util.*;
import javax.servlet.http.*;

import com.koitt.www.controller.*;
import com.koitt.www.dao.*;
import com.koitt.www.vo.*;

public class SurveyResult implements MainController {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String view = "/com/koitt/www/survey/surveyResult.jsp";
		// 1. 파라미터 받고
		String strno = req.getParameter("sno");
		int sno = Integer.parseInt(strno);
		// 2. 데이터베이스 작업하고
		SurveyDAO sDAO = new SurveyDAO();
		SurveyVO vo = sDAO.getSrvSno(sno);
		ArrayList<SurveyVO> list1 = sDAO.getQuest(sno);
		ArrayList<ArrayList<SurveyVO>> list2 = sDAO.getExData(list1);
		// 3. 데이터 넘기고
		req.setAttribute("DATA", vo);
		req.setAttribute("LIST1", list1);
		req.setAttribute("LIST2", list2);
		
		// 4. 뷰 부르고
		return view;
	}

}
