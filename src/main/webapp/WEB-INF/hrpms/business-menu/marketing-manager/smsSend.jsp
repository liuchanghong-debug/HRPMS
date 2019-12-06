<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<base href="<%=basePath%>">
<!DOCTYPE html>
<!-- saved from url=(0054)http://localhost:8080/jeesite/a/company/smsRecord/form -->
<html style="overflow-x:auto;overflow-y:auto;"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>短信管理 - Powered By JeeSite</title>
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
	<script src="js/layer-v3.1.0/layer/layer.js" type="text/javascript"></script>
	<!-- Baidu tongji analytics --><script>var _hmt=_hmt||[];(function(){var hm=document.createElement("script");hm.src="//hm.baidu.com/hm.js?82116c626a8d504a5c0675073362ef6f";var s=document.getElementsByTagName("script")[0];s.parentNode.insertBefore(hm,s);})();</script>


	<meta name="decorator" content="default">
	<script type="text/javascript">

        $(function () {
            $.post(
                "companyClient/selectAllCustomerName",
                {"name":"isAll"},
                function (json) {
                    var str = "<option value='' selected></option>";
                    for (var i = 0; i < json.length; i++) {
                        str += "<option value='" + json[i].phone + "'>" + json[i].name + "</option>"
                    }
                    $("#telephone").html(str);
                },
                "json"
            );
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

	<li class="active"><a href="/marketing-manager/addSmsRecoredJsp">短信发送</a></li>
</ul><br>
<form id="inputForm" class="form-horizontal" action="/marketing-manager/addSmsRecored" method="post" novalidate="novalidate">
	<input name="user_id" type="hidden" value="${sessionScope.tbSystemUser.id}">
	<script type="text/javascript">top.$.jBox.closeTip();</script>

	<div class="control-group">
		<label class="control-label">发送人：</label>
		<div class="controls">
			<input id="userId" name="username" class="input-xlarge" type="text" value="${sessionScope.tbSystemUser.username}" maxlength="512">
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">接收人：</label>
		<div class="controls">
			<select id="telephone" name="telephone" class="input-xlarge  select2-offscreen" tabindex="-1">

			</select>
			<span class="help-inline"><font color="red">*</font> </span>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">短信模板：</label>
		<div class="controls">
			<input type="text" name="templateId" id="templateId" value="" htmlescape="false" maxlength="512" class="input-xlarge required">

			<input type="button" name="chooseSms" value="选择" id="parentIframe">

		</div>
	</div>
	<div class="control-group">
		<label class="control-label">短信内容：</label>
		<div class="controls">
			<textarea id="content" name="content" class="input-xxlarge required" rows="4"></textarea>
			<span class="help-inline"><font color="red">*</font> </span>
		</div>
	</div>
	<div class="form-actions">
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="发送">&nbsp;
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

$('#parentIframe').on('click', function(){
    layer.open({
        type: 2,
        title: '',
        maxmin: true,
        shadeClose: true, //点击遮罩关闭层
        area : ['800px' , '400px'],
        content: 'marketing-manager/selectSmsModleJsp',
        btn: ['确定','返回'],
        yes: function(data){
            //当点击‘确定’按钮的时候，获取弹出层返回的值
            var list = window["layui-layer-iframe" + data].callbackdatasms();
            //打印返回的值，看是否有我们想返回的值。
            var id=parseInt(list[0]);
            $("#templateId").val(id);
            var content = list[1].replace(/\s+/g,"");
            $("#content").val(content);

            //最后关闭弹出层
            layer.close(data);
        },
        cancel: function(){
            //右上角关闭回调
        }
    });
});
</script>

</body></html>