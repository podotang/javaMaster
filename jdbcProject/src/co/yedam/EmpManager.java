package co.yedam;

import java.util.List;
import java.util.Scanner;

public class EmpManager {

	public static void main(String[] args) {
		//Scanner
		Scanner sc = new Scanner(System.in);
		boolean run = true;
		EmpDao dao = new EmpDao();
		
		while(run) {
			System.out.println("1.사원목록 2.사원등록 3.정보수정 4.사원삭제 5.종료");
			System.out.print("선택 > ");
			int menu = Integer.parseInt(sc.nextLine());
			
			switch(menu) {
			case 1:
				//조회
				List<Employee> emps = dao.empList();
				for(Employee emp : emps) {
					System.out.println(emp.showInfo());
				}
//				dao.empList();
				break;
			case 2:
				//등록
				System.out.print("사원명>> ");
				String name = sc.nextLine();
				System.out.print("연락처>> ");
				String phone = sc.nextLine();
				System.out.print("이메일>> ");
				String mail = sc.nextLine();
				System.out.print("급여>> ");
				String salary = sc.nextLine();
				System.out.print("입사일자>> ");
				String hdate = sc.nextLine(); // 2024-05-02
//				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				
				Employee emp = new Employee();
				emp.setEmpName(name);
				emp.setEmail(mail);
				emp.setPhone(phone);
				emp.setSalary(Integer.parseInt(salary));
				emp.setHireDate(hdate);
				
				
				if(dao.insertEmp(emp)) {
					System.out.println("등록완료");
				}else {
					System.out.println("등록실패");
				}
				
				break;
			case 3:
				//수정
				System.out.print("사원번호>> ");
				String eno = sc.nextLine();
				
				//여기 이메일 빈값들어오면 기존값 유지시키는 조건문쓸거임 length로
//				if() {
//					
//				}
				
				System.out.print("이메일>> ");
				mail = sc.nextLine();
				System.out.print("급여>> ");
				salary = sc.nextLine();
				
				emp = new Employee();
				emp.setEmpNo(Integer.parseInt(eno));
				emp.setEmail(mail);
				emp.setSalary(Integer.parseInt(salary));
								
				if(dao.updateEmp(emp)) {
					System.out.println("수정완료");
				}else {
					System.out.println("수정실패");
				}
				break;
				
			case 4:
				//삭제
				System.out.println("삭제할 사원의 번호>>");
				eno = sc.nextLine();
				
				emp = new Employee();
				emp.setEmpNo(Integer.parseInt(eno));
				
				if(dao.deleteEmp(emp)) {
					System.out.println("등록완료");
				}else {
					System.out.println("등록실패");
				}
				System.out.println("사원삭제");
				break;
			case 5:
				//종료
				System.out.println("종료");
				run = false;
				break;
			}
		
		}
		
		
		
		
		System.out.println("end of prog.");
	}

}










