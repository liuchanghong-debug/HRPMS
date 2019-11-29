var sbCard = false;
var payDate = false;

var trueStyle = "<span class=\"help-inline\"><span style=\"color: green; \">*</span></span>";
var falseStyle = "<span class=\"help-inline\"><span style=\"color: red; \">*</span></span>";

$(function () {
    //社保卡
    $("#sbCard").keyup(function () {
        if($("#sbCard").val() != "" && $("#sbCard").val() != null){
            $.ajaxSettings.async = false;
            $.get(
                "shebao/shebaoSbCardIsOnlyUpdate",
                {"sbCard":$("#sbCard").val(), "id":$("#id").val()},
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
});

//提交
function inputFormSubmit() {
    $("input").keyup().change();
    if(!(sbCard && payDate)){
        $("#btnSubmit").attr("disabled", "disabled");
    }else {
        $("#inputForm").submit();
    }
}