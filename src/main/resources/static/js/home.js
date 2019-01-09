$(function(){
	userId = get_cache("userId")
	userName = get_cache("userName")
	token = "Bearer:"+get_cache("token")
	shopId = "";
	
	//显示账号
	$('.l-userName').text(userName)
	
	if (get_cache("token")) {
		//查询店铺信息
		$.ajax({
			type:"get",
			url:"http://139.196.73.183:8088/shop/selectbyuserid?userId="+userId,
			async:true,
			beforeSend: function(request) {
	            request.setRequestHeader("Authorization", token);
	        },
			success:function(res){
				console.log(res)
				if (res.errorCode==0000) {
					if (res.data.length>0) {
						$('.addShop').hide()
						$('.qrcode,.xcxcode').show()
						$("html,body").animate({scrollTop:0}, 200);
						$('.l-topBar').slideDown(1000)
						setTimeout(function(){
							$('.l-topBar').slideUp(1000)
						},5000)
						//已有店铺赋值
						shopId = res.data[0].shopId;
						if (res.data[0].topImagePath) {
							var elemTopImage = "<div><img src="+res.data[0].topImagePath+" /><a href='javascript:void(0);' class='btn delBtn'>删除</a></div>"
							$('#ossfile1').empty().append(elemTopImage);
							$('#container1').addClass('dn')
						}
						//招牌菜品
						var elemDishImg= "";
						for (var i = 0; i < res.data[0].specialDishes.length; i++) {
							elemDishImg += "<div class='dataImg'><img src="+res.data[0].specialDishes[i].imagePath+"><a href='javascript:void(0);' class='btn delBtn'>删除</a><div class='dishesbox'><div class='dishesView'><span class='dishesTitle'>菜名：</span><input class='l-formMiddleInput' name='dishName' type='text' value="+(res.data[0].specialDishes[i].dishName?res.data[0].specialDishes[i].dishName:"")+"></div><div class='dishesView'><span class='dishesTitle'>价格：</span><input class='l-formMiddleInput' name='dishPrice' type='number' value="+(res.data[0].specialDishes[i].dishPrice?res.data[0].specialDishes[i].dishPrice:"")+"></div></div></div>"
						}
						$('#ossfile').empty().append(elemDishImg)
						if (res.data[0].specialDishes.length == 9) {
							$('#container').addClass('dn')
						}
//						var imgArr = res.data[0].introImages.split(";");
//						var elemImgIntro = "";
//						for (var i = 0; i < imgArr.length; i++) {
//							elemImgIntro += "<div><img src="+imgArr[i]+" /><a href='javascript:void(0);' class='btn delBtn'>删除</a></div>"
//						}
//						$('#ossfile').empty().append(elemImgIntro)
//						if (imgArr.length == 9) {
//							$('#container').addClass('dn')
//						}
						$("input[name='shopName']").val(res.data[0].shopName)
						$("input[name='shopAddress']").val(res.data[0].shopAddress)
						$("textarea[name='synopsis']").val(res.data[0].simpleIntro)
						$("input[name='contactWay']").val(res.data[0].contactWay)
						$("select[name='classId']").val(res.data[0].classId)
						$("select[name='isHot']").val(res.data[0].isHot)
						var featuresArr = res.data[0].features.split(";");
						var elemFeaturesIntro = "";
						for (var i = 0; i < featuresArr.length-1; i++) {
							elemFeaturesIntro += '<div class="l-formInputSmallView">'+
													'<input class="l-formInputSmall" onblur="checkWord(12,this)" placeholder="限六个字" type="text" name="features" value="" />'+
													'<div class="l-InputSmallClose">x</div>'+
												'</div>';
						}
						$('.features').append(elemFeaturesIntro)
						for (var i = 0; i < featuresArr.length; i++) {
							$("input[name='features']").eq(i).val(featuresArr[i])
						}
						$("input[name='longitude']").val(res.data[0].longitude)
						$("input[name='latitude']").val(res.data[0].latitude)
						var elemCouponIntro = "";
						for (var i = 0; i < res.data[0].couponList.length-1; i++) {
							elemCouponIntro += '<div class="l-formBox">'+
													'<span class="l-formCloseBtn">删除</span>'+
													'<div class="l-formRowView">'+
														'<span class="l-formTitle">优惠形式</span>'+
														'<select class="l-formSelect" name="couponType">'+
															'<option value="0">代金券</option>'+
															'<option value="1">折扣券</option>'+
															'<option value="2">赠品券</option>'+
														'</select>'+
													'</div>'+
													'<div class="l-formRowView">'+
														'<span class="l-formTitle">优惠内容</span>'+
														'<input class="l-formInput" onblur="checkWord(100,this)" placeholder="限50个字" type="text" name="couponValue" value="" />'+
														'<span class="l-formInputContent couponIntro db">元代金券面值</span>'+
														'<span class="l-formInputContent couponIntro">%折扣券比例</span>'+
														'<span class="l-formInputContent couponIntro">每份赠品内容</span>'+
														'<span class="l-formInputWarning">内容超长，已截取</span>'+
													'</div>'+
													'<div class="l-formRowView">'+
														'<span class="l-formTitle">转发数量</span>'+
														'<input class="l-formInput forwardingNumber" type="number" name="forwardingNumber" value="1" />'+
														'<span class="l-formInputContent">次转发兑换一次</span>'+
													'</div>'+
												'</div>';
						}
						$('.addCouponBtn').before(elemCouponIntro)
						for (var i = 0; i < res.data[0].couponList.length; i++) {
							$("select[name='couponType']").eq(i).val(res.data[0].couponList[i].couponType)
							$("input[name='couponValue']").eq(i).val(res.data[0].couponList[i].couponValue)
							$("input[name='forwardingNumber']").eq(i).val(res.data[0].couponList[i].exchangeTimes)
							$('.l-formBox').eq(i).find(".couponIntro").eq(res.data[0].couponList[i].couponType).addClass("db").siblings().removeClass("db")
						}
						$("textarea[name='useRule']").val(res.data[0].warmPrompt)
						$("textarea[name='shopIntro']").val(res.data[0].shopIntro)
						$("input[name='openTime']").val(res.data[0].openTime)
						$("input[name='heightTime']").val(res.data[0].heightTime)
//						$("input[name='indoorDevice']").val(res.data[0].indoorDevice)
						var indoorDeviceArr = res.data[0].indoorDevice.split(";");
						var elemIndoorDeviceIntro = "";
						for (var i = 0; i < indoorDeviceArr.length-1; i++) {
							elemIndoorDeviceIntro += '<div class="l-formInputSmallView">'+
													'<input class="l-formInputSmall" onblur="checkWord(12,this)" placeholder="限六个字" type="text" name="indoorDevice" value="" />'+
													'<div class="l-InputSmallClose">x</div>'+
												'</div>';
						}
						$('.indoorDevice').append(elemIndoorDeviceIntro)
						for (var i = 0; i < indoorDeviceArr.length; i++) {
							$("input[name='indoorDevice']").eq(i).val(indoorDeviceArr[i])
						}
//						$("input[name='signDish']").val(res.data[0].signDish)
						$("input[name='shareTitle']").val(res.data[0].shareTitle)
						if (res.data[0].shareImagePath) {
							var elemShareImg = "<div><img src="+res.data[0].shareImagePath+" /><a href='javascript:void(0);' class='btn delBtn'>删除</a></div>"
							$('#ossfile2').empty().append(elemShareImg);
							$('#container2').addClass('dn')
						}
						$("textarea[name='shopActivity']").val(res.data[0].shopActivity)
						$("input[name='flowTitle']").val(res.data[0].flowTitle)
						$("textarea[name='flowContent']").val(res.data[0].flowContent)
					} else{
						$('.modeifyShop').hide()
					}
				} else if (res.errorCode==0007){
					showModelMes("登陆已过期，请重新登陆")
					$('.yes').hide().siblings().show()
				} else{
					showModelMes(res.errorMsg)
				}
			},
			error:function(res){
				console.log(res)
			}
		});
	} else{
		window.location.href="login.html"
	}
	
	//注销
	$('.l-logOff').click(function(){
		set_cache("token","")
		window.location.href="login.html";
	})
	
	//返回登陆
	$('.backLogin').click(function(){
		window.location.href="login.html"
	})
	
	//关闭弹出框
	$('.yes').click(function(){
		hideModel()
	})
	
	//显示优惠卷简介
	$('.l-shopInfo').on("change",'select[name="couponType"]',function(){
		$(this).parent().next().children('.couponIntro').eq($(this).find("option:selected").index()).addClass("db").siblings().removeClass("db")
	})
	//增加优惠卷
	$('.addCouponBtn .l-formButton').click(function(){
		var elemCoupon = '<div class="l-formBox">'+
							'<span class="l-formCloseBtn">删除</span>'+
							'<div class="l-formRowView">'+
								'<span class="l-formTitle">优惠形式</span>'+
								'<select class="l-formSelect" name="couponType">'+
									'<option value="0">代金券</option>'+
									'<option value="1">折扣券</option>'+
									'<option value="2">赠品券</option>'+
								'</select>'+
							'</div>'+
							'<div class="l-formRowView">'+
								'<span class="l-formTitle">优惠内容</span>'+
								'<input class="l-formInput" onblur="checkWord(100,this)" placeholder="限50个字" type="text" name="couponValue" value="" />'+
								'<span class="l-formInputContent couponIntro db">元代金券面值</span>'+
								'<span class="l-formInputContent couponIntro">%折扣券比例</span>'+
								'<span class="l-formInputContent couponIntro">每份赠品内容</span>'+
								'<span class="l-formInputWarning">内容超长，已截取</span>'+
							'</div>'+
							'<div class="l-formRowView">'+
								'<span class="l-formTitle">转发数量</span>'+
								'<input class="l-formInput forwardingNumber" type="number" name="forwardingNumber" value="1" />'+
								'<span class="l-formInputContent">次转发兑换一次</span>'+
							'</div>'+
						'</div>';
//		console.log(elemCoupon)
		$('.addCouponBtn').before(elemCoupon)
	})
	//删除优惠卷
	$('.l-shopInfo').on("click",'.l-formCloseBtn',function(){
		if ($('select[name="couponType"]').length>1) {
			$(this).parent().remove()
		} else{
			showModelMes("至少设置一种优惠券！")
		}
	})
	//控制优惠卷转发数量
	$('.l-shopInfo').on("blur",'.forwardingNumber',function(){
		if($(this).val()<1 || !$(this).val()){
			$(this).val(1);
		}
	})
	
	//删除图片
	$('#ossfile').on("click","a",function(){
		$(this).parent().remove()
		$('#container').removeClass('dn');
		var length = $('#ossfile>div').length;
		var hasLength = $('.dataImg').length;
		uploader.files.splice((length-hasLength),uploader.files.length)
	})
	$('#ossfile1').on("click","a",function(){
		$(this).parent().remove()
		$('#container1').removeClass('dn');
	})
	$('#ossfile2').on("click","a",function(){
		$(this).parent().remove()
		$('#container2').removeClass('dn');
	})
	
	//控制二维码大小
	$('input[name="qrcode"]').blur(function(){
		if($(this).val()<100){
			$(this).val(100);
		}
		if($(this).val()>800){
			$(this).val(800);
		}
	})
	//控制小程序码大小
	$('input[name="xcxcode"]').blur(function(){
		if($(this).val()<280){
			$(this).val(280);
		}
		if($(this).val()>800){
			$(this).val(800);
		}
	})
	
	//生成二维码
	$('.qrcode .l-codeButton').click(function(){
		if (!$('input[name="qrcode"]').val()) {
			showModelMes("请输入二维码需要大小（单位：px）")
		} else{
			$.ajax({
				type:"get",
				url:"http://139.196.73.183:8088/qrcode/geturl?shopId="+shopId+"&width="+parseInt($('input[name="qrcode"]').val()),
				async:true,
				beforeSend: function(request) {
		            request.setRequestHeader("Authorization", token);
		        },
				contentType:"application/json",
				success:function(result){
//					console.log(result)
					if (result.errorCode==0000) {
						$('.qrcodeImg').attr("src",result.data)
					} else if (result.errorCode==0007){	
						showModelMes("登陆已过期，请重新登陆")
						$('.yes').hide().siblings().show()
					} else{
						showModelMes(result.errorMsg)
					}
				},
				error:function(xhr){
					console.log(xhr)
				}
			});
		}
	})
	
	//生成小程序码
	$('.xcxcode .l-codeButton').click(function(){
		if (!$('input[name="xcxcode"]').val()) {
			showModelMes("请输入小程序码需要大小（单位：px）")
		} else{
			$('.xcxcodeImg').attr("src","http://139.196.73.183:8088/qrcode/getwxcode?shopId="+shopId+"&width="+parseInt($('input[name="xcxcode"]').val()))
		}
	})
	
	//添加餐厅标签
	$(".featuresBtn").click(function(){
		var elemFeaturesInput = '<div class="l-formInputSmallView">'+
									'<input class="l-formInputSmall" onblur="checkWord(12,this)" placeholder="限六个字" type="text" name="features" value="" />'+
									'<div class="l-InputSmallClose">x</div>'+
								'</div>';
		$('.features').append(elemFeaturesInput)
	})
	//删除餐厅标签
	$('.features').on("click",'.l-InputSmallClose',function(){
		if ($('.features .l-formInputSmallView').length>1) {
			$(this).parent().remove()
		} else{
			showModelMes("至少添加一种标签！")
		}
	})
	
	//添加餐厅设施
	$(".indoorDeviceBtn").click(function(){
		var elemIndoorDeviceInput = '<div class="l-formInputSmallView">'+
									'<input class="l-formInputSmall" onblur="checkWord(12,this)" placeholder="限六个字" type="text" name="indoorDevice" value="" />'+
									'<div class="l-InputSmallClose">x</div>'+
								'</div>';
		$('.indoorDevice').append(elemIndoorDeviceInput)
	})
	//删除餐厅设施
	$('.indoorDevice').on("click",'.l-InputSmallClose',function(){
		if ($('.indoorDevice .l-formInputSmallView').length>1) {
			$(this).parent().remove()
		} else{
			showModelMes("至少添加一种餐厅设施！")
		}
	})
	
	//回车隐藏提示框
    document.onkeydown=function(e){
		var keycode=document.all?event.keyCode:e.which;
		if(keycode==13){
			if($(".l-mask").is(":visible")){
				$('.yes').click()
				return false;
			}
		};
	}
})

