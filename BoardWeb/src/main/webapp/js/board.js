/**
 * 
 */
//수정버튼
document.querySelector('#modBtn').addEventListener('click',function(){
	document.forms.myForm.action="modBoardForm.do"; //수정화면 호출
	document.forms.myForm.submit();
})

//삭제버튼
document.querySelector('.btn-danger').addEventListener('click',function(){
		document.forms.myForm.action="removeBoard.do"; //삭제화면 호출
		document.forms.myForm.submit();
})


