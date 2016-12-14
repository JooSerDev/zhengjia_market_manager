<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
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
	/* 	background: #000; */
	font-weight: bold;
}

.highLight {
	background-color: #32A5F8;
	font-weight: bold;
}
</style>
</head>
<body>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<%-- 			href="${pageContext.request.contextPath}/welcome.jsp" --%>
				<span class="navbar-brand" onclick="javascript:void(0)">正佳广场共享市集</span>
			</div>
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav" style="margin-left: 40px;" id="mainUl">
					<li><a
						href="${pageContext.request.contextPath}/item/itemManage?i=1"
						target="content">审核管理</a></li>
					<li><a href="${pageContext.request.contextPath}/exg/exgManage"
						target="content">交易管理</a></li>
					<!-- userManage -->
					<li><a href="${pageContext.request.contextPath}/wx/userManage"
						target="content">用户管理</a></li>
					<li><a
						href="${pageContext.request.contextPath}/community/cmtyManage"
						target="content">社群管理</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">活动管理 <b class="caret"></b>
					</a>
						<ul class="dropdown-menu">
							<li><a
								href="${pageContext.request.contextPath}/activity/actyManage"
								target="content">活动管理</a></li>
							<li class="divider"></li>
							<li><a
								href="${pageContext.request.contextPath}/activity/enterManage"
								target="content">报名管理</a></li>
						</ul></li>
					<li><a
						href="${pageContext.request.contextPath}/community/articleManage"
						target="content">文章管理</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">系统管理 <b class="caret"></b>
					</a>
						<ul class="dropdown-menu">
							<li><a target="content"
								href="${pageContext.request.contextPath}/admin/sysUserManage">管理员管理</a></li>
							<li class="divider"></li>
							<li><a target="content"
								href="${pageContext.request.contextPath}/admin/sysConfParamInit">字典管理</a></li>
							<li class="divider"></li>
							<li><a target="content"
								href="${pageContext.request.contextPath}/admin/toChgSysUserInfo">修改密码</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">外部平台管理<b class="caret"></b>
					</a>
						<ul class="dropdown-menu">
							<li><a target="content"
								href="${pageContext.request.contextPath}/itfToken/toItfToken">token生成</a></li>
							<li class="divider"></li>
							<li><a target="content"
								href="${pageContext.request.contextPath}/itfToken/toItfTokenLog">访问日志</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">数据统计<b class="caret"></b>
					</a>
						<ul class="dropdown-menu">
							<li><a target="content"
								href="${pageContext.request.contextPath}/statistics/user">用户统计</a></li>
							<li class="divider"></li>
							<li><a target="content"
								href="${pageContext.request.contextPath}/statistics/item">宝贝统计</a></li>
							<li class="divider"></li>
							<li><a target="content"
								href="${pageContext.request.contextPath}/statistics/visit">访问统计</a></li>
						</ul></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a data-toggle="modal" data-target="#loginOutModal">登出</a></li>
				</ul>
			</div>
		</div>
	</div>

	<div
		style="display: none; position: fixed; margin: 20px; top: 30px; height: 30px; width: 100px; background-color: black; line-height: 30px; text-align: center; border-radius: 5px; z-index: 999">
		<a style="color: white;">返回</a>
	</div>

	<div style="margin: 10px; margin-top: 70px; margin-bottom: 0px;">
		<iframe id="content" name="content" scrolling="no" frameborder="0"
			width="100%" height="auto;"
			src="${pageContext.request.contextPath}/item/itemManage"></iframe>
	</div>
	<div class="modal fade" id="loginOutModal" aria-hidden="true">
		<div class="modal-dialog" style="width: 400px; margin-top: 200px;">
			<div class="modal-content">
				<div class="modal-body" id="loginOutOptions">确认登出帐号？</div>
				<div class="modal-footer">
					<button class="btn btn-default" onclick="loginOut()">确定</button>
					<button class="btn btn-primary" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function() {
		$("#mainUl li").on("click", function() {
			$("#mainUl li").removeClass("highLight");
			$(this).addClass("highLight");
		});
	});
	function loginOut() {
		window.location.href = "${pageContext.request.contextPath}/admin/loginout";
	}
</script>
</html>