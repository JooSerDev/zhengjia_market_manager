<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
 
<title>首页</title>
<jsp:include page="/common/common_css.jsp" />
<jsp:include page="/common/common_js.jsp" />
<script
	src="${pageContext.request.contextPath}/fxsj/include/pagejs/exchange.js"></script>
<style type="text/css">
body {
	margin: 0px;
	padding: 0px;
	border-radius: 0;
}
</style>
</head>
<body>
	<div class="row">
		<div class="col-md-7">
			<div class="panel-body">
				<div class="panel panel-default">
					<div class="panel-heading">查询条件</div>
					<div class="panel-body">
						<form id="formSearch" class="form-horizontal">
							<div class="form-group" style="margin: 0px; padding: 0px;">
								<label class="control-label col-md-2" for="exchangeFinishStatus"
									style="margin-left: 0px;">交换状态</label>
								<div class="col-md-3">
									<select id="exchangeFinishStatus" class="form-control"
										name="exchangeFinishStatus">
										<option value="-1">全部</option>
										<option value="wait">未认证</option>
										<option value="NotWait">已认证</option>
									</select>
								</div>
								<label class="control-label col-md-2" for="mobile">手机号</label>
								<div class="col-md-3">
									<input type="text" class="form-control" id="mobile"
										name="mobile">
								</div>
								<div class="col-md-2 " style="text-align: right;">
									<button type="button" style="" id="btn_query"
										class="btn btn-primary">查询</button>
								</div>
							</div>
						</form>
					</div>
				</div>
				<table id="exchangeInfo" class="table table-bordered table-striped"></table>
			</div>
		</div>
		<div class="col-md-5" style="margin-left:0px;padding-left:0px;">
			<iframe id="content" name="content" scrolling="no" frameborder="0"
				width="100%" height="500px;"></iframe>
		</div>
	</div>
	
	<div>
	
	</div>
	
</body>
<script type="text/javascript">
	//模态对话框隐藏时移除数据  
	$(function() {
		//显示每个宝贝的发布时间、审核状态、等待审核时间、发布人昵称、宝贝名称、宝贝分类、是否被小编推荐，以及该宝贝交易状态
		parent.document.getElementById("content").height = 500;
		initExchangeInfoTale();
		buttonInitFunc();
	});
	function initExchangeInfoTale() {
		var columns = [ 
		{
			field : 'exchange',
			align : 'center',
			valign : 'center',
			title : '交易编号',
			formatter : 'idFmt'
		}, {
			field : 'user',
			align : 'center',
			valign : 'center',
			title : '交换方',
			formatter : 'userNameFmt'
		}, {
			field : 'exchange',
			align : 'center',
			valign : 'center',
			title : '物品名称',
			formatter : 'ownerItemFmt'
		}, {
			field : 'user',
			align : 'center',
			valign : 'center',
			title : '交换方手机',
			formatter : 'userMobileFmt'
		}, {
			field : 'target',
			align : 'center',
			valign : 'center',
			title : '申请方',
			formatter : 'userNameFmt'
		}, {
			field : 'exchange',
			align : 'center',
			valign : 'center',
			title : '物品名称',
			formatter : 'changerItemFmt'
		}, {
			field : 'target',
			align : 'center',
			valign : 'center',
			title : '申请方手机',
			formatter : 'userMobileFmt'
		}, {
			field : 'exchange',
			align : 'center',
			valign : 'center',
			title : '发起时间',
			formatter : 'exgTimeFmt'
		}, {
			field : 'exchange',
			align : 'center',
			valign : 'center',
			title : '交换状态',
			formatter : 'exgStatusFmt'
		}, {
			field : 'exchange',
			align : 'center',
			valign : 'center',
			title : '认证状态',
			formatter : 'finishExgStatusFmt'
		} ];
		var oTable = new TableInit("exchangeInfo", columns, null, "${pageContext.request.contextPath}/fxsj/exg/exgList",
				queryParamsFunc, onclickRowFunc, true);
		oTable.Init();
	}
	function queryParamsFunc(params) {
		var temp = { //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			limit : params.limit, //页面大小
			offset : params.offset, //页码
			exchangeFinishStatus : $("#exchangeFinishStatus").val(),
			mobile : $("#mobile").val()
		};
		return temp;
	}
	function onclickRowFunc(row) {
		var url = "${pageContext.request.contextPath}/fxsj/exg/exgDetail?exchangeId="
				+ row.exchange.exchangeId;
		$("#content").attr("src", url);
	}
	function buttonInitFunc() {
		$("#btn_query").bind("click", function() {
			$('#exchangeInfo').bootstrapTable('refresh', {
				url : '${pageContext.request.contextPath}/fxsj/exg/exgList'
			});
		});
	}
	function refreshTable() {

	}
</script>
</html>