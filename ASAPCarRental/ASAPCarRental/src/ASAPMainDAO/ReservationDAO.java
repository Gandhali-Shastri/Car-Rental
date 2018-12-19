package ASAPMainDAO;

import ASAPMainModel.User;
import ASAPMainModel.Reservation;
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

public class ReservationDAO{
	static SQLConnection DBMgr = SQLConnection.getInstance();

	private static int StoreLIstInDB(Reservation reservation, String queryString) throws SQLException {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		int id = 0;
		ResultSet idStored = null;
		System.out.println(conn);

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");

		Date sdate;
		try {
			sdate = simpleDateFormat.parse(reservation.getStartDate());

			Date edate = simpleDateFormat.parse(reservation.getEndDate());
			simpleDateFormat.applyPattern("yyyy-MM-dd");
			String startDate = simpleDateFormat.format(sdate);
			String endDate = simpleDateFormat.format(edate);
			try {
				stmt = conn.createStatement();
				String insertReservation = queryString + " VALUES ('"
						+ reservation.getUsername() + "','"
						+ reservation.getCarID() + "','"
						+ startDate + "','"
						+ endDate + "','"
						+ reservation.getPickUpTime() + "','"
						+ reservation.getDropTime() + "','"
						+ reservation.getTotalCost() + "','"
						+ reservation.getDiscount() + "','"
						+ reservation.getTax() + "','"
						+ reservation.isIfGPSChecked() + "','"
						+ reservation.isIfOnStarChecked() + "','"
						+ reservation.isIfSiriusXMChecked() + "','"
						+ "Booked')";
				System.out.println(insertReservation);
				id = stmt.executeUpdate(insertReservation, Statement.RETURN_GENERATED_KEYS);
				idStored = stmt.getGeneratedKeys();
				idStored.next();
				id = idStored.getInt(1);
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
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return id;

	}

	private static ArrayList<Reservation> ReturnReservation(String string){
		ArrayList<Reservation> reservationList = new ArrayList<Reservation>();

		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			ResultSet reservations = stmt.executeQuery(string);
			while (reservations.next()) {
				Reservation reservation = new Reservation();
				reservation.setReservationID(reservations.getString("reservation_id"));
				reservation.setUsername(reservations.getString("username"));
				reservation.setCarID(reservations.getString("car_id"));		
				reservation.setStartDate(reservations.getString("start_date"));
				reservation.setEndDate(reservations.getString("end_date"));
				reservation.setPickUpTime(reservations.getString("pickup_time"));
				reservation.setDropTime(reservations.getString("drop_time"));
				reservation.setTotalCost(reservations.getString("total_cost"));
				reservation.setTax(reservations.getString("tax"));
				reservation.setDiscount(reservations.getString("discount"));
				reservation.setStatus(reservations.getString("status"));
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


	public int registerReservation(Reservation reservation) throws SQLException {
		int id = StoreLIstInDB(reservation, "INSERT INTO reservation (username, car_id, start_date, end_date, pickup_time, drop_time, total_cost, discount, tax, GPS, SiriusXM, OnStar, status )");
		return id;
	}


	public ArrayList<Reservation> returnReservationForUser(String username) {
		ArrayList<Reservation> reservationList = new ArrayList<Reservation>();
		reservationList = ReturnReservation("SELECT * from reservation WHERE username = '"+username+"' ORDER BY start_date");
		if(reservationList.isEmpty()) {
			return null;
		}
		else {
			return reservationList;
		}
	}

	public Reservation returnSpecificReservation(String username, String reservationId) {
		ArrayList<Reservation> reservationList = new ArrayList<Reservation>();
		reservationList = ReturnReservation("SELECT * from reservation WHERE username = '"+username+"' AND reservation_id = '"+reservationId+"'");
		if(reservationList.isEmpty()) {
			return null;
		}
		else {
			return reservationList.get(0);
		}
	}

	public void cancelReservation(String username, String reservationID) throws SQLException {

		Reservation reservation = new Reservation();
		reservation = returnSpecificReservation(username, reservationID);
		Statement stmt = null;
		String sql = null;
		Connection conn = SQLConnection.getDBConnection();
		stmt = conn.createStatement();
		if(username==null) {
			sql = "UPDATE reservation " +
					"SET status = 'Cancelled' WHERE reservation_id = '"+reservationID+"'";
		} else {
			sql = "UPDATE reservation " +
					"SET status = 'Cancelled' WHERE username = '"+username+"' AND reservation_id = '"+reservationID+"'";
		}

		int id=stmt.executeUpdate(sql);
		conn.commit();
		System.out.println(id);

	}

	public ArrayList<Reservation> ReturnReservationBasedonID(String reservationID)
	{
		ArrayList<Reservation> reservationList = new ArrayList<Reservation>();
		reservationList=ReturnReservation("select * from reservation where reservation_id = " + reservationID);
		return reservationList;
	}	
}