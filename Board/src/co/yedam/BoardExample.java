package co.yedam;

import java.util.List;
import java.util.Scanner;

public class BoardExample {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		boolean run = true;
		BoardDAO dao = new BoardDAO();

		while (run) {
			System.out.println("1.게시판목록 2.제목검색 3.글쓰기 4.글수정하기 5.설정 6.종료");
			System.out.print("선택>>");
			int chooseN = sc.nextInt();

			switch (chooseN) {
			case 1:
				// 글목록

				List<Board> list = dao.boardList(null);
				System.out.println("====================================================================");
				System.out.println("글번호		글제목	 글쓴이		작성일");
				System.out.println("--------------------------------------------------------------------");

				for (Board boardL : list) {
					System.out.println(boardL.showBoardList());
				}

				break;
			case 2:
				// 글검색
				// 댓글관련포함
//				String title = null;
				
				while (true) {
					System.out.print("제목을 입력하세요>>");
					String title = sc.nextLine();
					sc.nextLine();
					title = title.trim();
										
//					if (title.isEmpty()) {
//						System.out.print("값을 넣으세요");
//					}
					
					List<Board> InfoList = dao.boardList(title);
					
					if (InfoList.isEmpty() || title == null) {
						System.out.println("검색한 제목에 해당하는 글이 없습니다.");
			            System.out.println("전체 목록을 출력합니다.");
			            List<Board> allList = dao.boardList("");
						for (Board board : allList) {
							System.out.println(board.showSearchList());
						}
					} else {
						for (Board search : InfoList) {
							System.out.println(search.showSearchList());
						}
					}
					break;
				}
				

				// 검색목록중 상세보기할 글번호선택 => 댓글같이 보여주기
				Board board = new Board();
				System.out.print("선택>> ");
				int boardNo = sc.nextInt();
				
				board.setBoardNo(boardNo); 
				dao.listInfo(board);
				
				// 글상세 안에서 댓글쓰기, 수정하기, 이전메뉴
				System.out.println(" 1.댓글쓰기 2.댓글수정 3.이전메뉴");
				System.out.print("선택>> ");
				int comNum = sc.nextInt();
//				dao.
				
				

				break;
			case 3:
				// 글쓰기		
				System.out.print("글제목>>");
				String boardTitle = sc.nextLine();
				System.out.print("글내용>>");
				String boardContent = sc.nextLine();
				System.out.print("작성자>>");
				String boardWriter = sc.nextLine();
				
				board = new Board();
				board.setBoardTitle(boardTitle);
				board.setBoardContent(boardContent);
				board.setBoardWriter(boardWriter);

				if(dao.insertContent(board)) {
					System.out.println("글 등록이 완료되었습니다.");
				}else {
					System.out.println("글 등록 실패");
				}
				break;
				
			case 4:
				// 글수정하기
				System.out.print("제목수정>> ");
				boardTitle = sc.nextLine();
				System.out.print("내용수정>> ");
				boardContent = sc.nextLine();
				
				board = new Board();
				board.setBoardTitle(boardTitle);
				board.setBoardContent(boardContent);
				
				if(dao.updateContent(board)) {
					System.out.println("글 수정 완료입니다.");
				}else {
					System.out.println("수정실패");
				}
				break;
				
			case 5:
				// 글삭제
				System.out.println("삭제할 글 번호를 입력>> ");
				String boardNum = sc.nextLine();
				
				board = new Board();
				board.setBoardNo(Integer.parseInt(boardNum));
				
				if(dao.deleteContent(board)) {
					System.out.println("글삭제 완료");
				}else {
					System.out.println("삭제 실패");
				}
				break;
			
			case 6:
				// 설정
				// 로그인 회원가입 아이디/비번수정 회원탈퇴
				System.out.println("1. 로그인 2. 회원가입 3. 회원정보 수정 4. 회원탈퇴");
				int memberSet = sc.nextInt();
				
				switch(memberSet) {
				
				case 1:
					
					System.out.println("로그인 완료");
					break;
				case 2:
					System.out.println("회원가입 완료");
					break;
				case 3:
					System.out.println("정보수정 완료");
					break;
				case 4:
					System.out.println("정말 탈퇴 하시겠습니까?");
					System.out.println("회원탈퇴 완료");
					break;
					
				}
				

				break;
			case 7:
				// 종료
				System.out.println("도서조회 서비스를 종료합니다");
				run = false;
				break;

			}

		}

	}

}
