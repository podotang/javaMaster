/**
 * 
 */
const ary = [];		//new Array();
ary.push('apple');
ary.push(['banana','cherry']);
ary.push({name : "홍길동", age : 12});
console.log(ary)

const fruits = []; 	//
//pop은 빼기 push 추가하기 pop은 뒤에서 부터하나씩제거 shift 앞에서 부터 하나씩 제거 unshift 맨앞에 하나추가
fruits.push({name:"사과", price: 10000});
fruits.push({name:"수박", price: 20000});
fruits.push({name:"복숭아", price: 7000});
fruits.pop();
fruits.unshift({name:"포도", price: 5000});
fruits.shift();
// 특정위치 값 안넣으면 삭제가능
fruits.splice(1, 1);

// [사과,수박] =>[사과,파인애플]
fruits.splice(1, 0, {name : '파인애플', price: 8000});	//추가
fruits.splice(1, 1, {name : '파인애플', price: 8000});	//수정
fruits.splice(1, 2);	//삭제 여러개 삭제가능

console.log(fruits);


