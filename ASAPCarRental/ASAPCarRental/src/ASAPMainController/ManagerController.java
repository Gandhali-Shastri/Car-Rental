package ASAPMainController;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
			System.out.println("UpdateUser "+ action);
			updateManager(request, session, response);
		}
		else if(action.equals("carSearch")) {
			carSearchForDate(request, session, response);
			try {
				getBookedCars(request, session, response);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(action.equals("checkReservation"))
		{
			checkReservation(request, session, response);
		}
		else if(action.equals("deleteReservation"))
		{
			deleteReservation(request, session, response);
		}
		else if(action.equals("addCar"))
		{
			addCar(request, session, response);
		}
		//doGet(request, response);
	}
	
	private void updateManager(HttpServletRequest request, HttpSession session, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println("Reaching in updateManger");
		User user = new User();
		user.setUtaID(request.getParameter("utaID"));
		user.setFirstName(request.getParameter("firstName"));
		user.setlastName(request.getParameter("lastName"));
		user.setUserName(request.getParameter("userName"));
		user.setPword(request.getParameter("pword"));
		String confirm = request.getParameter("conpword");
		user.setEmail(request.getParameter("email"));
		user.setPhone(" ");
		user.setAddress(" ");
		user.setState(" "); 
		user.setZipcode(" ");
		user.setlicExp("2000-10-10");
		user.setlicNo(" ");
		user.setAge("18");
		User use = (User) session.getAttribute("user");
		user.setUserRole("manager");
		user.setStatus("Active");
		user.setUserName(use.getUserName());

		//Gender
		//user.setGender(request.getParameter("gender"));
		//user.setUserRole(request.getParameter("userType"));
		UserDAO userReg=new UserDAO();
		UserErrorMessages errorMsgs = new UserErrorMessages();

		user.validateUser("ManagerController", user, errorMsgs);

		if (!errorMsgs.getErrorMsg().equals("")) {// if error messages
			System.out.println(errorMsgs.getErrorMsg());
			session.setAttribute("uerrorMsgs", errorMsgs);
			getServletContext().getRequestDispatcher("/managerUpdate.jsp").forward(request, response);
		}
		else {// if no error messages
			try {
				userReg.updateUser(user);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			session.removeAttribute("uerrorMsgs");
			session.removeAttribute("errorMsgs");
			getServletContext().getRequestDispatcher("/managerUpdate.jsp").forward(request, response);
			System.out.println(user);
			session.setAttribute("user", user);

		}
		doGet(request,response);
	
	}
	private void carSearchForDate(HttpServletRequest request, HttpSession session, HttpServletResponse response) throws ServletException, IOException
	{
		String startDate = request.getParameter("pickup");
		String endDate = request.getParameter("dropoff");
		String pickupTime = request.getParameter("time-pickup");
		int dropoffTime = 8;

		CarDAO carDAO = new CarDAO();
		CarErrorMessages cerrormsg = new CarErrorMessages();

		Car care = new Car();
		System.out.println("You see me rolling!");
		Reservation reserve = new Reservation();
		ReservationErrorMessages rerror = new ReservationErrorMessages();

		reserve.setStartDate(startDate);
		reserve.setEndDate(endDate);
		//reserve.setDropTime(dropoffTime);
		reserve.setPickUpTime(pickupTime);

		try {
			reserve.validateReservation(reserve, rerror, "managerReservation");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		if(!(rerror.getErrorMsg().equals("")) || !(cerrormsg.getErrorMsg().equals(""))) {
			if(!(cerrormsg.getErrorMsg().isEmpty())) {
				System.out.println("Search car");
				session.setAttribute("cerrormsg", cerrormsg);
			}
			if(!(rerror.getErrorMsg().isEmpty())) {
				session.setAttribute("rerrormsg", rerror);

			}
			getServletContext().getRequestDispatcher("/managerHome.jsp").forward(request, response);
		} else {
			List<Car> carResult;
			try {
				String capacity="1";
				carResult = carDAO.getAvailableCars(capacity, startDate, endDate, dropoffTime);
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
			//getServletContext().getRequestDispatcher("/managerHome.jsp").forward(request, response);

		}

	
	}
	
	private void checkReservation(HttpServletRequest request, HttpSession session, HttpServletResponse response) throws ServletException, IOException
	{
		String reservationID = request.getParameter("reservationID");
		if(reservationID.isEmpty()) {
			ReservationErrorMessages rerror = new ReservationErrorMessages();
			rerror.setDropTimeMsg("Please enter reservation id");
			rerror.setErrorMsg("ManagerContoller");
			session.setAttribute("rerrormsg", rerror);
			getServletContext().getRequestDispatcher("/managerViewReservation.jsp").forward(request, response);
		} else {
			Reservation reserve = new Reservation();
			Reservation reserve1 = new Reservation();
			ReservationErrorMessages rerror = new ReservationErrorMessages();
			reserve.setReservationID(reservationID);
			reserve1.setReservationID(reservationID);
			ReservationDAO reservationDAO = new ReservationDAO();
			ReservationErrorMessages rerrormsg = new ReservationErrorMessages();
			String resList=null, resList1=null;
			
			CarDAO cardao =	new CarDAO();
			
			List<Reservation> reservationResult;
			try {
				reservationResult = reservationDAO.ReturnReservationBasedonID(reserve.getReservationID());
				reservationResult = reservationDAO.ReturnReservationBasedonID(reserve1.getReservationID());
				Car car = cardao.returnCarDetails(reservationResult.get(0).getCarID());
				reservationResult.get(0).setCarID(car.getCarType());
				if(reservationResult.get(0).getStatus().equals("Cancelled")) {
					resList = "<tr>\r\n" + 
							"						\r\n" + 
							"						<td><input type=\"text\"  name=\"srno\" readonly value=\"1\" ></td>\r\n" + 
							"						<td><input type=\"text\"  name=\"username\" readonly value=\""+reservationResult.get(0).getUsername()+"\" ></td>\r\n" + 
							"						<td><input type=\"text\" name=\"reservationID\" readonly value=\""+reservationResult.get(0).getReservationID()+"\" ></td>\r\n" + 
							"						<td><input type=\"text\" name=\"cname\" readonly value=\""+reservationResult.get(0).getCarID()+"\" ></td>\r\n" + 
							"						<td><input type=\"text\" name=\"tamt\" readonly value=\""+reservationResult.get(0).getTotalCost()+"\" ></td>\r\n" + "</tr>" ;
							resList1 =	"<tr>\r\n" + 
							"						<td><input type=\"text\" name=\"pd\" readonly value=\""+reservationResult.get(0).getStartDate()+"\" ></td>\r\n" + 
							"						<td><input type=\"text\" name=\"pt\" readonly value=\""+reservationResult.get(0).getPickUpTime()+"\" ></td>\r\n" + 
							"						<td><input type=\"text\" name=\"dd\" readonly value=\""+reservationResult.get(0).getEndDate()+"\" ></td>\r\n" + 
							"						<td><input type=\"text\" name=\"dt\" readonly value=\""+reservationResult.get(0).getDropTime()+"\" ></td>\r\n" +
							"						<td><input type=\"text\" name=\"dt\" readonly value=\"Cancelled\" ></td>\r\n" +
							"						</tr>";
				} else {
					resList = "<tr>\r\n" + 
							"						\r\n" + 
							"						<td><input type=\"text\" name=\"username\" readonly value=\""+reservationResult.get(0).getUsername()+"\" ></td>\r\n" + 
							"						<td><input type=\"text\" name=\"reservationID\" readonly value=\""+reservationResult.get(0).getReservationID()+"\" ></td>\r\n" + 
							"						<td><input type=\"text\" name=\"cname\" readonly value=\""+reservationResult.get(0).getCarID()+"\" ></td>\r\n" + 
							"						<td><input type=\"text\" name=\"tamt\" readonly value=\""+reservationResult.get(0).getTotalCost()+"\" ></td>\r\n" + "</tr>" ;
							resList1 =	"<tr>\r\n" + 
							"						<td><input type=\"text\" name=\"pd\" readonly value=\""+reservationResult.get(0).getStartDate()+"\" ></td>\r\n" +  
							"						<td><input type=\"text\" name=\"pt\" readonly value=\""+reservationResult.get(0).getPickUpTime()+"\" ></td>\r\n" + 
							"						<td><input type=\"text\" name=\"dd\" readonly value=\""+reservationResult.get(0).getEndDate()+"\" ></td>\r\n" + 
							"						<td><input type=\"text\" name=\"dt\" readonly value=\""+reservationResult.get(0).getDropTime()+"\" ></td>\r\n" +
							"						<td><input type=\"submit\" value=\"Cancel\"></td>\r\n" + 
							"						<input name=\"action\" value=\"deleteReservation\" type=\"hidden\">\r\n"+
							"						</tr>";		
				}
				session.setAttribute("reserve", resList);
				session.setAttribute("reserve1", resList1);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			session.removeAttribute("rerrormsg");
			getServletContext().getRequestDispatcher("/managerViewReservation.jsp").forward(request, response);
		}
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
			reservationDAO.cancelReservation(null,reservationID);
			session.setAttribute("status", "success");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e)
		{
			e.printStackTrace();
		}
		session.removeAttribute("rerrormsg");
		session.removeAttribute("reserve");
		getServletContext().getRequestDispatcher("/managerViewReservation.jsp").forward(request, response);
	}
	
	private void addCar(HttpServletRequest request, HttpSession session, HttpServletResponse response) throws ServletException, IOException
	{

		// TODO Auto-generated method stub
		
		User user=new User();
		Car car=new Car();
		car.setCarType(request.getParameter("carName"));
		car.setCapacity(request.getParameter("capacity"));

		car.setGpsPrice(request.getParameter("gps"));
		car.setOnstarPrice(request.getParameter("onstar"));
		car.setSiriusXMPrice(request.getParameter("sirius"));
		car.setWeekdayPrice(request.getParameter("weekday"));
		car.setWeekendPrice(request.getParameter("weekend"));
		car.setWeekPrice(request.getParameter("week"));
		
		UserDAO userReg=new UserDAO();
		
		CarDAO cardao=new CarDAO();
		
		CarErrorMessages errorMsgs = new CarErrorMessages();
		
		//user.setAge("18");
		car.validateCar("ManagerController", car, errorMsgs);
		session.setAttribute("addCarReturn", car);
		if (!errorMsgs.getErrorMsg().equals("")) {// if error messages
			
			session.setAttribute("errorMsgs", errorMsgs);
			getServletContext().getRequestDispatcher("/addCar.jsp").forward(request, response);
		}
		else {
			
			cardao.addCar(car);
			System.out.println("Adding car");
			session.removeAttribute("addCarReturn");
			session.removeAttribute("errorMsgs");
			getServletContext().getRequestDispatcher("/managerHome.jsp").forward(request, response);
		}
		
		doGet(request, response);
	
	}
	
	private void getBookedCars(HttpServletRequest request, HttpSession session, HttpServletResponse response) throws ServletException, IOException, ParseException
	{
		String startDate = request.getParameter("pickup");
		String endDate = request.getParameter("dropoff");
		String pickupTime = request.getParameter("time-pickup");
		int dropoffTime = 8;

		CarDAO carDAO = new CarDAO();
		CarErrorMessages cerrormsg = new CarErrorMessages();

		Car care = new Car();

		Reservation reserve = new Reservation();
		ReservationErrorMessages rerror = new ReservationErrorMessages();

		reserve.setStartDate(startDate);
		reserve.setEndDate(endDate);
		//reserve.setDropTime(dropoffTime);
		reserve.setPickUpTime(pickupTime);

		try {
			reserve.validateReservation(reserve, rerror, "managerReservation");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		if(!(rerror.getErrorMsg().equals(""))) {
			if(!(rerror.getErrorMsg().isEmpty())) {
				session.setAttribute("rerrormsg", rerror);

			}
			getServletContext().getRequestDispatcher("/managerHome.jsp").forward(request, response);
		} else {
			List<Reservation> reserve1 = carDAO.getBookedCars(startDate, endDate, dropoffTime);
			for(Reservation r: reserve1) {
				r.setCarID(carDAO.returnCarDetails(r.getCarID()).getCarType());
			}
			session.setAttribute("r", reserve1);
		}

		session.removeAttribute("cerrormsg");
		session.removeAttribute("rerrormsg");
		getServletContext().getRequestDispatcher("/managerHome.jsp").forward(request, response);
	}
}