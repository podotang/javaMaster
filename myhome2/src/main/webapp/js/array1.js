/*
* js/array1.js
*/
empList.forEach((item, idx) => {
	/*if(item.gender == 'Female' && item.salary > 4000){
		console(item);
	}*/
	if (item.first_name.indexOf('c') >= 0) {
		console.log(item);
	}
});

let newAry = empList.filter((item, idx, ary) => {
	//return item.first_name.length>=6;
	return (idx + 1) == ary.length;
	//return true;
});
// A -> A'
newAry = empList.map((item, idx, ary) => {
	const obj = {
		no: item.id,
		name: item.first_name + "-" + item.last_name,
		email: item.email
	}
	return obj;
});
console.log(newAry);
console.clear();
let result = empList.reduce((acc, curVal) => {
	if (curVal.gender == 'Male') {
		acc.push(curVal);
	}
	return acc;
}, []);
console.log(result);

//reduce 는 매개값 4개다
const array1 = [1, 2, 3, 4];
const initialValue = 0;
//reduce 첫번째 매개값은 함수 두번째는 초기값 0으로 들어간 initialValue
const sumWithInitial = array1.reduce(function(accumulator, currentValue) {
	//여기 반환값이 두번째순서때 acc값으로 들어감
	//첫번 0 +1
	//두번 1+2
	//세번 3+3
	//네번 6+4
	//sumWithInitial 결과값은 10
	console.log(`accumulator => ${accumulator}, currentValue => ${currentValue}`)
	return accumulator + currentValue;
}, initialValue);
console.log(sumWithInitial);

// 최댓값
const array2 = [1, 7, 2, 9, 3, 4];
const initialVal = 0;
const maxValue = array2.reduce(function(accumulator, currentValue) {
	console.log(`accumulator => ${accumulator}, currentValue => ${currentValue}`)
	return accumulator > currentValue ? accumulator : currentValue;
}, initialVal);
console.log(maxValue);

// 최소값 => 초기값 없으면 첫순번값부터들어감
const minValue = array2.reduce(function(accumulator, currentValue) {
	return accumulator < currentValue ? accumulator : currentValue;
});
console.log(minValue);


// String.prototype.indexOf('') = > 찾는 값의 인덱스를 반환
// Array.prototype.indexOf('') = > 찾는 값의 인덱스를 반환

//indexOf -> 값이 없으면 -1 반환
console.log("abcde".indexOf("k"));
console.log([1, 2, 3, 4, 5].indexOf(3));
// 중복된 값 버리고 종류별로 하나씩
let genderAry = [];

empList.forEach(emp => {
	if(genderAry.indexOf(emp.gender) == -1){
	genderAry.push(emp.gender);
}
});

console.log(genderAry);	//male, female



























