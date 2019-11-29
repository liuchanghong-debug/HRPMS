var customerId = false;
var payMonth = false;

var trueStyle = "<span class=\"help-inline\"><span style=\"color: green; \">*</span></span>";
var falseStyle = "<span class=\"help-inline\"><span style=\"color: red; \">*</span></span>";

$(function () {
    //客户名称
    if($("#customerId").val() != "" && $("#customerId").val() != null){
        customerId = true;
        $("#customerId ~ span").remove();
        $("#customerId").after(trueStyle);
    }else {
        customerId = false;
        $("#customerId ~ span").remove();
        $("#customerId").after(falseStyle);
    }
    //缴费期间
    if($("#payMonth").val() != "" && $("#payMonth").val() != null){
        payMonth = true;
        $("#payMonth ~ span").remove();
        $("#payMonth").after(trueStyle);
    }else {
        payMonth = false;
        $("#payMonth ~ span").remove();
        $("#payMonth").after(falseStyle);
    }

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
    //页面加载时加载详细信息
    var onload = $("select").change();
});

//提交
function inputFormSubmit() {
    $("input").keyup();
    $("select").change();
    if(!(customerId && payMonth)){
        $("#btnSubmit").attr("disabled", "disabled");
    }else {
        $("#inputForm").submit();
    }
}


function getDetailMess(id) {
    if(id.value !== "" && id.value != null){
        $("#name").val(id.text);
        $.get(
            "shebao/getDetailMessBySocialInsuranceId",
            {"id":id.value},
            function (json) {
                var company = json.company;
                var customer = json.customer;
                var socialInsurance = json.socialInsurance;
                $("#name").val(socialInsurance.name);
                $("#idCard").val(socialInsurance.idCard);
                $("#sbCard").val(socialInsurance.sbCard);
                $("#companyName").val(company.name);
                $("#phone").val(customer.phone);
                $("#address").val(customer.address);
                $("#yangLao").val(socialInsurance.yangLao);
                $("#yiLiao").val(socialInsurance.yiLiao);
                $("#gongShang").val(socialInsurance.gongShang);
                $("#shiYe").val(socialInsurance.shiYe);
                $("#shengYu").val(socialInsurance.shengYu);
                $("#payMoney").val(socialInsurance.mustPay);
            },
            "json"
        );
    }else {
        $("#name").val(null);
        $("#idCard").val(null);
        $("#sbCard").val(null);
        $("#companyName").val(null);
        $("#phone").val(null);
        $("#address").val(null);
        $("#yangLao").val(null);
        $("#yiLiao").val(null);
        $("#gongShang").val(null);
        $("#shiYe").val(null);
        $("#shengYu").val(null);
        $("#payMoney").val(null);
    }
}