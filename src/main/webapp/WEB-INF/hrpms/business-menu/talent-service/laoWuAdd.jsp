<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<base href="<%=basePath%>">
<!DOCTYPE html>
<!-- saved from url=(0054)http://localhost:8080/jeesite/a/company/personJob/form -->
<html style="overflow-x:auto;overflow-y:auto;"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>劳务合作管理 - Powered By JeeSite</title>
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
	<li><a href="../laowuList/saved_resource.html">劳务合作列表</a></li>
	<li class="active"><a href="saved_resource.html">劳务合作添加</a></li>
</ul><br>
<form id="inputForm" class="form-horizontal" action="#" method="post" novalidate="novalidate">
	<input id="id" name="id" type="hidden" value="">

	<script type="text/javascript">top.$.jBox.closeTip();</script>

	<table class="table table-bordered table-condensed">
		<tbody><tr>
			<td><label class="control-label">客户名称：</label></td>
			<td>
				<input type="text" name="customerName" value="" class="input-xlarge required">
			</td>
			<td><label class="control-label">身份证号：</label></td>
			<td>
				<input id="idcard" name="idcard" class="input-xlarge required" type="text" value="" maxlength="20">
				<span class="help-inline"><font color="red">*</font> </span>
			</td>
		</tr>

		<tr>
			<td><label class="control-label">合作公司：</label></td>
			<td>
				<select id="companyid" name="companyid" class="input-xlarge required select2-offscreen" tabindex="-1">
					<option value="1000">智递科技</option>

					*
				</select>
			</td>
			<td>
				<label class="control-label">工作类型：</label>
			</td><td>
			<select name="jobtype" style="width:280px;" tabindex="-1" class="select2-offscreen">
				<option value="0">兼职</option>
				<option value="1">全职</option>
				<option value="2">外派</option>
			</select>
		</td>
		</tr>
		<tr>
			<td><label class="control-label">公司单价：</label></td>
			<td>
				<input id="companyprice" name="companyprice" class="input-xlarge " type="text" value="">
			</td>
			<td><label class="control-label">个人单价：</label></td>
			<td>
				<input id="personprice" name="personprice" class="input-xlarge " type="text" value="">
			</td>
		</tr>
		<tr>
			<td><label class="control-label">开始时间：</label></td>
			<td>
				<input name="starttime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate " value="" onclick="WdatePicker({dateFmt:&#39;yyyy-MM-dd HH:mm:ss&#39;,isShowClear:false});" style="width:270px;">
			</td>
			<td><label class="control-label">结束时间：</label></td>
			<td>
				<input name="endtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate " value="" onclick="WdatePicker({dateFmt:&#39;yyyy-MM-dd HH:mm:ss&#39;,isShowClear:false});" style="width:270px;">
			</td>
		</tr>
		<tr>
			<td><label class="control-label">合同上传：</label></td>
			<td>
				<input id="contracturl" name="contracturl" maxlength="256" class="input-xlarge" type="hidden" value="">

				<ol id="contracturlPreview"><li style="list-style:none;padding-top:5px;">无</li></ol><a href="javascript:" onclick="contracturlFinderOpen();" class="btn">添加</a>&nbsp;<a href="javascript:" onclick="contracturlDelAll();" class="btn">清除</a>
				<script type="text/javascript">
                    function contracturlFinderOpen(){//
                        var date = new Date(), year = date.getFullYear(), month = (date.getMonth()+1)>9?date.getMonth()+1:"0"+(date.getMonth()+1);
                        var url = "/jeesite/static/ckfinder/ckfinder.html?type=files&start=files:/company/personJob/"+year+"/"+month+
                            "/&action=js&func=contracturlSelectAction&thumbFunc=contracturlThumbSelectAction&cb=contracturlCallback&dts=0&sm=1";
                        windowOpen(url,"文件管理",1000,700);
                        //top.$.jBox("iframe:"+url+"&pwMf=1", {title: "文件管理", width: 1000, height: 500, buttons:{'关闭': true}});
                    }
                    function contracturlSelectAction(fileUrl, data, allFiles){
                        var url="", files=ckfinderAPI.getSelectedFiles();
                        for(var i=0; i<files.length; i++){//
                            url += files[i].getUrl();//
                            if (i<files.length-1) url+="|";
                        }//
                        $("#contracturl").val($("#contracturl").val()+($("#contracturl").val(url)==""?url:"|"+url));//
                        contracturlPreview();
                        //top.$.jBox.close();
                    }
                    function contracturlThumbSelectAction(fileUrl, data, allFiles){
                        var url="", files=ckfinderAPI.getSelectedFiles();
                        for(var i=0; i<files.length; i++){
                            url += files[i].getThumbnailUrl();
                            if (i<files.length-1) url+="|";
                        }//
                        $("#contracturl").val($("#contracturl").val()+($("#contracturl").val(url)==""?url:"|"+url));//
                        contracturlPreview();
                        //top.$.jBox.close();
                    }
                    function contracturlCallback(api){
                        ckfinderAPI = api;
                    }
                    function contracturlDel(obj){
                        var url = $(obj).prev().attr("url");
                        $("#contracturl").val($("#contracturl").val().replace("|"+url,"","").replace(url+"|","","").replace(url,"",""));
                        contracturlPreview();
                    }
                    function contracturlDelAll(){
                        $("#contracturl").val("");
                        contracturlPreview();
                    }
                    function contracturlPreview(){
                        var li, urls = $("#contracturl").val().split("|");
                        $("#contracturlPreview").children().remove();
                        for (var i=0; i<urls.length; i++){
                            if (urls[i]!=""){//
                                li = "<li><a href=\""+urls[i]+"\" url=\""+urls[i]+"\" target=\"_blank\">"+decodeURIComponent(urls[i].substring(urls[i].lastIndexOf("/")+1))+"</a>";//
                                li += "&nbsp;&nbsp;<a href=\"javascript:\" onclick=\"contracturlDel(this);\">×</a></li>";
                                $("#contracturlPreview").append(li);
                            }
                        }
                        if ($("#contracturlPreview").text() == ""){
                            $("#contracturlPreview").html("<li style='list-style:none;padding-top:5px;'>无</li>");
                        }
                    }
                    contracturlPreview();
				</script>
			</td>
			<td><label class="control-label">合作状态：</label></td>
			<td>
				<select name="status" style="width:280px;" tabindex="-1" class="select2-offscreen">
					<option value="0">正常</option>
					<option value="1">停止</option>
					<option value="2">结束</option>
				</select>
			</td>
		</tr>
		<tr>
			<td><label class="control-label">工作内容：</label></td>
			<td colspan="3"><textarea id="jobcontent" name="jobcontent" maxlength="256" class="input-xxlarge " rows="2"></textarea></td>
		</tr>
		<tr>
			<td><label class="control-label">备注信息：</label></td>
			<td colspan="3">
				<textarea id="remark" name="remark" maxlength="256" class="input-xxlarge " rows="2"></textarea>
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
        top.$.jBox('get:/jeesite/a/sys/menu/treeselect;JSESSIONID=3baf732ded45486a8f110d13601a4c9b', {title:'选择菜单', buttons:{'关闭':true}, width:300, height: 350, top:10});
        //if ($("#menuContent").html()==""){$.get("/jeesite/a/sys/menu/treeselect", function(data){$("#menuContent").html(data);});}else{$("#menuContent").toggle(100);}
    });
}//-->
</script>

</body></html>