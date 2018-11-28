$(function(){
	//输入提示
	$("input[name='registerEmail'],input[name='password'],input[name='passwordAgain']").focus(function(){
		$(this).removeClass("l-tipsBorder")
		$('.l-tips').hide()
	})
	$("input:checkbox[name='read']").change(function(){
		$('.l-tips').hide()
	})
	//关闭弹出框
	$('.yes').click(function(){
		hideModel()
	})
	$('.backLogin').click(function(){
		hideModel()
		window.location.href="login.html"
	})
})

//注册
function register(form){
	if (!form.registerEmail.value) {
		$('.l-tips').show().text("请输入注册邮箱")
		$("input[name='registerEmail']").addClass("l-tipsBorder")
	} else if (!form.password.value){
		$('.l-tips').show().text("请输入密码")
		$("input[name='password']").addClass("l-tipsBorder")
	} else if (form.password.value.length<6){
		$('.l-tips').show().text("密码不能少于六位")
		$("input[name='password']").addClass("l-tipsBorder")
	} else if (!form.passwordAgain.value){
		$('.l-tips').show().text("请输入重复密码")
		$("input[name='passwordAgain']").addClass("l-tipsBorder")
	} else if (!(form.password.value == form.passwordAgain.value)){
		$('.l-tips').show().text("密码与重复密码不相同")
	} else if (!$("input:checkbox[name='read']:checked").val()){
		$('.l-tips').show().text("请先确认阅读平台说明及免责声明")
	} else {
		var data = {
			userName:form.registerEmail.value,
			userPwd:form.password.value
		}
		console.log(data)
		$.ajax({
			type:"post",
			url:"http://139.196.73.183:8088/register",
			async:true,
			data:JSON.stringify(data),
			contentType:"application/json",
			success:function(result){
//				console.log(result)
				if (result.errorCode==0000) {
					$('#registerForm')[0].reset();
					showModelMes("注册成功!")
					$('.yes').css("margin-right","35px")
					$('.backLogin').show()
				} else{
					showModelMes(result.errorMsg)
				}
			},
			error:function(xhr){
				console.log(xhr)
			}
		});
	}
}
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
