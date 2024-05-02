package com.yedam.vo;

import java.sql.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
// lombok: 1이클립스에 설치, 2라이브러리 필요.
/*@Getter
@Setter
@ToString*/
@Data
public class EmpVo {
	private int empNo;	//오라클(emp_no) 자바(empNo)
	private String empName;
	private String empPhone;
	private String email;
	private String hireDate;	//2020-05-2
	private int salary;
}

