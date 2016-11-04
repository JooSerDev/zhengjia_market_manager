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
		<table id="wxUserDetail" class="table table-bordered table-striped"></table>
	</div>
	<div class="panel-body" style="padding-bottom: 0px;">
		<table id="wxUserItem" class="table table-bordered table-striped" title="用户宝贝概览"></table>
	</div>
</body>
<script type="text/javascript">
	var userId = "";
	// 	模态对话框隐藏时移除数据  
	$(function() {
		//显示用户昵称、头像、OpenID、手机号、性别、地区、积分数、被点赞数、
		//该用户发布的所有宝贝（包括未审核的，点击进入宝贝详情），该用户“认证完成”的交易数、
		// “信息不实”的交易数、用户当前状态（是否被封号以及封号时间）、以及该用户的被投诉次数、最近一次被投诉的时间
		userId = "${userId}";
		initUserDetailTable(userId);
		initUserItemTable(userId);
	});
	function initUserDetailTable(userId) {
		var columns = [{
			field : 'userId',
			align : 'center',
			valign : 'center',
			title : '用户编号'
		}, {
			field : 'nickname',
			align : 'center',
			valign : 'center',
			title : '用户昵称'
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
			title : '性别'
		}, {
			field : 'address',
			align : 'center',
			valign : 'center',
			title : '地区'
		}, {
			field : 'score',
			align : 'center',
			valign : 'center',
			title : '积分数',
			formatter:'numFormat'
		}, {
			field : 'likeNum',
			align : 'center',
			valign : 'center',
			title : '被点赞数'
		}, {
			field : 'finishNum',
			align : 'center',
			valign : 'center',
			title : '认证完成交易数'
		}, {
			field : 'exchangeFailNum',
			align : 'center',
			valign : 'center',
			title : '信息不实交易数'
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
			title : '最近投诉时间',
			formatter : 'dateTimeFormatter'
		} ];
		var pagination = false;
		var oTable = new TableInit("wxUserDetail", columns, "toolbar", 
				"${pageContext.request.contextPath}/wx/userDetailInfo?userId="+userId, null,null,pagination);
		oTable.Init();
		var oButtons = new ButtonInit(buttonFunc);
		oButtons.Init();
	}
	function initUserItemTable(userId){
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
		}, {
			field : 'status',
			align : 'center',
			valign : 'center',
			title : '状态'
		}, {
			field : 'approvalStatus',
			align : 'center',
			valign : 'center',
			title : '审批状态'
		}, {
			field : 'createTime',
			align : 'center',
			valign : 'center',
			title : '发布时间'
		}, {
			field : 'description',
			align : 'center',
			valign : 'center',
			title : '描述'
		}];
		var pagenation = true;
		var oTable = new TableInit("wxUserItem", columns, null, 
				"${pageContext.request.contextPath}/wx/userItems?userId="+userId, null,onRowClk,pagenation);
		oTable.Init();
	}
	function onRowClk(row){
		window.location.href="${pageContext.request.contextPath}/item/itemDetail?itemId="+row.itemId;
	}
	function buttonFunc(){
		$("#btn_ban").bind("click",function(){
			if(confirm("确认进行封号处理")){
				var url = "${pageContext.request.contextPath}/wx/banUser?userId="+userId;
				$.get(url,function(data){
					if(data.retFlag){
						$("#wxUserDetail").bootstrapTable('refresh');
					}
				});
			}
		});
		$("#btn_ban_cancel").bind("click",function(){
			var url = "";
			$.get(url,function(data){
				
			});
		});
		$("#btn_clear_all").bind("click",function(){
			if(confirm("确认清除所有评论")){
				var url = "${pageContext.request.contextPath}/wx/clearAllCmt?userId="+userId;
				$.get(url,function(data){
				});
			}
		});
	}
</script>
</html>