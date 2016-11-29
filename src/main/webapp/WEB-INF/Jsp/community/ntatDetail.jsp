<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>帖子详情</title>
<jsp:include page="/common/common_css.jsp" />
<jsp:include page="/common/common_js.jsp" />
<style type="text/css">
#toolbar > button{
	margin-right: 20px;
	width: 80px;
}
</style>
</head>
<body>

	<div class="panel-body" style="padding-bottom: 0px;">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">详细信息</h3>
			</div>
			<div class="panel-body">
				<div id="toolbar">
					<button class="btn btn-primary" id="forceToTopBtn" style="margin-right: 20px;">置顶</button>
					<button class="btn btn-warning" id="cancleToTopBtn" style="margin-right: 20px;">取消置顶</button>
					<button class="btn btn-primary" id="forceToEssenceBtn" style="margin-right: 20px;">加精</button>
					<button class="btn btn-warning" id="cancleToEssenceBtn" style="margin-right: 20px;">取消加精</button>
					<button class="btn btn-primary" id="removePostBtn" style="margin-right: 20px;" data-toggle="modal"
						data-target="#removePostModal">移帖</button>
					<button class="btn btn-danger" id="deleteNtatBtn" style="margin-right: 20px;">删帖</button>
					<button class="btn btn-success" id="btnPublish" style="margin-right: 20px;" 
						data-toggle="modal" data-target="#publishArticleModal">
						发布
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
	
	<div>
		<input type="hidden" value=${ntatId } id="ntatIdInput">
	</div>
	
	<div class="modal fade" id="removePostModal" aria-hidden="true">
		<div class="modal-dialog" style="width: 400px; margin-top: 200px;">
			<div class="modal-content">
				<div class="modal-header">移帖至</div>
				<div class="modal-body" style="text-align: center">
					<select id="cmtyId" class="form-control"
						name="cmtyId" style="width: 150px; ">
					</select>
				</div>
				<div class="modal-footer">
					<button class="btn btn-default" id="removeCmtyBtn">确定</button>
					<button class="btn btn-primary" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="publishArticleModal" aria-hidden="true">
		<div class="modal-dialog" style="width: 400px; margin-top: 200px;">
			<div class="modal-content">
				<div class="modal-header">发布至</div>
				<div class="modal-body" style="text-align: center">
					<select id="publishCmtyId" class="form-control"
						name="publishCmtyId" style="width: 150px; ">
					</select>
				</div>
				<div class="modal-footer">
					<button class="btn btn-default" id="publishArticleBtn">确定</button>
					<button class="btn btn-primary" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
	<script src="${pageContext.request.contextPath}/include/pagejs/community.js"></script>
