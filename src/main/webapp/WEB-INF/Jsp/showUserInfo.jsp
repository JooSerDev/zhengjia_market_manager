<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<title></title>
<jsp:include page="/common/common_css.jsp" />
<jsp:include page="/common/common_js.jsp" />

<style type="text/css">
body {
	background-color: #ffffff;
	margin: 0px;
	padding: 0px;
	border-radius: 0;
	margin: 0px;
}
</style>
</head>
<body>
	<div class="panel-body" style="padding-bottom: 0px;">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">用户信息</h3>
			</div>
			<div class="panel-body" style="padding-bottom: 0px;">
				<table id="wxUserDetail" class="table table-bordered table-striped"></table>
			</div>
		</div>
	</div>
	<div class="panel-body" style="padding-top: 0px;">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">用户宝贝信息</h3>
			</div>
			<div class="panel-body" style="padding-bottom: 0px;">
				<table id="wxUserItem" class="table table-bordered table-striped"
					title="用户宝贝概览"></table>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var userId = "";
	// 	模态对话框隐藏时移除数据  
	$(function() {
		userId = "${userId}";
		if (userId != null && userId != "") {
			initUserItemTable(userId);
			initUserDetailTable(userId);
		}
	});
	function initUserDetailTable(userId) {
		var columns = [ {
			field : 'userId',
			align : 'center',
			valign : 'center',
			title : '编号'
		}, {
			field : 'nickname',
			align : 'center',
			valign : 'center',
			title : '昵称'
		}, {
			field : 'headImgUrl',
			align : 'center',
			valign : 'center',
			title : '头像',
			formatter : 'imgFormat'
		}, {
			field : 'openid',
			align : 'center',
			valign : 'center',
			title : 'OpenId'
		}, {
			field : 'mobile',
			align : 'center',
			valign : 'center',
			title : '手机号'
		}, {
			field : 'sex',
			align : 'center',
			valign : 'center',
			title : '性别',
			formatter:"sexFmt"
		}, {
			field : 'address',
			align : 'center',
			valign : 'center',
			title : '地区'
		}, {
			field : 'score',
			align : 'center',
			valign : 'center',
			title : '积分',
			formatter : 'numFormat'
		}, {
			field : 'likeNum',
			align : 'center',
			valign : 'center',
			title : '点赞数'
		}, {
			field : 'finishNum',
			align : 'center',
			valign : 'center',
			title : '认证完成交换数'
		}, {
			field : 'exchangeFailNum',
			align : 'center',
			valign : 'center',
			title : '信息不实交换数'
		}, {
			field : 'state',
			align : 'center',
			valign : 'center',
			title : '状态',
			formatter : 'stateFormat'
		}, {
			field : 'lastModifyTime',
			align : 'center',
			valign : 'center',
			title : '状态更新时间',
			formatter : 'dateTimeFormatter'
		}, {
			field : 'cptNum',
			align : 'center',
			valign : 'center',
			title : '被投诉次数'
		}, {
			field : 'cptTime',
			align : 'center',
			valign : 'center',
			title : '投诉时间',
			formatter : 'dateTimeFormatter'
		} ];
		var oTable = new TableInit("wxUserDetail", columns, null,
				"${pageContext.request.contextPath}/wx/userDetailInfo?userId="
						+ userId, null, null, false);
		oTable.Init();
	}
	function initUserItemTable(userId) {
		var columns = [ {
			field : 'itemId',
			align : 'center',
			valign : 'center',
			title : '编号'
		}, {
			field : 'name',
			align : 'center',
			valign : 'center',
			title : '名称'
		}, {
			field : 'itemTypeName',
			align : 'center',
			valign : 'center',
			title : '宝贝分类'
		}, {
			field : 'status',
			align : 'center',
			valign : 'center',
			title : '状态',
			formatter:"itemStateFmt"
		}, {
			field : 'approvalStatus',
			align : 'center',
			valign : 'center',
			title : '审批状态',
			formatter:"itemAprFmt"
		}, {
			field : 'createTime',
			align : 'center',
			valign : 'center',
			title : '发布时间',
			formatter : 'dateTimeFormatter'
		}];
		var oTable = new TableInit("wxUserItem", columns, null,
				"${pageContext.request.contextPath}/wx/userItems?userId="
						+ userId, null, null, true);
		oTable.Init();
	}
	/* {
		field : 'description',
		align : 'center',
		valign : 'center',
		title : '描述',
	}  */
</script>
</html>