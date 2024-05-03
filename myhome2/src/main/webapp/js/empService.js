//empServie => 목록, 추가, 수정, 삭제 기능 객체
// 중복코드 줄이기위해 생성중
const svc = {
	// 목록.
	empList: function(successCall, errorCall) {
		fetch('../empJson.json')
			.then(result => result.json())
			.then(successCall)
			.catch(errorCall);
	},
	// 등록. 키벨류말고 메소드형식으로 넣어도됨
	// 파람{} 초기값설정하는것임
	addEmp(param = {}, successCall, errorCall) {
		fetch('../empsave.json', {
			method: 'post',
			headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
			body: 'job=add&name=' + param.name + '&phone=' + param.phone + '&salary=' + param.salary + '&hire=' + param.hire + '&email=' + param.email
		})
			.then(result => result.json())
			.then(successCall)
			.catch(errorCall)
	},
	// 수정.
	editEmp(param = {}, successCall, errorCall) {
		fetch('../empsave.json', {
			method: 'post',
			headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
			body: 'job=edit&empNo=' + empNo + '&salary=' + salary + '&email=' + email
		})
			.then(result => result.json())
			.then(successCall)
			.catch(errorCall);
	},
	//삭제
	deleteEmp(eno =1, successCall, errorCall) {
		fetch('../empsave.json?job=delete&empNo=' + eno)
			.then(restult => restult.json())
			.then(successCall)
			.catch(errorCall)
	}


}