package co.yedam;

public class AppTest {
	public static void main(String[] args) {
		BoardDAO dao = new BoardDAO();
		dao.boardList("첫번째").forEach(board->System.out.println(board.showBoardList()));
	}
}
