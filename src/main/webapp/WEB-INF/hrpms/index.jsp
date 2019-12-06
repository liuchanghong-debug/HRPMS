<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<base href="<%=basePath%>">
<!DOCTYPE html>
<!-- saved from url=(0037)http://localhost:8080/jeesite/a?login -->
<html style="overflow: hidden;"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>上海晟宏人事代理系统</title><!--  - Powered By JeeSite -->
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
    <script type="text/javascript">var ctx = './a', ctxStatic='js/static';</script>
    <!-- Baidu tongji analytics --><script>var _hmt=_hmt||[];(function(){var hm=document.createElement("script");hm.src="//hm.baidu.com/hm.js?82116c626a8d504a5c0675073362ef6f";var s=document.getElementsByTagName("script")[0];s.parentNode.insertBefore(hm,s);})();</script>



    <meta name="decorator" content="blank">

    <style type="text/css">
        #main {padding:0;margin:0;} #main .container-fluid{padding:0 4px 0 6px;}
        #header {margin:0 0 8px;position:static;} #header li {font-size:14px;_font-size:12px;}
        #header .brand {font-family:Helvetica, Georgia, Arial, sans-serif, 黑体;font-size:26px;padding-left:33px;}
        #footer {margin:8px 0 0 0;padding:3px 0 0 0;font-size:11px;text-align:center;border-top:2px solid #0663A2;}
        #footer, #footer a {color:#999;} #left{overflow-x:hidden;overflow-y:auto;} #left .collapse{position:static;}
        #userControl>li>a{/*color:#fff;*/text-shadow:none;} #userControl>li>a:hover, #user #userControl>li.open>a{background:transparent;}
    </style>
    <script type="text/javascript">
        $(document).ready(function() {
            //
            // 绑定菜单单击事件
            $("#menu a.menu").click(function(){
                // 一级菜单焦点
                $("#menu li.menu").removeClass("active");
                $(this).parent().addClass("active");
                // 左侧区域隐藏
                if ($(this).attr("target") == "mainFrame"){
                    $("#left,#openClose").hide();
                    wSizeWidth();
                    //
                    return true;
                }
                // 左侧区域显示
                $("#left,#openClose").show();
                if(!$("#openClose").hasClass("close")){
                    $("#openClose").click();
                }
                // 显示二级菜单
                var menuId = "#menu-" + $(this).attr("data-id");
                if ($(menuId).length > 0){
                    $("#left .accordion").hide();
                    $(menuId).show();
                    // 初始化点击第一个二级菜单
                    if (!$(menuId + " .accordion-body:first").hasClass('in')){
                        $(menuId + " .accordion-heading:first a").click();
                    }
                    if (!$(menuId + " .accordion-body li:first ul:first").is(":visible")){
                        $(menuId + " .accordion-body a:first i").click();
                    }
                    // 初始化点击第一个三级菜单
                    $(menuId + " .accordion-body li:first li:first a:first i").click();
                }else{
                    // 获取二级菜单数据
                    $.get($(this).attr("data-href"), function(data){
                        if (data.indexOf("id=\"loginForm\"") != -1){
                            alert('未登录或登录超时。请重新登录，谢谢！');
                            top.location = "/jeesite/a";
                            return false;
                        }
                        $("#left .accordion").hide();
                        $("#left").append(data);
                        // 链接去掉虚框
                        $(menuId + " a").bind("focus",function() {
                            if(this.blur) {this.blur()};
                        });
                        // 二级标题
                        $(menuId + " .accordion-heading a").click(function(){
                            $(menuId + " .accordion-toggle i").removeClass('icon-chevron-down').addClass('icon-chevron-right');
                            if(!$($(this).attr('data-href')).hasClass('in')){
                                $(this).children("i").removeClass('icon-chevron-right').addClass('icon-chevron-down');
                            }
                        });
                        // 二级内容
                        $(menuId + " .accordion-body a").click(function(){
                            $(menuId + " li").removeClass("active");
                            $(menuId + " li i").removeClass("icon-white");
                            $(this).parent().addClass("active");
                            $(this).children("i").addClass("icon-white");
                        });
                        // 展现三级
                        $(menuId + " .accordion-inner a").click(function(){
                            var href = $(this).attr("data-href");
                            if($(href).length > 0){
                                $(href).toggle().parent().toggle();
                                return false;
                            }
                            //
                        });
                        // 默认选中第一个菜单
                        $(menuId + " .accordion-body a:first i").click();
                        $(menuId + " .accordion-body li:first li:first a:first i").click();
                    });
                }
                // 大小宽度调整
                wSizeWidth();
                return false;
            });
            // 初始化点击第一个一级菜单
            $("#menu a.menu:first span").click();
            //
            // 鼠标移动到边界自动弹出左侧菜单
            $("#openClose").mouseover(function(){
                if($(this).hasClass("open")){
                    $(this).click();
                }
            });
            // 获取通知数目
            function getNotifyNum(){
                $.get("/jeesite/a/oa/oaNotify/self/count?updateSession=0&t="+new Date().getTime(),function(data){
                    var num = parseFloat(data);
                    if (num > 0){
                        $("#notifyNum,#notifyNum2").show().html("("+num+")");
                    }else{
                        $("#notifyNum,#notifyNum2").hide()
                    }
                });
            }
            getNotifyNum(); //
            setInterval(getNotifyNum, 60000); //
        });
        //
    </script>

