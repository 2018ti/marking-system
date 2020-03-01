var leader;
var textId = getQueryString("textId");


var wordleft = '';
var wordright = '';
var oContentleft = document.getElementById('text-left')
oContentleft.onmouseup = function() {
    wordleft = selectText(); //鼠标选中文字
};

function selectText() {
    if (document.Selection) {
        //ie浏览器
        return document.selection.createRange().text;
    } else {
        //标准浏览器
        return window.getSelection().toString();
    }
}
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
//数据渲染
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
                    return oldHTML.replace(data['trigger'], '<span style="color: red;" id="trigger-' + flag + '">' + data['trigger'] + '</span>');
                })
                $text.html(function(i, oldHTML) {
                    return oldHTML.replace(data['marking1'], '<span style="color: orange;" id="participant1-' + flag + '">' + data['marking1'] + '</span>');
                })
                $text.html(function(i, oldHTML) {
                    return oldHTML.replace(data['marking2'], '<span style="color: green;" id="participant2-' + flag + '">' + data['marking2'] + '</span>');
                })
                $text.html(function(i, oldHTML) {
                    return oldHTML.replace(data['marking3'], '<span style="color: purple;" id="time-' + flag + '">' + data['marking3'] + '</span>');
                })
                $text.html(function(i, oldHTML) {
                    return oldHTML.replace(data['marking4'], '<span style="color: blue;" id="place-' + flag + '">' + data['marking4'] + '</span>');
                })
                $("#t1-" + flag + "").html('参与方1');
                $("#t2-" + flag + "").html('参与方2');
                $("#t3-" + flag + "").html('时间');
                $("#t4-" + flag + "").html('地点');
                //---------------------------签署文件
            } else if (data["eventType"] == '签署文件') {
                $text.html(function(i, oldHTML) {
                    return oldHTML.replace(data['trigger'], '<span style="color: red;" id="trigger-' + flag + '">' + data['trigger'] + '</span>');
                })
                $text.html(function(i, oldHTML) {
                    return oldHTML.replace(data['marking1'], '<span style="color: orange;" id="signatory-' + flag + '">' + data['marking1'] + '</span>');
                })
                $text.html(function(i, oldHTML) {
                    return oldHTML.replace(data['marking2'], '<span style="color: purple;" id="file-' + flag + '">' + data['marking2'] + '</span>');
                })
                $text.html(function(i, oldHTML) {
                    return oldHTML.replace(data['marking3'], '<span style="color: green;" id="time-' + flag + '">' + data['marking3'] + '</span>');
                })
                $text.html(function(i, oldHTML) {
                    return oldHTML.replace(data['marking4'], '<span style="color: blue;" id="place-' + flag + '">' + data['marking4'] + '</span>');
                })
                $("#t1-" + flag + "").html('签署方');
                $("#t2-" + flag + "").html('文件');
                $("#t3-" + flag + "").html('签署时间');
                $("#t4-" + flag + "").html('签署地点');

            } else if (data["eventType"] == '设施启用') {
                $text.html(function(i, oldHTML) {
                    return oldHTML.replace(data['trigger'], '<span style="color: red;" id="trigger-' + flag + '">' + data['trigger'] + '</span>');
                })
                $text.html(function(i, oldHTML) {
                    return oldHTML.replace(data['marking1'], '<span style="color: green;" id="constructor-' + flag + '">' + data['marking1'] + '</span>');
                })
                $text.html(function(i, oldHTML) {
                    return oldHTML.replace(data['marking2'], '<span style="color: orange;" id="buildingName-' + flag + '">' + data['marking2'] + '</span>');
                })
                $text.html(function(i, oldHTML) {
                    return oldHTML.replace(data['marking3'], '<span style="color: blue;" id="time-' + flag + '">' + data['marking3'] + '</span>');
                })
                $text.html(function(i, oldHTML) {
                    return oldHTML.replace(data['marking4'], '<span style="color: purple;" id="place-' + flag + '">' + data['marking4'] + '</span>');
                })
                $("#t1-" + flag + "").html('设施修建方');
                $("#t2-" + flag + "").html('设施名称');
                $("#t3-" + flag + "").html('启动时间');
                $("#t4-" + flag + "").html('设施地点');
            } else {
                $text.html(function(i, oldHTML) {
                    return oldHTML.replace(data['trigger'], '<span style="color: red;" id="trigger-' + flag + '">' + data['trigger'] + '</span>');
                })
                $text.html(function(i, oldHTML) {
                    return oldHTML.replace(data['marking1'], '<span style="color: green;" id="holder-' + flag + '">' + data['marking1'] + '</span>');
                })
                $text.html(function(i, oldHTML) {
                    return oldHTML.replace(data['marking2'], '<span style="color: orange;" id="name-' + flag + '">' + data['marking2'] + '</span>');
                })
                $text.html(function(i, oldHTML) {
                    return oldHTML.replace(data['marking3'], '<span style="color: blue;" id="place-' + flag + '">' + data['marking3'] + '</span>');
                })
                $text.html(function(i, oldHTML) {
                    return oldHTML.replace(data['marking4'], '<span style="color: purple;" id="time-' + flag + '">' + data['marking4'] + '</span>');
                })
                $("#t1-" + flag + "").html('举办方');
                $("#t2-" + flag + "").html('活动名称');
                $("#t3-" + flag + "").html('活动地点');
                $("#t4-" + flag + "").html('活动时间');
            }
            $("#w-" + flag + "").html(data['trigger']);
            $("#w1-" + flag + "").html(data['marking1']);
            $("#w2-" + flag + "").html(data['marking2']);
            $("#w3-" + flag + "").html(data['marking3']);
            $("#w4-" + flag + "").html(data['marking4']);
        }
    })
}

function marking(text, index, flag, color) {
    if (wordleft != '') {
        $("#" + index + "").html(wordleft);
        if ($("#" + flag + "").length > 0) {
            $('#' + text + '').html(function(i, oldHTML) {
                //删除之前的标记
                return oldHTML.replace($("#" + flag + "").prop("outerHTML"), $("#" + flag + "").text());
            })
        }
        $('#' + text + '').html(function(i, oldHTML) {
            //标记文本
            return oldHTML.replace(wordleft, '<span style="color: ' + color + ';" id="' + flag + '">' + wordleft + '</span>');
        })
    }
    wordleft = '';
}