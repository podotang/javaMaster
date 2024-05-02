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

makeList(empList);
// 함수(배열)
function makeList() {
	let props = ['id', 'first_name', 'email', 'salary']
	let ary = {'id': 1 ,'first_name': '',email:'',salary:''}
	let tr = document.createElement('tr');
	document.querySelector('#show tbody').innerHTML = '';	// 기존목록지우기
	ary.forEach(emp => {
		props.forEach((prop) => {
			let td = document.createElement('td');
			td.innerHTML = emp[prop];
			tr.appendChild('td');
		})
		document.querySelector('#show tbody').appendChild(tr);
	})

}

document.querySelector('#genderList').addEventListener('change', function() {
	let filterAry = empList.filter(emp => emp.gender == this.value);
	makeList(filterAry);
})




