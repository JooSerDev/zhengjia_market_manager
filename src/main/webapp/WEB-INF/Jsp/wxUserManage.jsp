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
		<div class="panel panel-default">
			<div class="panel-heading">查询条件</div>
			<div class="panel-body">
				<form id="formSearch" class="form-horizontal">
					<div class="form-group" style="margin-top: 15px">
						<label class="control-label col-sm-1" for="state">过滤条件</label>
						<div class="col-sm-2">
							<select id="state" class="form-control" name="state">
								<option value="-1">全部</option>
								<option value="0">已注册</option>
								<option value="1">未注册</option>
								<option value="2">已封号</option>
							</select>
						</div>
					</div>
					<div class="form-group" style="margin-top: 15px">
						<label class="control-label col-sm-1" for="nickname">用户昵称</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" id="nickname"
								name="nickname">
						</div>
						<label class="control-label col-sm-1" for="mobile">手机号</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" id="mobile" name="mobile">
						</div>
						<label class="control-label col-sm-1" for="openid">OpenId</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" id="openid" name="openid">
						</div>
						<div class="col-sm-3" style="text-align: left;">
							<button type="button" style="margin-left: 50px" id="btn_query"
								class="btn btn-primary">查询</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		<table id="wxUserInfo" class="table table-bordered table-striped"></table>
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
	//模态对话框隐藏时移除数据  
	$(function() {
		parent.document.getElementById("content").height = 500;
		parent.document.body.scrollHeight = 500;
		var oTable = new TableInit();
		oTable.Init();
		var oButtons = new ButtonInit();
		oButtons.Init();
		$("#imgModal").on("hidden.bs.modal", function() {
			$(this).removeData("bs.modal");
		});
	});

	var ButtonInit = function() {
		var oButtonInit = new Object();
		oButtonInit.Init = function() {
			$("#btn_query").bind("click", function() {
				$('#wxUserInfo').bootstrapTable('refresh', {
					url : '${pageContext.request.contextPath}/wx/userList'
				});
			});
		};
		return oButtonInit;
	};
	var TableInit = function() {
		var oTableInit = new Object();
		oTableInit.Init = function() {
			$("#wxUserInfo")
					.bootstrapTable(
							{
								url : '${pageContext.request.contextPath}/wx/userList',
								method : 'get', //请求方式（*）
								striped : true, //是否显示行间隔色
								cache : false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
								pagination : true, //是否显示分页（*）
								sortable : false, //是否启用排序
								sortOrder : "asc", //排序方式
								queryParams : oTableInit.queryParams,//传递参数（*）
								sidePagination : "server", //分页方式：client客户端分页，server服务端分页（*）
								pageSize : 10, //每页的记录行数（*）
								pageList : [ 10, 25, 50, 100 ], //可供选择的每页的行数（*）
								strictSearch : true,
								showRefresh : true, //是否显示刷新按钮
								minimumCountColumns : 2, //最少允许的列数
								clickToSelect : true, //是否启用点击选中行
								detailView : false, //是否显示父子表
								queryParamsType : "limit", //参数格式,发送标准的RESTFul类型的参数请求
								silent : true, //刷新事件必须设置
								columns : [ {
									field : 'userId',
									align : 'center',
									valign : 'center',
									title : '编号'
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
									field : 'state',
									align : 'center',
									valign : 'center',
									title : '状态',
									formatter : 'stateFormat'
								} ],
								onLoadSuccess : function(data) {
									parent.document.getElementById("content").height = document.body.scrollHeight;
								},
								formatShowingRows: function (pageFrom, pageTo, totalRows) {
						            return '展示第 '+pageFrom+' 条到  '+pageTo+' 条，总量： '+totalRows+' 条';
						    	},
								formatLoadingMessage : function() {
									return "请稍等，正在加载中...";
								},
								formatNoMatches : function() { //没有匹配的结果
									return '无符合条件的记录';
								},
								onClickRow : function(row) {
									window.location.href = "${pageContext.request.contextPath}/wx/userDetail?userId="
											+ row.userId;
								}
							});
		};
		oTableInit.queryParams = function(params) {
			var temp = { //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
				limit : params.limit, //页面大小
				offset : params.offset, //页码
				state : $("#state").val(),
				nickname : $("#nickname").val(),
				mobile : $("#mobile").val(),
				openid : $("#openid").val()
			};
			return temp;
		};
		return oTableInit;
	};
	function stateFormat(value) {
		if (value == 0) {
			return "已注册";
		}
		if (value == 1) {
			return "未注册";
		}
		if (value == 2) {
			return "已封号";
		}
	}
	function imgFormat(url) {
		return '<img src="'
				+ url
				+ '" height="40px" width="40px"/>';
	}
</script>
</html>