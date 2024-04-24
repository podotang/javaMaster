package co.yedam;

import java.util.List;
import java.util.Scanner;

public class BoardExample {

	Scanner sc = new Scanner(System.in);
	BoardDAO bdao = new BoardDAO();
	CommentsDAO cdao = new CommentsDAO();
	String id;
	Board board = new Board();
	Comments com = new Comments();
	MemberDAO md = new MemberDAO();

	// 제일큰곳
	public void exe() {

		MemberDAO mdao = new MemberDAO();

		boolean success = false;
//		
//		for (int a=0; a<3; a++) {
//			System.out.print("비밀번호를 입력하세요>>>");
//			if (id.equals(pw)) {
//				success = true;
//				break;
//			}
//		}

		// 교수님 로그인처리법
		for (int i = 0; i < 3; i++) {
			System.out.println("id>>");
			id = sc.nextLine();
			System.out.println("pw>>");
			String pw = sc.nextLine();
			if (!mdao.loginCheck(id, pw)) {
				if (i == 2) {
					System.out.println("로그인 실패입니다.");
					return;
				}
				System.out.println("id, pw 확인하세요");
			} else {
				success = true;
				System.out.println("로그인 성공");
				break;
			}
		} // 3번반복후 탈출

		boolean run = true;

		while (run) {
			System.out.println("1.게시판목록 2.글쓰기 3.글수정하기 4.삭제 5.설정 6.종료");
			System.out.print("선택>>");
			int chooseN = sc.nextInt();
			sc.nextLine();

			switch (chooseN) {

			case 1:
				// 글목록
				// 상세보기
				// 글검색
				// 댓글관련포함
				boardList();
				break;
			case 2:
				// 글작성하기
				boardWriter();
				break;

			case 3:
				// 글수정하기 - 날짜수정하기
				boardUpdate();
				break;

			case 4:
				// 글삭제 -완료
				boardDelete();
				break;

			case 5:
				// 설정
				// 로그인 회원가입 아이디/비번수정 회원탈퇴
				System.out.println("1.회원가입 2.회원정보 수정 3.회원탈퇴");
				int memberSet = sc.nextInt();

				switch (memberSet) {
				case 1:
					System.out.println("회원가입 완료");
					break;
				case 2:
					System.out.println("정보수정 완료");
					break;
				case 3:
					System.out.println("정말 탈퇴 하시겠습니까?");
					System.out.println("회원탈퇴 완료");
					break;
				}
				break;
			case 6:
				// 종료
				System.out.println("게시판 서비스를 종료합니다");
				run = false;
				break;
			}

		}

	}// end of exe.

	public void boardList() {
		while (true) {
			System.out.print("제목을 입력하세요>>");
			String title = sc.nextLine();
			System.out.println("====================================================================");
			System.out.println("글번호		글제목	 글쓴이		작성일");
			System.out.println("--------------------------------------------------------------------");
			List<Board> InfoList = bdao.boardList(title);
			for (Board search : InfoList) {
				System.out.println(search.showSearch());
			}
			System.out.print("상세보기할 글 번호를 입력하세요>> ");
			int selectedBoardNo = sc.nextInt();
			sc.nextLine(); // 개행문자 처리
			boardDetail(selectedBoardNo);

			// 계속할 것인지 물어보기
			System.out.print("계속하시겠습니까? (y/n): ");
			String answer = sc.nextLine();
			if (!answer.equalsIgnoreCase("y")) {
				break;
			}
		}

	}

