package ASAPCarRental.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import ASAPMainModel.*;

@RunWith(JUnitParamsRunner.class)

public class UserTest {
	User usr;
	UserErrorMessages UerrMsgs;
	
	@Before
	public void setUp() throws Exception {
		usr= new User();
		UerrMsgs = new UserErrorMessages();
	}	
	@Test
	
	@FileParameters("test/User_test_cases.csv")
	public void test(int testcaseNo, String action, String utaID, String userName, String pword, 
					String firstName,String lastName, String gender, String email, String phone, 
					String address, String state, String zipcode, String aac, String LicNo, 
					String licExp, String userRole, String status, String age,String errorMsg, 
					String utaIDMsg, String userNameMsg, String pwordMsg, String firstNameMsg, 
					String lastNameMsg, String genderMsg, String emailMsg, String phoneMsg, 
					String stateMsg,String zipcodeMsg, String ageMsg, String addressMsg, String LicNoMsg , String LicExpMsg) {

		usr.setUtaID(utaID);
		usr.setUserName(userName);
		usr.setPword(pword);
		usr.setFirstName(firstName);
		usr.setlastName(lastName);
		usr.setGender(gender);
		usr.setEmail(email);
		usr.setPhone(phone);
		usr.setAddress(address);
		usr.setState(state);
		usr.setZipcode(zipcode);
		if(aac.equals("TRUE"))
		usr.setAac(true);
		if(aac.equals("FALSE"))
		usr.setAac(false);
		usr.setlicNo(LicNo);
		usr.setlicExp(licExp);
		usr.setUserRole(userRole);
		usr.setStatus(status);
		usr.setAge(age);
		usr.setlicExp(licExp);
		
		usr.validateUser(action, usr, UerrMsgs);

		//usr.validateUser(usr, UerrMsgs);
		//System.out.println(UerrMsgs.getUtaIDMsg());
		//System.out.println("Expected: " + utaIDMsg);
		
		assertTrue(errorMsg.equals(UerrMsgs.getErrorMsg()));
		if(!utaIDMsg.isEmpty())
		assertTrue(utaIDMsg.equals(UerrMsgs.getUtaIDMsg()));
		if(!userNameMsg.isEmpty())
		assertTrue(userNameMsg.equals(UerrMsgs.getUserNameMsg()));
		if(!pwordMsg.isEmpty())
		{
			assertTrue(pwordMsg.equals(UerrMsgs.getPwordMsg()));

		}
		if(!firstNameMsg.isEmpty())
			assertTrue(firstNameMsg.equals(UerrMsgs.getFirstNameMsg()));
		if(!lastNameMsg.isEmpty())
			assertTrue(lastNameMsg.equals(UerrMsgs.getLastNameMsg()));
		if(!genderMsg.isEmpty())
			assertTrue(genderMsg.equals(UerrMsgs.getGenderMsg()));
		if(!emailMsg.isEmpty())
			assertTrue(emailMsg.equals(UerrMsgs.getEmailMsg()));
		if(!phoneMsg.isEmpty())
			assertTrue(phoneMsg.equals(UerrMsgs.getPhoneMsg()));
		if(!stateMsg.isEmpty())
			assertTrue(stateMsg.equals(UerrMsgs.getStateMsg()));
		if(!zipcodeMsg.isEmpty())
			assertTrue(zipcodeMsg.equals(UerrMsgs.getZipcodeMsg()));
		if(!ageMsg.isEmpty())
			assertTrue(ageMsg.equals(UerrMsgs.getAgeMsg()));
		if(!addressMsg.isEmpty())
			assertTrue(addressMsg.equals(UerrMsgs.getAddressMsg()));
		if(!LicNoMsg.isEmpty())
			assertTrue(LicNoMsg.equals(UerrMsgs.getLicNoMsg()));
		if(!LicExpMsg.isEmpty())
			assertTrue(LicExpMsg.equals(UerrMsgs.getLicExpMsg()));

	}

}
