//验证
var nameId = false;
var companyJob = false;
var contractFile = false;

var trueStyle = "<span class=\"help-inline\"><span style=\"color: green; \">*</span></span>";
var falseStyle = "<span class=\"help-inline\"><span style=\"color: red; \">*</span></span>";


$(function () {
    //客户名称
    $("#nameId").blur(function () {
        if($("#nameId").val() != "" && $("#nameId") != null){
            nameId = true;
            $("#nameId ~ span").remove();
            $("#nameId").after(trueStyle);
        }else {
            nameId = false;
            $("#nameId ~ span").remove();
            $("#nameId").after(falseStyle);
        }
    });
    //合作公司
    $("#companyJob").blur(function () {
        if($("#companyJob").val() != "" && $("#companyJob") != null){
            companyJob = true;
            $("#companyJob ~ span").remove();
            $("#companyJob").after(trueStyle);
        }else {
            companyJob = false;
            $("#companyJob ~ span").remove();
            $("#companyJob").after(falseStyle);
        }
    });
    //文件上传
    $("#contractFile").change(function () {
        if($("#contractFile").val() != "" && $("#contractFile") != null){
            contractFile = true;
            $("#contractFile ~ span").remove();
            $("#contractFile").after(trueStyle);
        }else {
            contractFile = false;
            $("#contractFile ~ span").remove();
            $("#contractFile").after(falseStyle);
        }
    });

    $("input").click(function () {
        $("#btnSubmit").removeAttr("disabled");
    }).change(function () {
        $("#btnSubmit").removeAttr("disabled");
    });

    $("select").blur(function () {
        $("#btnSubmit").removeAttr("disabled");
    }).change(function () {
        $("#btnSubmit").removeAttr("disabled");
    }).click(function () {
        $("#btnSubmit").removeAttr("disabled");
    });
});

//提交
function inputFormSubmit() {
    $("input").change();
    $("select").blur();
    if(!(nameId && companyJob && contractFile)){
        $("#btnSubmit").attr("disabled", "disabled");
    }else {
        $("#inputForm").submit();
    }
}


//根据person的id得到其他值
function getIdCard(id) {
    $("#idCard").val(null);
    $("#personPrice").val(null);
    $("#companyId>option").removeAttr("hidden", "hidden").removeAttr("selected", "selected");
    $("#companyId").removeAttr("onchange").attr("onchange", "getPersonsByCompayId(this.value)");
    $("#companyJob").empty().attr("style", "display: none");
    $("#companyPrice").val(null);
    $("#startTime").val(null);
    $("#endTime").val(null);
    $("#jobContent").text("");

    if(id !== "" && id != null){
        $.get(
            "laowu/getPersonAndCompanyById",
            {"id":id},
            function (json) {
                var person = json.person;
                $("#idCard").val(person.idCard);
                $("#personPrice").val(person.forPrice);
                $("#jobType option[value='']").removeAttr("selected");
                $("#jobType option[value='" + person.jobType + "']").attr("selected","selected");
                $("#jobType").attr("disabled", "disabled");
                // 公司选项
                var companys = json.companys;
                $("#companyId").each(function () {
                    $(this).children("option").each(
                        function () {
                            var noFit = true;
                            if(this.value == ""){
                                noFit = false;
                            }
                            if(this.value != ""){
                                for (var i = 0; i < companys.length; i++) {
                                    if(this.value == companys[i].id){
                                        noFit = false;
                                        break;
                                    }
                                }
                            }
                            if(noFit){
                                $("#companyId option[value='" + this.value + "']").attr("hidden", "hidden");
                            }
                        }
                    )
                });
                $("#companyId").removeAttr("onchange").attr("onchange", "getCompanyByid(this.value)");
            },
            "json"
        );
    }
}
//根据companyid得到公司招聘信息
function getCompanyByid(id) {
    $("#companyJob").empty().attr("style", "display: none");
    $("#companyPrice").val(null);
    $("#startTime").val(null);
    $("#endTime").val(null);
    $("#jobContent").text("");
    if(id != "" && id != null){
        $.get(
            "laowu/getAllJobByCompanyId",
            {"id":id},
            function (json) {
                var str = "<option value=''>请选择</option>";
                for (var i = 0; i < json.length; i++) {
                    var id = json[i].id;
                    var jobName = json[i].jobName;
                    str += "<option value='" + id + "'>" + jobName + "</option>";
                }
                $("#companyJob").removeAttr("style").append(str).attr("onchange", "getDetailNeedJobById(this.value)");
            },
            "json"
        );
    }
}
//根据公司id得到详细信息
function getDetailNeedJobById(id) {
    $("#companyPrice").val(null);
    $("#startTime").val(null);
    $("#endTime").val(null);
    $("#jobContent").text("");
    if(id != "" && id != null){
        $.get(
            "laowu/getNeedJobById",
            {"id":id},
            function (json) {
                $("#companyPrice").val(json.price);
                var startTime = new Date(json.startTime);
                var endTime = new Date(json.endTime);
                $("#startTime").val(startTime.getFullYear().toString() + "-" + (startTime.getMonth() + 1).toString() + "-" + startTime.getDate().toString());
                $("#endTime").val(endTime.getFullYear().toString() + "-" + (endTime.getMonth() + 1).toString() + "-" + endTime.getDate().toString());
                $("#jobContent").text(json.jobContent);
            },
            "json"
        );
    }
}





