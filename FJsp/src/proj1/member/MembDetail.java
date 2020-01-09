package proj1.member;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import vo.*;
import dao.*;

/**
 * 		이 클래스는 회원 리스트에서 회원을 선택했을 때 
 * 		해당 회원의 정보 요청을 처리하는 클래스
 *  
 * @author	전은석
 * @since	2019.11.12
 * @version	v.1.0
 * 
 * 			변경이력
 * 				2019.11.12	-	클래스 작성 	- 담당자 : 전은석
 * 				
 */
@WebServlet("/member/membDetail.nop")
public class MembDetail extends HttpServlet {
	
	public void service(HttpServletRequest req, HttpServletResponse resp)
								throws ServletException, IOException {
		
		// 할일
		// 데이터 받고
		String sno = req.getParameter("mno");
		int mno = Integer.parseInt(sno);
		// 데이터베이스에서 데이터가져오고
		MemberVO vo = new MemberDAO().getDetail(mno);
		// json 문서 만들어 주고
		// PrintWriter 객체를 받아서 처리한다.
		
		// 응답객체 인코딩 설정하기
		resp.setCharacterEncoding("UTF-8");
		
		String tdate = vo.getsDate() + "  " + vo.getsTime();
		PrintWriter pw = resp.getWriter();
		pw.print("{");
		pw.print("\"mno\": " + vo.getMno() + ",");
		pw.print("\"mid\": \"" + vo.getId() + "\",");
		pw.print("\"mname\": \"" + vo.getName() + "\",");
		pw.print("\"mmail\": \"" + vo.getMail() + "\",");
		pw.print("\"mtel\": \"" + vo.getTel() + "\",");
		pw.print("\"mdate\": \"" + tdate + "\"");
		pw.print("}");
		
	}
}
