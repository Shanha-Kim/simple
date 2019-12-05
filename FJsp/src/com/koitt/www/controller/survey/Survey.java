package com.koitt.www.controller.survey;

import java.util.*;
import javax.servlet.http.*;

import com.koitt.www.controller.*;
import com.koitt.www.dao.*;
import com.koitt.www.vo.*;

public class Survey implements MainController {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String view = "/com/koitt/www/survey/survey.jsp";
		String sid = (String)req.getSession().getAttribute("SID");
		
		if(sid == null || sid.length() == 0) {
			req.setAttribute("isRD", true);
			view = "/member/login.cls";
		}
		
		// 데이터베이스 작업해서 결과 가져오고
		ArrayList<SurveyVO> list = new SurveyDAO().getCurList(sid);
		
		// 데이터 넘기고
		req.setAttribute("LIST", list);
		
		return view;
	}

}
