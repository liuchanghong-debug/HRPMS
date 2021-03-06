<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<base href="<%=basePath%>">
<!DOCTYPE html>
<!-- saved from url=(0062)http://localhost:8080/jeesite/a/company/system/systemDict/form -->
<html style="overflow-x:auto;overflow-y:auto;"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>数据字典管理 - Powered By JeeSite</title>
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
	<li><a href="datadict/datadictList">数据字典列表</a></li>
	<li class="active"><a href="datadict/datadictAdd">数据字典添加</a></li>
</ul><br>
<form id="inputForm" class="form-horizontal" action="datadict/datadictAddToDB" method="post" novalidate="novalidate">
	<script type="text/javascript">top.$.jBox.closeTip();</script>
	<div class="control-group">
		<label class="control-label">字典归属：</label>
		<div class="controls">
			<select id="parentId" name="parentId" class="input-xlarge  select2-offscreen" tabindex="-1">
				<%--ajax   字典里面没有值，则为disabled--%>
			</select>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">字典名称：</label>
		<div class="controls">
			<input id="name" name="name" class="input-xlarge required" type="text" value="" maxlength="50">
			<span class="help-inline"><span style="color: red; ">*</span> </span>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">存储值：</label>
		<div class="controls">
			<input id="value" name="value" class="input-xlarge required" type="text" value="" maxlength="50">
			<span class="help-inline"><span style="color: red; ">*</span> </span>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">显示值：</label>
		<div class="controls">
			<input id="label" name="label" class="input-xlarge " type="text" value="" maxlength="100">
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">描述：</label>
		<div class="controls">
			<input id="description" name="description" class="input-xlarge " type="text" value="" maxlength="100">
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">排序：</label>
		<div class="controls">
			<input id="sort" name="sort" class="input-xlarge " type="text" value="" maxlength="11">
		</div>
	</div>
	<!-- 		<div class="control-group"> -->
	<!-- 			<label class="control-label">父编码：</label> -->
	<!-- 			<div class="controls"> -->

	<!-- 			</div> -->
	<!-- 		</div> -->
	<div class="control-group">
		<label class="control-label">状态：</label>
		<div class="controls">
			<div class="select2-container input-xlarge" id="s2id_status">
				<select id="status" name="status" class="input-xlarge  select2-offscreen" tabindex="-1">
					<option value="" selected="selected"></option>
					<option value="0" selected>正常</option>
					<option value="1">删除</option>
				</select>
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