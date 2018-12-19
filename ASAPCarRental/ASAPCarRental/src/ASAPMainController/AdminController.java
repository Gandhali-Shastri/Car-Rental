package ASAPMainController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ASAPMainDAO.ReservationDAO;
import ASAPMainDAO.UserDAO;
import ASAPMainModel.Reservation;
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
//		HttpSession session = request.getSession();
//		String action = request.getParameter("action");
//		if(action.equals("returnAllUsers"))
//		{
//			returnAllUsers(request, session, response);
//		}
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		if(action.equals("updateUser")) {
			updateUser(request, session, response);
		}
		else if(action.equals("revokeUser"))
		{
			revokeUser(request, session, response);
		}
		else if(action.equals("searchUser"))
		{
			searchUser(request, session, response);
		}
		else if(action.equals("returnAllUsers"))
		{
			returnAllUsers(request, session, response);
		}
		//doGet(request, response);
	}

	private void updateUser(HttpServletRequest request, HttpSession session, HttpServletResponse response) throws ServletException, IOException 
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

		
		User use = (User) session.getAttribute("updateuser");
		user.setUserRole(use.getUserRole());
		user.setUserName(use.getUserName());
		user.setlicExp(request.getParameter("licExp"));
		user.setlicNo(request.getParameter("licNo"));
		user.setStatus(request.getParameter("status"));
		user.setAge(request.getParameter("age"));
			
		//Gender
		user.setGender(use.getGender());
		user.setAac(use.isAac());
		//user.setUserRole(request.getParameter("userType"));
		UserDAO userReg=new UserDAO();
		UserErrorMessages errorMsgs = new UserErrorMessages();

		user.validateUser("UpdateController", user, errorMsgs);

		if (!errorMsgs.getErrorMsg().equals("")) {// if error messages

			session.setAttribute("uerrorMsgs", errorMsgs);
			getServletContext().getRequestDispatcher("/adminUpdate.jsp").forward(request, response);
		}
		else {// if no error messages
			try {
				userReg.updateUser(user);
				user = userReg.returnUserDetails(user.getUserName());
			} catch (SQLException e) {
				
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			session.removeAttribute("uerrorMsgs");
			session.setAttribute("updateuser", user);
			getServletContext().getRequestDispatcher("/adminUpdate.jsp").forward(request, response);
		}
	}
	
	private void searchUser(HttpServletRequest request, HttpSession session, HttpServletResponse response) throws ServletException, IOException 
	{
		String username = request.getParameter("userName");
		UserErrorMessages errorMsgs = new UserErrorMessages();
		if(username.isEmpty()) {
			errorMsgs.setUserNameMsg("Please enter username");
			errorMsgs.setErrorMsg("AdminController");
			session.setAttribute("uerrorMsgs", errorMsgs);
			getServletContext().getRequestDispatcher("/adminUpdate.jsp").forward(request, response);
		} else {

			UserDAO usrDAO = new UserDAO();
			User users = usrDAO.returnUserDetails(username);
			
			session.setAttribute("updateuser", users);
			getServletContext().getRequestDispatcher("/adminUpdate.jsp").forward(request, response);
		}
	}

	private void revokeUser(HttpServletRequest request, HttpSession session, HttpServletResponse response) throws ServletException, IOException
	{
		String username = request.getParameter("username");
		User user=new User();
		
		UserErrorMessages errorMsgs = new UserErrorMessages();
		if(username.isEmpty()) {
			errorMsgs.setUserNameMsg("Please enter username");
			errorMsgs.setErrorMsg("AdminController");
			session.setAttribute("revokeErrorMsg", errorMsgs);
			getServletContext().getRequestDispatcher("/revokeUser.jsp").forward(request, response);
		} else {
			UserDAO usedao=new UserDAO();
			
			user=usedao.returnUserDetails(username);
			if(user!=null)
			{
				ArrayList<Reservation> reservation=new ArrayList<Reservation>();
				ReservationDAO reserve=new ReservationDAO();
				reservation=reserve.returnReservationForUser(username);
				try{
					for(Reservation res: reservation) {
						reserve.cancelReservation(username, res.getReservationID());
					}
					//revoke user here
					usedao.revokeUser(username);
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
				getServletContext().getRequestDispatcher("/AdminController?action=returnAllUsers").forward(request, response);
			}
			else
			{
				errorMsgs.setUserNameMsg("Username does not exist");
				errorMsgs.setErrorMsg("AdminController");
				session.setAttribute("revokeErrorMsg", errorMsgs);
				getServletContext().getRequestDispatcher("/revokeUser.jsp").forward(request, response);
			}

		}
		
		
	}

	private void returnAllUsers(HttpServletRequest request, HttpSession session, HttpServletResponse response) throws ServletException, IOException
	{
		UserDAO userdao=new UserDAO();
		ArrayList<User> users = userdao.returnAllUsers();
		session.setAttribute("adminuser", users);
		getServletContext().getRequestDispatcher("/adminHome.jsp").forward(request, response);



	}

}
