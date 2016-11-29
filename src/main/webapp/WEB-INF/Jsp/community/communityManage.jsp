<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>社群管理</title>
<jsp:include page="/common/common_css.jsp" />
<jsp:include page="/common/common_js.jsp" />
</head>
<body>
	<div class="panel-body" style="padding-bottom: 0px;">
		<div class="panel panel-default">
			<div class="panel-heading">查询条件</div>
			<div class="panel-body">
				<form id="formSearch" class="form-horizontal">
					<div class="form-group" >
						<label class="control-label col-sm-1" for="ntatType">筛 选</label>
						<div class="col-sm-2">
							<select id="ntatType" class="form-control" name="ntatType">
								<!-- <option value="all" selected="selected">全部</option> -->
								<option value="note" >帖子</option>
								<option value="article" >文章</option>
							</select>
						</div>
						<label class="control-label col-sm-1" for="cmtyId">社群分类</label>
						<div class="col-sm-2">
							<select id="cmtyId" class="form-control" name="cmtyId">
								<option value="0" selected="selected">全部</option>
								<c:forEach items="${cmtyTypes}" var="cmty">
									<option value="${cmty.cmtyId }">${cmty.cmtyName }</option>
								</c:forEach>
							</select>
						</div>
						<label class="control-label col-sm-1" for="typeId">帖子分类</label>
						<div class="col-sm-2">
							<select id="typeId" class="form-control" name="typeId"></select>
						</div>
					</div>
					<div class="form-group">
						
						<label class="control-label col-sm-1" for="title">标题</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" name="title" id="title">
						</div>
						<div class="col-sm-2" style="text-align: left;">
							<button type="button" style="margin-left: 50px" id="btn_query"
								class="btn btn-primary">查询</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		<div id="itemsInfoDiv" style="margin-bottom: 20px;">
			<table id="itemsInfo" class="table table-bordered table-striped"></table>
		</div>
	</div>
	<script src="${pageContext.request.contextPath}/include/pagejs/community.js"></script>
</body>

<script type="text/javascript">
    //模态对话框隐藏时移除数据
    $(function() {
    	//alert("${pageContext.request.contextPath}");
        buttonInitFunc();
        initItemsInfoTable();
        //$("#btn_query").click();
    });
    
    function initItemsInfoTable() {
        var columns = [{
            field : 'id',
            align : 'center',
            valign : 'center',
            visible : false,
            title : '编号',
        }, {
            field : 'ntatTypeFlag',
            align : 'center',
            valign : 'center',
            title : '类型',
        }, {
            field : 'title',
            align : 'center',
            valign : 'center',
            title : '标题',
        }, {
            field : 'cmtyName',
            align : 'center',
            valign : 'center',
            title : '所属社群',
        }, {
            field : 'typeName',
            align : 'center',
            valign : 'center',
            title : '帖子分类',
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
            field : 'authorName',
            align : 'center',
            valign : 'center',
            title : '发布者昵称',
        }, {
            field : 'showCreateTime',
            align : 'center',
            valign : 'center',
            title : '发布时间',
        }, {
            field : 'uId',
            align : 'center',
            valign : 'center',
            title : '发布者UID',
        } ];
        var url = globalPath + "/community/getNtatListByPage";
        //${pageContext.request.contextPath}/community/getNtatListByPage
        var oTable = new TableInit("itemsInfo", columns, null, 
        		url,queryParamsFunc, onclickRowFunc,true);
        oTable.Init();
    }

    function queryParamsFunc(params) {
        var temp = {
            limit : params.limit,
            offset : params.offset,
            cmtyId : $("#cmtyId").val(),
            ntatType : $("#ntatType").val(),
            typeId : $("#typeId").val(),
            noteTitle : $("#title").val()
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
                url : globalPath+'/community/getNtatListByPage'
            });
        });
        /* $("#cmtyId").on("change",function(cmtyId){
        	CmtyComm.initCmtySubSelect("typeId", cmtyId);
        }); */
        $("#cmtyId").change(function(){
        	CmtyComm.initCmtySubSelect("typeId", $("#cmtyId").val());
        });
    }
</script>
</html>