var leader;
var textId = getQueryString("textId");

//获取从前一个界面传过来的文章id
function getQueryString(name) {
    var result = window.location.search.match(new RegExp("[\?\&]" + name + "=([^\&]+)", "i"));
    if (result == null || result.length < 1) {
        return "";
    }
    return result[1];
}

$(document).ready(function() {
    $.ajax({
        url: "/getLeader",
        dataType: "JSON",
        method: "get",
        success: function(data) {
            leader = data;
            $.ajax({
                url: "/listMember",
                method: "get",
                dataType: "json",
                data: {
                    groupId: leader["groupId"]
                },
                success: function(data) {
                    for (var member in data) {
                        $("#select-left").append('<option value =' + data[member].name + '>' + data[member].name + '</option>')
                        $("#select-right").append('<option value =' + data[member].name + '>' + data[member].name + '</option>')
                    }
                }
            })
        },
        error: function(message) {}
    });

});

var select = function(flag) {
    var select;
    var textarea;
    if (flag == 'left') {
        select = "select-left"
        textarea = "text-left"
    } else {
        select = "select-right"
        textarea = "text-right"
    }
    var $select = $("#" + select + "")
    var $text = $("#" + textarea + "")
    var name = $select.val();
    $.ajax({
        url: "/getByTextIdAndUser",
        method: "get",
        dataType: "json",
        data: {
            name: name,
            textId: textId,
        },
        success: function(data) {
            var content = data['content']
            $text.text(content)
            if (data["eventType"] == '会见会谈') {
                $text.html(function(i, oldHTML) {
                    return oldHTML.replace(data['trigger'], '<span style="color: red;" id="trigger">' + data['trigger'] + '</span>');
                })
                $text.html(function(i, oldHTML) {
                    return oldHTML.replace(data['marking1'], '<span style="color: orange;" id="participant1">' + data['marking1'] + '</span>');
                })
                $text.html(function(i, oldHTML) {
                    return oldHTML.replace(data['marking2'], '<span style="color: green;" id="participant2">' + data['marking2'] + '</span>');
                })
                $text.html(function(i, oldHTML) {
                    return oldHTML.replace(data['marking3'], '<span style="color: purple;" id="time">' + data['marking3'] + '</span>');
                })
                $text.html(function(i, oldHTML) {
                    return oldHTML.replace(data['marking4'], '<span style="color: blue;" id="place">' + data['marking4'] + '</span>');
                })
                $("#t1").html('参与方1');
                $("#t2").html('参与方2');
                $("#t3").html('时间');
                $("#t4").html('地点');
                //---------------------------签署文件
            } else if (data["eventType"] == '签署文件') {
                $text.html(function(i, oldHTML) {
                    return oldHTML.replace(data['trigger'], '<span style="color: red;" id="trigger">' + data['trigger'] + '</span>');
                })
                $text.html(function(i, oldHTML) {
                    return oldHTML.replace(data['marking1'], '<span style="color: orange;" id="signatory">' + data['marking1'] + '</span>');
                })
                $text.html(function(i, oldHTML) {
                    return oldHTML.replace(data['marking2'], '<span style="color: purple;" id="file">' + data['marking2'] + '</span>');
                })
                $text.html(function(i, oldHTML) {
                    return oldHTML.replace(data['marking3'], '<span style="color: green;" id="time">' + data['marking3'] + '</span>');
                })
                $text.html(function(i, oldHTML) {
                    return oldHTML.replace(data['marking4'], '<span style="color: blue;" id="place">' + data['marking4'] + '</span>');
                })
                $("#t1").html('签署方');
                $("#t2").html('文件');
                $("#t3").html('签署时间');
                $("#t4").html('签署地点');

            } else if (data["eventType"] == '设施启用') {
                $text.html(function(i, oldHTML) {
                    return oldHTML.replace(data['trigger'], '<span style="color: red;" id="trigger">' + data['trigger'] + '</span>');
                })
                $text.html(function(i, oldHTML) {
                    return oldHTML.replace(data['marking1'], '<span style="color: green;" id="constructor">' + data['marking1'] + '</span>');
                })
                $text.html(function(i, oldHTML) {
                    return oldHTML.replace(data['marking2'], '<span style="color: orange;" id="buildingName">' + data['marking2'] + '</span>');
                })
                $text.html(function(i, oldHTML) {
                    return oldHTML.replace(data['marking3'], '<span style="color: blue;" id="time">' + data['marking3'] + '</span>');
                })
                $text.html(function(i, oldHTML) {
                    return oldHTML.replace(data['marking4'], '<span style="color: purple;" id="place">' + data['marking4'] + '</span>');
                })
                $("#t1").html('设施修建方');
                $("#t2").html('设施名称');
                $("#t3").html('启动时间');
                $("#t4").html('设施地点');
            } else {
                $text.html(function(i, oldHTML) {
                    return oldHTML.replace(data['trigger'], '<span style="color: red;" id="trigger">' + data['trigger'] + '</span>');
                })
                $text.html(function(i, oldHTML) {
                    return oldHTML.replace(data['marking1'], '<span style="color: green;" id="holder">' + data['marking1'] + '</span>');
                })
                $text.html(function(i, oldHTML) {
                    return oldHTML.replace(data['marking2'], '<span style="color: orange;" id="name">' + data['marking2'] + '</span>');
                })
                $text.html(function(i, oldHTML) {
                    return oldHTML.replace(data['marking3'], '<span style="color: blue;" id="place">' + data['marking3'] + '</span>');
                })
                $text.html(function(i, oldHTML) {
                    return oldHTML.replace(data['marking4'], '<span style="color: purple;" id="time">' + data['marking4'] + '</span>');
                })
                $("#t1").html('举办方');
                $("#t2").html('活动名称');
                $("#t3").html('活动地点');
                $("#t4").html('活动时间');
            }
        }
    })

}