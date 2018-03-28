<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>文档管理</title>
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
		<li><a href="${ctx}/hm/doc/zdoc/">文档列表</a></li>
		<li class="active"><a href="${ctx}/hm/doc/zdoc/form?id=${zdoc.id}">文档<shiro:hasPermission name="hm:doc:zdoc:edit">${not empty zdoc.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="hm:doc:zdoc:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="zdoc" action="${ctx}/hm/doc/zdoc/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">标题：</label>
			<div class="controls">
				<form:input path="title" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group" style="display: none;">
			<label class="control-label">介绍：</label>
			<div class="controls">
				<form:input path="introduce" htmlEscape="false" maxlength="2000" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">上传文件：</label>
			<div class="controls">
				<input type="hidden" id="files" name="files" value="${zdoc.files}" />
				<sys:ckfinder input="files" type="files" uploadPath="/docfiles/files" selectMultiple="false"/> 
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">文件类型：</label>
			<div class="controls">
				<form:select path="filetype" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('doc_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户类型：</label>
			<div class="controls"> 

				<form:select path="usertype" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('user_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所属分类：</label>
			<div class="controls"> 
				<%--
				<form:select path="sortid" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDocSortList()}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
				--%>
				<sys:treeselect id="office" name="sortid" value="${zdoc.sortid}" labelName="${fns:getDocSort(zdoc.sortid).name}" labelValue="${fns:getDocSort(zdoc.sortid).name}"
					title="课程分类" url="/hm/docsort/zdocSort/treeData" cssClass="required"/>
					 
			</div>
		</div>
		<div class="control-group" style="display: none;">
			<label class="control-label">权重：</label>
			<div class="controls">
				<form:input path="weight" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>

		<div class="control-group" style="display: none;">
			<label class="control-label">浏览量：</label>
			<div class="controls">
				<form:input path="look" htmlEscape="false" maxlength="11" class="input-xlarge "/><span style="margin-left: 30px;color: red">只能输入数字</span>
			</div>
		</div>
		<div class="control-group" style="display: none;">
			<label class="control-label">下载量：</label>
			<div class="controls">
				<form:input path="down" htmlEscape="false" maxlength="11" class="input-xlarge "/><span style="margin-left: 30px;color: red">只能输入数字</span>
			</div>
		</div>


		<div class="control-group" style="display: none;">
			<label class="control-label">关键字：</label>
			<div class="controls">
				<form:input path="keyword" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group"  style="display: none;">
			<label class="control-label">是否推荐：</label>
			<div class="controls">
				<form:select path="iscom" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('doc_commend')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group" style="display: none;">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="hm:doc:zdoc:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>