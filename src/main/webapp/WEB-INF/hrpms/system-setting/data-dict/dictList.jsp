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
<!-- saved from url=(0057)http://localhost:8080/jeesite/a/company/system/systemDict -->
<html style="overflow-x:auto;overflow-y:auto;"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>数据字典管理 - Powered By JeeSite</title>
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
	<li class="active"><a href="datadict/datadictList">数据字典列表</a></li>
	<li><a href="datadict/datadictAdd">数据字典添加</a></li>
</ul>
<form id="searchForm" class="breadcrumb form-search" action="datadict/datadictList" method="post">
	<ul class="ul-form">
		<li><label>字典名称：</label>
			<input name="nameQuery" class="input-medium" type="text" value="${dataDictOperation.nameQuery}" maxlength="50">
		</li>
		<li><label>状态：</label>
			<select name="statusQuery" class="input-medium">
				<option value="">全部</option>
				<c:forEach items="${dataStatus}" var="status">
					<c:if test="${dataDictOperation.statusQuery == status.value}">
						<option value="${status.value}" selected="selected">${status.label}</option>
					</c:if>
					<c:if test="${dataDictOperation.statusQuery != status.value}">
						<option value="${status.value}">${status.label}</option>
					</c:if>
				</c:forEach>
			</select>
		</li>
		<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"></li>
		<li class="clearfix"></li>
	</ul>
</form>

<script type="text/javascript">top.$.jBox.closeTip();</script>
<input type="hidden" id="currentPage" name="currentPage" value="${dataDicts.currentPage}">

<form action="" method="post" name="paging">
	<input type="hidden"  name="nameQuery" value="${dataDictOperation.nameQuery}">
	<input type="hidden"  name="statusQuery" value="${dataDictOperation.statusQuery}">

	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>字典名称</th>
				<th>存储值</th>
				<th>显示值</th>
				<th>描述</th>
				<th>排序</th>
				<th>状态</th>
				<th>操作</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach items="${dataDicts.dataList}" var="tbSystemDict">
			<tr>
				<td><a href="datadict/getTbSystemDictById?id=${tbSystemDict.id}">${tbSystemDict.name}</a></td>
				<td>${tbSystemDict.value}</td>
				<td>${tbSystemDict.label}</td>
				<td>${tbSystemDict.description}</td>
				<td>${tbSystemDict.sort}</td>
				<td>
					<c:forEach items="${dataStatus}" var="status">
						<c:if test="${tbSystemDict.status == status.value}">
							<span>${status.label}</span>
						</c:if>
					</c:forEach>
				</td>

					<input type="hidden" name="id" value="${tbSystemDict.id}">
				<td>
					<a href="javascript:void(0)" onclick="paging.action='datadict/toUpdate?currentPage=${dataDicts.currentPage}'; paging.submit()">修改</a>
					<a href="datadict/dictDelete?id=${tbSystemDict.id}&currentPage=${dataDicts.currentPage}&nameQuery=${dataDictOperation.nameQuery}&statusQuery=${dataDictOperation.statusQuery}" onclick="return confirmx(&#39;确认要删除该字典数据吗？&#39;, this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">
		<ul>
			<li class="disabled"><a href="javascript:void(0)" onclick="paging.action='datadict/datadictList?currentPage=${dataDicts.currentPage - 1}'; paging.submit()">« 上一页</a></li>
			<li class="active"><a href="javascript:void(0)">${dataDicts.currentPage}</a></li>
			<li class="disabled"><a href="javascript:void(0)" onclick="paging.action='datadict/datadictList?currentPage=${dataDicts.currentPage + 1}'; paging.submit()">下一页 »</a></li>
			<li class="disabled controls">
				<a href="javascript:">当前
					<input type="text" value="${dataDicts.currentPage}" onkeypress="var e=window.event||event;var c=e.keyCode||e.which;if(c==13)page(this.value,10,&#39;&#39;);" onclick="this.select();">
					/ <input type="text" value="${dataDicts.pageCount}" onkeypress="var e=window.event||event;var c=e.keyCode||e.which;if(c==13)page(1,this.value,&#39;&#39;);" onclick="this.select();">
					页，共 ${dataDicts.count} 条</a>
			</li>
		</ul>
		<div style="clear:both;"></div>
	</div>
</form>


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