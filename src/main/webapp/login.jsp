<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>
<jsp:include page="/common/common_css.jsp" />
<jsp:include page="/common/common_js.jsp" />
<style type="text/css">
body {
	background-color: #2B3E50;
}
</style>
</head>
<body>
	<div class="container" style="margin-top: 100px;">
		<div class="jumbotron center-block"
			style="width: 500px; margin-left: auto; margin-right: auto">
			<form action="${pageContext.request.contextPath}/fxsj/admin/login"
				method="post" class="form-horizontal" id="loginForm">
				<div class="form-group" id="headLabel">
					<label class="label label-primary col-sm-offset-5 col-sm-3"
						style="align: center">欢迎登录！</label>
				</div>
				<p id="loginPrompt">
					<font size="1" color="red">${prompt}</font>
				</p>
				<div class="form-group">
					<label for="loginId" class="col-sm-2 control-label">帐号</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="loginId"
							name="loginId" placeholder="请输入帐号" required>
					</div>
				</div>
				<div class="form-group">
					<label for="loginPass" class="col-sm-2 control-label">密码</label>
					<div class="col-sm-10">
						<input type="password" class="form-control" id="loginPass"
							name="loginPass" placeholder="请输入密码" required>
					</div>
				</div>
				<!-- <div class="form-group">
					<div class="col-sm-offset-3 col-sm-4 col-xs-4">
						<button onclick="submitForm()" class="btn btn-primary">确认登录</button>
					</div>
					<div class="col-sm-3">
						<button type="reset" class="btn btn-danger">清除</button>
					</div>
				</div> -->
				<div class="row col-sm-offset-1">
					<div class="col-md-6 col-sm-6">
						<button class="btn btn-primary btn-block"
							style="height: 40px;" onclick="submitForm()">确认登录</button>
					</div>
					<div class="col-md-6 col-sm-6">
						<button type="reset" class="btn btn-warning btn-block"
							style="height: 40px;">清除</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function() {
		/* var prompt = "${prompt}";
		if (prompt != null && prompt != "") {
			var doc = '<font color="red" size="1">*'+prompt + '</font>';
			$("#loginPrompt").html(doc);
		} */
	});
	function submitForm() {
		if ($("#loginForm").validate()) {
			$("#loginForm").submit();
		}
	}
</script>
</html>