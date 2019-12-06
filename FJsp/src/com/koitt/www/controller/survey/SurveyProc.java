package com.koitt.www.controller.survey;

import java.util.*;
import javax.servlet.http.*;

import com.koitt.www.controller.*;
import com.koitt.www.dao.*;

public class SurveyProc implements MainController {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String view = "/survey/surveyResult.cls";
		// 이 컨트롤러의 실행 결과는 성공하든 실패던지 결국 리다이렉트로 처리해야 한다.
		req.setAttribute("isRD", true);
		
		// 데이터 받고
		String sid = req.getParameter("sid");
		String slen = req.getParameter("len");
		String ssno = req.getParameter("sno");
		int sno = Integer.parseInt(ssno);
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
		int cnt = new SurveyDAO().updateCount(list, str, sno, sid);
		// 결과에 따라 뷰 부르고
		if(cnt != 1) {
			view = "/survey/survey.cls";
		}
		
		return view;
	}

}
