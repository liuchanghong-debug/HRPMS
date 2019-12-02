<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<base href="<%=basePath%>">
<!DOCTYPE html>
<!-- saved from url=(0060)http://localhost:8080/jeesite/a/company/socialInsuranceCount -->
<html style="overflow-x:auto;overflow-y:auto;"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>社保费用统计 - Powered By JeeSite</title>
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
	<li class="active"><a href="statement/shebaoCount">社保费用列表</a></li>

</ul>
<form id="searchForm" class="breadcrumb form-search" action="" method="post" name="operation">
	<ul class="ul-form">
		<li><label>客户名称：</label>
			<input type="text" name="nameQuery" value="${shebaoCountOperation.nameQuery}" htmlescape="false" maxlength="50" class="input-medium" style="width:150px">
		</li>
		<li><label>身份证号：</label>
			<input type="text" name="idCardQuery" value="${shebaoCountOperation.idCardQuery}" htmlescape="false" maxlength="20" class="input-medium" style="width:150px">
		</li>
		<li><label>社保卡号：</label>
			<input type="text" name="sbCardQuery" value="${shebaoCountOperation.sbCardQuery}" htmlescape="false" maxlength="50" class="input-medium" style="width:100px">
		</li>
		<li><label>所属公司：</label>
			<select name="companyIdQuery" style="width:150px" tabindex="-1" class="select2-offscreen">
				<option value="">查询所有公司</option>
				<c:forEach items="${companys}" var="company">
					<c:if test="${company[0] == shebaoCountOperation.companyIdQuery}">
						<option value="${company[0]}" selected>${company[1]}</option>
					</c:if>
					<c:if test="${company[0] != shebaoCountOperation.companyIdQuery}">
						<option value="${company[0]}">${company[1]}</option>
					</c:if>
				</c:forEach>
			</select>
		</li>
		<li class="btns"><input class="btn btn-primary" type="button" onclick="operation.action='statement/shebaoCount'; operation.submit()" value="统计"></li>
		<li class="btns"><input class="btn btn-primary" type="button" onclick="operation.action='statement/shebaoCountOut'; operation.submit()" value="导出"></li>
		<li class="clearfix"></li>
	</ul>
</form>

<script type="text/javascript">top.$.jBox.closeTip();</script>

<table id="contentTable" class="table table-striped table-bordered table-condensed">
	<thead>
	<tr>
		<th>客户名称</th>
		<th>身份证号</th>
		<th>社保号码</th>
		<th>所属公司</th>
		<th>社保月数</th>
		<th>社保总额</th>
		<th>费用总额</th>
		<th>状态</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${data}" var="shebao">
		<tr>
			<td>${shebao.name}</td>
			<td>${shebao.idCard}</td>
			<td>${shebao.sbCard}</td>
			<td>${shebao.companyName}</td>
			<td>${shebao.sbMonth}</td>
			<td>${shebao.sbMoneyCount}</td>
			<td>${shebao.cost}</td>
			<td>${shebao.status}</td>
		</tr>
	</c:forEach>

	</tbody>
</table>
<div class="pagination"></div>

<script type="text/javascript">//<!-- 无框架时，左上角显示菜单图标按钮。
if(!(self.frameElement && self.frameElement.tagName=="IFRAME")){
    $("body").prepend("<i id=\"btnMenu\" class=\"icon-th-list\" style=\"cursor:pointer;float:right;margin:10px;\"></i><div id=\"menuContent\"></div>");
    $("#btnMenu").click(function(){
        top.$.jBox('get:/jeesite/a/sys/menu/treeselect;JSESSIONID=3baf732ded45486a8f110d13601a4c9b', {title:'选择菜单', buttons:{'关闭':true}, width:300, height: 350, top:10});
        //if ($("#menuContent").html()==""){$.get("/jeesite/a/sys/menu/treeselect", function(data){$("#menuContent").html(data);});}else{$("#menuContent").toggle(100);}
    });
}//-->
</script>

</body></html>