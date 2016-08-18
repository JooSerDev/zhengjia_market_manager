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
	margin: 0px;
	padding: 0px;
	
}
</style>
</head>
<body>
	<div class="container" style="margin-top:100px;">
		<div class="jumbotron" style="align:center;width:40%;position: static;">
			<form action="${pageContext.request.contextPath}/admin/homeWithoutLogin" 
					method="post" class="form-horizontal" >
				<div class="form-group" id="headLabel" >
					<label class="label label-primary col-sm-offset-5 col-sm-2" style="align:center">欢迎登陆！</label>
				</div>
				<div class="form-group">
					<label for="loginId" class="col-sm-2 control-label">账号</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="loginId" 
							name="loginId" placeholder="请输入账号">
					</div>
				</div>
				<div class="form-group">
					<label for="loginPass" class="col-sm-2 control-label">密码</label>
					<div class="col-sm-10">
						<input type="password" class="form-control" id="loginPass" 
							name="loginPass" placeholder="请输入密码">
					</div>
				</div>
				<!-- <div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<div class="checkbox">
							<label> <input type="checkbox"> 请记住我
							</label>
						</div>
					</div>
				</div> -->
				<div class="form-group">
					<div class="col-sm-offset-3 col-sm-2">
						<button type="submit" class="btn btn-primary">登录</button>
					</div>
					<div class="col-sm-offset-3 col-sm-2">
						<button type="reset" class="btn btn-danger">重置</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function(){
		var prompt = "${prompt}";
		if(prompt!=null && prompt!=""){
			var doc = '<div class="alert alert-warning col-sm-offset-4 col-sm-3" style="width:200px;height:50px; ">'
				+'<a href="#" class="close" data-dismiss="alert">'
			   +'&times'
			   +'</a>'
			   +prompt+'</div>';
			$("#headLabel").append(doc);
			
		}
	});
</script>
</html>