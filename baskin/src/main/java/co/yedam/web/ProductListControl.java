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

public class ProductListControl implements Command {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) {
		String path = "product/productList.tiles";

		ProductVO pvo = new ProductVO();
		ProductService svc = new ProductServiceImpl();

		List<ProductVO> list = svc.productList();	//목록
		req.setAttribute("productList", list);
		try {
			req.getRequestDispatcher(path).forward(req, res);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

}
