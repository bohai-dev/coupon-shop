$(function(){
	//输入提示
	$("input[name='email'],input[name='password']").focus(function(){
		$(this).removeClass("l-tipsBorder")
		$('.l-tips').hide()
	})
	//关闭弹出框
	$('.yes').click(function(){
		hideModel()
	})
	$('.toRegister').click(function(){
		window.location.href="register.html"
	})
	
	//页面初始化时，如果帐号密码cookie存在则填充
	if(getCookie('user')){
	  $("input[name='email']").val(getCookie('user'));
	  $("input:checkbox[name='rememberEmail']")[0].checked = true;
	}
	if(getCookie('pswd')){
	  $("input[name='password']").val(getCookie('pswd'));
	  $("input:checkbox[name='rememberPassword']")[0].checked = true;
	}
	
    var oForm = document.getElementById('loginForm');
    //复选框勾选状态发生改变时，如果未勾选则清除cookie
    $("input:checkbox[name='rememberEmail']").change(function(){
    	if(!$(this).is(":checked")){
        delCookie('user');
      }
    })
    $("input:checkbox[name='rememberPassword']").change(function(){
    	if(!$(this).is(":checked")){
        delCookie('pswd');
      }
    })
    
    //回车登陆
    document.onkeydown=function(e){
		var keycode=document.all?event.keyCode:e.which;
		if(keycode==13){
			if($(".l-mask").is(":hidden")){
			    $('.loginBtn').click();
			}else{
				$('.yes').click()
			}
		};
	}
})

//登陆
function login(form){
	if (!form.email.value) {
		$('.l-tips').show().text("请输入邮箱")
		$("input[name='email']").addClass("l-tipsBorder")
	} else if (!form.password.value){
		$('.l-tips').show().text("请输入密码")
		$("input[name='password']").addClass("l-tipsBorder")
	} else {
		if($("input:checkbox[name='rememberEmail']")[0].checked){ 
	        setCookie('user',$("input[name='email']").val(),30); //保存帐号到cookie，有效期30天
	    }
		if($("input:checkbox[name='rememberPassword']")[0].checked){ 
	        setCookie('pswd',$("input[name='password']").val(),30); //保存密码到cookie，有效期30天
	    }
		var data = {
			userName:form.email.value,
			userPwd:form.password.value
		}
//		console.log(data)
		$.ajax({
			type:"post",
			url:"http://139.196.73.183:8088/login",
			async:true,
			data:JSON.stringify(data),
			contentType:"application/json",
			success:function(result){
//				console.log(result)
				if (result.errorCode==0000) {
					$('#loginForm')[0].reset();
//					window.location.href="index.html?userId="+result.data.userId+"&userName="+result.data.userName
					window.location.href="index.html";
					set_cache("userId",result.data.userId)
					set_cache("userName",result.data.userName)
					set_cache("token",result.data.token)
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

//设置cookie
function setCookie(name,value,day){
    var date = new Date();
    date.setDate(date.getDate() + day);
    document.cookie = name + '=' + value + ';expires='+ date;
};
 //获取cookie
function getCookie(name){
    var reg = RegExp(name+'=([^;]+)');
    var arr = document.cookie.match(reg);
    if(arr){
      return arr[1];
    }else{
      return '';
    }
};
//删除cookie
function delCookie(name){
	setCookie(name,null,-1);
};