//根据companyId的id得到合适的用户
//先点击合作公司改变nameId的绑定方法， 将companyId显示出来，
function getPersonsByCompayId(companyId) {
    $("#companyJob").empty()    .attr("style", "display: none");
    $("#idCard").val(null);
    $("#personPrice").val(null);
    $("#nameId>option").removeAttr("hidden", "hidden").removeAttr("selected", "selected");
    $("#nameId").removeAttr("onchange").attr("onchange", "getIdCard(this.value)");
    $("#companyPrice").val(null);
    $("#startTime").val(null);
    $("#endTime").val(null);
    $("#jobContent").text("");

    if(companyId != "" && companyId != null){
        $.get(
            //通过公司得到期望薪资差不多的求职信息
            "laowu/getPersonByCompanyIdForPrice",
            {"companyId":companyId},
            function (json) {
                var companyJob = json.needJobs;
                var str = "<option value=''>请选择</option>";
                for (var i = 0; i < companyJob.length; i++) {
                    var id = companyJob[i].id;
                    var jobName = companyJob[i].jobName;
                    str += "<option value='" + id + "'>" + jobName + "</option>";
                }
                $("#companyJob").removeAttr("style").append(str).attr("onchange", "getDetailNeedJobById(this.value)");

                var person = json.persons;
                $("#nameId").each(function () {
                    $(this).children("option").each(
                        function () {
                            var noFit = true;
                            if(this.value == ""){
                                noFit = false;
                            }
                            if(this.value != ""){
                                for (var i = 0; i < person.length; i++) {
                                    if(this.value == person[i].id){
                                        noFit = false;
                                        break;
                                    }
                                }
                            }
                            if(noFit){
                                $("#nameId option[value='" + this.value + "']").attr("hidden", "hidden");
                            }
                        }
                    )
                });
                $("#nameId").removeAttr("onchange").attr("onchange", "getPersonById(this.value)");
            },
            "json"
        );
    }
}
//根据职位id得到price得到个人信息
function getPersonByCompanyIdForPrice(id) {
    $("#companyPrice").val(null);
    $("#startTime").val(null);
    $("#endTime").val(null);
    $("#jobContent").text("");
    if(id != "" && id != null){
        $.get(
            "laowu/getAllJobByCompanyId",
            {"id":id},
            function (json) {
                var str = "<option value=''>请选择</option>";
                for (var i = 0; i < json.length; i++) {
                    var id = json[i].id;
                    var jobName = json[i].jobName;
                    str += "<option value='" + id + "'>" + jobName + "</option>";
                }
                $("#companyJob").removeAttr("style").append(str).attr("onchange", "getPersonById(this.value)");
            },
            "json"
        );
    }
}

//根据公司id得到详细信息
function getDetailNeedJobForCompanyTypeById(id) {
    $("#companyPrice").val(null);
    $("#startTime").val(null);
    $("#endTime").val(null);
    $("#jobContent").text("");
    if(id != "" && id != null){
        $.get(
            "laowu/getNeedJobById",
            {"id":id},
            function (json) {
                $("#companyPrice").val(json.price);
                var startTime = new Date(json.startTime);
                var endTime = new Date(json.endTime);

                $("#startTime").val(startTime.getFullYear().toString() + "-" + (startTime.getMonth() + 1).toString() + "-" + startTime.getDate().toString());
                $("#endTime").val(endTime.getFullYear().toString() + "-" + (endTime.getMonth() + 1).toString() + "-" + endTime.getDate().toString());

                $("#jobContent").text(json.jobContent);
            },
            "json"
        );
    }
}

//根据personid得到详细信息
function getPersonById(id) {
    $("#idCard").val(null);
    $("#jobType").val(null);
    $("#personPrice").val(null);
    if(id != "" && id != null){
        $.get(
            "laowu/getDetailPersonByid",
            {"id":id},
            function (json) {
                $("#idCard").val(json.idCard);
                $("#jobType option[value=" + json.jobType + "]").attr("selected", "selected");
                $("#jobType").attr("disabled", "disabled");
                $("#personPrice").val(json.forPrice);
            },
            "json"
        );
    }
}