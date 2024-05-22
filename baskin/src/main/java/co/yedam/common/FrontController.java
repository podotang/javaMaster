package co.yedam.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.web.ReviewCntControl;
import co.yedam.web.ReviewListControl;

//url: *.do로 끝나면 전부 여기로 들어와서 실행
public class FrontController extends HttpServlet {

	Map<String, Object> map = new HashMap<>();

	@Override
	public void init(ServletConfig config) throws ServletException {
		// 첫 페이지
		map.put("/reviewList.do", new ReviewListControl());
		map.put("/reviewCnt.do", new ReviewCntControl());
		
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 요청정보에 포함되어있는 한글을 인코딩방식 지정하기
		req.setCharacterEncoding("UTF-8");

		String uri = req.getRequestURI(); // 사용자가 어떤 페이지를 요청했는지에 대한 정보(프로젝트 정보의 뒤쪽부터)
											// //http://localhost:8080/helloJSP/??.do
		String context = req.getServletContext().getContextPath(); // 어떤 프로젝트인지에 대한 정보()
		System.out.println("uri : " + uri + ", context " + context);
		String page = uri.substring(context.length());
		System.out.println("page : " + page);
		Command controller = (Command) map.get(page); // 사용자가 요청한 페이지의 값을 맵의 값에서 찾기
		controller.exec(req, resp);

	}
}



