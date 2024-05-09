package com.yedam.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.web.AddBoardControl;
import com.yedam.web.AddFromControl;
import com.yedam.web.BoardInfoControl;
import com.yedam.web.MainControl;
import com.yedam.web.ModifyControl;
import com.yedam.web.ModifyFormControl;
import com.yedam.web.RemoveControl;
import com.yedam.web.RemoveFormControl;

public class FrontController extends HttpServlet {
	Map<String, Control> map;

	public FrontController() {
		map = new HashMap<>();
	}
	@Override
	public void init(ServletConfig config) throws ServletException {
		map.put("/main.do", new MainControl());
		//Form.do는 등록등 보여주는기능
		map.put("/addForm.do", new AddFromControl());
		map.put("/addBoard.do", new AddBoardControl());
		map.put("/boardInfo.do", new BoardInfoControl());
		//수정화면여는기능
		map.put("/modBoardForm.do", new ModifyFormControl());
		//실제 수정하고 목록으로이동
		map.put("/updateBoard.do", new ModifyControl());
		map.put("/deleteBoard.do", new RemoveControl());
		map.put("/removeBoard.do", new RemoveFormControl());
		
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String uri =req.getRequestURI();
		String context = req.getContextPath();
		System.out.println("uri : " + uri + ", context " + context);
		String path =uri.substring(context.length()); 
		System.out.println("path : " + path);
		Control control = map.get(path);
		control.exec(req,resp);
	}
	@Override
	public void destroy() {
	}

}