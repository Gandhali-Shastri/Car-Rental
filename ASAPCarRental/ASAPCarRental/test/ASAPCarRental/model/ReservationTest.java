
package ASAPCarRental.model;

import static org.junit.Assert.assertTrue;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import ASAPMainModel.Reservation;
import ASAPMainModel.ReservationErrorMessages;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class ReservationTest {
	
	Reservation resv;
	ReservationErrorMessages R_errMsgs;
	
	@Before
	public void setUp() throws Exception {
		resv = new Reservation();
		R_errMsgs = new ReservationErrorMessages();
	}	

	@Test
	@FileParameters("test/R1.csv")
	public void test(int testcaseNo, String Action, String startDate, String endDate,
						String pickUpTime, String dropTime, String errorMsg, String validateStartDateErrorMsg,
						String validateEndDateErrorMsg,
						String validatePickUpTimeErrorMsg, String validateDropTimeErrorMsg) throws ParseException {
		
		resv.setStartDate(startDate);
		resv.setEndDate(endDate);
		resv.setPickUpTime(pickUpTime);
		resv.setDropTime(dropTime);
		
		resv.validateReservation(resv,R_errMsgs , Action);
		
		assertTrue(errorMsg.equals(R_errMsgs.getErrorMsg()));
		assertTrue(validateStartDateErrorMsg.equals(R_errMsgs.getStartDateMsg()));		
		assertTrue(validateEndDateErrorMsg.equals(R_errMsgs.getEndDateMsg()));
		assertTrue(validatePickUpTimeErrorMsg.equals(R_errMsgs.getPickUpTimeMsg()));
		assertTrue(validateDropTimeErrorMsg.equals(R_errMsgs.getDropTimeMsg()));

		
		
	}

}
