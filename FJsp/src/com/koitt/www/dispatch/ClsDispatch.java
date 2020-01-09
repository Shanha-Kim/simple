package com.koitt.www.dispatch;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import com.koitt.www.controller.*;


/**
 * 		이 클래스는 .cls 로 요청이 된 경우 해당 요청을 Dispatch 시킬 서블릿 클래스이다.
 * 
 * @author	전은석
 * @since	2019.11.13
 * @version v.1.0
 * @see
 * 			변경이력
 * 				2019.11.13	-	ClsDispatch 클래스 제작		-	담당자 : 전은석
 * 
 */

@WebServlet("*.cls")
public class ClsDispatch extends HttpServlet {
	// 요청내용을 키값으로 하고 실행될 클래스를 데이터로 입력될 맵을 선언한다.
	public HashMap<String, MainController> map;
	
	public void init(ServletConfig config) throws ServletException {
		/*
			할일
				최초로 .cls 로 끝나는 요청이 들어오면 
				이 함수는 처음 한번 호출이 될 것이고
				그때 위에 선언해둔 맵을 초기화 해주면 된다.
				
				 먼저 요청과 클래스의 경로를 파일(Properties)로 저장을 해두고
				 해당 요청에 해당하는 클래스를 객체로 만들어서 맵에 요청과 함께 넣어두자.
		 */
		Properties prop = new Properties();
		FileInputStream fin = null;
		try {
			// 우선 현재 클래스를 기준으로 실제 실행되는 경로를 가져온다.
			String path = this.getClass().getResource("").getPath();
			// 파일을 읽어오고
			fin = new FileInputStream(path + "../resources/ClsProperties.properties");
			// 파일내용을 prop 에 담는다.
			prop.load(fin);
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fin.close();
			}catch(Exception e) {}
		}
		
		// 맵을 초기화하고 맵에 작성한 문서의 키값과 클래스객체를 쌍으로 넣어주자.
		map = new HashMap<String, MainController>();
		// 맵에 저장할 키값을 먼저 뽑아오자.
		Enumeration en = prop.keys();
		while(en.hasMoreElements()) {
			// 하나씩 키값을 꺼내고
			String key = (String) en.nextElement();
			// 이 키값에 해당하는 데이터를 꺼내고
			String strClass = prop.getProperty(key);
			// 꺼낸 데이터는 문자열로된 클래스이름고 이것을 실제 실행가능한 클래스로 만들어 줘야 한다.
			try {
				// 문자열을 실제 클래스로 변환 시키고
				Class tmp = Class.forName(strClass);
				// 강제로 new 시키고 ==> 인스턴스를 만들고
				MainController o = (MainController) tmp.newInstance();
				/*
					Object o = tmp.newInstance();
				 */				
				map.put(key, o);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	// 요청이 들어왔을 때 요청에 대해서 실제 처리하는 함수
	public void service(HttpServletRequest req, HttpServletResponse resp)
									throws ServletException, IOException {
		// 이 함수는 디스패치 서블릿의 목적에 맞게
		// 요청을 분석하고
		// 1. 전체 내용 분석
		String full = req.getRequestURI();
		// 2. 도메인을 찾아내고
		String domain = req.getContextPath();
		// 3. 실제요청만 알아내고
		String real = full.substring(domain.length());
		
		// 원하는 컨트롤러를 선택해서
		// <== 맵에 요청과 컨트롤러의 객체를 입력해 놓았으므로 키값으로 꺼내면 된다.
		MainController controller = map.get(real);
		// 실행하고
		
		// 함수의 실행 결과가 forward 인지 redirect로 요청을 새로 만들어야 하는지
		// 를 담을 데이터를 요청객체에 담아 놓고
		// forward 일 경우에는 false로 redirect 일 경우에는 true 로 데이터를 저장해서 보내고
		// 뷰를 부를때 그 값을 불러서 처리하면
		// 두가지 방식 모두 처리가 될 것이다.
		req.setAttribute("isRD", false);
		String view = controller.execute(req, resp);
		
		// 뷰를 호출한다.
		/*
			뷰는 컨트롤러에서 생산한 데이터를 이용해야 하므로
			요청이 계속 유지가 되어야하고
			만약 요청을 새롭게 만든다면 데이터는 사라지게 될 것이다.
			따라서 우리는 데이터를 유지해서 처리해야 하므로
				forward 방식으로 처리를 해야 한다.
		 */
		
		boolean isRD = (boolean) req.getAttribute("isRD");
		if(isRD == true) {
			resp.sendRedirect(view);
		}else {
			RequestDispatcher rd = req.getRequestDispatcher(view);
			rd.forward(req, resp);
		}
	}
}