	public void boardDetail(int selectedBoardNo) {
		Board selectedBoard = bdao.boardInfo(selectedBoardNo);
		if (selectedBoard != null) {
			// 선택한 글 정보 출력
			System.out.println("====================================================================");
			System.out.println("<글내용>");
			System.out.println("--------------------------------------------------------------------");
			System.out.println("제목 : " + selectedBoard.getBoardTitle());
			System.out.println("내용 : " + selectedBoard.getBoardContent());
			System.out.println("작성자: " + selectedBoard.getBoardWriter());
			System.out.println("작성일: " + selectedBoard.getWriteDate());
			System.out.println("--------------------------------------------------------------------");
			System.out.println("<댓글>");
			System.out.println("====================================================================");
			System.out.println("댓글번호	 내용	    글쓴이		작성일");

			List<Comments> clist = cdao.commentList(selectedBoardNo);
			for (Comments com : clist) {
				System.out.println(com.showComments());
			}
			boolean run = true;

			while (run) {
				System.out.println("1.댓글쓰기 2.댓글 수정 3.댓글 삭제 4.메인으로");
				int reply = sc.nextInt();
				sc.nextLine();

				switch (reply) {
				case 1:
					commentInsert(selectedBoardNo);
					break;
				case 2:
					commentUpdate();
					break;
				case 3:
					commentDelete();

					break;
				case 4:
					run = false;
					break;
				}
			}
		} else {
			System.out.println("해당하는 글이 없습니다.");
		}

		// 본인이 단 댓글이 있으면 댓글 수정,삭제 보이기

	}

	// 글쓰기 - 완료
	public void boardWriter() {
		System.out.print("글제목>>");
		String boardTitle = sc.next();
		System.out.print("글내용>>");
		String boardContent = sc.next();
		// 로그인한 아이디 정보
		String boardWriter = id;
		Board board = new Board();
		board.setBoardTitle(boardTitle);
		board.setBoardContent(boardContent);
		board.setBoardWriter(boardWriter);
		if (bdao.insertContent(board)) {
			System.out.println("글 등록이 완료되었습니다.");
		} else {
			System.out.println("글 등록 실패");
		}
	}

	// 글수정
	public void boardUpdate() {
		System.out.println("수정할 글 번호를 선택하세요>>");
		int chooseNum = sc.nextInt();
		sc.nextLine();
		System.out.print("제목수정>> ");
		String boardTitle = sc.nextLine();
		System.out.print("내용수정>> ");
		String boardContent = sc.nextLine();
		board.setBoardNo(chooseNum);
		board.setBoardTitle(boardTitle);
		board.setBoardContent(boardContent);
		if (bdao.updateContent(board)) {
			System.out.println("수정 완료");
		} else {
			System.out.println("수정 실패");
		}
	}

	// 글삭제
	public void boardDelete() {
		System.out.println("삭제할 글 번호를 입력>> ");
		int boardNum = sc.nextInt();
		board = new Board();
		board.setBoardNo(boardNum);
		if (bdao.deleteContent(board)) {
			System.out.println("글삭제 완료");
		} else {
			System.out.println("글삭제 실패");
		}
	}

	// 댓글쓰기
	public void commentInsert(int cno) {
		System.out.println("댓글내용을 입력하세요>>");
		String reply = sc.nextLine();
		Comments com = new Comments();
		String commentsWriter = id;
//		int cno = cno;
		com.setCommentContent(reply);
		com.setCommentWriter(commentsWriter);
		com.setCno(cno);
		if (cdao.insertContent(com)) {
			System.out.println("댓글등록 성공");
		} else {
			System.out.println("댓글등록 실패");
		}
	}

	// 댓글수정
	public void commentUpdate() {
		System.out.println("수정할 댓글번호 선택>> ");
		int updateReply = sc.nextInt();
		sc.nextLine();
		System.out.print("수정할 내용>>");
		String updateComment = sc.nextLine();
		com.setCommentNo(updateReply);
		com.setCommentContent(updateComment);
		if (cdao.updateContent(com)) {
			System.out.println("댓글 수정완료");
		} else {
			System.out.println("댓글 수정실패");
		}
	}

	// 댓글삭제
	public void commentDelete() {
		System.out.println("삭제할 댓글번호 선택>> ");
		int deleteReply = sc.nextInt();
		if (cdao.deleteContent(deleteReply)) {
			System.out.println("댓글 삭제 완료");
		} else {
			System.out.println("댓글 삭제 실패");
		}
	}

}
