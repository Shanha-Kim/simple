package proj1.member;

/**
	이 클래스는 
	로그인 폼 요청이 들어오면 실행되는 클래스
	로그인 폼 요청 처리를 할 클래스...
 *
 *	@since	2019.11.08
 *	@autor	전은석
 *	@version	v.0.9
 *	@see
 *			
 *			클래서 이력
 *				2019.11.08 	- Login 클래스 제작 	- 담장자 : 전은석
 */

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Login extends HttpServlet {
	
	public void service(HttpServletRequest req, HttpServletResponse resp) 
											throws ServletException, IOException{
		String view = "/com/koitt/www/member/login.jsp";
		System.out.println("### Login Class 작동 ###");
		// 이 클래스는 로그인 폼 화면만 불러오는 기능을 가진 클래스이다.
		// 따라서 로그인 폼을 불러오는 작업만 해주면 될 것이다.
		// 그리고 굳이 새로운 요청을 하지 않아도 무방하다.
		// 따라서 뷰를 forward 방식으로 불러주기만 하면 된다.
		
		// forward 시키는 기능을 가진 객체가 별도로 존재하는데
		// 		RequestDispatcher 라는 객체이고
		// 이 객체는 요청 객체에서(req 에서) 함수로 제공해주고 있다.
		
		RequestDispatcher rd = req.getRequestDispatcher(view);
		rd.forward(req, resp);
	}
}
