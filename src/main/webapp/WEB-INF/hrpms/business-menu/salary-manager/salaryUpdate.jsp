<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<base href="<%=basePath%>">
<!DOCTYPE html>
<!-- saved from url=(0070)http://localhost:8080/jeesite-master/a/company/salaryInfo/form?id=1000 -->
<html style="overflow-x:auto;overflow-y:auto;"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>工资管理 - Powered By JeeSite</title>
	<meta name="author" content="http://jeesite.com/">
	<meta name="renderer" content="webkit"><meta http-equiv="X-UA-Compatible" content="IE=8,IE=9,IE=10">
	<meta http-equiv="Expires" content="0"><meta http-equiv="Cache-Control" content="no-cache"><meta http-equiv="Cache-Control" content="no-store">
	<script src="./hm.js"></script><script src="./jquery-1.8.3.min.js" type="text/javascript"></script>
	<link href="./bootstrap.min.css" type="text/css" rel="stylesheet">
	<script src="./bootstrap.min.js" type="text/javascript"></script>
	<link href="./font-awesome.min.css" type="text/css" rel="stylesheet">
	<!--[if lte IE 7]><link href="/jeesite-master/static/bootstrap/2.3.1/awesome/font-awesome-ie7.min.css" type="text/css" rel="stylesheet" /><![endif]-->
	<!--[if lte IE 6]><link href="/jeesite-master/static/bootstrap/bsie/css/bootstrap-ie6.min.css" type="text/css" rel="stylesheet" />
	<script src="/jeesite-master/static/bootstrap/bsie/js/bootstrap-ie.min.js" type="text/javascript"></script><![endif]-->
	<link href="./select2.min.css" rel="stylesheet">
	<script src="./select2.min.js" type="text/javascript"></script>
	<link href="./jquery.validate.min.css" type="text/css" rel="stylesheet">
	<script src="./jquery.validate.min.js" type="text/javascript"></script>
	<link href="./jbox.min.css" rel="stylesheet">
	<script src="./jquery.jBox-2.3.min.js" type="text/javascript"></script>
	<script src="./WdatePicker.js" type="text/javascript"></script><link href="./WdatePicker.css" rel="stylesheet" type="text/css">
	<script src="./mustache.min.js" type="text/javascript"></script>
	<link href="./jeesite.css" type="text/css" rel="stylesheet">
	<script src="./jeesite.js" type="text/javascript"></script>
	<script type="text/javascript">var ctx = '/jeesite-master/a', ctxStatic='/jeesite-master/static';</script>
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
	<li><a href="http://localhost:8080/jeesite-master/a/company/salaryInfo/">工资列表</a></li>
	<li class="active"><a href="http://localhost:8080/jeesite-master/a/company/salaryInfo/form?id=1000">代发工资修改</a></li>
</ul><br>
<form id="inputForm" class="form-horizontal" action="http://localhost:8080/jeesite-master/a/company/salaryInfo/save" method="post" novalidate="novalidate">
	<input id="id" name="id" type="hidden" value="1000">















	<script type="text/javascript">top.$.jBox.closeTip();</script>


	<table class="table table-bordered table-condensed">
		<tbody><tr>
			<td><label class="control-label">客户名称：</label></td>
			<td>
				<input id="idcard" name="idcard" class="input-xlarge required" type="text" value="412724180000001511" maxlength="20">
			</td>
			<td><label class="control-label">身份证号：</label></td>
			<td><input id="idcard" name="idcard" class="input-xlarge required" type="text" value="412724180000001511" maxlength="20">
				<span class="help-inline"><font color="red">*</font> </span>
			</td>
		</tr>

		<tr>
			<td><label class="control-label">银行卡号：</label></td>
			<td>
				<input id="paycard" name="paycard" class="input-xlarge " type="text" value="62258837171508232" maxlength="20">
			</td>
			<td><label class="control-label">基本工资：</label></td>
			<td>
				<input id="basesalary" name="basesalary" class="input-xlarge " type="text" value="8000">
			</td>
		</tr>
		<tr>
			<td><label class="control-label">奖金：</label></td>
			<td><input id="bonuspay" name="bonuspay" class="input-xlarge " type="text" value="1000"></td>
			<td><label class="control-label">加班费：</label></td>
			<td><input id="overtimepay" name="overtimepay" class="input-xlarge " type="text" value="1000"></td>
		</tr>
		<tr>
			<td><label class="control-label">社保扣费：</label></td>
			<td><input id="shebaopay" name="shebaopay" class="input-xlarge " type="text" value="870"></td>
			<td><label class="control-label">公积金扣费：</label></td>
			<td><input id="gongjijinpay" name="gongjijinpay" class="input-xlarge " type="text" value="500"></td>
		</tr>

		<tr>
			<td><label class="control-label">应交税款：</label></td>
			<td><input id="taxpay" name="taxpay" class="input-xlarge " type="text" value="130"></td>
			<td><label class="control-label">应发工资：</label></td>
			<td>
				<input id="totalpay" name="totalpay" class="input-xlarge required" type="text" value="10000">
				<span class="help-inline"><font color="red">*</font> </span>
			</td>
		</tr>

		<tr>
			<td><label class="control-label">实发工资：</label></td>
			<td>
				<input id="mustpay" name="mustpay" class="input-xlarge required" type="text" value="8500">
				<span class="help-inline"><font color="red">*</font> </span>
			</td>
			<td><label class="control-label">代理费用：</label></td>
			<td>
				<input id="proxyfee" name="proxyfee" class="input-xlarge " type="text" value="75">
			</td>
		</tr>

		<tr>
			<td><label class="control-label">状态：</label></td>
			<td>
				<div class="select2-container input-xlarge" id="s2id_autogen1"><a href="javascript:void(0)" onclick="return false;" class="select2-choice" tabindex="-1">   <span class="select2-chosen">已发
						</span><abbr class="select2-search-choice-close"></abbr>   <span class="select2-arrow"><b></b></span></a><input class="select2-focusser select2-offscreen" type="text" id="s2id_autogen2"><div class="select2-drop select2-display-none select2-with-searchbox">   <div class="select2-search">       <input type="text" autocomplete="off" autocorrect="off" autocapitalize="off" spellcheck="false" class="select2-input">   </div>   <ul class="select2-results">   </ul></div></div><select name="status" class="input-xlarge  select2-offscreen" tabindex="-1">
				<option value="">已发
				</option><option value="">未发
			</option></select>
			</td>

			<td><label class="control-label">支付日期：</label></td>
			<td>
				<input name="paydate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate " value="2017-10-01" onclick="WdatePicker({dateFmt:&#39;yyyy-MM-dd&#39;,isShowClear:false});">
			</td>
		</tr>
		<tr>
			<td><label class="control-label">备注：</label></td>
			<td colspan="3"><input id="remark" name="remark" class="input-xlarge " type="text" value="test" maxlength="256"></td>
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
        top.$.jBox('get:/jeesite-master/a/sys/menu/treeselect;JSESSIONID=b30d2fa0f4264f6288e0788088ab4b22', {title:'选择菜单', buttons:{'关闭':true}, width:300, height: 350, top:10});
        //if ($("#menuContent").html()==""){$.get("/jeesite-master/a/sys/menu/treeselect", function(data){$("#menuContent").html(data);});}else{$("#menuContent").toggle(100);}
    });
}//-->
</script>

</body></html>