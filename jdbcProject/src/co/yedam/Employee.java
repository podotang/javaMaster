package co.yedam;

import java.util.Date;

public class Employee {
	private String empName;
	private String phone;
	private String email;
	private int salary;
	private String hireDate;
	private int empNo;
	
	
	
	
	public int getEmpNo() {
		return empNo;
	}
	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public String getHireDate() {
		return hireDate;
	}
	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}
	
	
	@Override
	public String toString() {
		// "  사번 이름 이메일 급여
		return String.format("%4d %4s %12s %5d", empNo, empName, email, salary); // + "	 " + empName + " 	" + email + "	 " + salary;
		
	}
	
	
	public String showInfo() {
		// "  사번 이름 이메일 급여
		return String.format("%4d %4s %-12s %5d", empNo, empName, phone, salary); // + "	 " + empName + " 	" + email + "	 " + salary;
		
	}
	
	
	
}


















