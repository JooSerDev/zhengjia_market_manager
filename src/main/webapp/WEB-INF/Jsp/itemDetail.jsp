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
		<div id="toolbar" class="btn-group">
			<button id="btn_ban" style="margin-right: 10px;"
				class="btn btn-danger">
				<span class="glyphicon glyphicon-ban-circle" aria-hidden="true"></span>封号
			</button>
			<button id="btn_ban_cancel" style="margin-right: 30px;"
				class="btn btn-primary">
				<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>解除封号
			</button>
			<button id="btn_clear_all" style="margin-right: 10px;"
				class="btn btn-warning">
				<span class="glyphicon glyphicon-minus" aria-hidden="true"></span>清除所有评论
			</button>
		</div>
		<table id="itemDetail" class="table table-bordered table-striped"></table>
	</div>
</body>
<script type="text/javascript">
	var itemId = "";
	$(function() {
		itemId = "${itemId}";
// 		initUserDetailTable(userId);
// 		initUserItemTable(userId);
	});
	function initUserDetailTable(userId) {
		var columns = [{
			field : 'itemId',
			align : 'center',
			valign : 'center',
			title : '宝贝编号'
		}, {
			field : 'name',
			align : 'center',
			valign : 'center',
			title : '宝贝名称'
		}, {
			field : 'itemType',
			align : 'center',
			valign : 'center',
			title : '宝贝分类'
		}];
		var pagination = false;
		var oTable = new TableInit("itemDetail", columns, "toolbar", 
				"${pageContext.request.contextPath}/item/itemDetail", null,null,pagination);
		oTable.Init();
		var oButtons = new ButtonInit(buttonFunc);
		oButtons.Init();
	}
	
	function onRowClk(row){
		window.location.href="${pageContext.request.contextPath}/item/itemDetail?itemId="+row.itemId;
	}
	
	function buttonFunc(){
		
	}
</script>
</html>