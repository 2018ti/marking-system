$("#btn-submit").click = function() {
    $.ajax({
        type: "GET",
        url: "/login",
        dataType: "JSON",
        data: {
            name: $("#exampleInputEmail1").val(),
            password: $("#exampleInputPassword1").val(),
        },
        success: function(data) {
            if (data !== null) {
                window.location.href = "/success.html";

            }
        },
    });
}