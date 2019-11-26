<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<base href="<%=basePath%>">
<!DOCTYPE html>
<!-- saved from url=(0047)http://localhost:8080/jeesite/a/company/needJob -->
<html style="overflow-x:auto;overflow-y:auto;"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>招聘信息管理 - Powered By JeeSite</title>
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
	<li class="active"><a href="zhaopin/zhaopinList">招聘信息列表</a></li>
	<li><a href="zhaopin/zhaopinToAdd">招聘信息添加</a></li>
</ul>
<form id="searchForm" class="breadcrumb form-search" action="zhaopin/zhaopinList" method="post">
	<ul class="ul-form">
		<li><label>需求名称：</label>
			<input name="jobNameQuery" class="input-medium" type="text" value="${needJobOperation.jobNameQuery}" maxlength="100">
		</li>
		<li><label>需求职位：</label>
			<input name="jobTypeQuery" class="input-medium" type="text" value="${needJobOperation.jobTypeQuery}" maxlength="2">
		</li>
		<li><label>所属行业：</label>
			<select name="industryQuery" htmlescape="false" maxlength="2" class="input-xlarge  select2-offscreen" tabindex="-1">
				<option value="">全部</option>
				<c:forEach items="${industrys}" var="industry">
					<c:if test="${needJobOperation.industryQuery == industry.value}">
						<option value="${industry.value}" selected>${industry.label}</option>
					</c:if>
					<c:if test="${needJobOperation.industryQuery != industry.value}">
						<option value="${industry.value}">${industry.label}</option>
					</c:if>
				</c:forEach>
			</select>
		</li>
		<li><label>发布公司：</label>
			<select name="companyIdQuery" style="width:180px;" tabindex="-1" class="select2-offscreen">
				<option value="">全部</option>
				<c:forEach items="${companys}" var="company">
					<c:if test="${needJobOperation.companyIdQuery == company[0]}">
						<option value="${company[0]}" selected>${company[1]}</option>
					</c:if>
					<c:if test="${needJobOperation.companyIdQuery != company[0]}">
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
		<th>需求名称</th>
		<th>需求职位</th>
		<th>所属行业</th>
		<th>需求人数</th>
		<th>支付方式</th>
		<th>需求单价</th>
		<th>发布公司</th>
		<th>开始日期</th>
		<th>结束日期</th>
		<th>工作地点</th>
		<th>需求状态</th>
		<th>信息类型</th>
		<th>操作</th>
	</tr>
	</thead>
	<tbody>

	<c:forEach items="${page.dataList}" var="needJob">
		<tr>
			<td><a href="zhaopin/zhaopinDetailById?id=${needJob.id}">${needJob.id}</a></td>
			<td>${needJob.jobName}</td>
			<td>${needJob.jobType}</td>
			<td>
				<c:forEach items="${industrys}" var="industry">
					<c:if test="${industry.value == needJob.industry}">
						<span>${industry.label}</span>
					</c:if>
				</c:forEach>
			</td>
			<td>${needJob.needPerson}</td>
			<td>
				<c:forEach items="${payTypes}" var="payType">
					<c:if test="${payType.value == needJob.payType}">
						<span>${payType.label}</span>
					</c:if>
				</c:forEach>
			</td>
			<td>${needJob.price}</td>
			<td>
				<c:forEach items="${companys}" var="company">
					<c:if test="${company[0] == needJob.companyId}">
						<span>${company[1]}</span>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<f:formatDate value="${needJob.startTime}" pattern="yyyy年MM月dd日" var="startTime"/>
					${startTime}
			</td>
			<td>
				<f:formatDate value="${needJob.endTime}" pattern="yyyy年MM月dd日" var="endTime"/>
					${endTime}
			</td>
			<td>${needJob.address}</td>
			<td>
				<c:forEach items="${statuss}" var="status">
					<c:if test="${status.value == needJob.status}">
						<span>${status.label}</span>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<c:forEach items="${infoTypes}" var="infoType">
					<c:if test="${infoType.value == needJob.infoType}">
						<span>${infoType.label}</span>
					</c:if>
				</c:forEach>
			</td>
			<td>
				<a href="">修改</a>
				<a href="#">删除</a>
			</td>
		</tr>
	</c:forEach>



	</tbody>
</table>
<div class="pagination"><ul>
	<li class="disabled"><a href="javascript:">« 上一页</a></li>
	<li class="active"><a href="javascript:">1</a></li>
	<li class="disabled"><a href="javascript:">下一页 »</a></li>
	<li class="disabled controls"><a href="javascript:">当前 <input type="text" value="1" onkeypress="var e=window.event||event;var c=e.keyCode||e.which;if(c==13)page(this.value,10,&#39;&#39;);" onclick="this.select();"> / <input type="text" value="10" onkeypress="var e=window.event||event;var c=e.keyCode||e.which;if(c==13)page(1,this.value,&#39;&#39;);" onclick="this.select();"> 条，共 1 条</a></li>
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