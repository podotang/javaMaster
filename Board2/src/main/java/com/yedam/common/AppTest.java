package com.yedam.common;

import java.util.List;

import com.yedam.dao.BoardDAO;
import com.yedam.vo.BoardVO;

public class AppTest {
	public static void main(String[] args) {
		BoardDAO bdao = new BoardDAO();

		// boardList
		List<BoardVO> list = bdao.boardList();
		for (BoardVO vo : list) {
			System.out.println(vo.toString());
		}
		// insertContent
		BoardVO bvo = new BoardVO();
		bvo.setBoardTitle("제목");
		bvo.setBoardContent("내용");
		bvo.setBoardWriter("정유진");
		bvo.setModifiedDate("2024-05-06");
		if (bdao.insertContent(bvo)) {
			System.out.println("성공");
		} else {
			System.out.println("실패ㅠ");
		}

		// updateContent
		bvo.setBoardNo(1);
		bvo.setBoardTitle("HELLO");
		bvo.setBoardContent("언제자?");
		bvo.setBoardWriter("yujin");
		if (bdao.updateContent(bvo)) {
			System.out.println("성공");
		} else {
			System.out.println("실패ㅠ");
		}

		// deleteContent
		bvo.setBoardNo(3);
		if (bdao.deleteContent(bvo)) {
			System.out.println("성공");
		} else {
			System.out.println("실패ㅠ");
		}

	}
}
