<!DOCTYPE html>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<base href="<%=basePath%>">
<!-- saved from url=(0062)http://localhost:8080/jeesite/a/company/system/systemUser/form -->
<html style="overflow-x:auto;overflow-y:auto;"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>用户信息管理 - Powered By JeeSite</title>
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
        var busername=false;
        var bpassword = false;
        var bphone = false;
        var bemail = false;
		$(function () {
            /*从数字字典中获取用户状态*/
			$.post(
			    "datadict/selectByName",
				{"name":"用户状态"},
				function (json) {
                    var str="";
                    for(var i=0;i<json.length;i++){
                        str +="<option value='"+json[i].value+"'>"+json[i].label+"</option>"
                    }
                    $("#status").html(str);
                },
                "json"
			);

			/*用户名唯一以及正则验证*/
			$("#username").blur(function () {
				var username = $(this).val();
				var res = /^[A-Za-z0-9]{5,10}$/;
                busername= res.test(username);
                if(busername){	//正则通过
                    $.post(
                        "user-manager/isOneUsername",
						{"username":username},
						function (json) {
							if(json){		//用户名唯一，可通过
							    $("#userIsOne").html("<font color='green' size='6'>√</font>");
							    busername=true;
							}else {
                                busername=false;
							    $("#userIsOne").html("<font color='red'>用户名已被使用，请重新输入！！</font>")
							}
                        },
						"json"
					)
				}else{
                    $("#userIsOne").html("<font color='red'>用户名格式错误！！</font>")
				}

            });

			/*密码验证*/
            $("#password").blur(function () {
                var password=$("#password").val();
                var re=/^[A-Za-z0-9]{6,7}$/;
                bpassword=re.test(password);
                if(bpassword){
                    $("#pwd").html("<font color='green' size='6'>√</font>")
                }else {
                    $("#pwd").html("<font color='red' size='6'>×</font>")
                }
            });

            /*手机号码正则以及唯一验证*/
			$("#phone").blur(function () {
			    var phone = $(this).val();
			    var res = /^1(3|4|5|6|7|8|9)\d{9}$/;
			    bphone = res.test(phone);
			    if(bphone){		//正则验证通过
			        $.post(
			            "user-manager/isOneUserPhone",
						{"phone":phone},
						function (json) {
			                if(json){	//手机号码唯一，可通过
                                $("#phoneIsOne").html("<font color='green' size='6'>√</font>");
			                    bphone=true;
							}else{		//手机号码不唯一，不可通过
                                bphone=false;
                                $("#phoneIsOne").html("<font color='red' size='6'>×</font>");
							}
                        },
						"json"
					)
				}else {
			        $("#phoneIsOne").html("<font color='red' size='6'>×</font>");
				}
            });

			/*电子邮件正则及唯一验证*/
			$("#email").blur(function () {
			    var email = $(this).val();
			    var res = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
			    bemail = res.test(email);
			    if(bemail){		//正则验证通过
			        $.post(
			            "user-manager/isOneUserEmail",
						{"email":email},
						function (json) {
							if(json){	//电子邮件唯一，可通过
							    bemail=true;
                                $("#emailIsOne").html("<font color='green' size='6'>√</font>");
							}else{
							    bemail=false;
                                $("#emailIsOne").html("<font color='red' size='6'>×</font>");
							}
                        },
						"json"
					)

				}else{
                    $("#emailIsOne").html("<font color='red' size='6'>×</font>");
				}
            });

        });
        function sub() {
            return busername && bpassword && bphone && bemail;
        }



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
	<li><a href="user-manager/selectSystemUserByDuo">用户信息列表</a></li>
	<li class="active"><a href="user-manager/userAddJsp">用户信息添加</a></li>
</ul><br>
<form id="inputForm" class="form-horizontal" action="user-manager/addSystemUser" method="post" onsubmit="return sub()">
	<input id="id" name="id" type="hidden" value="">
	<input type="hidden" name="createBy" value="${sessionScope.tbSystemUser.id}">
	<script type="text/javascript">top.$.jBox.closeTip();</script>

		<div class="control-group">
			<label class="control-label">用户名称：</label>
			<div class="controls">
				<input id="username" name="username" placeholder="由5-10个数字或字母组成" class="input-xlarge required" type="text" value="" maxlength="50">
				<span class="help-inline" id="userIsOne"><font color="red">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户密码：</label>
			<div class="controls">
				<input id="password" name="password" placeholder="由6-7个数字或字母组成" class="input-xlarge required" type="password" value="" maxlength="50">
				<span class="help-inline" id="pwd"><font color="red">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">电子邮件：</label>
			<div class="controls">
				<input id="email" name="email" class="input-xlarge " type="text" value="" maxlength="50">
				<span class="help-inline" id="emailIsOne"><font color="red">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">手机号码：</label>
			<div class="controls">
				<input id="phone" name="phone" class="input-xlarge " type="text" value="" maxlength="13">
				<span class="help-inline" id="phoneIsOne"><font color="red">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">排序：</label>
			<div class="controls">
				<input id="sortnum" name="sortnum" class="input-xlarge " type="text" value="" maxlength="11">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
				<div class="select2-container input-xlarge" id="s2id_status">
					<select id="status" name="status"  tabindex="-1">

					</select>
				</div>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户角色:</label>
			<div class="controls">
				<span><input id="userRoleList1" name="userRoleId"  class="required"   type="radio" value="1">
					<label for="userRoleList1">系统管理员</label>
				</span>
				<span><input id="userRoleList2" name="userRoleId"  class="required" type="radio" value="2">
					<label for="userRoleList2">高管</label>
				</span>
				<span><input id="userRoleList3" name="userRoleId" class="required" type="radio" value="3">
					<label for="userRoleList3">业务经理</label>
				</span>
				<span><input id="userRoleList4" name="userRoleId" class="required" type="radio" value="4">
					<label for="userRoleList4">业务人员</label>
				</span>
				<input type="hidden" name="_userRoleList" value="on">
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<input id="usernote" name="userNote" class="input-xlarge " type="text" value="" maxlength="255">
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
        top.$.jBox('get:/jeesite/a/sys/menu/treeselect;JSESSIONID=ff9a71c594b14065828ca71d9cf465d6', {title:'选择菜单', buttons:{'关闭':true}, width:300, height: 350, top:10});
        //if ($("#menuContent").html()==""){$.get("/jeesite/a/sys/menu/treeselect", function(data){$("#menuContent").html(data);});}else{$("#menuContent").toggle(100);}
    });
}//-->
</script>

</body></html>