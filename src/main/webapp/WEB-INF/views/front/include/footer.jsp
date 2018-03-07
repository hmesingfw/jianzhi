<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<footer class="footer">
	<div class="page-width clearfix">
		<div class="foot_top clearfix">
			<div class="link-item hidden-xs">
				<h3>新闻中心</h3>
				<ul>
					<li>
						<a href="${ctxF}/news" target="_blank">新闻资讯</a>
					</li>
					<li>
						<a href="http://me.cs.com/a/1256.aspx" target="_blank">文件下载</a>
					</li>
				</ul>
			</div>
			<div class="link-item ">
				<h3>经营范围</h3>
				<ul>
					<li>
						<a href="/v-1-4457.aspx" target="_blank">在线学习</a>
					</li>
					<li>
						<a href="/v-1-4456.aspx" target="_blank">在线测试</a>
					</li>
					<li>
						<a href="/v-1-4455.aspx" target="_blank"> 在线文档</a>
					</li>
				</ul>
			</div>
			<div class="link-item ">
				<h3>资料下载</h3>
				<ul>
					<li>
						<a href="/v-1-4460.aspx" target="_blank">文档下载</a>
					</li>
				</ul>
			</div>
			<div class="link-item" style="width: 300px;">
				<ul>
					<h3>联系我们</h3>
					<li>
						<a href="javascript:;">
							<span style="font-size: 24px;">${aboutPhone.content}</span>
						</a><br>
						<a href="javascript:;">${aboutAdress.content}</a>
					</li>
				</ul>
			</div>
			<div class="tel-item">
				<div class="tel">
					<img src="${aboutCode.file}" style="height: 100px;" />
					<br />
					<span>关注微信公众号</span>
				</div>
				<div class="tel"></div>
			</div>
		</div>
		<div class="foot_inner clearfix">
			<img src="${ctxStatic}/jianzhi/icon/icon1.png" /> ${aboutIpc.content}
		</div>
	</div>
</footer>