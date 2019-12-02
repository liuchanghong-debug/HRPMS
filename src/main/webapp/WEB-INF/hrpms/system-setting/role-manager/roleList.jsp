<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<base href="<%=basePath%>">
<!DOCTYPE html>
<!-- saved from url=(0057)http://localhost:8080/jeesite/a/company/system/systemRole -->
<html style="overflow-x:auto;overflow-y:auto;"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>角色信息管理 - Powered By JeeSite</title>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" /><meta name="author" content="http://jeesite.com/"/>
	<meta name="renderer" content="webkit"><meta http-equiv="X-UA-Compatible" content="IE=8,IE=9,IE=10" />
	<meta http-equiv="Expires" content="0"><meta http-equiv="Cache-Control" content="no-cache"><meta http-equiv="Cache-Control" content="no-store">
	<script src="js/static/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
	<link href="js/static/bootstrap/2.3.1/css_cerulean/bootstrap.min.css" type="text/css" rel="stylesheet" />
	<script src="js/static/bootstrap/2.3.1/js/bootstrap.min.js" type="text/javascript"></script>
	<link href="js/static/bootstrap/2.3.1/awesome/font-awesome.min.css" type="text/css" rel="stylesheet" />
	<!--[if lte IE 7]><link href="js/static/bootstrap/2.3.1/awesome/font-awesome-ie7.min.css" type="text/css" rel="stylesheet" /><![endif]-->
	<!--[if lte IE 6]><link href="js/static/bootstrap/bsie/css/bootstrap-ie6.min.css" type="text/css" rel="stylesheet" />
	<script src="js/static/bootstrap/bsie/js/bootstrap-ie.min.js" type="text/javascript"></script><![endif]-->
	<link href="js/static/jquery-select2/3.4/select2.min.css" rel="stylesheet" />
	<script src="js/static/jquery-select2/3.4/select2.min.js" type="text/javascript"></script>
	<link href="js/static/jquery-validation/1.11.0/jquery.validate.min.css" type="text/css" rel="stylesheet" />
	<script src="js/static/jquery-validation/1.11.0/jquery.validate.min.js" type="text/javascript"></script>
	<link href="js/static/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css" rel="stylesheet" />
	<script src="js/static/jquery-jbox/2.3/jquery.jBox-2.3.min.js" type="text/javascript"></script>
	<script src="js/static/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	<script src="js/static/common/mustache.min.js" type="text/javascript"></script>
	<link href="js/static/common/jeesite.css" type="text/css" rel="stylesheet" />
	<script src="js/static/common/jeesite.js" type="text/javascript"></script>
	<script type="text/javascript">var ctx = '../a', ctxStatic='js/static';</script>
	<!-- Baidu tongji analytics --><script>var _hmt=_hmt||[];(function(){var hm=document.createElement("script");hm.src="//hm.baidu.com/hm.js?82116c626a8d504a5c0675073362ef6f";var s=document.getElementsByTagName("script")[0];s.parentNode.insertBefore(hm,s);})();</script>


	<meta name="decorator" content="default">
	<script type="text/javascript">
        $(document).ready(function() {

        });
        function page(n,s){
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }
	</script>

</head>
<body>

<ul class="nav nav-tabs">
	<li class="active"><a href="/role-manager/selectSystemRoleByDuo">角色信息列表</a></li>
	<li><a href="role-manager/addSystemRoleJsp">角色信息添加</a></li>
</ul>
<form id="searchForm" class="breadcrumb form-search" action="/role-manager/selectSystemRoleByDuo" method="post">
	<ul class="ul-form">
		<li><label>角色名称：</label>
			<input id="rolename" name="roleName" class="input-medium" type="text" value="" maxlength="50">
		</li>
		<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"></li>
		<li class="clearfix"></li>
	</ul>
</form>


<script type="text/javascript">top.$.jBox.closeTip();</script>

<table id="contentTable" class="table table-striped table-bordered table-condensed">
	<thead>
	<tr>
		<th>角色ID</th>
		<th>角色名称</th>
		<th>排序</th>
		<th>状态</th>
		<th>备注</th>
		<th>操作</th>
	</tr>
	</thead>
	<tbody>

	<c:forEach items="${page.dataList}" var="role">
		<tr>
			<td><a href="../updateRole/saved_resource_unEdit.html">
				${role.id}
			</a></td>
			<td>
				${role.roleName}
			</td>
			<td>
				${role.sortNum}
			</td>
			<td>
				<c:if test="${role.status=='0'}" var="bo">
					正常
				</c:if>
				<c:if test="${!bo}">
					删除
				</c:if>
			</td>
			<td>
				${role.roleNote}
			</td>
			<td>
				<a href="../updateRole/saved_resource.html">修改</a>
				<a href="#" onclick="return confirmx(&#39;确认要删除该角色信息吗？&#39;, this.href)">删除</a>
			</td>
		</tr>
	</c:forEach>

	</tbody>
</table>
<div class="pagination"><ul>
	<li class="disabled"><a href="role-manager/selectSystemRoleByDuo?currentPage=${page.currentPage-1}&roleName=${map.roleName}">上一页</a></li>
	<c:forEach begin="1" end="${page.pageCount}" var="num">
		<li class="active"><a href="role-manager/selectSystemRoleByDuo?currentPage=${num}&roleName=${map.roleName}">${num}</a></li>
	</c:forEach>

	<li class="disabled"><a href="role-manager/selectSystemRoleByDuo?currentPage=${page.currentPage+1}&roleName=${map.roleName}">下一页</a></li>
	<li class="disabled controls"><a href="javascript:">当前
		<input type="text" value="${page.currentPage}" readonly> /
		<input type="text" value="${page.pageCount}" readonly> 页，共 ${page.pageCount} 页</a></li>
</ul>
	<div style="clear:both;"></div></div>

<script type="text/javascript">//<!-- 无框架时，左上角显示菜单图标按钮。
if(!(self.frameElement && self.frameElement.tagName=="IFRAME")){
    $("body").prepend("<i id=\"btnMenu\" class=\"icon-th-list\" style=\"cursor:pointer;float:right;margin:10px;\"></i><div id=\"menuContent\"></div>");
    $("#btnMenu").click(function(){
        top.$.jBox('get:/jeesite/a/sys/menu/treeselect;JSESSIONID=ff9a71c594b14065828ca71d9cf465d6', {title:'选择菜单', buttons:{'关闭':true}, width:300, height: 350, top:10});
        //if ($("#menuContent").html()==""){$.get("/jeesite/a/sys/menu/treeselect", function(data){$("#menuContent").html(data);});}else{$("#menuContent").toggle(100);}
    });
}//-->
</script>

</body></html>