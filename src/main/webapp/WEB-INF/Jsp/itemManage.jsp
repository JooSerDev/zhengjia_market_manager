<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title>宝贝审核</title>
<jsp:include page="/common/common_css.jsp" />
<jsp:include page="/common/common_js.jsp" />
<script src="${pageContext.request.contextPath}/include/pagejs/wxItem.js"></script>
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
						<label class="control-label col-sm-1" for="itemFilter">过滤条件</label>
						<div class="col-sm-2">
							<select id="itemFilter" class="form-control" name="itemFilter">
								<option value="all">全部</option>
								<option value="wait" selected="selected">待审核</option>
								<option value="yes">审核-通过</option>
								<option value="no">审核-不通过</option>
								<option value="complaint">被举报</option>
								<option value="recommended">被推荐</option>
							</select>
						</div>
						<label class="control-label col-sm-1" for="itemType">宝贝分类</label>
						<div class="col-sm-2">
							<select id="itemType" class="form-control" name="itemType">
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
		<div id="itemsInfoDiv" style="margin-bottom:20px;">
			<table id="itemsInfo" class="table table-bordered table-striped"></table>
		</div>
		<div id="offenseItemsDiv" style="margin-bottom:20px;">
			<table id="offenseItems" class="table table-bordered table-striped"></table>
		</div>
	</div>
</body>
<script type="text/javascript">
	//模态对话框隐藏时移除数据  
	$(function() {
		parent.document.getElementById("content").height = 500;
		//显示每个宝贝的发布时间、审核状态、等待审核时间、发布人昵称、宝贝名称、宝贝分类、是否被小编推荐，以及该宝贝交易状态
		initItemsInfoTale();
		buttonInitFunc();
		initOffenseItemsTable();
		$("#offenseItemsDiv").hide();
		itemTypeInit();
// 		$("itemsInfo").bootstrapTable('refresh',{
// 			url:'${pageContext.request.contextPath}/item/itemList'
// 		});
		$("#btn_query").click();
	});
	function initItemsInfoTale() {
		var columns = [{
			field : 'itemId',
			align : 'center',
			valign : 'center',
			title : '宝贝编号',
		}, {
			field : 'name',
			align : 'center',
			valign : 'center',
			title : '宝贝名称',
		}, {
			field : 'approvalStatus',
			align : 'center',
			valign : 'center',
			title : '审核状态',
			formatter:'itemAprFmt'
		}, {
			field : 'lastModifyTime',
			align : 'center',
			valign : 'center',
			title : '审核时间',
			formatter:'approvalTimeFmt'
		}, {
			field : 'ownerNickname',
			align : 'center',
			valign : 'center',
			title : '发布人',
		}, {
			field : 'itemTypeName',
			align : 'center',
			valign : 'center',
			title : '宝贝分类',
		}, {
			field : 'status',
			align : 'center',
			valign : 'center',
			title : '宝贝状态',
			formatter:"itemStateFmt"
		}, {
			field : 'isRecommended',
			align : 'center',
			valign : 'center',
			title : '推荐',
			formatter:'itemRcmFmt'
		}, {
			field : 'lockStatus',
			align : 'center',
			valign : 'center',
			title : '宝贝交易状态',
			formatter:'itemBuzFmt'
		} ];
// 		${pageContext.request.contextPath}/item/itemList
		var oTable = new TableInit("itemsInfo", columns, null, null,
				queryParamsFunc, onclickRowFunc,true,onBstLoadSuccess);
		oTable.Init();
	}
	function onBstLoadSuccess(data){
		var tbData = [];
		$.each(data,function(index,value){
			tbData.push(value.item);
		});
		$("#itemsInfo").bootstrapTable('load',tbData);
	}
	function onBstLoadOffenseSuccess(data){
		var tbData = [];
		$.each(data,function(index,value){
			tbData.push(value.userCpt);
		});
		$("#offenseItems").bootstrapTable('load',tbData);
	}
	function initOffenseItemsTable() {
		var columns = [ {
			field : 'itemid',
			align : 'center',
			valign : 'center',
			title : '宝贝编号'
		}, {
			field : 'itemname',
			align : 'center',
			valign : 'center',
			title : '宝贝名称'
		}, {
			field : 'username',
			align : 'center',
			valign : 'center',
			title : '举报人昵称'
		}, {
			field : 'addtime',
			align : 'center',
			valign : 'center',
			title : '举报时间',
			formatter:'dateTimeFormatter'
		}, {
			field : 'message',
			align : 'center',
			valign : 'center',
			title : '举报内容'
		} ];
		var oTable = new TableInit("offenseItems", columns, null, null,
				queryParamsFunc, onclickRowFuncOffsense,true,onBstLoadOffenseSuccess);
		oTable.Init();
	}

	function queryParamsFunc(params) {
		var temp = { //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			limit : params.limit, //页面大小
			offset : params.offset, //页码
			itemFilter : $("#itemFilter").val(),
			itemName : $("#name").val(),
			itemType : $("#itemType").val()
		};
		return temp;
	}
	function onclickRowFunc(row) {
		if(row!=null){
			window.location.href = "${pageContext.request.contextPath}/item/itemDetail?itemId="+row.itemId;
		}
	}
	function onclickRowFuncOffsense(row) {
		if(row!=null){
			window.location.href = "${pageContext.request.contextPath}/item/itemDetail?itemId="+row.itemid;
		}
	}
	function buttonInitFunc() {
		$("#btn_query").bind("click", function() {
			var showTableId = "";
			if ($("#itemFilter option:selected").val() == "complaint") {//当为被举报时
				$("#itemsInfoDiv").hide();
				$("#offenseItemsDiv").show();
				showTableId = "offenseItems";
			} else {
				$("#itemsInfoDiv").show();
				$("#offenseItemsDiv").hide();
				showTableId = "itemsInfo";
			}
			$('#'+showTableId).bootstrapTable('refresh', {
				url : '${pageContext.request.contextPath}/item/itemList'
			});
		});
	}
	//初始化物品类别
	function itemTypeInit(){
		$("#itemType").empty();
		$("#itemType").append('<option value="-1">全部</option>');
		$.get('${pageContext.request.contextPath}/item/itemTypes',function(data){
			var obj = JSON.parse(data);
			var itemTypes = obj.resultList;
			$.each(itemTypes,function(index,value){
				$("#itemType").append('<option value="'+value.typeId+'">'+value.name+'</option>');
			});
		});
	}
</script>
</html>