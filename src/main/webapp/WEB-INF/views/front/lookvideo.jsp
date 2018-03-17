<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!doctype html>
<html>
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <script>
    	$(function(){
    		$(".head1-center").height($(window).height()-$(".head1").height());

            setInterval(function(){
                var currentTime = $("#current").text();     //当前播放时间
                var duration = $("#duration").text();       //视频总时长
                $.post('${ctxF}/courseLookRecord',{courseid:'${zcourse.id}',current:currentTime,duration:duration},function(){

                })
            },5000)


            $("#video").on(
            "timeupdate", 
            function(event){
                onTrackedVideoFrame(this.currentTime, this.duration);
            });

            function onTrackedVideoFrame(currentTime, duration){
              
                $("#current").text(Math.floor(currentTime));
                $("#duration").text(Math.floor(duration));
            }
    	})


        
    </script>
    <style>
    	html,body{
    		padding: 0;
    		margin: 0;
    	}
   		.head1{
   			width: 100%;
   			background: #14191e;
   			overflow: hidden;
   			line-height: 50px;
   		}
   		.head1-lt{
   			float: left;
   			margin-left: 50px;
   		}
   		.head1-lt h1{
   			color: #fff;
   			font-size: 18px;
   		}
   		.head1-rt{
   			float: right;
   			margin-right: 50px;
   		}
   		.head1-rt img{
   			width: 30px;
   			height: 30px;
   			border-radius: 100%;
   			float: left;
   			margin-top: 20px;
   		}
   		.head1-rt span{
   			color: #fff;
   			line-height: 70px;
   			margin-left: 20px;
   		}
   		.head1-center{
   			background: #000000;
   			width: 100%;
   			
   		}
   		.head1-center video{
   			width: 100%;
   			height: 100%;
   		}
    </style>
</head>
<body>
    <div style="display: none;" id="current"></div>
    <div style="display: none;" id="duration"></div>
	<div class="head1">
		<div class="head1-lt"><h1>${zcourse.title}</h1></div>
		<div class="head1-rt"><img src="${sessionMyinfo.img}"/><span>${sessionMyinfo.name}</span></div>
	</div>
	<div class="head1-center">
		<video controls="controls" id="video" src="${zcourse.files}"></video>
	</div>
	
</body>
</html>