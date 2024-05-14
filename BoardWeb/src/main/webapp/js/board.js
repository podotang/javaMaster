
//수정버튼
document.querySelector('#modBtn').addEventListener('click', function() {
	document.forms.myForm.action = "modBoardForm.do"; //수정화면 호출
	document.forms.myForm.submit();
})

//삭제버튼
document.querySelector('.btn-danger').addEventListener('click', function() {
	document.forms.myForm.action = "removeBoard.do"; //삭제화면 호출
	document.forms.myForm.submit();
})

// 댓글목록 출력
//const bno = 165; 여기서 값을 읽어올수 없음
let page = 1;
console.log('bno:', bno);
showList();

function showList() {
	
	//댓글목록을 초기화
	document.querySelectorAll('div.content ul li').forEach((li, idx) => {
		if (idx >= 3) {
			li.remove();
		}
	})

	fetch('replyList.do?bno=' + bno + '&page=' + page)
		.then(resolve => resolve.json())	//json ->객체
		.then(result => {
			console.log(result);
			result.forEach(reply => {
				const row = makeRow(reply);
				document.querySelector('div.reply ul').appendChild(row);
			})
			createPageList();
		})
		.catch(err => {
			console.log(err);
		})
}


//삭제버튼의 이벤트
function deleteRow(e) {
	const rno = e.target.parentElement.parentElement.dataset.rno;
	const tr = e.target.parentElement.parentElement;

	//fetch 삭제 기능 구현
	fetch('removeReply.do?rno=' + rno)
		.then(result => result.json())
		.then(data => {
			if (data.retCode == 'OK') {
				alert('성공');
				tr.remove();
                showList();
			} else if (data.retCode == 'NG') {
				alert('처리실패!');
			} else {
				alert('처리실패!');
			}
		})
		.catch(err => {
			console.log(err);
		})
}

document.getElementById('addReply').addEventListener('click', function(e) {
	console.log(e);
	let reply = document.querySelector('#reply').value;

	fetch('addReply.do?bno=' + bno + '&replyer=' + writer + '&reply=' + reply)
		.then(resolve => resolve.json())
		.then(result => {
			if (result.retCode == 'OK') {
				//location.reload();
				const row = makeRow(result.retVal);
				document.querySelector('div.reply ul').appendChild(row);
				document.getElementById('reply').value = '';
				alert('성공');
			} else if (result.retCode == 'NG') {
				alert('result!');
			} else {
				alert('처리실패!');
			}
		})
		.catch(err => console.log(err));
})

function refuse(e) {
	alert('로그인하세요');
}

//줄생성
function makeRow(reply = {}) {
	let tmpl = document.querySelector('div.reply li:nth-of-type(3)').cloneNode(true);
	console.log(tmpl);
	tmpl.style.display = 'block';
	tmpl.setAttribute('data-rno', reply.replyNo);
	tmpl.querySelector('span:nth-of-type(1)').innerText = reply.replyNo;
	tmpl.querySelector('span:nth-of-type(2)').innerText = reply.reply;
	tmpl.querySelector('span:nth-of-type(3)').innerText = reply.replyer;
	return tmpl;
}

//댓글 페이징 생성
let pagination = document.querySelector('div.pagination');

function createPageList() {
	let totalCnt = 127;
	let startPage, endPage, realEnd;
	let prev, next;

	realEnd = Math.ceil(totalCnt / 5);
	endPage = Math.ceil(page / 5) * 5;
	startPage = endPage - 4;
	endPage = endPage > realEnd ? realEnd : endPage;

	prev = startPage > 1;
	next = endPage < realEnd;

	console.log(startPage, endPage, realEnd, prev, next);

	//a태그 생성
	pagination.innerHTML = '';
	// 이전페이지 여부
	if (prev) {
		let aTag = document.createElement('a');
		aTag.setAttribute('data-page', startPage - 1);
		aTag.setAttribute('href', '#');
		aTag.innerHTML = "&laquo;";
		pagination.appendChild(aTag);
		aTag.addEventListener('click', function(e) {
			e.preventDefault();	//aTag는 페이지 이동
			page = e.target.dataset.page;	//페이지 지정
			showList();

		})
		
	}
	for (let pg = startPage; pg <= endPage; pg++) {
		let aTag = document.createElement('a');
		aTag.setAttribute('data-page', pg);
		aTag.setAttribute('href', pg);
		if (pg == page) {
			//aTag.setAttribute()
			aTag.className = 'active';
		}
		aTag.innerHTML = pg;
		aTag.addEventListener('click', function(e) {
			e.preventDefault();	//aTag는 페이지 이동
			page = e.target.dataset.page;	//페이지 지정
			showList();
		})
		pagination.appendChild(aTag);
	}
	//이후페이지여부
	if (next) {
		let aTag = document.createElement('a');
		aTag.setAttribute('data-page', endPage + 1);
		aTag.setAttribute('href', '#');
		aTag.innerHTML = "&raquo;";
		pagination.appendChild(aTag);
		aTag.addEventListener('click', function(e) {
			e.preventDefault();	//aTag는 페이지 이동
			page = e.target.dataset.page;	//페이지 지정
			showList();
		})

	}

}






