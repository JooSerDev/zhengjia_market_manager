<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>宝贝审核</title>
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
						<label class="control-label col-sm-1" for="itemFilter">筛选项</label>
						<div class="col-sm-2">
							<select id="itemFilter" class="form-control" name="itemFilter">
								<option value="0">待审核</option>
								<option value="1">已审核</option>
								<option value="2">被举报</option>
								<option value="3">被推荐</option>
							</select>
						</div>
						<label class="control-label col-sm-1" for="itemType">宝贝类别</label>
						<div class="col-sm-2">
							<select id="filterSelect" class="form-control" name="itemType">
								<option valu="0" default>a</option>
								<option valu="1">b</option>
								<option valu="2">c</option>
								<option valu="3">d</option>
							</select>
						</div>
						<label class="control-label col-sm-1" for="name">宝贝名称</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" name="name" id="name">
						</div>
						<div class="col-sm-2" style="text-align: left;">
							<button type="button" style="margin-left: 50px" id="btn_query"
								class="btn btn-primary">查询</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		<div id="itemsInfoDiv">
			<table id="itemsInfo" class="table table-bordered table-striped"></table>
		</div>
		<div id="offenseItemsDiv">
			<table id="offenseItems" class="table table-bordered table-striped"></table>
		</div>
	</div>
	<div class="modal fade" id="manageConfParam"></div>
</body>
<script type="text/javascript">
	//模态对话框隐藏时移除数据  
	$(function() {
		//显示每个宝贝的发布时间、审核状态、等待审核时间、发布人昵称、宝贝名称、宝贝分类、是否被小编推荐，以及该宝贝交易状态
		initItemsInfoTale();
		initOffenseItemsTable();
		buttonInitFunc();
		$("#itemsInfoDiv").hide();
		$("#offenseItemsDiv").hide();
	});
	function initItemsInfoTale() {
		var columns = [ {
			checkbox : true
		}, {
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
			field : 'approvalStatus',
			align : 'center',
			valign : 'center',
			title : '审核状态'
		}, {
			field : 'name',
			align : 'center',
			valign : 'center',
			title : '等待审核时间'
		}, {
			field : 'ownerName',
			align : 'center',
			valign : 'center',
			title : '发布人昵称'
		}, {
			field : 'itemType',
			align : 'center',
			valign : 'center',
			title : '宝贝分类'
		}, {
			field : 'status',
			align : 'center',
			valign : 'center',
			title : '状态'
		}, {
			field : 'isRecommended',
			align : 'center',
			valign : 'center',
			title : '是否被小编推荐'
		}, {
			field : 'lockStatus',
			align : 'center',
			valign : 'center',
			title : '宝贝交易状态'
		} ];
		var oTable = new TableInit("itemsInfo", columns, null, null,
				queryParamsFunc, onclickRowFunc);
		oTable.Init();
	}
	function initOffenseItemsTable() {
		var columns = [ {
			checkbox : true
		}, {
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
			field : 'userName',
			align : 'center',
			valign : 'center',
			title : '举报人昵称'
		}, {
			field : 'addTime',
			align : 'center',
			valign : 'center',
			title : '举报时间'
		}, {
			field : 'message',
			align : 'center',
			valign : 'center',
			title : '举报内容'
		} ];
		var oTable = new TableInit("offenseItems", columns, null, null,
				queryParamsFunc, onclickRowFunc);
		oTable.Init();
	}

	function queryParamsFunc(params) {
		var temp = { //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			limit : params.limit, //页面大小
			offset : params.offset, //页码
			filter : $("#itemFilter").val(),
			itemName : $("#name").val(),
			itemType : $("#itemType").val()
		};
		return temp;
	}
	function onclickRowFunc(row) {
		window.location.href = "${pageContext.request.contextPath}/item/itemList?itemId="
				+ row.itemId;
	}
	function buttonInitFunc() {
		$("#btn_query").bind("click", function() {
			var showTableId = "";
			if ($("#itemFilter option:selected").val() == 2) {//当为被举报时
				$("#itemsInfoDiv").hide();
				$("#offenseItemsDiv").show();
				showTableId = "offenseItems";
			} else {
				$("#itemsInfoDiv").show();
				$("#offenseItemsDiv").hide();
				showTableId = "itemsInfo";
			}
			$('#showTableId').bootstrapTable('refresh', {
				url : '${pageContext.request.contextPath}/item/itemList'
			});
		});
	}
</script>
</html>