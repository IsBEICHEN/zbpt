;
! function() {
	var layer = layui.layer,
		form = layui.form,
		carousel = layui.carousel;

	// 背景图片轮播
	carousel.render({
		elem: '#login_carousel',
		width: '100%',
		height: '100%',
		interval: 3000,
		arrow: 'none',
		anim: 'fade',
		indicator: 'none'
	});

	// 验证码值存储变量
	var vailCode;
	// 执行获取验证码
	refCode();

	// 点击刷新验证码
	$("#refCode_login_img").on("click", function() {
		refCode();
	});



	//监听提交  
	form.on("submit(login)", function() {
		$.ajax({
			url: "user/login.do",
			type: "post",
			data: {
				"account": $("#account").val(),
				"password": $("#password").val()
			},
			success: function(result) {
				if(result.status == 0) {
					location = "index.html";
				} else {
					refCode();
					$("#password").val("");
					layer.alert(result.msg, {
						title: '提交结果'
					});
				}
			}
		});

		return false;
	});
}();