<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!doctype html>
<html>
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>    
    <style>
    	html,body{
    		padding: 0;
    		margin: 0;
            overflow-y: hidden;
    	}
        li{
            list-style-type: none;
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

        .live_rightbox{
            width: 250px;
            height: 100%;
            position: fixed;
            z-index: 999;
            top: 0px;
            right: 0px;
            background: #14191e;
        }        
        .tab_name{
            /*height: 74px;*/
            line-height: 74px;
            margin-top: -18px;
            background: #03a9f4;
            color: #fff;
            text-align: center;
        }
        .positionrigh{
            position: relative;
        }
        
        .live_rightbox ul{
            padding-left: 20px;
        }
        .live_rightbox li{
            color:#787d82;
            margin-top: 10px;            
        }
        .tupiao{
            position: absolute;
            left: -22px;
            color: #fff;
            top: 50%;
            background: #14191e;
            width: 30px;
            line-height: 40px;
            height: 40px;
            text-align: center;
            border-radius: 10px;
        }
        .listul{
            position: relative;
            height: 700px;
            overflow: scroll;
            width: 300px;
            overflow-x:hidden;
        }
        .listul li div{
            cursor: pointer;
        }
    </style>
    <script type="text/javascript">
        $(function(){
            $(".head1-center").height($(window).height()-$(".head1").height());

            var rightWidth = $('.live_rightbox').outerWidth();
            $("#hide_btn").click(function () {
                if ($(this).attr('data-flag') == 0) {
                    $(this).attr('data-flag', 1);
                    $(this).find('img').attr('src', '${ctxStatic}/jianzhi/img/hideicon.png');
                    $('.live_rightbox').animate({ right: '-' + rightWidth }, 250);
                    $('.live_centerbox').css({ marginRight: '0px' });
                } else {
                    $(this).attr('data-flag', 0);
                    $(this).find('img').attr('src', '${ctxStatic}/jianzhi/img/showicon.png');
                    $('.live_rightbox').animate({ right: 0 }, 250);
                    $('.live_centerbox').css({ marginRight: rightWidth + 'px' });
                };
            });

             $("#video").on("timeupdate", function(event){
                onTrackedVideoFrame(this.currentTime, this.duration);
            });


            var id = $(".listul li:first div").attr('id');
            var url = $(".listul li:first div").attr('url');             
            playvideo(id, url);
        })
        var playing = null;
        var currcourseid = ''; //当时课时          
        function playvideo(courseid,url){
            $.post('${ctxF}/createCourseuser',{courseid:courseid},function(){
                $("#video").attr('src',url);
                clearInterval(playing);
                currcourseid=courseid;

                playing = setInterval(function(){
                    var currentTime = $("#current").text();     //当前播放时间
                    var duration = $("#duration").text();       //视频总时长

                    currentTime = Math.floor(currentTime);
                    duration = Math.floor(duration);
                    $.post('${ctxF}/courseLookRecord',{courseid:currcourseid,current:currentTime,duration:duration},function(){

                    })
                },5000)
            })
        }

        function onTrackedVideoFrame(currentTime, duration){              
            $("#current").text(Math.floor(currentTime));
            $("#duration").text(Math.floor(duration));
        }

        // playing = setInterval(function(){
        //     var currentTime = $("#current").text();     //当前播放时间
        //     var duration = $("#duration").text();       //视频总时长
        //     $.post('${ctxF}/courseLookRecord',{courseid:currcourseid,current:currentTime,duration:duration},function(){

        //     })
        // },5000) 
        
        
        function onplays(){
            var videos = document.getElementById("video");
            console.log()

            if(videos.paused){
                $('video').trigger('play');
                $("#play").hide();
            }else{
                $('video').trigger('pause');
                $("#play").show();
            }
            
        }
          

    </script>
</head>
<body>
    <div style="display: none;" id="current"></div>
    <div style="display: none;" id="duration"></div>


	<div class="head1">
		<div class="head1-lt"><h1>${zcourse.title}</h1></div>
		<div class="head1-rt"><img src="${sessionMyinfo.img}"/><span>${sessionMyinfo.name}</span></div>
	</div>
	<div class="head1-center" onclick="onplays()">
		<video controls="controls" id="video" src=""></video>
	</div>
   <div class="live_rightbox">
        <div class="positionrigh">
            <div class="tab_name"><h1 style="    font-size: 24px;">课时目录</h1></div>
            <div class="tupiao" id="hide_btn" data-flag="0">
                <img src="${ctxStatic}/jianzhi/img/showicon.png">
            </div>
            <div class="listul">
                <ul>
                    <c:forEach items="${hourlist}" var="info" varStatus="list">
                        <li><div title="${info.title}" id="${info.id}" url="${info.trueUrl}" onclick="playvideo('${info.id}','${info.trueUrl}')">${fns:abbr(info.title, 30)}</div></li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
    <button type="button" style="position: absolute;top: 50%;left: 50%;z-index: 11;color: #fff;width: 150px;height: 30px;margin-top: -15px;margin-left: -75px;background: #03a9f4;border: none;border-radius:5px;cursor: pointer; " id="play" >点击播放</button>
</body>
</html>