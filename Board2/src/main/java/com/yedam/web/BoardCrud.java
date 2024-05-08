package com.yedam.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.dao.BoardDAO;
import com.yedam.vo.BoardVO;


@WebServlet("/boardCrud.json")
public class BoardCrud extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/json;charset=utf-8");
		Gson gson = new GsonBuilder().create();
		BoardVO bvo = new BoardVO();
		BoardDAO bdao = new BoardDAO();
		Map<String,Object> map = new HashMap<>();
		String bno = req.getParameter("boardNo");
		String btitle = req.getParameter("boardTitle");
		String bcontnent = req.getParameter("boardContent");
		String bwriter = req.getParameter("boardWriter");
		String bdate = req.getParameter("date");
		String bmodi = req.getParameter("modifiedDate");
		String job= req.getParameter("job");
		
		if(job.equals("add")) {
			bvo.setBoardTitle(btitle);
			bvo.setBoardContent(bcontnent);
			bvo.setBoardWriter(bwriter);
			
			if(bdao.insertContent(bvo)) {
				map.put("retCode", "OK");
				map.put("retVal", bvo);
				System.out.println(bvo);
				resp.getWriter().print(gson.toJson(map));
			}else {
				map.put("retCode", "NG");
				map.put("retVal", null);
				resp.getWriter().print(gson.toJson(map));
			}
			
		}
		
		
	}
}
