<script src="${pageContext.request.contextPath}/bootstrap/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap-table.js"></script>
<script src="${pageContext.request.contextPath}/common/common.js"></script>
<!--[if lte IE 9]>
<script src="${pageContext.request.contextPath}/common/html5.js"></script>
<script src="${pageContext.request.contextPath}/common/respond.js"></script>
<![endif]-->

<%
response.setHeader("Pragma","No-cache");    
response.setHeader("Cache-Control","no-cache");    
response.setDateHeader("Expires", -10);   
%>