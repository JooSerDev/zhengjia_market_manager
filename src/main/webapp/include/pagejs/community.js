var CmtyComm = {
	//初始化选择社群分类
	initCmtySelect : function(selectId){
		$("#"+selectId).empty();
		$("#"+selectId).append('<option value="0">全部</option>');
		$.get(globalPath+"/community/getCmtyTypes",function(data){
			var obj = JSON.parse(data);
			var itemTypes = obj.resultList;
			$.each(itemTypes,function(index,value){
				$("#"+selectId).append('<option value="'+value.cmtyId+'">'+value.cmtyName+'</option>');
			});
		});
	},
	//初始化选择社群二级分类
	initCmtySubSelect : function(selectId,cmtyId){
		$("#"+selectId).empty();
		$("#"+selectId).append('<option value="0">全部</option>');
		$.get(globalPath+"/community/getCmtySubTypes?cmtyId="+cmtyId,function(data){
			var obj = JSON.parse(data);
			var itemTypes = obj.resultList;
			$.each(itemTypes,function(index,value){
				$("#"+selectId).append('<option value="'+value.typeId+'">'+value.typeName+'</option>');
			});
		});
	},
	initUE:function(ueId){
		var ue = UE.getEditor(ueId, {
			toolbars : [ [ 
			  'undo', //撤销
			  'redo', //重做
			  'bold', //加粗
			  'indent', //首行缩进
			  'italic', //斜体
			  'underline', //下划线
			  'pasteplain', //纯文本粘贴模式
			  'selectall', //全选
			  'preview', //预览
			  'time', //时间
			  'date', //日期
			  'cleardoc', //清空文档
			  'fontfamily', //字体
			  'fontsize', //字号
			  'paragraph', //段落格式
			  'simpleupload', //单图上传
			  'insertimage', //多图上传
			  'link', //超链接
			  'emotion', //表情
			  'spechars', //特殊字符
			  'justifyleft', //居左对齐
			  'justifyright', //居右对齐
			  'justifycenter', //居中对齐
			  'justifyjustify', //两端对齐
			  'forecolor', //字体颜色
			  'backcolor', //背景色
			  'insertorderedlist', //有序列表
			  'insertunorderedlist', //无序列表
			  'fullscreen', //全屏
			  'rowspacingtop', //段前距
			  'rowspacingbottom', //段后距
			  'imagenone', //默认
			  'imageleft', //左浮动
			  'imageright', //右浮动
			  'imagecenter', //居中
			  'lineheight', //行间距
              ] ],
              autoHeightEnabled : true,
              autoFloatEnabled : true
		});
		return ue;
	},
	article : undefined,
};

CmtyComm.article = {
	initPage:function(){
		CmtyComm.initUE("container");
		CmtyComm.initCmtySelect("cmtyId");
	},
};

/*
Cmty.common = {
	getCmtyTypes: function(callback){
		$.get(webPath+"/cmty/getCmtyTypes",function(data){
			var obj = JSON.parse(data);
			console.log(obj);
			if(callback instanceof Function){
				callback(obj.result);// 回调
			}else{
				console.log(callback+" ：传入的回调不是方法类型")
			}
		});
	},
	getNoteTypes: function(cmtyTypeId,callback){
		$.get(webPath+"/cmty/getCmtyTypes",function(data){
			var obj = JSON.parse(data);
			console.log(obj);
			if(callback instanceof Function){
				callback(obj.result);// 回调
			}else{
				console.log(callback+" ：传入的回调不是方法类型")
			}
		});
	},
}

Cmty.article = {
		initCmtyTypes:function(selectorId){
			Cmty.common.getCmtyTypes(function(types){
				$.each(types,function(index,value){
					var option = '<option value="'+value.cmtyId+'">'+value.cmtyName+'</option>';
					$("#"+selectorId).append(option);
				});
			});
		},
		writlePage:{
			initPage:function(){
				var that = this;
				Cmty.common.initUE("container");
				Cmty.article.initCmtyTypes("cmtyTypes");
				$("#saveBtn").on("click",function(){
					that.saveArticle();
				});
				$("#saveAndTopBtn").on("click",function(){
					that.saveAndHotArticle();
				});
				$("#saveAndHotBtn").on("click",function(){
					that.saveAndTopArticle();
				});
				$("#cmtyTypes").on("change",function(value){

				});
			},
			saveArticle:function(){
				if(!this.validateArticle()){
					return false;
				}
				var sendData = {
						articleTitle:$("#articleTitle").val(),
						content:$('[name="articleContent"]').val(),
						author:$("#authorName").val(),
						cmtyId:$("#cmtyTypes").val(),
						cmtyName:$("#cmtyTypes").text()
				}
				$.post(webPath+"/article/writeArticle",sendData,function(data){
					console.log(data);

				});
			},
			saveAndHotArticle:function(){
				if(!this.validateArticle()){
					return false;
				}
				alert("start to save and hot the content");
			},
			saveAndTopArticle:function(){
				if(!this.validateArticle()){
					return false;
				}
				alert("start to save and top the content");
			},
			validateArticle:function(){
				var articleTitle = $("#articleTitle").val();
				var authorName = $("#authorName").val();
				var cmtyType = $("#cmtyTypes").val();
//				var content = $("#container").val();
				var content = $('[name="articleContent"]').val();
				if(articleTitle==null || ""==articleTitle){
					alert("请输入文章标题！");
					return false;
				}
				if(cmtyType==null || ""==cmtyType){
					alert("请选择社群分类！");
					return false;
				}
				if(content == null || ""==content){
					alert("请输入文章正文内容！");
					return false;
				}
				return true;
			}
		}
}*/

String.prototype.trim = function(){
	return this.replace(/(^\s*)(\s*$)/g,"");
}
String.prototype.ltrim = function(){
	return this.replace(/(^\s*)/g,"");
}
String.prototype.rtrim = function(){
	return this.replace(/(^\s*$)/g,"");
}

String.prototype.endWith = function(str){
	if(str == null || str == "" || this.length == 0 
			 || str.length == 0 || str.length > this.length){
		return false;
	}
	if(this.substring(this.length-str.length) == str){
		return true;
	}else{
		return false;
	}
}
String.prototype.startWith = function(str){
	if(str == null || str == "" || this.length == 0 
			 || str.length == 0 || str.length > this.length){
		return false;
	}
	if(this.substr(0,str.length) == str){
		return true;
	}else{
		return false;
	}
}