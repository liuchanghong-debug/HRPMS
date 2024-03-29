<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<base href="<%=basePath%>">
<!DOCTYPE html>
<!-- saved from url=(0067)http://localhost:8080/jeesite-master/a/company/personInfo/form?id=1 -->
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

	<script src="js/static/verify/PersonUpdate.js"></script>
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
	<li class="active"><a href="talent/personToUpdate?id=${person.id}&currentPage=${currentPage}&nameQuery=${personOperation.nameQuery}&idCardQuery=${personOperation.idCardQuery}&jobInterentsionQuery=${personOperation.jobInterentsionQuery}&forAddressQuery=${personOperation.forAddressQuery}">人才信息修改</a></li>
</ul><br>
<form id="inputForm" class="form-horizontal" action="talent/personUpdate" method="post" novalidate="novalidate" enctype="multipart/form-data">
	<input type="hidden" name="nameQuery" value="${personOperation.nameQuery}">
	<input type="hidden" name="idCardQuery" value="${personOperation.idCardQuery}">
	<input type="hidden" name="jobInterentsionQuery" value="${personOperation.jobInterentsionQuery}">
	<input type="hidden" name="forAddressQuery" value="${personOperation.forAddressQuery}">
	<input type="hidden" name="currentPage" value="${currentPage}">

	<input type="hidden" id="id" name="id" value="${person.id}">
	<script type="text/javascript">top.$.jBox.closeTip();</script>
	<table class="table table-bordered table-condensed">
		<tbody><tr>
			<td><label class="control-label">客户名称：</label></td>
			<td>
				<input id="name" type="text" name="name" value="${person.name}" readonly class="input-xlarge required">
				<span class="help-inline"><font color="green">*</font> </span>
			</td>
			<td><label class="control-label">身份证号：</label></td>
			<td>
				<input id="idCard" name="idCard" class="input-xlarge required" type="text" value="${person.idCard}" readonly maxlength="20">
				<span class="help-inline"><font color="green">*</font> </span>
			</td>
		</tr>

		<tr>
			<td><label class="control-label">求职意向：</label></td>
			<td>
				<input id="jobIntentsion" name="jobIntentsion" class="input-xlarge required" type="text" value="${person.jobIntentsion}" maxlength="256">
				<span class="help-inline"><font color="green">*</font> </span>
			</td>
			<td>
				<label class="control-label">工作类型：</label>
			</td><td>
			<select name="jobType"  class="select2-offscreen">
				<c:forEach items="${jobTypes}" var="jobType">
					<c:if test="${jobType.value == person.jobType}">
						<option value="${jobType.value}" selected>${jobType.label}</option>
					</c:if>
					<c:if test="${jobType.value != person.jobType}">
						<option value="${jobType.value}">${jobType.label}</option>
					</c:if>
				</c:forEach>
			</select>
		</td>
		</tr>
		<tr>
			<td><label class="control-label">期望月薪：</label></td>
			<td>
				<input id="forPrice" name="forPrice" class="input-xlarge required" type="text" value="${person.forPrice}">
				<span class="help-inline"><font color="green">*</font> </span>
			</td>
			<td><label class="control-label">期望工作地：</label></td>
			<td>
				<input id="forAddress" name="forAddress" class="input-xlarge " type="text" value="${person.forAddress}" maxlength="20">
				<span class="help-inline"><font color="green">*</font> </span>
			</td>
		</tr>
		<tr>
			<td><label class="control-label">工作经历：</label></td>
			<td>
				<textarea id="worked" name="worked" maxlength="256" class="input-xxlarge " rows="2">${person.worked}</textarea>
			</td>
			<td><label class="control-label">状态：</label></td>
			<td>
				<select name="status" tabindex="-1" class="select2-offscreen">
					<c:forEach items="${statuss}" var="status">
						<c:if test="${status.value == person.status}">
							<option value="${status.value}" selected>${status.label}</option>
						</c:if>
						<c:if test="${status.value != person.status}">
							<option value="${status.value}">${status.label}</option>
						</c:if>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td><label class="control-label">自我介绍：</label></td>
			<td>
				<textarea id="personInfo" name="personInfo" maxlength="256" class="input-xxlarge " rows="3">${person.personInfo}</textarea>
			</td>
		</tr>

		<tr>
			<td><label class="control-label">个人简历：</label></td>
			<td colspan="3">
				<ol id="resumeurlPreview">
					<li style="list-style:none;padding-top:5px;">
						<span id="fileName">${fn:substring(person.resumeUrl, fn:indexOf(person.resumeUrl, '_') + 1, fn:length(person.resumeUrl))}</span>
					</li>
				</ol>
				<a href="javascript:" onclick="$('#resumeFile').click()" class="btn">添加</a>&nbsp;
				<a href="javascript:" onclick="fileEmpty();" class="btn">清除</a>

				<input type="file" id="resumeFile" name="resumeFile" onchange="fileChange(this.value)" style="display: none">
				<script>
                    function fileChange(name) {
                        $("#fileName").text(name.slice(name.lastIndexOf("\\") + 1, name.length));
                    }
                    function fileEmpty() {
                        $("#resumeFile").val(null);
                        $("#fileName").text("");
                    }
				</script>
			</td>
		</tr>

		<tr>
			<td><label class="control-label">备注信息：</label></td>
			<td colspan="3">
				<textarea id="remark" name="remark" maxlength="256" class="input-xxlarge " rows="2">${person.remark}</textarea>
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
        top.$.jBox('get:/jeesite-master/a/sys/menu/treeselect;JSESSIONID=b30d2fa0f4264f6288e0788088ab4b22', {title:'选择菜单', buttons:{'关闭':true}, width:300, height: 350, top:10});
        //if ($("#menuContent").html()==""){$.get("/jeesite-master/a/sys/menu/treeselect", function(data){$("#menuContent").html(data);});}else{$("#menuContent").toggle(100);}
    });
}//-->
</script>

</body></html>