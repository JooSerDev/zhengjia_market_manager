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
			<button class="btn btn-primary" data-toggle="modal"
				data-target="#addActivityModal" style="margin-right: 20px;">添加</button>
			<button class="btn btn-primary" id="hideActyEnter"
				style="margin-right: 20px;">隐藏报名入口</button>
			<button class="btn btn-primary" id="showActyEnter"
				style="margin-right: 20px;">显示报名入口</button>
		</div>
		<div id="itemsInfoDiv" style="margin-bottom: 20px;">
			<table id="itemsInfo" class="table table-bordered table-striped"></table>
		</div>
	</div>
	
	<input type="hidden" value="${addActivityPrompt }" id="addActivityPrompt">

	<div class="modal fade" id="addActivityModal" aria-hidden="true">
		<div class="modal-dialog" style="width: 600px;">
			<div class="modal-content">
				<form id="addActyForm" class="form-horizontal" method="post" enctype="multipart/form-data">
				<div class="modal-body">
					<div class="form-group" >
						<label class="control-label col-sm-2" for="title">标题</label>
						<div class="col-sm-9">
							<input id="title" class="form-control" name="title">
						</div>
					</div>
					<div class="form-group" >
						<label class="control-label col-sm-2" for="outSideUrl">跳转地址</label>
						<div class="col-sm-9">
							<input id="outSideUrl" class="form-control" name="outSideUrl">
						</div>
					</div>
					<div class="form-group" >
						<label class="control-label col-sm-2" for="file">背景图</label>
						<div class="col-sm-9">
							<input type="file" accept="image/png,image/jpg" class="form-control" name="file" >
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button class="btn btn-primary" id="addActivitySubmit">确定添加</button>
					<button class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
				</form>
			</div>
		</div>
	</div>

</body>

<script type="text/javascript">
	//模态对话框隐藏时移除数据
	$(function() {
		if($("#addActivityPrompt").val() != null &&
				$("#addActivityPrompt").val() != ''){
			alert($("#addActivityPrompt").val());
		}
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
		},{
			field : 'status',
			align : 'center',
			valign : 'center',
			visible : false,
			title : '发布时间',
		}];
		var url = globalPath + "/activity/getActyListByPage";
		var oTable = new TableInit("itemsInfo", columns, "toolbar", url,
				queryParamsFunc, onclickRowFunc, true);
		oTable.Init();
	}

	function queryParamsFunc(params) {
		var temp = {
			limit : params.limit,
			offset : params.offset,
		};
		return temp;
	}

	function onclickRowFunc(row) {
		if (row != null) {
			window.location.href = globalPath + "/activity/actyDetail?actyId="
					+ row.id;
		}
	}

	function buttonInitFunc() {
		$("#addActivitySubmit").bind("click", function() {
			$("#addActyForm").attr("action",globalPath + "/activity/addActivity");
			$("#addActyForm").submit();
			
		});
		$("#addActivitySubmit").bind("click", function() {
			$('#addActivityModal').modal('hide');
		});
		$("#hideActyEnter").bind("click",function(){
			if(confirm("确定隐藏报名活动入口？")){
				var data = {
					opType: "invisible",
					actyId: 1
				};
				$.post(globalPath+"/activity/changeEnterActyStatus",data,function(data){
					var retObj = JSON.parse(data);
					alert(retObje.retMsg);
				});
			}
		});
		$("#showActyEnter").bind("click",function(){
			if(confirm("确定显示报名活动入口？")){
				var data = {
					opType: "normal",
					actyId: 1
				};
				$.post(globalPath+"/activity/changeEnterActyStatus",data,function(data){
					var retObj = JSON.parse(data);
					alert(retObje.retMsg);
				});
			}
		});
	}
</script>
</html>