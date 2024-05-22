package co.yedam.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.common.Command;
import co.yedam.service.ReviewService;
import co.yedam.service.ReviewServiceImpl;

public class ReviewCntControl implements Command{

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String path = "review/reviewList.tiles";

		
		String rno  = req.getParameter("rno");
		ReviewService svr = new ReviewServiceImpl();
		int cnt = svr.getReviewCnt(Integer.parseInt(rno));
		
		res.getWriter().print("{\"totalCount\": " + cnt + "}");
		req.setAttribute("cnt", cnt);
		req.getRequestDispatcher(path).forward(req, res);

		
	}

}
