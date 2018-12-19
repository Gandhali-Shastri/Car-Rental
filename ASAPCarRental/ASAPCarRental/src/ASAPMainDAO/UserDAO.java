package ASAPMainDAO;

import ASAPMainModel.User;
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

public class UserDAO {
	static SQLConnection DBMgr = SQLConnection.getInstance();


	private static void StoreListinDB (User user,String queryString) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  

		System.out.println(conn);
		try {
			stmt = conn.createStatement();
			if(user.getUserRole().equals("user")) {
				String insertCompany = queryString + " VALUES ('"  
						+ user.getUserRole()  + "','"
						+ user.getUserName() + "','"		
						+ user.getUtaID() + "','"
						+ user.getFirstName() + "','"
						+ user.getlastName() + "','"
						+ user.getGender() + "','"
						+ user.getEmail() + "','"
						+ user.getPword() + "','"
						+ user.getPhone() + "','"
						+ user.getAddress() + "','"
						+ user.getState() + "','"
						+ user.getZipcode() + "','"
						+ user.getlicNo() + "','"
						+ user.getlicExp() + "','"
						+ user.isAac() + "','"
						+ user.getStatus() + "','"
						+ user.getAge() + "')";
				System.out.println(insertCompany);
				stmt.executeUpdate(insertCompany);
				conn.commit(); 
			} else {
				String insertCompany = queryString + " VALUES ('"  
						+ user.getUserRole()  + "','"
						+ user.getUserName() + "','"		
						+ user.getUtaID() + "','"
						+ user.getFirstName() + "','"
						+ user.getlastName() + "','"
						+ user.getGender() + "','"
						+ user.getEmail() + "','"
						+ user.getPword() + "','"
						+ user.getPhone() + "','"
						+ user.getAddress() + "','"
						+ user.getState() + "','"
						+ user.getZipcode() + "','"
						+ user.getlicNo() + "',null"
						+ ",'"
						+ user.isAac() + "','"
						+ user.getStatus() + "','"
						+ user.getAge() + "')";
				System.out.println(insertCompany);
				stmt.executeUpdate(insertCompany);
				conn.commit(); 
			}

		} catch (SQLException e) {
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


	private static ArrayList<User> ReturnUser(String string) {
		ArrayList<User> userList = new ArrayList<User>();

		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			ResultSet users = stmt.executeQuery(string);
			while (users.next()) {
				User user = new User(); 
				user.setUserRole(users.getString("user_role"));
				user.setUserName(users.getString("username"));		
				user.setUtaID(users.getString("UTA_id"));
				user.setFirstName(users.getString("first_name"));
				user.setlastName(users.getString("last_name"));
				user.setGender(users.getString("gender"));
				user.setEmail(users.getString("email"));
				user.setPword(users.getString("password"));
				user.setPhone(users.getString("phone"));
				user.setAddress(users.getString("address"));
				user.setState(users.getString("state"));
				user.setZipcode(users.getString("zipcode"));
				user.setlicNo(users.getString("license_num"));
				String exp = users.getString("expiry");
				//				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm/dd/yyyy");
				//				exp = simpleDateFormat.format(new Date());
				user.setlicExp(exp);

				if(users.getString("AAC").equals("true")) {
					user.setAac(true);
				} else {
					user.setAac(false);
				}
				user.setStatus(users.getString("status"));
				user.setAge(users.getString("age"));

				userList.add(user);	
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
		return userList;
	}

	public static Boolean isUserNameUnique(String username)  {  
		return (ReturnUser(" SELECT * from user WHERE username = '"+username+"' ORDER BY username").isEmpty());
	}

	public User returnUserDetails(String username) {
		ArrayList<User> userList = new ArrayList<User>();
		userList = ReturnUser(" SELECT * from user WHERE username = '"+username+"' ORDER BY username");
		if(userList.isEmpty()) {
			return null;
		} else {
			return userList.get(0);
		}

	}

	public void registerUser(User user) {
		StoreListinDB(user,"INSERT INTO user (user_role,username,UTA_id,first_name,last_name,gender,email,password,phone,address,state,zipcode,license_num,expiry,AAC,status,age) ");	
	}


	public void updateUser(User user) throws SQLException {

		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		stmt = conn.createStatement();
		String sql = "UPDATE user " +
				"SET user_role = '" +user.getUserRole()+ "', UTA_id = '" + user.getUtaID() + "' ,first_name = '" + user.getFirstName() + "' ,last_name = '" + user.getlastName() + "' ,gender = '" + user.getGender() + "' ,email = '" + user.getEmail() + "' ,password = '" + user.getPword() + "' ,phone = '" + user.getPhone() + "', address = '" + user.getAddress() + "',state = '" + user.getState() + "' ,zipcode = '" + user.getZipcode() +"',license_num= '"+ user.getlicNo() + "',expiry = '" + user.getlicExp() + "',AAC='"+ user.isAac() + "' ,status = '" + user.getStatus()+"',age = '"+ user.getAge()+"' where username = '"+user.getUserName()+"'";
		stmt.executeUpdate(sql);
		conn.commit();

	}

	public void revokeUser(String username) throws SQLException {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		stmt = conn.createStatement();
		String sql = "UPDATE crms.user SET status = 'inactive' WHERE username = '" + username + "'";
		stmt.executeUpdate(sql);
		conn.commit();
		}
	
	public ArrayList<User> returnAllUsers() {
		ArrayList<User> userList = new ArrayList<User>();
		userList = ReturnUser(" SELECT * from user");
		return userList;
	}
}


