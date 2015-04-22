/*Create*/
function createComments() {
	if ($(".txtArea_guest").val() == "") {
		alert("댓글 내용을 입력해주세요..");
	} else {
		var form = $('#replyFrm');
		$.ajax({
			type : form.attr('method'),
			url : form.attr('action'),
			data : form.serialize(),
			success : function(result) {
				$("#tbody").remove();
				$("#tfoot").remove();
				$("#thead").after(result);
			},error : function(xhr, status, error){
                alert("에러"+error.toString()+xhr.toString()+status.toString());
            }
		});
		$(".txtArea_guest").val("");
	}
}

/*Delete*/
$(function() {
	/*alert("초기작업시작!");*/
	$('body').on('click','.btn_delete',function(){
		if(confirm("댓글을 삭제 하시겠습니까?")){
			var num = $(this).parent().parent().parent()[0].id;
			var obj = $(this).parent().parent().parent();
			ajax_delete(num, obj);
		}else{
			return;
		}  
	});
	//처음에 읽을 시 댓글 불러오기
	var address = $('#replyFrm').attr('action');
	$.get( address, function( data ) {
		  $("#thead").after( data );
	});
	
});
function ajax_delete(num, obj){
	var urlAddress=$("#hiddenAddress").val();
	var finalAddress = urlAddress+num;
	$.ajax({
	    url: finalAddress,
	    type: 'DELETE',
	    success: function(result) {
	    	if(result==1){
	    		obj.remove();
	    	}else{
	    		alert('무슨 일인지 모르겠지만 삭제실패하였습니다 ㅠㅠ');
	    	}
	    },
		error:function(request,status,error){
        	alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
       }
    });
}



