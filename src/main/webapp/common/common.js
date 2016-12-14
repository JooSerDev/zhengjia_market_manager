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
function dateFormatter(value) {
	if(value==null || value ==""){
		return "";
	}
	var time = new Date(value);
	var y = time.getFullYear();
	var m = time.getMonth() + 1;
	var d = time.getDate();
	return y + '-' + add0(m) + '-' + add0(d) ;
}

/**
 * 时间的加减
 * @param date
 * @param days
 * @returns {String}
 */
function addDate(date,days){
    var d=new Date(date);
    d.setDate(d.getDate()+days);
    var month=d.getMonth()+1;
    var day = d.getDate();
    if(month<10){
        month = "0"+month;
    }
    if(day<10){
        day = "0"+day;
    }
    var val = d.getFullYear()+""+month+""+day;
    return val;
}

/**
 * 状态转换 微信用户状态
 * @param value
 * @returns {String}
 */
function stateFormat(value){
	if(value==0){
		return "已注册";
	}
	if(value==1){
		return "非正常";
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
	return '<img src="'+url+'"  height="40px" width="40px"/>';
}
var ButtonInit = function(buttonInitFunc) {
	var oButtonInit = new Object();
	oButtonInit.Init = function(){
		return buttonInitFunc();
	};
	return oButtonInit;
};
/**
 * 表格初始化
 * param 1:tableId
 * param 2:columns
 * param 3:toolbarid
 * param 4:url
 * param 5:queryParamsFunc
 * param 6:onclickRowFunc
 * param 7:pagination
 * param 8:onBstLoadSuccess
 */
var TableInit = function(tableid,columns,toolbarid,url,queryParamsFunc,onclickRowFunc,pagination,onBstLoadSuccess,sidePagination) {
	if(sidePagination == null || sidePagination == "undefined"){
		sidePagination = "server";
	}
	var oTableInit = new Object();
	oTableInit.Init = function() {
		$("#"+tableid).bootstrapTable({
			url : url,
			method : 'get', //请求方式（*）
			striped : false, //是否显示行间隔色
			toolbar : '#'+toolbarid, //工具按钮用哪个容器
			cache : false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
			pagination : pagination, //是否显示分页（*）
			sortable : true, //是否启用排序
			sortOrder : "asc", //排序方式
			queryParams : oTableInit.queryParams,//传递参数（*）
			sidePagination : sidePagination, //分页方式：client客户端分页，server服务端分页（*）
			pageNumber : 1, //初始化加载第一页，默认第一页
			pageSize : 10, //每页的记录行数（*）
			pageList : [ 10, 25, 50, 100 ], //可供选择的每页的行数（*）
			strictSearch : true,
			showRefresh : false, //是否显示刷新按钮
			minimumCountColumns : 2, //最少允许的列数
			clickToSelect : true, //是否启用点击选中行
			detailView : false, //是否显示父子表
			queryParamsType : "limit", //参数格式,发送标准的RESTFul类型的参数请求
			silent : true, //刷新事件必须设置
			columns:columns,
			onLoadSuccess:function(data){
				/*if(data.length==1){
					console.log(data);
				}*/
				/*if(obj.retCode=="1001"){//表示用户未登陆
					window.top.location.href = "${pageContext.request.contextPath}/login.jsp"
				}*/
				
				if(onBstLoadSuccess!=null){
					onBstLoadSuccess(data);
				}
				var f = parent;
//					var grandf = f.parent;
				if(f.top!=self){
					var c = f.parent.document.getElementById("content");
					if(c!=null && c.hight<document.body.offsetHeight){
//						c.height=document.body.scrollHeight;
						c.height=document.body.offsetHeight;
					}
				}
				var pc = parent.document.getElementById("content");
				if(pc!=null){
//					pc.height = document.body.scrollHeight;
					pc.height = document.body.offsetHeight;
				}
				
			},
			responseHandler:function(data){
				if(sidePagination == "client"){
					return data.rows;
				}else{
					return data;
				}
			},
			formatShowingRows: function (pageFrom, pageTo, totalRows) {
		            return '展示第 '+pageFrom+' 条到  '+pageTo+' 条，总量： '+totalRows+' 条';
		    },
			onLoadError:function(data){
				console.log(data);
			},
			formatLoadingMessage : function() {
				return "请稍等，正在加载中...";
			},
			formatNoMatches : function() { //没有匹配的结果
				return '无符合条件的记录';
			},
			onClickRow : function(row) {
				if(onclickRowFunc == null){
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
function setAutoHeight(f){
	if(f.top!=self){
		setAutoHeight(f.parent);
	}
	f.parent.getElement("content").height = document.body.scrollHeight;
}

function showImg(url) {
	$("#showImgLarge").attr('src', url);
	$("#imgModal").modal("show");
}
/**
 * 有效，无效 翻译
 * @param value
 * @returns {String}
 */
function statusFmt(value){
	if(value==null){
		return "";
	}
	if(value==1){
		return "有效";
	}
	if(value==0){
		return "无效";
	}
}
/**
 * 男，女 翻译
 * @param value
 */
function sexFmt(value){
	if(value==null){
		return "";
	}
	if(value==1){
		return "男";
	}
	if(value==2){
		return "女";
	}
}
/**
 * 宝贝审批状态  已审核：通过   已审核：未通过 待审核
 * @param value
 */
function itemAprFmt(value){
	if(value==null){
		return "";
	}
	if(value=="yes"){
		return "已通过";
	}
	if(value=="no"){
		return "已拒绝";
	}
	if(value=="wait"){
		return "待审核";
	}
}
/**
 * 宝贝状态：  正常，已下架
 * @param value
 * @returns {String}
 */
function itemStateFmt(value){
	if(value==null){
		return "";
	}
	if(value==1 || value==2 || value==3){
		return "已下架";
	}
	if(value==0){
		return "上架";
	}
}
/**
 * 宝贝交易状态： 已完成，交易中，待交易
 */
function itemBuzFmt(value){
	if(value==null){
		return "";
	}
	if(value=="exchanged"){
		return "已完成";
	}
	if(value=="exchanging"){
		return "交换中";
	}
	if(value=="wait" || value=="normal"){
		return "待交换";
	}
	if(value=="cancel"){
		return "已取消";
	}
}
/**
 * 宝贝认证状态： 已认证：通过， 已认证：不通过，  待认证
 */
function itemFinishFmt(value){
	if(value==null){
		return "";
	}
	if(value=="wait"){
		return "待认证";
	}
	if(value=="yes"){
		return "认证成功交易";
	}
	if(value=="no"){
		return "认证失败交易";
	}
}

/**
 * 是否被小编推荐： 是，否
 * @param value
 * @returns {String}
 */
function itemRcmFmt(value){
	if(value==null){
		return "";
	}
	if(value==1){
		return "是";
	}
	if(value==0){
		return "否";
	}
}

function resizeHeight(){
	
}