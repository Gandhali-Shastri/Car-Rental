package ASAPMainModel;

public class CarErrorMessages {

	String carTypeMsg = "";
	String capacityMsg = "";
	String weekdayPriceMsg = "";
	String weekendPriceMsg = "";
	String weekPriceMsg = "";
	String gpsPriceMsg = "";
	String onstarPriceMsg = "";
	String siriusXMPriceMsg = "";
	
	public String getCarTypeMsg() {
		return carTypeMsg;
	}

	public void setCarTypeMsg(String carTypeMsg) {
		this.carTypeMsg = carTypeMsg;
	}

	public String getCapacityMsg() {
		return capacityMsg;
	}

	public void setCapacityMsg(String capacityMsg) {
		this.capacityMsg = capacityMsg;
	}

	public String getWeekdayPriceMsg() {
		return weekdayPriceMsg;
	}

	public void setWeekdayPriceMsg(String weekdayPriceMsg) {
		this.weekdayPriceMsg = weekdayPriceMsg;
	}

	public String getWeekendPriceMsg() {
		return weekendPriceMsg;
	}

	public void setWeekendPriceMsg(String weekendPriceMsg) {
		this.weekendPriceMsg = weekendPriceMsg;
	}

	public String getWeekPriceMsg() {
		return weekPriceMsg;
	}

	public void setWeekPriceMsg(String weekPriceMsg) {
		this.weekPriceMsg = weekPriceMsg;
	}

	public String getGpsPriceMsg() {
		return gpsPriceMsg;
	}

	public void setGpsPriceMsg(String gpsPriceMsg) {
		this.gpsPriceMsg = gpsPriceMsg;
	}

	public String getOnstarPriceMsg() {
		return onstarPriceMsg;
	}

	public void setOnstarPriceMsg(String onstarPriceMsg) {
		this.onstarPriceMsg = onstarPriceMsg;
	}

	public String getSiriusXMPriceMsg() {
		return siriusXMPriceMsg;
	}

	public void setSiriusXMPriceMsg(String siriusXMPriceMsg) {
		this.siriusXMPriceMsg = siriusXMPriceMsg;
	}


	

	private String errorMsg= "";

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String action) {
		
		if (action.equals("ReservationController")) {
			if(!capacityMsg.equals("") ){
				this.errorMsg = "Please correct following fields";
			}
		}
		else {
			if(!carTypeMsg.equals("") || !capacityMsg.equals("") || !weekdayPriceMsg.equals("") || !weekendPriceMsg.equals("") || !weekPriceMsg.equals("")
					|| !gpsPriceMsg.equals("") || !onstarPriceMsg.equals("") || !siriusXMPriceMsg.equals("")){
				this.errorMsg = "Please correct following fields";
			}
		}

		
		
	}
}