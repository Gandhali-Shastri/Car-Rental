package ASAPCarRental.model;

import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import ASAPMainModel.Car;
import ASAPMainModel.CarErrorMessages;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class CarTest {
	Car car;
	CarErrorMessages errorMsg;
	
	@Before
	public void setUp() throws Exception {
		car = new Car();
		errorMsg = new CarErrorMessages();
	}
	
	
	@Test
	@FileParameters("test/Car_test_cases - Sheet1.csv")
	public void test(	int testcaseNo, String action, String cartype, String capacity, String weekdayprice, String weekendprice, String weekprice,    
						String gpsprice,    String onStarPrice,    String siriusXMPrice, String errorMsgs,    String cartypeErr, String capacityErr, String weekdayError,
						String weekendError, String weekError, String gpsError, String onStarError, String siriusXMError) {
		
		car.setCapacity(capacity);
		car.setCarType(cartype);
		car.setWeekdayPrice(weekdayprice);
		car.setWeekendPrice(weekendprice);
		car.setWeekPrice(weekprice);
		car.setGpsPrice(gpsprice);
		car.setOnstarPrice(onStarPrice);
		car.setSiriusXMPrice(siriusXMPrice);
		car.validateCar(action, car, errorMsg);
		assertTrue(errorMsgs.equals(errorMsg.getErrorMsg()));
		assertTrue(cartypeErr.equals(errorMsg.getCarTypeMsg()));
		assertTrue(capacityErr.equals(errorMsg.getCapacityMsg()));
		assertTrue(weekdayError.equals(errorMsg.getWeekdayPriceMsg()));
		assertTrue(weekendError.equals(errorMsg.getWeekendPriceMsg()));
		assertTrue(weekError.equals(errorMsg.getWeekPriceMsg()));
		assertTrue(gpsError.equals(errorMsg.getGpsPriceMsg()));
		assertTrue(onStarError.equals(errorMsg.getOnstarPriceMsg()));
		assertTrue(siriusXMError.equals(errorMsg.getSiriusXMPriceMsg()));
	
	}


}
