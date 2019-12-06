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
<!-- saved from url=(0072)http://localhost:8080/jeesite-master/a/company/customerInfo/form?id=1000 -->
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
	<li><a href="companyClient/customerList">个人客户列表</a></li>
	<li class="active"><a href="companyClient/customerMess?id=${customer.id}">个人客户查看</a></li>
</ul><br>
<form id="inputForm" class="form-horizontal" action="#" method="post" novalidate="novalidate">
	<input id="id" name="id" type="hidden" value="1000">

	<script type="text/javascript">top.$.jBox.closeTip();</script>

	<table class="table table-bordered table-condensed">
		<tbody><tr>
			<td><label class="control-label">客户名称：</label></td>
			<td>
				<input id="name" name="name" class="input-xlarge required" type="text" value="${customer.name}" maxlength="20" disabled="disabled">
				<span class="help-inline"><span style="color: red; ">*</span> </span>
			</td>
			<td><label class="control-label">身份证号：</label></td>
			<td><input id="idcard" name="idcard" class="input-xlarge " type="text" value="${customer.idCard}" maxlength="20" disabled="disabled">
				<span class="help-inline"><span style="color: red; ">*</span> </span>
			</td>
		</tr>

		<tr>
			<td><label class="control-label">客户性别：</label></td>
			<td>
				<input id="sex" name="sex" class="input-xlarge " type="text" value="${customer.sex}" maxlength="2" disabled="disabled">
			</td>
			<td><label class="control-label">出生年月：</label></td>
			<td>
				<input name="birthday" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate " value="${customer.birthday}" onclick="WdatePicker({dateFmt:&#39;yyyy-MM-dd HH:mm:ss&#39;,isShowClear:false});" disabled="disabled">
			</td>
		</tr>
		<tr>
			<td><label class="control-label">手机号码：</label></td>
			<td><input id="phone" name="phone" class="input-xlarge " type="text" value="${customer.phone}" maxlength="13" disabled="disabled"></td>
			<td><label class="control-label">电子邮件：</label></td>
			<td><input id="email" name="email" class="input-xlarge " type="text" value="${customer.email}" maxlength="50" disabled="disabled"></td>
		</tr>
		<tr>
			<td><label class="control-label">现在住址：</label></td>
			<td><input id="address" name="address" class="input-xlarge " type="text" value="${customer.address}" maxlength="100" disabled="disabled"></td>
			<td><label class="control-label">邮政编码：</label></td>
			<td><input id="zipcode" name="zipcode" class="input-xlarge " type="text" value="${customer.zipCode}" maxlength="20" disabled="disabled"></td>
		</tr>

		<tr>
			<td><label class="control-label">毕业学校：</label></td>
			<td><input id="school" name="school" class="input-xlarge " type="text" value="${customer.school}" maxlength="20" disabled="disabled"></td>
			<td><label class="control-label">所学专业：</label></td>
			<td><input id="specialty" name="specialty" class="input-xlarge " type="text" value="${customer.specialty}" maxlength="20" disabled="disabled"></td>
		</tr>

		<tr>
			<td><label class="control-label">毕业时间：</label></td>
			<td><input id="graduation" name="graduation" class="input-xlarge " type="text" value="${customer.graduation}" maxlength="20" disabled="disabled"></td>
			<td><label class="control-label">所属公司：</label></td>
			<td>
				<select id="companyid" name="companyid" class="input-xlarge " disabled="disabled">
					<c:forEach items="${companys}" var="company">
						<c:if test="${company.id == customer.companyId}">
							<option>${company.name}</option>
						</c:if>
					</c:forEach>
				</select>
			</td>
		</tr>

		<tr>
			<td><label class="control-label">客户类别：</label></td>
			<td>
				<select name="companyid" class="input-xlarge " disabled="disabled">
					<c:forEach items="${types}" var="type">
						<c:if test="${type.value == customer.customerType}">
							<option value="">${type.label}</option>
						</c:if>
					</c:forEach>
				</select>
			</td>
			<td><label class="control-label">代发工资：</label></td>
			<td>
				<c:if test="${customer.isSalary == 0}">
					<input type="checkbox" name="isSalary" checked disabled="disabled">
				</c:if>
				<c:if test="${customer.isSalary == 1}">
					<input type="checkbox" name="isSalary" disabled="disabled">
				</c:if>
			</td>
		</tr>

		<tr>
			<td><label class="control-label">代缴社保：</label></td>
			<td>
				<c:if test="${customer.isSheBao == 0}">
					<input type="checkbox" name="isshebao" checked disabled="disabled">
				</c:if>
				<c:if test="${customer.isSheBao == 1}">
					<input type="checkbox" name="isshebao" disabled="disabled">
				</c:if>
			</td>

			<td><label class="control-label">代缴公积金：</label></td>
			<td>
				<c:if test="${customer.isGongJiJin == 0}">
					<input type="checkbox" name="isgongjijin" checked disabled="disabled">
				</c:if>
				<c:if test="${customer.isGongJiJin == 1}">
					<input type="checkbox" name="isgongjijin" disabled="disabled">
				</c:if>
			</td>

		</tr>
		<tr>
			<td><label class="control-label">状态：</label></td>
			<td>
				<select name="status" class="input-xlarge " disabled="disabled">
					<c:forEach items="${statuss}" var="status">
						<c:if test="${status.value == customer.status}">
							<option value="">${status.label}</option>
						</c:if>
					</c:forEach>
				</select>
			</td>
			<td><label class="control-label">备注：</label></td>
			<td><input id="remark" name="remark" class="input-xlarge " type="text" value="${customer.name}" maxlength="256" disabled="disabled"></td>

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
        top.$.jBox('get:/jeesite-master/a/sys/menu/treeselect;JSESSIONID=6ad5829a0dc647b8b897f72c1f625c24', {title:'选择菜单', buttons:{'关闭':true}, width:300, height: 350, top:10});
        //if ($("#menuContent").html()==""){$.get("/jeesite-master/a/sys/menu/treeselect", function(data){$("#menuContent").html(data);});}else{$("#menuContent").toggle(100);}
    });
}//-->
</script>

</body></html>