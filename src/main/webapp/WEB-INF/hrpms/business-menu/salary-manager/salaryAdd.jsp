<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<base href="<%=basePath%>">
<!DOCTYPE html>
<!-- saved from url=(0055)http://localhost:8080/jeesite/a/company/salaryInfo/form -->
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
	<li><a href="../salaryList/saved_resource.html">工资列表</a></li>
	<li class="active"><a href="saved_resource.html">代发工资</a></li>
</ul><br>
<form id="inputForm" class="form-horizontal" action="#" method="post" novalidate="novalidate">
	<input id="id" name="id" type="hidden" value="">

	<script type="text/javascript">top.$.jBox.closeTip();</script>


	<table class="table table-bordered table-condensed">
		<tbody><tr>
			<td><label class="control-label">客户名称：</label></td>
			<td>
				<input id="idcard" name="idcard" class="input-xlarge required" type="text" value="" maxlength="20">
			</td>
			<td><label class="control-label">身份证号：</label></td>
			<td><input id="idcard" name="idcard" class="input-xlarge required" type="text" value="" maxlength="20">
				<span class="help-inline"><font color="red">*</font> </span>
			</td>
		</tr>

		<tr>
			<td><label class="control-label">银行卡号：</label></td>
			<td>
				<input id="paycard" name="paycard" class="input-xlarge " type="text" value="" maxlength="20">
			</td>
			<td><label class="control-label">基本工资：</label></td>
			<td>
				<input id="basesalary" name="basesalary" class="input-xlarge " type="text" value="">
			</td>
		</tr>
		<tr>
			<td><label class="control-label">奖金：</label></td>
			<td><input id="bonuspay" name="bonuspay" class="input-xlarge " type="text" value=""></td>
			<td><label class="control-label">加班费：</label></td>
			<td><input id="overtimepay" name="overtimepay" class="input-xlarge " type="text" value=""></td>
		</tr>
		<tr>
			<td><label class="control-label">社保扣费：</label></td>
			<td><input id="shebaopay" name="shebaopay" class="input-xlarge " type="text" value=""></td>
			<td><label class="control-label">公积金扣费：</label></td>
			<td><input id="gongjijinpay" name="gongjijinpay" class="input-xlarge " type="text" value=""></td>
		</tr>

		<tr>
			<td><label class="control-label">应交税款：</label></td>
			<td><input id="taxpay" name="taxpay" class="input-xlarge " type="text" value=""></td>
			<td><label class="control-label">应发工资：</label></td>
			<td>
				<input id="totalpay" name="totalpay" class="input-xlarge required" type="text" value="">
				<span class="help-inline"><font color="red">*</font> </span>
			</td>
		</tr>

		<tr>
			<td><label class="control-label">实发工资：</label></td>
			<td>
				<input id="mustpay" name="mustpay" class="input-xlarge required" type="text" value="">
				<span class="help-inline"><font color="red">*</font> </span>
			</td>
			<td><label class="control-label">代理费用：</label></td>
			<td>
				<input id="proxyfee" name="proxyfee" class="input-xlarge " type="text" value="">
			</td>
		</tr>

		<tr>
			<td><label class="control-label">状态：</label></td>
			<td>
				<select name="status" class="input-xlarge  select2-offscreen" tabindex="-1">
					<option value="">已发
					</option><option value="">未发
				</option></select>
			</td>

			<td><label class="control-label">支付日期：</label></td>
			<td>
				<input name="paydate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate " value="" onclick="WdatePicker({dateFmt:&#39;yyyy-MM-dd&#39;,isShowClear:false});">
			</td>
		</tr>
		<tr>
			<td><label class="control-label">备注：</label></td>
			<td colspan="3"><input id="remark" name="remark" class="input-xlarge " type="text" value="" maxlength="256"></td>
		</tr>
		</tbody></table>

	<div class="form-actions">
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存">&nbsp;
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