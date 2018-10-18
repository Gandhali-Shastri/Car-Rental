package ASAPMainModel;

public class PaymentErrorMessages {
	public String getUsernameMsg() {
		return usernameMsg;
	}
	public void setUsernameMsg(String usernameMsg) {
		this.usernameMsg = usernameMsg;
	}
	public String getCVVMsg() {
		return CVVMsg;
	}
	public void setCVVMsg(String cVVMsg) {
		CVVMsg = cVVMsg;
	}
	public String getReservationIDMsg() {
		return reservationIDMsg;
	}
	public void setReservationIDMsg(String reservationIDMsg) {
		this.reservationIDMsg = reservationIDMsg;
	}
	public String getTotalCostMsg() {
		return totalCostMsg;
	}
	public void setTotalCostMsg(String totalCostMsg) {
		this.totalCostMsg = totalCostMsg;
	}
	public String getPaymentIDMsg() {
		return paymentIDMsg;
	}
	public void setPaymentIDMsg(String paymentIDMsg) {
		this.paymentIDMsg = paymentIDMsg;
	}
	public String getCreditCardNumberMsg() {
		return creditCardNumberMsg;
	}
	public void setCreditCardNumberMsg(String creditCardNumberMsg) {
		this.creditCardNumberMsg = creditCardNumberMsg;
	}
	public String getNameOnCardMsg() {
		return nameOnCardMsg;
	}
	public void setNameOnCardMsg(String nameOnCardMsg) {
		this.nameOnCardMsg = nameOnCardMsg;
	}
	private String usernameMsg ="";


	private String CVVMsg = "";

	private String reservationIDMsg ="";
	private String totalCostMsg ="";
	private String paymentIDMsg = "";
	private String creditCardNumberMsg = "";

	private String nameOnCardMsg = "";

	private String errorMsg= "";


	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		if( !creditCardNumberMsg.equals("") || !nameOnCardMsg.equals("") || !CVVMsg.equals("") ){
			this.errorMsg = "Please correct following fields";
		}

	}
}
