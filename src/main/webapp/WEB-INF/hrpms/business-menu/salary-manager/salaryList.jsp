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
<!-- saved from url=(0050)http://localhost:8080/jeesite/a/company/salaryInfo -->
<html style="overflow-x:auto;overflow-y:auto;"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>工资管理 - Powered By JeeSite</title>
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

        function openFile() {
            $("#file").click();
        }
        function upload() {
            $("#fileSubmit").click();
        }
	</script>

</head>
<body>

<ul class="nav nav-tabs">
	<li class="active"><a href="salary-manager/selectSalaryByDuo?flag=1">工资列表</a></li>
	<li><a href="salary-manager/addSalaryJsp">代发工资</a></li>
</ul>
<form id="searchForm" class="breadcrumb form-search" action="salary-manager/selectSalaryByDuo?flag=1" method="post">
<input id="pageNo" name="pageNo" type="hidden" value="1">
	<input id="pageSize" name="pageSize" type="hidden" value="10">
	<ul class="ul-form">
		<li><label>客户名称：</label>
			<input id="name" name="name" class="input-medium" type="text" value="" maxlength="20">
		</li>
		<li><label>身份证号：</label>
			<input id="idcard" name="idCard" class="input-medium" type="text" value="" maxlength="20">
		</li>
		<li class="btns"><input id="btnSubmit1" class="btn btn-primary" type="submit" value="查询"></li>
		</form>
		<li class="btns"><input class="btn btn-primary" type="button" onclick="location.href='salary-manager/salaryModleDownload?name=客户工资模板'" value="模板下载"></li>
		<li class="btns">
			<form action="salary-manager/salaryUpload" method="post" id="fileUpload" enctype="multipart/form-data">
				<input class="btn btn-primary" type="button" value="导入" onclick="openFile()">
				<input type="file" name="file" id="file" onchange="upload()" style="display: none" accept="application/Excel,application/vnd.ms-excel">
				<input type="submit" style="display: none" id="fileSubmit">
			</form>
		</li>
		<li class="btns"><input class="btn btn-primary" type="button" onclick="location.href='salary-manager/salaryDownload'" value="导出"></li>
		<li class="clearfix"></li>
	</ul>


<script type="text/javascript">top.$.jBox.closeTip();</script>

<table id="contentTable" class="table table-striped table-bordered table-condensed">
	<thead>
	<tr>
		<th>编号</th>
		<th>客户名称</th>
		<th>身份证号</th>
		<th>银行卡号</th>
		<th>支付日期</th>
		<th>基本工资</th>
		<th>奖金</th>
		<th>加班费</th>
		<th>社保扣费</th>
		<th>公积金扣费</th>
		<th>应交税款</th>
		<th>应发工资</th>
		<th>实发工资</th>
		<th>代理费用</th>
		<th>操作</th>
	</tr>
	</thead>
	<tbody>

	<c:forEach items="${page.dataList}" var="salary">
		<tr>
			<td><a href="salary-manager/selectSalaryById?id=${salary.id}&flag=1">
				${salary.id}
			</a></td>
			<td>
				${salary.name}
			</td>
			<td>
				${salary.idCard}
			</td>
			<td>
				${salary.payCard}
			</td>
			<td>
				${salary.payDate}
			</td>
			<td>
				${salary.baseSalary}
			</td>
			<td>
				${salary.bonusPay}
			</td>
			<td>
				${salary.overTimePay}
			</td>
			<td>
				${salary.sheBaoPay}
			</td>
			<td>
				${salary.gongJiJinPay}
			</td>
			<td>
				${salary.taxPay}
			</td>
			<td>
				${salary.totalPay}
			</td>
			<td>
				${salary.mustPay}
			</td>
			<td>
				${salary.proxyFee}
			</td>
			<td>
				<a href="salary-manager/selectSalaryById?id=${salary.id}&flag=2">修改</a>
				<a href="/salary-manager/deleteSalaryById?id=${salary.id}" onclick="return confirmx(&#39;确认要删除该工资吗？&#39;, this.href)">删除</a>
			</td>
		</tr>

	</c:forEach>

	</tbody>
</table>
<div class="pagination"><ul>
	<li class="disabled"><a href="salary-manager/selectSalaryByDuo?currentPage=${page.currentPage-1}&name=${map.name}&idCard=${map.idCard}&flag=1">上一页</a></li>
	<c:forEach begin="1" end="${page.pageCount}" var="num">
		<li class="active"><a href="salary-manager/selectSalaryByDuo?currentPage=${num}&name=${map.name}&idCard=${map.idCard}&flag=1">${num}</a></li>
	</c:forEach>

	<li class="disabled"><a href="salary-manager/selectSalaryByDuo?currentPage=${page.currentPage+1}&name=${map.name}&idCard=${map.idCard}&flag=1">下一页</a></li>
	<li class="disabled controls"><a href="javascript:">当前
		<input type="text" value="${page.currentPage}" readonly> /
		<input type="text" value="${page.pageCount}" readonly> 条，共 ${page.pageCount} 页</a></li>
</ul>
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