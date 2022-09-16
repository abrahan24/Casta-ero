$(function () {
    $(document).on('click','.user-details',function(){
        var contexPath = '<%=request.getContextPath() %>'; 
        var userId = this.id;
        alert("Algo "+userId);
            $.ajax({ url: 'userin/'+userId,
                type: 'GET',
                dataType : 'json',
                contentType: "application/json;charset=utf-8",
                success: function (datos) {
                	console.log(datos);
                        alert("hola "+datos.sede);
                	
                },
            error: function(error,xhr) {
				alert(error+" errorsss "+xhr.status);
				console.log(error);
			}
            
            });
    });
});