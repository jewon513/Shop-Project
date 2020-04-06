<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>

<script src="https://code.jquery.com/jquery-latest.min.js"></script>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>


<script type="text/javascript">
	var sock = new SockJS('http://localhost:8090/socket/chat');

	// 소켓이 서버에 접속이 성공한 다음 최초에 실행될 코드
	sock.onopen = function() {

		let userName = prompt("채팅방 입장!! 이름을 입력하세요")

		if (userName && userName != "") {
			sock.send("username:" + userName)
		}

	};

	// 서버로부터 수신되는 이벤트
	sock.onmessage = function(e) {
		console.log('message', e.data);
		//alert(e.data);
		// 문자열형으로 수신된 json 문자열을 json 객체로 변환
		let messageJson = JSON.parse(e.data)

		if (messageJson.msg && messageJson.msg == 'userName') {

			$("#userName").val(messageJson.userName)
			$("#userName").prop("readonly", true)
			return false;
		}
		
		if (messageJson.msg && messageJson.msg == 'userList'){
			
			let userList = JSON.parse(messageJson.userList)
			
			// 동적 태그를 만드는 코드
			let options = $("<option/>",{value: all, text:"전체"})
			
			$("#toList").append(options)
			
			for(let i = 0; i <userList.length; i++){
				options.append = $("<option/>",{value: userList[i].userName, text: userList[i].userName})	
			}
			
			
		}

		let html = "<div class='from text-right'>"
		html += "<span class='userName'>" + messageJson.userName + ": </span>"
		html += messageJson.message + "<br/>"
		html += "</div>"
		$("#message_list").append(html)
		//sock.close();
	};

	// 소켓 서버와 접속 종료
	sock.onclose = function() {
		console.log('close');
	};
</script>

<script type="text/javascript">
	$(function() {

		$(document).on("submit", "form", function(event) {

			event.preventDefault();

		})

		$(document).on("click", "#btn-send", function() {

			let userName = $("#userName").val()

			if (userName == "") {
				alert("보내는 사람이름을 입력해주세요.")
				return false;
			}

			let message = {
				userName : userName,
				message : $("#message").val()
			}

			let html = "<div class='to'>"
			html += "<span class='userName'>"
			html += "나 :"
			html += "</span>"
			html += message.message
			html += "</div>"

			$("#message_list").append(html)

			sock.send(JSON.stringify(message))

			$("#message").val("")
		})
		
		sock.send("getUserList")

	})
</script>

<style type="text/css">
.from .to {
	padding: 5px;
}

.userName {
	color: blue;
	font-weight: bold;
}
</style>

</head>
<body>

	<header class="jumbotron bg-success">
		<h2 class="text-white text-center">MY CHAT SERVICE</h2>
	</header>

	<div class="container-fluid">
		<form>
			<div class="form-group">
				<label>FROM</label> <input id="userName" class="form-control" placeholder="보내는 사람...">
			</div>
			<div class="form-group">
				<label>받는사람</label>
				<select class="form-control" id="toList">
					<option value="all">전체</option>
					
				</select>
			</div>
			<div class="form-group">
				<label>Message</label> <input id="message" class="form-control" placeholder="메시지 입력">
			</div>
			<button id="btn-send" class="btn btn-primary">전송</button>
		</form>
	</div>

	<section class="container-fluid justify-content-between">
		<div id="user_list" class="col-3"></div>
		<div id="message_list" class="col-8"></div>
	</section>


</body>
</html>