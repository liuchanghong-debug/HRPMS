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
<!-- saved from url=(0052)http://localhost:8080/jeesite/a/company/customerInfo -->
<html style="overflow-x:auto;overflow-y:auto;"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>个人客户管理 - Powered By JeeSite</title>
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
        function openSelectFile() {
			$("#file").click();
        }
        function subFile() {
			$("#sub").click();
        }
	</script>

</head>
<body>

<ul class="nav nav-tabs">
	<li class="active"><a href="customerClient/customerList">个人客户列表</a></li>
	<li><a href="customerClient/customerAdd">个人客户添加</a></li>
</ul>
<form id="searchForm" class="breadcrumb form-search" action="customerClient/customerList" method="post">
	<input id="pageNo" name="pageNo" type="hidden" value="1">
	<input id="pageSize" name="pageSize" type="hidden" value="10">
	<ul class="ul-form">
		<li><label>客户名称：</label>
			<input name="nameQuery" class="input-medium" type="text" value="${customerOperation.nameQuery}" maxlength="50">
		</li>
		<li><label>身份证号：</label>
			<input name="idCardQuery" class="input-medium" type="text" value="${customerOperation.idCardQuery}" maxlength="20">
		</li>
		<li><label>所属公司：</label>
			<select name="companyIdQuery" class="input-medium select2-offscreen" tabindex="-1">
				<option value="">全部</option>
				<c:forEach items="${companys}" var="company">
					<c:if test="${customerOperation.companyIdQuery == company.id}">
						<option value="${company.id}" selected="selected">${company.name}</option>
					</c:if>
					<c:if test="${customerOperation.companyIdQuery != company.id}">
						<option value="${company.id}">${company.name}</option>
					</c:if>
				</c:forEach>
			</select>
</form>

		</li>
		<li class="btns"><input class="btn btn-primary" type="submit" value="查询"></li>
		<li class="btns"><input class="btn btn-primary" type="button" onclick="location.href='customerClient/customerTemplateDownload?name=个人客户模板'" value="模板下载"></li>
<form action="customerClient/customerUploadOfExcel" method="post" enctype="multipart/form-data">
		<li class="btns"><input class="btn btn-primary" type="button" onclick="openSelectFile()" value="导入"></li>
		<input type="file" id="file" name="file" style="display: none" onchange="subFile()" accept="application/Excel,application/vnd.ms-excel">
		<input type="submit" id="sub" style="display: none">

		<li class="btns"><input class="btn btn-primary" type="button" value="导出" onclick="location.href='customerClient/customerDataOutOfExcel?nameQuery=' + $('#nameQuery').val() + '&idCardQuery=' + $('#idCardQuery').val() + '&companyIdQuery=' + $('#companyIdQuery').val()"></li>
		<li class="clearfix"></li>
	</ul>
</form>


<script type="text/javascript">top.$.jBox.closeTip();</script>
<form action="" method="post" name="paging">
	<input type="hidden" id="nameQuery" name="nameQuery" value="${customerOperation.nameQuery}">
	<input type="hidden" id="idCardQuery" name="idCardQuery" value="${customerOperation.idCardQuery}">
	<input type="hidden" id="companyIdQuery" name="companyIdQuery" value="${customerOperation.companyIdQuery}">


	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
		<tr>
			<th>客户名称</th>
			<th>身份证号</th>
			<th>客户性别</th>
			<th>出生年月</th>
			<th>手机号码</th>
			<th>电子邮件</th>
			<th>毕业学校</th>
			<th>所学专业</th>
			<th>毕业时间</th>
			<th>工资薪酬</th>
			<th>社保信息</th>
			<th>公积金</th>
			<th>操作</th>
		</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.dataList}" var="customer">
			<tr>
				<td><a href="customerClient/customerMess?id=${customer.id}">${customer.name}</a></td>
				<td>${customer.idCard}</td>
				<td>${customer.sex}</td>
				<td>${customer.birthday}</td>
				<td>${customer.phone}</td>
				<td>${customer.email}</td>
				<td>${customer.school}</td>
				<td>${customer.specialty}</td>
				<td>${customer.graduation}</td>
				<td><a href="salary-manager/selectSalaryByDuo?flag=1&idCard=${customer.idCard}">薪酬工资</a></td>
				<td><a href="#">社保信息</a></td>
				<td>公积金</td>
				<td>
					<a href="javascript:void(0)" onclick="paging.action='customerClient/customerUpdate?currentPage=${page.currentPage}&id=${customer.id}'; paging.submit()">修改</a>
					<a href="customerClient/customerDelete?currentPage=${page.currentPage}&id=${customer.id}&nameQuery=${customerOperation.nameQuery}&idCardQuery=${customerOperation.idCardQuery}&companyIdQuery=${customerOperation.companyIdQuery}" onclick="return confirmx(&#39;确认要删除该个人客户吗？&#39;, this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>

	<div class="pagination">
		<ul>
			<li class="disabled"><a href="javascript:void(0)" onclick="paging.action='customerClient/customerList?currentPage=${page.currentPage - 1}'; paging.submit()">« 上一页</a></li>
			<li class="active"><a href="javascript:void(0)">${page.currentPage}</a></li>
			<li class="disabled"><a href="javascript:void(0)" onclick="paging.action='customerClient/customerList?currentPage=${page.currentPage + 1}'; paging.submit()">下一页 »</a></li>
			<li class="disabled controls"><a href="javascript:">当前
				<input type="text" value="${page.currentPage}" onkeypress="var e=window.event||event;var c=e.keyCode||e.which;if(c==13)page(this.value,10,&#39;&#39;);" onclick="this.select();">
				/ <input type="text" value="${page.pageSize}" onkeypress="var e=window.event||event;var c=e.keyCode||e.which;if(c==13)page(1,this.value,&#39;&#39;);" onclick="this.select();">
				页，共 ${page.count} 条</a></li>
		</ul>
	<div style="clear:both;"></div></div>
</form>

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