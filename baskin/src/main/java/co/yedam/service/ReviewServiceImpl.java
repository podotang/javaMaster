package co.yedam.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import co.yedam.common.DataSource;
import co.yedam.mapper.ReviewMapper;
import co.yedam.vo.ReviewVO;

public class ReviewServiceImpl implements ReviewService {
	// 주소패킹
		SqlSession session = DataSource.getInstance().openSession(true);
		// 인터페이스
		ReviewMapper mapper = session.getMapper(ReviewMapper.class);

	@Override
	public List<ReviewVO> proReviewList() {
		return mapper.reviewList();
	}

	@Override
	public int getReviewCnt(int reviewNo) {
		return mapper.getReviewTotalCnt(reviewNo);
	}

}
