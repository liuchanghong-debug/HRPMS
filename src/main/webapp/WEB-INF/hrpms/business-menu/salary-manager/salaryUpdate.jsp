<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" /><meta name="author" content="http://jeesite.com/"/>
	<meta name="renderer" content="webkit"><meta http-equiv="X-UA-Compatible" content="IE=8,IE=9,IE=10" />
	<meta http-equiv="Expires" content="0"><meta http-equiv="Cache-Control" content="no-cache"><meta http-equiv="Cache-Control" content="no-store">

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
	<script src="js/static/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
	<script type="text/javascript">
        $(function () {
            var baseSalary =0;
            var bonus = 0;
            var shebao = 0;
            var overtimepay = 0;
            var gongjijin = 0;
            var taxpay = 0;
            var proxy =0;
            $("#basesalary").blur(function () {
                baseSalary= parseFloat($("#basesalary").val());
                shebao=baseSalary*0.102;
                $("#shebaopay").val(shebao);
                gongjijin=baseSalary*0.08;
                $("#gongjijinpay").val(gongjijin);
                var salary=baseSalary-baseSalary*0.102-5000.0;
                if(salary>0){
                    taxpay=salary*0.03;
                    $("#taxpay").val(taxpay)
                }else {
                    taxpay=0;
                    $("#taxpay").val(taxpay);
                }

            });

            $("#bonuspay").blur(function () {
                bonus= parseFloat($(this).val());
                $("#totalpay").val(baseSalary+bonus+overtimepay);
                $("#mustpay").val(baseSalary+bonus+overtimepay-shebao-gongjijin-taxpay-proxy);
            });

            $("#overtimepay").blur(function () {
                overtimepay= parseFloat($(this).val());
                $("#totalpay").val(baseSalary+bonus+overtimepay);
                $("#mustpay").val(baseSalary+bonus+overtimepay-shebao-gongjijin-taxpay-proxy);
            });

            $("#proxyfee").blur(function () {
                proxy= parseFloat($(this).val());
                $("#totalpay").val(baseSalary+bonus+overtimepay);
                $("#mustpay").val(baseSalary+bonus+overtimepay-shebao-gongjijin-taxpay-proxy);
            });
        });



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
	<li><a href="salary-manager/selectSalaryByDuo?flag=1">工资列表</a></li>
	<li class="active"><a href="salary-manager/selectSalaryById?flag=2&id=${tbSalary.id}">代发工资修改</a></li>
</ul><br>
<form id="inputForm" class="form-horizontal" action="salary-manager/updateSalaryById" method="post" novalidate="novalidate">

	<script type="text/javascript">top.$.jBox.closeTip();</script>

	<table class="table table-bordered table-condensed">
		<tbody><tr>
			<input id="id" name="id" type="hidden" value="${tbSalary.id}">
			<input name="updateBy" type="hidden" value="${sessionScope.tbSystemUser.id}">
			<input type="hidden" name="createBy" value="${tbSalary.createBy}">
			<input type="hidden" name="createTime" value="<fmt:formatDate value="${tbSalary.createTime}" pattern="yyyy-MM-dd HH:mm:ss.SSS"/> ">
			<td><label class="control-label">客户名称：</label></td>
			<td>
				<input id="name" name="name" class="input-xlarge required" type="text" value="${tbSalary.name}" readonly maxlength="20">
			</td>
			<td><label class="control-label">身份证号：</label></td>
			<td><input id="idcard" name="idCard" class="input-xlarge required" type="text" value="${tbSalary.idCard}" readonly maxlength="20">
			</td>
		</tr>

		<tr>
			<td><label class="control-label">银行卡号：</label></td>
			<td>
				<input id="paycard" name="payCard" class="input-xlarge " type="text" value="${tbSalary.payCard}" maxlength="20">
			</td>
			<td><label class="control-label">基本工资：</label></td>
			<td>
				<input id="basesalary" name="baseSalary" class="input-xlarge " type="text" value="${tbSalary.baseSalary}">
			</td>
		</tr>
		<tr>
			<td><label class="control-label">奖金：</label></td>
			<td><input id="bonuspay" name="bonusPay" class="input-xlarge " type="text" value="${tbSalary.bonusPay}"></td>
			<td><label class="control-label">加班费：</label></td>
			<td><input id="overtimepay" name="overTimePay" class="input-xlarge " type="text" value="${tbSalary.overTimePay}"></td>
		</tr>
		<tr>
			<td><label class="control-label">社保扣费：</label></td>
			<td><input id="shebaopay" name="sheBaoPay" class="input-xlarge " type="text" value="${tbSalary.sheBaoPay}"></td>
			<td><label class="control-label">公积金扣费：</label></td>
			<td><input id="gongjijinpay" name="gongJiJinPay" class="input-xlarge " type="text" value="${tbSalary.gongJiJinPay}"></td>
		</tr>

		<tr>
			<td><label class="control-label">应交税款：</label></td>
			<td><input id="taxpay" name="taxPay" class="input-xlarge " type="text" value="${tbSalary.taxPay}"></td>
			<td><label class="control-label">应发工资：</label></td>
			<td>
				<input id="totalpay" name="totalPay" class="input-xlarge required" type="text" value="${tbSalary.totalPay}">
			</td>
		</tr>

		<tr>
			<td><label class="control-label">实发工资：</label></td>
			<td>
				<input id="mustpay" name="mustPay" class="input-xlarge required" type="text" value="${tbSalary.mustPay}">
			</td>
			<td><label class="control-label">代理费用：</label></td>
			<td>
				<input id="proxyfee" name="proxyFee" class="input-xlarge " type="text" value="${tbSalary.proxyFee}">
			</td>
		</tr>

		<tr>
			<td><label class="control-label">状态：</label></td>
			<td>
				<select name="status" class="input-xlarge" tabindex="-1">
					<c:if test="${tbSalary.status=='0'}" var="bo">
						<option value="0" selected>已发</option>
						<option value="1">未发</option>
					</c:if>
					<c:if test="${!bo}">
						<option value="0">已发</option>
						<option value="1" selected>未发</option>
					</c:if>
				</select>
			</td>

			<td><label class="control-label">支付日期：</label></td>
			<td>
				<fmt:formatDate value="${tbSalary.payDate}" var="paydate" pattern="yyyy-MM-dd"/>
				<input id="paydate" name="payDate" type="date"  maxlength="20" class="input-medium Wdate " value="${paydate}">
			</td>
		</tr>
		<tr>
			<td><label class="control-label">备注：</label></td>
			<td colspan="3"><input id="remark" name="remark" class="input-xlarge " type="text" value="${tbSalary.remark}" maxlength="256"></td>
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