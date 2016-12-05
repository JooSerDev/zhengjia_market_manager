<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>活动管理</title>
<jsp:include page="/common/common_css.jsp" />
<jsp:include page="/common/common_js.jsp" />
</head>
<body>
	<div class="panel-body" style="padding-bottom: 0px;">
		<!-- <div class="panel panel-default">
			<div class="panel-heading">查询条件</div>
			<div class="panel-body">
				<form id="formSearch" class="form-horizontal">
					<div class="form-group" >
						<label class="control-label col-sm-1" for="ntatType">筛 选</label>
						<div class="col-sm-2">
							<select id="ntatType" class="form-control" name="ntatType">
								<option value="all" selected="selected">全部</option>
								<option value="note" >正常</option>
								<option value="article" >隐藏</option>
							</select>
						</div>
						<label class="control-label col-sm-1" for="title">标题</label>
						<div class="col-sm-2">
							<input id="title" class="form-control" name="title"></select>
						</div>
						<div class="col-sm-2" style="text-align: left;">
							<button type="button" style="margin-left: 50px" id="btn_query"
								class="btn btn-primary">查询</button>
						</div>
					</div>
				</form>
			</div>
		</div> -->
		<div id="toolbar">
			<button class="btn btn-primary" id="deleteActivity"
				style="margin-right: 20px;">下撤</button>
		</div>
		<div id="itemsInfoDiv" style="margin-bottom: 20px;">
			<table id="itemsInfo" class="table table-bordered table-striped"></table>
		</div>
		<img src="" id="bgImg" style="height:300px;margin-bottom:20px;">

	</div>

	<input type="hidden" value="${actyId}" id="actyId">
</body>

<script type="text/javascript">
	//模态对话框隐藏时移除数据
	$(function() {
		buttonInitFunc();
		initItemsInfoTable();
	});

	function initItemsInfoTable() {
		var columns = [ {
			field : 'id',
			align : 'center',
			valign : 'center',
			visible : false,
			title : '编号',
		}, {
			field : 'title',
			align : 'center',
			valign : 'center',
			title : '标题',
		}, {
			field : 'showPublishTime',
			align : 'center',
			valign : 'center',
			title : '发布时间',
		}, {
			field : 'outSideUrl',
			align : 'center',
			valign : 'center',
			title : '跳转地址',
		}, {
			field : 'status',
			align : 'center',
			valign : 'center',
			title : '状态',
		}, {
			field : 'showStatusTime',
			align : 'center',
			valign : 'center',
			title : '状态时间',
		}, {
			field : 'bgImgUrl',
			align : 'center',
			valign : 'center',
			title : '背景图',
		}, {
			field : 'showCreateTime',
			align : 'center',
			valign : 'center',
			title : '创建时间',
		},];
		var url = globalPath + "/activity/getActyDetail";
		var oTable = new TableInit("itemsInfo", columns, "toolbar", url,
				queryParamsFunc, null, true,onBTSRet);
		oTable.Init();
	}

	function queryParamsFunc(params) {
		var temp = {
			limit : params.limit,
			offset : params.offset,
			id : $("#actyId").val(),
		};
		return temp;
	}

	function onBTSRet(data){
		var row = $("#itemsInfo").bootstrapTable('getData')[0];
		$("#bgImg").attr("src", row.bgImgUrl);
	}
	
	function buttonInitFunc() {
		$("#deleteActivity").bind(
				"click",
				function() {
					if (confirm("确定下撤此活动,此操作不可恢复？")) {
						var data = {
							opType : "deleted",
							actyId : $("#actyId").val(),
						};
						$.post(globalPath + "/activity/changeEnterActyStatus",
								data, function(data) {
									var retObj = JSON.parse(data);
									if(retObj.retCode != '0000'){
										alert(retObj.retMsg);
									}
									location.href = globalPath + "/activity/actyManage";
								});
					}
				});
	}
</script>
</html>