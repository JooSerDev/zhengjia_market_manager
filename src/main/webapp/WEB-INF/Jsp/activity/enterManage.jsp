<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>线下报名管理</title>
<jsp:include page="/common/common_css.jsp" />
<jsp:include page="/common/common_js.jsp" />
</head>
<body>
	<div class="panel-body" style="padding-bottom: 0px;">
		<div class="panel panel-default">
			<div class="panel-heading">查询条件</div>
			<div class="panel-body">
				<form id="formSearch" class="form-horizontal">
					<div class="form-group">
						<label class="control-label col-sm-1" for="approvalStatus">筛
							选</label>
						<div class="col-sm-2">
							<select id="approvalStatus" class="form-control"
								name="approvalStatus">
								<option value="all" >全部</option>
								<option value="waiting" selected="selected">未审核</option>
								<option value="approved">审核通过</option>
								<option value="refused">审核拒绝</option>
							</select>
						</div>
						<div class="col-sm-2" style="text-align: left;">
							<button type="button" style="margin-left: 50px" id="btn_query"
								class="btn btn-primary">查询</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		<div id="toolbar">
			<button class="btn btn-primary" id="addActivity" data-toggle="modal"
				data-target="#configCurrentActyModal" style="margin-right: 20px;">本期活动设置</button>
			<button class="btn btn-primary" id="approveEnterBtn" style="margin-right: 20px;">通过</button>
			<button class="btn btn-warn" id="refuseEnterBtn" style="margin-right: 20px;">拒绝</button>
		</div>
		<div id="itemsInfoDiv" styl e="margin-bottom: 20px;">
			<table id="itemsInfo" class="table table-bordered table-striped"></table>
		</div>
	</div>

	<div class="modal fade" id="configCurrentActyModal" aria-hidden="true">
		<div class="modal-dialog" style="width: 400px; margin-top: 200px;">
			<div class="modal-content">
				<div class="modal-body">
					
				</div>
				<div class="modal-footer">
					<button class="btn btn-default" id="saveCurrentConf">确定</button>
					<button class="btn btn-primary" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>

</body>

<script type="text/javascript">
    //模态对话框隐藏时移除数据
    $(function() {
        buttonInitFunc();
        initItemsInfoTable();
        //$("#btn_query").click();
    });
    
    function initItemsInfoTable() {
        var columns = [{
        	radio : true
        }, {
            field : 'oId',
            align : 'center',
            valign : 'center',
            visible : false,
            title : '编号',
        }, {
            field : 'actyTime',
            align : 'center',
            valign : 'center',
            title : '活动周期',
        }, {
            field : 'enterTime',
            align : 'center',
            valign : 'center',
            title : '申请提交时间',
        }, {
            field : 'userName',
            align : 'center',
            valign : 'center',
            title : '申请人姓名',
        }, {
            field : 'userMobile',
            align : 'center',
            valign : 'center',
            title : '手机号',
        }, {
            field : 'approvalStatus',
            align : 'center',
            valign : 'center',
            title : '审核状态',
        }, {
        	field : 'userRemark',
            align : 'center',
            valign : 'center',
            width: '100px',
            title : '附言',
        },{
        	field : 'actyId',
            align : 'center',
            valign : 'center',
            visible : false,
            title : '活动编号',
        }];
        var url = globalPath + "/activity/getActyEnterListByPage";
        var oTable = new TableInit("itemsInfo", columns, "toolbar", 
        		url,queryParamsFunc, null,true);
        oTable.Init();
    }

    function queryParamsFunc(params) {
        var temp = {
            limit : params.limit,
            offset : params.offset,
	 		approvalStatus : $("#approvalStatus").val()
        };
        return temp;
    }

    function onclickRowFunc(row) {
       	if (row != null) {
   			window.location.href = globalPath+"/activity/enterDetail?enterId="+row.oId;
   		}
    }

    function buttonInitFunc() {
        $("#btn_query").bind("click", function() {
            $('#itemsInfo').bootstrapTable('refresh', {
                url : globalPath+'/activity/getActyEnterListByPage'
            });
        });
        $("#saveCurrentConf").bind("click",function(){
        	$("#configCurrentActyModal").hide();
        });
        $("#approveEnterBtn").bind("click",function(){
        	var row = $("#itemsInfo").bootstrapTable("getSelections")[0];
        	if(row.status == 'approved'){
        		alert("该报名已通过审核，无需重复操作。")
        		return false;
        	}
        	if(confirm("确定该报名通过审核？")){
				var data = {
					oId : row.oId,
					opType: "approved",
					actyId: row.actyId
				};
				$.post(globalPath+"/activity/approvalTheEnter",data,function(data){
					var retObj = JSON.parse(data);
					alert(retObje.retMsg);
				});
			}
        });
        $("#refuseEnterBtn").bind("click",function(){
        	var row = $("#itemsInfo").bootstrapTable("getSelections")[0];
        	if(row.status == 'approved'){
        		alert("该报名已被拒绝，无需重复操作。")
        		return false;
        	}
        	if(confirm("确定拒绝该报名？")){
				var data = {
					oId : row.oId,
					opType: "refused",
					actyId: row.actyId
				};
				$.post(globalPath+"/activity/approvalTheEnter",data,function(data){
					var retObj = JSON.parse(data);
					alert(retObje.retMsg);
				});
			}
        });
    }
</script>
</html>