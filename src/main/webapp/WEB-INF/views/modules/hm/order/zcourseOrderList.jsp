<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户课程订单管理</title>
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
		<li class="active"><a href="${ctx}/hm/order/zcourseOrder/">用户课程订单列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="zcourseOrder" action="${ctx}/hm/order/zcourseOrder/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>课程编号：</label> 
				<form:select path="courseid" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getCourseList()}" itemLabel="title" itemValue="id" htmlEscape="false"/>
				</form:select>

			</li>
			<li><label>用户编号：</label>
				<form:select path="userid" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getZuserList()}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>支付方式：</label>
				<form:select path="paytype" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('pay_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>购买状态：</label>
				<form:select path="paystatus" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('pay_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>课程名</th>
				<th>用户名</th>
				<th>支付方式</th>
				<th>支付时间</th>
				<th>购买状态</th>
				<th>更新时间</th>
				<th>备注</th>
				<shiro:hasPermission name="hm:order:zcourseOrder:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="zcourseOrder">
			<tr>
				<td>${fns:getCourse(zcourseOrder.courseid).title}</td>
				<td>${fns:getZuser(zcourseOrder.userid).name}</td>
				<td>
					<a href="${ctx}/hm/order/zcourseOrder/form?id=${zcourseOrder.id}">
						${fns:getDictLabel(zcourseOrder.paytype, 'pay_type', '')}
					</a>
				</td>
				<td>
					<fmt:formatDate value="${zcourseOrder.paytime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(zcourseOrder.paystatus, 'pay_status', '')}
				</td>
				<td>
					<fmt:formatDate value="${zcourseOrder.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${zcourseOrder.remarks}
				</td>
				<shiro:hasPermission name="hm:order:zcourseOrder:edit"><td>
    				<a href="${ctx}/hm/order/zcourseOrder/form?id=${zcourseOrder.id}">修改</a>
					<a href="${ctx}/hm/order/zcourseOrder/delete?id=${zcourseOrder.id}" onclick="return confirmx('确认要删除该用户课程订单吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>