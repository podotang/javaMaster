package co.yedam;

import java.util.Scanner;

public class MainProc {
	Scanner sc = new Scanner(System.in);

	Member member = new Member();
	MemberDAO mdao = new MemberDAO();

	String id;
	String pw;
	String y;
	String n;

	boolean success = false;

	// 제일큰곳
	public void exe() {
		boolean sign = true;
		while (sign) {
			System.out.println("1.로그인하기 2.회원가입하기");
			int login = sc.nextInt();
			sc.nextLine();
			switch (login) {
			case 1:
				login();
				sign = false;
				break;
			case 2:
				join();
				break;
			}
		}

	}// end of exe.

	// 페이징 처리
	// m:총개시글 n:한페이지에 보여줄갯수
	static int getTotalPage(int m, int n) {
		if (m % n == 0) {
			return m / n;
		} else {
			return m / n + 1;
		}
	}

	public static void main(String[] args) {
		System.out.println(getTotalPage(5, 10)); // 1 출력
		System.out.println(getTotalPage(15, 10)); // 2 출력
		System.out.println(getTotalPage(25, 10)); // 3 출력
		System.out.println(getTotalPage(30, 10)); // 3 출력
	}

	// 로그인하기
	public void login() {
		for (int i = 0; i < 3; i++) {
			System.out.print("id>>");
			id = sc.nextLine();
			System.out.print("pw>>");
			String pw = sc.nextLine();
			if (!mdao.loginCheck(id, pw)) {
				if (i == 2) {
					System.out.println("로그인 실패입니다.");
					join();
//					return;
				}
				System.out.println("id, pw 확인하세요");
			} else {
				success = true;
				System.out.println("로그인 성공");
				break;
			}
		} // 3번반복후 탈출

		BoardProc bproc = new BoardProc(id);
		bproc.boarding();

	}

	// 회원가입
	public void join() {
		System.out.println("==================회원가입==================");
		System.out.println("아이디를 입력하세요");
		String id = sc.nextLine();
		System.out.println("비밀번호를 입력하세요");
		String pw = sc.nextLine();
		System.out.println("이메일을 입력하세요");
		String email = sc.nextLine();
		Member member = new Member();
		member.setMember_id(id);
		member.setMember_pw(pw);
		member.setMemberEmail(email);
		if (mdao.signup(member)) {
			System.out.println("회원가입이 완료되었습니다.");
		} else {
			System.out.println("회원가입 실패");
		}
	}
}
