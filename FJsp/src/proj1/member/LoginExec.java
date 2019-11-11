package proj1.member;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginExec extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse resp) 
											throws ServletException, IOException {
		// 보여줄 뷰 정의해 놓고
		String view = "/"; // 로그인에 성공하면 메인페이지로 보내자.
		
		// 데이터 받고
		String sid = req.getParameter("id");
		String spw = req.getParameter("pw");
		
		// 데이터베이스작업하고 결과 받고
		
		// 결과에 따라 뷰 부르고...
	}
}
