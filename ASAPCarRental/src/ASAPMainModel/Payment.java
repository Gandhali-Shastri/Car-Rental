package ASAPMainModel;
import java.util.regex.Pattern;

public class Payment {
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getReservationID() {
		return reservationID;
	}
	public void setReservationID(String reservationID) {
		this.reservationID = reservationID;
	}
	public String getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(String totalCost) {
		this.totalCost = totalCost;
	}
	public String getPaymentID() {
		return paymentID;
	}
	public void setPaymentID(String paymentID) {
		this.paymentID = paymentID;
	}
	private String CVV = "";
	public String getCVV() {
		return CVV;
	}
	public void setCVV(String cVV) {
		CVV = cVV;
	}
	private String username ="";
	private String reservationID ="";
	private String totalCost ="";
	private String paymentID = "";
	private String creditCardNumber = "";
	public String getCreditCardNumber() {
		return creditCardNumber;
	}
	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}
	public String getNameOnCard() {
		return nameOnCard;
	}
	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}

	private String nameOnCard = "";



	public void validate_payment(String action, Payment payment, PaymentErrorMessages errorMsg, String cardType){
			errorMsg.setCreditCardNumberMsg(validateCreditCardNumberMsg(payment.getCreditCardNumber(), cardType));
			errorMsg.setNameOnCardMsg(validateNameOnCardMsg(payment.getNameOnCard()));
			errorMsg.setCVVMsg(validateCVV(payment.getCVV()));
			errorMsg.setErrorMsg("error");
	}
	private String validateCVV(String cvv) {
		String result = "";
		int cv = 0;
		try{
			cv = Integer.parseInt(cvv);
		}
		catch(NumberFormatException e){
			result = "Invalid input";
			return result;
		}
		if(cvv.length() != 3 ){
			result = "Invalid CVV length";
		}

		return result;
	}
	private String validateCreditCardNumberMsg(String creditCardNumber, String cardType) {
		String result = "";
		if(creditCardNumber.isEmpty()) {
			result = "Card number cannot be empty.";
		}else if(cardType.equals("Visa") && !creditCardNumber.startsWith("4")) {
			result ="Enter appropriate Credit Card Number";
		}else if(cardType.equals("Master Card") && !creditCardNumber.startsWith("5")) {
			result ="Enter appropriate Credit Card Number";
		}else if(cardType.equals("American Express") && !creditCardNumber.startsWith("6")) {
			result ="Enter appropriate Credit Card Number";
		}else if(cardType.equals("Discover") && !creditCardNumber.startsWith("7")) {
			result ="Enter appropriate Credit Card Number";
		}else if(!(creditCardNumber.length() == 16)) {
			result = "Credit Card Number incomplete";
		}

		return result;
	}

	private String validateNameOnCardMsg(String nameoncard) {
		String result = "";
		if(nameoncard.equals("")){
			result = "Name field cannot be blank";
		}
		if (Pattern.matches("[a-zA-Z'-]+", nameoncard)){
		}
		else{
			result = "Name should be only alphabets";
		}

		return result;
	}
}
