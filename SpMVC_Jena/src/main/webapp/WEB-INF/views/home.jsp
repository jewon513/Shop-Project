<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>


<script type="text/javascript">

	$(function(){
		
		var jsonResult = ${result}
		
		var result = jsonResult.results
		var bindings = result.bindings
		
		$.each(bindings,function(key, data){
			
			$("#test").append(data.name.value + "<br>")
			
		})
		
	})

</script>

</head>
<body>

	<div id="test">
	
	</div>

</body>
</html>