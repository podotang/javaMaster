
$(document).ready(function() {
    // Create a "close" button and append it to each list item
    //화면의 li요소에 담기
    $('li').each(function() {
        var $span = $('<span></span>').text('\u00D7').addClass('close');
        $(this).append($span);
    });

    // Click on a close button to hide the current list item
    $(document).on('click', '.close', function() {
        $(this).parent().hide();
    });
	//ul하위 li요소에 클릭이벤트
    // Add a "checked" symbol when clicking on a list item
    $('ul').on('click', 'li', function() {
        $(this).toggleClass('checked');
    });

});
	//신규요소 등록하기
    // Create a new list item when clicking on the "Add" button
    function newElement() {
        var $li = $('<li></li>');
        var inputValue = $('#myInput').val();
        if (inputValue === '') {
            alert("You must write something!");
        } else {
            $li.text(inputValue);
            var $span = $('<span></span>').text('\u00D7').addClass('close');
            $li.append($span);
            $('#myUL').append($li);
        }
        $('#myInput').val('');
    };
