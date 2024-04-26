package co.yedam;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class BoardProc {
	Scanner sc = new Scanner(System.in);
	BoardDAO bdao = new BoardDAO();
	CommentsDAO cdao = new CommentsDAO();
	String id;
	Board board = new Board();
	Comments com = new Comments();

	public BoardProc(String id) {
		this.id = id;
	}

	// 글작업.
	void boarding() {
		boolean run = true;
		while (run) {
			try {
				System.out.println("1.게시판목록 2.글쓰기 3.글수정하기 4.삭제 5.개인정보수정 6.로그아웃");
				System.out.print("선택>>");
				int chooseN = sc.nextInt();
				sc.nextLine();

				switch (chooseN) {
				case 1:
					// 글목록
					boardList();
					break;

				case 2:
					// 글작성하기
					boardWriter();
					break;

				case 3:
					// 글수정
					// 수정하고 상세보기
					boardUpdate();
					break;

				case 4:
					// 글삭제
					boardDelete();
					break;

				case 5:
					// 회원정보수정 , 회원탈퇴
					editMember();
					break;

				case 6:
					// 종료
					System.out.println("로그아웃합니다");
					login();
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("잘못된 입력값입니다 프로그램을 종료합니다.");
				break;
			}
		}
	} // end of boarding.

	void editMember() {
		MemberProc mproc = new MemberProc(id);
		mproc.editMember();
	}

	void login() {
		MainProc mainproc = new MainProc();
		mainproc.login();
	}

	// 글목록
	public void boardList() {
		int page = 1;
		System.out.print("찾으시는 글 제목을 입력하세요>>");
		String title = sc.nextLine();
		while (true) {
			// 전체글개수
			int totalArticle = bdao.boardCount(title);
			System.out.println("====================================================================");
			System.out.println("글번호		글제목	 글쓴이		작성일	");
			System.out.println("--------------------------------------------------------------------");
			List<Board> InfoList = bdao.boardList(title, page);

			int currentpage = page;
			int lastPage = 0;
			if (totalArticle % 20 != 0) {
				lastPage = totalArticle / 20 + 1;
			}

			for (Board search : InfoList) {
				System.out.println(search.showSearch());
			}

			for (int i = 1; i <= lastPage; i++) {
				if (currentpage == i) {
					System.out.printf("%d", i);
				} else {
					System.out.printf("[%d]", i);
				}
			}

			System.out.print("상세보기(글번호) 이전(p) 다음(n) \n (메인으로 가려면 0번)>> ");
			int selectedBoardNo = 0;
			String choice = null;
			try {
				choice = sc.nextLine();
				selectedBoardNo = Integer.parseInt(choice);// 개행문자 처리
				if (selectedBoardNo == 0) {
					return;
				} else {
					boardDetail(selectedBoardNo);
					// return;
				}
			} catch (NumberFormatException e) {
				if (choice.equals("n")) {
					page++;
				} else if (choice.equals("p")) {
					page--;
				}
			}
		}
	}

	// 글상세
	public void boardDetail(int selectedBoardNo) {
		Board selectedBoard = bdao.boardInfo(selectedBoardNo);
		boolean run = true;
		while (run) {
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
				System.out.println("댓글번호	  내용	  	 	 글쓴이			작성일");

				List<Comments> clist = cdao.commentList(selectedBoardNo);
				for (Comments com : clist) {
					System.out.println(com.showComments());
				}

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

			} else {
				System.out.println("해당하는 글이 없습니다.");
			}
			// 본인이 단 댓글이 있으면 댓글 수정,삭제 보이기
		}
	}

	// 글쓰기 - 완료
	public void boardWriter() {
		System.out.print("글제목>>");
		String boardTitle = sc.nextLine();
		System.out.print("글내용>>");
		String boardContent = sc.nextLine();
		// 로그인한 아이디 정보
		String boardWriter = id;
		Board board = new Board();
		board.setBoardTitle(boardTitle);
		board.setBoardContent(boardContent);
		board.setBoardWriter(boardWriter);
		if (bdao.insertContent(board)) {
			System.out.println("글 등록이 완료되었습니다.");
			System.out.println("--------------------------------------------------------------------");
			System.out.println(bdao.boardInfo(board.getBoardNo()).showSearch());
			System.out.println("--------------------------------------------------------------------");
			return;
		} else {
			System.out.println("글 등록 실패");
		}
	}

	// 글수정
	public void boardUpdate() {
		System.out.println("수정할 글 번호를 선택하세요>>");
		int chooseNum = sc.nextInt();
		sc.nextLine();
		Board board = bdao.boardInfo(chooseNum);
		if (!board.getBoardWriter().equals(id)) {
			System.out.println("작성자만 글을 수정할 수 있음");
			return;
		}

		System.out.print("제목수정>> ");
		String boardTitle = sc.nextLine();
		System.out.print("내용수정>> ");
		String boardContent = sc.nextLine();

		board.setBoardNo(chooseNum);
		board.setBoardTitle(boardTitle);
		board.setBoardContent(boardContent);

		if (bdao.updateContent(board)) {
			System.out.println("수정 완료");
			System.out.println(bdao.boardInfo(board.getBoardNo()).showSearch());
		} else {
			System.out.println("수정 실패");
		}

	}

	// 글삭제
	public void boardDelete() {
		System.out.println("삭제할 글 번호를 입력>> ");
		int boardNum = sc.nextInt();
		sc.nextLine();
//		board = new Board();
		Board board = bdao.boardInfo(boardNum);

		if (!board.getBoardWriter().equals(id)) {
			System.out.println("작성자만 글을 삭제할 수 있음");
			return;
		}

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
//			int cno = cno;
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
		sc.nextLine();
		if (cdao.deleteContent(deleteReply)) {
			System.out.println("댓글 삭제 완료");
		} else {
			System.out.println("댓글 삭제 실패");
		}
	}

}
