<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文章详情</title>
<jsp:include page="/common/common_css.jsp" />
<jsp:include page="/common/common_js.jsp" />
</head>
<body>

	<div class="panel-body" style="padding-bottom: 0px;">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">文章信息</h3>
			</div>
			<div class="panel-body">
				<div id="toolbar">


					<div style="display: inline-block; margin-right: 20px">
						<button class="btn btn-primary" id="forceToTop"
							style="margin-right: 20px;" data-toggle="modal"
							data-target="#forceTotopModal">置顶</button>
						<button class="btn btn-default" id="cancleToTop"
							data-toggle="modal" data-target="#cancleToTopModal">
							取消置顶</button>
					</div>


					<div style="display: inline-block; margin-right: 20px">
						<button class="btn btn-primary" id="addEssence"
							style="margin-right: 20px;" data-toggle="modal"
							data-target="#addEssenceModal">加精</button>
						<button class="btn btn-default" id="cancleEssence"
							data-target="#cancleEssenceModal" data-toggle="modal">
							取消加精</button>
					</div>

					<div style="display: inline-block; margin-right: 20px">
						<button class="btn btn-primary" id="removePostBtn"
							style="margin-right: 20px;" data-toggle="modal"
							data-target="#removePostModal">移帖</button>
						<button class="btn btn-danger" id="deletePost" data-toggle="modal"
							data-target="#deletePostModal">删帖</button>
					</div>

					<div style="display: inline-block; margin-right: 20px">
						<button class="btn btn-warning" id="editArticle"
							style="margin-right: 20px;">编辑</button>
						<button class="btn btn-warning" id="canclePostArticle"
							data-toggle="modal" data-target="#moveOutArticleModal">
							下撤</button>
					</div>
					<button class="btn btn-danger" id="publishArticle"
						data-toggle="modal" data-target="#publishArticleModal">
						发布</button>

				</div>
				<table id="itemDetail" class="table table-bordered table-striped"></table>
				<div style="margin-top: 20px">
					<label style="margin-right: 50px; font-size: medium">外链URL:</label>
					<label id="outUrl"></label>
				</div>
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

	<div class="modal fade" id="forceTotopModal" aria-hidden="true">
		<div class="modal-dialog" style="width: 400px; margin-top: 200px;">
			<div class="modal-content">
				<div class="modal-body">确认要置顶吗？</div>
				<div class="modal-footer">
					<button class="btn btn-default" id="forceToTopBtn">确定</button>
					<button class="btn btn-primary" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="cancleToTopModal" aria-hidden="true">
		<div class="modal-dialog" style="width: 400px; margin-top: 200px;">
			<div class="modal-content">
				<div class="modal-body">确认要取消置顶吗？</div>
				<div class="modal-footer">
					<button class="btn btn-default" id="cancleToTopBtn">确定</button>
					<button class="btn btn-primary" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="addEssenceModal" aria-hidden="true">
		<div class="modal-dialog" style="width: 400px; margin-top: 200px;">
			<div class="modal-content">
				<div class="modal-body">确认要加精吗？</div>
				<div class="modal-footer">
					<button class="btn btn-default" id="addEssenceBtn">确定</button>
					<button class="btn btn-primary" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="cancleEssenceModal" aria-hidden="true">
		<div class="modal-dialog" style="width: 400px; margin-top: 200px;">
			<div class="modal-content">
				<div class="modal-body">确认要取消加精吗？</div>
				<div class="modal-footer">
					<button class="btn btn-default" id="cancleEssenceBtn">确定</button>
					<button class="btn btn-primary" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="removePostModal" aria-hidden="true">
		<div class="modal-dialog" style="width: 400px; margin-top: 200px;">
			<div class="modal-content">
				<div class="modal-header">移帖至</div>
				<div class="modal-body" style="text-align: center">
					<select id="removePostSelect" class="form-control"
						name="removePost" style="width: 150px; display: inline-block">
					</select>
				</div>
				<div class="modal-footer">
					<button class="btn btn-default" id="removePostButton">确定</button>
					<button class="btn btn-primary" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="deletePostModal" aria-hidden="true">
		<div class="modal-dialog" style="width: 400px; margin-top: 200px;">
			<div class="modal-content">
				<div class="modal-body">此操作不可恢复,确定要删除此贴吗?</div>
				<div class="modal-footer">
					<button class="btn btn-default" id="deletePostBtn">确定</button>
					<button class="btn btn-primary" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="moveOutArticleModal" aria-hidden="true">
		<div class="modal-dialog" style="width: 400px; margin-top: 200px;">
			<div class="modal-content">
				<div class="modal-body">确定要下撤此文章吗?</div>
				<div class="modal-footer">
					<button class="btn btn-default" id="moveOutArticleBtn">确定</button>
					<button class="btn btn-primary" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="publishArticleModal" aria-hidden="true">
		<div class="modal-dialog" style="width: 400px; margin-top: 200px;">
			<div class="modal-content">
				<div class="modal-header">发帖至</div>
				<div class="modal-body" style="text-align: center">
					<select id="publishArticleSelect" class="form-control"
						name="removePost" style="width: 150px; display: inline-block">
					</select>
				</div>
				<div class="modal-footer">
					<button class="btn btn-default" id="publishArticleBtn">确定</button>
					<button class="btn btn-primary" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>

