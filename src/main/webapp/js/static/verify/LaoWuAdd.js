//验证
var nameId = false;
var companyId = false;
var contractFile = false;

var trueStyle = "<span class=\"help-inline\"><span style=\"color: green; \">*</span></span>";
var falseStyle = "<span class=\"help-inline\"><span style=\"color: red; \">*</span></span>";


$(function () {
    //客户名称
    $("#nameId").change(function () {
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
    $("#companyId").change(function () {
        if($("#companyId").val() != "" && $("#companyJob") != null){
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


//根据person的id得到其他值  1
function getPersonAndCompanyByPersonId(personId) {
    $("#idCard").val(null);
    $("#personPrice").val(null);
    $("#company>option").removeAttr("hidden", "hidden").removeAttr("selected", "selected");
    //合作公司
    $("#company").removeAttr("onchange").attr("onchange", "getPersonsAndCompanyByCompayId(this.value)");
    //公司job  隐藏
    $("#companyId").empty().attr("style", "display: none");
    $("#companyPrice").val(null);
    $("#startTime").val(null);
    $("#endTime").val(null);
    $("#jobContent").text("");
    $("#jobType").val(null);
    $("#jobTypeSelect option[value='']").removeAttr("selected");
    $("#jobTypeSelect option[value='']").attr("selected","selected");

    if(personId != "" && personId != null){
        $.get(
            "laowu/getPersonAndCompanyById",
            {"personId":personId},
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
                $("#company").removeAttr("onchange").attr("onchange", "getCompanyJobById(this.value)");
            },
            "json"
        );
    }
}
//根据company  和个人的id  得到公司招聘信息
function getCompanyJobById(companyId) {
    $("#companyId").empty().attr("style", "display: none");
    $("#companyPrice").val(null);
    $("#startTime").val(null);
    $("#endTime").val(null);
    $("#jobContent").text("");
    if(companyId != "" && companyId != null){
        $.ajaxSettings.async = false;
        $.get(
            "laowu/getAllJobByCompanyId",
            {"companyId":companyId, "personId":$("#nameId").val()},
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
function getDetailNeedJobById(needJobId) {
    $("#companyPrice").val(null);
    $("#startTime").val(null);
    $("#endTime").val(null);
    $("#jobContent").text("");
    if(needJobId != "" && needJobId != null){
        $.get(
            "talent/getNeedJobDetailById",
            {"needJobId":needJobId},
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
function getPersonsAndCompanyByCompayId(companyId) {
    $("#companyId").empty().attr("style", "display: none");
    $("#idCard").val(null);
    $("#personPrice").val(null);
    $("#nameId>option").removeAttr("hidden", "hidden").removeAttr("selected", "selected");
    $("#nameId").removeAttr("onchange").attr("onchange", "getPersonAndCompanyByPersonId(this.value)");
    $("#companyPrice").val(null);
    $("#startTime").val(null);
    $("#endTime").val(null);
    $("#jobContent").text("");
    $("#jobTypeSelect option[value='']").removeAttr("selected");
    $("#jobTypeSelect option[value='']").attr("selected","selected");

    if(companyId != "" && companyId != null){
        $.get(
            //通过公司得到期望薪资差不多的求职信息
            "talent/getPersonsAndCompanyByCompanyId",
            {"companyId":companyId},
            function (json) {
                var companyJobs = json.needJobs;
                var str = "<option value=''>请选择</option>";
                for (var i = 0; i < companyJobs.length; i++) {
                    var id = companyJobs[i].id;
                    var jobName = companyJobs[i].jobName;
                    str += "<option value='" + id + "'>" + jobName + "</option>";
                }
                //根据招聘id查询合适的人才
                $("#companyId").removeAttr("style").append(str).attr("onchange", "getPersonsAndNeedJobByNeedJobId(this.value)");

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
                //根据人才查询合适的职位
                $("#nameId").removeAttr("onchange").attr("onchange", "getPersonAndNeedJobsById(this.value)");
                $("#companyId").removeAttr("onchange").attr("onchange", "getPersonsAndNeedJobByNeedJobId(this.value)");
            },
            "json"
        );
    }
}


//根据职位 id 得到 price 得到个人信息(多)    将职位信息加上
function getPersonsAndNeedJobByNeedJobId(needJobId) {
    $("#idCard").val(null);
    $("#personPrice").val(null);
    $("#jobTypeSelect option[value='']").removeAttr("selected");
    $("#jobTypeSelect option[value='']").attr("selected","selected");

    $("#companyPrice").val(null);
    $("#startTime").val(null);
    $("#endTime").val(null);
    $("#jobContent").text("");
    if(needJobId != "" && needJobId != null){
        $.get(
            "talent/getPersonsAndNeedJobByNeedJobId",
            {"needJobId":needJobId},
            function (json) {
                var needJob = json.tbNeedJob;
                var persons = json.persons;
                $("#companyPrice").val(needJob.price);
                var startTime = new Date(needJob.startTime);
                var endTime = new Date(needJob.endTime);
                $("#startTime").val(startTime.getFullYear().toString() + "-" + (startTime.getMonth() + 1).toString() + "-" + startTime.getDate().toString());
                $("#endTime").val(endTime.getFullYear().toString() + "-" + (endTime.getMonth() + 1).toString() + "-" + endTime.getDate().toString());
                $("#jobContent").text(needJob.jobContent);

                $("#nameId > option").removeAttr("hidden");
                $("#nameId").each(function () {
                    $(this).children("option").each(
                        function () {
                            var noFit = true;
                            if(this.value == ""){
                                noFit = false;
                            }
                            if(this.value != ""){
                                for (var i = 0; i < persons.length; i++) {
                                    if(this.value == persons[i].id){
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
                $("#nameId").removeAttr("onchange").attr("onchange", "getPersonDetatilById(this.value)");
            },
            "json"
        );
    }else {
        $("#nameId").removeAttr("onchange").attr("onchange", "getPersonAndNeedJobsById(this.value)");
    }
}
//公司招聘选择后选择客户信息展示
function getPersonDetatilById(personId) {
    $("#idCard").val(null);
    $("#personPrice").val(null);
    $("#jobTypeSelect option[value='']").removeAttr("selected");
    $("#jobTypeSelect option[value='']").attr("selected","selected");
    if(personId != "" && personId != null){
        $.get(
            "talent/getPersonDetatilById",
            {"personId":personId},
            function (person) {
                $("#idCard").val(person.idCard);
                $("#personPrice").val(person.forPrice);
                $("#jobTypeSelect option[value='']").removeAttr("selected");
                $("#jobTypeSelect option[value='" + person.jobType + "']").attr("selected","selected");
                $("#jobType").val(person.jobType);
            },
            "json"
        );
    }
}




//根据personid得到详细信息  招聘信息(多)
function getPersonAndNeedJobsById(personId) {
    $("#idCard").val(null);
    $("#personPrice").val(null);
    $("#jobTypeSelect option[value='']").removeAttr("selected");
    $("#jobTypeSelect option[value='']").attr("selected","selected");

    $("#companyPrice").val(null);
    $("#startTime").val(null);
    $("#endTime").val(null);
    $("#jobContent").text("");

    $("#companyId").empty().attr("style", "display: none").removeAttr("onchange").attr("onchange", "getPersonsAndNeedJobByNeedJobId(this.value)");
    if(personId != "" && personId != null){
        $.get(
            "talent/getPersonAndNeedJobsById",
            {"personId":personId, "companyId":$("#company").val()},
            function (json) {
                var person = json.person;
                var needJobs = json.needJobs;
                $("#idCard").val(person.idCard);
                $("#jobTypeSelect option[value=" + person.jobType + "]").attr("selected", "selected");
                $("#jobType").val(person.jobType);
                $("#personPrice").val(person.forPrice);

                var str = "<option value=''>请选择</option>";
                if(needJobs != null){
                    for (var i = 0; i < needJobs.length; i++) {
                        var id = needJobs[i].id;
                        var jobName = needJobs[i].jobName;
                        str += "<option value='" + id + "'>" + jobName + "</option>";
                    }
                }
                $("#companyId").removeAttr("style").append(str).removeAttr("onchange").attr("onchange", "getNeedJobDetailById(this.value)");
            },
            "json"
        );
    }
}
//根据公司id得到详细信息
function getNeedJobDetailById(needJobId) {
    $("#companyPrice").val(null);
    $("#startTime").val(null);
    $("#endTime").val(null);
    $("#jobContent").text("");
    if(needJobId != "" && needJobId != null){
        $.get(
            "talent/getNeedJobDetailById",
            {"needJobId":needJobId},
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