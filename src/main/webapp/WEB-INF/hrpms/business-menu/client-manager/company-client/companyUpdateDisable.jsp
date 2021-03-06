<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<base href="<%=basePath%>">
<!DOCTYPE html>
<!-- saved from url=(0064)http://localhost:8080/jeesite/a/company/companyInfo/form?id=1000 -->
<html style="overflow-x:auto;overflow-y:auto;"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>公司客户管理 - Powered By JeeSite</title>
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
	<style type="text/css">
		#table1{
			margin:1px;
			padding:1px;
		}
		#table1 tr td{
			margin:1px;
			padding:1px;

		}
	</style>
</head>
<body>

<ul class="nav nav-tabs">
	<li><a href="companyClient/companyClientList">公司客户列表</a></li>
	<li class="active"><a href="companyClient/getCompanyById?id=${tbCompany.id}">公司客户查看</a></li>
</ul><br>
<form id="inputForm" class="form-horizontal" action="#" method="post" novalidate="novalidate">
	<script type="text/javascript">top.$.jBox.closeTip();</script>
	<table class="table table-bordered table-condensed">
		<tbody><tr>
			<td><label class="control-label">公司名称：</label></td>
			<td>
				<input id="name" name="name" class="input-xlarge required" type="text" value="${tbCompany.name}" maxlength="100" disabled="disabled">
				<span class="help-inline"><font color="red">*</font> </span>
			</td>
			<td>
				<label class="control-label">统一信用号：</label>
			</td>
			<td>
				<input id="companyNo" name="companyNo" class="input-xlarge " type="text" value="${tbCompany.companyNo}" maxlength="20" disabled="disabled">
			</td>
		</tr>
		<tr>
			<td><label class="control-label">公司电话：</label></td>
			<td><input id="telPhone" name="telPhone" class="input-xlarge " type="text" value="${tbCompany.telPhone}" maxlength="13" disabled="disabled"></td>
			<td><label class="control-label">邮编：</label></td>
			<td><input id="zipcode" name="zipcode" class="input-xlarge " type="text" value="${tbCompany.zipCode}" maxlength="20" disabled="disabled"></td>
		</tr>

		<tr>
			<td><label class="control-label">法人：</label></td>
			<td><input id="owner" name="owner" class="input-xlarge " type="text" value="${tbCompany.owner}" maxlength="30" disabled="disabled"></td>
			<td><label class="control-label">身份证号：</label></td>
			<td><input id="idCard" name="idCard" class="input-xlarge " type="text" value="${tbCompany.idCard}" maxlength="20" disabled="disabled"></td>
		</tr>

		<tr>
			<td><label class="control-label">法人手机：</label></td>
			<td><input id="phone" name="phone" class="input-xlarge " type="text" value="${tbCompany.phone}" maxlength="13" disabled="disabled"></td>
			<td><label class="control-label">法人性别：</label></td>
			<td>
				<input type="radio" checked disabled="disabled">${tbCompany.sex}
			</td>
		</tr>

		<tr>
			<td><label class="control-label">电子邮件：</label></td>
			<td><input id="email" name="email" class="input-xlarge " type="text" value="${tbCompany.email}" maxlength="50" disabled="disabled"></td>
			<td><label class="control-label">公司性质：</label></td>
			<td><input id="ownerShip" name="ownerShip" class="input-xlarge " type="text" value="${tbCompany.ownerShip}" maxlength="150" disabled="disabled"></td>
		</tr>

		<tr>
			<td><label class="control-label">状态：</label></td>
			<td>
				<select id="status" name="status" class="input-xlarge  select2-offscreen" tabindex="-1" disabled="disabled">
					<option>${tbCompany.status}</option>
				</select>
			</td>
			<td><label class="control-label">公司类别：</label></td>
			<td>
				<select name="companyType" class="input-xlarge  select2-offscreen" tabindex="-1" disabled="disabled">
					<option>${tbCompany.companyType}</option>
				</select>
			</td>
		</tr>

		<tr>
			<td><label class="control-label">公司地址：</label></td>
			<td colspan="3"><textarea id="address" name="address" maxlength="256" class="input-xxlarge " rows="3" disabled="disabled">${tbCompany.address}</textarea></td>
		</tr>

		<tr>
			<td><label class="control-label">备注：</label></td>
			<td colspan="3"><textarea id="remark" name="remark" maxlength="512" class="input-xxlarge " rows="3" disabled="disabled">${tbCompany.remark}</textarea></td>
		</tr>

		</tbody></table>

	<div class="form-actions">
		<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)">
	</div>
</form>

<script type="text/javascript">//<!-- 无框架时，左上角显示菜单图标按钮。
if(!(self.frameElement && self.frameElement.tagName=="IFRAME")){
    $("body").prepend("<i id=\"btnMenu\" class=\"icon-th-list\" style=\"cursor:pointer;float:right;margin:10px;\"></i><div id=\"menuContent\"></div>");
    $("#btnMenu").click(function(){
        top.$.jBox('get:/jeesite/a/sys/menu/treeselect;JSESSIONID=ae68ac8a5eb54d779c61f0b46c49a440', {title:'选择菜单', buttons:{'关闭':true}, width:300, height: 350, top:10});
        //if ($("#menuContent").html()==""){$.get("/jeesite/a/sys/menu/treeselect", function(data){$("#menuContent").html(data);});}else{$("#menuContent").toggle(100);}
    });
}//-->
</script>

</body></html>