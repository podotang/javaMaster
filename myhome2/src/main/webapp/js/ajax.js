// setTimeout 은 비동기방식으로 아래 코드에서 3초안에 다끝남 동기시 다더한 6초안에끝나게됨
// Ajax(Asynchronous JavaScript and XML->xml은 예전방식이고 요즘은 json으로 처리함) 
setTimeout(function() {
	console.log("step 1");
}, 1000);
setTimeout(function() {
	console.log("step 2");
}, 2000);
setTimeout(function() {
	console.log("step 3");
}, 3000);













