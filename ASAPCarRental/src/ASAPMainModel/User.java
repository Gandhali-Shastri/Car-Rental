package ASAPMainModel;

import java.util.Date;

import com.mysql.cj.util.StringUtils;

import ASAPMainDAO.UserDAO;

public class User {

	private String utaID="";
	private String firstName="";
	private String lastName="";
	private String gender="";
	private String email="";
	private String phone="";
	private String address="";
	private String state = "";
	private String zipcode = "";
	private String status = "";
	private String age = "";

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	private boolean aac=false;
	private String LicNo="";
	private String licExp;
	private String userRole="";

	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public boolean isAac() {
		return aac;
	}
	public void setAac(boolean aac) {
		this.aac = aac;
	}
	private String userName="";
	private String pword="";

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPword() {
		return pword;
	}
	public void setPword(String pword) {
		this.pword = pword;
	}
	public String getUtaID() {
		return utaID;
	}
	public void setUtaID(String utaID) {
		this.utaID = utaID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getlastName() {
		return lastName;
	}
	public void setlastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public String getlicNo() {
		return LicNo;
	}
	public void setlicNo(String LicNo) {
		this.LicNo = LicNo;
	}
	public String getlicExp() {
		return licExp;
	}
	public void setlicExp(String date) {
		this.licExp = date;
	}

	public void validateUser (String action, User user, UserErrorMessages errorMsgs) {
		if (action.equals("RegistrationController")) {
			errorMsgs.setUtaIDMsg(validateUTAID(user.getUtaID()));
			errorMsgs.setUserNameMsg(validateUsername(user.getUserName(), action));
			errorMsgs.setFirstNameMsg(validateFirstName(user.getFirstName()));
			errorMsgs.setLastNameMsg(validateLastName(user.getlastName()));

			//errorMsgs.setstateError(validateState(user.getState()));
			errorMsgs.setZipcodeMsg(validateZipcode(user.getZipcode(), user.getUserRole()));
			errorMsgs.setPwordMsg(validatePassword(user.getPword()));
			//errorMsgs.setgenderError(validateGender(user.getGender()));
			errorMsgs.setPhoneMsg(validatePhone(user.getPhone(), user.getUserRole()));
			errorMsgs.setEmailMsg(validateEmail(user.getEmail()));
			errorMsgs.setAgeMsg(validateAge(user.getAge()));
			errorMsgs.setErrorMsg(action);
		} else if (action.equals("LoginController")) {
			errorMsgs.setUserNameMsg(validateUsername(user.getUserName(), action));
		}
	}

	private String validateUTAID(String utaID) {
		String result="";
		if (utaID.equals(""))
			result="UTA ID cannot be blank.";
		else if(!(utaID.matches("[0-9]+")))
			result="Your UTA ID must be a number";
		else
			if (utaID.length()!=10) {
				result= "Your UTA Id must be 10 digits long.";
			}
		return result;
	}

	private String validateUsername( String username, String action) {
		String result="";

		if (StringUtils.isNullOrEmpty(username)) {
			result="Username cannot be blank.";
		} else
			if ((username.length()<=4 || username.length()>25) && action.equals("RegistrationController")) 
				result= "Your username must be atleast 5 in length.";
			else
				if ((!(UserDAO.isUserNameUnique(username)) && action.equals("RegistrationController")))
					result="Username exists";
		return result;
	}

	private String validateFirstName(String firstName) {
		String result="";
		if (firstName.equals(""))
			result="First Name cannot be blank.";
		else
			if (firstName.length()<=2 || firstName.length()>25)
				result= "Your First Name must be atleast 2 in length and atmost 25.";
		return result;
	}

	private String validateLastName(String lastName) {
		String result="";
		if (lastName.equals(""))
			result="Last Name cannot be blank.";
		else
			if (lastName.length()<=2 || lastName.length()>25)
				result= "Your First Name must be atleast 2 in length and atmost 25.";
		return result;
	}

	private String validatePhone(String phone, String userRole) {
		String result="";
		if(userRole.equals("User")) {
			if (phone.length()!=10  && userRole.equals("User"))
				result="Phone number must be 10 digits in length";
			else
				if (!(phone.matches("[0-9]+")  && userRole.equals("User")))
					result="Phone number must be a number";
		}

		return result;		
	}

	private String validateEmail(String email) {
		String result="",extension="";
		if (!email.contains("@"))
			result = "Email address needs to contain @";
		else
			if (email.length()<7 || email.length()>45)
				result="Email address must be between 7 and 45 characters long";
			else {
				extension = email.substring(email.length()-4, email.length());
				if (!extension.equals(".org") && !extension.equals(".edu") && !extension.equals(".com") 
						&& !extension.equals(".net") && !extension.equals(".gov") && !extension.equals(".mil"))
					result = "Invalid domain name";				
			}
		return result;		
	}

	private String validatePassword(String password) {

		boolean valid = true;
		String result="";
		String upperCaseChars = "(.*[A-Z].*)";
		String lowerCaseChars = "(.*[a-z].*)";
		String numbers = "(.*[0-9].*)";
		String specialChars = "(.*[,~,!,@,#,$,%,^,&,*,(,),-,_,=,+,[,{,],},|,;,:,<,>,/,?].*$)";

		if (password.length() > 15 || password.length() < 8)
		{
			result="Password should be less than 15 and more than 8 characters in length.";
			valid = false;
		}
		else if (password.indexOf(userName) > -1)
		{
			result="Password Should not be same as user name";
			valid = false;
		}

		else if (!password.matches(upperCaseChars ))
		{
			result="Password should contain atleast one upper case alphabet";
			valid = false;
		}

		else if (!password.matches(lowerCaseChars ))
		{
			result="Password should contain atleast one lower case alphabet";
			valid = false;
		}

		else if (!password.matches(numbers ))
		{
			result="Password should contain atleast one number.";
			valid = false;
		}

		else if (!password.matches(specialChars ))
		{
			result="Password should contain atleast one special character";
			valid = false;
		}
		return result;
	}

	private String validateZipcode(String zipcode, String userRole) {
		String result = "";
		if(userRole.equals("User")) {
			if (zipcode.length() != 5 && userRole.equals("User")){
				result="Zipcode should be exactly 5 in length.";
			}
			else
				if(!(zipcode.matches("[0-9]+") && userRole.equals("User")))
					result="Zipcode must be a number";
		}

		return result;
	}

	private String validateAge(String age) {
		String result = "";
		if(userRole.equals("User")) {
			if (age.equals(""))
				result="Age cannot be blank.";
			else if(Integer.parseInt(age) < 18) {
				result = "Age cannot be less than 18";
			}
		}
		return result;
	}
}
