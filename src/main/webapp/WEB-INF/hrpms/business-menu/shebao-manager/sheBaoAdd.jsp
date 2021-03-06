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
<!-- saved from url=(0060)http://localhost:8080/jeesite/a/company/socialInsurance/form -->
<html style="overflow-x:auto;overflow-y:auto;"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>社保信息管理 - Powered By JeeSite</title>
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
	<script src="js/static/verify/SheBaoAdd.js"></script>
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
	<li><a href="shebao/shebaoList">社保信息列表</a></li>
	<li class="active"><a href="shebao/shebaoToAdd">社保信息添加</a></li>
</ul><br>
<form id="inputForm" class="form-horizontal" action="shebao/shebaoAdd" method="post" novalidate="novalidate">
	<script type="text/javascript">top.$.jBox.closeTip();</script>
	<table>
		<tbody><tr>
			<td><label class="control-label">客户名称：</label></td>
			<td>
				<select name="customerId" id="customerId" class="input-xlarge  select2-offscreen" onchange="getDetailOfCustomer(this.value)" tabindex="-1">
					<option value="" selected>请选择</option>
				</select>
				<input type="hidden" name="name" id="name">
			</td>
			<td><label class="control-label">身份证号：</label></td>
			<td>
				<input id="idCard" name="idCard" class="input-xlarge required" readonly type="text" maxlength="20">
			</td>
		</tr>
		<tr>
			<td><label class="control-label">客户性别：</label></td>
			<td id="sex">
				<input type="radio" name="sex"  value="男" disabled>男
				<input type="radio" name="sex" value="女" disabled>女
			</td>
			<td><label class="control-label">出生年月：</label></td>
			<td>
				<input id="birthday" name="birthday" type="text" readonly="readonly" maxlength="20">
			</td>
		</tr>
		<tr>
			<td><label class="control-label">手机号码：</label></td>
			<td><input id="phone" type="text" name="phone" maxlength="13" readonly class="input-xlarge "></td>
			<td><label class="control-label">电子邮件：</label></td>
			<td><input id="email" type="text" name="email" maxlength="50" readonly class="input-xlarge "></td>
		</tr>
		<tr>
			<td><label class="control-label">现在住址：</label></td>
			<td><input id="address" type="text" name="address" maxlength="100" readonly class="input-xlarge "></td>
			<td><label class="control-label">邮政编码：</label></td>
			<td><input id="zipCode" type="text" name="zipCode" maxlength="20" readonly class="input-xlarge "></td>
		</tr>
		<tr>
			<td><label class="control-label">毕业学校：</label></td>
			<td><input id="school" type="text" name="school" maxlength="20" readonly class="input-xlarge "></td>
			<td><label class="control-label">所学专业：</label></td>
			<td><input id="specialty" type="text" name="specialty" maxlength="20" readonly class="input-xlarge "></td>
		</tr>

		<tr>
			<td><label class="control-label">毕业时间：</label></td>
			<td><input id="graduation" type="text" name="graduation" maxlength="20" readonly class="input-xlarge "></td>
			<td><label class="control-label">所属公司：</label></td>
			<td>
				<input id="companyId" type="hidden" name="companyId"  readonly>
				<input id="companyName" type="text" name="companyName" maxlength="20" readonly class="input-xlarge ">
			</td>
		</tr>


		<tr>
			<td><label class="control-label">社保卡号：</label></td>
			<td><input id="sbCard" name="sbCard" class="input-xlarge " type="text"  maxlength="20"></td>
			<td><label class="control-label">缴费基数：</label></td>
			<td><input id="basePay" name="basePay" class="input-xlarge " readonly type="text" ></td>
		</tr>

		<tr>
			<td><label class="control-label">应缴金额：</label></td>
			<td><input id="mustPay" name="mustPay" class="input-xlarge " readonly type="text" ></td>
			<td><label class="control-label">个人比率：</label></td>
			<td><input id="personRatio" name="personRatio" class="input-xlarge" value="10.2%" readonly type="text"  maxlength="5"></td>
		</tr>

		<tr>
			<td><label class="control-label">单位比率：</label></td>
			<td><input id="companyRatio" name="companyRatio" class="input-xlarge" value="31.1%" readonly type="text"  maxlength="5"></td>
			<td><label class="control-label">养老保险：</label></td>
			<td><input id="yangLao" name="yangLao" class="input-xlarge " readonly type="text" ></td>
		</tr>
		<tr>
			<td><label class="control-label">医疗保险：</label></td>
			<td><input id="yiLiao" name="yiLiao" class="input-xlarge " readonly type="text" ></td>
			<td><label class="control-label">工伤保险：</label></td>
			<td><input id="gongShang" name="gongShang" class="input-xlarge " readonly type="text" ></td>
		</tr>

		<tr>
			<td><label class="control-label">失业保险：</label></td>
			<td><input id="shiYe" name="shiYe" class="input-xlarge " readonly type="text" ></td>
			<td><label class="control-label">生育保险：</label></td>
			<td><input id="shengYu" name="shengYu" class="input-xlarge " readonly type="text" ></td>
		</tr>

		<tr>
			<td><label class="control-label">预交款日：</label></td>
			<td>
				<input id="payDate" name="payDate" type="date" maxlength="20">
			</td>
			<td><label class="control-label">代理费用：</label></td>
			<td><input id="proxyFee" name="proxyFee" class="input-xlarge " value="80" readonly type="text" ></td>
		</tr>

		<tr>
			<td><label class="control-label">社保状态：</label></td>
			<td>
				<select name="status" class="input-xlarge  select2-offscreen" disabled tabindex="-1">
					<option>正常</option>
				</select>
			</td>
			<td><label class="control-label">备注信息：</label></td>
			<td><input id="remark" name="remark" class="input-xlarge " type="text"  maxlength="512"></td>
		</tr>
		</tbody>
	</table>
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