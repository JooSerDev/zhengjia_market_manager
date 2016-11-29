<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文章管理</title>
<jsp:include page="/common/common_css.jsp" />
<jsp:include page="/common/common_js.jsp" />
</head>
<body>
	<div class="panel-body" style="padding-bottom: 0px;">
		<div class="panel panel-default">
			<div class="panel-heading">查询条件</div>
			<div class="panel-body">
				<form id="formSearch" class="form-horizontal">
					<div class="form-group" style="margin-top: 15px">
						<%-- <label class="control-label col-sm-1" for="cmtyId">社群分类</label>
						<div class="col-sm-2">
							<select id="cmtyId" class="form-control" name="cmtyId">
								<option value="0" selected="selected">全部</option>
								<c:forEach items="${cmtyTypes}" var="cmty">
									<option value="${cmty.cmtyId }">${cmty.cmtyName }</option>
								</c:forEach>
							</select>
						</div> --%>
						<label class="control-label col-sm-1" for="status">是否发布</label>
						<div class="col-sm-2">
							<select id="status" class="form-control" name="status">
								<option value="normal">已发布</option>
								<option value="waiting">未发布</option>
							</select>
						</div>
						<label class="control-label col-sm-1" for="title">文章标题</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" id="title"
								placeholder="请输入文章标题查询">
						</div>
						<div class="col-sm-2">
							<button type="button" style="margin-left: 50px" id="btn_query"
								class="btn btn-primary">查询</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		
		<div id="toolbar" >
			<button id="btnAdd" style="margin-right: 20px;"class="btn btn-primary">
				添加
			</button>
			<button id="btnEdit" style="margin-right: 20px;" class="btn btn-primary">
				编辑
			</button>
		</div>
		<div id="itemsInfoDiv" style="margin-bottom: 20px;">
			<table id="itemsInfo" class="table table-bordered table-striped"></table>
		</div>
	</div>
	
	<div class="modal fade" id="removePostModal" aria-hidden="true">
		<div class="modal-dialog" style="width: 400px; margin-top: 200px;">
			<div class="modal-content">
				<div class="modal-header">发布至</div>
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
	<script src="${pageContext.request.contextPath}/include/pagejs/community.js"></script>
</body>

<script type="text/javascript">
	//模态对话框隐藏时移除数据
	$(function() {
		initItemsInfoTable();
		CmtyComm.initCmtySelect("cmtyId");
		buttonInitFunc();
	});
	
	function initItemsInfoTable() {
		var columns = [ {
			field : 'id',
			align : 'center',
			valign : 'center',
			title : '编号',
		}, {
            field : 'title',
            align : 'center',
            valign : 'center',
            title : '标题',
        }, {
			field : 'authorName',
			align : 'center',
			valign : 'center',
			title : '发布者昵称',
		}, {
			field : 'isPublished',
			align : 'center',
			valign : 'center',
			title : '是否发布',
		}, {
			field : 'showCreateTime',
			align : 'center',
			valign : 'center',
			title : '生成时间',
		}, {
			field : 'showUpdateTime',
			align : 'center',
			valign : 'center',
			title : '更新时间',
		} ];

		var oTable = new TableInit("itemsInfo", columns, "toolbar",
				'${pageContext.request.contextPath}/community/getNtatListByPage', queryParamsFunc, onclickRowFunc,
				true, null);
		oTable.Init();
	}

	function queryParamsFunc(params) {
		var temp = {
			limit : params.limit,
			offset : params.offset,
			title : $("#title").val(),
			status : $("#status").val(),
	        ntatType : "article",
		};
		return temp;
	}

	function onclickRowFunc(row) {
		if (row != null) {
			window.location.href = globalPath+"/community/ntatDetail?ntatId="+row.id;
		}
	}

	function buttonInitFunc() {
		$("#btn_query").bind("click", function() {
			$('#itemsInfo').bootstrapTable('refresh', {
				url : '${pageContext.request.contextPath}/community/getNtatListByPage'
			});
		});
		$('#btnAdd').bind("click", function() {
			location.href="${pageContext.request.contextPath}/community/writeArticle";
		});
		$('#btnEdit').bind("click", function() {
			var rows = $("#itemsInfo").bootstrapTable('getSelections');
			if (rows == null || rows.length != 1) {
				alert("请选择一项进行操作");
				return ;
			}
			location.href="${pageContext.request.contextPath}/community/writeArticle?id="+rows[0].id;
		});
	}
</script>
</html>