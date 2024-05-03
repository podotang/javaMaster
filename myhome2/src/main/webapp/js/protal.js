//? 뒤부터가 파라메터 앞은 url
const showTitle = ['id', 'centerName', 'address', 'sido', 'phoneNumber'];
let url = 'https://api.odcloud.kr/api/15077586/v1/centers?page=1&perPage=284&serviceKey=odp3R%2BAnv93%2BqG0hMhsxznQIF589DFV7I%2BJbKgPbJu2h86CikqZnQN0weoWc9r1FZqDwWOL3YsDVzXFd%2BvX7%2Bw%3D%3D'

let totalData = []

// 서버에서 데이터 가져오기위한 api호출
fetch(url)
	.then(restult => restult.json())
	.then(data => {
		//console.log(data)
		totalData = data.data;
		/*data.data.forEach(center => {
			let tr = makeRow(center);
			document.querySelector('#list').appendChild(tr);
		})*/
		showPaging(1);
		//pagingList();
	})
	.catch(console.log);

// 링크 클릭하면 페이지 호출.
document.querySelectorAll('.pagination a').forEach(aTag => {
	console.log(aTag)
	aTag.addEventListener('click', function(e) {
		e.preventDefault(); //a페이지이동 차단.
		//console.log(this);
		let page = this.innerText;
		showPaging(page);
	})
})

// pagingList: 전체건수를 계산해서 284이면 29페이지가 생성됨
let totalCnt = 284;
function pagingList(page = 1) {
	let pagination = document.querySelector('.pagination');
	pagination.innerHTML = '';

	let endPage, startPage;
	let prev, next;
	let realEnd = Math.ceil(totalCnt / 10);
	endPage = Math.ceil(page / 10) * 10; //
	startPage = endPage - 9;
	endPage = endPage > realEnd ? realEnd : endPage;
	next = endPage < realEnd ? true : false;
	prev = startPage > 1;
	//aTag 생성. 이벤트 추가

	if (prev) {
		let aTag = document.createElement('a');
		aTag.setAttribute('href', '#');
		aTag.setAttribute('data-page', startPage - 1);
		aTag.innerHTML = '&laquo';
		aTag.addEventListener('click', function(e) {
			let page = e.target.dataset.page;
			showPaging(page);
		})
		pagination.appendChild(aTag);
	}

	for (let n = startPage; n <= endPage; n++) {
		let aTag = document.createElement('a');
		aTag.setAttribute('href', '#');
		aTag.innerHTML = n;
		if (page == n) {
			aTag.className = 'active';	// class 속성쓸때 사용
		}
		aTag.addEventListener('click', function(e) {
			let page = e.target.innerHTML;
			showPaging(page);
		})
		pagination.appendChild(aTag);
	}// end of for

	if (next) {
		let aTag = document.createElement('a');
		aTag.setAttribute('href', '#');
		aTag.setAttribute('data-page', endPage + 1);

		aTag.innerHTML = '&raquo';
		aTag.addEventListener('click', function(e) {
			let page = e.target.dataset.page;
			showPaging(page);
		})
		pagination.appendChild(aTag);
	}
}

//10p씩 보여주기
function showPaging(page = 1) {	//0~9 : 1page //10~19 : 2page
	//filter는 조건만족하는걸 배열에담아줌
	let startNo = (page - 1) * 10;
	let endNo = page * 10;
	let fAry = totalData.filter((center, idx) => {
		if (idx >= startNo && idx < endNo) {
			return true;
		}
	})
	//목록삭제
	document.querySelector('#list').innerHTML = '';
	fAry.forEach(center => {
		let tr = makeRow(center);
		document.querySelector('#list').appendChild(tr);
	})
	console.log(fAry);
	pagingList(page);	//페이지 목록생성
	//console.log(fAry[centerName]);
}

//데이터(센터) tr 함수.
function makeRow(center = {}) {
	let tr = document.createElement('tr');
		
	tr.addEventListener('click', function(e) {
		window.open('daum.html?x=' + center.lat + '&y=' + center.lng + '&centerName=' + center.centerName);
	})
	//attribute 속성!
	showTitle.forEach(title => {
		let td = document.createElement('td');
		//코로나19부분 공란으로 바꾸기 바로 replace적용안됨
		let name = center[title];
		td.innerHTML = (name + '').replace('코로나19 ', '');
		tr.appendChild(td);
	});
	return tr;
}// end of makeRow();




