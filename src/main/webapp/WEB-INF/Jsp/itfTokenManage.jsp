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
		<div id="toolbar" >
			<button id="btnAdd" style="margin-right: 20px;"class="btn btn-danger" 
				data-toggle="modal" data-target="#addTokenAppModal">
				申请
			</button>
			<button id="btnDelete" style="margin-right: 20px;" class="btn btn-primary">
				删除
			</button>
		</div>
		<div id="itfTokenAppDiv" style="margin-bottom:20px;">
			<table id="itfTokenApp" class="table table-bordered table-striped"></table>
		</div>
	</div>
	<div class="modal fade" id="addTokenAppModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">申请应用序列号</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" role="form" method="post" id="addTokenAppForm">
						<div class="form-group">
							<label class="control-label col-sm-3" for="applyNameForm">申请者名称</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" name="applyNameForm"
									id="applyNameForm" required>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-3" for="descForm">描述</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" name="descForm"
									id="descForm" required>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-3"></div>
							<div class="col-sm-2">
								<button class="btn btn-default" data-dismiss="modal">关闭</button>
							</div>
							<div class="col-sm-1"></div>
							<div class="col-sm-2">
								<button onclick="addTokenApp()" class="btn btn-primary" >确认添加</button>
							</div>
						</div>
					</form>
				</div>
			</div>
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
			radio:true
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
			field : 'oprLoginId',
			align : 'center',
			valign : 'center',
			title : '操作员',
		}, {
			field : 'applyTime',
			align : 'center',
			valign : 'center',
			title : '申请时间',
			formatter:'dateTimeFormatter'
		}, {
			field : 'updateTime',
			align : 'center',
			valign : 'center',
			title : '更新时间',
			formatter:'dateTimeFormatter'
		}, {
			field : 'invalidTime',
			align : 'center',
			valign : 'center',
			title : '失效时间',
			formatter:'dateTimeFormatter'
		}, {
			field : 'status',
			align : 'center',
			valign : 'center',
			title : '状态',
		}, {
			field : 'remark',
			align : 'center',
			valign : 'center',
			title : '备注',
		}];
		var oTable = new TableInit("itfTokenApp", columns, 'toolbar', 
				'${pageContext.request.contextPath}/itfToken/listTokenApp',
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
			$('#itfTokenApp').bootstrapTable('refresh', {
				url : '${pageContext.request.contextPath}/itfToken/listTokenApp'
			});
		});
		$("#btnDelete").bind("click",function(){
			var row = $("#itfTokenApp").bootstrapTable('getSelections');
			if(row.length==0 || row[0].status == 'deleted'){
				alert("请选择要删除的 appId");
				return false;
			}
			if(confirm("确认删除该 appId ?")){
				var param = {
						appId:row.appId
				}
				$.get('${pageContext.request.contextPath}/itfToken/addTokenApp', param,function(data){
					var obj = JSON.parse(data);
					alert(obj.retMsg);
					$("#btn_query").click();
				});
			}
		});
	}
	function addTokenApp(){
		//校验元素
		var applyName = $("#applyNameForm").val();
		var desc = $("#descForm").val();
		if(applyName == null || applyName == ""){
			return ;
		}
		if(desc == null || desc == ""){
			return ;
		}
		if(confirm("确认信息填写正确！")){
			var param = {
					applyName:applyName,
					remark:desc
			};
			$.get('${pageContext.request.contextPath}/itfToken/addTokenApp', param,function(data){
				var obj = JSON.parse(data);
				alert(obj.retMsg);
				$("#btn_query").click();
			});
		}
	}
</script>
</html>