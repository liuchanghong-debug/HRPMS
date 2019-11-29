//验证
var companyId = false;

var trueStyle = "<span class=\"help-inline\"><span style=\"color: green; \">*</span></span>";
var falseStyle = "<span class=\"help-inline\"><span style=\"color: red; \">*</span></span>";

$(function () {
    $("#companyId").change(function () {
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


function getCompanyMessByCompanyId(id) {
    $("#companyPrice").val(null);
    $("#startTime").val(null);
    $("#endTime").val(null);
    $("#jobContent").text("");
    $("#companyId").empty().attr("style", "display: none");

    if(id != "" && id != null){
        $.get(
            //通过公司得到期望薪资差不多的求职信息
            "laowu/getNeedJobByCompanyIdAndPersonPrice",
            {"companyId":id, "price":$("#personPrice").val()},
            function (needJobs) {
                var str = "<option value=''>请选择</option>";
                for (var i = 0; i < needJobs.length; i++) {
                    var id = needJobs[i].id;
                    var jobName = needJobs[i].jobName;
                    str += "<option value='" + id + "'>" + jobName + "</option>";
                }
                $("#companyId").removeAttr("style").append(str);
            },
            "json"
        );
    }
}

function getDetailNeedJobById(id) {
    $("#companyPrice").val(null);
    $("#startTime").val(null);
    $("#endTime").val(null);
    $("#jobContent").val("");
    $.get(
        "laowu/getDetailNeedJobById",
        {"id":id},
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