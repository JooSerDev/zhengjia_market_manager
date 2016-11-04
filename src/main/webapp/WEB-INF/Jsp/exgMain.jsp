<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<jsp:include page="/common/common_css.jsp" />
<jsp:include page="/common/common_js.jsp" />
<script
	src="${pageContext.request.contextPath}/include/pagejs/exchange.js"></script>
<style type="text/css">
body {
	margin: 0px;
	padding: 0px;
	border-radius: 0;
}
</style>
</head>
<body>
		<div class="panel-body">
			<div class="panel panel-default">
				<div class="panel-heading">查询条件</div>
				<div class="panel-body">
					<form id="formSearch" class="form-horizontal">
						<div class="form-group" style="margin: 0px; padding: 0px;">
							<label class="control-label col-md-2" for="exchangeFinishStatus"
								style="margin-left: 0px;">筛选项</label>
							<div class="col-md-3">
								<select id="exchangeFinishStatus" class="form-control"
									name="exchangeFinishStatus">
									<option value="-1">全部</option>
									<option value="wait">未见证</option>
									<option value="NotWait">已见证</option>
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
		<!-- <div class="col-md-6">
			<iframe id="content" name="content" scrolling="no" frameborder="0"
				width="100%" height="500px;"></iframe>
		</div> -->

	<div class="modal fade" id="exgDeatilInfo">
		<div class="modal-dialog">
			<div class="modal-content"></div>
		</div>
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
		var columns = [ {
			field : 'exchange',
			align : 'center',
			valign : 'center',
			title : '交易编号',
			formatter : 'idFmt'
		}, {
			field : 'user',
			align : 'center',
			valign : 'center',
			title : '交易方甲',
			formatter : 'userNameFmt'
		}, {
			field : 'exchange',
			align : 'center',
			valign : 'center',
			title : '甲方物品',
			formatter : 'ownerItemFmt'
		}, {
			field : 'user',
			align : 'center',
			valign : 'center',
			title : '甲方联系方式',
			formatter : 'userMobileFmt'
		}, {
			field : 'target',
			align : 'center',
			valign : 'center',
			title : '交易方乙',
			formatter : 'userNameFmt'
		}, {
			field : 'exchange',
			align : 'center',
			valign : 'center',
			title : '乙方物品',
			formatter : 'changerItemFmt'
		}, {
			field : 'target',
			align : 'center',
			valign : 'center',
			title : '乙方联系方式',
			formatter : 'userMobileFmt'
		}, {
			field : 'exchange',
			align : 'center',
			valign : 'center',
			title : '交易发起时间',
			formatter : 'exgTimeFmt'
		}, {
			field : 'exchange',
			align : 'center',
			valign : 'center',
			title : '状态',
			formatter : 'exgStatusFmt'
		} ];
		var oTable = new TableInit("exchangeInfo", columns, null,
				"${pageContext.request.contextPath}/exg/exgList",
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

		/* $("#content").attr("src", url); */
		$("#exgDeatilInfo")
				.on(
						'shown.bs.modal',
						function() {
							var url = "${pageContext.request.contextPath}/exg/exgDetail?exchangeId="
									+ row.exchange.exchangeId;
							$(this).load(url);
							console.log('modal has show');
						});
		$("#exgDeatilInfo").modal('show');
	}
	function buttonInitFunc() {
		$("#btn_query").bind("click", function() {
			$('#exchangeInfo').bootstrapTable('refresh', {
				url : '${pageContext.request.contextPath}/exg/exgList'
			});
		});
	}
	function refreshTable() {

	}
</script>
</html>