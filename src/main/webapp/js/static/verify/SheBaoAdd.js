var customerId = false;
var sbCard = false;
var payDate = false;

var trueStyle = "<span class=\"help-inline\"><span style=\"color: green; \">*</span></span>";
var falseStyle = "<span class=\"help-inline\"><span style=\"color: red; \">*</span></span>";

$(function () {
    $.get(
        "shebao/getCustomerNotInSocia",
        function (json) {
            var str = "";
            for (var i = 0; i < json.length; i++) {
                str += "<option value='" + json[i].id +  "'>" + json[i].name + "</option>";
            }
            $("#customerId").append(str);
        },
        "json"
    );
    //客户名称
    $("#customerId").change(function () {
        if($("#customerId").val() != "" && $("#customerId").val() != null){
            customerId = true;
            $("#customerId ~ span").remove();
            $("#customerId").after(trueStyle);
        }else {
            customerId = false;
            $("#customerId ~ span").remove();
            $("#customerId").after(falseStyle);
        }
    });
    //社保卡
    $("#sbCard").keyup(function () {
        if($("#sbCard").val() != "" && $("#sbCard").val() != null){
            $.ajaxSettings.async = false;
            $.get(
                "shebao/shebaoSbCardIsOnly",
                {"sbCard":$("#sbCard").val()},
                function (json) {
                    if(json){
                        sbCard = true;
                        $("#sbCard ~ span").remove();
                        $("#sbCard").after(trueStyle);
                    }else {
                        sbCard = false;
                        $("#sbCard ~ span").remove();
                        $("#sbCard").after(falseStyle);
                    }
                },
                "json"
            );
        }else {
            sbCard = false;
            $("#sbCard ~ span").remove();
            $("#sbCard").after(falseStyle);
        }
    });
    // 预交款日
    $("#payDate").change(function () {
        if($("#payDate").val() != "" && $("#payDate").val() != null){
            payDate = /^\d{4}-\d{1,2}-\d{1,2}$/.test($("#payDate").val());
            if(payDate){
                $("#payDate ~ span").remove();
                $("#payDate").after(trueStyle);
            }else {
                $("#payDate ~ span").remove();
                $("#payDate").after(falseStyle);
            }
        }else {
            payDate = false;
            $("#payDate ~ span").remove();
            $("#payDate").after(falseStyle);
        }
    });

    $("input").keyup(function () {
        $("#btnSubmit").removeAttr("disabled");
    }).change(function () {
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

//提交
function inputFormSubmit() {
    $("input").keyup();
    $("input").change();
    $("select").change();
    if(!(customerId && sbCard && payDate)){
        $("#btnSubmit").attr("disabled", "disabled");
    }else {
        $("#inputForm").submit();
    }
}






function getDetailOfCustomer(id) {
    if(id !== "" && id != null){
        $.get(
            "shebao/getDetailOfCustomerById",
            {"id":id},
            function (json) {
                $("#name").val(json.name);
                $("#idCard").val(json.idCard);
                if(json.sex === "男"){
                    $("#sex").empty().append("<input type=\"radio\" name=\"sex\"  value=\"男\" checked disabled>男\n" +
                        "\t\t\t\t<input type=\"radio\" name=\"sex\" value=\"女\" disabled>女");
                }
                if(json.sex === "女"){
                    $("#sex").empty().append("<input type=\"radio\" name=\"sex\"  value=\"男\" disabled>男\n" +
                        "\t\t\t\t<input type=\"radio\" name=\"sex\" value=\"女\" checked disabled>女");
                }
                $("#birthday").val(json.birthday);
                $("#phone").val(json.phone);
                $("#email").val(json.email);
                $("#address").val(json.address);
                $("#zipCode").val(json.zipCode);
                $("#school").val(json.school);
                $("#specialty").val(json.specialty);
                $("#graduation").val(json.graduation);
                $("#companyId").val(json.companyId);
                $("#companyName").val(json.companyName);
                $("#basePay").val(json.salary);
                getPayData(json.salary);
            },
            "json"
        );
    }else {
        $("#name").val(null);
        $("#idCard").val(null);
        $("#sex").empty().append("<input type=\"radio\" name=\"sex\"  value=\"男\" disabled>男\n" +
            "\t\t\t\t<input type=\"radio\" name=\"sex\" value=\"女\" disabled>女");
        $("#birthday").val(null);
        $("#phone").val(null);
        $("#email").val(null);
        $("#address").val(null);
        $("#zipCode").val(null);
        $("#school").val(null);
        $("#specialty").val(null);
        $("#graduation").val(null);
        $("#companyId").val(null);
        $("#companyName").val(null);
        $("#basePay").val(null);
    }
}
function getPayData(payData) {
    $("#mustPay").val((payData * 0.413).toFixed(2));
    $("#yangLao").val((payData * 0.27).toFixed(2));
    $("#yiLiao").val((payData * 0.12).toFixed(2));
    $("#gongShang").val((payData * 0.005).toFixed(2));
    $("#shiYe").val((payData * 0.01).toFixed(2));
    $("#shengYu").val((payData * 0.008).toFixed(2));
}