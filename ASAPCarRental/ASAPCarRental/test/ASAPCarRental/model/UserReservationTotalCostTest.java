package ASAPCarRental.model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;


import ASAPMainController.UserController;
import ASAPMainDAO.CarDAO;
import ASAPMainModel.Car;
import ASAPMainModel.Reservation;
import ASAPMainModel.User;



import java.io.IOException;
import java.text.ParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@RunWith(JUnitParamsRunner.class)
public class UserReservationTotalCostTest {
	
	Reservation reservation;
	
	@Before
	public void setUp() throws Exception {
		reservation = new Reservation();
	}
	
	//	CompanyDAO mockobj;

	@Test
	@FileParameters("test/ReservationTotal_test_cases.csv")
	public void test(	int testcaseNo, String action, String carType, String capacity,boolean gps, boolean onstar, boolean sirius, String startdate,    
						String enddate, String pickup,    String dropoff, String age, Boolean isAac, String totalCost, String discount, String tax) throws ServletException, IOException, ParseException {
		User user = new User();
		
		
		user.setAac(isAac);
		user.setAge(age);
		
		reservation.setStartDate(startdate);
		reservation.setEndDate(enddate);
		reservation.setPickUpTime(pickup);
		reservation.setDropTime(dropoff);
		
		reservation.setIfGPSChecked(gps);
		reservation.setIfOnStarChecked(onstar);
		reservation.setIfSiriusXMChecked(sirius);
				
		CarDAO cardao = new CarDAO();

		Car car = cardao.returnCarDetails(carType);
		
		reservation.setTotalCost(user, car, reservation);

		
		assertEquals(totalCost, reservation.getTotalCost());
		assertEquals(discount, reservation.getDiscount());
		assertEquals(tax, reservation.getTax());
		
	}
}