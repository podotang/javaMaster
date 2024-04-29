package co.yedam;

import java.sql.SQLSyntaxErrorException;
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
			System.out.println("====================================================================");
			System.out.println("1.로그인하기 2.회원가입하기");
			System.out.println("====================================================================");

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
		login();
	}
}
