<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<base href="<%=basePath%>">
<!DOCTYPE html>
<!-- saved from url=(0050)http://localhost:8080/jeesite/a/sys/user/modifyPwd -->
<html style="overflow-x:auto;overflow-y:auto;"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>修改密码 - Powered By JeeSite</title>
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
	<script src="js/static/MD5/md5.js" type="text/javascript"></script>
	<script type="text/javascript">
        var bpassword = false;
        var bnewpassword = false;
        var bconpassword = false;
		$(function () {
			$("#oldPassword").blur(function () {
				var password =$(oldPassword).val();
				password = hex_md5(password);
				if(password=="${tbSystemUser.password}"){
                    bpassword = true;
                    $("#oldpwd").html("<font color='green' size='6'>√</font>")
                }else {
                    $("#oldpwd").html("密码输入错误")
                }
            });

			/*密码正则验证*/
			$("#newPassword").blur(function () {
                var newpassword=$("#newPassword").val();
                var re=/^[A-Za-z0-9]{6,7}$/;
                bnewpassword=re.test(newpassword);
                if(bnewpassword){
                    bnewpassword=true;
                    $("#newpwd").html("<font color='green' size='6'>√</font>")
                }else {
                    bnewpassword=false;
                    $("#newpwd").html("<font color='red' size='6'>×</font>")
                }
            });


            $("#confirmNewPassword").blur(function () {
                var newpassword=$("#newPassword").val();
                var connewpassword=$("#confirmNewPassword").val();
                if(newpassword==connewpassword){
                    bconpassword=true;
                    $("#connewpwd").html("<font color='green' size='6'>√</font>")
				}else{
                    bconpassword=false;
                    $("#connewpwd").html("请与新密码保持一致!");
				}

            });


        });

		function tj(){
		    return bpassword && bnewpassword && bconpassword;
		}

        $(document).ready(function() {
            $("#oldPassword").focus();
            $("#inputForm").validate({
                rules: {
                },
                messages: {
                    confirmNewPassword: {equalTo: "输入与上面相同的密码"}
                },
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
	<li><a href="personal-information/userPersonalInformation">个人信息</a></li>
	<li class="active"><a href="personal-information/updateUserPassword">修改密码</a></li>
</ul><br>
<form id="inputForm" class="form-horizontal" action="personal-information/updatePersonalPassword" onsubmit="return tj()" method="post" novalidate="novalidate">
	<script type="text/javascript">top.$.jBox.closeTip();</script>

	<input type="hidden" name="id" value="${tbSystemUser.id}">
	<div class="control-group">
		<label class="control-label">旧密码:</label>
		<div class="controls">
			<input id="oldPassword" name="oldPassword" type="password" value="" maxlength="50" minlength="3" class="required">
			<span class="help-inline" id="oldpwd"><font color="red">*</font> </span>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">新密码:</label>
		<div class="controls">
			<input id="newPassword" name="newPassword" placeholder="由6-7个数字或字母组成"  type="password" value="" maxlength="50" minlength="3" class="required">
			<span class="help-inline" id="newpwd"><font color="red">*</font> </span>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">确认新密码:</label>
		<div class="controls">
			<input id="confirmNewPassword" placeholder="由6-7个数字或字母组成"  name="confirmNewPassword" type="password" value="" maxlength="50" minlength="3" class="required" equalto="#newPassword">
			<span class="help-inline" id="connewpwd"><font color="red">*</font> </span>
		</div>
	</div>
	<div class="form-actions">
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存">
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