</head>
<body>

<div id="main" style="width: auto;">
    <div id="header" class="navbar navbar-fixed-top">
        <div class="navbar-inner">
            <div class="brand"><span id="productName">上海晟宏人事代理系统</span></div>
            <ul id="userControl" class="nav pull-right">
                <%--<li><a href="index" target="_blank" title="访问网站主页"><i class="icon-home"></i></a></li>
                <li id="themeSwitch" class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#" title="主题切换"><i class="icon-th-large"></i></a>
                    <ul class="dropdown-menu">
                        <li><a href="#" onclick="location=&#39;/jeesite/theme/default?url=&#39;+location.href">默认主题</a></li><li><a href="#" onclick="location=&#39;/jeesite/theme/cerulean?url=&#39;+location.href">天蓝主题</a></li><li><a href="#" onclick="location=&#39;/jeesite/theme/readable?url=&#39;+location.href">橙色主题</a></li><li><a href="#" onclick="location=&#39;/jeesite/theme/united?url=&#39;+location.href">红色主题</a></li><li><a href="#" onclick="location=&#39;/jeesite/theme/flat?url=&#39;+location.href">Flat主题</a></li>
                        <li><a href="javascript:cookie(&#39;tabmode&#39;,&#39;1&#39;);location=location.href">开启页签模式</a></li>
                    </ul>
                    <!--[if lte IE 6]><script type="text/javascript">$('#themeSwitch').hide();</script><![endif]-->
                </li>--%>
                <li id="userInfo" class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#" title="个人信息">您好, ${tbSystemUser.username}&nbsp;<span id="notifyNum" class="label label-info hide" style="display: none;"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="personal-information/userPersonalInformation" target="mainFrame"><i class="icon-user"></i>&nbsp; 个人信息</a></li>
                        <li><a href="personal-information/updateUserPassword" target="mainFrame"><i class="icon-lock"></i>&nbsp;  修改密码</a></li>
                       <%-- <li><i class="icon-bell"></i>&nbsp;  我的通知 <span id="notifyNum2" class="label label-info hide" style="display: none;"></span></li>--%>
                    </ul>
                </li>
                <li><a href="loginout" title="退出登录">退出</a></li>
                <li>&nbsp;</li>
            </ul>

            <div class="nav-collapse">
                <ul id="menu" class="nav" style="*white-space:nowrap;float:none;">
                    <%--一级菜单--%>
                    <c:forEach items="${sessionScope.functions}" var="func">
                        <c:if test="${func.parentId==0}">
                            <li class="menu">
                                <a class="menu" href="javascript:" data-href="#" data-id="${func.id}"><span>${func.funcName}</span></a>
                            </li>
                        </c:if>
                    </c:forEach>
                </ul>
            </div><!--/.nav-collapse -->
        </div>
    </div>
    <div class="container-fluid">
        <div id="content" class="row-fluid">
            <div id="left" style="width: 160px; height: 510px;">
               <%-- 一级菜单--%>
                <c:forEach items="${sessionScope.functions}" var="func1">
                    <c:if test="${func1.parentId==0}">
                        <div class="accordion" id="menu-${func1.id}" style="display: none;">
                            <div class="accordion-group">
                                <%--二级菜单--%>
                                <c:forEach items="${sessionScope.functions}" var="func2">


                                    <c:if test="${func2.parentId==func1.id}">
                                        <div class="accordion-heading">
                                            <a class="accordion-toggle" data-toggle="collapse" data-parent="#menu-${func1.id}" data-href="#collapse-${func2.id}" href="#collapse-${func2.id}" title=""><i class="icon-chevron-down"></i>&nbsp;${func2.funcName}</a>
                                        </div>
                                        <div id="collapse-${func2.id}" class="accordion-body collapse in">
                                            <div class="accordion-inner">
                                                <ul class="nav nav-list">
                                                    <%--三级菜单--%>
                                                    <c:forEach items="${sessionScope.functions}" var="func3">
                                                        <c:if test="${func3.parentId==func2.id}">
                                                            <li class=""><a data-href=".menu3-${func3.id}" href="${func2.funcUrl}/${func3.funcUrl}" target="mainFrame"><i class="icon-user icon-white"></i>&nbsp;${func3.funcName}</a>
                                                                <ul class="nav nav-list hide" style="margin:0;padding-right:0;"></ul>
                                                            </li>
                                                        </c:if>
                                                    </c:forEach>
                                                </ul>
                                            </div>
                                        </div>
                                    </c:if>
                                </c:forEach>
                            </div>

                        </div>
                    </c:if>

                </c:forEach>

            </div>

            <div id="openClose" class="close" style="height: 700px;">&nbsp;
            </div>

            <div id="right" style="height: 95%; width: 1185px;">
                <iframe id="mainFrame" name="mainFrame" style="overflow: visible; height: 100%;" scrolling="yes" frameborder="no" width="100%" height="100%"></iframe>
            </div>

        </div>
        <div id="footer" class="row-fluid">
                Copyright © 2012-2017 上海晟宏人事代理系统 - Powered By JeeSite V1.2.7
        </div>
    </div>