//限制长度
function checkWord(len,evt){
	//len为英文字符的个数，中文自动为其一般数量,evt是欲检测的对象
	var str = evt.value;
	var myLen = 0;
	for(i=0; i<str.length&&myLen<=len; i++){
		if(str.charCodeAt(i)>0&&str.charCodeAt(i)<128)
			myLen++;
		else
			myLen+=2;
		}
	if ($(evt).hasClass("l-formInput")||$(evt).hasClass("l-formTextarea")) {
		if(myLen>len){
			$(evt).siblings(".l-formInputWarning").show().fadeOut(1200)
			evt.value = str.substring(0,i-1);
		}
	} else{
		if(myLen>len){
			$(evt).parents(".l-formInputSmall-box").siblings(".l-childRowView").children(".l-formInputWarning").show().fadeOut(1200)
			evt.value = str.substring(0,i-1);
		}
	}
}

//验证电话号码
function validatePhoneNum(num){
	var phoneNum = num.value;
	if (!(/^(\d|-)*$/.test(phoneNum))) {
		showModelMes("请输入正确号码！")
	}
}

//格式划两位数
function twonumber(num) {
	if (num < 10) {
		return "0" + num;
	}
    return num;
}
//控制时间小时
function ctrlTimeH(elem){
	$(elem).blur(function(){
		if($(elem).val()<0){
			$(elem).val(0);
		}else if ($(elem).val()>23) {
			$(elem).val(23);
		} else{
			$(elem).val(twonumber(new Number($(elem).val())));
		}
	})
}
//控制时间分钟
function ctrlTimeM(elem){
	$(elem).blur(function(){
		if($(elem).val()<0){
			$(elem).val(0);
		}else if ($(elem).val()>59) {
			$(elem).val(59);
		} else{
			$(elem).val(twonumber(new Number($(elem).val())));
		}
	})
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

//获取地址值
(function ($) {
    $.getUrlParam = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    }
})(jQuery);

