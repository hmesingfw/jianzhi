<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>课时管理管理</title>
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
		<li class="active"><a href="${ctx}/hm/coursehour/zcourseHour/list?courseid=${courseid}">课时管理列表</a></li>
		<li><a href="${ctx}/hm/coursehour/zcourseHour/form?courseid=${courseid}">课时管理添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="zcourseHour" action="${ctx}/hm/coursehour/zcourseHour/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>标题</th>
				<th>权重</th>
				<th>链接地址</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="zcourseHour">
			<tr>
				<td>
					<a href="${ctx}/hm/coursehour/zcourseHour/form?id=${zcourseHour.id}">
						${zcourseHour.title}
					</a>
				</td>
				<td>
					${zcourseHour.weight}
				</td>
				<td>
					${zcourseHour.url}
				</td>
				<td>
					<fmt:formatDate value="${zcourseHour.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${zcourseHour.remarks}
				</td>
				<td>
    				<a href="${ctx}/hm/coursehour/zcourseHour/form?id=${zcourseHour.id}">修改</a>
					<a href="${ctx}/hm/coursehour/zcourseHour/delete?id=${zcourseHour.id}" onclick="return confirmx('确认要删除该课时管理吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>