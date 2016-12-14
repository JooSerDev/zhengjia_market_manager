<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title>宝贝统计</title>
<jsp:include page="/common/common_css.jsp" />
<jsp:include page="/common/common_js.jsp" />
<style type="text/css">
.typeSelect {
	margin-top: 10px;
	line-height: 25px;
	height: 25px;
	text-align: center;
}

.typeSelect label {
	display: inline-block;
	height: 30px;
	border-radius: 5px;
	padding: 3px;
	margin: 2px; //
	background-color: #5EA4EC;
	width: auto;
}

.typeSelect .active {
	background-color: #5EA4EC;
}
</style>
</head>
<body>

	<div class="container">
		<div class="typeSelect">
			<label id="selectPv" class="active">点击率统计</label> | <label id="selectExg">交换成功转化率统计</label>
		</div>
		<hr>
	</div>
	<div class="panel panel-default" style="margin:20px;">
		<div class="panel-body">
			<form id="formSearch" class="form-horizontal">
				<div class="form-group" style="margin-top: 15px">
					<label class="control-label col-sm-1" for="itemType">宝贝分类</label>
					<div class="col-sm-2">
						<select id="itemType" class="form-control" name="itemType">
						</select>
					</div>
					<label class="control-label col-sm-1" for="day">时间(/天)</label>
					<div class="col-sm-2">
						<select id="day" class="form-control" name="day">
							<option value="1">1</option>
							<option value="7">7</option>
							<option value="30">30</option>
							<option value="60">60</option>
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
	<div id="itemsPvDiv" style="margin:20px;">
		<table id="itemsPv" class="table table-bordered table-striped"></table>
	</div>
	
	<input type="hidden" value="pvRate" id="rankType">
</body>
<script type="text/javascript">
	//模态对话框隐藏时移除数据  
	$(function() {
		parent.document.getElementById("content").height = 500;
		initItemsInfoTable();
		buttonInitFunc();
		itemTypeInit();
	});
	function initItemsInfoTable() {
		var columns = [ {
			field : 'itemId',
			align : 'center',
			valign : 'center',
			visible: false,
			title : '宝贝编号',
		}, {
			field : 'showCreateTime',
			align : 'center',
			valign : 'center',
			title : '发布时间',
			formatter : 'approvalTimeFmt'
		}, {
			field : 'ownerNickname',
			align : 'center',
			valign : 'center',
			title : '发布人',
		}, {
			field : 'name',
			align : 'center',
			valign : 'center',
			title : '宝贝名称',
		},{
			field : 'itemTypeName',
			align : 'center',
			valign : 'center',
			title : '宝贝分类',
		}, {
			field : 'exgStatus',
			align : 'center',
			valign : 'center',
			title : '宝贝交易状态',
			formatter: function(value,row,index){
				if(value == "exchanging"){
					return "交换中";
				}
				if(value == "exchanged"){
					return "交换完成";
				}
				if(value == "normal"){
					return "未交换";
				}
			}
		}, {
			field : 'itemPv',
			align : 'center',
			valign : 'center',
			title : '当前时间段PV',
		}, {
			field : 'exgFinishRate',
			align : 'center',
			valign : 'center',
			title : '当前时间段交换成功转化率',
		}];
		var url = globalPath + "/statistics/itemStatistics";
		var oTable = new TableInit("itemsPv", columns, null, url,
				queryParamsFunc, onclickRowFunc, true,null,"client");
		oTable.Init();
	}

	function queryParamsFunc(params) {
		var temp = { //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			/* limit : params.limit, //页面大小
			offset : params.offset, //页码 */
			itemType : $("#itemType").val(),
			rankType: $("#rankType").val(),
			day:$("#day").val()
		};
		return temp;
	}
	function onclickRowFunc(row) {
		if (row != null) {
			window.location.href = globalPath + "/item/itemDetail?itemId="
					+ row.itemId;
		}
	}
	function buttonInitFunc() {
		$("#btn_query").bind("click", function() {
			$('#itemsPv').bootstrapTable('refresh', {
				url : globalPath + '/statistics/itemStatistics'
			});
		});
		$("#selectPv").on("click",function(){
			$("#selectPv").addClass("active");
			$("#selectExg").removeClass("active");
			$("#rankType").val("pvRate");
			$("#btn_query").click();
		});
		$("#selectExg").on("click",function(){
			$("#selectPv").removeClass("active");
			$("#selectExg").addClass("active");
			$("#rankType").val("exgFinishRate");
			$("#btn_query").click();
		});
	}
	
	//初始化物品类别
	function itemTypeInit() {
		$("#itemType").empty();
		$("#itemType").append('<option value="-1">全部</option>');
		$.get(globalPath + '/item/itemTypes', function(data) {
			var obj = JSON.parse(data);
			var itemTypes = obj.resultList;
			$.each(itemTypes, function(index, value) {
				$("#itemType").append(
						'<option value="'+value.typeId+'">' + value.name
								+ '</option>');
			});
		});
	}
</script>
</html>