<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文章详情</title>
<jsp:include page="/common/common_css.jsp" />
<jsp:include page="/common/common_js.jsp" />
<style>
body{
	scroll: auto;
}
</style>
</head>
<body>
<div style="margin:10px;width:100%;height:auto;">
	${article.content }
</div>
</body>

<script type="text/javascript">
$(function(){
	parent.document.getElementById("content").height = this.body.scrollHeight;
});
</script>
</html>