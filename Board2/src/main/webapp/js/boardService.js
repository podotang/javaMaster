const svc = {
	boardList: function(successCall, errorCall) {
		fetch('../boardJson.json')
			.then(result => result.json())
			.then(successCall)
			.catch(errorCall);
	},
	addBoard(param = {}, successCall, errorCall){
		fetch('..//boardCrud.json',{
			method : 'post',
			headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
			body : 	'job=add&title=' + param.boardTitle + '&content=' +param.boardContent +'&writer='+param.boardWriter
		})
		.then(result=>result.json())
		.then(successCall)
		.catch(errorCall)
		
	},



}