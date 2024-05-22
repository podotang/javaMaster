package co.yedam.service;

import java.util.List;

import co.yedam.vo.ReviewVO;

public interface ReviewService {

	List<ReviewVO> proReviewList();
	int getReviewCnt(int reviewNo);

}
