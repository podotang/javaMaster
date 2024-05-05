// 추가 클릭이벤트 등록.
//사용자의 입력값 3개 = > tr > td*3  : tr의 하위요소로 td를 3개 만들어라 => tbody하위요소 추가.
document.querySelector('#addMember').addEventListener('click', addMemberfnc);

function addMemberfnc() {
	let num = document.querySelector('#memberNo').value;
	let name = document.querySelector('#memberName').value;
	let point = document.querySelector('#memberPoint').value;
	const mem = { num, name, point }
	let tr = makeRow(mem);
	document.querySelector('table#tlist tbody').appendChild(tr);
}

//member  정보를 활용 tr반환.
function makeRow(member = { num, name, point }) {

	// tr 생성
	let tr = document.createElement('tr');
	//tr click이벤트 등록
	tr.addEventListener('click', function(e) {
		e.stopPropagation();

		// 기존에 선택된 행에 대한 selected 클래스 제거
		let selectedRow = document.querySelector('#tlist tbody tr.selected');
		if (selectedRow) {
			selectedRow.classList.remove('selected');
		}

		// 현재 클릭한 행에 selected 클래스 추가
		tr.classList.add('selected');

		document.querySelector('#memberNo').value
			= tr.children[0].innerHTML;
		document.querySelector('#memberName').value
			= tr.children[1].innerHTML;
		document.querySelector('#memberPoint').value
			= tr.children[2].innerHTML;
	}, false)

	for (let prop in member) {
		let td = document.createElement('td');
		td.innerText = member[prop]; //mem.num
		tr.appendChild(td);
	}
	// <td><button>삭제</button></td>
	let td = document.createElement('td');
	let btn = document.createElement('button');
	btn.innerText = '삭제';
	btn.addEventListener('click', deleteRow);
	td.appendChild(btn);
	tr.appendChild(td);
	// 체크박스
	td = document.createElement('td');
	let chk = document.createElement('input');
	chk.setAttribute('type', 'checkbox');
	/*chk.addEventListener('change',changeRow);*/
	td.appendChild(chk);
	tr.appendChild(td);

	return tr;
} // end makeRow

// 행삭제
function deleteRow(event) {
	//onsole.log(event);
	event.stopPropagation();	// 상하위 요소로 이벤트 차단 => 삭제누를시 인풋에 삭제값올리는거 사라지게해줌
	event.target.parentElement.parentElement.remove();
}

//members 배열의 갯수만큼 tr 생성.
members.forEach(function(item) {
	let tr = makeRow(item); // tr을 만들어주는 함수를 변수에담아줌
	document.querySelector('table#tlist tbody').appendChild(tr);
});

//체크박스
//thead > input[type="checkbox"]
document.querySelector('thead input[type=checkbox]')
	.addEventListener('change', function() {
		//thead > tbody에 적용
		//document.querySelectorAll('tbody>tr>td>input[type = "checkbox"]').
		//아래 함수에 this쓰고싶은데 함수안의 this는 윈도우객체로 변환되어서 현재의 this를 inp에 담아줌
		let inp = this;
		document.querySelectorAll('tbody input[type = "checkbox"]')
			//= .forEach(function(item) => {
			.forEach((item) => { //화살표함수 :윈도우함수가 아니라 원래 this사용가능함
				//console.log(this);
				item.checked = this.checked;
			})
	});


// 수정 
document.querySelector('#updateBtn').addEventListener('click', function() {
	let num = document.querySelector('#memberNo').value;
	let name = document.querySelector('#memberName').value;
	let point = document.querySelector('#memberPoint').value;

	//console.log("수정된 정보:", num, name, point);

	let selectedRow = document.querySelector('#tlist tbody tr.selected');
	if (selectedRow) {
		let cells = selectedRow.querySelectorAll('td');
		cells[0].innerText = num;
		cells[1].innerText = name;
		cells[2].innerText = point;
		cells.forEach(cell => console.log(cell.innerText));
		}
	});



