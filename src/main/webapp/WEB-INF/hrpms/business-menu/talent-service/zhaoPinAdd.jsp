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
<!-- saved from url=(0052)http://localhost:8080/jeesite/a/company/needJob/form -->
<html style="overflow-x:auto;overflow-y:auto;"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>招聘信息管理 - Powered By JeeSite</title>
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

	<script src="js/static/verify/ZhaoPinAdd.js"></script>
	<meta name="decorator" content="default">
	<script type="text/javascript">
        $(document).ready(function() {
            $("#inputForm").validate({
                submitHandler: function(form){
                    loading('正在提交，请稍等...');
                    form.submit();
                },
                errorContainer: "#messageBox",
                errorPlacement: function(error, element) {
                    $("#messageBox").text("输入有误，请先更正。");
                    if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
                        error.appendTo(element.parent().parent());
                    } else {
                        error.insertAfter(element);
                    }
                }
            });
        });
	</script>

</head>
<body>

<ul class="nav nav-tabs">
	<li><a href="talent/zhaopinList">招聘信息列表</a></li>
	<li class="active"><a href="talent/zhaopinToAdd">招聘信息添加</a></li>
</ul><br>
<form id="inputForm" class="form-horizontal" action="talent/zhaopinAdd" method="post" novalidate="novalidate">
	<script type="text/javascript">top.$.jBox.closeTip();</script>
	<table class="table table-bordered table-condensed">
		<tbody><tr>
			<td><label class="control-label">需求名称：</label></td>
			<td>
				<input id="jobName" name="jobName" class="input-xlarge required" type="text" value="" maxlength="100">
				<span class="help-inline"><font color="red">*</font> </span>
			</td>
			<td><label class="control-label">需求职位：</label></td>
			<td><input id="jobType" name="jobType" class="input-xlarge " type="text" value="" maxlength="100">
			</td>
		</tr>

		<tr>
			<td><label class="control-label">所属行业：</label></td>
			<td>
				<select id="industry" name="industry" htmlescape="false" maxlength="2" class="input-xlarge  select2-offscreen" tabindex="-1">
					<option value="">请选择</option>
					<c:forEach items="${industrys}" var="industry">
                        <option value="${industry.value}">${industry.label}</option>
                    </c:forEach>
				</select>
			</td>
			<td>
				<label class="control-label">发布公司：</label>
			</td><td>
			<select id="companyId" path="companyId" name="companyId" class="input-xlarge  select2-offscreen" style="width:270px" tabindex="-1">
				<option value="">请选择</option>
                <c:forEach items="${companys}" var="company">
                    <option value="${company[0]}">
                        ${company[1]}
                    </option>
                </c:forEach>
            </select>
		</td>

		</tr>
		<tr>
			<td><label class="control-label">需求人数：</label></td>
			<td><input id="needPerson" name="needPerson" class="input-xlarge " type="number" value="" maxlength="11"></td>
			<td><label class="control-label">结算方式：</label></td>
			<td>
				<select name="payType" path="payType" class="input-xlarge  select2-offscreen" style="width:270px" tabindex="-1">
					<c:forEach items="${payTypes}" var="payType">
                        <option value="${payType.value}">${payType.label}</option>
                    </c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td><label class="control-label">需求单价：</label></td>
			<td><input id="price" name="price" class="input-xlarge " type="number" value=""></td>
			<td><label class="control-label">工作地点：</label></td>
			<td><input id="address" name="address" class="input-xlarge " type="text" value="" maxlength="50"></td>
		</tr>
		<tr>
			<td><label class="control-label">开始日期：</label></td>
			<td>
				<input id="startTime" name="startTime" type="date" maxlength="20" class="input-medium Wdate " value="">
			</td>
			<td><label class="control-label">结束日期：</label></td>
			<td><input id="endTime" name="endTime" type="date" maxlength="20" class="input-medium Wdate " value=""></td>
		</tr>

		<tr>
			<td><label class="control-label">信息类型：</label></td>
			<td>
				<select name="infoType" style="width:270px;" tabindex="-1" class="select2-offscreen">
					<c:forEach items="${infoTypes}" var="infoType">
                        <option value="${infoType.value}">${infoType.label}</option>
                    </c:forEach>
				</select>
			</td>
			<td><label class="control-label">需求状态：</label></td>
			<td>
				<select name="status" style="width:270px;" tabindex="-1" class="select2-offscreen">
					<c:forEach items="${statuss}" var="status">
                        <option value="${status.value}">${status.label}</option>
                    </c:forEach>
				</select>
			</td>
		</tr>

		<tr>
			<td><label class="control-label">需求详细：</label></td>
			<td>
				<textarea id="jobContent" name="jobContent" class="input-xlarge " rows="4" cols="30"></textarea>
			</td>
			<td><label class="control-label">备注信息：</label></td>
			<td>
				<textarea id="remark" name="remark" class="input-xlarge " rows="4" cols="30"></textarea>
			</td>
		</tr>
		</tbody></table>

	<div class="form-actions">
		<input id="btnSubmit" class="btn btn-primary" type="button" onclick="inputFormSubmit()" value="保 存">&nbsp;
		<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)">
	</div>
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