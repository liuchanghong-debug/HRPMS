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
<!-- saved from url=(0049)http://localhost:8080/jeesite/a/company/personJob -->
<html style="overflow-x:auto;overflow-y:auto;"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>劳务合作管理 - Powered By JeeSite</title>
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
	<li class="active"><a href="talent/laowuList">劳务合作列表</a></li>
	<li><a href="talent/laowuToAdd">劳务合作添加</a></li>
</ul>
<form id="searchForm" class="breadcrumb form-search" action="talent/laowuList" method="post">
	<ul class="ul-form">
		<li><label>客户名称：</label>
			<input name="nameQuery" class="input-medium" type="text" value="${personJobOperation.nameQuery}" maxlength="20">
		</li>
		<li><label>身份证号：</label>
			<input name="idCardQuery" class="input-medium" type="text" value="${personJobOperation.idCardQuery}" maxlength="20">
		</li>
		<li><label>合作公司：</label>
			<select name="companyIdQuery" class="input-medium select2-offscreen" tabindex="-1">
				<c:forEach items="${companys}" var="company">
					<c:if test="${personJobOperation.companyIdQuery == company[0]}">
						<option value="${company[0]}" selected="selected">${company[1]}</option>
					</c:if>
					<c:if test="${personJobOperation.companyIdQuery != company[0]}">
						<option value="${company[0]}">${company[1]}</option>
					</c:if>
				</c:forEach>
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
		<th>客户名称</th>
		<th>身份证号</th>
		<th>合作公司</th>
		<th>工作类别</th>
		<th>公司单价</th>
		<th>个人单价</th>
		<th>开始时间</th>
		<th>结束时间</th>
		<th>操作</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${page.dataList}" var="person">
		<tr>
			<td><a href="talent/laowuDetailById?id=${person.id}">${person.id}</a></td>
			<td>${person.name}</td>
			<td>${person.idCard}</td>
			<td>
				<c:forEach items="${needJobs}" var="needJob">
					<c:if test="${needJob.id == person.companyId}">
						<c:forEach items="${companys}" var="company">
							<c:if test="${needJob.companyId == company[0]}">
								<span>${company[1]}</span>
							</c:if>
						</c:forEach>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:forEach items="${jobTypes}" var="jobType">
					<c:if test="${jobType.value == person.jobType}">
						<span>${jobType.label}</span>
					</c:if>
				</c:forEach>
			</td>
			<td>${person.companyPrice}</td>
			<td>${person.personPrice}</td>
			<td>
				<f:formatDate value="${person.startTime}" pattern="yyyy年MM月dd日" var="startTime"/>
				<span>${startTime}</span>
		</td>
			<td>
				<f:formatDate value="${person.endTime}" pattern="yyyy年MM月dd日" var="endTime"/>
				<span>${endTime}</span>
		</td>
			<td>
				<a href="talent/laowuToUpdate?id=${person.id}&currentPage=${page.currentPage}&nameQuery=${personJobOperation.nameQuery}&idCardQuery=${personJobOperation.idCardQuery}&companyIdQuery=${personJobOperation.companyIdQuery}">修改</a>
				<a href="talent/laowuDelete?id=${person.id}&currentPage=${page.currentPage}&nameQuery=${personJobOperation.nameQuery}&idCardQuery=${personJobOperation.idCardQuery}&companyIdQuery=${personJobOperation.companyIdQuery}" onclick="return confirmx(&#39;确认要删除该劳务合作吗？&#39;, this.href)">删除</a>
			</td>
		</tr>
	</c:forEach>


	</tbody>
</table>
<div class="pagination">
	<form action="" method="post" name="paging">
		<input type="hidden" name="nameQuery" value="${personJobOperation.nameQuery}">
		<input type="hidden" name="idCardQuery" value="${personJobOperation.idCardQuery}">
		<input type="hidden" name="companyIdQuery" value="${personJobOperation.companyIdQuery}">

		<ul>
			<li class="disabled"><a href="javascript:void(0)" onclick="paging.action='talent/laowuList?currentPage=${page.currentPage - 1}'; paging.submit()">« 上一页</a></li>
			<li class="active"><a href="javascript:void(0)">${page.currentPage}</a></li>
			<li class="disabled"><a href="javascript:void(0)" onclick="paging.action='talent/laowuList?currentPage=${page.currentPage + 1}'; paging.submit()">下一页 »</a></li>
			<li class="disabled controls">
				<a href="javascript:">
					当前 <input type="text" value="${page.currentPage}" onkeypress="var e=window.event||event;var c=e.keyCode||e.which;if(c==13)page(this.value,10,&#39;&#39;);" onclick="this.select();">
					/ <input type="text" value="${page.pageCount}" onkeypress="var e=window.event||event;var c=e.keyCode||e.which;if(c==13)page(1,this.value,&#39;&#39;);" onclick="this.select();">
					页，共 ${page.count} 条</a></li>
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