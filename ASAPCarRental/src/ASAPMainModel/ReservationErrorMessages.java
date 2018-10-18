package ASAPMainModel;

public class ReservationErrorMessages {
	private String startDateMsg = "";
	private String endDateMsg = "";
	private String pickUpTimeMsg = "";
	private String dropTimeMsg = "";
	private String errorMsg = "";
	
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		if(!startDateMsg.equals("")||!endDateMsg.equals("")||!pickUpTimeMsg.equals("")||!dropTimeMsg.equals("")) {
			this.errorMsg = "Please fix following errors";
		}
	}
	public String getStartDateMsg() {
		return startDateMsg;
	}
	public void setStartDateMsg(String startDateMsg) {
		this.startDateMsg = startDateMsg;
	}
	public String getEndDateMsg() {
		return endDateMsg;
	}
	public void setEndDateMsg(String endDateMsg) {
		this.endDateMsg = endDateMsg;
	}
	public String getPickUpTimeMsg() {
		return pickUpTimeMsg;
	}
	public void setPickUpTimeMsg(String pickUpTimeMsg) {
		this.pickUpTimeMsg = pickUpTimeMsg;
	}
	public String getDropTimeMsg() {
		return dropTimeMsg;
	}
	public void setDropTimeMsg(String dropTime) {
		this.dropTimeMsg = dropTime;
	}
	
	

}
