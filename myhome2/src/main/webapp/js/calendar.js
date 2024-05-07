document.addEventListener("DOMContentLoaded", initForm)

function initForm() {
	let show = document.querySelector('#show');
	show.appendChild(svc.makeTable());
	//document.querySelector('#show>table').appendChild(svc.makeHeader());
	document.querySelector('#show>table').appendChild(svc.makeHeader2());
	//달별로 달력출력
	document.querySelector('#show>table').appendChild(svc.makeBody(1));

	console.log(show);
}

//svc(서브스라는 객체) 안에 메소드 모아두고 사용
const svc = {
	makeTable: function() {
		let tbl = document.createElement('table');
		tbl.setAttribute('border', "2");
		return tbl;
	},
	//메소드 안 함수는 메소드 사용할때만사용가능
	makeHeader: function() {
		const days = ['Sun', 'Mon', 'Tue', 'Wed', 'Thr', 'Fri', 'Sat'];
		let thd = document.createElement('thead');
		let tr = document.createElement('tr');
		//forEach매개변수로 함수를 받음
		days.forEach((day) => {
			let th = document.createElement('th');
			th.innerHTML = day;
			tr.appendChild(th);
		});
		thd.appendChild(tr);
		return thd;
	},

	makeHeader2: function() {
		const days = ['Sun', 'Mon', 'Tue', 'Wed', 'Thr', 'Fri', 'Sat'];
		let tr = days.reduce((acc, curVal) => {
			let th = document.createElement('th');
			th.innerHTML = curVal;
			acc.appendChild(th);
			//반환해줘야 그 다음값 초기값으로 또 쓸수있음
			return acc;
		}, document.createElement('tr'));
		let thd = document.createElement('thead');
		thd.appendChild(tr);
		return thd;
	},

	makeBody: function(month) {
		let tbd = document.createElement('tbody');
		let tr = document.createElement('tr');
		let spaces = this.getFirstDate(month);
		//		let spaces = 1;
		for (let i = 0; i < spaces; i++) {
			let td = document.createElement('td');
			td.innerHTML = " ";
			tr.appendChild(td);
		};

		//시작 두번째 칸부터
		//토요일 지나면 다음줄로
		for (let d = 1; d <= this.getLastDate(month); d++) {
			let td = document.createElement('td');
			td.innerHTML = d;
			tr.appendChild(td);
			//td계속생성
			if ((d + spaces) % 7 == 0) {
				//tr새로생성
				tbd.appendChild(tr);
				tr = document.createElement('tr');
			}
		}
		tbd.appendChild(tr);
		return tbd;
	},
	getFirstDate(month) {
		// Date 객체 홀용해서 계산
		let now = new Date(2024, month -1, 1);
		console.log('요일위치', now.getDay);
		return now.getDay();
	},
	getLastDate(month) {
		let now = new Date(2024, month, 0);
		return now.getDate();
	}


}// end of svc










