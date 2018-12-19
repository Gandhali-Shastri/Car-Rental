package ASAPMainDAO;

import ASAPMainUtil.SQLConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.cj.util.StringUtils;

import ASAPMainModel.Car;
import ASAPMainModel.Reservation;
import ASAPMainModel.User;

public class CarDAO {
	static SQLConnection DBMgr = SQLConnection.getInstance();

	public List<Car> getAvailableCars(String capacity, String startDate, String endDate, int dropTime) throws ParseException {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();

		List<Car> cars = new ArrayList<>();

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");

		Date sdate = simpleDateFormat.parse(startDate);
		Date edate = simpleDateFormat.parse(endDate);
		simpleDateFormat.applyPattern("yyyy-MM-dd");
		startDate = simpleDateFormat.format(sdate);
		endDate = simpleDateFormat.format(edate);

		try {
			stmt = conn.createStatement();
			String searchCars = "SELECT car_id,drop_time \r\n" + 
					"    FROM   reservation R\r\n" + 
					"WHERE  ((start_date <= '" + startDate + "' AND end_date >= '" + startDate + "') " + 
					"OR (start_date < '" + endDate + "' AND end_date >= '" + endDate + "' )" + 
					"OR ('" + startDate + "' <= start_date AND '" + endDate + "' >= start_date)) And status = 'Booked'";
			ResultSet result = stmt.executeQuery(searchCars);

			List<String> carid = new ArrayList<>();

			while (result.next()) {
				if(dropTime <= Integer.parseInt(result.getString("drop_time"))) {
					carid.add(result.getString("car_id"));
				}
			}
			String idList = toSqlArray(carid);


			stmt = conn.createStatement();
			if(idList.isEmpty()) {
				String getCars = "Select * from cars";
				ResultSet rs = stmt.executeQuery(getCars);

				while(rs.next()) {
					Car car = new Car();
					car.setCarId(rs.getString("car_id"));
					car.setCapacity(rs.getString("Capacity"));
					car.setCarType(rs.getString("CarType"));
					car.setWeekdayPrice(rs.getString("weekday"));
					car.setWeekendPrice(rs.getString("weekend"));
					car.setWeekPrice(rs.getString("week"));
					car.setGpsPrice(rs.getString("gps"));
					car.setOnstarPrice(rs.getString("onstar"));
					car.setSiriusXMPrice(rs.getString("siriusxm"));

					cars.add(car);
				}

			} else {
				String getCars = "Select * from cars where car_id not in (" + idList + ")";
				ResultSet rs = stmt.executeQuery(getCars);

				while(rs.next()) {
					Car car = new Car();
					car.setCarId(rs.getString("car_id"));
					car.setCapacity(rs.getString("Capacity"));
					car.setCarType(rs.getString("CarType"));
					car.setWeekdayPrice(rs.getString("weekday"));
					car.setWeekendPrice(rs.getString("weekend"));
					car.setWeekPrice(rs.getString("week"));
					car.setGpsPrice(rs.getString("gps"));
					car.setOnstarPrice(rs.getString("onstar"));
					car.setSiriusXMPrice(rs.getString("siriusxm"));

					cars.add(car);
				}

			}


		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			};

		}
		return cars;
	}

	public List<Reservation> getBookedCars(String startDate, String endDate, int dropTime) throws ParseException {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();

		List<Car> cars = new ArrayList<>();

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");

		Date sdate = simpleDateFormat.parse(startDate);
		Date edate = simpleDateFormat.parse(endDate);
		simpleDateFormat.applyPattern("yyyy-MM-dd");
		startDate = simpleDateFormat.format(sdate);
		endDate = simpleDateFormat.format(edate);

		ArrayList<Reservation> reservationList = new ArrayList<Reservation>();
		
		try {
			stmt = conn.createStatement();
			String searchCars = "SELECT * \r\n" + 
					"    FROM   reservation R\r\n" + 
					"WHERE  ((start_date <= '" + startDate + "' AND end_date >= '" + startDate + "') " + 
					"OR (start_date < '" + endDate + "' AND end_date >= '" + endDate + "' )" + 
					"OR ('" + startDate + "' <= start_date AND '" + endDate + "' >= start_date)) AND status = 'Booked'";
			ResultSet result = stmt.executeQuery(searchCars);

			

			while (result.next()) {
				Reservation reservation = new Reservation();
				reservation.setReservationID(result.getString("reservation_id"));
				reservation.setUsername(result.getString("username"));
				reservation.setCarID(result.getString("car_id"));		
				reservation.setStartDate(result.getString("start_date"));
				reservation.setEndDate(result.getString("end_date"));
				reservation.setPickUpTime(result.getString("pickup_time"));
				reservation.setDropTime(result.getString("drop_time"));
				reservation.setTotalCost(result.getString("total_cost"));
				reservation.setTax(result.getString("tax"));
				reservation.setDiscount(result.getString("discount"));
				reservation.setStatus(result.getString("status"));
				reservation.setIfGPSChecked(true);
				reservation.setIfSiriusXMChecked(true);
				reservation.setIfOnStarChecked(true);
				reservationList.add(reservation);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			};

		}
		return reservationList;
	}

	public static String toSqlArray(List<String> strings) {
		StringBuilder sb = new StringBuilder();
		boolean doneOne = false;
		for(String str: strings){
			if(doneOne){
				sb.append(", ");
			}
			sb.append("'").append(str).append("'");
			doneOne = true;
		}
		return sb.toString();
	}

	private static ArrayList<Car> ReturnCar(String string) {
		ArrayList<Car> carlist = new ArrayList<Car>();

		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			ResultSet cars = stmt.executeQuery(string);
			while (cars.next()) {
				Car car = new Car(); 
				car.setCarId(cars.getString("car_id"));
				car.setCarType(cars.getString("CarType"));
				car.setCapacity(cars.getString("Capacity"));
				car.setWeekdayPrice(cars.getString("weekday"));
				car.setWeekendPrice(cars.getString("weekend"));
				car.setWeekPrice(cars.getString("week"));
				car.setGpsPrice(cars.getString("gps"));
				car.setOnstarPrice(cars.getString("onstar"));
				car.setSiriusXMPrice(cars.getString("siriusxm"));
				carlist.add(car);	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			};
		}
		return carlist;
	}

	public void addCar(Car car) {
		StoreLIstInDB(car, "INSERT INTO cars (carType, Capacity, weekday, weekend, week, gps, onstar, siriusxm )");
	}
	
	public static Boolean isCarTypeUnique(String cartype)  {  
		return (ReturnCar(" SELECT * from cars WHERE carType = '"+cartype+"' ORDER BY carType").isEmpty());
	}

	private static void StoreLIstInDB(Car car, String queryString) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		System.out.println(conn);
		try {
			stmt = conn.createStatement();
			String insertCar = queryString + " VALUES ('"
					+ car.getCarType() + "','"
					+ car.getCapacity() + "','"
					+ car.getWeekdayPrice() + "','"
					+ car.getWeekendPrice() + "','"
					+ car.getWeekPrice() + "','"
					+ car.getGpsPrice() + "','"
					+ car.getOnstarPrice() + "','"
					+ car.getSiriusXMPrice() + "')";
			System.out.println(insertCar);
			stmt.executeUpdate(insertCar);
			conn.commit();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}};
	}

	public Car returnCarDetails(String carID) {

		ArrayList<Car> carlist = new ArrayList<Car>();
		if(carID.matches("[0-9]+")) {
			carlist = ReturnCar(" SELECT * from cars WHERE car_id = '"+carID+"' ");
		} else {
			carlist = ReturnCar(" SELECT * from cars WHERE CarType = '"+carID+"' ");
		}

		if(carlist.isEmpty()) {
			return null;
		} else {
			return carlist.get(0);
		}

	}
}
