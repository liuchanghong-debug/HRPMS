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
<!-- saved from url=(0062)http://localhost:8080/jeesite/a/company/system/systemRole/form -->
<html style="overflow-x:auto;overflow-y:auto;"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>角色信息管理 - Powered By JeeSite</title>
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
	<link href="js/static/jquery-ztree/3.5.12/css/zTreeStyle/zTreeStyle.min.css" rel="stylesheet" type="text/css">
	<script src="js/static/jquery-ztree/3.5.12/js/jquery.ztree.all-3.5.min.js" type="text/javascript"></script>
	<script type="text/javascript">
		var funct ;
		var brolename = false;
        $(document).ready(function() {
            $.ajaxSettings.async=false;
            $.post(
                "menu-manager/selectAllSystemFunctionName",
				function (json) {
                    funct=json;
                },
				"json"
			);

            //角色名唯一验证
            $("#rolename").blur(function () {
                var roleName = $(this).val();
                $.post(
                    "role-manager/roleNameIsOne",
                    {"roleName":roleName},
                    function (json) {
                        if(json){
                            $("#sprole").html("<font color='green' size='6'>√</font>");
                            brolename=true;
                        }else{
                            brolename=false;
                            $("#sprole").html("<font color='red'size='6'>×</font>");
                        }
                    },
                    "json"
                )

            });

            //$("#name").focus();
            /*$("#inputForm").validate({
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
            });*/

            var setting = {check:{enable:true,nocheckInherit:true},view:{selectedMulti:false},
                data:{simpleData:{enable:true}},callback:{
                    //beforeClick:function(id, node){
                    //tree.checkNode(node, !node.checked, true, true);
                    //return false; }
                    onCheck:onCheck
                }};

            // 用户-菜单
            var zNodes=funct;
            // 初始化树结构
            var tree = $.fn.zTree.init($("#menuTree"), setting, zNodes);
            // 不选择父节点
            tree.setting.check.chkboxType = { "Y" : "ps", "N" : "s" };
            // 默认选择节点
            var ids = "".split(",");
            for(var i=0; i<ids.length; i++) {
                var node = tree.getNodeByParam("id", ids[i]);
                try{tree.checkNode(node, true, false);}catch(e){}
            }
            // 默认展开全部节点
            tree.expandAll(true);

            function onCheck(e,treeId,treeNode){
                var treeObj=$.fn.zTree.getZTreeObj("menuTree");
                var nodes=treeObj.getCheckedNodes(true);
                var v="";
                for(var i=0;i<nodes.length;i++){
                    v+=nodes[i].id + ",";
                }
                $("#menu_id").val(v);
            }
        });

        function sub() {
            return brolename;
        }
	</script>

</head>
<body>

<ul class="nav nav-tabs">
	<li><a href="/role-manager/selectSystemRoleByDuo">角色信息列表</a></li>
	<li class="active"><a href="/role-manager/addSystemRoleJsp">角色信息添加
	</a></li>
</ul>
<br>
<form id="inputForm" class="form-horizontal" action="/role-manager/addSystemRole" method="post" onsubmit="return sub()" novalidate="novalidate">
	<input type="hidden" name="createBy" value="${sessionScope.tbSystemUser.id}">

	<script type="text/javascript">top.$.jBox.closeTip();</script>

	<div class="control-group">
		<label class="control-label">角色名称：</label>
		<div class="controls">
			<input id="rolename" name="roleName" class="input-xlarge required" type="text" value="" maxlength="50">
			<span class="help-inline" id="sprole"></span>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">排序：</label>
		<div class="controls">
			<input id="sortnum" name="sortNum" class="input-xlarge " type="text" value="" maxlength="11">
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">状态：</label>
		<div class="controls">
			<select id="status" name="status" class="input-xlarge">
				<option value="" selected="selected"></option>
				<option value="0">正常</option><option value="1">删除</option>
			</select>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">角色授权:</label>
		<div class="controls">
			<div id="menuTree" class="ztree"
				 style="margin-top: 3px; float: left;"></div>
			<input id="menu_id" name="menuIds" type="hidden" value=""/>
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