</div>
<script type="text/javascript">
    var leftWidth = 160; // 左侧窗口大小
    var tabTitleHeight = 33; // 页签的高度
    var htmlObj = $("html"), mainObj = $("#main");
    var headerObj = $("#header"), footerObj = $("#footer");
    var frameObj = $("#left, #openClose, #right, #right iframe");
    function wSize(){
        var minHeight = 500, minWidth = 980;
        var strs = getWindowSize().toString().split(",");
        htmlObj.css({"overflow-x":strs[1] < minWidth ? "auto" : "hidden", "overflow-y":strs[0] < minHeight ? "auto" : "hidden"});
        mainObj.css("width",strs[1] < minWidth ? minWidth - 10 : "auto");
        frameObj.height((strs[0] < minHeight ? minHeight : strs[0]) - headerObj.height() - footerObj.height() - (strs[1] < minWidth ? 42 : 28));
        $("#openClose").height($("#openClose").height() - 5);//
        wSizeWidth();
    }
    function wSizeWidth(){
        if (!$("#openClose").is(":hidden")){
            var leftWidth = ($("#left").width() < 0 ? 0 : $("#left").width());
            $("#right").width($("#content").width()- leftWidth - $("#openClose").width() -5);
        }else{
            $("#right").width("100%");
        }
    }//
</script>
<script src="./index/wsize.min.js" type="text/javascript"></script>
</body></html>