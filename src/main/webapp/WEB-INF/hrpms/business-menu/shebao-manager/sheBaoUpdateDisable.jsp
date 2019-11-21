<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<base href="<%=basePath%>">
<!DOCTYPE html>
<!-- saved from url=(0072)http://localhost:8080/jeesite-master/a/company/socialInsurance/form?id=1 -->
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
        function getCustomerByName(){
            var customer_name = document.getElementById('customer_name').value;
            alert(customer_name);
        }
	</script>

</head>
<body>

<ul class="nav nav-tabs">
	<li><a href="../shebaoList/saved_resource.html">社保信息列表</a></li>
	<li class="active"><a href="saved_resource_unEdit.html">社保信息修改</a></li>
</ul><br>
<form id="inputForm" class="form-horizontal" action="#" method="post" novalidate="novalidate">
	<input id="id" name="id" type="hidden" value="1">















	<script type="text/javascript">top.$.jBox.closeTip();</script>

	<table>
		<tbody><tr>
			<td><label class="control-label">客户名称：</label></td>
			<td>
				<input type="text" id="customer_name" name="customerName" value="" onblur="getCustomerByName();" style="width:270px;" disabled="disabled">
			</td>
			<td><label class="control-label">身份证号：</label></td>
			<td>
				<input id="idcard" name="idcard" class="input-xlarge required" type="text" value="412724180000001511" maxlength="20" disabled="disabled">
				<span class="help-inline"><font color="red">*</font> </span>
			</td>
		</tr>
		<tr>
			<td><label class="control-label">客户性别：</label></td>
			<td>

				<input type="radio" name="sex" value="" disabled="disabled">男
				<input type="radio" name="sex" value="" disabled="disabled">女
			</td>
			<td><label class="control-label">出生年月：</label></td>
			<td>
				<input name="birthday" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate " value="" onclick="WdatePicker({dateFmt:&#39;yyyy-MM-dd HH:mm:ss&#39;,isShowClear:false}); "disabled="disabled">
			</td>
		</tr>
		<tr>
			<td><label class="control-label">手机号码：</label></td>
			<td><input type="text" name="phone" maxlength="13" class="input-xlarge " disabled="disabled"></td>
			<td><label class="control-label">电子邮件：</label></td>
			<td><input type="text" name="email" maxlength="50" class="input-xlarge " disabled="disabled"></td>
		</tr>
		<tr>
			<td><label class="control-label">现在住址：</label></td>
			<td><input type="text" name="address" maxlength="100" class="input-xlarge " disabled="disabled"></td>
			<td><label class="control-label">邮政编码：</label></td>
			<td><input type="text" name="zipcode" maxlength="20" class="input-xlarge " disabled="disabled"></td>
		</tr>

		<tr>
			<td><label class="control-label">毕业学校：</label></td>
			<td><input type="text" name="school" maxlength="20" class="input-xlarge " disabled="disabled"></td>
			<td><label class="control-label">所学专业：</label></td>
			<td><input type="text" name="specialty" maxlength="20" class="input-xlarge " disabled="disabled"></td>
		</tr>

		<tr>
			<td><label class="control-label">毕业时间：</label></td>
			<td><input type="text" name="graduation" maxlength="20" class="input-xlarge " disabled="disabled"></td>
			<td><label class="control-label">所属公司：</label></td>
			<td>

				<select name="companyid" class="input-xlarge" disabled="disabled">
					<option value="">
					</option><option value="">智递科技有限公司</option>
				</select>
			</td>
		</tr>


		<tr>
			<td><label class="control-label">社保卡号：</label></td>
			<td><input id="sbcard" name="sbcard" class="input-xlarge " type="text" value="1000047" maxlength="20" disabled="disabled"></td>
			<td><label class="control-label">缴费基数：</label></td>
			<td><input id="basepay" name="basepay" class="input-xlarge " type="text" value="5000" disabled="disabled"></td>
		</tr>

		<tr>
			<td><label class="control-label">应缴金额：</label></td>
			<td><input id="mustpay" name="mustpay" class="input-xlarge " type="text" value="870" disabled="disabled"></td>
			<td><label class="control-label">个人比率：</label></td>
			<td><input id="personratio" name="personratio" class="input-xlarge " type="text" value="20%" maxlength="5" disabled="disabled"></td>
		</tr>

		<tr>
			<td><label class="control-label">单位比率：</label></td>
			<td><input id="companyratio" name="companyratio" class="input-xlarge " type="text" value="80%" maxlength="5" disabled="disabled"></td>
			<td><label class="control-label">养老保险：</label></td>
			<td><input id="yanglao" name="yanglao" class="input-xlarge " type="text" value="200" disabled="disabled"></td>
		</tr>
		<tr>
			<td><label class="control-label">医疗保险：</label></td>
			<td><input id="yiliao" name="yiliao" class="input-xlarge " type="text" value="200" disabled="disabled"></td>
			<td><label class="control-label">工伤保险：</label></td>
			<td><input id="gongshang" name="gongshang" class="input-xlarge " type="text" value="200" disabled="disabled"></td>
		</tr>

		<tr>
			<td><label class="control-label">失业保险：</label></td>
			<td><input id="shiye" name="shiye" class="input-xlarge " type="text" value="100" disabled="disabled"></td>
			<td><label class="control-label">生育保险：</label></td>
			<td><input id="shengyu" name="shengyu" class="input-xlarge " type="text" value="70" disabled="disabled"></td>
		</tr>

		<tr>
			<td><label class="control-label">预交款日：</label></td>
			<td>
				<input name="paydate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate " value="2017-10-26 00:00:00" onclick="WdatePicker({dateFmt:&#39;yyyy-MM-dd HH:mm:ss&#39;,isShowClear:false});" disabled="disabled">
			</td>
			<td><label class="control-label">代理费用：</label></td>
			<td><input id="proxyfee" name="proxyfee" class="input-xlarge " type="text" value="75" disabled="disabled"></td>
		</tr>

		<tr>
			<td><label class="control-label">社保状态：</label></td>
			<td>
				<select name="status" class="input-xlarge" disabled="disabled">
					<option value="">正常
					</option><option value="">封存
				</option></select>
			</td>
			<td><label class="control-label">备注信息：</label></td>
			<td><input id="remark" name="remark" class="input-xlarge " type="text" value="test" maxlength="512" disabled="disabled"></td>
		</tr>
		</tbody></table>

	<!-- 		<div class="control-group"> -->
	<!-- 			<label class="control-label">创建者：</label> -->
	<!-- 			<div class="controls"> -->

	<!-- 			</div> -->
	<!-- 		</div> -->
	<!-- 		<div class="control-group"> -->
	<!-- 			<label class="control-label">创建时间：</label> -->
	<!-- 			<div class="controls"> -->
	<!-- 				<input name="createtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate " -->

	<!-- 					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> -->
	<!-- 			</div> -->
	<!-- 		</div> -->
	<!-- 		<div class="control-group"> -->
	<!-- 			<label class="control-label">更新者：</label> -->
	<!-- 			<div class="controls"> -->

	<!-- 			</div> -->
	<!-- 		</div> -->
	<!-- 		<div class="control-group"> -->
	<!-- 			<label class="control-label">更新时间：</label> -->
	<!-- 			<div class="controls"> -->
	<!-- 				<input name="updatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate " -->

	<!-- 					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> -->
	<!-- 			</div> -->
	<!-- 		</div> -->
	<div class="form-actions">
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存" disabled="disabled">&nbsp;
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