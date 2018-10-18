package ASAPMainController;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ASAPMainDAO.CarDAO;
import ASAPMainDAO.ReservationDAO;
import ASAPMainDAO.UserDAO;
import ASAPMainModel.Car;
import ASAPMainModel.CarErrorMessages;
import ASAPMainModel.Reservation;
import ASAPMainModel.ReservationErrorMessages;
import ASAPMainModel.User;
import ASAPMainModel.UserErrorMessages;

/**
 * Servlet implementation class ManagerController
 */
@WebServlet("/ManagerController")
public class ManagerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerController() {
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
			updateManager(request, session, response);
		}
		else if(action.equals("carSearch")) {
			carSearchForDate(request, session, response);
		}
		else if(action.equals("checkReservation"))
		{
			checkReservation(request, session, response);
		}
		else if(action.equals("deleteReservation"))
		{
			deleteReservation(request, session, response);
		}
		//doGet(request, response);
	}
	
	private void updateManager(HttpServletRequest request, HttpSession session, HttpServletResponse response) throws ServletException, IOException 
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
	private void carSearchForDate(HttpServletRequest request, HttpSession session, HttpServletResponse response) throws ServletException, IOException
	{
		String startDate = request.getParameter("pickup");
		String endDate = request.getParameter("dropoff");
		String pickupTime = request.getParameter("time-pickup");
		String dropoffTime = request.getParameter("time-dropoff");

		CarDAO carDAO = new CarDAO();
		CarErrorMessages cerrormsg = new CarErrorMessages();

		Car care = new Car();

		Reservation reserve = new Reservation();
		ReservationErrorMessages rerror = new ReservationErrorMessages();

		reserve.setStartDate(startDate);
		reserve.setEndDate(endDate);
		reserve.setDropTime(dropoffTime);
		reserve.setPickUpTime(pickupTime);

		try {
			reserve.validateReservation(reserve, rerror);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		if(!(rerror.getErrorMsg().equals("")) || !(cerrormsg.getErrorMsg().equals(""))) {
			if(!(cerrormsg.getErrorMsg().isEmpty())) {
				session.setAttribute("cerrormsg", cerrormsg);
			}
			if(!(rerror.getErrorMsg().isEmpty())) {
				session.setAttribute("rerrormsg", rerror);

			}
			getServletContext().getRequestDispatcher("/managerHome.jsp").forward(request, response);
		} else {
			List<Car> carResult;
			try {
				String capacity="1000";
				carResult = carDAO.getAvailableCars(capacity, startDate, endDate, Integer.parseInt(dropoffTime));
				for (Iterator<Car> car = carResult.iterator(); car.hasNext(); ) {
					Car c = car.next();
					if(Integer.parseInt(c.getCapacity()) < Integer.parseInt(capacity)) {
						car.remove();
					}
				}
				session.setAttribute("startdate", startDate);
				session.setAttribute("enddate", endDate);
				session.setAttribute("pickup", pickupTime);
				session.setAttribute("dropoff", dropoffTime);
				session.setAttribute("cars", carResult);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			session.removeAttribute("cerrormsg");
			session.removeAttribute("rerrormsg");
			getServletContext().getRequestDispatcher("/managerHome.jsp").forward(request, response);

		}

	
	}
	
	private void checkReservation(HttpServletRequest request, HttpSession session, HttpServletResponse response) throws ServletException, IOException
	{
		String reservationID = request.getParameter("reservationID");
		Reservation reserve = new Reservation();
		ReservationErrorMessages rerror = new ReservationErrorMessages();
		reserve.setReservationID(reservationID);
		
		ReservationDAO reservationDAO = new ReservationDAO();
		ReservationErrorMessages rerrormsg = new ReservationErrorMessages();

		List<Reservation> reservationResult;
		try {
			reservationResult = reservationDAO.ReturnReservationBasedonID(reserve.getReservationID());
			session.setAttribute("reservations", reservationResult);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.removeAttribute("rerrormsg");
		getServletContext().getRequestDispatcher("/viewReservationManager.jsp").forward(request, response);
	
	}
	private void deleteReservation(HttpServletRequest request, HttpSession session, HttpServletResponse response) throws ServletException, IOException
	{
		String reservationID = request.getParameter("reservationID");
		Reservation reserve = new Reservation();
		ReservationErrorMessages rerror = new ReservationErrorMessages();
		reserve.setReservationID(reservationID);
		
		ReservationDAO reservationDAO = new ReservationDAO();
		ReservationErrorMessages rerrormsg = new ReservationErrorMessages();

		try {
			reservationDAO.cancelReservationBasedonID(reservationID);
			session.setAttribute("status", "success");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e)
		{
			e.printStackTrace();
		}
		session.removeAttribute("rerrormsg");
		getServletContext().getRequestDispatcher("/viewReservationManager.jsp").forward(request, response);
	}
}
