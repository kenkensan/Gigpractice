package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Kadaidto;

/**
 * Servlet implementation class Kadai1Confirm
 */
@WebServlet("/Kadai1Confirm")
public class Kadai1Confirm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Kadai1Confirm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name =request.getParameter("name");
		int age =Integer.parseInt(request.getParameter("age")) ;
		String gen =request.getParameter("gender");
		int tell =Integer.parseInt(request.getParameter("tell")) ;
		String mail =request.getParameter("mail");
		String pw =request.getParameter("pw");
		String gender = gen.equals("0") ? "男" : "女";
		
		Kadaidto kadaidto=new Kadaidto(name,age,gender,tell,mail,null,pw,null);
		
				HttpSession session = request.getSession();

				session.setAttribute("input_data", kadaidto);
				String view = "WEB-INF/view/kadai1confirm.jsp";
				RequestDispatcher dispatcher = request.getRequestDispatcher(view);
				dispatcher.forward(request, response);	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
