package proj1.member;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import vo.*;
import dao.*;

/**
 * 		이 클래스는 회원가입에 성공한 
 * 		회원들의 리스트를 보여주는 페이지를 부르는 클래스
 * @author 전은석
 * @since	2019.11.12
 * @version	v.1.0
 * 
 * 			변경이력
 * 				2019.11.12	-	클래스 작성	-	담당자 : 전은석
 *
 */

/*
	웹 어플리케이션을 제작을 하다보면 
	자바 클래스들이 다수 작성이 되는데
	이중에서 클라이언트의 요청을 처리해주는 클래스가 있고
	그 클래스의 처리를 도와주는 클래스가 있다.
	여기서
		Controller - 클라이언트의 요청을 처리해주는 클래스
					 (HttpServlet 을 상속받아서 제작한 클래스)
		일반클래스	- 컨트롤러의 처리를 도와주는 클래스들...
 */

@WebServlet("/member/memberList.nop")
public class MemberList extends HttpServlet {
	
	/*
			MemberList mlist = new MemberList();
			mlist.service(request, response);
	 */
	public void service(HttpServletRequest req, HttpServletResponse resp)
									throws ServletException, IOException {
		// 할일
		// 먼저 뷰만 띄워보자.
		String view = "/com/koitt/www/member/memberList.jsp";
		
		// 데이터 가져오고
		MemberDAO mDAO = new MemberDAO();
		
		// 여러명의 데이터가 리스트에 담겨서 반환이 될 것이기 때문에
		// ArrayList의 변수로 반환값을 받아둔다.
		ArrayList<MemberVO> list = mDAO.getMembList();
		
		/*
			컨트롤러에서 데이터를 뷰에게 넘겨주는 방법
			세션을 이용하던 요청객체(Request)를 이용하던지 동일하게 처리된다.
			setAttribute()함수로 넘겨줘야 되기 때문에...
			
			데이터를 꺼낼때는
				request.getAttribute("키값");
		 */
		req.setAttribute("LIST", list);
		
		// 뷰를 forward 시키자.
		req.getRequestDispatcher(view).forward(req, resp);
		/*
			RequestDispatcher rd = req.getRequestDispatcher(view);
			rd.forward(req, resp);
			를 한줄로 줄여서 표현한 방식이다.
		 */
	}
}
