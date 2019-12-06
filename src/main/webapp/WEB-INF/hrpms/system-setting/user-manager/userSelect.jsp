<!DOCTYPE html>
<!-- saved from url=(0077)http://localhost:8080/jeesite-master/a/company/system/systemUser/form?id=1000 -->
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<base href="<%=basePath%>">
<html style="overflow-x:auto;overflow-y:auto;"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>用户信息管理 - Powered By JeeSite</title>
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
		$(function () {
            //获取所有角色名称
            $.post(
                "role-manager/selectAllRoleName",
                function (json) {
                    var str = "";
                    var userrole ="${userRole}".split(",");
                    for(var i=0;i<json.length;i++){
                        for(var j=0;j<userrole.length;j++){
                            if(json[i].id==userrole[j]){
                                str += json[i].roleName+" ";
                            }
                        }
                    }
                    $("#userRole").append(str);
                },
                "json"
            );

        });
	</script>
</head>
<body>

<ul class="nav nav-tabs">
	<li><a href="user-manager/selectSystemUserByDuo">用户信息列表</a></li>
	<li class="active"><a href="user-manager/selectSystemUserById?id=${systemUser.id}&flag=1">用户信息详情</a></li>
</ul><br>
	<input id="id" name="id" type="hidden" value="1000">
	<script type="text/javascript">top.$.jBox.closeTip();</script>

<label class="control-label">用户编号：${systemUser.id}</label>
</div>

<div class="control-group">
	<label class="control-label">用户名称：${systemUser.username}</label>
</div>

<div class="control-group">
	<label class="control-label">电子邮件：${systemUser.email}</label>
</div>
<div class="control-group">
	<label class="control-label">手机号码：${systemUser.phone}</label>
</div>
<div class="control-group">
	<label class="control-label">排序：${systemUser.sortnum}</label>
</div>
<div class="control-group">
	<label class="control-label">状态：
		<c:if test="${systemUser.status=='0'}" var="bo">
			正常
		</c:if>
		<c:if test="${!bo}">
			删除
		</c:if>
	</label>
</div>
<div class="control-group">
    <label class="control-label" id="userRole">用户角色: </label>

</div>
<div class="control-group">
	<label class="control-label">备注信息：${systemUser.userNote}</label>
</div>
	<div class="form-actions">
		<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)">
	</div>

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