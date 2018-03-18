<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>轮播图管理</title>
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
		<li class="active"><a href="${ctx}/hm/banner/zbanner/">图片列表</a></li>
		<shiro:hasPermission name="hm:banner:zbanner:edit"><li><a href="${ctx}/hm/banner/zbanner/form">图片添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="zbanner" action="${ctx}/hm/banner/zbanner/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li>
				<label>类型推荐：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('banner_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>名称</th>
				<th>类型</th>
				<th>图片</th>
				<th>权重</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="hm:banner:zbanner:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="zbanner">
			<tr>
				<td><a href="${ctx}/hm/banner/zbanner/form?id=${zbanner.id}">
					${zbanner.name}
				</a></td>
				<td>
					${fns:getDictLabel(zbanner.type, 'banner_type', '')}
				</td>
				<td>
					<img src="${zbanner.filepath}" style="width: 190px;height: 50px;">
				</td>
				<td>
					${zbanner.weight}
				</td>
				<td>
					<fmt:formatDate value="${zbanner.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${zbanner.remarks}
				</td>
				<shiro:hasPermission name="hm:banner:zbanner:edit"><td>
    				<a href="${ctx}/hm/banner/zbanner/form?id=${zbanner.id}">修改</a>
					<a href="${ctx}/hm/banner/zbanner/delete?id=${zbanner.id}" onclick="return confirmx('确认要删除该轮播图吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>