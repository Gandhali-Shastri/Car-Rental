package ASAPMainModel;
import com.mysql.cj.util.StringUtils;
import ASAPMainDAO.ReservationDAO;

import java.math.BigDecimal;
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

	public void setTotalCost(User user, Car car, Reservation reservation) throws ParseException {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");

		Date sdate = simpleDateFormat.parse(reservation.getStartDate());
		Date edate = simpleDateFormat.parse(reservation.getEndDate());

		Calendar startCal = Calendar.getInstance();
		startCal.setTime(sdate);        

		Calendar endCal = Calendar.getInstance();
		endCal.setTime(edate);

		int weekDays = 0;
		int weekends = 0;
		int days = 0;
		int week=0;

		//		startCal.setTime(edate);
		//		endCal.setTime(sdate);

		do {
			//excluding start date
			days++;
			if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
				weekDays++;
			}else {
				weekends++;
			}
			if(days==7) {
				week++;
				weekDays=0;
				weekends=0;	
			}
			startCal.add(Calendar.DAY_OF_MONTH, 1);
		} while (startCal.getTimeInMillis() <= endCal.getTimeInMillis());

		float totalAmount = (week * Float.parseFloat(car.getWeekPrice())) + (weekDays * Float.parseFloat(car.getWeekdayPrice())) + (weekends * Float.parseFloat(car.getWeekendPrice()));
		if(reservation.isIfGPSChecked()) {
			totalAmount = totalAmount + Float.parseFloat(car.getGpsPrice());
		}
		if(reservation.isIfOnStarChecked()) {
			totalAmount = totalAmount + Float.parseFloat(car.getOnstarPrice());
		}
		if(reservation.isIfSiriusXMChecked()) {
			totalAmount = totalAmount + Float.parseFloat(car.getSiriusXMPrice());
		}


		//System.out.println("Total Amount:"+totalAmount);

		float discount = 0;
		if(user.isAac()) {
			discount = (float) (0.1 * totalAmount);
			BigDecimal bd = new BigDecimal(discount);
			bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
			discount = bd.floatValue();
			//reservation.setDiscount(String.valueOf(discount)); 
		}
		reservation.setDiscount(String.valueOf(discount));
		float tax = 0;
		tax = (float) (0.0825 * totalAmount);
		BigDecimal bd = new BigDecimal(tax);
		bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
		tax = bd.floatValue();
		reservation.setTax(String.valueOf(tax));
		int deposit = 0;
		if(Integer.parseInt(user.getAge())<25) {
			deposit = 250;
		}

		totalAmount = totalAmount + tax + deposit - discount;
		bd = new BigDecimal(totalAmount);
		bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
		totalAmount = bd.floatValue();
		reservation.setTotalCost(String.valueOf(totalAmount));

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

	public void validateReservation(Reservation reservation, ReservationErrorMessages errorMsgs, String action) throws ParseException {
		if(action.equals("makeReservation")) {
			errorMsgs.setStartDateMsg(validateStartDate(reservation.getStartDate(),"makeReservation"));
			errorMsgs.setEndDateMsg(validateEndDate(reservation.getStartDate(), reservation.getEndDate(),"makeReservation"));
			errorMsgs.setPickUpTimeMsg(validatePickUpTime(startDate, pickUpTime));
			errorMsgs.setDropTimeMsg(validateDropTime(reservation.getEndDate(), reservation.getDropTime()));
			errorMsgs.setErrorMsg("error");
		}
		if(action.equals("managerReservation")) {
			errorMsgs.setStartDateMsg(validateStartDate(reservation.getStartDate(), "managerReservation"));
			errorMsgs.setEndDateMsg(validateEndDate(reservation.getStartDate(), reservation.getEndDate(), "managerReservation"));
			errorMsgs.setErrorMsg("error");
		}

	}

	public String validateStartDate(String startDate, String action) throws ParseException {
		if (startDate.equals("")) {
			result = "Date cannot be blank";

		}else {
			if(!action.equals("managerReservation")) {
				//convert string to date
				Date date = new SimpleDateFormat("MM/dd/yyyy").parse(startDate);
				Date today = new Date();
				DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
				today = df.parse(df.format(today));		

				if(!date.after(today)) {
					result = "Chosen date can't be before today's date";				 
				}

			}
		}
		return result;
	}

	public String validateEndDate(String startDate, String endDate, String action) throws ParseException {
		String result="";
		if (endDate.equals("")) {
			result = "End date cannot be blank";
		}
		else if(!startDate.equals("")) {
			//result = "Start date cannot be blank";

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
		String result="";
		
		if(startDate.equals("")) {
			result = "Pick-up date is empty";
		} else {
			Date start = new SimpleDateFormat("MM/dd/yyyy").parse(startDate);
			SimpleDateFormat sdf = new SimpleDateFormat("EEEEE");
			String dow = sdf.format(start);
			int time = Integer.parseInt(pickUpTime);
			if (startDate.isEmpty() || pickUpTime.isEmpty()) {
				result = "Pick up time/Start date cannot be blank";
			}else if((dow.equals("Monday") || dow.equals("Tuesday") || dow.equals("Wednesday") ||
					dow.equals("Thursday") || dow.equals("Friday")) && (time < 8 || time > 20)) {
				result = "Mon to Fri 8am to 8pm";
			}else if((dow.equals("Saturday"))  && (time < 8 || time > 17)) {
				result = "Saturdays 8am to 5pm";
			}else if ((dow.equals("Sunday")) && (time < 12 || time > 17 )) {				
				result = "Sundays 12pm to 5pm";
			}
		}

		return result;
	}

	public String validateDropTime(String endDate, String dropTime) throws ParseException {
		String result="";

		if(endDate.equals("")) {
			result = "Drop-off date is empty";
		} else {
			Date end = new SimpleDateFormat("MM/dd/yyyy").parse(endDate);
			SimpleDateFormat sdf = new SimpleDateFormat("EEEEE");
			String dow = sdf.format(end);
			int time = Integer.parseInt(dropTime);
			if (endDate.isEmpty() || dropTime.isEmpty()) {
				result = "Drop in time/End date cannot be blank";
			}else if((dow.equals("Monday") || dow.equals("Tuesday") || dow.equals("Wednesday") ||
					dow.equals("Thursday") || dow.equals("Friday")) && (time < 8 || time > 20)) {
				result = "Mon to Fri 8am to 8pm";
			}else if((dow.equals("Saturday"))  && (time < 8 || time > 17)) {
				result = "Saturdays 8am to 5pm";
			}else if ((dow.equals("Sunday")) && (time < 12 || time > 17 )) {				
				result = "Sundays 12pm to 5pm";
			}
		}
		return result;
	}

}
