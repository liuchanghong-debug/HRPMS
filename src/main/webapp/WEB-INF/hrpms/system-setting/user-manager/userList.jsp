<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<base href="<%=basePath%>">
<!-- saved from url=(0057)http://localhost:8080/jeesite/a/company/system/systemUser -->
<html style="overflow-x:auto;overflow-y:auto;"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>用户信息管理 - Powered By JeeSite</title>
	<meta name="renderer" content="webkit"><meta http-equiv="X-UA-Compatible" content="IE=8,IE=9,IE=10" />
	<meta http-equiv="Expires" content="0"><meta http-equiv="Cache-Control" content="no-cache"><meta http-equiv="Cache-Control" content="no-store">

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
	<script src="js/static/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
	<script type="text/javascript">

        $(function () {
            $.post(
                "datadict/selectByName",
                {"name":"用户状态"},
                function (json) {
                    var str="<option value='' selected>全部</option>";
                    for(var i=0;i<json.length;i++){
                        str +="<option value='"+json[i].value+"'>"+json[i].label+"</option>"
                    }
                    $("#status").html(str);
                },
                "json"
            );
        });

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
	<li class="active"><a href="user-manager/selectSystemUserByDuo">用户信息列表</a></li>
	<li><a href="user-manager/userAddJsp">用户信息添加</a></li>
</ul>
<form id="searchForm" class="breadcrumb form-search" action="user-manager/selectSystemUserByDuo" method="post">
	<input id="pageNo" name="pageNo" type="hidden" value="1">
	<input id="pageSize" name="pageSize" type="hidden" value="10">
	<ul class="ul-form">
		<li><label>用户名称：</label>
			<input id="username" name="username" class="input-medium" type="text" value="" maxlength="50">
		</li>
		<li><label>手机号码：</label>
			<input id="phone" name="phone" class="input-medium" type="text" value="" maxlength="13">
		</li>
		<li><label>状态：</label>
			<select id="status" name="status" class="input-medium">
			</select>
		</li>
		<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"></li>
		<li class="clearfix"></li>
	</ul>
</form>

<script type="text/javascript">top.$.jBox.closeTip();</script>

<table id="contentTable" class="table table-striped table-bordered table-condensed">
	<thead>
	<tr>
		<th>编号</th>
		<th>用户名称</th>
		<th>电子邮件</th>
		<th>手机号码</th>
		<th>排序</th>
		<th>状态</th>
		<th>操作</th>
	</tr>
	</thead>
	<tbody>

	<c:forEach items="${page.dataList}" var="user">
		<tr>
			<td><a href="user-manager/selectSystemUserById?id=${user.id}&flag=1">
				${user.id}
			</a></td>
			<td>
				${user.username}
			</td>
			<td>
				${user.email}
			</td>
			<td>
				${user.phone}
			</td>
			<td>
				${user.sortnum}
			</td>
			<td>
				<c:if test="${user.status==0}" var="bo">
					正常
				</c:if>
				<c:if test="${!bo}">
					删除
				</c:if>
			</td>
			<td>
				<a href="user-manager/selectSystemUserById?id=${user.id}&flag=2">修改</a>
				<a href="user-manager/deleteSystemUserById?id=${user.id}" onclick="return confirmx(&#39;确认要删除该用户信息吗？&#39;, this.href)">删除</a>
			</td>
		</tr>
	</c:forEach>

	</tbody>
</table>
<div class="pagination"><ul>
	<li class="disabled"><a href="user-manager/selectSystemUserByDuo?currentPage=${page.currentPage-1}&username=${map.username}&phone=${map.phone}&status=${map.status}">上一页</a></li>
	<c:forEach begin="1" end="${page.pageCount}" var="num">
		<li class="active"><a href="user-manager/selectSystemUserByDuo?currentPage=${num}&username=${map.username}&phone=${map.phone}&status=${map.status}">${num}</a></li>
	</c:forEach>

	<li class="disabled"><a href="user-manager/selectSystemUserByDuo?currentPage=${page.currentPage+1}&username=${map.username}&phone=${map.phone}&status=${map.status}">下一页</a></li>
	<li class="disabled controls"><a href="javascript:">当前
		<input type="text" value="${page.currentPage}" readonly> /
		<input type="text" value="${page.pageCount}" readonly> 条，共 ${page.pageCount} 页</a></li>
</ul>
	<div style="clear:both;"></div></div>

<script type="text/javascript">//<!-- 无框架时，左上角显示菜单图标按钮。
if(!(self.frameElement && self.frameElement.tagName=="IFRAME")){
    $("body").prepend("<i id=\"btnMenu\" class=\"icon-th-list\" style=\"cursor:pointer;float:right;margin:10px;\"></i><div id=\"menuContent\"></div>");
    $("#btnMenu").click(function(){
        top.$.jBox('get:/jeesite/a/sys/menu/treeselect;JSESSIONID=29c6dc47ff794aa086b874d4089adcb7', {title:'选择菜单', buttons:{'关闭':true}, width:300, height: 350, top:10});
        //if ($("#menuContent").html()==""){$.get("/jeesite/a/sys/menu/treeselect", function(data){$("#menuContent").html(data);});}else{$("#menuContent").toggle(100);}
    });
}//-->
</script>

</body></html>