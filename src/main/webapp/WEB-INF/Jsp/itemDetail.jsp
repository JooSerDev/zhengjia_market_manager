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

<style type="text/css">
body {
	margin: 0px;
	padding: 0px;
	border-radius: 0;
}
</style>
</head>
<body>
	<!-- 	<div> -->
	<!-- 		<a href="javascript:history.go(-1)">返回</a> -->
	<!-- 	</div> -->
	<div class="panel-body" style="padding-bottom: 0px;">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">宝贝信息</h3>
			</div>
			<div class="panel-body">
				<div class="form-horizontal">
					<div class="form-group" style="margin-left: 20px;">
						<div class="col-md-2 col-sm-2 col-xs-2" style="margin-right: 0px;">
							<label class="radio-inline"> <input type="radio"
								name="approvalStatus" id="approvalSuccess" value="yes">通过
							</label>
						</div>
						<div class="col-md-2 col-sm-2 col-xs-2" style="margin-right: 0px;">
							<label class="radio-inline"> <input type="radio"
								name="approvalStatus" id="approvalFail" value="no">拒绝
							</label>
						</div>
						<div class="col-md-2 col-sm-2 col-xs-2" style="margin-right: 0px;">
							<label class="checkbox-inline"> <input type="checkbox"
								name="isRecommended" id="isRecommended" value="1">推荐
							</label>
						</div>
						<div class="col-sm-6  col-sm-6 col-xs-6" style="display: none"
							id="chkFailMsgDiv">
							<label class="control-label col-sm-2 col-xs-6" for="chkFailMsg">理由：</label>
							<div class="col-sm-10">
								<input type="text" class="form-control " id="chkFailMsg"
									name="chkFailMsg">
							</div>
						</div>
					</div>
				</div>
				<div id="toolbar">
					<button class="btn btn-danger" id="forceToDown" style="margin-right:30px;">
						下架此宝贝
					</button>
					<button class="btn btn-primary" id="saveOption">
						确认审核
					</button>
				</div>
				<table id="itemDetail" class="table table-bordered table-striped"></table>
				<div id="itemImgs" class="inline" style="margin-top: 10px;"></div>
			</div>
		</div>
	</div>
	<!-- 	<div class="well well-lg">期望交换物品： test</div> -->
	<div class="panel-body" style="padding-top: 0px;">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">宝贝所属用户信息</h3>
			</div>
			<div class="panel-body">
				<div class="form-horizontal">
					<div class="form-group">
						<div class="col-md-4 col-sm-6 col-xs-6">
							<p>
								<label>用户名昵称：</label><span id="userNickName"></span>
							</p>
							<hr>
							<p>
								<label>用户手机号：</label><span id="userMobile"></span>
							</p>
						</div>
						<div
							class="col-md-4 col-offset-md-2 col-sm-4 col-xs-4 col-offset-sm-2 col-offset-xs-2">
							<img id="userHeadImgUrl" onclick="showImg(this.src)"
								width="100px;" height="100px">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="imgModal"
		style="width: auto; height: auto; margin-left: auto; margin-right: auto;">
		<div class="modal-dialog">
			<div class="modal-content"
				style="text-align: center; background-color: #ffffff">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
				</div>
				<img id="showImgLarge" height="200px" width="200px" align="center">
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function() {
		var itemId = "${itemId}";
		initItemDetailTable(itemId);
	});
	function initItemDetailTable(itemId) {
		var columns = [ {
			field : 'name',
			align : 'center',
			valign : 'center',
			title : '宝贝名称'
		}, {
			field : 'itemTypeName',
			align : 'center',
			valign : 'center',
			title : '分类'
		}, {
			field : 'createTime',
			align : 'center',
			valign : 'center',
			title : '发布时间',
			formatter : 'dateTimeFormatter'
		}, {
			field : 'lastModifyTime',
			align : 'center',
			valign : 'center',
			title : '审核时间',
			formatter : 'dateTimeFormatter'
		},{
			field : 'approvalStatus',
			align : 'center',
			valign : 'center',
			title : '审核状态',
			formatter : "itemAprFmt"
		}, {
			field : 'isRecommended',
			align : 'center',
			valign : 'center',
			title : '推荐',
			formatter : "itemRcmFmt",
		}, {
			field : 'lockStatus',
			align : 'center',
			valign : 'center',
			title : '交换状态',
			formatter : "itemBuzFmt"
		}, {
			field : 'status',
			align : 'center',
			valign : 'center',
			title : '宝贝状态',
			formatter : "itemStateFmt"
		}, {
			field : 'wishItem',
			align : 'center',
			valign : 'center',
			title : '期望交换物'
		}, {
			field : 'description',
			align : 'center',
			valign : 'center',
			title : '宝贝描述'
		} ];
		var oTable = new TableInit("itemDetail", columns, "toolbar",
				"${pageContext.request.contextPath}/fxsj/item/itemDetailInfo?itemId="
						+ itemId, null, null, false, onBstLoadSuccess);
		oTable.Init();
		var oButtons = new ButtonInit(buttonFunc);
		oButtons.Init();
	}
	function onBstLoadSuccess(data) {
		var userInfo = data[0].ownerInfo;
		if (userInfo != null) {
			$("#userNickName").html(userInfo.nickname);
			$("#userMobile").html(userInfo.mobile);
			$("#userHeadImgUrl").attr("src", userInfo.headImgUrl);
		}
		var tbData = [];
		tbData.push(data[0].item);
		var item = tbData[0];
		if (item != null) {
			var imgUrls = item.itemImgUrls.split(";");
			$
					.each(
							imgUrls,
							function(index, url) {
								if (url != null && url != "") {
									var img = '<img src="'
											+ url
											+ '" onclick="showImg(this.src)" width="100px" height="100px" style="margin-top:5px;margin-bottom:5px;margin-right:10px;"/>';
									$("#itemImgs").append(img);
								}
							});
		}
		/* var centerImgUrls = item.itemCenterImgUrls.split(";");
		$.each(centerImgUrls,function(index,url){
			if(url!=null && url!= ""){
				var img = '<img src="'+url+'" width="100px" height="100px" style="margin-right:10px;"/>';
				$("#itemImgs").append(img);
			}
		}); */
		$("#itemDetail").bootstrapTable('load', tbData);//加载item数据到表格中
	}

	function buttonFunc() {
		$("input[name='approvalStatus']").change(function() {
			if ($(this).val() == "yes") {//审核通过
			// 				$("input[id='isRecommended']").prop("checked",true);
				$("#chkFailMsgDiv").hide();
				$("#chkFailMsg").val("");
				$("#isRecommended").removeAttr("disabled");
			} else if ($(this).val() == "no") {//审核不通过
			// 				$("input[id='isRecommended']").prop("checked",false);
				$("#chkFailMsgDiv").show();
				$("#chkFailMsg").focus();
				$("#isRecommended").attr("checked",false);
				$("#isRecommended").attr("disabled",true);
			}
		})
		$("#forceToDown")
				.on(
						"click",
						function() {
							var item = $("#itemDetail").bootstrapTable(
									'getData')[0];
							if (item.approvalStatus == "no") {
								alert("该宝贝还未审核通过，下架操作只针对审核通过的宝贝");
								return false;
							}
							if (confirm("确认下架该宝贝？")) {
								var param = {
									itemId : item.itemId,
								}
								$.get("${pageContext.request.contextPath}/fxsj/item/forceToDowm",param,function(data) {
									var obj = JSON.parse(data);
									alert(obj.retMsg);
									window.location.href = "${pageContext.request.contextPath}/fxsj/item/itemDetail?itemId="
											+ param.itemId
								});
							}
						});
		$("#saveOption")
				.on(
						"click",
						function() {
							var chk = $("input[name='approvalStatus']:checked")
									.val();
							if (chk == null) {
								alert("尚未选择审核结果！");
								return false;
							}
							if (chk == "yes") {//审核通过，请求后台更改状态
								$("#chkFailMsg").val("审核通过");
							} else if (chk == "no") {//审核不通过，必须填写理由
								var failMsg = $("#chkFailMsg").val();
								if (failMsg == null || failMsg.trim() == "") {
									alert("请填写审核拒绝理由")
									$("#chkFailMsg").focus();
									return false;
								}
							}
							if(confirm("确认审核结果？")){
								var rcm = 0;
								if ($("#isRecommended").is(":checked")) {
									rcm = 1;
								}
								var param = {
									itemId : "${itemId}",
									approvalStatus : chk,
									isRecommended : rcm,
									approvalMsg : $("#chkFailMsg").val()
								}
								$.get("${pageContext.request.contextPath}/fxsj/item/approvalItem",param, function(data) {
									var obj = JSON.parse(data);
									alert(obj.retMsg);
									history.go(-1);
									/* 	window.location.href = "${pageContext.request.contextPath}/fxsj/item/itemDetail?itemId="
												+ param.itemId */
								});
							}
						});
	}
</script>
</html>