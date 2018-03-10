<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>文档中心管理</title>
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
		<li class="active"><a href="${ctx}/hm/doc/zdoc/">文档中心列表</a></li>
		<shiro:hasPermission name="hm:doc:zdoc:edit"><li><a href="${ctx}/hm/doc/zdoc/form">文档中心添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="zdoc" action="${ctx}/hm/doc/zdoc/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>文件类型：</label>
				<form:select path="filetype" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('doc_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li>
				<label>是否推荐：</label>
				<form:select path="iscom" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('doc_commend')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li>
				<label>文档分类：</label>
				<form:select path="sortid" class="input-medium ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDocSortList()}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li>
				<label>用户类型：</label>
				<form:select path="usertype" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('user_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>文件类型</th>
				<th>浏览量</th>
				<th>下载量</th>
				<th>文档分类</th>
				<th>用户类型</th>
				<th>更新时间</th>
				<shiro:hasPermission name="hm:doc:zdoc:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="zdoc">
			<tr>
				<td><a href="${ctx}/hm/doc/zdoc/form?id=${zdoc.id}">
					${zdoc.title}
				</a></td>
				<td>
					${fns:getDictLabel(zdoc.filetype, 'doc_type', '')}
				</td>
				<td>${zdoc.look}</td>
				<td>${zdoc.down}</td>
				<td>
					${fns:getDocSort(zdoc.sortid).name}
				</td>
				<td>
					${fns:getDictLabel(zdoc.usertype, 'user_type', '')}
				</td>
				
				<td>
					<fmt:formatDate value="${zdoc.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="hm:doc:zdoc:edit"><td>
    				<a href="${ctx}/hm/doc/zdoc/form?id=${zdoc.id}">修改</a>
					<a href="${ctx}/hm/doc/zdoc/delete?id=${zdoc.id}" onclick="return confirmx('确认要删除该文档中心吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>