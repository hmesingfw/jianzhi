<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>试题管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/layer/layer.js"></script>
	<script type="text/javascript" src="${ctxStatic}/common/tender.js"></script>

	<script type="text/javascript">
		$(document).ready(function() {
			
		

			$("#all").on("click",function(){	
				var b = $(this).attr("checked");
		      	if(b){
		        	// checkboxAll("ids");
		        	$("input[name='ids']").attr("checked","true");
		     	}else{
			        // clearSelect("ids"); 
			        $("input[name='ids']").removeAttr("checked");
		      	}
		   	});
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }

        // 选择试题
		function addQuesSel() {
		    var ids = "";
		    var type = "";
		    var title = "";
		    var score = "";
		    $("input[name='ids']:checked").each(function () {
				ids += $(this).val()+"~";

				type += $("#type_"+$(this).val()).val()+"~";
				title += $("#title_"+$(this).val()).val()+"~";
				score += $("#score_"+$(this).val()).val()+"~";
            });
		    ids = ids.substr(0, ids.length - 1);

            type = type.substr(0, type.length - 1);
            title = title.substr(0, title.length - 1);
            score = score.substr(0, score.length - 1);
			window.opener.addQue(ids, title, type, score);
			self.close();
		}
	</script>
</head>
<body>
	

 
	<form:form id="searchForm" modelAttribute="zquestion" action="${ctx}/hm/question/zquestion/quesel" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="3000" class="input-medium"/>
			</li>
			<li><label>类型：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('question_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<div class="control-group">
		&nbsp;&nbsp;&nbsp;<input class="btn btn-primary" type="button" value="确认选择" onclick="addQuesSel()"/>
	</div>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th><input type="checkbox" name="all" id="all"/></th>
				<th>标题</th>
				<th>类型</th>
				<th>默认分数</th>
				<th>试题解析</th>
				<th>更新时间</th>
				<th>备注信息</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="zquestion">
			<tr>
				<td><input type="checkbox" name="ids" value="${zquestion.id }" /></td>
				<td>
					${zquestion.title}
					<input type="hidden" id="title_${zquestion.id}" value="${zquestion.title}"/>
				</td>
				<td>
					${fns:getDictLabel(zquestion.type, 'question_type', '')}
					<input type="hidden" id="type_${zquestion.id}" value="${fns:getDictLabel(zquestion.type, 'question_type', '')}"/>
				</td>
				<td >
					${zquestion.defaultFraction}

					<input type="hidden" id="score_${zquestion.id}" value="${zquestion.defaultFraction}"/>
				</td>
				<td >
					${zquestion.analytical}
				</td>
				<td>
					<fmt:formatDate value="${zquestion.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${zquestion.remarks}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>