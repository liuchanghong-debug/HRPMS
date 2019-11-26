<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<base href="<%=basePath%>">
<!DOCTYPE html>
<!-- saved from url=(0073)http://localhost:8080/jeesite-master/a/company/accumulationFund/form?id=1 -->
<html style="overflow-x:auto;overflow-y:auto;"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>公积金管理 - Powered By JeeSite</title>
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
	<li><a href="gongjijin-manager/selectAccumulationByDuo">公积金列表</a></li>
	<li class="active"><a href="gongjijin-manager/selectAccumulationById?id=${tbAccumulationFund.id}&flag=2">公积金修改</a></li>
</ul><br>
<form id="inputForm" class="form-horizontal" action="gongjijin-manager/updateAccumulationById" method="post" novalidate="novalidate">
	<input id="id" name="id" type="hidden" value="${tbAccumulationFund.id}">
	<input name="updateBy" type="hidden" value="${sessionScope.tbSystemUser.id}">
	<input name="createBy" type="hidden" value="${tbAccumulationFund.createBy}">


	<script type="text/javascript">top.$.jBox.closeTip();</script>

	<div class="control-group">
		<label class="control-label">客户名称：</label>
		<div class="controls">
			<input id="name" name="name" readonly class="input-xlarge"  type="text" value="${tbAccumulationFund.name}" maxlength="20" >
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">身份证号：</label>
		<div class="controls">
			<input id="idcard" name="idCard" readonly class="input-xlarge required"  type="text" value="${tbAccumulationFund.idCard}" maxlength="20" >
			<span class="help-inline"><font color="red">*</font> </span>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">公积金号：</label>
		<div class="controls">
			<input id="accountno" name="accountNo" class="input-xlarge required"  type="text" value="${tbAccumulationFund.accountNo}" maxlength="20" >
			<span class="help-inline"><font color="red">*</font> </span>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">缴费期间：</label>
		<div class="controls">
			<input id="paydate" name="payDate" class="input-xlarge required"  type="text" value="${tbAccumulationFund.payDate}" maxlength="20" >
			<span class="help-inline"><font color="red">*</font> </span>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">缴费金额：</label>
		<div class="controls">
			<input id="paymoney" name="payMoney" class="input-xlarge required" type="text"  value="${tbAccumulationFund.payMoney}" >
			<span class="help-inline"><font color="red">*</font> </span>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">代理费：</label>
		<div class="controls">
			<input id="proxyfee" name="proxyFee" class="input-xlarge "  type="text" value="${tbAccumulationFund.proxyFee}" >
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">状态：</label>
		<div class="controls">
			<select name="status" class="input-xlarge" >
				<c:if test="${tbAccumulationFund.status=='0'}" var="bo">
					<option value="0" selected>未缴</option>
					<option value="1">已缴</option>
				</c:if>
				<c:if test="${!bo}">
					<option value="1" selected>已缴</option>
					<option value="0">未缴</option>
				</c:if>
			</select>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">备注：</label>
		<div class="controls">
			<input id="remark" name="remark" class="input-xlarge " type="text"  value="${tbAccumulationFund.remark}" maxlength="256" >
		</div>
	</div>
	<div class="form-actions">
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存">&nbsp;
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