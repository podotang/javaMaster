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
//console.log('bno:', bno);
showList();

function showList() {
	//댓글목록을 초기화
	document.querySelectorAll('div.content ul li').forEach((li, idx) => {
		if (idx >= 3) {
			li.remove();
		}
	})
	//$('div.content ul li:gt(2)').remove();
	svc.replyList({ bno: bno, page: page },
		result => {
			//console.log(result);
			result.forEach(reply => {
				const row = makeRow(reply);
				document.querySelector('div.reply ul').appendChild(row);
			})
			makePageInfo();
		},	// 두번째 param
		err => {
			console.log(err);
		}	// 세번째 param
	)	// end of replyList
}//	목록출력끝부분


//삭제버튼의 이벤트
function deleteRow(e) {
	const rno = e.target.parentElement.parentElement.dataset.rno;
	const tr = e.target.parentElement.parentElement;

	svc.removeReply(rno,
		data => {
			if (data.retCode == 'OK') {
				alert('성공');
				tr.remove();
				showList();
			} else if (data.retCode == 'NG') {
				alert('삭제불가!');
			} else {
				alert('알수없는 오류!');
			}
		},
		err => {
			console.log(err);
		}
	)
}

document.getElementById('addReply').addEventListener('click', function(e) {
	console.log(e);
	let reply = document.querySelector('#reply').value;

	svc.addReply({ bno: bno, writer: writer, reply: reply },
		result => {
			if (result.retCode == 'OK') {
				//location.reload();
				const row = makeRow(result.retVal);
				document.querySelector('div.reply ul').appendChild(row);
				document.getElementById('reply').value = '';
				alert('성공');
				showList();
			} else if (result.retCode == 'NG') {
				alert('result!');
			} else {
				alert('처리실패!');
			}
		},
		err => console.log(err)
	)

})

function refuse(e) {
	alert('로그인하세요');
}

//줄생성
function makeRow(reply = {}) {
	let tmpl = document.querySelector('div.reply li:nth-of-type(3)').cloneNode(true);
	//console.log(tmpl);
	tmpl.style.display = 'block';
	tmpl.setAttribute('data-rno', reply.replyNo);
	tmpl.querySelector('span:nth-of-type(1)').innerText = reply.replyNo;
	tmpl.querySelector('span:nth-of-type(2)').innerText = reply.reply;
	tmpl.querySelector('span:nth-of-type(3)').innerText = reply.replyer;
	return tmpl;
}

//댓글 페이징 생성
let pagination = document.querySelector('div.pagination');


function makePageInfo() {
	svc.getTotalCount(bno
		,createPageList //param2
		, err => console.log(err));
}

function createPageList(result) {
	//console.log(result);

	let totalCnt = result.totalCnt;	//
	let startPage, endPage, realEnd;
	let prev, next;

	realEnd = Math.ceil(totalCnt / 5);
	endPage = Math.ceil(page / 5) * 5;
	startPage = endPage - 4;
	endPage = endPage > realEnd ? realEnd : endPage;

	prev = startPage > 1;
	next = endPage < realEnd;
	//console.log(startPage, endPage, realEnd, prev, next);
	//a태그 생성
	pagination.html = '';
	// 이전페이지 여부
	if (prev) {
		let aTag = document.createElement('a');
		aTag.setAttribute('data-page', startPage - 1);
		aTag.setAttribute('href', '#');
		aTag.html = "&laquo;";
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
		aTag.html = pg;
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
		aTag.html = "&raquo;";
		pagination.appendChild(aTag);
		aTag.addEventListener('click', function(e) {
			e.preventDefault();	//aTag는 페이지 이동
			page = e.target.dataset.page;	//페이지 지정
			showList();
		})
	}
}







