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
//				console.log(res)
				if (res.errorCode==0000) {
					if (res.data.length>0) {
						$('.addShop').hide()
						$('.qrcode').show()
						$("html,body").animate({scrollTop:0}, 200);
						$('.l-topBar').slideDown(1000)
						setTimeout(function(){
							$('.l-topBar').slideUp(1000)
						},5000)
						//已有店铺赋值
						shopId = res.data[0].shopId;
						var imgArr = res.data[0].introImages.split(";");
						var elemImgIntro = "";
						for (var i = 0; i < imgArr.length; i++) {
							elemImgIntro += "<div><img src="+imgArr[i]+" /><a href='javascript:void(0);' class='btn delBtn'>删除</a></div>"
						}
						$('#ossfile').empty().append(elemImgIntro)
						if (imgArr.length == 9) {
							$('#container').addClass('dn')
						}
						$("input[name='shopName']").val(res.data[0].shopName)
						$("input[name='shopAddress']").val(res.data[0].shopAddress)
						$("textarea[name='synopsis']").val(res.data[0].simpleIntro)
						$("input[name='contactWay']").val(res.data[0].contactWay)
						$("select[name='classId']").val(res.data[0].classId)
						$("select[name='isHot']").val(res.data[0].isHot)
						var featuresArr = res.data[0].features.split(";");
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
						$("input[name='indoorDevice']").val(res.data[0].indoorDevice)
						$("input[name='signDish']").val(res.data[0].signDish)
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
	
	//设置控制时间
	ctrlTimeH('#openStartTimeH')
	ctrlTimeM('#openStartTimeM')
	ctrlTimeH('#openEndTimeH')
	ctrlTimeM('#openEndTimeM')
	ctrlTimeH('#heightStartTimeH')
	ctrlTimeM('#heightStartTimeM')
	ctrlTimeH('#heightEndTimeH')
	ctrlTimeM('#heightEndTimeM')
	
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
	
	//生成二维码
	$('.l-codeButton').click(function(){
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
	if(myLen>len){
		$(evt).siblings(".l-formInputWarning").show().fadeOut(1200)
		evt.value = str.substring(0,i-1);
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
	//图片数据
	var imgArr = [];
	$('#ossfile img').each(function(){
		imgArr.push($(this).attr("src"))
	})
	var imgArrIntro = imgArr.join(";");
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
    if (!imgArrIntro) {
    	showModelMes("请上传 1 ~ 9 张图片！")
    } else if (!form.shopName.value){
    	showModelMes("请输入餐厅名称！")
    } else if (!form.shopAddress.value){
    	showModelMes("请输入餐厅地址！")
    } else if (!form.synopsis.value){
    	showModelMes("请输入餐厅简介！")
    } else if (!form.shopIntro.value){
    	showModelMes("请输入详细介绍！")
    } else if (!form.contactWay.value){
    	showModelMes("请输入联系方式！")
    } else if (!features){
    	showModelMes("请输入 1 ~ 3 种餐厅特色！")
    } else if (couponList.length<1){
    	showModelMes("请设置至少一种优惠券！")
    } else if (!form.openTime.value){
    	showModelMes("请输入营业时间！")
    } else if (!form.indoorDevice.value){
    	showModelMes("请输入室内设施！")
    } else if (!form.signDish.value){
    	showModelMes("请输入招牌菜品！")
    } else {
    	var data = {
    		adminUserId:userId,
    		classId:form.classId.value,
    		contactWay:form.contactWay.value,
    		couponList:couponList,
    		features:features,
    		heightTime:form.heightTime.value,
    		indoorDevice:form.indoorDevice.value,
    		introImages:imgArrIntro,
    		isHot:form.isHot.value,
    		openTime:form.openTime.value,
//  		shopActivity:form.shopActivity.value,
    		shopAddress:form.shopAddress.value,
    		shopIntro:form.shopIntro.value,
    		shopName:form.shopName.value,
    		signDish:form.signDish.value,
    		warmPrompt:form.useRule.value,
    		simpleIntro:form.synopsis.value,
    		flowTitle:form.flowTitle.value,
    		flowContent:form.flowContent.value,
    		longitude:form.longitude.value,
    		latitude:form.latitude.value
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
					showModelMes("设置成功！")
					setTimeout(function(){
						window.location.reload()
					},500)
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
}

//修改店铺设置
function modeifyShopFrom(form){
	//图片数据
	var imgArr = [];
	$('#ossfile img').each(function(){
		imgArr.push($(this).attr("src"))
	})
	var imgArrIntro = imgArr.join(";");
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
    if (!imgArrIntro) {
    	showModelMes("请上传 1 ~ 9 张图片！")
    } else if (!form.shopName.value){
    	showModelMes("请输入餐厅名称！")
    } else if (!form.shopAddress.value){
    	showModelMes("请输入餐厅地址！")
    } else if (!form.synopsis.value){
    	showModelMes("请输入餐厅简介！")
    } else if (!form.shopIntro.value){
    	showModelMes("请输入详细介绍！")
    } else if (!form.contactWay.value){
    	showModelMes("请输入联系方式！")
    } else if (!features){
    	showModelMes("请输入 1 ~ 3 种餐厅特色！")
    } else if (couponList.length<1){
    	showModelMes("请设置至少一种优惠券！")
    } else if (!form.openTime.value){
    	showModelMes("请输入营业时间！")
    } else if (!form.indoorDevice.value){
    	showModelMes("请输入室内设施！")
    } else if (!form.signDish.value){
    	showModelMes("请输入招牌菜品！")
    } else {
    	var data = {
    		shopId:shopId,
    		adminUserId:userId,
    		classId:form.classId.value,
    		contactWay:form.contactWay.value,
    		couponList:couponList,
    		features:features,
    		heightTime:form.heightTime.value,
    		indoorDevice:form.indoorDevice.value,
    		introImages:imgArrIntro,
    		isHot:form.isHot.value,
    		openTime:form.openTime.value,
//  		shopActivity:form.shopActivity.value,
    		shopAddress:form.shopAddress.value,
    		shopIntro:form.shopIntro.value,
    		shopName:form.shopName.value,
    		signDish:form.signDish.value,
    		warmPrompt:form.useRule.value,
    		simpleIntro:form.synopsis.value,
    		flowTitle:form.flowTitle.value,
    		flowContent:form.flowContent.value,
    		longitude:form.longitude.value,
    		latitude:form.latitude.value
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
					showModelMes("修改成功！")
					$("html,body").animate({scrollTop:0}, 200);
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
}
