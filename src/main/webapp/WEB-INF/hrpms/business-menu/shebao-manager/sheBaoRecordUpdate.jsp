<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<base href="<%=basePath%>">
<!DOCTYPE html>
<!-- saved from url=(0078)http://localhost:8080/jeesite-master/a/company/socialInsuranceRecord/form?id=2 -->
<html style="overflow-x:auto;overflow-y:auto;"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>社保缴费管理 - Powered By JeeSite</title>
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

	<script src="js/static/verify/SheBaoRecordUpdate.js"></script>
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
	<li><a href="shebao/shebaoRecordList">社保缴费列表</a></li>
	<li class="active"><a href="shebao/shebaoPayToUpdate?id=${map.shebaoRecord.id}&nameQuery=${socialIncuranctOperation.nameQuery}&idCardQuery=${socialIncuranctOperation.idCardQuery}&sbCardQuery=${socialIncuranctOperation.sbCardQuery}&currentPage=${currentPage}">社保缴费修改</a></li>
</ul><br>
<form id="inputForm" class="form-horizontal" action="shebao/shebaoRecordUpdate" method="post" novalidate="novalidate">
	<input type="hidden" name="nameQuery" value="${socialIncuranctOperation.nameQuery}">
	<input type="hidden" name="idCardQuery" value="${socialIncuranctOperation.idCardQuery}">
	<input type="hidden" name="sbCardQuery" value="${socialIncuranctOperation.sbCardQuery}">
	<input type="hidden" name="currentPage" value="${currentPage}">

	<input type="hidden" name="id" value="${map.shebaoRecord.id}">

	<script type="text/javascript">top.$.jBox.closeTip();</script>

	<table class="table table-bordered table-condensed">
		<tbody><tr>
			<td><label class="control-label">客户名称：</label></td>
			<td>
				<input type="text" id="customer_name" name="customerName" value="${map.shebaoRecord.name}" disabled onblur="getCustomerByName();" style="width:270px;">
			</td>
			<td><label class="control-label">身份证号：</label></td>
			<td><input type="text" name="idcard" htmlescape="false" value="${map.shebaoRecord.idCard}" disabled maxlength="20" class="input-xlarge " style="width:270px;">
			</td>
		</tr>

		<tr>
			<td><label class="control-label">社保号码：</label></td>
			<td>
				<input id="sdcard" name="sdcard" class="input-xlarge required" disabled type="text" value="${map.shebaoRecord.sbCard}" maxlength="20">
			</td>
			<td>
				<label class="control-label">所属公司：</label>
			</td>
			<td><input type="text" value="${map.company.name}" class="input-xlarge required" disabled style="width:270px;"></td>
		</tr>
		<tr>
			<td><label class="control-label">手机号码：</label></td>
			<td><input type="text" name="phone" htmlescape="true" value="${map.customer.phone}" disabled maxlength="13" style="width:270px;"></td>
			<td><label class="control-label">现在住址：</label></td>
			<td><input type="text" name="address" htmlescape="true" value="${map.customer.address}" disabled maxlength="100" style="width:270px;"></td>
		</tr>
		<tr>
			<td><label class="control-label">养老保险：</label></td>
			<td><input id="yanglao" name="yanglao" class="input-xlarge " type="text" disabled value="${map.shebaoRecord.yangLao}"></td>
			<td><label class="control-label">医疗保险：</label></td>
			<td><input id="yiliao" name="yiliao" class="input-xlarge " type="text" disabled value="${map.shebaoRecord.yiLiao}"></td>
		</tr>
		<tr>

			<td><label class="control-label">工伤保险：</label></td>
			<td><input id="gongshang" name="gongshang" class="input-xlarge " type="text" disabled value="${map.shebaoRecord.gongShang}"></td>
			<td><label class="control-label">失业保险：</label></td>
			<td><input id="shiye" name="shiye" class="input-xlarge " type="text" disabled value="${map.shebaoRecord.shiYe}"></td>
		</tr>

		<tr>
			<td><label class="control-label">生育保险：</label></td>
			<td><input id="shengyu" name="shengyu" class="input-xlarge " type="text" disabled value="${map.shebaoRecord.shengYu}"></td>
			<td><label class="control-label">缴费金额：</label></td>
			<td>
				<input id="paymoney" name="paymoney" class="input-xlarge " type="text" disabled value="${map.shebaoRecord.payMoney}">
			</td>
		</tr>

		<tr>
			<td><label class="control-label">缴费期间：</label></td>
			<td>
				<input id="payMonth" name="payMonth" class="input-xlarge " type="date" value="${map.shebaoRecord.payMonth}" maxlength="20">
				<span class="help-inline"><font color="red">*</font> </span>
			</td>
			<td><label class="control-label">缴费状态：</label></td>
			<td>
				<select name="status"  class="select2-offscreen">
					<c:forEach items="${payStatuss}" var="status">
						<c:if test="${map.shebaoRecord.status == status.value}">
							<option value="${status.value}" selected>${status.label}</option>
						</c:if>
						<c:if test="${map.shebaoRecord.status != status.value}">
							<option value="${status.value}">${status.label}</option>
						</c:if>
					</c:forEach>
				</select>
			</td>
		</tr>

		<tr>
			<td><label class="control-label">备注信息：</label></td>
			<td colspan="3">
				<input id="remark" name="remark" class="input-xlarge " type="text" value="${map.shebaoRecord.remark}">
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