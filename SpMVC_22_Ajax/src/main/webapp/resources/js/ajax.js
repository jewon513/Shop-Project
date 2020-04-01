$(function(){
	
	
	$(document).on("click","#call_ajax",function(){
		
		$.ajax({
			
			url : rootPath + "/ajax", 
			type: "GET",
			success : function(result){
				
				alert(result)
				
			},
			error : function(error){
				
				alert("서버 통신 오류")
				
			}
			
		})		
		
	})
	
	
	$("#call_msg").click(function(){
		
		$.ajax({
			
			url : rootPath + "/ajax/msg",
			data : {msg : $("#msg").val() },
			
			type : "GET",
			success : function(result){
				alert(result)
			},
			error : function(error){
				alert("서버 통신 오류")
			}
			
		})
	})
	
	
	$(document).on("click","#call_addr",function(){
		
		$.ajax({
			
			url : rootPath + "/ajax/addr", 
			type: "GET",
			success : function(result){
				
				var addr = "<div>" + result.ad_name + "</div>"
				addr += "<div>" + result.ad_addr + "</div>"
				addr += "<div>" + result.ad_tel + "</div>"
				addr += "<div>" + result.ad_age + "</div>"
				
				$("#sub").html(addr)
				
			},
			error : function(error){
				
				alert("서버 통신 오류")
				
			}
			
		})		
		
	})
	
		$(document).on("click","#call_addrView",function(){
		
		$.ajax({
			
			url : rootPath + "/ajax/addrView", 
			type: "GET",
			success : function(result){
				
				$("#sub").html(result)
				
			},
			error : function(error){
				
				alert("서버 통신 오류")
				
			}
			
		})		
		
	})
	
	$(document).on("click","#ad_name",function(){
		// alert($(this).text())
		
		$.ajax({
			
			url : rootPath + "/ajax/put",
			type: "PUT",
			data : {msg : "테스트"},
			success : function(result){
				alert(result)
			},
			error : function(error){
				alert("통신오류")
			}
			
		})
		
	})
	
	
	
	
	
})