<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<jsp:include page="/common/common_css.jsp" />
<jsp:include page="/common/common_js.jsp" />
<script
	src="${pageContext.request.contextPath}/include/pagejs/exchange.js"></script>
<style type="text/css">
* {
	border-radius: 0;
}
</style>
</head>
<body>
	<div class="panel-body" style="padding-bottom: 0px;">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">交易详情</h3>
			</div>
			<div class="panel-body">
				<div class="row">
					<div class="form-group">
						<label class="col-md-4 ">交换方：</label> <a class="col-md-4"
							href="${pageContext.request.contextPath}/wx/showUserInfo?userId=${exgDetail.exchange.ownerId}"
							data-target="#detailInfo" data-toggle="modal">${exgDetail.user.nickname}</a>
						<label class="col-md-4 ">物品名称：</label> <a class="col-md-4"
							data-target="#detailInfo" data-toggle="modal"
							href="${pageContext.request.contextPath}/item/showItemInfo?itemId=${exgDetail.exchange.ownerItemId}">${exgDetail.exchange.ownerItemName}</a>
					</div>
					<div class="form-group" id="userItemImgs" style="margin-left: 5px;"></div>
					<hr>
					<div class="form-group">
						<label class="col-md-4 ">申请方：</label> <a class="col-md-4"
							data-target="#detailInfo" data-toggle="modal"
							href="${pageContext.request.contextPath}/wx/showUserInfo?userId=${exgDetail.exchange.changerId}">${exgDetail.target.nickname}</a>
						<label class="col-md-4 ">物品名称：</label> <a class="col-md-4"
							data-target="#detailInfo" data-toggle="modal"
							href="${pageContext.request.contextPath}/item/showItemInfo?itemId=${exgDetail.exchange.changerItemId}">${exgDetail.exchange.changerItemName}</a>
					</div>
					<div id="targetItemImgs" class="form-group"
						style="margin-left: 5px;"></div>
					<hr>
					<div class="form-group">
						<label class="col-md-4 ">发起时间：</label><span id="exgCreateTime"></span>
					</div>
					<div class="form-group">
						<label class="col-md-4 ">申请同意时间：</label><span id="exgRespTime"></span>
					</div>
					<div class="form-group">
						<label class="col-md-4 ">交易状态：</label><span id="exgStatus"></span>
					</div>
					<div class="form-group">
						<label class="col-md-4 ">认证交易：</label><span id="exgFinishStatus"></span>
					</div>
					<div class="form-group" id="exgOprBtns" style="margin-left: 10px;">
						<button class="btn btn-primary" id="btnExgSus"
							style="margin-left: 20px;">确认认证交易</button>
						<button class="btn btn-warning" id="btnExgFail">认证交易失败</button>
					</div>
					<div id="failSelect" style="display: none">
						<form role="form" id="dealFailForm">
							<div class="form-group">
								<label class="col-md-4 " for="infoNotReal">是否信息不实：</label> <label
									class="col-md-4 ">交易方：</label> <input type="checkbox"
									name="infoNotReal" value="owner"> <label
									class="col-md-4 ">申请方：</label> <input type="checkbox"
									name="infoNotReal" value="target">
							</div>
							<div class="form-group">
								<label class="col-md-4 " for="infoNotReal">交换方投诉：</label> <input
									class="col-md-2 " type="checkbox" name="charge" value="target">
								<div class="col-md-6" style="display:none">
									<input type="text" name="chargeMsgTarget" class="form-control">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-4 " for="infoNotReal">申请方投诉：</label> <input
									class="col-md-2 " type="checkbox" name="charge" value="owner">
								<div class="col-md-6" style="display:none">
									<input type="text" name="chargeMsgOwn" class="form-control" >
								</div>
							</div>
						</form>
						<div class="form-group" style="margin-left: 10px;">
							<button id="cancelExgDeal" class="btn btn-info">取消处置</button>
							<button id="ensureFailDeal" class="btn btn-danger">确认处置</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="detailInfo">
		<div class="modal-dialog">
			<div class="modal-content"></div>
			<div class="form-horizontal" style="margin-top:10px;">
				<button class="btn btn-primary form-control" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
