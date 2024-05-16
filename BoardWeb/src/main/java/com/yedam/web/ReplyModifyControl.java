package com.yedam.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.common.Control;
import com.yedam.service.ReplyService;
import com.yedam.service.ReplyServiceImpl;
import com.yedam.vo.ReplyVO;

public class ReplyModifyControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String rno = req.getParameter("rno");
        String reply = req.getParameter("reply");
        String rpage = req.getParameter("rpage");

        ReplyVO vo = new ReplyVO();
        vo.setReplyNo(Integer.parseInt(rno));
        vo.setReply(reply);
        //vo.set;

        ReplyService service = new ReplyServiceImpl();


		Map<String,Object> result = new HashMap<String, Object>();

        if (service.modifyReply(vo)) {
        	result.put("retCode","OK");
			result.put("retVal",vo);
        } else {
        	result.put("retCode","NG");
			result.put("retVal",null);
        }

        resp.setContentType("application/json;charset=utf-8");
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(result);
        resp.getWriter().print(json);
		
	}

}
