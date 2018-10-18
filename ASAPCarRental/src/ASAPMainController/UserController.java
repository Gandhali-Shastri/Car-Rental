package ASAPMainController;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import ASAPMainModel.Payment;
import ASAPMainModel.PaymentErrorMessages;
import ASAPMainModel.Reservation;
import ASAPMainModel.ReservationErrorMessages;
import ASAPMainModel.User;
import ASAPMainModel.UserErrorMessages;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserController() {
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
		String action = request.getParameter("action");

		if(action.equals("updateUser")) {
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
			//user.setlicExp(request.getParameter("licExp"));
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String linExp = request.getParameter("licExp");
			try {
				Date date = simpleDateFormat.parse(linExp);
				simpleDateFormat.applyPattern("yyyy-MM-dd");
				linExp = simpleDateFormat.format(date);
				user.setlicExp(linExp);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			user.setUserRole(request.getParameter("userType"));
			user.setAge(request.getParameter("age"));

			//AAC
			user.setAac(Boolean.parseBoolean(request.getParameter("aac")));

			//Gender
			user.setGender(request.getParameter("gender"));

			user.setUserRole(request.getParameter("userType"));
			UserDAO userReg=new UserDAO();
			UserErrorMessages errorMsgs = new UserErrorMessages();

			user.validateUser("UpdateController", user, errorMsgs);

			if (!errorMsgs.getErrorMsg().equals("")) {// if error messages

				session.setAttribute("uerrorMsgs", errorMsgs);
				getServletContext().getRequestDispatcher("/userProfile.jsp").forward(request, response);
			}
			else {// if no error messages
				try {
					userReg.updateUser(user);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				session.removeAttribute("errorMsgs");
				getServletContext().getRequestDispatcher("/userProfile.jsp").forward(request, response);
				session.setAttribute("user", user);

			}

		}

		if(action.equals("carSearch")) {

			String capacity = request.getParameter("traveller");
			String startDate = request.getParameter("pickup");
			String endDate = request.getParameter("dropoff");
			String pickupTime = request.getParameter("time-pickup");
			String dropoffTime = request.getParameter("time-dropoff");

			CarDAO carDAO = new CarDAO();
			CarErrorMessages cerrormsg = new CarErrorMessages();

			Car care = new Car();
			care.setCapacity(capacity);
			care.validate_capacity(care, cerrormsg);

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
				getServletContext().getRequestDispatcher("/userHome.jsp").forward(request, response);
			} else {
				List<Car> carResult;
				try {
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
				getServletContext().getRequestDispatcher("/userHome.jsp").forward(request, response);

			}

		}

		if(action.equals("makeresrvation")) {
			String carType = request.getParameter("cname");
			String capacity = request.getParameter("cap");
			String gps = request.getParameter("gps");
			String onstar = request.getParameter("onstar");
			String sirius = request.getParameter("sirius");
			String startdate = (String) session.getAttribute("startdate");
			String enddate = (String) session.getAttribute("enddate");
			String pickup = (String) session.getAttribute("pickup");
			String dropoff = (String) session.getAttribute("dropoff");

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");


			CarDAO cardao = new CarDAO();

			Car car = cardao.returnCarDetails(carType);
			try{

				Date sdate = simpleDateFormat.parse(startdate);
				Date edate = simpleDateFormat.parse(enddate);

				Calendar startCal = Calendar.getInstance();
				startCal.setTime(sdate);        

				Calendar endCal = Calendar.getInstance();
				endCal.setTime(edate);

				int weekDays = 0;
				int weekends = 0;
				int days = 0;
				int week=0;

				startCal.setTime(edate);
				endCal.setTime(sdate);

				do {
					//excluding start date

					startCal.add(Calendar.DAY_OF_MONTH, 1);
					days++;
					if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
						weekDays++;
					}else {
						weekends++;
					}
					if(days==7) {
						week++;
						weekDays=0;
						weekends=0;
					}
				} while (startCal.getTimeInMillis() <= endCal.getTimeInMillis());

				float totalAmount = (week * Float.parseFloat(car.getWeekPrice())) + (weekDays * Float.parseFloat(car.getWeekdayPrice())) + (weekends * Float.parseFloat(car.getWeekendPrice()));
				if(gps.equals("on")) {
					totalAmount = totalAmount + Float.parseFloat(car.getGpsPrice());
				}
				if(onstar.equals("on")) {
					totalAmount = totalAmount + Float.parseFloat(car.getOnstarPrice());
				}
				if(sirius.equals("on")) {
					totalAmount = totalAmount + Float.parseFloat(car.getSiriusXMPrice());
				}

				User curUser = (User) session.getAttribute("user");
				System.out.println("Total Amount:"+totalAmount);

				float discount = 0;
				if(curUser.isAac()) {
					discount = (float) (0.1 * totalAmount); 
				}
				float tax = 0;
				tax = (float) (0.0825 * totalAmount);
				int deposit = 0;
				if(Integer.parseInt(curUser.getAge())<25) {
					deposit = 250;
				}

				totalAmount = totalAmount + tax + deposit - discount;

				Reservation reservation = new Reservation();

				reservation.setUsername(curUser.getUserName());
				reservation.setCarID(car.getCarId());
				reservation.setStartDate(startdate);
				reservation.setEndDate(enddate);
				reservation.setPickUpTime(pickup);
				reservation.setDropTime(dropoff);
				reservation.setTotalCost(String.valueOf(totalAmount));
				reservation.setTax(String.valueOf(tax));
				reservation.setDiscount(String.valueOf(discount));
				if(gps.equals("on")) {
					reservation.setIfGPSChecked(true);
				} else {
					reservation.setIfGPSChecked(false);
				}
				if(onstar.equals("on")) {
					reservation.setIfOnStarChecked(true);
				} else {
					reservation.setIfOnStarChecked(true);
				}
				if(sirius.equals("on")) {
					reservation.setIfSiriusXMChecked(true);
				} else {
					reservation.setIfSiriusXMChecked(false);
				}
				//session.removeAttribute("startdate");
				//				session.removeAttribute("enddate");
				//				session.removeAttribute("pickup");
				//				session.removeAttribute("dropoff");
				session.setAttribute("reservation", reservation);
				getServletContext().getRequestDispatcher("/paymentPage.jsp").forward(request, response);

			}catch(Exception e)
			{
				e.printStackTrace();
			}


		}
		if(action.equals("confirmPay")) {
			Reservation reservation = (Reservation) session.getAttribute("reservation");
			User curUser = (User) session.getAttribute("user");
			String cardType = request.getParameter("cardType");
			String cardNum = request.getParameter("cardnum");
			String cardName = request.getParameter("cardname");
			String cvv = request.getParameter("cvv");

			Payment payment = new Payment();
			payment.setCreditCardNumber(cardNum);
			payment.setCVV(cvv);
			payment.setTotalCost(reservation.getTotalCost());
			payment.setUsername(curUser.getUserName());
			payment.setNameOnCard(cardName);

			ReservationDAO resDao = new ReservationDAO();
			PaymentErrorMessages errorMsg = new PaymentErrorMessages();
			payment.validate_payment("PaymentAction", payment, errorMsg, cardType);
			if(!errorMsg.getErrorMsg().equals("")) {
				session.setAttribute("perrormsg", errorMsg);
				getServletContext().getRequestDispatcher("/paymentPage.jsp").forward(request, response);
			} else {
				String Id = resDao.registerReservation(reservation);
				//payment.setReservationID(Id);

				session.removeAttribute("reservation");
				session.removeAttribute("perrormsg");
				getServletContext().getRequestDispatcher("/userHome.jsp").forward(request, response);

			}

		}
		doGet(request, response);
	}

}
