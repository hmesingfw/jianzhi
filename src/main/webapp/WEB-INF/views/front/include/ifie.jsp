 <%@ page contentType="text/html;charset=UTF-8" %>	
 	<!--[if lt IE 9]>
	<style>
		.low-ie-bg{width:100%;height:100%; position: fixed;left:0;top:0;background:#000;filter:alpha(opacity=30); -moz-opacity:0.3; -khtml-opacity: 0.3;  opacity: 0.3; z-index: 990;display:none;}
		.low-ie-tips{width:580px;margin:0 auto; position: fixed;left:50%;top:50%;margin-left:-290px;height:360px;margin-top:-180px;background:#fff; z-index: 999; text-align: center; display:none;}
		.low-ie-title{font-size:24px;font-weight:bold;margin-top:30px; line-height:36px;}
		.low-ie-title img{height:44px;margin:-7px 10px 0 0;}
		.low-ie-info{line-height:34px; font-size:14px;color:#555;margin-top:5px;}
		.low-ie-btn{font-size:16px;margin-top:20px;line-height:36px; color:#999;}
		.low-ie-btn span{cursor: pointer;}
		.low-ie-browset{ text-align: center;line-height: 36px;height:130px;margin:40px 40px 0 40px; overflow: hidden;}
		.low-ie-browset li{width:33.2%;float:left; text-align: center; font-size:14px;line-height: 24px;}
		.low-ie-browset li img{width:80px;height:80px; display: block;margin:0 auto;}
		.low-ie-browset li a{color:#555;}
	</style>

	<div class="low-ie-bg"></div>
	<div class="low-ie-tips">
		<div class="low-ie-title"><img src="img/browset/tips.png" align="texttop" />警告！您的浏览器版本过低</div>
		<div class="low-ie-info">推荐使用以下浏览器或浏览器版本，以便获得更好的学习体验</div>
		<div class="low-ie-browset">
			<ul>
				<li><a href="https://www.baidu.com/s?wd=%E8%B0%B7%E6%AD%8C%E6%B5%8F%E8%A7%88%E5%99%A8&rsv_spt=1&rsv_iqid=0xc82eac3500000ba5&issp=1&f=8&rsv_bp=1&rsv_idx=2&ie=utf-8&rqlang=cn&tn=baiduhome_pg&rsv_enter=0&oq=%25E8%25B0%25B7%25E6%25AD%258C%25E6%25B5%258F%25E8%25A7%2588%25E5%2599%25A8&rsv_t=e3e8nc78TItJRNOlWeQoLRSt7BZkBas9lY0qfIPGvesm%2BHUoy8B9w0aUZiqX7cLTjzHS&rsv_pq=a91368a7000010c7&bs=%E8%B0%B7%E6%AD%8C%E6%B5%8F%E8%A7%88%E5%99%A8" target="_blank"><img src="img/browset/chrome.jpg" /><p>chrome</p></a></li>
				<li><a href="https://www.baidu.com/s?wd=Firefox%E7%81%AB%E7%8B%90%E6%B5%8F%E8%A7%88%E5%99%A8&rsv_spt=1&rsv_iqid=0xc82eac3500000ba5&issp=1&f=8&rsv_bp=1&rsv_idx=2&ie=utf-8&rqlang=cn&tn=baiduhome_pg&rsv_enter=0&oq=firefox&rsv_t=78a1JcRWP0F3Z59OBsjVWKXAUnACD1InVL2aRDEXOQ44tx8iPgTjH30s1JMtk7cRTUjD&rsv_pq=b30683b900004db5&inputT=543&rsv_sug3=34&rsv_sug1=31&rsv_sug7=100&rsv_n=2&rsv_sug2=0&rsv_sug4=569" target="_blank"><img src="img/browset/firefox.png" /><p>Firfox</p></a></li>
				<li><a href="https://www.baidu.com/s?wd=ie10&rsv_spt=1&rsv_iqid=0xc82eac3500000ba5&issp=1&f=8&rsv_bp=1&rsv_idx=2&ie=utf-8&rqlang=cn&tn=baiduhome_pg&rsv_enter=0&oq=ie%25E6%25B5%258F%25E8%25A7%2588%25E5%2599%25A8&rsv_t=0c09TewQcsF02CYRaFZ%2BZqSXC9qojBGkTmoiRVrSbJtLu0utmlbRo4avOYLJYvWWtEtw&rsv_pq=df1c07c80000468d&inputT=522&rsv_sug3=25&rsv_sug1=22&rsv_sug7=100&rsv_sug2=0&rsv_sug4=1757" target="_blank"><img src="img/browset/ie.jpg" /><p>IE9+</p></a></li>
			</ul>
		</div>
		<div class="low-ie-btn"><span onclick="closeLowIeTips()">不，我就要用这个浏览器访问 &gt;&gt;</span></div>
	</div>
	<script>
		if(getCookie('closeLowIe')!=1){
			$('.low-ie-tips,.low-ie-bg').show();
		};
		function closeLowIeTips(){
			$('.low-ie-bg,.low-ie-tips').fadeOut(300);
			setCookie('closeLowIe',1);
		};
	</script>
	<![endif]-->