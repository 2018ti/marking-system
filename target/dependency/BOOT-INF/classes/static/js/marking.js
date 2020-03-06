var textId = getQueryString("textId");

//获取从前一个界面传过来的文章id
function getQueryString(name) {
    var result = window.location.search.match(new RegExp("[\?\&]" + name + "=([^\&]+)", "i"));
    if (result == null || result.length < 1) {
        return "";
    }
    return result[1];
}


var wordleft = '';
var wordright = '';
var oContentleft = document.getElementById('text-left')
oContentleft.onmouseup = function() {
    wordleft = selectText(); //鼠标选中文字
};

$(document).ready(function() {

    $.ajax({
        url: "/getTxtById",
        method: "get",
        dataType: "json",
        data: {
            textId: textId,
        },
        success: function(data) {
            $("#text-left").text(data['content']);
        }
    })
    tippy('[data-tippy-content]');
});


function selectText() {
    if (document.Selection) {
        //ie浏览器
        return document.selection.createRange().text;
    } else {
        //标准浏览器
        return window.getSelection().toString();
    }
}



function marking(text, index, flag, color, tippycontent) {
    if (text == 'text-left') {
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
                return oldHTML.replace(wordleft, '<span data-tippy-content="' + tippycontent + '" style="color: ' + color + ';" id="' + flag + '">' + wordleft + '</span>');
            })
        }
        wordleft = '';
    } else {
        if (wordright != '') {
            $("#" + index + "").html(wordright);
            if ($("#" + flag + "").length > 0) {
                $('#' + text + '').html(function(i, oldHTML) {
                    //删除之前的标记
                    return oldHTML.replace($("#" + flag + "").prop("outerHTML"), $("#" + flag + "").text());
                })
            }
            $('#' + text + '').html(function(i, oldHTML) {
                //标记文本
                return oldHTML.replace(wordright, '<span data-tippy-content="' + tippycontent + '" style="color: ' + color + ';" id="' + flag + '">' + wordright + '</span>');
            })
        }
        wordright = '';
    }
    tippy('[data-tippy-content]');
}

function resetmarking(index) {
    $('#text-' + index + '').html(function(i, oldHTML) {
        return oldHTML.replace($('#text-' + index + '').html(), $('#text-' + index + '').text());
    })
    $("#t1-left").html("1")
    $("#t2-left").html("2")
    $("#t3-left").html("3")
    $("#t4-left").html("4")
}

//点击保存按钮时发送数据到后台
function save(index) {

    if ($("#t1-" + index + "").text() == "参与方1") { //根据之前生成的标签内容判断用户标记属于哪一种事件类型
        //标记内容
        var participant1 = $("#participant1-" + index + "").text();
        var participant2 = $("#participant2-" + index + "").text();
        var trigger = $("#trigger-" + index + "").text();
        var time = $("#time-" + index + "").text();
        var place = $("#place-" + index + "").text();

        $.ajax({
            url: "/marking/insertEventMarking",
            method: "post",
            datatype: "json",
            data: {
                trigger: trigger,
                participant1: participant1,
                participant2: participant2,
                time: time,
                place: place,
                textId: textId,
            },
            success: function(data) {
                if (data["msg"] == "标记成功" || data['msg'] == '更新成功') {
                    Swal.fire({
                        icon: 'success',
                        text: '保存成功',
                    })
                } else {
                    Swal.fire({
                        icon: 'error',
                        text: '请勿重复提交',
                    })
                }
            }
        })
    } else if ($("#t1-" + index + "").text() == "签署方") {
        $.ajax({
            url: "/marking/insertFileMarking",
            method: "post",
            datatype: "json",
            data: {
                trigger: $("#trigger-" + index + "").text(),
                signatory: $("#signatory-" + index + "").text(),
                file: $("#file-" + index + "").text(),
                time: $("#time-" + index + "").text(),
                place: $("#place-" + index + "").text(),
                textId: textId,
            },
            success: function(data) {
                if (data["msg"] == "标记成功" || data['msg'] == '更新成功') {
                    Swal.fire({
                        icon: 'success',
                        text: '保存成功',
                    })
                } else {
                    Swal.fire({
                        icon: 'error',
                        text: '请勿重复提交',
                    })
                }
            }
        })
    } else if ($("#t1-" + index + "").text() == "设施修建方") {
        $.ajax({
            url: "/marking/insertBuildMarking",
            method: "post",
            datatype: "json",
            data: {
                trigger: $("#trigger-" + index + "").text(),
                constructor: $("#constructor-" + index + "").text(),
                buildingName: $("#buildingName-" + index + "").text(),
                time: $("#time-" + index + "").text(),
                place: $("#place-" + index + "").text(),
                textId: textId,
            },
            success: function(data) {
                if (data["msg"] == "标记成功" || data['msg'] == '更新成功') {
                    Swal.fire({
                        icon: 'success',
                        text: '保存成功',
                    })
                } else {
                    Swal.fire({
                        icon: 'error',
                        text: '请勿重复提交',
                    })
                }
            }
        })
    } else {
        $.ajax({
            url: "/marking/insertActivityMarking",
            method: "post",
            datatype: "json",
            data: {
                trigger: $("#trigger-" + index + "").text(),
                holder: $("#holder-" + index + "").text(),
                name: $("#name-" + index + "").text(),
                time: $("#time-" + index + "").text(),
                place: $("#place-" + index + "").text(),
                textId: textId,
            },
            success: function(data) {
                if (data["msg"] == "标记成功" || data['msg'] == '更新成功') {
                    Swal.fire({
                        icon: 'success',
                        text: '保存成功',
                    })
                } else {
                    Swal.fire({
                        icon: 'error',
                        text: '请勿重复提交',
                    })
                }
            }
        })
    }
}

function init(event, index) {
    if ($("#t1-" + index + "").text() == '1') {
        if (event == '会见会谈') {
            $("#t1-" + index + "").html("参与方1");
            $("#t2-" + index + "").html("参与方2");
            $("#t3-" + index + "").html("时间");
            $("#t4-" + index + "").html("地点");
        } else if (event == '签署文件') {
            $("#t1-" + index + "").html("签署方");
            $("#t2-" + index + "").html("文件");
            $("#t3-" + index + "").html("时间");
            $("#t4-" + index + "").html("地点");
        } else if (event == '设施启用') {
            $("#t1-" + index + "").html("设施修建方");
            $("#t2-" + index + "").html("设施名称");
            $("#t3-" + index + "").html("启动时间");
            $("#t4-" + index + "").html("设施地点");
        } else {
            $("#t1-" + index + "").html("举办方");
            $("#t2-" + index + "").html("活动名称");
            $("#t3-" + index + "").html("活动地点");
            $("#t4-" + index + "").html("活动时间");
        }
    }
}