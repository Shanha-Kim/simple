package com.koitt.www.controller.board;

import java.util.*;
import javax.servlet.http.*;

import com.koitt.www.controller.*;
import com.koitt.www.dao.*;
import com.koitt.www.vo.*;

public class BoardList implements MainController {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		// 뷰 정하고
		String view = "/com/koitt/www/board/boardList.jsp";
		// 2. 데이터받고 - 없으니 패스
		// 3. DAO에서 데이터베이스작업하고
		FileBoardDAO fbDAO = new FileBoardDAO();
		ArrayList<FileBoardVO> list = fbDAO.getFBList();
		
		// 4. 데이터 넘기고
		/*
			이 컨트롤러에서 부르는 뷰는 
			forward 방식으로 부를 것이고
			forward 방식은 요청을 유지하면서 뷰만 바꿔서 보여주는 것이므로
			요청 객체가 유지가 될 것이고
			이 말은 req 데이터가 계속 유지가 된다는 이야기다.
			따라서 req 에 데이터를 넣어 둔다면 뷰에서도 
			넣어둔 그 데이터를 사용할 수 있을 것이다.
			
			한마디로 forward 방식은 데이터 전달을 위해서 사용하는 방식이다.
		 */
		req.setAttribute("LIST", list);
		// 5. 뷰 부르고
		return view;
	}

}
