package ASAPMainModel;

public class Car {
	String carType = "";
	String capacity = "";
	String carId;
	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getCapacity() {
		return capacity;
	}

	String weekdayPrice = "";
	String weekendPrice = "";
	String weekPrice = "";
	public String getWeekPrice() {
		return weekPrice;
	}
	public void setWeekPrice(String weekPrice) {
		this.weekPrice = weekPrice;
	}
	String gpsPrice = "";
	String onstarPrice = "";
	String siriusXMPrice = "";

	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
	public String getWeekdayPrice() {
		return weekdayPrice;
	}
	public void setWeekdayPrice(String weekdayPrice) {
		this.weekdayPrice = weekdayPrice;
	}
	public String getWeekendPrice() {
		return weekendPrice;
	}
	public void setWeekendPrice(String weekendPrice) {
		this.weekendPrice = weekendPrice;
	}
	public String getGpsPrice() {
		return gpsPrice;
	}
	public void setGpsPrice(String gpsPrice) {
		this.gpsPrice = gpsPrice;
	}
	public String getOnstarPrice() {
		return onstarPrice;
	}
	public void setOnstarPrice(String onstarPrice) {
		this.onstarPrice = onstarPrice;
	}
	public String getSiriusXMPrice() {
		return siriusXMPrice;
	}
	public void setSiriusXMPrice(String siriusXMPrice) {
		this.siriusXMPrice = siriusXMPrice;
	}

	public void validate_capacity(Car car, CarErrorMessages errorMsg){
		errorMsg.setCapacityMsg(validateCapacity(car.getCapacity()));
		errorMsg.setErrorMsg("error");
	}

	private String validateCapacity(String capacity) {
		String result = "";
		if(capacity.equals("")){
			result = "Capacity of car must be entered.";
		}
		if(!(capacity.matches("[0-9]+")))
			result="Capacity must only be numbers";
		return result;

	}
}
