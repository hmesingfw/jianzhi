<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>新闻资讯管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/hm/news/znew/">新闻资讯列表</a></li>
		<li class="active"><a href="${ctx}/hm/news/znew/form?id=${znew.id}">新闻资讯<shiro:hasPermission name="hm:news:znew:edit">${not empty znew.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="hm:news:znew:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="znew" action="${ctx}/hm/news/znew/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">标题：</label>
			<div class="controls">
				<form:input path="title" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">介绍：</label>
			<div class="controls">
				<form:input path="introduce" htmlEscape="false" maxlength="400" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">图片：</label>
			<div class="controls">

				<input type="hidden" id="img" name="img" value="${znew.img}" />
				<sys:ckfinder input="img" type="images" uploadPath="/about/news" selectMultiple="false"/> 
				<span style="margin-left: 40px;color: red">推荐尺寸 230*150</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否推荐：</label>
			<div class="controls">
				<form:select path="iscommend" class="input-xlarge ">
					<form:option value="2" label="否"/>
					<form:options items="${fns:getDictList('news_commend')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">权重：</label>
			<div class="controls">
				<form:input path="weight" htmlEscape="false" maxlength="11" class="input-xlarge "/><span style="margin-left: 40px;color: red">只能输入数字</span>
			</div>
		</div>
		<div class="control-group" style="display: none;">
			<label class="control-label">点击量：</label>
			<div class="controls">
				<form:input path="click" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">内容：</label>
			<div class="controls">

				<form:textarea id="content" htmlEscape="true" path="content" rows="4" maxlength="200" class="input-xxlarge"/>
				<sys:ckeditor replace="content" uploadPath="/cms/article" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="hm:news:znew:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>