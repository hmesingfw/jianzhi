
//购课车数量
$.ajax({
	url:'/plus/Course/GetcourseNum.aspx',
	success:function(num){
		var val = num.split('document.write(')[1].split("'")[1];
		if(val!=0){
			$('#cartnum').append('<span class="num">'+val+'</span>');
		};
	}
});

//获取用户登录信息
function getUserInfo(userUrl,type,other){
	var index = userUrl.index;
	var loginout = userUrl.loginout;
	var editinfo = userUrl.editinfo;
	var message = userUrl.message;
	var myCourse = userUrl.myCourse;
	var courseOrder = userUrl.courseOrder;
	var userLogin = userUrl.userLogin;
	var courseUrl = userUrl.courseUrl;
	$.ajax({
		type: "get",
		dataType: "xml",
		url: "/plus/ajaxs.ashx?a=checkislogin",
		success: function (xml) {
			
			var islogin = $(xml).find("login islogin").text();
			var messageNum = '';
			if($(xml).find("login message").text()!=0){
				messageNum = '<i class="num">'+$(xml).find("login message").text()+'</i>';
			};

			if (islogin == 'false'){
				if(other=='index'){
					$('.index-right-user').html('<div class="u-avatar">'+
												'<img src="/sysimg/avatar.jpg" />'+
												'</div>'+
												'<div class="u-name">Hi~ 同学</div>'+
												'<div class="u-btn"><a href="'+userLogin+'" target="_blank">登录</a></div>'+
												'<div class="u-task">'+
												'<div class="task-name" style="text-align: center;">请先登录<br />开启你的学习之旅</div>'+
												'<div class="task-link"><a href="'+userLogin+'" target="_blank">查看我的任务<i class="edufont e-icon-top-copy"></i></a></div>'+
												'</div>');
					
				}
			}else{

				if(other=='index'){
					
					$('.index-right-user').html('<div class="u-avatar">'+
												'<img src="/UploadFiles/avatar/' + $(xml).find("login userid").text() + '.jpg" class="m-avatar" />'+
												'</div>'+
												'<div class="u-name">Hi~ ' + $(xml).find("login username").text()+ '</div>'+
												'<div class="u-bt clearfix">'+
												'<ul>'+
												'<li><a href="'+myCourse+'" target="_blank"><i class="edufont e-icon-kecheng1"></i><p>我的课程</p></a></li>'+
												'<li><a href="'+courseOrder+'" target="_blank"><i class="edufont e-icon-ts-icon-order"></i><p>我的订单</p></a></li>'+
												'</ul>'+
												'</div>'+
												'<div class="u-task">'+
												
												'<div class="task-link"><a href="'+myCourse+'" target="_blank">查看我的任务<i class="edufont e-icon-top-copy"></i></a></div>'+
												'</div>');

					$.ajax({
						type: "get",
						dataType: "json",
						url:'/plus/course/courseajax.ashx?action=getuserlearnperiod',
						success:function(data){
							var couseName = data.learncourse.name;
							var courseid = data.learncourse.courseid;
							var teachway = Number(data.learncourse.teachway);
							switch(teachway)
							{
								case 0:
									courseType = '面授'
									break;
								case 1:
									courseType = '点播'
									break;
								case 2:
									courseType = '组合'
									break;
								case 5:
									courseType = '直播'
									break;
							}
							$('.index-right-user').find('.task-link').before('<div class="task-name"><a href="'+courseUrl+''+courseid+'" target="_blank">【'+courseType+'】'+couseName+'</a></div>');
						}
					})
				};

				if(type=='sub'){
					var str = '<div class="message"><a href="'+message+'">'+messageNum+'<i class="subfont subicon-xiaoxi"></i></a></div><div class="haed-user"><img src="/UploadFiles/avatar/' + $(xml).find("login userid").text() + '.jpg" class="m-avatar" />' + $(xml).find("login username").text()+ '<div class="u-navdropmenu"><span><a href="'+index+'" target="_blank"><i class="subfont subicon-shouye"></i>我的主页</a></span><span><a href="'+editinfo+'" target="_blank"><i class="subfont subicon-bianji"></i>修改资料</a></span><span><a href="'+myCourse+'" target="_blank"><i class="subfont subicon-kecheng1"></i>我的课程</a></span><span><a href="'+loginout+'" target="_blank"><i class="subfont subicon-web-quit"></i>退出</a></span></div></div>';
				}else{
					var str = '<div class="message"><a href="'+message+'">'+messageNum+'<i class="edufont e-icon-xiaoxi"></i></a></div><div class="haed-user"><img src="/UploadFiles/avatar/' + $(xml).find("login userid").text() + '.jpg" class="m-avatar" />' + $(xml).find("login username").text()+ '<div class="u-navdropmenu"><span><a href="'+index+'" target="_blank"><i class="edufont e-icon-shouye"></i>我的主页</a></span><span><a href="'+editinfo+'" target="_blank"><i class="edufont e-icon-bianji"></i>修改资料</a></span><span><a href="'+myCourse+'" target="_blank"><i class="edufont e-icon-kecheng1"></i>我的课程</a></span><span><a href="'+loginout+'" target="_blank"><i class="edufont e-icon-web-quit"></i>退出</a></span></div></div>';
					
				};
				$("#nologin").html(str);
				$('.haed-user').hover(function(){
					$(this).find('.u-navdropmenu').show();
				},function(){
					$(this).find('.u-navdropmenu').hide();
				});
				dealingWithAnErrorPicture('.m-avatar','/sysimg/avatar.jpg');
			};

		}
	});

};


