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
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">宝贝信息</h3>
			</div>
			<div class="panel-body">
				<table id="itemDetail" class="table table-bordered table-striped"></table>
				<div id="itemImgs" class="inline" style="margin-top: 10px;"></div>
			</div>
		</div>
	</div>
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
								<label>用户昵称：</label><span id="userNickName"></span>
							</p>
							<hr>
							<p>
								<label>用户手机号：</label><span id="userMobile"></span>
							</p>
						</div>
						<div
							class="col-md-4 col-offset-md-2 col-sm-4 col-xs-4 col-offset-sm-2 col-offset-xs-2">
							<img id="userHeadImgUrl" width="100px;" height="100px">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function() {
		var itemId = "${itemId}";
		if (itemId != null && itemId != "") {
			initItemDetailTable(itemId);
		}
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
		}, {
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
			field : 'wishItem',
			align : 'center',
			valign : 'center',
			title : '期望交换物'
		}];
		var oTable = new TableInit("itemDetail", columns, null,
				"${pageContext.request.contextPath}/item/itemDetailInfo?itemId="
						+ itemId, null, null, false, onBstLoadSuccess);
		oTable.Init();
	}
	function onBstLoadSuccess(data) {
		var userInfo = data[0].ownerInfo;
		if(userInfo!=null){
			$("#userNickName").html(userInfo.nickname);
			$("#userMobile").html(userInfo.mobile);
			$("#userHeadImgUrl").attr("src", userInfo.headImgUrl);
		}
		var tbData = [];
		tbData.push(data[0].item);
		var item = tbData[0];
		if(item!=null){
			var imgUrls = item.itemImgUrls.split(";");
			$.each(imgUrls, function(index, url) {
				if (url != null && url != "") {
					var img = '<img src="'+url+'" width="100px" height="100px" style="margin-top:5px;margin-bottom:5px;margin-right:10px;"/>';
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
	/* , {
		field : 'description',
		align : 'center',
		valign : 'center',
		title : '宝贝描述',
	}  */
</script>
</html>