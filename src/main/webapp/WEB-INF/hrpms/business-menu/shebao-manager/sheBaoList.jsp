<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<base href="<%=basePath%>">
<!DOCTYPE html>
<!-- saved from url=(0055)http://localhost:8080/jeesite/a/company/socialInsurance -->
<html style="overflow-x:auto;overflow-y:auto;"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>社保信息管理 - Powered By JeeSite</title>
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
	<li class="active"><a href="shebao/shebaoList">社保信息列表</a></li>
	<li><a href="shebao/shebaoToAdd">社保信息添加</a></li>
</ul>
<form id="searchForm" class="breadcrumb form-search" action="shebao/shebaoList" method="post">
	<ul class="ul-form">
		<li><label>客户名称：</label>
			<input type="text" name="nameQuery" maxlength="20" value="${socialInsuranceOperation.nameQuery}" class="input-medium">
		</li>
		<li><label>身份证号：</label>
			<input name="idCardQuery" class="input-medium" type="text" value="${socialInsuranceOperation.idCardQuery}" maxlength="20">
		</li>
		<li><label>社保卡号：</label>
			<input name="sbCatdQuery" class="input-medium" type="text" value="${socialInsuranceOperation.sbCardQuery}" maxlength="20">
		</li>
		<li class="btns"><input class="btn btn-primary" type="submit" value="查询"></li>
</form>
		<li class="btns"><input class="btn btn-primary" type="button" onclick="location.href='shebao/shebaoTemplateDownload?name=社保信息模板'" value="模板下载"></li>
		<li class="btns">
			<form action="shebao/shebaoInOfExcel" method="post" enctype="multipart/form-data">
				<input id="btnSubmit" class="btn btn-primary" type="button" onclick="$('#file').click()" value="导入">
				<input type="file" style="display: none" id="file" name="file" onchange="$('#sub').click()" accept="application/Excel,application/vnd.ms-excel">
				<input type="submit" style="display: none" id="sub">
			</form>
		</li>
		<li class="btns"><input class="btn btn-primary" type="button" onclick="location.href='shebao/dataOutOfExcel?nameQuery=${socialInsuranceOperation.nameQuery}&idCardQuery=${socialInsuranceOperation.idCardQuery}&sbCardQuery=${socialInsuranceOperation.sbCardQuery}'" value="导出"></li>
		<li class="clearfix"></li>
	</ul>


<script type="text/javascript">top.$.jBox.closeTip();</script>
<form action="" method="post" name="paging">
	<input type="hidden" name="nameQuery" value="${socialInsuranceOperation.nameQuery}">
	<input type="hidden" name="idCardQuery" value="${socialInsuranceOperation.idCardQuery}">
	<input type="hidden" name="sbCardQuery" value="${socialInsuranceOperation.sbCardQuery}">


	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
		<tr>
			<th>客户名称</th>
			<th>身份证号</th>
			<th>社保卡号</th>
			<th>缴费基数</th>
			<th>应缴金额</th>
			<th>个人比率</th>
			<th>单位比率</th>
			<th>养老保险</th>
			<th>医疗保险</th>
			<th>工伤保险</th>
			<th>失业保险</th>
			<th>生育保险</th>
			<th>预交款日</th>
			<th>代理费用</th>
			<th>状态</th>
			<th>操作</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.dataList}" var="socialInsurance">
			<tr>
				<td><a href="shebao/shebaoShow?id=${socialInsurance.id}">${socialInsurance.name}</a></td>
				<td>${socialInsurance.idCard}</td>
				<td>${socialInsurance.sbCard}</td>
				<td>${socialInsurance.basePay}</td>
				<td>${socialInsurance.mustPay}</td>
				<td>${socialInsurance.personRatio}</td>
				<td>${socialInsurance.companyRatio}</td>
				<td>${socialInsurance.yangLao}</td>
				<td>${socialInsurance.yiLiao}</td>
				<td>${socialInsurance.gongShang}</td>
				<td>${socialInsurance.shiYe}</td>
				<td>${socialInsurance.shengYu}</td>
				<td>
					<f:formatDate value="${socialInsurance.payDate}" var="time" pattern="yyyy-MM-dd"/>
						${time}
				</td>
				<td>${socialInsurance.proxyFee}</td>
				<td>
					<c:forEach items="${status}" var="statu">
						<c:if test="${statu.value == socialInsurance.status}">
							<span>${statu.label}</span>
						</c:if>
					</c:forEach>
				</td>
				<td>
					<c:if test="${socialInsurance.status == '1'}">
						<a href="shebao/shebaoRecordToAdd?id=${socialInsurance.id}">缴费</a>
					</c:if>

					<a href="shebao/shebaoToUpdate?id=${socialInsurance.id}&currentPage=${page.currentPage}&nameQuery=${socialInsuranceOperation.nameQuery}&idCardQuery=${socialInsuranceOperation.idCardQuery}&sbCardQuery=${socialInsuranceOperation.sbCardQuery}">修改</a>
					<a href="shebao/shebaoDelete?id=${socialInsurance.id}&currentPage=${page.currentPage}&nameQuery=${socialInsuranceOperation.nameQuery}&idCardQuery=${socialInsuranceOperation.idCardQuery}&sbCardQuery=${socialInsuranceOperation.sbCardQuery}" onclick="return confirmx(&#39;确认要删除该社保信息吗？&#39;, this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>

		</tbody>
	</table>
	<div class="pagination"><ul>
		<li class="disabled"><a href="javascript:void(0)" onclick="paging.action='shebao/shebaoList?currentPage=${page.currentPage - 1}'; paging.submit()">« 上一页</a></li>
		<li class="active"><a href="javascript:void(0)">${page.currentPage}</a></li>
		<li class="disabled"><a href="javascript:void(0)" onclick="paging.action='shebao/shebaoList?currentPage=${page.currentPage + 1}'; paging.submit()">下一页 »</a></li>
		<li class="disabled controls"><a href="javascript:">当前
			<input type="text" value="${page.currentPage}" onkeypress="var e=window.event||event;var c=e.keyCode||e.which;if(c==13)page(this.value,10,&#39;&#39;);" onclick="this.select();">
			/ <input type="text" value="${page.pageCount}" onkeypress="var e=window.event||event;var c=e.keyCode||e.which;if(c==13)page(1,this.value,&#39;&#39;);" onclick="this.select();">
			条，共 ${page.count} 条</a></li>
	</ul>
</form>
	<div style="clear:both;"></div></div>

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