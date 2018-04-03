<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>随机测试题管理</title>
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
		<li class="active"><a href="${ctx}/hm/testrandom/ztestRandom/">随机测试题列表</a></li>
		<shiro:hasPermission name="hm:testrandom:ztestRandom:edit"><li><a href="${ctx}/hm/testrandom/ztestRandom/form">随机测试题添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ztestRandom" action="${ctx}/hm/testrandom/ztestRandom/" method="post" class="breadcrumb form-search">
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
				<th>备注信息</th>
				<shiro:hasPermission name="hm:testrandom:ztestRandom:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ztestRandom">
			<tr>
				<td><a href="${ctx}/hm/testrandom/ztestRandom/form?id=${ztestRandom.id}">
					<fmt:formatDate value="${ztestRandom.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					${ztestRandom.remarks}
				</td>
				<shiro:hasPermission name="hm:testrandom:ztestRandom:edit"><td>
    				<a href="${ctx}/hm/testrandom/ztestRandom/form?id=${ztestRandom.id}">修改</a>
					<a href="${ctx}/hm/testrandom/ztestRandom/delete?id=${ztestRandom.id}" onclick="return confirmx('确认要删除该随机测试题吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>