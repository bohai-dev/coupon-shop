$(function(){
	
	var app = new Vue({
	  el: '#app',
	  data: {
	    count: '',
	    noCount:false,
	    clickCount:'',
	    noClickCount:false
	  }
	})
	
	//查询优惠券兑换统计
	$.ajax({
		type:"get",
		url:"http://139.196.73.183:8088/statistics",
		async:true,
		contentType:"application/json",
		success:function(result){
//			console.log(result)
			if (result.data.length>0) {
				app.count = result.data
			} else{
				app.noCount = true
			}
		},
		error:function(xhr){
			console.log(xhr)
		}
	});
	
	//点击量统计
	$.ajax({
		type:"get",
		url:"http://139.196.73.183:8088/statistics/clickcount",
		async:true,
		contentType:"application/json",
		success:function(result){
			console.log(result)
			if (result.data.length>0) {
				app.clickCount = result.data
			} else{
				app.noClickCount = true
			}
		},
		error:function(xhr){
			console.log(xhr)
		}
	});
	
	//重置转发状态
	$('.l-resetSubmitBtn').click(function(){
		$.ajax({
			type:"get",
			url:"http://139.196.73.183:8088/pointrecord/resetstatus",
			async:true,
			contentType:"application/json",
			success:function(result){
//				console.log(result)
				if (result.errorCode==0000) {
					showModelMes("重置成功！")
				} else{
					showModelMes(result.errorMsg)
				}
			},
			error:function(xhr){
				console.log(xhr)
			}
		});
	})
	
	//关闭弹出框
	$('.yes').click(function(){
		hideModel()
	})
})
//显示弹窗
function showModelMes(mes){
	$('.l-mask').css("display","flex")
	$('.l-mask-model').fadeIn()
	$('.l-mask-modelChild>p').text(mes)
}
//关闭弹窗
function hideModel(){
	$('.l-mask').hide()
	$('.l-mask-model').fadeOut()
}