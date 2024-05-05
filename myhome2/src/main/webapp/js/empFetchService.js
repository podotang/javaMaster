//
//empSvc 객체에 기능을 구현. 메소드 호출
document.addEventListener("DOMContentLoaded", initForm);

function initForm() {
	//Ajax 호출 : 목록출력부분
	//첫값 성공함수 두번째 실패시함수
	svc.empList(result => {
		result.forEach(emp => {
			let tr = makeRow(emp);
			document.querySelector('#elist').appendChild(tr);
		}) // forEach 
		document.querySelector('thead input[type=checkbox]')
			.addEventListener('change', function() {
				document.querySelectorAll('tbody input[type=checkbox]')
					.forEach((item) => {
						item.checked = this.checked
					})
			});
	},//successCall
		err => console.log(err) //errorCall
	);

	/*	fetch('../empJson.json')	//반환결과값이 promise 객체
			.then(result => result.json()) // 출력스트림(json)문자열 -> 객체변환
			.then(data =>
				data.forEach(emp => {
					let tr = makeRow(emp);
					document.querySelector('#elist').appendChild(tr);
				})
			)
			.catch(err => console.log(err));*/
	// 등록이벤트.
	document.querySelector('#addBtn').addEventListener('click', addRow);
	document.querySelector('#delBtn').addEventListener('click', deleteChecked);

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


	td = document.createElement('td');
	let chk = document.createElement('input');
	chk.setAttribute('type', 'checkbox');
	td.appendChild(chk);
	tr.appendChild(td);
	return tr;
}// end of makeRow();


//삭제이벤트 (완료)
function deleteRow() {
	//this 버튼이벤트를 받는 대상 -> 데이터의 사번부분
	let eno = this.parentElement.parentElement.dataset.no;
	let tr = this.parentElement.parentElement;
	console.log(eno);
	svc.deleteEmp(eno,
		data => {
			if (data.returnCode == 'OK') {
				tr.remove();
			}
		},
		err => console.log(err)
	)
} // end of deleteRow

function deleteChecked() {
	let checkedRows = document.querySelectorAll('input[type="checkbox"]:checked');
	checkedRows.forEach(checkbox => {
		let eno = checkbox.parentElement.parentElement.dataset.no;
		svc.deleteEmp(eno,
			data => {
				if (data.returnCode == 'OK') {
					checkbox.parentElement.parentElement.remove();
				}
			},
			err => console.log(err)
		)
	});
}

//등록이벤트 (완료)
function addRow() {
	//db insert & 화면출력
	//사원이름(ename), 연락처(phone),(email),(hire), 급여(salary)
	let paramObj = {
		job: 'add',
		name: document.querySelector('#ename').value,
		phone: document.querySelector('#ephone').value,
		salary: document.querySelector('#esalary').value,
		hire: document.querySelector('#ehire').value,
		email: document.querySelector('#email').value
	} //등록 param.

	svc.addEmp(paramObj,
		data => {
			if (data.returnCode == 'OK') {
				let tr = makeRow(data.retVal);
				document.querySelector('#elist').appendChild(tr);
			}
		},
		err => console.log(err) //errorCall
	)
}
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
	//속성과 값같을때 : 안쓰고 하나만써도됨
	let paramObj = {
		empNo,
		salary,
		email
	}

	svc.editEmp(paramObj,
		data => {
			if (data.returnCode == 'OK') {
				let newTr = makeRow(data.retVal);
				oldTr.parentElement.replaceChild(newTr, oldTr);
			}
		},
		err => console.log(err)
	)

}


