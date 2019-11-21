<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<base href="<%=basePath%>">
<!DOCTYPE html>
<!-- saved from url=(0064)http://localhost:8080/jeesite-master/a/company/needJob/form?id=1 -->
<html style="overflow-x:auto;overflow-y:auto;"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>招聘信息管理 - Powered By JeeSite</title>
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
	<li><a href="../zhaopinList/saved_resource.html">招聘信息列表</a></li>
	<li class="active"><a href="saved_resource.html">招聘信息修改</a></li>
</ul><br>
<form id="inputForm" class="form-horizontal" action="#" method="post" novalidate="novalidate">
	<input id="id" name="id" type="hidden" value="1">















	<script type="text/javascript">top.$.jBox.closeTip();</script>


	<table class="table table-bordered table-condensed">
		<tbody><tr>
			<td><label class="control-label">需求名称：</label></td>
			<td>
				<input id="jobname" name="jobname" class="input-xlarge required" type="text" value="JAVA开发工程师" maxlength="100">
				<span class="help-inline"><font color="red">*</font> </span>
			</td>
			<td><label class="control-label">需求职位：</label></td>
			<td><input id="jobtype" name="jobtype" class="input-xlarge " type="text" value="JAVA开发工程师" maxlength="100">
			</td>
		</tr>

		<tr>
			<td><label class="control-label">所属行业：</label></td>
			<td>
				<select name="industry" htmlescape="false" maxlength="2" class="input-xlarge">
					<option value="0">软件互联网</option>
					<option value="1">建筑房地产</option>
					<option value="2">商业服务业</option>
					<option value="3">金融业</option>
					<option value="4">贸易批发零售</option>
					<option value="5">文体教育传媒</option>
					<option value="6">加工制造</option>
					<option value="7">农林牧副渔</option>
					<option value="8">其他</option>
				</select>
			</td>
			<td>
				<label class="control-label">发布公司：</label>
			</td><td>
			<select path="companyid" class="input-xlarge" style="width:270px" >
				<option value="" label="">

				</option></select>
		</td>

		</tr>
		<tr>
			<td><label class="control-label">需求人数：</label></td>
			<td><input id="needperson" name="needperson" class="input-xlarge " type="text" value="5" maxlength="11"></td>
			<td><label class="control-label">结算方式：</label></td>
			<td>
				<select path="paytype" class="input-xlarge" style="width:270px" >
					<option value="">日结</option>
					<option value="">月结</option>
				</select>
			</td>
		</tr>
		<tr>
			<td><label class="control-label">需求单价：</label></td>
			<td><input id="price" name="price" class="input-xlarge " type="text" value="5000"></td>
			<td><label class="control-label">工作地点：</label></td>
			<td><input id="address" name="address" class="input-xlarge " type="text" value="" maxlength="2"></td>
		</tr>
		<tr>
			<td><label class="control-label">开始日期：</label></td>
			<td>
				<input name="starttime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate " value="2017-11-08 00:00:00" onclick="WdatePicker({dateFmt:&#39;yyyy-MM-dd HH:mm:ss&#39;,isShowClear:false});">
			</td>
			<td><label class="control-label">结束日期：</label></td>
			<td><input name="endtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate " value="2017-11-08 00:00:00" onclick="WdatePicker({dateFmt:&#39;yyyy-MM-dd HH:mm:ss&#39;,isShowClear:false});"></td>
		</tr>

		<tr>
			<td><label class="control-label">信息类型：</label></td>
			<td>
				<select name="infotype" style="width:270px;" class="select2-offscreen">
					<option value="1">合作公司招聘</option>
					<option value="0">本公司招聘</option>
				</select>
			</td>
			<td><label class="control-label">需求状态：</label></td>
			<td>
				<select name="status" style="width:270px;" class="select2-offscreen">
					<option value="0">有效</option>
					<option value="1">无效</option>
				</select>
			</td>
		</tr>

		<tr>
			<td><label class="control-label">需求详细：</label></td>
			<td>

					<textarea id="jobcontent" name="jobcontent" class="input-xlarge " rows="4" cols="30">招聘要求：高薪急聘
1、计算机或相关专业本科学历；
2、熟悉SSH后台架构，熟悉HTML、CSS和Javascript前台语言；
3、有两年以上JAVA软件开发经验，熟悉MIS、ERP管理系统开发；
4、参与过至少两个以上的实际项目的应用开发者优先考虑；
5、有大型软件需求分析、系统设计经验，负责过软件系统分析设计的优先。
6、同时做过NET的优先。</textarea>
			</td>
			<td><label class="control-label">备注信息：</label></td>
			<td>

				<textarea id="remark" name="remark" class="input-xlarge " rows="4" cols="30">出差郑州上班</textarea>
			</td>
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