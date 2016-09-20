<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
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
				<div class="form-group" style="margin-top: 15px">
					<label class="control-label col-sm-1" for="loginid">登录账号</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" id="loginid"
							name="loginid">
					</div>
					<label class="control-label col-sm-1" for="loginname">用户昵称</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" id="loginname"
							name="loginname">
					</div>
					<div class="col-sm-3" style="text-align: left;">
						<button type="button" style="margin-left: 50px" id="btn_query"
							class="btn btn-primary">查询</button>
					</div>
				</div>
			</div>
		</div>
		<div id="toolbar" >
			<button id="btnAdd" style="margin-right: 20px;"class="btn btn-danger" 
				data-toggle="modal" data-target="#saveConfParamModal">
				添加
			</button>
			<button id="btnDelete" style="margin-right: 20px;" class="btn btn-primary">
				删除
			</button>
			<button id="btnResetPass" style="margin-right: 20px;" class="btn btn-warning">
				重置密码
			</button>
		</div>
		<table id="sysUserInfo" class="table table-bordered table-striped"></table>
	</div>
	<div class="modal fade" id="saveConfParamModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">添加管理员</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" method="post" id="addSysUserForm"
						action="${pageContext.request.contextPath}/fxsj/admin/addSysUser">
						<div class="form-group">
							<label class="control-label col-sm-3" for="loginnameA">管理员昵称</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="loginnameA"
									name="loginnameA" required>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-3" for="loginidA">管理员账号</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="loginidA"
									name="loginidA" required>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-3" for="extdA">备注</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="extdA"
									name="extdA" required>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-3" for="extdA">&nbsp;</label>
							<div class="col-sm-6">
								<font color="red">* 新添加的操作员默认密码为 88888888 *</font>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-3"></div>
							<div class="col-sm-2">
								<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
							</div>
							<div class="col-sm-2"></div>
							<div class="col-sm-2">
								<button onclick="addSysUser()" class="btn btn-primary" >确认添加</button>
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
		initExchangeInfoTale();
		buttonInitFunc();
		$('#sysUserInfo').bootstrapTable('refresh', {
			url : '${pageContext.request.contextPath}/fxsj/admin/sysUserList'
		});
	});
	function initExchangeInfoTale() {
		var columns = [ {
			radio : true
		}, {
			field : 'id',
			align : 'center',
			valign : 'center',
			title : '编号'
		}, {
			field : 'loginid',
			align : 'center',
			valign : 'center',
			title : '登录账号'
		}, {
			field : 'loginname',
			align : 'center',
			valign : 'center',
			title : '用户昵称'
		}, {
			field : 'addtime',
			align : 'center',
			valign : 'center',
			title : '添加时间',
			formatter : "dateTimeFormatter"
		}, {
			field : 'status',
			align : 'center',
			valign : 'center',
			title : '账号状态',
			formatter:"statusFmt"
		}, {
			field : 'extd',
			align : 'center',
			valign : 'center',
			title : '账号描述'
		} ];
		var oTable = new TableInit("sysUserInfo", columns, "toolbar", null,
				queryParamsFunc, null, true);
		oTable.Init();
	}
	function queryParamsFunc(params) {
		var temp = { //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			limit : params.limit, //页面大小
			offset : params.offset, //页码
			loginid : $("#loginid").val(),
			loginname : $("#loginname").val()
		};
		return temp;
	}
	function buttonInitFunc() {
		$("#btnDelete").on("click",function() {
			var rows = $("#sysUserInfo").bootstrapTable('getSelections');
			if (rows == null || rows.length != 1) {
				alert("请选择一个用户后再进行操作");
				return false;
			}else if(rows[0].status!=1){
				alert("请选择状态为 '有效' 的用户进行操作");
				return false;
			}
			if(confirm("确认删除该用户？删除后无法恢复")){
				$.get("${pageContext.request.contextPath}/fxsj/admin/deleteSysUser?id="
						+ rows[0].id, function(data) {
					var obj = JSON.parse(data);
					alert(obj.retMsg);
					$("#btn_query").click();//更新视图
				});
			}
		});
		$("#btnResetPass").on("click",function() {
			var rows = $("#sysUserInfo").bootstrapTable('getSelections');
			if (rows == null || rows.length != 1) {
				alert("请选择一项进行操作");
				return false;
			}
			if(confirm("是否重置该用户密码")){
				$.get("${pageContext.request.contextPath}/fxsj/admin/changeSysUser?id="
							+ rows[0].id + "&salt=" + rows[0].salt,function(data) {
					var obj = JSON.parse(data);
					alert(obj.retMsg);
					$("#btn_query").click();//更新视图
				});
			}
		});
		$("#btn_query").on("click", function() {
			$('#sysUserInfo').bootstrapTable('refresh', {
				url : '${pageContext.request.contextPath}/fxsj/admin/sysUserList'
			});
		});
	}
	function addSysUser(){
		//校验元素
		if ($("#addSysUserForm").validate()) {
			$("#addSysUserForm").submit();
		} 
	}
	function sysUserStatusFmt(value){
		if(value==null){
			return "";
		}
		if(value==1){
			return "可使用";
		}
		if(value==0){
			return "不可用";
		}
	}
</script>
</html>