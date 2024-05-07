//Ajax 기능을 fetch기능을 활용해서 만들어줌 =>DOMContentLoaded가 호출되면 initForm실행하라
//empSvc 객체에 기능을 구현. 메소드 호출
document.addEventListener("DOMContentLoaded", initForm);

function initForm() {
	//Ajax 호출 : 목록출력부분
	fetch('../empJson.json')	//반환결과값이 promise 객체
		.then(result => result.json()) // 출력스트림(json)문자열 -> 객체변환
		.then(data =>
			data.forEach(emp => {
				let tr = makeRow(emp);
				document.querySelector('#elist').appendChild(tr);
			})
		)
		.catch(err => console.log(err));
	// 등록이벤트.
	document.querySelector('#addBtn').addEventListener('click', addRow);
}// end of initForm

//
function makeRow(emp = {}) {
	let props = ['empNo', 'empName', 'email', 'salary']
	let tr = document.createElement('tr');
	//attribute 속성!
	tr.setAttribute('data-no', emp.empNo)
	tr.addEventListener('dblclick', modifyRow);
	props.forEach(prop => {
		let td = document.createElement('td');
		td.innerHTML = emp[prop];
		tr.appendChild(td);
	});
	let td = document.createElement('td');
	let btn = document.createElement('button');
	btn.innerHTML = '삭제';
	btn.addEventListener('click', deleteRow);
	td.appendChild(btn);
	tr.appendChild(td);
	return tr;
}// end of makeRow();

//삭제이벤트 (완료)
function deleteRow() {
	//this 버튼이벤트를 받는 대상 -> 데이터의 사번부분
	let eno = this.parentElement.parentElement.dataset.no;
	let tr = this.parentElement.parentElement;
	fetch('../empsave.json?job=delete&empNo=' + eno)
		.then(restult => restult.json())
		.then(data => {
			if (data.returnCode == 'OK') {
				tr.remove();
			} else if (data.returnCode == 'NG') {
				alert('처리실패!');
			}
		})
		.catch(err => console.log(err));
} // end of deleteRow

//등록이벤트 (완료)
function addRow() {
	//db insert & 화면출력
	//사원이름(ename), 연락처(phone),(email),(hire), 급여(salary)
	let ename = document.querySelector('#ename').value;
	let ephone = document.querySelector('#ephone').value;
	let ehire = document.querySelector('#ehire').value;
	let esalary = document.querySelector('#esalary').value;
	let email = document.querySelector('#email').value
	let param = 'job=add&name=' + ename + '&phone=' + ephone + '&salary=' + esalary + '&hire=' + ehire + '&email=' + email;
	//파라미터 여러개 넘길때 &엔퍼센트 써주기!
	//페치두번째 옵션객체넣기
	fetch('../empsave.json', {
		method: 'post',
		headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
		body: param
	})
		.then(result => result.json())
		.then(data => {
			if (data.returnCode == 'OK') {
				let tr = makeRow(data.retVal);
				document.querySelector('#elist').appendChild(tr);
			}
		})
		.catch(console.log);
} // end of addRow

//	화면수정 
function modifyRow() {
	let originMail = this.children[2].innerText;
	let originSalary = this.children[3].innerText;
	let oldTr = this;
	// cloneNode!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! true넣어야 하위요소 다 가져옴 
	let newTr = this.cloneNode(true);
	newTr.querySelector('td:nth-of-type(3)').innerHTML = '<input value ="' + originMail + '">'
	newTr.querySelector('td:nth-of-type(4)').innerHTML = '<input value ="' + originSalary + '">'
	newTr.querySelector('button').innerText = '수정';
	newTr.querySelector('button').addEventListener('click', updateRow);
	console.log(newTr);
	oldTr.parentElement.replaceChild(newTr, oldTr);
}

// db값 수정 => Ajax호출 => 혼자해봄....... (완료)
function updateRow() {
	let oldTr = this.parentElement.parentElement;
	let empNo = this.parentElement.parentElement.dataset.no;	// data-no => dataset.no
	let email = this.parentElement.parentElement.children[2].children[0].value;
	let salary = this.parentElement.parentElement.children[3].children[0].value;
	console.log(empNo, email, salary);
	let param = 'job=edit&empNo=' + empNo + '&salary=' + salary + '&email=' + email;
	
	fetch('../empsave.json',{
		method: 'post',
		headers:{ 'Content-Type': 'application/x-www-form-urlencoded' },
		body: param
	})
	.then(result => result.json())
	.then(data=> {
		if (data.returnCode == 'OK') {
			let newTr = makeRow(data.retVal);
			oldTr.parentElement.replaceChild(newTr, oldTr);
		}
	})
	.catch(console.log)
}


