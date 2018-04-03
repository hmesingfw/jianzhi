<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>试题管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出用户数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/sys/user/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
			$("#btnImport").click(function(){
				$.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true}, 
					bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
			});


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


		   	$("#alldelete").click(function(){
		   		layer.confirm('确认要删除该试题吗？', {
				  	btn: ['确定', '取消'] //可以无限个按钮
				  	,btn3: function(index, layero){
				    //按钮【按钮三】的回调
				  	}
				}, function(index, layero){
				  //按钮【按钮一】的回调
				  	var ids = "";
				 	$("input[name='ids']:checked").each(function () {
						ids += $(this).val()+"~";
					});
					console.log(ids)
					$("#deleteValue").val(ids);
					$("#deleteform").submit();
				});

		   		    		 	
			});
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
	<script src="https://cdn.bootcss.com/layer/3.1.0/layer.js"></script>
</head>
<body>
	<form action="${ctx}/hm/question/zquestion/deleteAll" id="deleteform" method="post" style="display: none;">
		<input type="text" name="deleteValue" id="deleteValue" value="">
	</form>


	<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/hm/question/zquestion/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctxF}/docDownTestmodel">下载模板</a>
		</form>
	</div>

	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/hm/question/zquestion/">试题管理列表</a></li>
		<shiro:hasPermission name="hm:question:zquestion:edit"><li><a href="${ctx}/hm/question/zquestion/form">试题管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="zquestion" action="${ctx}/hm/question/zquestion/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><input id="alldelete" class="btn btn-primary" type="button" value="批量删除" /></li> </li>

			<li><label>标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="3000" class="input-medium"/>
			</li>
			<li><label>所属专业：</label>
				<sys:treeselect id="office" name="parentid" value="office.id" labelName="" labelValue=""
					title="课程分类" url="/hm/course_sort/zcourseSort/treeData" cssClass="required"/>
			</li>
			<li><label>类型：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('question_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
				<!-- <input id="btnExport" class="btn btn-primary" type="button" value="导出"/> -->
				<input id="btnImport" class="btn btn-primary" type="button" value="导入"/></li> 

			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th><input type="checkbox" name="all" id="all"/></th>
				<th>标题</th>
				<th>类型</th>
				<th>试题解析</th>
				<th>所属专业</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="hm:question:zquestion:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="zquestion">
			<tr>
				<td><input type="checkbox" name="ids" value="${zquestion.id }" /></td>
				<td>
					<a href="${ctx}/hm/question/zquestion/form?id=${zquestion.id}">
						${zquestion.title}
					</a>
				</td>
				<td>
					${fns:getDictLabel(zquestion.type, 'question_type', '')}
				</td>
				<td>
					${zquestion.analytical}
				</td>
				<td>
					${fns:getCourseSort(zquestion.parentid).name}
				</td>
				<td>
					<fmt:formatDate value="${zquestion.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${zquestion.remarks}
				</td>
				<shiro:hasPermission name="hm:question:zquestion:edit"><td>
    				<a href="${ctx}/hm/question/zquestion/form?id=${zquestion.id}">修改</a>
					<a href="${ctx}/hm/question/zquestion/delete?id=${zquestion.id}" onclick="return confirmx('确认要删除该试题吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>