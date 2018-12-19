package ASAPMainModel;

import com.mysql.cj.util.StringUtils;

import ASAPMainDAO.CarDAO;


public class Car {
	String carType = "";
	String capacity = "";
	String carId;
	String weekdayPrice = "";
	String weekendPrice = "";
	String weekPrice = "";
	String gpsPrice = "";
	String onstarPrice = "";
	String siriusXMPrice = "";
	String Carname = "";
	
	public String getCarname() {
		return Carname;
	}

	public void setCarname(String carname) {
		this.Carname = carname;
	}

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

	
	public String getWeekPrice() {
		return weekPrice;
	}
	public void setWeekPrice(String weekPrice) {
		this.weekPrice = weekPrice;
	}
	

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
	
	public String validateCapacity(String capacity) {
		String result = "";
		if(capacity.equals("")){
			result = "Capacity of car must be entered.";
		}else 
		if(!(capacity.matches("[0-9]+")))
			result="Capacity must only be numbers";
		return result;

	}
	
	private String validateWeekdayPrice(String price) {
		String result = "";
		if(price.equals("")){
			result = "Price of car must be entered.";
		}else
		if(!(price.matches("[0-9]*\\.?[0-9]+")))
			result="Price must only be numbers";
		return result;

	}
	
	private String validateWeekendPrice(String price) {
		String result = "";
		if(price.equals("")){
			result = "Price of car must be entered.";
		}else
		if(!(price.matches("[0-9]*\\.?[0-9]+")))
			result="Price must only be numbers";
		return result;

	}
	
	private String validateWeekPrice(String price) {
		String result = "";
		if(price.equals("")){
			result = "Price of car must be entered.";
		}else if(!(price.matches("[0-9]*\\.?[0-9]+")))
			result="Price must only be numbers";
		return result;

	}
	
	private String validateGpsPrice(String price) {
		String result = "";
		if(!price.equals("")) {
			if(!(price.matches("[0-9]+")))
				result="Price must only be numbers";
		}
		else if(price.equals("")){
			result = emptystring();
		}
		return result;

	}
	
	private String validateOnStarPrice(String price) {
		String result = "";
		
		if(!price.equals("")) {
			if(!(price.matches("[0-9]+")))
				result="Price must only be numbers";
		}
		else if(price.equals("")){
			result = emptystring();
		}
		return result;

	}
	public String emptystring(){
		return "This field cannot be empty";
	}
	private String validateSiriusXMPrice(String price) {
		String result = "";
		System.out.println("XMPRICE");
		if(!price.equals("")) {
			if(!(price.matches("[0-9]+")))
				result="Price must only be numbers";
		}
		else if(price.equals("")){
			result = emptystring();
		}
		return result;

	}
	private String validateCarname(String Cname){
		String result = "";
		System.out.println("Carname"+ Cname);
		if(Cname.equals("")){
			result = "Name cannot be empty!";
			return result;
		}
		else if(!(Cname.matches("[a-zA-Z]+"))){
			result = "Invalid name format(Please enter only letters)";	
		} else
			if ((!(CarDAO.isCarTypeUnique(Cname))))
				result="Cartype exists";
		
		return result;
	}
	
	
	public void validateCar(String action, Car car, CarErrorMessages errorMsg){
		if(action.equals("ReservationController")) {
			errorMsg.setCapacityMsg(validateCapacity(car.getCapacity()));
			errorMsg.setErrorMsg(action);
		}else if(action.equals("ManagerController")) {
			//errorMsg.setCarTypeMsg(validateCarType(car.getCarType()));
			errorMsg.setCarTypeMsg(validateCarname(car.getCarType()));
			errorMsg.setCapacityMsg(validateCapacity(car.getCapacity()));
			errorMsg.setWeekdayPriceMsg(validateWeekdayPrice(car.getWeekdayPrice()));
			errorMsg.setWeekendPriceMsg(validateWeekendPrice(car.getWeekendPrice()));
			errorMsg.setWeekPriceMsg(validateWeekPrice(car.getWeekPrice()));
			errorMsg.setGpsPriceMsg(validateGpsPrice(car.getGpsPrice()));
			errorMsg.setSiriusXMPriceMsg(validateSiriusXMPrice(car.getSiriusXMPrice()));
			errorMsg.setOnstarPriceMsg(validateOnStarPrice(car.getOnstarPrice()));
			errorMsg.setErrorMsg(action);

		} 
		
//		else if(action.equals("UserController")) {
//			errorMsg.setCapacityMsg(validateCapacity(car.getCapacity()));
//		}
		
	}
	
}