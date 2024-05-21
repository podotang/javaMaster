// 숫자 3자리 콤마찍기
Number.prototype.numberFormat = function() {
    if (this == 0)
        return 0;
    let regex = /(^[+-]?\d+)(\d{3})/;
    let nstr = (this + '');
    while (regex.test(nstr)) {
        nstr = nstr.replace(regex, '$1' + ',' + '$2');
    }
    return nstr;
};

let basket = {
    cartCount: 0, // 전체수량.
    cartTotal: 0, // 전체금액.

    list: function() {
        // 목록.
        svc.cartList(
            result => {
                console.log(result);
                result.forEach(cart => {
                    basket.cartCount += cart.qty;
                    basket.cartTotal += (cart.qty * cart.price);

                    const $rowDiv = $('div[data-id="0"]').clone(true);
                    $rowDiv.css('display', 'block');
                    $rowDiv.attr('data-id', cart.no);
                    $rowDiv.find('div.img>img').attr('src', 'images/' + cart.productNm + '.jpg');
                    $rowDiv.find('div.pname>span').text(cart.productNm);
                    
                    $rowDiv.find('div.basketprice').contents().filter(function() {
                        return this.nodeType === 3;
                        
                    }).remove();
                    $rowDiv.find('div.basketprice').append(document.createTextNode(cart.price.numberFormat() + "원"));

                    $rowDiv.find('div.updown input').val(cart.qty).attr('id', cart.no);
                    $rowDiv.find('div.basketprice input').val(cart.price).attr('id', 'p_price' + cart.no);
                    $rowDiv.find('div.updown input').val(cart.qty).attr('id', 'p_num' + cart.no);

                    // 이벤트 핸들러 등록 전 기존 이벤트 제거
                    $rowDiv.find('div.updown input').off('keyup').on('keyup', () => basket.changePNum(cart.no));
                    $rowDiv.find('div.updown span').off('click').on('click', () => basket.changePNum(cart.no));
                    $rowDiv.find('div.updown span:nth-of-type(2)').off('click').on('click', () => basket.changePNum(cart.no));

                    // 개별합계
                    $rowDiv.find('div.sum').text((cart.qty * cart.price).numberFormat() + "원").attr('id', 'p_sum' + cart.no);

                    $('#basket').append($rowDiv);
                });
                basket.reCalc();
            },
            err => {
                console.log(err);
            }
        ); // end of cartList
    },

    delItem: function() {
		let no = $(event.target).parent().parent().parent().data('id');
        svc.cartRemove(no,
            result => {
                if (result.retCode === 'OK') {
                    let price = $('#p_price' + no).val(); // 단가
                    let qty = $('#p_num' + no).val(); // 현재 수량
                    // 합계반영
                    basket.cartCount -= qty;
                    basket.cartTotal -= (price * qty);
                    basket.reCalc();
                    // 화면에서 지우기
                    $('div[data-id="' + no + '"]').remove();
                }
            },
            err => console.log(err)
        );
    },

    reCalc: function() {
        // 수량, 금액 합계 계산
        // 합계 자리에 출력
        $('#sum_p_num span').text(basket.cartCount);
        $('#sum_p_price span').text(basket.cartTotal.numberFormat());
    },

    changePNum: function(no) {
        console.log(event);
        let qty = -1;
        if ($(event.target).is("I")) {
            // 증가감소확인 아이콘클래스중 up이 있는지
            if ($(event.target).hasClass("up")) {
                qty = 1;
            }
        } else if ($(event.target).is("INPUT")) {
            if (event.key === "ArrowUp") {
                qty = 1;
            }
        }

        let price = $('#p_price' + no).val(); // 금액
        let $qtyElem = $('#p_num' + no); // 수량
        let $sumElem = $('#p_sum' + no); // 합계

        let cvo = { no, qty };
        svc.cartUpdate(cvo,
            result => {
                console.log(result);
                $qtyElem.val(parseInt($qtyElem.val()) + qty); // 수량변경
                $sumElem.text((price * $qtyElem.val()).numberFormat() + "원"); // 금액과 변경된값
                // 전체수량, 금액
                basket.cartCount += qty;
                basket.cartTotal += (price * qty);
                basket.reCalc();
            },
            err => {
                console.log(err);
            }
        );
    },

    delCheckedItem: function() {
        $('input:checked').each(function(idx, item) {
            if (idx > 0) {
         	let no = item.parentElement.parentElement.parentElement.dataset.id;
                svc.cartRemove(no,
                    result => {
                        if (result.retCode == 'OK') {
                            let price = $('#p_price' + no).val(); // 단가
                            let qty = $('#p_num' + no).val(); // 현재 수량
                            // 합계반영
                            basket.cartCount -= qty;
                            basket.cartTotal -= (price * qty);
                            basket.reCalc();
                            // 화면에서 지우기
                            $('div[data-id="' + no + '"]').remove();
                        }
                    },
                    err => console.log(err)
                );
            }
        });
    },

    delAllItem: function() {
        // 전체 아이템 삭제 (구현 필요)
    }
};

$(document).ready(function() {
    basket.list();
});
