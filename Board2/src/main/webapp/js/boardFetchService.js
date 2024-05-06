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
	tr.setAttribute('data-no', board.boardNo);
	props.forEach(prop=>{
		let td = document.createElement('td');
		td.innerHTML = board[prop]
		tr.appendChild(td);
	});
		return tr;

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


