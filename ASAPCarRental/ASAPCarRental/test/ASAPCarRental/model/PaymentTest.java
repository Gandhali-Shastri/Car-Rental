package ASAPCarRental.model;

import static org.junit.Assert.assertTrue;
import org.junit.runner.RunWith;

import ASAPMainModel.Payment;
import ASAPMainModel.PaymentErrorMessages;

import org.junit.Before;
import org.junit.Test;
import junitparams.JUnitParamsRunner;
import junitparams.FileParameters;

@RunWith(JUnitParamsRunner.class)
public class PaymentTest {

	//declare variable here
	Payment p;
	PaymentErrorMessages pe;
	
	
	@Before
	public void setUp() throws Exception {
		//instantiate variable here
		p = new Payment();
		pe = new PaymentErrorMessages();
	}

	@FileParameters("test/PaymentTest.csv")
	@Test
	// parameters for test() need to be the same as in the Excel test case
	public void test(int testcaseno, String action,String cVV, String nameOnCard, String creditCardNumber, String cardType,
							String errorMsgs, String CVVMsg, String creditCardNumberMsg, String nameOnCardMsg){

		p.setCreditCardNumber(creditCardNumber);
		p.setCVV(cVV);
		p.setNameOnCard(nameOnCard);
		p.validate_payment(action, p, pe, cardType);
		assertTrue(errorMsgs.equals(pe.getErrorMsg()));
		assertTrue(CVVMsg.equals(pe.getCVVMsg()));
		assertTrue(creditCardNumberMsg.equals(pe.getCreditCardNumberMsg()));
		assertTrue(nameOnCardMsg.equals(pe.getNameOnCardMsg()));


		
		}
}
