package ASAPMainModel;

public class UserErrorMessages {
	private String utaIDMsg="";
	private String firstNameMsg="";
	private String lastNameMsg="";
	private String genderMsg="";
	private String emailMsg="";
	private String phoneMsg="";
	private String addressMsg="";
	private String stateMsg = "";
	private String zipcodeMsg = "";
	private String userNameMsg="";
	private String pwordMsg="";
	private String ageMsg = "";
	private String licNoMsg="";
	public String getLicNoMsg() {
		return licNoMsg;
	}
	public void setLicNoMsg(String licNoMsg) {
		this.licNoMsg = licNoMsg;
	}
	public String getLicExpMsg() {
		return licExpMsg;
	}
	public void setLicExpMsg(String licExpMsg) {
		this.licExpMsg = licExpMsg;
	}
	private String licExpMsg="";

	public String getAgeMsg() {
		return ageMsg;
	}
	public void setAgeMsg(String ageMsg) {
		this.ageMsg = ageMsg;
	}
	private String errorMsg="";

	public String getUtaIDMsg() {
		return utaIDMsg;
	}
	public void setUtaIDMsg(String utaIDMsg) {
		this.utaIDMsg = utaIDMsg;
	}
	public String getFirstNameMsg() {
		return firstNameMsg;
	}
	public void setFirstNameMsg(String firstNameMsg) {
		this.firstNameMsg = firstNameMsg;
	}
	public String getLastNameMsg() {
		return lastNameMsg;
	}
	public void setLastNameMsg(String lastNameMsg) {
		this.lastNameMsg = lastNameMsg;
	}
	public String getGenderMsg() {
		return genderMsg;
	}
	public void setGenderMsg(String genderMsg) {
		this.genderMsg = genderMsg;
	}
	public String getEmailMsg() {
		return emailMsg;
	}
	public void setEmailMsg(String emailMsg) {
		this.emailMsg = emailMsg;
	}
	public String getPhoneMsg() {
		return phoneMsg;
	}
	public void setPhoneMsg(String phoneMsg) {
		this.phoneMsg = phoneMsg;
	}
	public String getAddressMsg() {
		return addressMsg;
	}
	public void setAddressMsg(String addressMsg) {
		this.addressMsg = addressMsg;
	}
	public String getStateMsg() {
		return stateMsg;
	}
	public void setStateMsg(String stateMsg) {
		this.stateMsg = stateMsg;
	}
	public String getZipcodeMsg() {
		return zipcodeMsg;
	}
	public void setZipcodeMsg(String zipcodeMsg) {
		this.zipcodeMsg = zipcodeMsg;
	}
	public String getUserNameMsg() {
		return userNameMsg;
	}
	public void setUserNameMsg(String userNameMsg) {
		this.userNameMsg = userNameMsg;
	}
	public String getPwordMsg() {
		return pwordMsg;
	}
	public void setPwordMsg(String pwordMsg) {
		this.pwordMsg = pwordMsg;
	}

	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String action) {
		if (!utaIDMsg.equals("") || !firstNameMsg.equals("") || !lastNameMsg.equals("") || !genderMsg.equals("") || 
				!emailMsg.equals("") || !phoneMsg.equals("")|| !addressMsg.equals("")|| !stateMsg.equals("") ||
				!zipcodeMsg.equals("")|| !userNameMsg.equals("")|| !pwordMsg.equals("") || !ageMsg.equals("") || !licNoMsg.equals("") || !licExpMsg.equals(""))
			this.errorMsg="Please correct the following errors";
	}

}