</body>

<script type="text/javascript">
	$(function() {
		initCurrentPage();
		initItemsInfoTale();
		buttonInitFunc();
	});

	function initItemsInfoTale() {
		var columns = [ {
			field : 'itemId',
			align : 'center',
			valign : 'center',
			title : '文章列表',
		}, {
			field : 'name',
			align : 'center',
			valign : 'center',
			title : '发布者昵称',
		}, {
			field : 'isPublish',
			align : 'center',
			valign : 'center',
			title : '是否发布',
		}, {
			field : 'articleCreatTime',
			align : 'center',
			valign : 'center',
			title : '文章生成时间',
		}, {
			field : 'lastChangeTime',
			align : 'center',
			valign : 'center',
			title : '最后编辑时间',
		} ];

		var oTable = new TableInit("itemDetail", columns, null,
				"json/article.json", null, null, false, null);

		oTable.Init();
	}

	function initCurrentPage() {

		$.get('json/postItemClasses.json', function(data) {
			$.each(data, function(index, currentClass) {
				$("#removePostSelect").append(
						'<option value=currentClass.value>' + currentClass.text
								+ '</option>');
			})
		})
		$.get('json/postItemClasses.json', function(data) {
			$.each(data, function(index, currentClass) {
				$("#publishArticleSelect").append(
						'<option value=currentClass.value>' + currentClass.text
								+ '</option>');
			})
		})

		//获取tabel的数据
		$
				.get(
						'json/article.json',
						function(tdData) {

							var outUrl = tdData.outUrl;
							$("#outUrl").html(outUrl);
							var imgUrls = tdData.images;
							$
									.each(
											imgUrls,
											function(index, postImage) {
												if (postImage.url != null
														&& postImage.url != "") {
													var img = '<img src="'
															+ postImage.url
															+ '" onclick="showImg(this.src)" width="100px" height="100px" style="margin-top:5px;margin-bottom:5px;margin-right:10px;"/>';
													$("#itemImgs").append(img);
												}
											});

						})

		//获取用户信息
		$.get('json/userInfo.json', function(data) {
			$("#userNickName").html(data.userNickName);
			$("#userMobile").html(data.userMobile);
			$("#userHeadImgUrl").attr("src", data.userHeadImgUrl);
		})
	}

	function buttonInitFunc() {
		//置顶
		$("#forceToTopBtn").bind("click", function() {
			reloadPage("forceTotopModal");
		});
		//取消置顶
		$("#cancleToTopBtn").bind("click", function() {
			reloadPage("cancleToTopModal");
		});
		//加精
		$("#addEssenceBtn").bind("click", function() {
			reloadPage("addEssenceModal");
		});
		//取消加精
		$("#cancleEssenceBtn").bind("click", function() {
			reloadPage("cancleEssenceModal");
		});
		//移帖
		$("#removePostButton").bind("click", function() {

			var data = {
				itemType : $("#removePostSelect").val()
			}

			$.post('', data, function() {
				$('#itemDetail').bootstrapTable('refresh', {
					url : 'json/post.json'
				});
			})
			$('#removePostModal').modal('hide')
		});
		//删帖
		$("#deletePostBtn").bind("click", function() {

			var data = {
				itemId : ''
			}
			$.post('', data, function() {

			})
			$('#deletePostModal').modal('hide')
		});
		//编辑
		$("#cancleEssenceBtn").bind("click", function() {

		});
		//下撤
		$("#moveOutArticleBtn").bind("click", function() {
			reloadPage("moveOutArticleModal");
		});

		//发布
		$("#publishArticleBtn").bind("click", function() {

			var data = {
				itemType : $("#publishArticleSelect").val()
			}

			$.post('', data, function() {
				$('#itemDetail').bootstrapTable('refresh', {
					url : 'json/post.json'
				});
			})
			$('#publishArticleModal').modal('hide')
		});
	}

	function reloadPage(modalId) {
		$.post('', function(data) {
			$('#itemDetail').bootstrapTable('refresh', {
				url : 'json/post.json'
			});
		})

		$('#' + modalId).modal('hide')
	}
</script>
</html>