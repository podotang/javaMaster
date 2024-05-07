document.addEventListener("DOMContentLoaded", initForm);
//화면로딩 후 처음 실행할 함수
function initForm() {
	//Ajax 호출
	const xhtp = new XMLHttpRequest();
	xhtp.open('get', '../empJson.json');
	xhtp.send();
	xhtp.onload = function() {
		let data = JSON.parse(xhtp.responseText);
		console.log(data);
		data.forEach(emp => {
			let tr = makeRow(emp);
			document.querySelector('#elist').appendChild(tr);
		})
	}
	//등록버튼 이벤트
	document.querySelector('#addBtn').addEventListener('click', addRow);
} // end of initForm

function addRow() {
	//db insert & 화면출력
	const addhtp = new XMLHttpRequest();
	//사원이름(ename), 연락처(phone),(email),(hire), 급여(salary)
	let ename = document.querySelector('#ename').value;
	let ephone = document.querySelector('#ephone').value;
	let ehire = document.querySelector('#ehire').value;
	let esalary = document.querySelector('#esalary').value;
	let email = document.querySelector('#email').value
	//파라미터 여러개 넘길때 &엔퍼센트 써주기!
	let param = '../empsave.json?job=add&name=' + ename + '&phone=' + ephone + '&salary=' + esalary + '&hire=' + ehire + '&email=' + email;
	addhtp.open('get', param);
	addhtp.send();
	addhtp.onload = function() {
		let result = JSON.parse(addhtp.responseText);
		console.log(result);
		if (result.returnCode == 'OK') {
			let tr = makeRow(result.retVal);
			document.querySelector('#elist').appendChild(tr);
		}
	}
}

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

// db값 수정 => Ajax호출
function updateRow() {
	let oldTr = this.parentElement.parentElement;
	let empNo = this.parentElement.parentElement.dataset.no;	// data-no => dataset.no
	let email = this.parentElement.parentElement.children[2].children[0].value;
	let salary = this.parentElement.parentElement.children[3].children[0].value;
	console.log(empNo, email, salary);
	const editHtp = new XMLHttpRequest();
	editHtp.open('get', '../empsave.json?job=edit&empNo=' + empNo + '&salary=' + salary + '&email=' + email);
	editHtp.send();
	editHtp.onload = function() {
		let result = JSON.parse(editHtp.responseText);
		console.log(result);
		console.log(oldTr);
		//oldTr, newTr 이용해서 수정누르면 다시 인풋이던게 원래로 돌아가기
		if (result.returnCode == 'OK') {
			let newTr = makeRow(result.retVal);
			oldTr.parentElement.replaceChild(newTr, oldTr);
		}	}
}


function deleteRow() {
	//이벤트핸들러에서 이벤트 대상은 click되는 버튼임!
	const delNo = this.parentElement.parentElement.children[0].innerText;
	let tr = this.parentElement.parentElement;
	console.log(delNo);
	//서블릿실행(삭제) -> 반환ok -> 화면처리
	//Ajax 호출
	const delHtp = new XMLHttpRequest();
	delHtp.open('get', '../empsave.json?job=delete&empNo=' + delNo);
	delHtp.send();
	delHtp.onload = function() {
		let result = JSON.parse(delHtp.responseText); //성공하면 returnCode: OK
		if (result.returnCode == 'OK') {
			tr.remove();
		} else if (result.returnCode == 'NG') {
			alert('처리중 에러발생')
		} else {
			alert('처리코드를 확인하세요')
		}
	}
}

















