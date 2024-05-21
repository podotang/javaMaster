package co.yedam.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.common.Command;
import co.yedam.service.ProductService;
import co.yedam.service.ProductServiceImpl;
import co.yedam.vo.ProductVO;

public class ProductInfoControl implements Command {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) {
		String path = "WEB-INF/product/productInfo.jsp";
		path ="product/productInfo.tiles";
		//ProductVO pvo = new ProductVO();
		ProductService svc = new ProductServiceImpl();

		List<ProductVO> Info = svc.productList();	//목록
		req.setAttribute("productInfo", Info);
		try {
			req.getRequestDispatcher(path).forward(req, res);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
