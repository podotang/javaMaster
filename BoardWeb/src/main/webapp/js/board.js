/**
 * 
 */
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

console.log('bno:', bno);
fetch('replyList.do?bno=' + bno)
	.then(resolve => resolve.json())	//json ->객체
	.then(result => {
		console.log(result);
		result.forEach(reply => {
			let tmpl = document.querySelector('div.reply li:nth-of-type(3)').cloneNode(true);
			tmpl.style.display = 'block';
			tmpl.setAttribute('data-rno', reply.replyNo);
			tmpl.querySelector('span:nth-of-type(1)').innerText = reply.replyNo;
			tmpl.querySelector('span:nth-of-type(2)').innerText = reply.reply;
			tmpl.querySelector('span:nth-of-type(3)').innerText = reply.replyer;
			console.log(tmpl);
			document.querySelector('div.reply ul').appendChild(tmpl);
		})
	})
	.catch(err => {
		console.log(err);
	})


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
				location.reload();
				alert('성공');
			} else if (result.retCode == 'NG') {
				alert('result!');
			} else {
				alert('처리실패!');
			}
		})
		.catch(err => console.log(err));

})

function refuse(e){
	alert('로그인하세요');
}




