<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title>tokenApp管理</title>
<jsp:include page="/common/common_css.jsp" />
<jsp:include page="/common/common_js.jsp" />
<style type="text/css">
body {
	margin: 0px;
	padding: 0px;
	border-radius: 0;
}
</style>
</head>
<body>
	<div class="panel-body" style="padding-bottom: 0px;">
		<div class="panel panel-default">
			<div class="panel-heading">查询条件</div>
			<div class="panel-body">
				<form id="formSearch" class="form-horizontal">
					<div class="form-group" style="margin-top: 15px">
						<label class="control-label col-sm-1" for="appId">应用序列号</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" name="appId" id="appId">
						</div>
						<label class="control-label col-sm-1" for="applyName">申请者</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" name="applyName" id="applyName">
						</div>
						<div class="col-sm-2" style="text-align: left;">
							<button type="button" style="margin-left: 50px" id="btn_query"
								class="btn btn-primary">查询</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		<div id="itfTokenLogDiv" style="margin-bottom:20px;">
			<table id="itfTokenLog" class="table table-bordered table-striped"></table>
		</div>
	</div>
</body>
<script type="text/javascript">
	//模态对话框隐藏时移除数据  
	$(function() {
		parent.document.getElementById("content").height = 500;
		initItemsInfoTale();
		buttonInitFunc();
	});
	function initItemsInfoTale() {
		var columns = [{
			field : 'oId',
			align : 'center',
			valign : 'center',
			title : '编号',
		}, {
			field : 'appId',
			align : 'center',
			valign : 'center',
			title : '序列号',
		}, {
			field : 'applyName',
			align : 'center',
			valign : 'center',
			title : '申请者',
		}, {
			field : 'accessTime',
			align : 'center',
			valign : 'center',
			title : '访问时间',
			formatter:'dateTimeFormatter'
		}, {
			field : 'token',
			align : 'center',
			valign : 'center',
			title : 'Token',
			width: 100,
		}, {
			field : 'flag',
			align : 'center',
			valign : 'center',
			title : 'Token标识',
		}, {
			field : 'expireTime',
			align : 'center',
			valign : 'center',
			title : '过期时间',
			formatter:'dateTimeFormatter'
		}];
		var oTable = new TableInit("itfTokenLog", columns, null, 
				'${pageContext.request.contextPath}/itfToken/listTokenAppLog',
				queryParamsFunc, null,true,null);
		oTable.Init();
	}

	function queryParamsFunc(params) {
		var temp = { //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			limit : params.limit, //页面大小
			offset : params.offset, //页码
			appId:$("#appId").val(),
			applyName:$("#applyName").val(),
		};
		return temp;
	}
	function buttonInitFunc() {
		$("#btn_query").bind("click", function() {
			$('#itfTokenLog').bootstrapTable('refresh', {
				url : '${pageContext.request.contextPath}/itfToken/listTokenAppLog'
			});
		});
	}
</script>
</html>