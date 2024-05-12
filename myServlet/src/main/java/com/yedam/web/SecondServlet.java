package com.yedam.web;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/second.action")
public class SecondServlet extends HttpServlet {

	// 생명주기
	// init -> service -> destroy
	public SecondServlet() {
		System.out.println("1 생성자 호출됨. :: 인스턴스 생성.");
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("2 init 호출됨. :: 서버실행 후 최초한번만 호출됨.");
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("3 service 호출됨. :: 서블릿이 실행될 때마다 호출.");
	}
	
	@Override
	//서버 스탑시
	public void destroy() {
		System.out.println("destroy 호출됨. :: 인스턴스가(생성된 객체가) 메모리에서 사라질 때 한번 호출.");
	}
	

}
