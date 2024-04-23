package co.yedam;

public class Member {
	private int sempNo;
	private String sempName;
	private String sempPhone;
	private String sempBirth;
	private String sempGen;
	
	public int getSempNo() {
		return sempNo;
	}
	public void setSempNo(int sempNo) {
		this.sempNo = sempNo;
	}
	public String getSempName() {
		return sempName;
	}
	public void setSempName(String sempName) {
		this.sempName = sempName;
	}
	public String getSempPhone() {
		return sempPhone;
	}
	public void setSempPhone(String sempPhone) {
		this.sempPhone = sempPhone;
	}
	public String getSempBirth() {
		return sempBirth;
	}
	public void setSempBirth(String sempBirth) {
		this.sempBirth = sempBirth;
	}
	public String getSempGen() {
		return sempGen;
	}
	public void setSempGen(String sempGen) {
		this.sempGen = sempGen;
	}
	
	
	public String showInfo() {
		return String.format("%4d    %-6s    %-12s  %-10s  %s", sempNo,sempName,sempPhone,sempBirth,sempGen);
	}
	
	
	
	
	
} // end class













