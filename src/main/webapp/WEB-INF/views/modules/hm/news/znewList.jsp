<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>新闻资讯管理</title>
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
		<li class="active"><a href="${ctx}/hm/news/znew/">新闻资讯列表</a></li>
		<shiro:hasPermission name="hm:news:znew:edit"><li><a href="${ctx}/hm/news/znew/form">新闻资讯添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="znew" action="${ctx}/hm/news/znew/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>是否推荐：</label>
				<form:select path="iscommend" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('news_commend')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
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
				<th>是否推荐</th>
				<th>更新时间</th>
				<th>备注</th>
				<shiro:hasPermission name="hm:news:znew:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="znew">
			<tr>
				<td><a href="${ctx}/hm/news/znew/form?id=${znew.id}">
					${znew.title}
				</a></td>
				<td>
					${fns:getDictLabel(znew.iscommend, 'news_commend', '')}
				</td>
				<td>
					<fmt:formatDate value="${znew.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${znew.remarks}
				</td>
				<shiro:hasPermission name="hm:news:znew:edit"><td>
    				<a href="${ctx}/hm/news/znew/form?id=${znew.id}">修改</a>
					<a href="${ctx}/hm/news/znew/delete?id=${znew.id}" onclick="return confirmx('确认要删除该新闻资讯吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>