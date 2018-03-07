<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>文档分类管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<script type="text/javascript">
		$(document).ready(function () {
			$("#treeTable").treeTable({ expandLevel: 3 }).show();
		}); 
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/hm/docsort/zdocSort/">文档分类列表</a></li>
		<shiro:hasPermission name="hm:docsort:zdocSort:edit"><li><a href="${ctx}/hm/docsort/zdocSort/form?parent=0">文档分类添加</a></li></shiro:hasPermission>
	</ul>
	<%--
	<form:form id="searchForm" modelAttribute="zdocSort" action="${ctx}/hm/docsort/zdocSort/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	--%>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>名称</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="hm:docsort:zdocSort:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<%--
		<c:forEach items="${page.list}" var="zdocSort">
			<tr>
				<td><a href="${ctx}/hm/docsort/zdocSort/form?id=${zdocSort.id}">
					${zdocSort.name}
				</a></td>
				<td>
					<fmt:formatDate value="${zdocSort.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${zdocSort.remarks}
				</td>
				<shiro:hasPermission name="hm:docsort:zdocSort:edit"><td>
    				<a href="${ctx}/hm/docsort/zdocSort/form?id=${zdocSort.id}">修改</a>
					<a href="${ctx}/hm/docsort/zdocSort/delete?id=${zdocSort.id}" onclick="return confirmx('确认要删除该文档分类吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		--%>

			<c:forEach items="${page.list}" var="menu">
				<tr id="${menu.id}" pId="${menu.parent ne 0?menu.parent:'0'}">

					<td nowrap><a href="javascript:;">${menu.name}</a></td>			
					<shiro:hasPermission name="hm:docsort:zdocSort:edit">
						<td nowrap>
							<a href=" ">修改</a>
							<a href="" onclick="return confirmx('要删除该菜单及所有子菜单项吗？', this.href)">等下删除</a>
							<a href="${ctx}/tfile_sort_child/tfileSortChild/form?sortid=${menu.id}&resuid=${sortid}">添加下级菜单</a>
						</td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>


		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>