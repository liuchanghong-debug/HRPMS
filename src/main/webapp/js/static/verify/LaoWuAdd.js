//验证
var nameId = false;
var companyId = false;
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
    $("#companyId").blur(function () {
        if($("#companyId").val() != "" && $("#companyId") != null){
            companyId = true;
            $("#companyId ~ span").remove();
            $("#companyId").after(trueStyle);
        }else {
            companyId = false;
            $("#companyId ~ span").remove();
            $("#companyId").after(falseStyle);
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

    //给隐藏jobType赋值
    $("#jobTypeSelect").change(function () {
        $("#jobType").empty().val($("#jobTypeSelect").val());
    });
    //给name 赋值
    $("#nameId").change(function () {
        $("#name").empty().val($("#nameId :selected").text())
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
    if(!(nameId && companyId && contractFile)){
        $("#btnSubmit").attr("disabled", "disabled");
    }else {
        $("#inputForm").submit();
    }
}


//根据person的id得到其他值
function getIdCard(id) {
    $("#idCard").val(null);
    $("#personPrice").val(null);
    $("#company>option").removeAttr("hidden", "hidden").removeAttr("selected", "selected");
    $("#company").removeAttr("onchange").attr("onchange", "getPersonsByCompanyId(this.value)");
    $("#companyId").empty().attr("style", "display: none");
    $("#companyPrice").val(null);
    $("#startTime").val(null);
    $("#endTime").val(null);
    $("#jobContent").text("");
    $("#jobType").val(null);

    if(id != "" && id != null){
        $.get(
            "laowu/getPersonAndCompanyById",
            {"id":id},
            function (json) {
                var person = json.person;
                $("#idCard").val(person.idCard);
                $("#personPrice").val(person.forPrice);
                $("#jobTypeSelect option[value='']").removeAttr("selected");
                $("#jobTypeSelect option[value='" + person.jobType + "']").attr("selected","selected");
                $("#jobType").val(person.jobType);
                // 公司选项
                var companys = json.companys;
                $("#company").each(function () {
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
                                $("#company option[value='" + this.value + "']").attr("hidden", "hidden");
                            }
                        }
                    )
                });
                $("#company").removeAttr("onchange").attr("onchange", "getCompanyById(this.value)");
            },
            "json"
        );
    }
}
//根据company得到公司招聘信息
function getCompanyById(id) {
    $("#companyId").empty().attr("style", "display: none");
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
                $("#companyId").removeAttr("style").append(str).attr("onchange", "getDetailNeedJobById(this.value)");
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





//根据company的id得到合适的用户
//先点击合作公司改变nameId的绑定方法， 将company显示出来，
function getPersonsByCompayId(companyId) {
    $("#companyId").empty().attr("style", "display: none");
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
                var companyJobs = json.needJobs;
                var str = "<option value=''>请选择</option>";
                for (var i = 0; i < companyJobs.length; i++) {
                    var id = companyJobs[i].id;
                    var jobName = companyJobs[i].jobName;
                    str += "<option value='" + id + "'>" + jobName + "</option>";
                }
                $("#companyId").removeAttr("style").append(str).attr("onchange", "getDetailNeedJobById(this.value)");

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
function getPersonBycompanyForPrice(id) {
    $("#companyPrice").val(null);
    $("#startTime").val(null);
    $("#endTime").val(null);
    $("#jobContent").text("");
    if(id != "" && id != null){
        $.get(
            "laowu/getAllJobBycompany",
            {"id":id},
            function (json) {
                var str = "<option value=''>请选择</option>";
                for (var i = 0; i < json.length; i++) {
                    var id = json[i].id;
                    var jobName = json[i].jobName;
                    str += "<option value='" + id + "'>" + jobName + "</option>";
                }
                $("#companyId").removeAttr("style").append(str).attr("onchange", "getPersonById(this.value)");
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
    $("#jobTypeSelect").val(null);
    $("#personPrice").val(null);
    $("#jobType").val(null);
    if(id != "" && id != null){
        $.get(
            "laowu/getDetailPersonByid",
            {"id":id},
            function (json) {
                $("#idCard").val(json.idCard);
                $("#jobTypeSelect option[value=" + json.jobType + "]").attr("selected", "selected");
                $("#jobType").val(json.jobType);
                $("#personPrice").val(json.forPrice);
            },
            "json"
        );
    }
}