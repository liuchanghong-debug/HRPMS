var name = false;
var companyNo = false;
var telPhone = false;
var zipCode = false;
var owner = false;
var idCard = false;
var phone = false;
var email = false;
var ownerShip = false;
var address = false;

var trueStyle = "<span class=\"help-inline\"><span style=\"color: green; \">*</span></span>";
var falseStyle = "<span class=\"help-inline\"><span style=\"color: red; \">*</span></span>";


$(function () {
    // 公司名称
    $("#name").keyup(function () {
        if($("#name").val() != "" && $("#name").val() != null){
            name = true;
            $("#name ~ span").remove();
            $("#name").after(trueStyle);
        }else {
            $("#name ~ span").remove();
            $("#name").after(falseStyle);
        }
    });
    //统一信用号
    $("#companyNo").keyup(function () {
        if($("#companyNo").val() != "" && $("#companyNo").val() != null){
            if(/^[0-9]*$/.test($("#companyNo").val())){
                $.ajaxSettings.async = false;
                $.get(
                    "companyClient/companyNoIsOnlyOnUpdate",
                    {"companyNo":$("#companyNo").val(), "id":$("#id").val()},
                    function (json) {
                        if(json){
                            companyNo = true;
                            $("#companyNo ~ span").remove();
                            $("#companyNo").after(trueStyle);
                        }else {
                            companyNo = false;
                            $("#companyNo ~ span").remove();
                            $("#companyNo").after(falseStyle);
                        }
                    },
                    "json"
                );
            }else {
                $("#companyNo ~ span").remove();
                $("#companyNo").after(falseStyle);
            }

        }else {
            $("#compantNo ~ span").remove();
            $("#companyNo").after(falseStyle);
        }
    });
    //公司电话
    $("#telPhone").keyup(function () {
        if($("#telPhone").val() != "" && $("#telPhone").val() != null){
            telPhone = /^\d{3,4}[\s,-]?\d{7,8}$/.test($("#telPhone").val());
            if(telPhone){
                $("#telPhone ~ span").remove();
                $("#telPhone").after(trueStyle);
            }else {
                $("#telPhone ~ span").remove();
                $("#telPhone").after(falseStyle);
            }
        }else  {
            $("#telPhone ~ span").remove();
            $("#telPhone").after(falseStyle);
        }
    });
    //邮编
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
        }
    });
    //法人
    $("#owner").keyup(function () {
        if($("#owner").val() != "" && $("#owner").val() != null){
            owner = true;
            $("#owner ~ span").remove();
            $("#owner").after(trueStyle);
        }else {
            $("#owner ~ span").remove();
            $("#owner").after(falseStyle);
        }
    });
    //身份证号
    $("#idCard").keyup(function () {
        if($("#idCard").val() != "" && $("#idCard") != null){
            idCard = /^[1-9][0-9,x|X]{14,17}$/.test($("#idCard").val());
            if(idCard){
                $("#idCard ~ span").remove();
                $("#idCard").after(trueStyle);
            }else {
                $("#idCard ~ span").remove();
                $("#idCard").after(falseStyle);
            }
        }else {
            $("#idCard ~ span").remove();
            $("#idCard").after(falseStyle);
        }
    });
    //法人手机
    $("#phone").keyup(function () {
        if($("#phone").val() != "" && $("#phone").val() != null){
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
        }
    });
    //电子邮件
    $("#email").keyup(function () {
        if($("#email").val() != "" && $("#email").val() != null){
            email = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test($("#email").val());
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
        }
    });
    //公司性质
    $("#ownerShip").keyup(function () {
        if($("#ownerShip").val() != "" && $("#ownerShip").val() != null){
            $("#ownerShip ~ span").remove();
            $("#ownerShip").after(trueStyle);
            ownerShip = true
        }else {
            $("#ownerShip ~ span").remove();
            $("#ownerShip").after(falseStyle);
        }
    });
    //公司地址
    $("#address").keyup(function () {
        if($("#address").val() != ""  && $("#address").val() != null){
            $("#address ~ span").remove();
            $("#address").after(trueStyle);
            address = true;
        }else {
            $("#address ~ span").remove();
            $("#address").after(falseStyle);
        }
    });

    $("input").keyup(function () {
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
    $("input").keyup();
    $("textarea").keyup();
    if(!(name && companyNo && telPhone && zipCode && owner && ownerShip && idCard && phone && email && address)){
        $("#btnSubmit").attr("disabled", "disabled");
    }else {
        $("#inputForm").submit();
    }
}


