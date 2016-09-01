<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<jsp:include page="/common/common_css.jsp" />
<jsp:include page="/common/common_js.jsp" />
<style type="text/css">
body {
	background-color: #2B3E50;
	margin: 0px;
	padding: 0px;
	
}
</style>
</head>
<body>
	<div class="container" style="margin-top:100px;">
		<div class="jumbotron" style="align:center;width:500px;margin-left:auto;margin-right:auto">
			<form action="${pageContext.request.contextPath}/admin/chgSysUserInfo" 
					method="post" id="chgSysUserInfoForm" class="form-horizontal" >
				<p id="chgPrompt" align="center"><font size="1" color="red">${prompt}</font></p>
				<div class="form-group">
					<label for="loginId" class="col-sm-3 control-label">账号</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id="loginId" 
							name="loginId" value="${CurrentSysUser.loginid }" disabled="disabled">
					</div>
				</div>
				<div class="form-group">
					<label for="loginId" class="col-sm-3 control-label">昵称</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id="loginId" 
							name="loginId" value="${CurrentSysUser.loginname }" readonly="readonly">
					</div>
				</div>
				<div class="form-group">
					<label for="loginId" class="col-sm-3 control-label">原密码</label>
					<div class="col-sm-9">
						<input type="password" class="form-control" id="oldPass" 
							name="oldPass" placeholder="请输入原密码" required>
					</div>
				</div>
				<div class="form-group">
					<label for="loginPass" class="col-sm-3 control-label">新密码</label>
					<div class="col-sm-9">
						<input type="password" class="form-control" id="loginPass" 
							name="loginPass" placeholder="请输入新密码" required>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-4 col-sm-4 col-xs-4">
						<button type="submit" class="btn btn-primary ">提交</button>
					</div>
					<div class="col-sm-3 col-xs-4">
						<a href="${pageContext.request.contextPath}/welcome.jsp" class="btn btn-default">取消</a>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>