//新增店铺设置
function addShopFrom(form){
	$('.l-loading').css("display","flex")
	//图片数据
//	var imgArr = [];
//	$('#ossfile img').each(function(){
//		imgArr.push($(this).attr("src"))
//	})
//	var imgArrIntro = imgArr.join(";");
	//特色数据
	var featuresArr = [];
    $("input[name='features']").each(function(){
    	if ($(this).val()) {
    		featuresArr.push($(this).val())
    	}
    })
    var features = featuresArr.join(";");
    //优惠卷数据
    var couponList = [];
    $("input[name='couponValue']").each(function(index){
    	if ($(this).val()) {
    		var couponInfo = {
    			couponType:$("select[name='couponType']").eq(index).val(),
    			couponValue:$(this).val(),
    			exchangeTimes:Number($("input[name='forwardingNumber']").eq(index).val())
    		}
    		couponList.push(couponInfo);
    	}
    })
    //招牌菜品数据
    var specialDishes = [];
    $('#ossfile img').each(function(index){
//  	if ($("input[name='dishName']").eq(index).val()&&$("input[name='dishPrice']").eq(index).val()) {
    		var dishesInfo = {
    			imagePath:$(this).attr("src"),
    			dishName:$("input[name='dishName']").eq(index).val(),
    			dishPrice:$("input[name='dishPrice']").eq(index).val()
    		}
    		specialDishes.push(dishesInfo);
//		}else{
//  		specialDishes = 1;
//  		return false;
//  	}
    })
	//餐厅设施数据
	var indoorDeviceArr = [];
    $("input[name='indoorDevice']").each(function(){
    	if ($(this).val()) {
    		indoorDeviceArr.push($(this).val())
    	}
    })
    var indoorDevice = indoorDeviceArr.join(";");
    
    if (!$('#ossfile1>div>img').attr("src")) {
    	$('.l-loading').hide()
    	showModelMes("请上传顶部图片！")
    } else if (specialDishes.length<1){
    	$('.l-loading').hide()
    	showModelMes("请添加至少一种招牌菜品！")
//  } else if (specialDishes == 1){
//  	$('.l-loading').hide()
//  	showModelMes("请输入招牌菜品名字或价格！")
    } else if (!form.shopName.value){
    	$('.l-loading').hide()
    	showModelMes("请输入餐厅名称！")
    } else if (!form.shopAddress.value){
    	$('.l-loading').hide()
    	showModelMes("请输入餐厅地址！")
    } else if (!form.synopsis.value){
    	$('.l-loading').hide()
    	showModelMes("请输入餐厅简介！")
    } else if (!form.shopIntro.value){
    	$('.l-loading').hide()
    	showModelMes("请输入详细介绍！")
    } else if (!form.contactWay.value){
    	$('.l-loading').hide()
    	showModelMes("请输入联系方式！")
    } else if (!(/^(\d|-)*$/.test(form.contactWay.value))){
    	$('.l-loading').hide()
    	showModelMes("请输入正确手机 / 电话号码！")
    } else if (!features){
    	$('.l-loading').hide()
    	showModelMes("请添加至少一种餐厅标签！")
    } else if (couponList.length<1){
    	$('.l-loading').hide()
    	showModelMes("请设置至少一种优惠券！")
    } else if (!form.openTime.value){
    	$('.l-loading').hide()
    	showModelMes("请输入营业时间！")
    } else if (!indoorDevice){
    	$('.l-loading').hide()
    	showModelMes("请添加至少一种餐厅设施！")
    } else if (!form.shareTitle.value){
    	$('.l-loading').hide()
    	showModelMes("请输入分享卡标题！")
    } else if (!$('#ossfile2>div>img').attr("src")){
    	$('.l-loading').hide()
    	showModelMes("请上传分享卡图片！")
    } else {
    	var data = {
    		adminUserId:userId,
    		classId:form.classId.value,
    		contactWay:form.contactWay.value,
    		couponList:couponList,
    		features:features,
    		heightTime:form.heightTime.value,
    		indoorDevice:indoorDevice,
//  		introImages:imgArrIntro,
    		isHot:form.isHot.value,
    		openTime:form.openTime.value,
//  		shopActivity:form.shopActivity.value,
    		shopAddress:form.shopAddress.value,
    		shopIntro:form.shopIntro.value,
    		shopName:form.shopName.value,
//  		signDish:"0",
    		warmPrompt:form.useRule.value,
    		simpleIntro:form.synopsis.value,
    		flowTitle:form.flowTitle.value,
    		flowContent:form.flowContent.value,
    		longitude:form.longitude.value,
    		latitude:form.latitude.value,
    		topImagePath:$('#ossfile1>div>img').attr("src"),
    		shareTitle:form.shareTitle.value,
    		shareImagePath:$('#ossfile2>div>img').attr("src"),
    		specialDishes:specialDishes
    	}
//  	console.log(data)
    	$.ajax({
			type:"post",
			url:"http://139.196.73.183:8088/shop/add",
			async:true,
			data:JSON.stringify(data),
			beforeSend: function(request) {
	            request.setRequestHeader("Authorization", token);
	        },
			contentType:"application/json",
			success:function(result){
//				console.log(result)
				if (result.errorCode==0000) {
					$('.l-loading').hide()
					showModelMes("设置成功！")
					setTimeout(function(){
						window.location.reload()
					},500)
				} else if (result.errorCode==0007){	
					$('.l-loading').hide()
					showModelMes("登陆已过期，请重新登陆")
					$('.yes').hide().siblings().show()
				} else{
					$('.l-loading').hide()
					showModelMes(result.errorMsg)
				}
			},
			error:function(xhr){
				console.log(xhr)
			}
		});
    }
}

