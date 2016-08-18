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
						<label class="control-label col-sm-1" for="paramGroup">组别名称</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" id="paramGroup" name="paramGroup">
						</div>
						<label class="control-label col-sm-1" for="paramName">字典名称</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" id="paramName">
						</div>
						<div class="col-sm-4" style="text-align: left;">
							<button type="button" style="margin-left: 50px" id="btn_query"
								class="btn btn-primary">查询</button>
						</div>
					</div>
				</form>
			</div>
		</div>

		<div id="toolbar" class="btn-group">
			<button id="btn_add" style="margin-right:10px;" class="btn btn-primary">
				<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
			</button>
			<!-- <button id="btn_edit" type="button" class="btn btn-default">
				<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
			</button> -->
			<button id="btn_delete" style="margin-right:10px;" class="btn btn-danger">
				<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
			</button>
		</div>
		
		<table id="confParamInfo" class="table table-bordered table-striped"></table>
	</div>
	<div class="modal fade" id="manageConfParam"></div>
</body>
<script type="text/javascript">
//模态对话框隐藏时移除数据  
	$(function() {
		var oTable = new TableInit();
		oTable.Init();
		var oButtons = new ButtonInit();
		oButtons.Init();
	});

	var ButtonInit = function() {
		var oButtonInit = new Object();
		oButtonInit.Init = function() {
			$("#btn_query").bind("click", function() {
				$('#confParamInfo').bootstrapTable('refresh',{url:'${pageContext.request.contextPath}/admin/showParamsInfo'});
			});
			$("#btn_add").bind("click",function(){
				//模态对话框隐藏时移除数据  
				//$("#myModal").on("hidden", function() {    $(this).removeData("modal");  });
				// 显示模态对话框  admin/toAddParamsInfo
				var remote = "${pageContext.request.contextPath}/admin/home";
				if(remote != ""){
				  $("#manageConfParam").load(remote,function(){
					  $("#manageConfParam").modal('show');
				  });
				}
			});
			$("#btn_delete").bind("click",function(){
				
			});
		};
		return oButtonInit;
	};
	var TableInit = function() {
		var oTableInit = new Object();
		oTableInit.Init = function() {
			$("#confParamInfo").bootstrapTable({
// 				url : '${pageContext.request.contextPath}/admin/showParamsInfo',
				method : 'get', //请求方式（*）
				toolbar : '#toolbar', //工具按钮用哪个容器
				striped : true, //是否显示行间隔色
				cache : false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
				pagination : true, //是否显示分页（*）
				sortable : false, //是否启用排序
				sortOrder : "asc", //排序方式
				queryParams : oTableInit.queryParams,//传递参数（*）
				sidePagination : "server", //分页方式：client客户端分页，server服务端分页（*）
// 				pageNumber : 1, //初始化加载第一页，默认第一页
				pageSize : 10, //每页的记录行数（*）
				pageList : [ 10, 25, 50, 100 ], //可供选择的每页的行数（*）
// 				search : true, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
				strictSearch : true,
// 				showColumns : true, //是否显示所有的列
				showRefresh : true, //是否显示刷新按钮
				minimumCountColumns : 2, //最少允许的列数
				clickToSelect : false, //是否启用点击选中行
				// 				height : auto, //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
// 				uniqueId : "ID", //每一行的唯一标识，一般为主键列
// 				showToggle : true, //是否显示详细视图和列表视图的切换按钮
// 				cardView : false, //是否显示详细视图
				detailView : false, //是否显示父子表
				queryParamsType : "limit", //参数格式,发送标准的RESTFul类型的参数请求
				silent : true, //刷新事件必须设置
				columns : [ {
					checkbox : true
				}, {
					field : 'paramid',
					align : 'center',
					valign : 'center',
					title : '参数值'
				}, {
					field : 'paramname',
					align : 'center',
					valign : 'center',
					title : '参数名'
				}, {
					field : 'paramgroup',
					align : 'center',
					valign : 'center',
					title : '参数组'
				}, {
					field : 'paramdesc',
					align : 'center',
					valign : 'center',
					title : '参数描述'
				}, {
					field : 'addtime',
					align : 'center',
					valign : 'center',
					title : '添加时间',
					formatter:'dateTimeFormatter'
				},  {
					field : 'updatetime',
					align : 'center',
					valign : 'center',
					title : '更新时间',
					formatter:'dateTimeFormatter'
				}, {
					field : 'status',
					align : 'center',
					valign : 'center',
					title : '状态'
				}],
				onLoadSuccess:function(data){
					parent.document.getElementById("content").height = document.body.scrollHeight;
				},
				formatLoadingMessage : function() {
					return "请稍等，正在加载中...";
				},
				formatNoMatches : function() { //没有匹配的结果
					return '无符合条件的记录';
				},
				onClickRow : function(row) {
					console.log(row);
				}
			});
		};
		oTableInit.queryParams = function(params) {
			var temp = { //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
				limit : params.limit, //页面大小
				offset : params.offset, //页码
				url : $("#paramGroup").val(),
				title : $("#paramName").val()
			};
			return temp;
		};
		return oTableInit;
	};
</script>
</html>