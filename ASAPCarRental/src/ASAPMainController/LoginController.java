package ASAPMainController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.util.StringUtils;

import ASAPMainDAO.UserDAO;
import ASAPMainModel.User;
import ASAPMainModel.UserErrorMessages;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		User user = new User();
		user.setUserName(request.getParameter("username"));
		user.setPword(request.getParameter("password"));
		session.setAttribute("loginForm",user);
		UserErrorMessages errorMsgs = new UserErrorMessages();
		
		if(StringUtils.isNullOrEmpty(user.getUserName())) {
			errorMsgs.setUserNameMsg("Username cannot be empty");
			errorMsgs.setErrorMsg("Username or Password empty");
		}
		if(StringUtils.isNullOrEmpty(user.getPword())) {
			errorMsgs.setPwordMsg("Password cannot be empty");
			errorMsgs.setErrorMsg("Username or Password empty");
		}
		UserDAO userReg=new UserDAO();
		

		user.validateUser("LoginController", user, errorMsgs);

		if (!errorMsgs.getErrorMsg().equals("")) {// if error messages

			session.setAttribute("errorMsgs", errorMsgs);
			getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
		}
		else {// if no error messages
			User userDetail = userReg.returnUserDetails(user.getUserName());
			
			if(userDetail == null) {
				errorMsgs.setUserNameMsg("Username not found");
				session.setAttribute("lerrorMsgs", errorMsgs);
				getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
			} else if (!userDetail.getPword().equals(user.getPword())) {
				errorMsgs.setPwordMsg("Password Incorrect");
				getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
			} else {
				session.setAttribute("user",userDetail);
				session.removeAttribute("lerrorMsgs");
				if(userDetail.getUserRole().equals("user")) {
					getServletContext().getRequestDispatcher("/userHome.jsp").forward(request, response);
				}
				if(userDetail.getUserRole().equals("manager")) {
					getServletContext().getRequestDispatcher("/managerHome.jsp").forward(request, response);
				}
				if(userDetail.getUserRole().equals("admin")) {
					getServletContext().getRequestDispatcher("/adminHome.jsp").forward(request, response);
				}
				
			}
		}

		doGet(request, response);
	}

}
