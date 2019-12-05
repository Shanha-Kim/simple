package com.koitt.www.controller.survey;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koitt.www.controller.MainController;

public class SurveyResult implements MainController {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String view = "/com/koitt/www/survey/surveyResult.jsp";
		
		
		return view;
	}

}
