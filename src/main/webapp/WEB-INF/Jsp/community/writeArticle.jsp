<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<style type="text/css">
html {
	overflow-x: hidden;
}
</style>
<title>宝贝审核</title>
<jsp:include page="/common/common_css.jsp" />
<jsp:include page="/common/common_js.jsp" />
<style type="text/css">
body {
	margin: 0px;
	padding-top: 0px;
	border-radius: 0;
	overflow: scroll;
	overflow-x: hidden;
	margin-bottom: 10px;
	margin-top: 10px;
}
</style>
</head>
<body>
	<div id="writeArticlePage">
		<form class="form-horizontal" style="padding-top: 20px;">
			<div class="form-group">
				<label class="control-label col-sm-1" for="articleTitle">文章标题</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="articleTitle"
						id="articleTitle">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1" for="authorName">作者名称</label>
				<div class="col-sm-3">
					<input type="text" class="form-control" name="authorName"
						id="authorName" placeholder="默认为登陆用户的名字">
				</div>
				<label class="control-label col-sm-1" for="cmtyId">社群分类</label>
				<div class="col-sm-3">
					<select id="cmtyId" class="form-control" name="cmtyId">
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1" for="articleContent">文章正文</label>
				<div class="col-sm-3">
					<a class="btn btn-primary" id="saveBtn">保存</a>
				</div>
				<div class="col-sm-3">
					<a class="btn btn-primary" id="saveAndTopBtn">保存并置顶</a>
				</div>
				<div class="col-sm-3">
					<a class="btn btn-primary" id="saveAndHotBtn">保存并加精</a>
				</div>
			</div>
			<div id="contentDiv"
				style="margin-left: 20px; margin-right: 20px; margin-bottom: 20px;">
				<textarea style="width: 100%; min-height: 400px;"
					name="articleContent" id="container"></textarea>
			</div>
		</form>
	</div>

</body>
<script
	src="${pageContext.request.contextPath}/ueditor/ueditor.config.js"></script>
<script src="${pageContext.request.contextPath}/ueditor/ueditor.all.js"></script>
<script
	src="${pageContext.request.contextPath}/include/pagejs/community.js"></script>
<!-- 实例化编辑器 -->
<script type="text/javascript">
	$(function() {
		btnInit();
		window.UEDITOR_HOME_URL = "${pageContext.request.contextPath}/";
		CmtyComm.article.initPage();
		$("#container").css("max-height","500px");
		var screenHeight = top.screen.availHeight;
		parent.document.getElementById("content").height = screenHeight - 100;
	});

	function btnInit(){
		$("#saveBtn").on("click",function(){
			if(validateArticle()){
				var sendData = {
						articleTitle:$("#articleTitle").val(),
						content:$('[name="articleContent"]').val(),
						author:$("#authorName").val(),
						hotFlag:'no',
						TopFlag:'no',
						status:'waiting',
						cmtyId:$("#cmtyId").val(),
						cmtyName:$("#cmtyId").find("option:selected").text()
					};
				$.ajax({
					url:globalPath+"/community/saveArticle",
					data:sendData,
					success:function(data){
						alert("添加成功");
						location.href=globalPath+"/community/articleManage";
					},
					error:function(data){
						alert(data.retMsg);
					}
				});
			}
		});
	}
	
	function validateArticle(){
		var articleTitle = $("#articleTitle").val();
		var authorName = $("#authorName").val();
		var cmtyType = $("#cmtyId").val();
//		var content = $("#container").val();
		var content = $('[name="articleContent"]').val();
		if(articleTitle==null || ""==articleTitle){
			alert("请输入文章标题！");
			return false;
		}
		if(cmtyType==null || ""==cmtyType || cmtyType == 0){
			alert("请选择社群分类！");
			return false;
		}
		if(content == null || ""==content){
			alert("请输入文章正文内容！");
			return false;
		}
		return true;
	}
</script>
</html>