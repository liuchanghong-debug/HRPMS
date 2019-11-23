<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<base href="<%=basePath%>">
<!DOCTYPE html>
<!-- saved from url=(0045)http://localhost:8080/jeesite/a/sys/user/info -->
<html style="overflow-x:auto;overflow-y:auto;"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>个人信息 - Powered By JeeSite</title>
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
	<li class="active"><a href="personal-information/userPersonalInformation">个人信息</a></li>
	<li><a href="personal-information/updateUserPassword">修改密码</a></li>
</ul><br>

	<script type="text/javascript">top.$.jBox.closeTip();</script>

	<div class="control-group">
		<%--<label class="control-label">头像:</label>
		<div class="controls">
			<input id="nameImage" name="photo" maxlength="255" class="input-xlarge" type="hidden" value="">


			<ol id="nameImagePreview"><li style="list-style:none;padding-top:5px;">无</li></ol><a href="javascript:" onclick="nameImageFinderOpen();" class="btn">选择</a>&nbsp;<a href="javascript:" onclick="nameImageDelAll();" class="btn">清除</a>
			<script type="text/javascript">
                function nameImageFinderOpen(){//
                    var date = new Date(), year = date.getFullYear(), month = (date.getMonth()+1)>9?date.getMonth()+1:"0"+(date.getMonth()+1);
                    var url = "/jeesite/static/ckfinder/ckfinder.html?type=images&start=images:/photo/"+year+"/"+month+
                        "/&action=js&func=nameImageSelectAction&thumbFunc=nameImageThumbSelectAction&cb=nameImageCallback&dts=0&sm=0";
                    windowOpen(url,"文件管理",1000,700);
                    //top.$.jBox("iframe:"+url+"&pwMf=1", {title: "文件管理", width: 1000, height: 500, buttons:{'关闭': true}});
                }
                function nameImageSelectAction(fileUrl, data, allFiles){
                    var url="", files=ckfinderAPI.getSelectedFiles();
                    for(var i=0; i<files.length; i++){//
                        url += files[i].getUrl();//
                        if (i<files.length-1) url+="|";
                    }//
                    $("#nameImage").val(url);//
                    nameImagePreview();
                    //top.$.jBox.close();
                }
                function nameImageThumbSelectAction(fileUrl, data, allFiles){
                    var url="", files=ckfinderAPI.getSelectedFiles();
                    for(var i=0; i<files.length; i++){
                        url += files[i].getThumbnailUrl();
                        if (i<files.length-1) url+="|";
                    }//
                    $("#nameImage").val(url);//
                    nameImagePreview();
                    //top.$.jBox.close();
                }
                function nameImageCallback(api){
                    ckfinderAPI = api;
                }
                function nameImageDel(obj){
                    var url = $(obj).prev().attr("url");
                    $("#nameImage").val($("#nameImage").val().replace("|"+url,"","").replace(url+"|","","").replace(url,"",""));
                    nameImagePreview();
                }
                function nameImageDelAll(){
                    $("#nameImage").val("");
                    nameImagePreview();
                }
                function nameImagePreview(){
                    var li, urls = $("#nameImage").val().split("|");
                    $("#nameImagePreview").children().remove();
                    for (var i=0; i<urls.length; i++){
                        if (urls[i]!=""){//
                            li = "<li><img src=\""+urls[i]+"\" url=\""+urls[i]+"\" style=\"max-width:100px;max-height:100px;_height:100px;border:0;padding:3px;\">";//
                            li += "&nbsp;&nbsp;<a href=\"javascript:\" onclick=\"nameImageDel(this);\">×</a></li>";
                            $("#nameImagePreview").append(li);
                        }
                    }
                    if ($("#nameImagePreview").text() == ""){
                        $("#nameImagePreview").html("<li style='list-style:none;padding-top:5px;'>无</li>");
                    }
                }
                nameImagePreview();
			</script>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">归属公司:</label>
		<div class="controls">
			<label class="lbl">二七区分公司</label>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">归属部门:</label>
		<div class="controls">
			<label class="lbl">公司领导</label>
		</div>
	</div>--%>
	<div class="control-group">
		<label class="control-label">姓名:</label>
		${tbSystemUser.username}
	</div>
	<div class="control-group">
		<label class="control-label">邮箱:</label>
		${tbSystemUser.email}
	</div>
	<%--<div class="control-group">
		<label class="control-label">电话:</label>
		<div class="controls">
			<input id="phone" name="phone" type="text" value="" maxlength="50">
		</div>
	</div>--%>
	<div class="control-group">
		<label class="control-label">手机:</label>
		${tbSystemUser.phone}
	</div>
	<div class="control-group">
		<label class="control-label">备注:</label>
		${tbSystemUser.userNote}
	</div>
	<%--<div class="control-group">
		<label class="control-label">用户类型:</label>
		<div class="controls">
			<label class="lbl">无</label>
		</div>
	</div>--%>
	<div class="control-group">
		<label class="control-label">用户角色:</label>
		<c:if test="${tbSystemUser.tbUserRole.roleId==1}">
			系统管理员
		</c:if >
		<c:if test="${tbSystemUser.tbUserRole.roleId==2}">
			高管
		</c:if>
		<c:if test="${tbSystemUser.tbUserRole.roleId==3}">
			业务经理
		</c:if>
		<c:if test="${tbSystemUser.tbUserRole.roleId==4}">
			业务人员
		</c:if>
	</div>
	<%--<div class="control-group">
		<label class="control-label">上次登录:</label>
		<div class="controls">
			<label class="lbl">IP: 0:0:0:0:0:0:0:1&nbsp;&nbsp;&nbsp;&nbsp;时间：2017年11月17日 星期五 16:51:18</label>
		</div>
	</div>--%>
	<%--<div class="form-actions">
		<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)">
	</div>--%>


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