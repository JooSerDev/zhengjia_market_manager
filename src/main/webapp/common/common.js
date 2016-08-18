/**
 * 公共使用类
 */

function add0(m){return m<10?'0'+m:m }
/**
 * 格式化时间
 * @param value
 * @returns {String}
 */
function dateTimeFormatter(value) {
	if(value==null || value ==""){
		return "";
	}
	var time = new Date(value);
	var y = time.getFullYear();
	var m = time.getMonth() + 1;
	var d = time.getDate();
	var h = time.getHours();
	var mm = time.getMinutes();
	var s = time.getSeconds();
	return y + '-' + add0(m) + '-' + add0(d) + ' ' + add0(h) + ':' + add0(mm)
			+ ':' + add0(s);
}
/**
 * 状态转换 微信用户状态
 * @param value
 * @returns {String}
 */
function stateFormat(value){
	if(value==0){
		return "未注册";
	}
	if(value==1){
		return "已注册";
	}
	if(value==2){
		return "已封号";
	}
}
function numFormat(value){
	if(value!=null){
		return value;
	}
}
/**
 * 图片地址转换为图片
 * @param url
 * @returns {String}
 */
function imgFormat(url){
	if(url == null || url == ""){
		return "";
	}
	return '<img src="'+url+'" height="40px" width="40px"/>';
}
var ButtonInit = function(buttonInitFunc) {
	var oButtonInit = new Object();
	oButtonInit.Init = function(){
		return buttonInitFunc();
	};
	return oButtonInit;
};
/**
 * 
 */
var TableInit = function(tableid,columns,toolbarid,url,queryParamsFunc,onclickRowFunc,pagination) {
	var oTableInit = new Object();
	oTableInit.Init = function() {
		$("#"+tableid).bootstrapTable({
			url : url,
			method : 'get', //请求方式（*）
			striped : true, //是否显示行间隔色
			toolbar : '#'+toolbarid, //工具按钮用哪个容器
			cache : false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
			pagination : pagination, //是否显示分页（*）
			sortable : false, //是否启用排序
			sortOrder : "asc", //排序方式
			queryParams : oTableInit.queryParams,//传递参数（*）
			sidePagination : "server", //分页方式：client客户端分页，server服务端分页（*）
			pageSize : 10, //每页的记录行数（*）
			pageList : [ 10, 25, 50, 100 ], //可供选择的每页的行数（*）
			strictSearch : true,
			showRefresh : true, //是否显示刷新按钮
			minimumCountColumns : 2, //最少允许的列数
			clickToSelect : false, //是否启用点击选中行
			detailView : false, //是否显示父子表
			queryParamsType : "limit", //参数格式,发送标准的RESTFul类型的参数请求
			silent : true, //刷新事件必须设置
			columns:columns,
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
				if(noclickRowFunc == null){
					return;
				}
				onclickRowFunc(row);
			}
		});
	};
	oTableInit.queryParams = function(params){
		if(queryParamsFunc == null){
			return;
		}
		return queryParamsFunc(params);
	};
	return oTableInit;
};