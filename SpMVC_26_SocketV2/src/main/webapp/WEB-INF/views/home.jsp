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
		
		sock.send("getUserList:" + userName)

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
			
			$("#toList").empty()
			
			$("#toList").append(
						$("<option/>",{value: "all", text:"전체"})
					)
			
			// js코드에서
			// userList 객체 그룹중에서 각 요소의 키값만 추출하여 배열로 만들어달라
			let userListKeys = Object.keys(userList)
			
			for(let i = 0; i < userListKeys.length; i ++){
				console.log("사용자 정보 : ", userList[userListKeys[i]].userName)
				
				// 동적 태그를 만드는 코드
				$("#toList").append(
						$("<option/>",{
							value: userListKeys[i], 
							text: userList[userListKeys[i]].userName}
						)
					)
			}			
			return false;
		}

		let html = "<div class='from'>"
		html += "<span class='userName'>" + messageJson.userName + ": </span>"
		html += messageJson.message + "<br/>"
		html += "</div>"
		$("#message_list").append(html)
		$("#message_list").scrollTop($("#message_list").prop('scrollHeight'))
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
			
			let toUserId = $("#toList").val()
			let toUserName = $("#toList option:checked").text()

			let userName = $("#userName").val()

			if (userName == "") {
				alert("보내는 사람이름을 입력해주세요.")
				return false;
			}

			let message = {
				userName : userName,
				toUser : toUserId,
				message : $("#message").val()
			}

			let html = "<div class='to text-right'>"
			html += "<span class='userName'>"
			html += "나 :"
			html += "</span>"
			html += message.message
			html += "<span>("
			html += toUserName
			html += ")</span>"
			html += "</div>"

			$("#message_list").append(html)
			// 채팅 칠때마다 스크롤 아래로 내려가게
			$("#message_list").scrollTop($("#message_list").prop('scrollHeight'))

			sock.send(JSON.stringify(message))

			$("#message").val("")
		})
		
		

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

#message_list{
	border: 1px solid red;
	height: 30vh;
	overflow: auto;
	padding: 20px;
}

</style>

</head>
<body>

	<header class="jumbotron bg-success">
		<h2 class="text-white text-center">MY CHAT SERVICE</h2>
	</header>

	<section class="container-fluid justify-content-between">
		<div id="message_list"></div>
	</section>

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




</body>
</html>