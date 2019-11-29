var name = false;
var idCard = false;
var jobIntentsion = false;
var forPrice = false;
var forAddress = false;

var trueStyle = "<span class=\"help-inline\"><span style=\"color: green; \">*</span></span>";
var falseStyle = "<span class=\"help-inline\"><span style=\"color: red; \">*</span></span>";


$(function () {
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
    $("#idCard").keyup(function () {
        if($("#idCard").val() != "" && $("#idCard").val() != null){
            $.get(
                "person/personIdCardIsOnly",
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
    });
    $("#jobIntentsion").keyup(function () {
        if($("#jobIntentsion").val() != "" && $("#jobIntentsion").val() != null){
            $("#jobIntentsion ~ span").remove();
            $("#jobIntentsion").after(trueStyle);
            jobIntentsion = true;
        }else {
            $("#jobIntentsion ~ span").remove();
            $("#jobIntentsion").after(falseStyle);
            jobIntentsion = false;
        }
    });
    $("#forPrice").keyup(function () {
        if($("#forPrice").val() != "" && $("#forPrice").val() != null){
            $("#forPrice ~ span").remove();
            $("#forPrice").after(trueStyle);
            forPrice = true;
        }else {
            $("#forPrice ~ span").remove();
            $("#forPrice").after(falseStyle);
            forPrice = false;
        }
    });
    $("#forAddress").keyup(function () {
        if($("#forAddress").val() != "" && $("#forAddress").val() != null){
            $("#forAddress ~ span").remove();
            $("#forAddress").after(trueStyle);
            forAddress = true;
        }else {
            $("#forAddress ~ span").remove();
            $("#forAddress").after(falseStyle);
            forAddress = false;
        }
    });

    $("input").keyup(function () {
        $("#btnSubmit").removeAttr("disabled");
    }).click(function () {
        $("#btnSubmit").removeAttr("disabled");
    });

});

function inputFormSubmit() {
    $("input").keyup();
    if(!(name && idCard && jobIntentsion && forPrice && forAddress)){
        $("#btnSubmit").attr("disabled", "disabled");
    }else {
        $("#inputForm").submit();
    }
}