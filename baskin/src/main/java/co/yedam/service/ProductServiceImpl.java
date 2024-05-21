package co.yedam.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import co.yedam.common.DataSource;
import co.yedam.product.ProductVO;
import co.yedam.product.mapper.ProductMapper;

public class ProductServiceImpl implements ProductService {
	// 주소패킹
		SqlSession session = DataSource.getInstance().openSession(true);
		// 인터페이스
		ProductMapper mapper = session.getMapper(ProductMapper.class);

	@Override
	public List<ProductVO> productList() {
		return mapper.productList();
	}

}
