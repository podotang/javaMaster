package co.yedam.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.common.Command;
import co.yedam.service.ReviewService;
import co.yedam.service.ReviewServiceImpl;
import co.yedam.vo.ReviewVO;

public class ReviewListControl implements Command {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse res) {
		String path = "review/reviewList.tiles";

		ReviewService svc = new ReviewServiceImpl();

		List<ReviewVO> list = svc.proReviewList();	//목록
		req.setAttribute("reviewList", list);
		try {
			req.getRequestDispatcher(path).forward(req, res);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
}
