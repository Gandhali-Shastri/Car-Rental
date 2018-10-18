package ASAPMainController;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ASAPMainDAO.UserDAO;
import ASAPMainModel.User;
import ASAPMainModel.UserErrorMessages;

/**
 * Servlet implementation class AdminController
 */
@WebServlet("/AdminController")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminController() {
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
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		if(action.equals("updateUser")) {
			updateAdmin(request, session, response);
		}
		//doGet(request, response);
	}
	
	private void updateAdmin(HttpServletRequest request, HttpSession session, HttpServletResponse response) throws ServletException, IOException 
	{

		User user = new User();
		user.setUtaID(request.getParameter("utaID"));
		user.setFirstName(request.getParameter("firstName"));
		user.setlastName(request.getParameter("lastName"));
		user.setUserName(request.getParameter("userName"));
		user.setPword(request.getParameter("pword"));
		String confirm = request.getParameter("conpword");
		user.setEmail(request.getParameter("email"));
		user.setPhone(request.getParameter("phone"));
		user.setAddress(request.getParameter("address"));
		user.setState(request.getParameter("state"));
		user.setZipcode(request.getParameter("zipcode"));

		user.setUserRole(request.getParameter("userType"));


		//Gender
		user.setGender(request.getParameter("gender"));

		user.setUserRole(request.getParameter("userType"));
		UserDAO userReg=new UserDAO();
		UserErrorMessages errorMsgs = new UserErrorMessages();

		user.validateUser("UpdateController", user, errorMsgs);

		if (!errorMsgs.getErrorMsg().equals("")) {// if error messages

			session.setAttribute("uerrorMsgs", errorMsgs);
			getServletContext().getRequestDispatcher("/managerProfile.jsp").forward(request, response);
		}
		else {// if no error messages
			try {
				userReg.updateUser(user);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			session.removeAttribute("errorMsgs");
			getServletContext().getRequestDispatcher("/managerProfile.jsp").forward(request, response);
			session.setAttribute("user", user);

		}
	
	}

}
