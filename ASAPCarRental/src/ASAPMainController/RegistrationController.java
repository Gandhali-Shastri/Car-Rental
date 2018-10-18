package ASAPMainController;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
 * Servlet implementation class RegistrationController
 */
@WebServlet("/RegistrationController")
public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationController() {
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
		
		User user=new User();
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
		user.setlicNo(request.getParameter("licNo"));
		String linExp = request.getParameter("licExp");
		if(linExp.isEmpty()) {
			user.setlicExp(null);
		}else {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
			
			try {
				Date date = simpleDateFormat.parse(linExp);
				simpleDateFormat.applyPattern("yyyy-MM-dd");
				linExp = simpleDateFormat.format(date);
				user.setlicExp(linExp);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		}
		if(!request.getParameter("age").equals("")) {
			user.setAge(request.getParameter("age"));
		} else {
			user.setAge("18");
		}
		
		user.setUserRole(request.getParameter("userType"));
		
		//AAC
		String aac=request.getParameter("aacTrue");
		if(aac!=null)
		{
			user.setAac(true);
		}
		else
		{
			aac=request.getParameter("aacFalse");
			if(aac!=null)
			{
				user.setAac(false);
			}
			else
			{
				//What the hell bro?!
			}
		}
		
		//Gender
		String gender=request.getParameter("male");
		if(gender!=null)
		{
			user.setGender("male");
		}
		else
		{
			gender=request.getParameter("female");
			if(gender!=null)
			{
				user.setGender("female");
			}
			else
			{
				//What the hell bro?!
			}
		}
		
		String userRole=request.getParameter("userType");
		
		if(userRole=="user")
		{
			user.setUserRole("user");
		}
		else if(userRole=="manager")
		{
			user.setUserRole("manager");
		}
		else if(userRole=="admin")
		{
			user.setUserRole("admin");
		}
		
		UserDAO userReg=new UserDAO();
		UserErrorMessages errorMsgs = new UserErrorMessages();
		//user.setAge("18");
		user.validateUser("RegistrationController", user, errorMsgs);
		session.setAttribute("register", user);
		if (!errorMsgs.getErrorMsg().equals("")) {// if error messages
			
			session.setAttribute("errorMsgs", errorMsgs);
			getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
		}
		else {// if no error messages
//			user.setAge("18");
			user.setStatus("Active");
			userReg.registerUser(user);
			session.removeAttribute("register");
			session.removeAttribute("errorMsgs");
			getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
		}
		
		doGet(request, response);
	}

}
