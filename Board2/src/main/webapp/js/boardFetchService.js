document.addEventListener("DOMContentLoaded", initForm);

function initForm() {
	svc.boardList(result => {
		result.forEach(board => {
			let tr = makeRow(board);
			document.querySelector('#blist').appendChild(tr);
		});

	},
		err => console.log(err) //errorCall
	);
	document.querySelector('#addBtn').addEventListener('click',addRow);
}

function makeRow(board = {}) {
	let props = ['boardNo', 'boardTitle', 'boardWriter', 'modifiedDate']
	let tr = document.createElement('tr');
	tr.addEventListener('click', boardDetail);
	tr.setAttribute('data-no', board.boardNo);
	props.forEach(prop=>{
		let td = document.createElement('td');
		td.innerHTML = board[prop]
		tr.appendChild(td);
	});
		return tr;
}

function boardDetail(){
	//클릭한 tr의 parentelement.parentelement에 서 첫번째값 글번호로 그 글번호에 해당하는 
	// 제목 내용 작성자 작성시간 가져오기
	
	
}



function addRow(){
		let paramObj = {
		job: 'add',
		name: document.querySelector('#title').value,
		phone: document.querySelector('#content').value,
		salary: document.querySelector('#writer').value,
	} //등록 param.

	svc.addBoard(paramObj,
		data => {
			if (data.retCode == 'OK') {
				let tr = makeRow(data.retVal);
				document.querySelector('#blist').appendChild(tr);
			}
		},
		err => console.log(err) //errorCall
	)
}


