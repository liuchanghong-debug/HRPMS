var jobName = false;
var jobType = false;
var industry = false;
var companyId = false;
var needPerson = false;
var price = false;
var address = false;
var startTime = false;
var endTime = false;
var jobContent = false;

var trueStyle = "<span class=\"help-inline\"><span style=\"color: green; \">*</span></span>";
var falseStyle = "<span class=\"help-inline\"><span style=\"color: red; \">*</span></span>";

$(function () {
    //需求名称
    $("#jobName").keyup(function () {
        if($("#jobName").val() != "" && $("#jobName").val() != null){
            $("#jobName ~ span").remove();
            $("#jobName").after(trueStyle);
            jobName = true;
        }else {
            $("#jobName ~ span").remove();
            $("#jobName").after(falseStyle);
            jobName = false;
        }
    });
    //需求职位
    $("#jobType").keyup(function () {
        if($("#jobType").val() != "" && $("#jobType").val() != null){
            $("#jobType ~ span").remove();
            $("#jobType").after(trueStyle);
            jobType = true;
        }else {
            $("#jobType ~ span").remove();
            $("#jobType").after(falseStyle);
            jobType = false;
        }
    });
    //所属行业
    $("#industry").change(function () {
        if($("#industry").val() != "" && $("#industry").val() != null){
            $("#industry ~ span").remove();
            $("#industry").after(trueStyle);
            industry = true;
        }else {
            $("#industry ~ span").remove();
            $("#industry").after(falseStyle);
            industry = false;
        }
    });
    //发布公司
    $("#companyId").change(function () {
        if($("#companyId").val() != "" && $("#companyId").val() != null){
            $("#companyId ~ span").remove();
            $("#companyId").after(trueStyle);
            companyId = true;
        }else {
            $("#companyId ~ span").remove();
            $("#companyId").after(falseStyle);
            companyId = false;
        }
    });
    //需求人数
    $("#needPerson").keyup(function () {
        if($("#needPerson").val() != "" && $("#needPerson").val() != null){
            $("#needPerson ~ span").remove();
            $("#needPerson").after(trueStyle);
            needPerson = true;
        }else {
            $("#needPerson ~ span").remove();
            $("#needPerson").after(falseStyle);
            needPerson = false;
        }
    });
    //需求单价
    $("#price").keyup(function () {
        if($("#price").val() != "" && $("#price").val() != null){
            $("#price ~ span").remove();
            $("#price").after(trueStyle);
            price = true;
        }else {
            $("#price ~ span").remove();
            $("#price").after(falseStyle);
            price = false;
        }
    });
    //工作地点
    $("#address").keyup(function () {
        if($("#address").val() != "" && $("#address").val() != null){
            $("#address ~ span").remove();
            $("#address").after(trueStyle);
            address = true;
        }else {
            $("#address ~ span").remove();
            $("#address").after(falseStyle);
            address = false;
        }
    });
    //开始日期
    $("#startTime").change(function () {
        if($("#startTime").val() != "" && $("#startTime").val() != null){
            $("#startTime ~ span").remove();
            $("#startTime").after(trueStyle);
            startTime = true;
        }else {
            $("#startTime ~ span").remove();
            $("#startTime").after(falseStyle);
            startTime = false;
        }
    });
    //结束日期
    $("#endTime").change(function () {
        if($("#endTime").val() != "" && $("#endTime").val() != null){
            $("#endTime ~ span").remove();
            $("#endTime").after(trueStyle);
            endTime = true;
        }else {
            $("#endTime ~ span").remove();
            $("#endTime").after(falseStyle);
            endTime = false;
        }
    });
    //需求详细
    $("#jobContent").keyup(function () {
        if($("#jobContent").val() != "" && $("#jobContent").val() != null){
            $("#jobContent ~ span").remove();
            $("#jobContent").after(trueStyle);
            jobContent = true;
        }else {
            $("#jobContent ~ span").remove();
            $("#jobContent").after(falseStyle);
            jobContent = false;
        }
    });

    $("input").keyup(function () {
        $("#btnSubmit").removeAttr("disabled");
    }).click(function () {
        $("#btnSubmit").removeAttr("disabled");
    }).change(function () {
        $("#btnSubmit").removeAttr("disabled");
    });

    $("select").change(function () {
        $("#btnSubmit").removeAttr("disabled");
    }).click(function () {
        $("#btnSubmit").removeAttr("disabled");
    });

    $("textarea").keyup(function () {
        $("#btnSubmit").removeAttr("disabled");
    }).click(function () {
        $("#btnSubmit").removeAttr("disabled");
    });
});

function inputFormSubmit() {
    $("input").keyup().change();
    $("select").change();
    $("textarea").keyup();
    if(!(jobName && jobType && industry && companyId && needPerson && price && address && startTime && endTime && jobContent)){
        $("#btnSubmit").attr("disabled", "disabled");
    }else {
        $("#inputForm").submit();
    }
}