</body>
<script type="text/javascript">
	$(function() {
		//var ntatId = "${ntatId}";
		var ntatId = $("#ntatIdInput").val();
		if(ntatId.endWith("nt")){
			//说明是 帖子，则发布不可见
			$("#btnPublish").hide();
		}else if(ntatId.endWith("at")){
			//说明是 文章，则发布可见
			$("#btnPublish").show();
		}else{
			$("#btnPublish").hide();
		}
		//initCurrentPage(); display: inline-block
		CmtyComm.initCmtySelect("cmtyId");
		CmtyComm.initCmtySelect("publishCmtyId");
		initItemDetailTable(ntatId);
		buttonInitFunc();
	});

	function initItemDetailTable(ntatId) {
		var columns = [{
			field : 'id',
			align : 'center',
			valign : 'center',
			visible : false,
			title : '编号',
		}, {
			field : 'authorName',
			align : 'center',
			valign : 'center',
			title : '用户',
		}, {
			field : 'authorId',
			align : 'center',
			valign : 'center',
			visible : false,
			title : '用户',
		}, {
			field : 'cmtyName',
			align : 'center',
			valign : 'center',
			title : '社群',
		}, {
			field : 'typeName',
			align : 'center',
			valign : 'center',
			title : '分类',
		}, {
			field : 'topFlag',
			align : 'center',
			valign : 'center',
			title : '是否置顶',
		}, {
			field : 'hotFlag',
			align : 'center',
			valign : 'center',
			title : '是否精华',
		}, {
			field : 'showCreateTime',
			align : 'center',
			valign : 'center',
			title : '发布时间',
		}, {
			field : 'likeNum',
			align : 'center',
			valign : 'center',
			title : '点赞数',
		}, {
			field : 'cmtNum',
			align : 'center',
			valign : 'center',
			title : '评论数',
		}, {
			field : 'readNum',
			align : 'center',
			valign : 'center',
			visible : false,
			title : '阅读数',
		}, {
			field : 'status',
			align : 'center',
			valign : 'center',
			title : '状态',
		}, {
			field : 'uId',
			align : 'center',
			valign : 'center',
			title : 'UID',
		} ];
		var oTable = new TableInit("itemDetail", columns, "toolbar",
				'${pageContext.request.contextPath}/community/getNtatDetail?ntatId='+ntatId,
				null, null, false, onBtsCallback);
		oTable.Init();
	}

	function onBtsCallback(data){
		if(data.retCode){
			alert(data.retMsg);
			return;
		}
		//$("#itemDetail").bootstrapTable('load', data);//加载item数据到表格中
	}
	
	
	function initCurrentPage() {
		//获取tabel的数据
		$.get('json/post.json',function(tdData) {
			var imgUrls = tdData.images;
			$.each(imgUrls,function(index, postImage) {
				if (postImage.url != null && postImage.url != "") {
					var img = '<img src="' + postImage.url
							+ '" onclick="showImg(this.src)" width="100px" height="100px" style="margin-top:5px;margin-bottom:5px;margin-right:10px;"/>';
					$("#itemImgs").append(img);
				}
			});
	
		});

		//获取用户信息
		$.get('json/userInfo.json', function(data) {
			console.log(data.userNickName);
			console.log(data.userMobile);
			$("#userNickName").html(data.userNickName);
			$("#userMobile").html(data.userMobile);
			$("#userHeadImgUrl").attr("src", data.userHeadImgUrl);
		})
	}

	function buttonInitFunc() {
		//置顶
		$("#forceToTopBtn").bind("click", function() {
			var ntat = $("#itemDetail").bootstrapTable('getData')[0];
			if(ntat.topFlag == "yes"){
				alert("已被置顶，请勿重复操作")
				return false;
			}
			if(confirm("确认置顶？")){
				var url = globalPath + "/community/opNtat?opType=forceToTop&ntatId="+ntat.id;
				$.get(url,function(obj){
					var data = JSON.parse(obj);
					if(!data.retFlag){
						alert(data.retMsg);
					}
					location.reload();
				});
			}
		});
		//取消置顶
		$("#cancleToTopBtn").bind("click", function() {
			var ntat = $("#itemDetail").bootstrapTable('getData')[0];
			if(ntat.topFlag == "no"){
				alert("该项未被置顶")
				return false;
			}
			if(confirm("确认取消置顶？")){
				var url = globalPath + "/community/opNtat?opType=cancelToTop&ntatId="+ntat.id;
				$.get(url,function(obj){
					var data = JSON.parse(obj);
					if(!data.retFlag){
						alert(data.retMsg);
					}
					location.reload();
				});
			}
		});
		//加精
		$("#forceToEssenceBtn").bind("click", function() {
			var ntat = $("#itemDetail").bootstrapTable('getData')[0];
			if(ntat.hotFlag == "yes"){
				alert("已被加精，请勿重复操作")
				return false;
			}
			if(confirm("确认加精？")){
				var url = globalPath + "/community/opNtat?opType=forceToEssence&ntatId="+ntat.id;
				$.get(url,function(obj){
					var data = JSON.parse(obj);
					if(!data.retFlag){
						alert(data.retMsg);
					}
					location.reload();
				});
			};
		});
		//取消加精
		$("#cancleToEssenceBtn").bind("click", function() {
			var ntat = $("#itemDetail").bootstrapTable('getData')[0];
			if(ntat.hotFlag == "no"){
				alert("该项未被加精")
				return false;
			}
			if(confirm("确认取消加精？")){
				var url = globalPath + "/community/opNtat?opType=cancelToEssence&ntatId="+ntat.id;
				$.get(url,function(obj){
					var data = JSON.parse(obj);
					if(!data.retFlag){
						alert(data.retMsg);
					}
					location.reload();
				});
			}
		});
		//移帖
		$("#removeCmtyBtn").bind("click", function() {
			var ntat = $("#itemDetail").bootstrapTable('getData')[0];
			var cmtyId = $("#cmtyId").val();
			if(cmtyId == 0 || cmtyId == ntat.cmtyId){
				alert("请选择要移至的社群,不能和之前的相同");
				return false;
			}
			if(confirm("确认移动该项至另一个社群？")){
				var url = globalPath + "/community/opNtat?opType=changeCmty&ntatId="+ntat.id+"&cmtyId="+$("#cmtyId").val();
				$.get(url,function(obj){
					var data = JSON.parse(obj);
					if(!data.retFlag){
						alert(data.retMsg);
					}
					location.reload();
				});
				$('#removePostModal').modal('hide');
			}
		});
		//删帖
		$("#deleteNtatBtn").bind("click", function() {
			var ntat = $("#itemDetail").bootstrapTable('getData')[0];
			if(ntat.status == "deleted"){
				alert("已被被管理员删除或下架，请勿重复操作")
				return false;
			}
			if(confirm("确认删除下架该项，此操作不可恢复？")){
				var url = globalPath + "/community/opNtat?opType=deleteNtat&ntatId="+ntat.id;
				$.get(url,function(obj){
					var data = JSON.parse(obj);
					if(!data.retFlag){
						alert(data.retMsg);
					}
					location.reload();
				});
			}
		});
		//发布文章
		$("#publishArticleBtn").bind("click", function() {
			var ntat = $("#itemDetail").bootstrapTable('getData')[0];
			if(ntat.isPublished){
				alert("该文章已经发布，请选择其它文章操作");
				return false;
			}
			var cmtyId = $("#cmtyId").val();
			if(confirm("确认发布文章至该社群？")){
				var url = globalPath + "/community/opNtat?opType=changeCmty&ntatId="+ntat.id+"&cmtyId="+$("#cmtyId").val();
				$.get(url,function(obj){
					var data = JSON.parse(obj);
					if(!data.retFlag){
						alert(data.retMsg);
					}
					location.reload();
				});
				$('#publishArticleModal').modal('hide');
			}
		});
	}

</script>
</html>