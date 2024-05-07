
document.addEventListener("DOMContentLoaded", () => {
	loadData();

	//체크박스
	document.querySelector('thead input[type=checkbox]')
		.addEventListener('change', function() {
			document.querySelectorAll('tbody input[type = "checkbox"]')
				.forEach((item) => {
					item.checked = this.checked;
				})
		});
	document.querySelector('#save').addEventListener('click', addRow);
	document.querySelector('#delete').addEventListener('click', deleteSelectedRows);
});

function loadData() {
	fetch('data/data.json')
		.then(response => response.json())
		.then(data => {
			data.forEach(book => {
				let tr = makeRow(book);
				document.querySelector('#tbody').appendChild(tr);
			});
		})
		.catch(err => console.error('Error loading data:', err));
}

function makeRow(board = {}) {
	let props = ['code', 'book_name', 'author_name', 'press', 'price']
	let tr = document.createElement('tr');
	//attribute 속성!
	tr.setAttribute('data-no', board.code)
	// 체크박스를 위한 새로운 td 요소 생성
	let checkboxTd = document.createElement('td');
	let checkbox = document.createElement('input');
	checkbox.type = 'checkbox';
	checkboxTd.appendChild(checkbox);
	tr.appendChild(checkboxTd); // 체크박스를 행의 맨 앞에 추가
	props.forEach(prop => {
		let td = document.createElement('td');
		td.innerHTML = board[prop];
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

function addRow() {
	let book = {
		code: document.querySelector('#code').value,
		book_name: document.querySelector('#bookname').value,
		author_name: document.querySelector('#writer').value,
		press: document.querySelector('#publisher').value,
		price: document.querySelector('#amount').value
	};
	document.querySelector('#tbody').appendChild(makeRow(book));
}

function deleteRow(button) {
	let tr = this.parentElement.parentElement;
	console.log(tr);
	tr.remove();
}

function deleteSelectedRows() {
	document.querySelectorAll('#tbody input[type="checkbox"]:checked').forEach(checkbox => {
		checkbox.parentNode.parentNode.remove();
	});
}

