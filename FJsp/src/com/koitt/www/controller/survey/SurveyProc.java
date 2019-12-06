package com.koitt.www.controller.survey;

import java.util.*;
import javax.servlet.http.*;

import com.koitt.www.controller.*;
import com.koitt.www.dao.*;

public class SurveyProc implements MainController {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String view = "/survey/surveyResult.cls";
		
		// 데이터 받고
		String sid = req.getParameter("sid");
		String slen = req.getParameter("len");
		int len = Integer.parseInt(slen);
		ArrayList<Integer> list = new ArrayList<Integer>();
		// 문항 갯수만큼 반복해서 데이터 뽑아오고
		String str = "";
		for(int i = 1 ; i < len + 1 ; i++) {
			if(i != 1) {
				str += ", ?";
			}
			String tmp = req.getParameter(i + "");
			list.add(Integer.parseInt(tmp));
		}
		
		// 데이터베이스 작업하고
		
		// 결과에 따라 뷰 부르고
		
		
		return view;
	}

}
