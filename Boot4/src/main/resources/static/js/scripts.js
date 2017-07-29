
$(".submit-write input[type=submit]").click(addAnswer);

function addAnswer(e) {
	e.preventDefault(); // 동작이 멈춘다.
	console.log("Click Me!!!");
	
	var queryString = $(".submit-write").serialize();
	
	var url = $(".submit-write").attr("action");
	
	$.ajax({
		type : 'post',
		url : url,
		data : queryString,
		dataType : 'json',
		error : onError,
		success : onSuccess
	});
}

function onError() {
	
}

//data: json으로 넘어온 AnswerVO status : 상태
// console.log(data)로 확인하면 getter 메소드를 쓴 필드값만 json으로 받게된다. 그러므로 answerVO에서 getter 메소드를
// 추가 하던가 해줘야 한다. 여기까지가 6-1

// 여기서부터 6-2 
// getter메소드를 추가해 줘야 하지만 @JsonProperty 애노테이션을 통해 같은 효과를 줄 수 있다. 
// 여기서 answerTemplate.format()이 가르키는건 바로 밑에 있는 String.prototype.format이다!(show.html에 있는 id=answerTemplate)
// 파라미터의 순서대로 answerTemplate의 {0},{1}... 인자로 값이 들어간다.
function onSuccess(data, status) { 
	console.log(data);
	var answerTemplate = $("#answerTemplate").html();
	var qId = data.question.id;
 	var Template = answerTemplate.format(data.writer.userId, data.formatTime, data.contents,data.question.id,data.id); 
	$(".qna-comment-slipp-articles").prepend(Template);
	
	$("textarea[name=contents]").val("");  // 답변을 달고 답변의 input값을 지워줌
}

$("li .link-delete-article").click(deleteAnswer);

function deleteAnswer(e) {
	e.preventDefault();
	
	var daleteBtn = $(this);      // 이렇게 this를 전역변수로 선언해야 각각의 안에서 쓸 수 있다. 
	var url = daleteBtn.attr("href");
	console.log("url : "+ url);
	
	$.ajax({
		type : 'delete',       
		url : url,
		dataType : 'json',
		error : function(xhr, status) {
			console.log("error");
		},
		success : function(data, status) {
			console.log("success");
			if(data){
				daleteBtn.closest("article").remove();
			}
			else{
				alert("자신의 글만 수정,삭제 하실 수 있습니다!");
			}
		}
	});
}

String.prototype.format = function() {
	var args = arguments;
	return this.replace(/{(\d+)}/g, function(match, number) {
		return typeof args[number] != 'undefined'
			? args[number]
		: match
		;
	});
};



