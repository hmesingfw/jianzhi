<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>试卷题目内容管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/hm/test_question/ztestQuestion/">试卷题目内容管理列表</a></li>
		<shiro:hasPermission name="hm:test_question:ztestQuestion:edit"><li><a href="${ctx}/hm/test_question/ztestQuestion/form">试卷题目内容管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ztestQuestion" action="${ctx}/hm/test_question/ztestQuestion/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>更新时间</th>
				<shiro:hasPermission name="hm:test_question:ztestQuestion:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ztestQuestion">
			<tr>
				<td><a href="${ctx}/hm/test_question/ztestQuestion/form?id=${ztestQuestion.id}">
					<fmt:formatDate value="${ztestQuestion.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<shiro:hasPermission name="hm:test_question:ztestQuestion:edit"><td>
    				<a href="${ctx}/hm/test_question/ztestQuestion/form?id=${ztestQuestion.id}">修改</a>
					<a href="${ctx}/hm/test_question/ztestQuestion/delete?id=${ztestQuestion.id}" onclick="return confirmx('确认要删除该试卷题目内容管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>