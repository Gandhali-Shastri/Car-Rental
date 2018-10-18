package ASAPMainModel;

public class CarErrorMessages {

	private String capacityMsg = "";
	public String getCapacityMsg() {
		return capacityMsg;
	}

	public void setCapacityMsg(String capacityMsg) {
		this.capacityMsg = capacityMsg;
	}

	private String errorMsg= "";

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {

		if(!capacityMsg.equals("") ){
			this.errorMsg = "Please correct following fields";
		}
		
	}
}
