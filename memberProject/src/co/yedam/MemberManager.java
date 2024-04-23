package co.yedam;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class MemberManager {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean run = true;
		MemberDao dao = new MemberDao();
		
		while(run) {
			System.out.println("1.회원목록 2.회원등록 3.정보수정 4.정보삭제 5.종료");
			System.out.print("번호를 선택하세요>>");
			int cho = Integer.parseInt(sc.nextLine());
			
		switch(cho) {
		case 1:
			List<Member> semps = dao.sempList();
			System.out.println("회원번호 회원명    회원연락처 	회원생일 	성별");
			System.out.println("--------------------------------------------");
			for(Member semp : semps) {
				System.out.println(semp.showInfo());
			}
			break;
			
		case 2:
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			System.out.print("회원명>>");
			String name = sc.nextLine();
			System.out.print("연락처>>");
			String phone = sc.nextLine();
			System.out.print("생일>>");
			String birth = sc.nextLine();
			System.out.print("성별>>");
			String gender = sc.nextLine();
			
			Member mb = new Member();
			mb.setSempName(name);
			mb.setSempPhone(phone);
			mb.setSempBirth(birth);
			mb.setSempGen(gender);
							
			if(dao.insertSemp(mb)) {
				System.out.println("회원 등록 완료");
			}else {
				System.out.println("등록 실패");
			}
			
			break;
		case 3:
			System.out.println("수정할 회원번호>> ");
			String sempNo = sc.nextLine();
			System.out.println("연락처>> ");
			phone = sc.nextLine();
			mb = new Member();
			mb.setSempNo(Integer.parseInt(sempNo));
			
			if(dao.updateSemp(mb)) {
				System.out.println("수정완료");
			}else {
				System.out.println("수정실패");
			}
			break;
			
			
		case 4:
			System.out.print("삭제할 회원번호>>");
			sempNo = sc.nextLine();
			
			mb = new Member();
			mb.setSempNo(Integer.parseInt(sempNo));
			
			if(dao.deleteSemp(mb)) {
				System.out.println("삭제완료");
			}else {
				System.out.println("삭제실패");
			}
			break;
			
		case 5:
			System.out.println("시스템 종료합니다.");
			run = false;
			break;	
		}
		
		
		}

	}


}























