<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>关于我们信息管理</title>
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
		<li class="active"><a href="${ctx}/hm/aboutus/taboutUs/">关于我们信息列表</a></li>
		<%-- <shiro:hasPermission name="hm:aboutus:taboutUs:edit"><li><a href="${ctx}/hm/aboutus/taboutUs/form">关于我们信息添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="taboutUs" action="${ctx}/hm/aboutus/taboutUs/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>内容：</label>
				<form:input path="content" htmlEscape="false" maxlength="2000" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>名称</th>
				<th>内容</th>
				<th>更新时间</th>
				
				<shiro:hasPermission name="hm:aboutus:taboutUs:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="taboutUs">
			<tr>
				<td>
					${taboutUs.dir}
				</td>
				<td> 
					${fns:abbr(taboutUs.content, 100)}
				</td>
				<td>
					<fmt:formatDate value="${taboutUs.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				
				<shiro:hasPermission name="hm:aboutus:taboutUs:edit"><td>
    				<a href="${ctx}/hm/aboutus/taboutUs/form?id=${taboutUs.id}">修改</a>
					<%-- <a href="${ctx}/hm/aboutus/taboutUs/delete?id=${taboutUs.id}" onclick="return confirmx('确认要删除该关于我们信息吗？', this.href)">删除</a> --%>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>