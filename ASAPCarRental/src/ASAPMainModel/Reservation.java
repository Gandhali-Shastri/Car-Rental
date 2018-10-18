package ASAPMainModel;
import com.mysql.cj.util.StringUtils;
import ASAPMainDAO.ReservationDAO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Reservation {


	private String reservationID ="";
	private String username ="";
	private String carID ="";
	private String startDate ="";
	private String endDate ="";
	private String pickUpTime ="";
	private String dropTime ="";
	private String totalCost ="";
	private boolean ifGPSChecked = false;
	private boolean ifSiriusXMChecked = false;
	private boolean ifOnStarChecked = false;
	private String status = "";
	private String discount ="";
	private String tax ="";
	private String result="";

	public String getReservationID() {
		return reservationID;
	}
	public void setReservationID(String reservationID) {
		this.reservationID = reservationID;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCarID() {
		return carID;
	}
	public void setCarID(String carID) {
		this.carID = carID;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getPickUpTime() {
		return pickUpTime;
	}
	public void setPickUpTime(String pickUpTime) {
		this.pickUpTime = pickUpTime;
	}
	public String getDropTime() {
		return dropTime;
	}
	public void setDropTime(String dropTime) {
		this.dropTime = dropTime;
	}
	public String getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(String totalCost) {
		this.totalCost = totalCost;
	}
	public boolean isIfGPSChecked() {
		return ifGPSChecked;
	}
	public void setIfGPSChecked(boolean ifGPSChecked) {
		this.ifGPSChecked = ifGPSChecked;
	}
	public boolean isIfSiriusXMChecked() {
		return ifSiriusXMChecked;
	}
	public void setIfSiriusXMChecked(boolean ifSiriusXMChecked) {
		this.ifSiriusXMChecked = ifSiriusXMChecked;
	}
	public boolean isIfOnStarChecked() {
		return ifOnStarChecked;
	}
	public void setIfOnStarChecked(boolean ifOnStarChecked) {
		this.ifOnStarChecked = ifOnStarChecked;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getTax() {
		return tax;
	}
	public void setTax(String tax) {
		this.tax = tax;
	}

	public void validateReservation(Reservation reservation, ReservationErrorMessages errorMsgs) throws ParseException {
		errorMsgs.setStartDateMsg(validateStartDate(reservation.getStartDate()));
		errorMsgs.setEndDateMsg(validateEndDate(reservation.getStartDate(), reservation.getEndDate()));
		errorMsgs.setPickUpTimeMsg(validatePickUpTime(startDate, pickUpTime));
		errorMsgs.setDropTimeMsg(validateDropTime(reservation.getEndDate(), reservation.getDropTime()));
		errorMsgs.setErrorMsg("error");
	}

	public String validateStartDate(String startDate) throws ParseException {
		if (startDate.equals("")) {
			result = "Date cannot be blank";

		}else {
			//convert string to date
			Date date = new SimpleDateFormat("MM/dd/yyyy").parse(startDate);
			Date today = new Date();
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			today = df.parse(df.format(today));		

			if(!date.after(today)) {
				result = "Chosen date can't be before today's date";				 
			}
		}
		return result;
	}

	public String validateEndDate(String startDate, String endDate) throws ParseException {
		if (endDate.equals("")) {
			result = "Date cannot be blank";

		}else {
			//convert string to date
			Date start = new SimpleDateFormat("MM/dd/yyyy").parse(startDate);
			Date end = new SimpleDateFormat("MM/dd/yyyy").parse(endDate);
			if(end.before(start)) {
				result = "Chosen drop date can't be before Start date";				 
			}
		}
		return result;
	}

	public String validatePickUpTime(String startDate, String pickUpTime) throws ParseException {
		if(startDate.equals("")) {
			result = "Start date is empty";
		} else {
			Date start = new SimpleDateFormat("MM/dd/yyyy").parse(startDate);
			SimpleDateFormat sdf = new SimpleDateFormat("EEEEE");
			String dow = sdf.format(start);
			int time = Integer.parseInt(pickUpTime);
			if (startDate.isEmpty() || pickUpTime.isEmpty()) {
				result = "Pick up time/Start date cannot be blank";
			}else if((dow.equals("Monday") || dow.equals("Tuesday") || dow.equals("Wednesday") ||
					dow.equals("Thursday") || dow.equals("Friday")) && (time < 8 || time > 20)) {
				result = "Weekdays drop in time restricted to 8hr to 20hr";
			}else if((dow.equals("Saturday"))  && (time < 8 || time > 17)) {
				result = "Saturdays drop in time restricted to 8hr to 17hr";
			}else if ((dow.equals("Sunday")) && (time < 12 || time > 17 )) {				
				result = "Sundays drop in time restricted to 12hr to 17hr";
			}
		}

		return result;
	}

	public String validateDropTime(String endDate, String dropTime) throws ParseException {
		if(endDate.equals("")) {
			result = "End date is empty";
		} else {
			Date end = new SimpleDateFormat("MM/dd/yyyy").parse(endDate);
			SimpleDateFormat sdf = new SimpleDateFormat("EEEEE");
			String dow = sdf.format(end);
			int time = Integer.parseInt(dropTime);
			if (endDate.isEmpty() || dropTime.isEmpty()) {
				result = "Drop in time/End date cannot be blank";
			}else if((dow.equals("Monday") || dow.equals("Tuesday") || dow.equals("Wednesday") ||
					dow.equals("Thursday") || dow.equals("Friday")) && (time < 8 || time > 20)) {
				result = "Weekdays drop in time restricted to 8hr to 20hr";
			}else if((dow.equals("Saturday"))  && (time < 8 || time > 17)) {
				result = "Saturdays drop in time restricted to 8hr to 17hr";
			}else if ((dow.equals("Sunday")) && (time < 12 || time > 17 )) {				
				result = "Sundays drop in time restricted to 12hr to 17hr";
			}
		}
		return result;
	}



}
