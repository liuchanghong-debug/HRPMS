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
<!-- saved from url=(0055)http://localhost:8080/jeesite/a/company/personInfo/form -->
<html style="overflow-x:auto;overflow-y:auto;"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>人才信息管理 - Powered By JeeSite</title>
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

	<script src="js/static/verify/PersonAdd.js"></script>
	<meta name="decorator" content="default">
	<script type="text/javascript">
        $(document).ready(function() {
            //$("#name").focus();
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
	<li><a href="talent/personList">人才信息列表</a></li>
	<li class="active"><a href="talent/personToAdd">人才信息添加</a></li>
</ul><br>
<form id="inputForm" class="form-horizontal" action="talent/personAdd" method="post" novalidate="novalidate" enctype="multipart/form-data">
	<script type="text/javascript">top.$.jBox.closeTip();</script>
	<table class="table table-bordered table-condensed">
		<tbody><tr>
			<td><label class="control-label">客户名称：</label></td>
			<td>
				<select id="customerId" name="customerId" tabindex="-1" class="input-xlarge  select2-offscreen">
					<option value="">请选择</option>
					<c:forEach items="${customerList}" var="customer">
						<option value="${customer.id}">${customer.name}</option>
					</c:forEach>
				</select>
				<input id="name" type="hidden" name="name" value="" class="input-xlarge required">
				<span class="help-inline"><font color="red">*</font></span>
			</td>
			<td><label class="control-label">身份证号：</label></td>
			<td>
				<input id="idCard" name="idCard" class="input-xlarge required" readonly type="text" value="" maxlength="20">
				<span class="help-inline"><font color="red">*</font></span>
			</td>
		</tr>

		<tr>
			<td><label class="control-label">求职意向：</label></td>
			<td>
				<input id="jobIntentsion" name="jobIntentsion" class="input-xlarge required" type="text" value="" maxlength="256">
				<span class="help-inline"><font color="red">*</font></span>
			</td>
			<td>
				<label class="control-label">工作类型：</label>
			</td><td>
			<select name="jobType" tabindex="-1" class="select2-offscreen">
				<c:forEach items="${jobTypes}" var="jobType">
					<option value="${jobType.value}">${jobType.label}</option>
				</c:forEach>
			</select>
		</td>
		</tr>
		<tr>
			<td><label class="control-label">期望月薪：</label></td>
			<td>
				<input id="forPrice" name="forPrice" class="input-xlarge required" type="text" value="">
				<span class="help-inline"><font color="red">*</font></span>
			</td>
			<td><label class="control-label">期望工作地：</label></td>
			<td>
				<input id="forAddress" name="forAddress" class="input-xlarge " type="text" value="" maxlength="20">
				<span class="help-inline"><font color="red">*</font></span>
			</td>
		</tr>
		<tr>
			<td><label class="control-label">工作经历：</label></td>
			<td><textarea id="worked" name="worked" maxlength="256" class="input-xxlarge " rows="2"></textarea></td>
			<td><label class="control-label">状态：</label></td>
			<td>
				<select name="status" tabindex="-1" class="select2-offscreen">
					<c:forEach items="${statuss}" var="status">
						<option value="${status.value}">${status.label}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td><label class="control-label">自我介绍：</label></td>
			<td colspan="3">
				<textarea id="personInfo" name="personInfo" maxlength="256" class="input-xxlarge " rows="3"></textarea>
			</td>
		</tr>

		<tr>
			<td><label class="control-label">个人简历：</label></td>
			<td colspan="3">
				<ol id="resumeurlPreview">
					<li style="list-style:none;padding-top:5px;"><span id="fileName"></span></li>
				</ol>
				<a href="javascript:" onclick="$('#resumeFile').click()" class="btn">添加</a>&nbsp;
				<a href="javascript:" onclick="fileEmpty();" class="btn">清除</a>
				<input type="file" id="resumeFile" name="resumeFile" onchange="fileChange(this.value)" style="display: none;">
				<script>
                    function fileChange(name) {
						$("#fileName").text(name.slice(name.lastIndexOf("\\") + 1, name.length));
                    }
                    function fileEmpty() {
						$("#resumeFile").empty();
                        $("#fileName").text("");
                    }
				</script>
			</td>
		</tr>

		<tr>
			<td><label class="control-label">备注信息：</label></td>
			<td colspan="3">
				<textarea id="remark" name="remark" maxlength="256" class="input-xxlarge " rows="2"></textarea>
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