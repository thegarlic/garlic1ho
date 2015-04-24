/*List */
function listComments(pageNumber){
	var address = $('#replyFrm').attr('action');
	address += "/"+pageNumber+"page";
	$.get( address, function( data ) {
		$("#tbody").remove();
		$("#tfoot").remove();
		$("#thead").after( data );
	});
}
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
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            }
		});
		$(".txtArea_guest").val("");
	}
}
temp = [];
temp.comment ="temp";
/*Update*/
function updateComment(commentId, getForm){
	if ($(".ta_update").val() == "") {
		alert("댓글 내용을 입력해주세요.."+commentId);
	} else {
		var urlAddress=$("#hiddenAddress").val();
		var finalAddress = urlAddress+commentId+"update";
		var form = $('.updateComment');
		temp.comment = $(".ta_update").val();
		$.ajax({
			type : 'POST',
			url : finalAddress,
			data : form.serialize(),
			success : function(result) {
				cancleModify(form.parent().prev().prev().find('a.btn_modifycancle'));
			},error : function(xhr, status, error){
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            }
		});
		$(".ta_update").val("");
	}
}

/*UpdateForm*/
$(function(){
	//수정 눌렀을 때
	$('body').on('click','.btn_modify',function(){
		//만약에 지금 댓글 수정중인 것이 있다면 모두 원상태로 돌리고
		$( "#tbody tr" ).each(function( i ) {
    		if($(this).attr('status')=="modify"){
    			cancleModify($(this).find('a.btn_modifycancle'));
    		}
  		});
		//댓글 수정폼으로 바꾼다.
		var obj_tr = $(this).parent().parent().parent(); //추가를위해 tr를 미리 뽑는다.
		var obj_div = $(this).parent().next().next(); // 내용을 바꿀 div를 뽑는다.
		var comment = $(this).parent().next().next().html(); //코멘트얻기위해 뽑는다.
		temp.comment = comment;
		obj_tr.attr("status", "modify");
		$(this).attr('class', 'btn_modifycancle');
		$(this).html('수정취소');
		//obj_div.remove();
		var form_update ="<form class='updateComment'>" +
				"<textarea class='ta_update' rows='5' name='comments'>"+comment+
				"</textarea><input type='button' onclick='updateComment("+obj_tr[0].id+", this.form)' value='수정' /><br>" +
				"</form>";
		obj_div.html(form_update);
		$(".ta_update").focus();
	});

	//수정취소를 눌렀을 때 액션
	$('body').on('click','.btn_modifycancle',function(){
		cancleModify($(this));
	});
});
function cancleModify(obj){
	var obj_tr = obj.parent().parent().parent(); //추가를위해 tR를 미리 뽑는다.
	var obj_div = obj.parent().next().next(); // 내용을 바꿀 div를 뽑는다.
	var comment = $(".ta_update").html(); //코멘트얻기위해 뽑는다.
	obj.attr('class', 'btn_modify');
	obj.html('수정');
	obj_tr.removeAttr( "status" );
	obj_div.html(temp.comment);
	temp.comment =null;
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
			alert('무슨 일인지 모르겠지만 삭제실패하였습니다 ㅠㅠ');
			/*alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);*/
       }
    });
}