//修改店铺设置
function modeifyShopFrom(form){
	$('.l-loading').css("display","flex")
	//图片数据
//	var imgArr = [];
//	$('#ossfile img').each(function(){
//		imgArr.push($(this).attr("src"))
//	})
//	var imgArrIntro = imgArr.join(";");
	//特色数据
	var featuresArr = [];
    $("input[name='features']").each(function(){
    	if ($(this).val()) {
    		featuresArr.push($(this).val())
    	}
    })
    var features = featuresArr.join(";");
    //优惠卷数据
    var couponList = [];
    $("input[name='couponValue']").each(function(index){
    	if ($(this).val()) {
    		var couponInfo = {
    			couponType:$("select[name='couponType']").eq(index).val(),
    			couponValue:$(this).val(),
    			exchangeTimes:Number($("input[name='forwardingNumber']").eq(index).val())
    		}
    		couponList.push(couponInfo);
    	}
    })
    //招牌菜品数据
    var specialDishes = [];
    $('#ossfile img').each(function(index){
//  	if ($("input[name='dishName']").eq(index).val()&&$("input[name='dishPrice']").eq(index).val()) {
    		var dishesInfo = {
    			imagePath:$(this).attr("src"),
    			dishName:$("input[name='dishName']").eq(index).val(),
    			dishPrice:$("input[name='dishPrice']").eq(index).val()
    		}
    		specialDishes.push(dishesInfo);
//  	}else{
//  		specialDishes = 1;
//  		return false;
//  	}
    })
    //餐厅设施数据
	var indoorDeviceArr = [];
    $("input[name='indoorDevice']").each(function(){
    	if ($(this).val()) {
    		indoorDeviceArr.push($(this).val())
    	}
    })
    var indoorDevice = indoorDeviceArr.join(";");
    
    if (!$('#ossfile1>div>img').attr("src")) {
    	$('.l-loading').hide()
    	showModelMes("请上传顶部图片！")
    } else if (specialDishes.length<1){
    	$('.l-loading').hide()
    	showModelMes("请添加至少一种招牌菜品！")
//  } else if (specialDishes == 1){
//  	$('.l-loading').hide()
//  	showModelMes("请输入招牌菜品名字或价格！")
    } else if (!form.shopName.value){
    	$('.l-loading').hide()
    	showModelMes("请输入餐厅名称！")
    } else if (!form.shopAddress.value){
    	$('.l-loading').hide()
    	showModelMes("请输入餐厅地址！")
    } else if (!form.synopsis.value){
    	$('.l-loading').hide()
    	showModelMes("请输入餐厅简介！")
    } else if (!form.shopIntro.value){
    	$('.l-loading').hide()
    	showModelMes("请输入详细介绍！")
    } else if (!form.contactWay.value){
    	$('.l-loading').hide()
    	showModelMes("请输入联系方式！")
    } else if (!(/^(\d|-)*$/.test(form.contactWay.value))){
    	$('.l-loading').hide()
    	showModelMes("请输入正确手机 / 电话号码！")
    } else if (!features){
    	$('.l-loading').hide()
    	showModelMes("请添加至少一种餐厅标签！")
    } else if (couponList.length<1){
    	$('.l-loading').hide()
    	showModelMes("请设置至少一种优惠券！")
    } else if (!form.openTime.value){
    	$('.l-loading').hide()
    	showModelMes("请输入营业时间！")
    } else if (!indoorDevice){
    	$('.l-loading').hide()
    	showModelMes("请添加至少一种餐厅设施！")
    } else if (!form.shareTitle.value){
    	$('.l-loading').hide()
    	showModelMes("请输入分享卡标题！")
    } else if (!$('#ossfile2>div>img').attr("src")){
    	$('.l-loading').hide()
    	showModelMes("请上传分享卡图片！")
    } else {
    	var data = {
    		shopId:shopId,
    		adminUserId:userId,
    		classId:form.classId.value,
    		contactWay:form.contactWay.value,
    		couponList:couponList,
    		features:features,
    		heightTime:form.heightTime.value,
    		indoorDevice:indoorDevice,
//  		introImages:imgArrIntro,
    		isHot:form.isHot.value,
    		openTime:form.openTime.value,
//  		shopActivity:form.shopActivity.value,
    		shopAddress:form.shopAddress.value,
    		shopIntro:form.shopIntro.value,
    		shopName:form.shopName.value,
//  		signDish:form.signDish.value,
    		warmPrompt:form.useRule.value,
    		simpleIntro:form.synopsis.value,
    		flowTitle:form.flowTitle.value,
    		flowContent:form.flowContent.value,
    		longitude:form.longitude.value,
    		latitude:form.latitude.value,
    		topImagePath:$('#ossfile1>div>img').attr("src"),
    		shareTitle:form.shareTitle.value,
    		shareImagePath:$('#ossfile2>div>img').attr("src"),
    		specialDishes:specialDishes
    	}
//  	console.log(data)
    	$.ajax({
			type:"post",
			url:"http://139.196.73.183:8088/shop/updateshop",
			async:true,
			data:JSON.stringify(data),
			beforeSend: function(request) {
	            request.setRequestHeader("Authorization", token);
	        },
			contentType:"application/json",
			success:function(result){
//				console.log(result)
				if (result.errorCode==0000) {
					$('.l-loading').hide()
					showModelMes("修改成功！")
					$("html,body").animate({scrollTop:0}, 200);
				} else if (result.errorCode==0007){	
					$('.l-loading').hide()
					showModelMes("登陆已过期，请重新登陆")
					$('.yes').hide().siblings().show()
				} else{
					$('.l-loading').hide()
					showModelMes(result.errorMsg)
				}
			},
			error:function(xhr){
				console.log(xhr)
			}
		});
    }
}
