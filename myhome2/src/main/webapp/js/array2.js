let genderAry = [];
empList.forEach(emp => {
	if (genderAry.indexOf(emp.gender) == -1) {
		genderAry.push(emp.gender);
	}
});

genderAry.forEach(gender => {
	let opt = document.createElement('option');
	opt.innerHTML = gender;
	document.querySelector('#genderList').appendChild(opt);
});

document.querySelector('#genderList').addEventListener('change', updateList)

function updateList() {
	let allGender = document.querySelector('#genderList').value;
	let selectedGender = empList.filter(emp => emp.gender == allGender);
	makeList(selectedGender);
}

// 함수(배열)
function makeList(ary = []) {
	let tbody = document.querySelector('#show tbody');
	tbody.innerHTML = '';

	let props = ['id', 'first_name', 'email', 'salary']
	ary.forEach(emp => {
		let tr = document.createElement('tr');
		props.forEach(prop => {
			let td = document.createElement('td');
			td.innerHTML = emp[prop];
			tr.appendChild(td);
		})
		tbody.appendChild(tr); // 행을 테이블에 추가
	})
}

makeList(empList);






