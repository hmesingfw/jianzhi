<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>试题管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
		function update(){
			var val = $("input[name='corret']");
			var corret = '';
			$.each(val,function(){
				// console.log($(this).is(':checked'))
				if($(this).is(':checked')){
					corret = corret + "1,";
				}else{
					corret = corret + "2,";
				}				 
			})
			$("#correts").val(corret)		 
		}
		function addcorret(){ 
			var str = '';
			str += '<div class="control-group">';
			str += '	<label class="control-label">答案选项：</label>';
			str += '	<div class="controls">';
			str += '		<input type="text" name="answer" class="required"/>';
			str += '		<input type="checkbox" name="corret" value="1" />';
			str += '		<span>是否为正确答案</span>';
			str += '		<a href="javascript:;" onclick="del(this)" style="margin-left: 20px;">删除这行</a>'
			str += '	</div>';
			str += '</div>'; 
			$(".addhtml").append(str);
		}

		function del(obj){
			$(obj).parent().parent().remove();
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/hm/question/zquestion/">试题管理列表</a></li>
		<li class="active"><a href="${ctx}/hm/question/zquestion/form?id=${zquestion.id}">试题管理<shiro:hasPermission name="hm:question:zquestion:edit">${not empty zquestion.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="hm:question:zquestion:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="zquestion" action="${ctx}/hm/question/zquestion/save" method="post" class="form-horizontal" onsubmit="return update()">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">所属专业：</label>
			<div class="controls"> 
				<%--
				<form:select path="parentid" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getCourseSortList()}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
				--%>
			</div>

			<sys:treeselect id="office" name="parentid" value="${office.id}" labelName="office.name" labelValue="${office.name}"
					title="课程分类" url="/hm/course_sort/zcourseSort/treeData" cssClass="required"/>

			
		</div>
		<div class="control-group">
			<label class="control-label">标题：</label>
			<div class="controls">
				<form:input path="title" htmlEscape="false" maxlength="3000" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">类型：</label>
			<div class="controls">
				<form:select path="type" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('question_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">默认分数：</label>
			<div class="controls">
				<form:input path="defaultFraction" htmlEscape="false" class="required digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"></label>
			<div class="controls">
				<input class="btn btn-primary" type="button" value="添加答案选项" onclick="addcorret()" />
			</div>
		</div>
		<input type="hidden" name="correts" id="correts" value="">
		<div class="addhtml">
			<c:forEach items="${answerlist}" var="info">
				<div class="control-group">
					<label class="control-label">答案选项：</label>
					<div class="controls">
						<input type="text" name="answer" class="required" value="${info.answer}" />
						<input type="checkbox" name="corret" value="1" ${info.isCorrect == 1 ? 'checked' : ''} />
						<span>是否为正确答案</span>
						<a href="javascript:;" onclick="del(this)" style="margin-left: 20px;">删除这行</a>
					</div>
				</div>
			</c:forEach>

			<c:if test="${fn:length(answerlist) == 0}">
				<div class="control-group">
					<label class="control-label">答案选项：</label>
					<div class="controls">
						<input type="text" name="answer" class="required"/>
						<input type="checkbox" name="corret" value="1" />
						<span>是否为正确答案</span>
						<a href="javascript:;" onclick="del(this)" style="margin-left: 20px;">删除这行</a>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">答案选项：</label>
					<div class="controls">
						<input type="text" name="answer" class="required"/>
						<input type="checkbox" name="corret" value="1" />
						<span>是否为正确答案</span>
						<a href="javascript:;" onclick="del(this)" style="margin-left: 20px;">删除这行</a>
					</div>
				</div>
			</c:if>
			
		</div>


		<div class="control-group">
			<label class="control-label">试题解析：</label>
			<div class="controls">
				<form:input path="analytical" htmlEscape="false" maxlength="3000" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="hm:question:zquestion:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>