//验证
var companyId = false;

var trueStyle = "<span class=\"help-inline\"><span style=\"color: green; \">√</span></span>";
var falseStyle = "<span class=\"help-inline\"><span style=\"color: red; \">×</span></span>";

$(function () {
    $("#companyId").change(function () {
        $("#btnSubmit").removeAttr("disabled");
        if($("#companyId").val() != "" && $("#companyId").val() != null){
            companyId = true;
            $("#companyId ~ span").remove();
            $("#companyId").after(trueStyle);
        }else {
            companyId = false;
            $("#companyId ~ span").remove();
            $("#companyId").after(falseStyle);
        }
    });
});
function inputFormSubmit() {
    if(!companyId){
        $("#btnSubmit").attr("disabled", "disabled");
    }else {
        $("#inputForm").submit();
    }
}


function getCompanyMessByCompanyId(companyId) {
    $("#companyPrice").val(null);
    $("#startTime").val(null);
    $("#endTime").val(null);
    $("#jobContent").val("");
    $("#companyId").empty();

    if(companyId != "" && companyId != null){
        $.get(
            //通过公司得到期望薪资差不多的求职信息
            "laowu/getNeedJobByCompanyIdAndPersonPrice",
            {"companyId":companyId, "price":$("#personPrice").val()},
            function (needJobs) {
                var str = "<option value=''>请选择</option>";
                for (var i = 0; i < needJobs.length; i++) {
                    var id = needJobs[i].id;
                    var jobName = needJobs[i].jobName;
                    str += "<option value='" + id + "'>" + jobName + "</option>";
                }
                $("#companyId").append(str);
            },
            "json"
        );
    }
}


function getNeedJobDetailById(needJobId) {
    $("#companyPrice").val(null);
    $("#startTime").val(null);
    $("#endTime").val(null);
    $("#jobContent").val("");
    $.get(
        "laowu/getNeedJobDetailById",
        {"needJobId":needJobId},
        function (needJob) {
            $("#companyPrice").val(needJob.price);
            var startTime = new Date(needJob.startTime);
            var endTime = new Date(needJob.endTime);
            $("#startTime").val(startTime.getFullYear().toString() + "-" + (startTime.getMonth() + 1).toString() + "-" + startTime.getDate().toString());
            $("#endTime").val(endTime.getFullYear().toString() + "-" + (endTime.getMonth() + 1).toString() + "-" + endTime.getDate().toString());
            $("#jobContent").val(needJob.jobContent);
        },
        "json"
    );
}