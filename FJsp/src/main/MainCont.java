package main;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

@WebServlet("/main")
public class MainCont extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "/main/main.jsp";
		req.getRequestDispatcher(view).forward(req, resp);
	}
}
