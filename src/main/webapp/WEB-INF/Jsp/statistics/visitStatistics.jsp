<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>访问统计</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/include/pagejs/echarts.js"></script>
<jsp:include page="/common/common_css.jsp" />
<jsp:include page="/common/common_js.jsp" />
<style type="text/css">
.container {
	width: 100%;
}

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
			<label id="selectPv" class="active">平台 PV 统计曲线图</label> | <label id="selectUv">平台 UV 统计曲线图</label>
		</div>
		<hr>
		<div id="statisticsCharts" style="width: 100%; height: 400px;"></div>
	</div>

</body>
<script type="text/javascript">
	var xAxis = {
		data : [],
		type : 'category',
		boundaryGap : false,
		show : true,
		axisLabel : {
			interval : 0, //0:表示全部显示不间隔， auto表示自动根据可读个数和宽度自动设置间隔个数,n 自己设置间隔值
			rotate : 45,
			textStyle : {
				color : "#222",
			}
		}
	};
	var series = [ {
		name : '数量',
		type : 'line',
		smooth : true,
		data : [],
	} ];

	$(function() {
		parent.document.getElementById("content").height = "500px";
		initPVCharts();
		$("#selectPv").on("click",initPVCharts);
		$("#selectUv").on("click",initUVCharts);
	});

	function initPVCharts() {
		$("#selectPv").addClass("active");
		$("#selectUv").removeClass("active");
		initCharts("pv");
	}
	function initUVCharts() {
		$("#selectPv").removeClass("active");
		$("#selectUv").addClass("active");
		initCharts("uv");
	}
	
	function initCharts(type){
		var newCharts = echarts.init(document.getElementById("statisticsCharts"));
		newCharts.showLoading();
		//清空xAxis.data
		xAxis.data.splice(0,xAxis.data.length);
		var now = new Date();
		for (var i = 30; i >= 1; i--) {
			xAxis.data.push(addDate(now, -i));
		}
		var url = globalPath + "/statistics/visitStatistics?type="+type;
		$.get(url,function(data){
			newCharts.hideLoading();
			var obj = JSON.parse(data);
			series[0].data = obj.result;
			var date = new Date();
			var opTitle = addDate(date,-30) + "  ~  " + addDate(date,-1) + " 平台 "+type+" 统计曲线图"
			var option = {
					title : {
						text : opTitle,
					},
					tooltip : {},
					xAxis : xAxis,
					yAxis : {},
					series : series,
				};
			newCharts.setOption(option);
		});
	}

	function addDate(date, days) {
		var d = new Date(date);
		d.setDate(d.getDate() + days);
		var month = d.getMonth() + 1;
		var day = d.getDate();
		if (month < 10) {
			month = "0" + month;
		}
		if (day < 10) {
			day = "0" + day;
		}
		return d.getFullYear() + "" + month + "" + day;
	}

	function resizeDom() {
		parent.document.getElementById("content").offsetHeight = this.document.body.scrollHeight;
	}
</script>
</html>