dealingWithAnErrorPicture('.m-img','/sysimg/nopic_225_142.png');
dealingWithAnErrorPicture('.m-avatar','/sysimg/avatar.jpg');
//处理地址错误图片
function dealingWithAnErrorPicture(range,url){
	var $target = $('img');
	if(range){
		$target = $(range);
	};
	
	$target.each(function(){
		
		var that = this;
		var imgSrc = $(this).attr('src');
		//判断是否是字段链接，字段链接不处理
		if(imgSrc.substr(0,1)!='{'){
		    var ImgObj = new Image(); //创建图片对象
		    ImgObj.src = imgSrc;
		    ImgObj.onerror = function(){
			that.src = url;
						
		    }

		};

	});
	
};

function imgLoad(obj,img){
	//懒加载
	$('.'+obj).lazyload({
		effect : "fadeIn" ,
		placeholder :img,   
		event : "scroll" 
	});   

};


//执行页面动画
$('.page-animate').each(function() {
    var ofTop = $(this).offset().top-$(window).height();
    var maxTop = $(this).offset().top+$(this).outerHeight();
	var type = $(this).attr('data-type');
	if(!type){
		type = 'fadeInUp';
	};
	var $that = $(this);
	if($(window).scrollTop()>ofTop&&$(window).scrollTop()<maxTop){
		var delay = $that.attr('data-delay');
		if(delay){
			$that.css({animationDelay:''+delay+'s'});
		};
		$that.removeClass('page-animate').addClass('animated '+type+'');
		if($that.find('.page-timer').length!=0){
			$that.find('.page-timer').each(count);
		};

	};
	$(window).scroll(function(){
		if($(window).scrollTop()>ofTop&&$(window).scrollTop()<maxTop){
			$that.removeClass('page-animate').addClass('animated '+type+'');
			if($that.find('.page-timer').length!=0){
				$that.find('.page-timer').each(count);
			}
		};
	});


});


function subNav(text){
	$('.sub-header').find('li').each(function() {
        if($(this).text()==text){
			$(this).addClass('active');
		};
    });
};

subContMinHeight();
$(window).resize(function(){
    subContMinHeight();
});
function subContMinHeight(){
    var minH = $(window).height()-$('.sub-header').outerHeight(true)-$('.sub-footer').outerHeight(true);
    $('#subContMinHeight').css({minHeight:minH+'px'});
};

