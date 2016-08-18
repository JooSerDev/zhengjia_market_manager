<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<jsp:include page="/common/common_css.jsp" />
<jsp:include page="/common/common_js.jsp" />
<style type="text/css">
body {
	background-color: #2B3E50;
	margin: 0px;
	padding: 0px;
}

.navbar li:hover {
	background: #000;
	font-weight: bold;
}
</style>
</head>
<body>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">正佳广场共享集市</a>
			</div>
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav" style="margin-left: 40px;">
					<li><a href="${pageContext.request.contextPath}/item/itemManage"
						target="content">审核管理</a></li>
					<li><a href="${pageContext.request.contextPath}/exchange/exchangeManage"
						target="content">交易管理</a></li>
						<!-- userManage -->
					<li><a href="${pageContext.request.contextPath}/wx/userManage"
						target="content">用户管理</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> 系统管理 <b class="caret"></b>
					</a>
						<ul class="dropdown-menu">
							<li><a target="content" href="${pageContext.request.contextPath}/admin/userManage">管理员管理</a></li>
							<li class="divider"></li>
							<li><a target="content" href="${pageContext.request.contextPath}/admin/sysConfParamInit">字典管理</a></li>
							<li class="divider"></li>
							<li><a href="#" click="changePassWordModal()">修改密码</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
	</div>
	<div style="margin: 100px;margin-bottom: 0px;">
		<iframe id="content" name="content" scrolling="no" frameborder="0"
			width="100%" height="500px;"></iframe>
	</div>
</body>
<script type="text/javascript">
	function changePassWordModal() {
		
	}
</script>
</html>