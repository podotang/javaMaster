/**
 * 
 */
//document.addEventListener("DOMContentLoaded",initForm);

$(document).ready(function(){
	$('#addBtn').on('click',function(){
		$('tbody button').on('click', delRow);
		// 2개 값을 td생성. tr생성. tbody의 하위요소에 추가
		let inputName = $('input[name="name"]');
		let inputScore = $('input[name="score"]');
		//네임속성에 값이 없음
		if(!inputName.val()){
			alert('값을 입력하세요');
			return;
		}
		let tr = $('<tr/>').append(
						  $('<td/>').append($('<input/>').attr('type','checkbox'))
						 ,$('<td/>').text(inputName.val())
						 ,$('<td/>').text(inputScore.val())
						 ,$('<td/>').append($('<button>삭제</button>').on('click',delRow))
						 );
		$('#list tbody').append(tr);
		inputName.val('');
		inputScore.val('');
		//$('tbody input:checked').remove()
	})
});


function delRow(e){
	$(e.target).parent().parent().remove();
}













