package proj1.member;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import dao.*;

// 서블릿 매핑은 어노테이션 방식으로 처리한다.
@WebServlet("/member/loginExec.nop")
public class LoginExec extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse resp) 
											throws ServletException, IOException {
		System.out.println("######### service() ############");
		// 보여줄 뷰 정의해 놓고
		String view = "/"; // 로그인에 성공하면 메인페이지로 보내자.
		
		// 데이터 받고
		String sid = req.getParameter("id");
		String spw = req.getParameter("pw");
		
		// 데이터베이스작업하고 결과 받고
		MemberDAO mDAO = new MemberDAO();
		int cnt = mDAO.getCnt(sid, spw);
		// 결과에 따라 뷰 부르고...
		if(cnt == 1) {
			// 해당 정보의 회원이 있는 경우이므로 로그인 처리를 해야된다.
			
			// 로그인 처리를 해주고
			// 로그인 처리는 세션에 SID라는 키값으로 아이디를 입력해 두기로 하자.
			HttpSession session = req.getSession();
			session.setAttribute("SID", sid);
			// 참고] 세션에 입력되는 데이터는 모두 Object 타입으로 입력이 된다.
			
			// 메인페이지로 리다이렉트 시키자.
			resp.sendRedirect(view);
		} else {
			// 행당 정보의 회원이 없는 경우이므로 다시 로그인 페이지로 보낸다.
			view = "/member/login.nop";
			resp.sendRedirect(view);
		}
	}
	public void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		System.out.println("######### doGet() ############");
		// 보여줄 뷰 정의해 놓고
		String view = "/"; // 로그인에 성공하면 메인페이지로 보내자.
		
		// 데이터 받고
		String sid = req.getParameter("id");
		String spw = req.getParameter("pw");
		
		// 데이터베이스작업하고 결과 받고
		MemberDAO mDAO = new MemberDAO();
		int cnt = mDAO.getCnt(sid, spw);
		// 결과에 따라 뷰 부르고...
		if(cnt == 1) {
			// 해당 정보의 회원이 있는 경우이므로 로그인 처리를 해야된다.
			
			// 로그인 처리를 해주고
			// 로그인 처리는 세션에 SID라는 키값으로 아이디를 입력해 두기로 하자.
			HttpSession session = req.getSession();
			session.setAttribute("SID", sid);
			// 참고] 세션에 입력되는 데이터는 모두 Object 타입으로 입력이 된다.
			
			// 메인페이지로 리다이렉트 시키자.
			resp.sendRedirect(view);
		} else {
			// 행당 정보의 회원이 없는 경우이므로 다시 로그인 페이지로 보낸다.
			view = "/member/login.nop";
			resp.sendRedirect(view);
		}
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		System.out.println("######### doPost() ############");
		// 보여줄 뷰 정의해 놓고
		String view = "/"; // 로그인에 성공하면 메인페이지로 보내자.
		
		// 데이터 받고
		String sid = req.getParameter("id");
		String spw = req.getParameter("pw");
		
		// 데이터베이스작업하고 결과 받고
		MemberDAO mDAO = new MemberDAO();
		int cnt = mDAO.getCnt(sid, spw);
		// 결과에 따라 뷰 부르고...
		if(cnt == 1) {
			// 해당 정보의 회원이 있는 경우이므로 로그인 처리를 해야된다.
			
			// 로그인 처리를 해주고
			// 로그인 처리는 세션에 SID라는 키값으로 아이디를 입력해 두기로 하자.
			HttpSession session = req.getSession();
			session.setAttribute("SID", sid);
			// 참고] 세션에 입력되는 데이터는 모두 Object 타입으로 입력이 된다.
			
			// 메인페이지로 리다이렉트 시키자.
			resp.sendRedirect(view);
		} else {
			// 행당 정보의 회원이 없는 경우이므로 다시 로그인 페이지로 보낸다.
			view = "/member/login.nop";
			resp.sendRedirect(view);
		}
	}
}
