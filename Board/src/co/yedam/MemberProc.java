package co.yedam;

import java.util.Scanner;

public class MemberProc {
	String id;
	Scanner sc = new Scanner(System.in);
	MemberDAO mdao = new MemberDAO();
	Member member = new Member();

	public MemberProc(String id) {
		this.id = id;
	}

	// 회원삭제
	public void memberDelete() {
		System.out.println("정말 탈퇴하시나요?(y/n)");
		String deleteMem = sc.nextLine();

		if (deleteMem.equals("y")) {
			member.setMember_id(id);
			mdao.deleteMember(id);
			System.out.println("탈퇴완료");
		} else if (deleteMem.equals("n")) {
			System.out.println("탈퇴취소");
			return;
		}
	}

	// 회원정보 수정
	public void editMember() {
		System.out.println("1.회원정보 수정 2.회원탈퇴");
		int memberSet = sc.nextInt();
		sc.nextLine();
		switch (memberSet) {
		case 1:
			// 로그인된 본인의 아이디나 비밀번호 수정
			System.out.println("1.이메일수정 2.비밀번호수정");
			int editIdPw = sc.nextInt();
			sc.nextLine();

			switch (editIdPw) {

			case 1:
				System.out.println("새로운 이메일>>");
				String newEmail = sc.nextLine();
				member.setMemberEmail(newEmail);
				if (mdao.editEmail(member, id)) {
					System.out.println("이메일 수정완료");
				}
				break;
			case 2:
				System.out.println("새로운 비밀번호>>");
				String newPw = sc.nextLine();
				member.setMember_pw(newPw);
				if (mdao.editPw(member, id)) {
					System.out.println("비밀번호 수정완료");
				}
				break;
			}

			break;
		case 2:
			// 로그인된 상태에서 자신것만 없앰
			memberDelete();
			break;
		}
	}
}
