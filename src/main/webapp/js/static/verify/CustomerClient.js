var name = false;
var idCard = false;
var birthday = false;
var phone = false;
var email = false;
var address = false;
var zipCode = false;
var school = false;
var specialty = false;
var graduation = false;
var companyId = false;
var customerType = false;
var remark = false;

var trueStyle = "<span class=\"help-inline\"><span style=\"color: green; \">*</span></span>";
var falseStyle = "<span class=\"help-inline\"><span style=\"color: red; \">*</span></span>";


$(function () {
    //姓名
    $("#name").keyup(function () {
        if($("#name").val() != "" && $("#name").val() != null){
            $("#name ~ span").remove();
            $("#name").after(trueStyle);
            name = true;
        }else {
            $("#name ~ span").remove();
            $("#name").after(falseStyle);
            name = false;
        }
    });
    //身份证
    $("#idCard").keyup(function () {
        if($("#idCard").val() != "" && $("#idCard") != null){
            if(/^[1-9][0-9,x|X]{14,17}$/.test($("#idCard").val())){
                $.ajaxSettings.async = false;
                $.get(
                    "customerClient/customerIdCardIsOnly",
                    {"idCard":$("#idCard").val()},
                    function (json) {
                        if(json){
                            $("#idCard ~ span").remove();
                            $("#idCard").after(trueStyle);
                            idCard = true;
                        }else {
                            $("#idCard ~ span").remove();
                            $("#idCard").after(falseStyle);
                            idCard = false;
                        }
                    },
                    "json"
                );
            }else {
                $("#idCard ~ span").remove();
                $("#idCard").after(falseStyle);
                idCard = false;
            }
        }else {
            $("#idCard ~ span").remove();
            $("#idCard").after(falseStyle);
            idCard = false;
        }
    });
    // 生日
    $("#birthday").change(function () {
        if($("#birthday").val() != "" && $("#birthday").val() != null){
            birthday = /^\d{4}-\d{1,2}-\d{1,2}$/.test($("#birthday").val());
            if(birthday){
                $("#birthday ~ span").remove();
                $("#birthday").after(trueStyle);
            }else {
                $("#birthday ~ span").remove();
                $("#birthday").after(falseStyle);
            }
        }else {
            birthday = false;
            $("#birthday ~ span").remove();
            $("#birthday").after(falseStyle);
        }
    });
    //手机号码
    $("#phone").keyup(function () {
        if($("#phone") != "" && $("#phone").val() != null){
            phone = /^\d{3,4}[\s,-]?\d{7,8}$/.test($("#phone").val());
            if(phone){
                $("#phone ~ span").remove();
                $("#phone").after(trueStyle);
            }else {
                $("#phone ~ span").remove();
                $("#phone").after(falseStyle);
            }
        }else {
            $("#phone ~ span").remove();
            $("#phone").after(falseStyle);
            phone = false;
        }
    });
    //电子邮件
    $("#email").keyup(function () {
        if($("#email").val() != "" && $("#email").val() != null){
            email = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test($("#email").val())
            if(email){
                $("#email ~ span").remove();
                $("#email").after(trueStyle);
            }else {
                $("#email ~ span").remove();
                $("#email").after(falseStyle);
            }
        }else {
            $("#email ~ span").remove();
            $("#email").after(falseStyle);
            email = false;
        }
    });
    //住址
    $("#address").keyup(function () {
        if($("#address").val() != "" && $("#address").val() != null){
            address = true;
            $("#address ~ span").remove();
            $("#address").after(trueStyle);
        }else {
            $("#address ~ span").remove();
            $("#address").after(falseStyle);
            address = false;
        }
    });
    // 邮编
    $("#zipCode").keyup(function () {
        if($("#zipCode").val() != "" && $("#zipCode").val() != null){
            zipCode = /^[1-9]\d{5}(?!\d)$/.test($("#zipCode").val());
            if(zipCode){
                $("#zipCode ~ span").remove();
                $("#zipCode").after(trueStyle);
            }else {
                $("#zipCode ~ span").remove();
                $("#zipCode").after(falseStyle);
            }
        }else {
            $("#zipCode ~ span").remove();
            $("#zipCode").after(falseStyle);
            zipCode = false;
        }
    });
    //毕业学校
    $("#school").keyup(function () {
        if($("#school").val() != "" && $("#school").val() != null){
            school = true;
            $("#school ~ span").remove();
            $("#school").after(trueStyle);
        }else {
            school = false;
            $("#school ~ span").remove();
            $("#school").after(falseStyle);
        }
    });
    //专业
    $("#specialty").keyup(function () {
        if($("#specialty").val() != "" && $("#specialty").val() != null){
            specialty = true;
            $("#specialty ~ span").remove();
            $("#specialty").after(trueStyle);
        }else {
            specialty = false;
            $("#specialty ~ span").remove();
            $("#specialty").after(falseStyle);
        }
    });
    //毕业时间
    $("#graduation").keyup(function () {
        if($("#graduation").val() != "" && $("#graduation").val() != null){
            graduation = true;
            $("#graduation ~ span").remove();
            $("#graduation").after(trueStyle);
        }else {
            graduation = false;
            $("#graduation ~ span").remove();
            $("#graduation").after(falseStyle);
        }
    });
    //所属公司
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
    //客户类别
    $("#customerType").change(function () {
        if($("#customerType").val() != "" && $("#customerType").val() != null){
            customerType = true;
            $("#customerType ~ span").remove();
            $("#customerType").after(trueStyle);
        }else {
            customerType = false;
            $("#customerType ~ span").remove();
            $("#customerType").after(falseStyle);
        }
    });
    //备注
    $("#remark").keyup(function () {
        if($("#remark").val() != "" && $("#remark").val() != null){
            remark = true;
            $("#remark ~ span").remove();
            $("#remark").after(trueStyle);
        }else {
            remark = false;
            $("#remark ~ span").remove();
            $("#remark").after(falseStyle);
        }
    });
    $("input").keyup(function () {
        $("#btnSubmit").removeAttr("disabled");
    }).click(function () {
        $("#btnSubmit").removeAttr("disabled");
    });
    $("select").change(function () {
        $("#btnSubmit").removeAttr("disabled");
    }).click(function () {
        $("#btnSubmit").removeAttr("disabled");
    });
});

function inputFormSubmit() {
    $("input").keyup();
    $("select").change();
    if(!(name && idCard && birthday && phone && email && address && zipCode && school && specialty && graduation && companyId && customerType && remark)){
        $("#btnSubmit").attr("disabled", "disabled");
    }else {
        $("#inputForm").submit();
    }
}