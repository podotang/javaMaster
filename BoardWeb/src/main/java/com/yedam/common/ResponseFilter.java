package com.yedam.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResponseFilter implements Filter{

	String resp;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		resp = filterConfig.getInitParameter("response");	// /main.do /addForm.do
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println(resp);
		
		//request.getServletContext().getContextPath()
		HttpServletRequest hrequest = (HttpServletRequest) request;
		String uri = hrequest.getRequestURI();
		String context = hrequest.getContextPath();
		String path = uri.substring(context.length());
		
		System.out.println(resp.indexOf(path));
		if(resp.indexOf(path)!= -1) {
			//권한체크할 대상임 담아서 감 chain.doFilter가 실행
			request.setAttribute("logPath", "/logForm.do");
			//((HttpServletResponse) response).sendRedirect("logForm.do");
		}
		//서블릿실행 -> 메인컨트롤로감
		chain.doFilter(request, response);
		
		
	}

}