<!-- 	 background-color: transparent; -->
	<div class="modal fade" id="imgModal" style="width: auto; height: auto; 
		margin-left: auto; margin-right: auto;">
		<div class="modal-dialog">
			<div class="modal-content" style="text-align: center;background-color:#ffffff">
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
		//显示每个宝贝的发布时间、审核状态、等待审核时间、发布人昵称、宝贝名称、宝贝分类、是否被小编推荐，以及该宝贝交易状态
		initUI();
		initModal();
	});
	function initBtns() {
		//交易失败处置，显示复选框
		$("#btnExgFail").on("click", function() {
			$("#btnExgSus").attr("disabled", true);
			$("#failSelect").show();
			frameSuit();
		});
		//交易成功保存
		$("#btnExgSus")
				.on(
						"click",
						function() {
							//TODO 
							if (!confirm("确认双方现场交易成功")) {
								return false;
							}
							var exchangeId = '${exgDetail.exchange.exchangeId}';
							$
									.get(
											"${pageContext.request.contextPath}/exg/exgFaceSuccess?exchangeId=${exgDetail.exchange.exchangeId}",
											function(data) {
												alert(JSON.parse(data).retMsg);
												$("#btnExgSus").attr(
														"disabled", true);
												$("#btnExgFail").attr(
														"disabled", true);
												$('#exchangeInfo',
														window.parent.document)
														.bootstrapTable(
																'refresh');
												/* , {
													url : '${pageContext.request.contextPath}/exg/exgList'
												} */
												window.close();
												//window.location.href = "${pageContext.request.contextPath}/exg/exgDetail?exchangeId="+exchangeId;
											});
						});
		//取消处理，隐藏复选框选项
		$("#cancelExgDeal").on("click", function() {
			$("#failSelect").hide();
			$("input:checkbox").attr("checked", false);
			$("#btnExgSus").removeAttr("disabled");
			frameSuit();
		});
		$("#ensureFailDeal")
				.on(
						"click",
						function() {
							if (confirm("确认提交交易处置结果")) {
								var exchangeId = '${exgDetail.exchange.exchangeId}';
								var sendData = $("#dealFailForm").serialize();
								$
										.get(
												"${pageContext.request.contextPath}/exg/exgFaceFail?exchangeId=${exgDetail.exchange.exchangeId}",
												sendData,
												function(data) {
													alert(JSON.parse(data).retMsg);
													$("#btnExgSus").attr(
															"disabled", true);
													$("#btnExgFail").attr(
															"disabled", true);
													$("#failSelect").hide();
													$("input:checkbox").attr(
															"checked", false);
													/* $('#exchangeInfo', window.parent.document).bootstrapTable(
														'refresh',{url : '${pageContext.request.contextPath}/exg/exgList'}); */
													$("#exchangeInfo",parent.document).bootstrapTable("refresh");
													$(window.parent).find("iframe[id='content']").attr("src","");
												});
							}
						});
	}
	function initImgs() {
		var urls = '${exgDetail.userItem.itemImgUrls}'
		var imgUrls = [];
		if (urls != null) {
			imgUrls = urls.split(";");
		}
		if (imgUrls != null && imgUrls != '') {
			$
					.each(
							imgUrls,
							function(index, url) {
								if (url != null && url != "") {
									var img = '<img src="'
											+ url
											+ '" onclick="showImg(this.src)" width="100px" height="100px" style="margin-top:5px;margin-bottom:5px;margin-right:10px;"/>';
									$("#userItemImgs").append(img);
								}
							});
		}
		urls = '${exgDetail.targetItem.itemImgUrls}';
		if (urls != null) {
			imgUrls = urls.split(";");
		}
		if (imgUrls != null && imgUrls != '') {
			$
					.each(
							imgUrls,
							function(index, url) {
								if (url != null && url != "") {
									var img = '<img src="'
											+ url
											+ '" onclick="showImg(this.src)" width="100px" height="100px" style="margin-top:5px;margin-bottom:5px;margin-right:10px;"/>';
									$("#targetItemImgs").append(img);
								}
							});
		}
		frameSuit();
	}
	function frameSuit() {
		parent.parent.document.getElementById("content").height = document.body.scrollHeight;
		parent.document.getElementById("content").height = document.body.scrollHeight;
	}
	function initUI() {
		$("#exgCreateTime").html('${exgDetail.exchange.displayTime}');
		$("#exgRespTime").html("${exgDetail.exchange.displayExchangeTime}");
		var exchangeState = '${exgDetail.exchange.exchangeState}';
		$("#exgStatus").html(itemBuzFmt(exchangeState));
		var exgFinishStatus = '${exgDetail.exchange.exchangeFinishStatus}';
		$("#exgFinishStatus").html(itemFinishFmt(exgFinishStatus));
		if (exchangeState == "cancel"
				|| (exgFinishStatus != null && exgFinishStatus != 'wait')) {//已取消的交易就不需要显示
			$("#exgOprBtns").hide();
		}
		initImgs();
		initBtns();
	}
	function initModal() {
		/* $("#detailInfo").on('shown.bs.modal',function(){
			var url = "${pageContext.request.contextPath}/Jsp/showUserInfo.jsp?userId=${exgDetail.exchange.changerId}";
			$(this).load(url);
			$("#userDetailInfo").load(url);
			console.log('modal has show');
		}); */
		// 		$("#detailInfo").modal("show");
		$("#detailInfo").on("hidden.bs.modal", function() {
			$(this).removeData("bs.modal");
			console.log("modal has removeData");
		});
		$("#detailInfo").on("show.bs.modal",function(){
	        $(".modal-backdrop").remove();//删除class值为modal-backdrop的标签，可去除阴影
		});
		/* $("#detailInfo").on('blur',function(){
			$(this).modal('hide');
		}) */
	}

	function transdate(endTime) {
		var date = new Date();
		date.setFullYear(endTime.substring(0, 4));
		date.setMonth(endTime.substring(5, 7) - 1);
		date.setDate(endTime.substring(8, 10));
		date.setHours(endTime.substring(11, 13));
		date.setMinutes(endTime.substring(14, 16));
		date.setSeconds(endTime.substring(17, 19));
		return Date.parse(date) / 1000;
	}
	function test() {
		alert("callback");
	}
</script>
</html>