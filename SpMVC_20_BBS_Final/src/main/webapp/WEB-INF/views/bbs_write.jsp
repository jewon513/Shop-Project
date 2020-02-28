<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />

<head>
<%@ include file="/WEB-INF/views/incldue/include-head.jspf"%>


<script>
	$(function() {
		
		var toolbar = [
			['style',['bold','italic','underline'] ],
			['fontsize',['fontsize']],
			['font Style',['fontname']],
			['color',['color']],
			['para',['ul','ol','paragraph']],
			['height',['height']],
			['table',['table']],
			['insert',['link','hr','picture']],
			['view',['fullscreen','codeview']]
			
		]
		
		$("#b_content").summernote({
			lang:'ko-KR',
			placeholder:'본문을 입력하세요',
			width:'100%',
			toolbar:toolbar,
			height:'400px',
			disableDragAndDrop : false,
			// 이미지 업로드를 가로챈다.
			// 드래그 앤 드롭 하면 files에 파일 정보가 저장되고
			// editor에는 summernote 정보가 저장됨
			callbacks : {
				
				onImageUpload : function(files,editor,isEdite){
					
					for(let i = files.length -1 ; i >= 0 ; i--){
						// 실제로 파일을 한개씩 업로드할 함수이다.
						upFile(files[i],this) // this = editor
					}
					
				}
				
			}
		}) // end summer
		
		
		// ajax를 사용해서 파일을 서버로 업로드를 수행하고
		// 실제 저장된 파일 이름을 받아서 
		// summernote에 기록된 내용중 img src=""을 변경 할 것이다.
		function upFile(file,editor){
			
			var formData = new FormData()
			// 앞에는 컨트롤러에서 받을 이름이고 뒤에는 업로드할 file이다.
			// upFile 변수에 file 정보를 담아서 보내기 위한 준비를 한다.
			formData.append('upFile',file)
			
			$.ajax({
				
				url : "${rootPath}/image_up",
				type : "POST",
				data : formData,
				contentType : false,
				processData : false,
				enctype : "multipart/form-data",
				success : function(result){
					
					result = "${rootPath}/files/" + result
					
					$(editor).summernote('editor.insertImage',result)
					
				},
				error : function(){
					alert("서버 통신 오류")
				}
				
				
			})
			
			
		}
		
		
		$(".btn-list").click(function(){
			
			document.location.href = "${rootPath}/list"
			
		})
		

	})
</script>

</head>
<body>

	<%@ include file="/WEB-INF/views/incldue/include-header.jsp" %>

	<section class="container mb-5">
		<h2>글작성</h2>
		<hr/>
		<fieldset>
			<form method="POST">
				<div class="form-group">
					<input class="form-control border border-secondary" id="b_writer" name="b_writer" value="${BBSVO.b_writer}" placeholder="작성자">			
				</div>	
				<div class="form-group">
					<input class="form-control border border-secondary" id="b_subject" name="b_subject" value="${BBSVO.b_subject}" placeholder="제목">			
					
				</div>
				<div class="form-group">
					<textarea rows="20" class="form-control" id="b_content"  name="b_content" placeholder="본문">${BBSVO.b_content}</textarea>			
				</div>
				
				<hr/>
				<div class="form-group">
					<div class="d-flex justify-content-end">
						<button class="btn btn-success mr-2">저장</button>
						<button class="btn btn-success btn-list" type="button">목록으로</button>
					</div>
				</div>
			</form>
		</fieldset>
		
	</section>


</body>
</html>