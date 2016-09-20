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
		<div id="toolbar" >
			<button id="btn_ban" style="margin-right: 20px;" class="btn btn-danger">
				屏蔽用户
			</button>
			<button id="btn_ban_cancel" style="margin-right: 20px;" class="btn btn-primary">
				解除屏蔽
			</button>
			<button id="btn_clear_all" style="margin-right: 10px;" class="btn btn-danger">
				清除所有评论
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
		parent.document.getElementById("content").height = 500;
		parent.document.body.scrollHeight = 500;
		userId = "${userId}";
		initUserDetailTable(userId);
		initUserItemTable(userId);
	});
	function initUserDetailTable(userId) {
		var columns = [{
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
			formatter:'numFormat'
		}, {
			field : 'likeNum',
			align : 'center',
			valign : 'center',
			title : '点赞'
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
			title : '更新时间',
			formatter : 'dateTimeFormatter'
		}, {
			field : 'cptNum',
			align : 'center',
			valign : 'center',
			title : '被投诉'
		}, {
			field : 'cptTime', 
			align : 'center',
			valign : 'center',
			title : '投诉时间',
			formatter : 'dateTimeFormatter'
		} ];
		var oTable = new TableInit("wxUserDetail", columns, "toolbar", 
				"${pageContext.request.contextPath}/fxsj/wx/userDetailInfo?userId="+userId, null,null,false);
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
			field : 'itemTypeName',
			align : 'center',
			valign : 'center',
			title : '宝贝分类'
		}, {
			field : 'status',
			align : 'center',
			valign : 'center',
			title : '宝贝状态',
			formatter:"itemStateFmt"
		}, {
			field : 'lockStatus',
			align : 'center',
			valign : 'center',
			title : '宝贝交易状态',
			formatter:"itemBuzFmt"
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
			formatter:'dateTimeFormatter'
		}, {
			field : 'description',
			align : 'center',
			valign : 'center',
			title : '宝贝描述'
		}];
		/* onRowClk */
		var oTable = new TableInit("wxUserItem", columns, null, 
				"${pageContext.request.contextPath}/fxsj/wx/userItems", queryParamsFunc,
						null,true);
		oTable.Init();
// 		"${pageContext.request.contextPath}/fxsj/wx/userItems?userId="+userId
	}
	function queryParamsFunc(params) {
		var temp = { //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			limit : params.limit, //页面大小
			offset : params.offset, //页码
			userId : userId//
		};
		return temp;
	}
	function onRowClk(row){
		window.location.href="${pageContext.request.contextPath}/fxsj/item/itemDetail?itemId="+row.itemId;
	}
	function buttonFunc(){
		$("#btn_ban").bind("click",function(){
			if(confirm("确认屏蔽该用户？")){
				var url = "${pageContext.request.contextPath}/fxsj/wx/banUser?userId="+userId;
				$.get(url,function(data){
					var retObj = JSON.parse(data);
					alert(retObj.retMsg);//提示
					$("#wxUserDetail").bootstrapTable('refresh');
					$("#wxUserItem").bootstrapTable('refresh');
				});
			}
		});
		$("#btn_ban_cancel").bind("click",function(){
			if(confirm("解除屏蔽该用户？")){
				var url = "${pageContext.request.contextPath}/fxsj/wx/cancelBanUser?userId="+userId;
				$.get(url,function(data){
					var retObj = JSON.parse(data);
					alert(retObj.retMsg);//提示
					$("#wxUserDetail").bootstrapTable('refresh');
					$("#wxUserItem").bootstrapTable('refresh');
				});
			}
		});
		$("#btn_clear_all").bind("click",function(){
			if(confirm("确认清除该所有评论记录")){
				var url = "${pageContext.request.contextPath}/fxsj/wx/clearAllCmt?userId="+userId;
				$.get(url,function(data){
					var retObj = JSON.parse(data);
					alert(retObj.retMsg);//提示
					$("#wxUserDetail").bootstrapTable('refresh');
					$("#wxUserItem").bootstrapTable('refresh');
				});
			}
		});
	}
</script>
</html>