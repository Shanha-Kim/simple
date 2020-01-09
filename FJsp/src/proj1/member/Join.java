package proj1.member;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

/**
 * 	이 클래스는 회원가입 요청이 올 경우 실행되는 클래스
 * 
 * @author	전은석
 * @since	2019.11.11
 * @version	v.1.0
 * @see
 * 		
 * 			변경이력
 * 				2019.11.11	-	Join 클래스 제작 	- 담당자 : 전은석
 *
 */

@WebServlet("/member/join.nop")
public class Join extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse resp)
										throws ServletException, IOException {
		// 할일
		// 데이터를 받아야 할 일이 없고
		// 단지 회원가입폼 요청이 요청이 들어오면
		// 뷰만 보여주면 될것 같다.
		String view = "/com/koitt/www/member/join.jsp";
		
		// 그런데 이미 로그인을 한 사람이 이 요청을 한 경우는
		// 세션 값을 확인후에 처리하면 되겠다.
		HttpSession session = req.getSession();
		String sid = (String)session.getAttribute("SID");
		
		if(sid == null || sid.length() == 0) {
			// 이 경우는 로그인 안한경우이므로 회원가입 폼으로 넘겨주자.
			
			// 요청은 유지하고 뷰만 바꿔서 보여주는 forward 방식으로 처리해야 한다.
			RequestDispatcher rd = req.getRequestDispatcher(view);
			rd.forward(req, resp);
		} else {
			// 이미 로그인이 된 상태이므로
			// 메인페이지로 강제로 보내도록 하자.
			resp.sendRedirect("/");
		}
		
	}
}
