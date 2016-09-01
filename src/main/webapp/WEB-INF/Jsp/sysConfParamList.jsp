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
			<div class="panel-heading">系统参数配置列表</div>
			<div id="toolbar" class="btn-group">
				<button id="btnChgConfParam" style="margin-right: 10px;"
					class="btn btn-primary">
					<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
				</button>
			</div>
			<div class="panel-body">
				<table id="confParamInfo" class="table table-bordered table-striped"></table>
			</div>
		</div>
	</div>
	<div class="modal fade" id="chgConfParamModal"
		style="width: auto; height: auto; margin-left: auto; margin-right: auto;">
		<div class="modal-dialog">
			<div class="modal-content">
				<form action="${pageContext.request.contextPath}/admin/chgConfParamInfo"
					method="post" class="form-horizontal" id="chgConfForm"
					style="margin-left: 10px;">
					<p id="loginPrompt">
						<font size="1" color="red">${prompt}</font>
					</p>
					<div class="form-group">
						<label for="paramgroup" class="col-sm-2 control-label">参数组</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="paramgroup"
								name="paramgroup" readonly="readonly">
						</div>
					</div>
					<div class="form-group">
						<label for="paramid" class="col-sm-2 control-label">参数键</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="paramid"
								name="paramid" readonly="readonly">
						</div>
					</div>
					<div class="form-group">
						<label for="paramname" class="col-sm-2 control-label">参数名</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="paramname"
								name="paramname" readonly="readonly">
						</div>
					</div>
					<div class="form-group">
						<label for="paramvalue" class="col-sm-2 control-label">参数值</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="paramvalue"
								name="paramvalue" placeholder="请输入参数值" required>
						</div>
					</div>
					<div class="form-group">
						<label for="status" class="col-sm-2 control-label">状态</label>
						<div class="col-sm-8">
							<select id="status" class="form-control" name="status">
								<option value="1" selected>有效</option>
<!-- 								<option value="0">无效</option> -->
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="paramdesc" class="col-sm-2 control-label">参数描述</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="paramdesc"
								name="paramdesc" placeholder="请输入参数描述">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-3 col-sm-4 col-xs-4">
							<button onclick="submitForm()" class="btn btn-primary">确认修改</button>
						</div>
						<div class="col-sm-3">
							<button type="button" class="btn btn-default" data-dismiss="modal">关闭 </button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	//模态对话框隐藏时移除数据  
	$(function() {
		var prompt = "${chgConfParamInfoPrompt}";
		if (prompt != null && prompt != "") {
			alert(prompt);
		}
		initEvent();
		initSysConfParamListTale();
	});
	/* ${pageContext.request.contextPath}/admin/showParamsInfo */
	function initSysConfParamListTale() {
		var columns = [ {
			radio : true
		}, {
			field : 'paramid',
			align : 'center',
			valign : 'center',
			title : '参数键'
		}, {
			field : 'paramname',
			align : 'center',
			valign : 'center',
			title : '参数名'
		}, {
			field : 'paramvalue',
			align : 'center',
			valign : 'center',
			title : '参数值'
		}, {
			field : 'paramgroup',
			align : 'center',
			valign : 'center',
			title : '参数组'
		}, {
			field : 'paramdesc',
			align : 'center',
			valign : 'center',
			title : '参数描述'
		}, {
			field : 'addtime',
			align : 'center',
			valign : 'center',
			title : '添加时间',
			formatter : 'dateTimeFormatter'
		}, {
			field : 'updatetime',
			align : 'center',
			valign : 'center',
			title : '更新时间',
			formatter : 'dateTimeFormatter'
		}, {
			field : 'status',
			align : 'center',
			valign : 'center',
			title : '状态',
			formatter : 'statusFmt'
		} ];
		var oTable = new TableInit("confParamInfo", columns, "toolbar",
				"${pageContext.request.contextPath}/admin/showParamsInfo",
				queryParamsFunc, null, true);
		oTable.Init();

	}
	function initEvent() {
		$("#btnChgConfParam").on("click", function() {
			var rows = $("#confParamInfo").bootstrapTable("getSelections");
			if (rows == null || rows.length == 0) {
				alert("请选择一项进行修改");
				return false;
			}
			$("#paramid").val(rows[0].paramid);
			$("#paramgroup").val(rows[0].paramgroup);
			$("#paramname").val(rows[0].paramname);
			$("#paramvalue").val(rows[0].paramvalue);
			$("#paramdesc").val(rows[0].paramdesc);
			$("#status").val(rows[0].status);
			$("#chgConfParamModal").modal("show");
		});
	}
	function queryParamsFunc(params) {
		var temp = { //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			limit : params.limit, //页面大小
			offset : params.offset, //页码
		};
		return temp;
	}
	//提交
	function submitForm() {
		if (!confirm("修改参数会更改系统配置，是否确认修改？")) {
			$("#chgConfForm").submit(function(e){
			    e.preventDefault();
			  });
			return false;
		}
		if ($("#chgConfForm").validate()) {
			$("#chgConfForm").submit();
		}
	}
</script>
</html>