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
	src="${pageContext.request.contextPath}/fxsj/include/pagejs/exchange.js"></script>
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
						<label class="col-md-4 ">甲方昵称：</label> <a class="col-md-4"
							href="${pageContext.request.contextPath}/fxsj/wx/showUserInfo?userId=${exgDetail.exchange.ownerId}"
							data-target="#detailInfo" data-toggle="modal">${exgDetail.user.nickname}</a>
						<label class="col-md-4 ">商品名称：</label> <a class="col-md-4"
							data-target="#detailInfo" data-toggle="modal"
							href="${pageContext.request.contextPath}/fxsj/item/showItemInfo?itemId=${exgDetail.exchange.ownerItemId}">${exgDetail.exchange.ownerItemName}</a>
					</div>
					<div class="form-group" id="userItemImgs" style="margin-left: 5px;"></div>
					<hr>
					<div class="form-group">
						<label class="col-md-4 ">乙方昵称：</label> <a class="col-md-4"
							data-target="#detailInfo" data-toggle="modal"
							href="${pageContext.request.contextPath}/fxsj/wx/showUserInfo?userId=${exgDetail.exchange.changerId}">${exgDetail.target.nickname}</a>
						<label class="col-md-4 ">商品名称：</label> <a class="col-md-4"
							data-target="#detailInfo" data-toggle="modal"
							href="${pageContext.request.contextPath}/fxsj/item/showItemInfo?itemId=${exgDetail.exchange.changerItemId}">${exgDetail.exchange.changerItemName}</a>
					</div>
					<div id="targetItemImgs" class="form-group"
						style="margin-left: 5px;"></div>
					<hr>
					<div class="form-group">
						<label class="col-md-4 ">交易发起时间：</label><span id="exgCreateTime"></span>
						<label class="col-md-4 ">交易响应时间：</label><span id="exgRespTime"></span>
					</div>
					<div class="form-group">
						<label class="col-md-4 ">交易状态：</label><span id="exgStatus"></span>
						<label class="col-md-4 ">见证状态：</label><span id="exgFinishStatus"></span>
					</div>
					<div class="form-group" id="exgOprBtns" style="margin-left: 10px;">
						<button class="btn btn-primary" id="btnExgSus"
							style="margin-left: 20px;">确认认证成功</button>
						<button class="btn btn-warning" id="btnExgFail">认证失败</button>
					</div>
					<div id="failSelect" style="display: none">
						<form role="form" id="dealFailForm">
							<div class="form-group">
								<label class="col-md-4 " for="infoNotReal">信息不实：</label> <label
									class="col-md-4 ">甲方：</label> <input type="checkbox"
									name="infoNotReal" value="owner"> <label
									class="col-md-4 ">乙方：</label> <input type="checkbox"
									name="infoNotReal" value="target">
							</div>
							<div class="form-group">
								<label class="col-md-4 " for="infoNotReal">投诉甲方：</label> <input
									class="col-md-2 " type="checkbox" name="charge" value="owner">
								<div class="col-md-6">
									<input type="text" name="chargeMsgOwn" class="form-control">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-4 " for="infoNotReal">投诉乙方：</label> <input
									class="col-md-2 " type="checkbox" name="charge" value="target">
								<div class="col-md-6">
									<input type="text" name="chargeMsgTarget" class="form-control">
								</div>
							</div>
						</form>
						<div class="form-group" style="margin-left: 10px;">
							<button id="cancelExgDeal" class="btn btn-info">取消</button>
							<button id="ensureFailDeal" class="btn btn-danger">确认交易处置</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="detailInfo">
		<div class="modal-dialog">
			<div class="modal-content">
				<!-- <div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">详细信息</h4>
				</div>
				<div class="modal-body"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div> -->
			</div>
			<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
	<div class="modal fade" id="imgModal"
		style="width: auto; height: auto; margin-left: auto; margin-right: auto;">
		<div class="modal-dialog">
			<div class="modal-content" style="width: 200px;">
				<img id="showImgLarge" height="200px" width="200px" align="center">
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
			$("#btnExgSus").attr("disabled",true);
			$("#failSelect").show();
			frameSuit();
		});
		//交易成功保存
		$("#btnExgSus")
				.on(
						"click",
						function() {
							//TODO 
							if(!confirm("确认认证交易成功")){
								return false;
							}
							var exchangeId = '${exgDetail.exchange.exchangeId}';
							$
									.get(
											"${pageContext.request.contextPath}/fxsj/exg/exgFaceSuccess?exchangeId=${exgDetail.exchange.exchangeId}",
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
													url : '${pageContext.request.contextPath}/fxsj/exg/exgList'
												} */
												window.close();
												//window.location.href = "${pageContext.request.contextPath}/fxsj/exg/exgDetail?exchangeId="+exchangeId;
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
							if (confirm("是否确认进行此操作")) {
								var exchangeId = '${exgDetail.exchange.exchangeId}';
								var sendData = $("#dealFailForm").serialize();
								$
										.get(
												"${pageContext.request.contextPath}/fxsj/exg/exgFaceFail?exchangeId=${exgDetail.exchange.exchangeId}",
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
													$(
															'#exchangeInfo',
															window.parent.document)
															.bootstrapTable(
																	'refresh',
																	{
																		url : '${pageContext.request.contextPath}/fxsj/exg/exgList'
																	});
													window.close();
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
									var img = '<img src="'+url+'" onmouseover="showImg(this.src)" width="100px" height="100px" style="margin-right:10px;"/>';
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
									var img = '<img src="'+url+'" onmouseover="showImg(this.src)" width="100px" height="100px" style="margin-right:10px;"/>';
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
			var url = "${pageContext.request.contextPath}/fxsj/Jsp/showUserInfo.jsp?userId=${exgDetail.exchange.changerId}";
			$(this).load(url);
			console.log('modal has show');
		}) */
		$("#detailInfo").on("hidden.bs.modal", function() {
			$(this).removeData("bs.modal");
			console.log("modal has removeData");
		});
		$("#detailInfo").on('blur',function(){
			$(this).modal('hide');
		})
	}
	
	function transdate(endTime){  
	    var date=new Date();  
	    date.setFullYear(endTime.substring(0,4));  
	    date.setMonth(endTime.substring(5,7)-1);  
	    date.setDate(endTime.substring(8,10));  
	    date.setHours(endTime.substring(11,13));  
	    date.setMinutes(endTime.substring(14,16));  
	    date.setSeconds(endTime.substring(17,19));  
	    return Date.parse(date)/1000;  
	}  
</script>
</html>