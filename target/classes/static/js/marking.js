var word = '';
var color = ["red", "blue", "yellow", "pink", "purple"];
//选择文本内容
// $("#text").mouseup(function() {
//     var txt = window.getSelection ? window.getSelection() : document.selection.createRange().text;

//     word = txt.toString();
//     console.log(txt.toString());
//     console.log($.type(txt));
// })
// $("#text").mouseup(function() {
//     window.getSelection ? window.getSelection().removeAllRanges() : document.selection.empty();
// })
var oContent = document.getElementById('text');
oContent.onmouseup = function() {
    word = selectText();
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

$("#event1").click(function() { //点击“会见会谈”按钮，改变表格内容
    $("#w").html(word); //触发词
    $("#w1").html(''); //清空表格内容
    $("#w2").html('');
    $("#w3").html('');
    $("#w4").html('');
    $("#t1").html('参与方1');
    $('#text').html(function(i, oldHTML) {
        return oldHTML.replace(word, '<span style="color: red;" id="trigger">' + word + '</span>');
    })
    word = '';
    //console.log($("#t1").html());
    $("#event1_person1").click(function() { //点击“参与方1”按钮
        $("#w1").html(word);
        //console.log($("#t1").html());
        $('#text').html(function(i, oldHTML) {
            return oldHTML.replace(word, '<span style="color: orange;" id="participant1">' + word + '</span>');
        })
        word = '';
    })
    $("#t2").html('参与方2');
    $("#event1_person2").click(function() { //点击“参与方2”按钮
        $("#w2").html(word);
        $('#text').html(function(i, oldHTML) {
            return oldHTML.replace(word, '<span style="color: green;" id="participant2">' + word + '</span>');
        })
        word = '';
    })
    $("#t3").html('时间');
    $("#event1_date").click(function() { //点击“时间”按钮
        $("#w3").html(word);
        $('#text').html(function(i, oldHTML) {
            return oldHTML.replace(word, '<span style="color: purple;" id="time">' + word + '</span>');
        })
        word = '';
    })
    $("#t4").html('地点');
    $("#event1_place").click(function() { //点击“地点”按钮
        $("#w4").html(word);
        $('#text').html(function(i, oldHTML) {
            return oldHTML.replace(word, '<span style="color: blue;" id="place">' + word + '</span>');
        })
        word = '';
    })
})
$("#event2").click(function() {
    $("#w").html(word);
    $("#w1").html('');
    $("#w2").html('');
    $("#w3").html('');
    $("#w4").html('');
    $("#t1").html('签署方');
    $('#text').html(function(i, oldHTML) {
        return oldHTML.replace(word, '<span style="color: red;" id="trigger">' + word + '</span>');
    })
    word = '';
    //console.log($("#t1").html());
    $("#event2_person").click(function() { //点击“参与方1”按钮
        $("#w1").html(word);
        //console.log($("#t1").html());
        $('#text').html(function(i, oldHTML) {
            return oldHTML.replace(word, '<span style="color: orange;" id="signatory">' + word + '</span>');
        })
        word = '';
    })
    $("#t2").html('文件');
    $("#event2_document").click(function() { //点击“参与方2”按钮
        $("#w2").html(word);
        $('#text').html(function(i, oldHTML) {
            return oldHTML.replace(word, '<span style="color: purple;" id="file">' + word + '</span>');
        })
        word = '';
    })
    $("#t3").html('签署时间');
    $("#event2_date").click(function() { //点击“时间”按钮
        $("#w3").html(word);
        $('#text').html(function(i, oldHTML) {
            return oldHTML.replace(word, '<span style="color: green;" id="time">' + word + '</span>');
        })
        word = '';
    })
    $("#t4").html('签署地点');
    $("#event2_place").click(function() { //点击“地点”按钮
        $("#w4").html(word);
        $('#text').html(function(i, oldHTML) {
            return oldHTML.replace(word, '<span style="color: blue;" id="place">' + word + '</span>');
        })
        word = '';
    })
})
$("#event3").click(function() {
    $("#w").html(word);
    $("#w1").html('');
    $("#w2").html('');
    $("#w3").html('');
    $("#w4").html('');
    $("#t1").html('设施修建方');
    $('#text').html(function(i, oldHTML) {
        return oldHTML.replace(word, '<span style="color: red;" id="trigger">' + word + '</span>');
    })
    word = '';
    //console.log($("#t1").html());
    $("#event3_person").click(function() { //点击“参与方1”按钮
        $("#w1").html(word);
        //console.log($("#t1").html());
        $('#text').html(function(i, oldHTML) {
            return oldHTML.replace(word, '<span style="color: green;" id="constructor">' + word + '</span>');
        })
        word = '';
    })
    $("#t2").html('设施名称');
    $("#event3_name").click(function() { //点击“参与方2”按钮
        $("#w2").html(word);
        $('#text').html(function(i, oldHTML) {
            return oldHTML.replace(word, '<span style="color: orange;" id="buildingName">' + word + '</span>');
        })
        word = '';
    })
    $("#t3").html('启动时间');
    $("#event3_date").click(function() { //点击“时间”按钮
        $("#w3").html(word);
        $('#text').html(function(i, oldHTML) {
            return oldHTML.replace(word, '<span style="color: blue;" id="time">' + word + '</span>');
        })
        word = '';
    })
    $("#t4").html('设施地点');
    $("#event3_place").click(function() { //点击“地点”按钮
        $("#w4").html(word);
        $('#text').html(function(i, oldHTML) {
            return oldHTML.replace(word, '<span style="color: purple;" id="place">' + word + '</span>');
        })
        word = '';
    })
})
$("#event4").click(function() {
    $("#w").html(word);
    $("#w1").html('');
    $("#w2").html('');
    $("#w3").html('');
    $("#w4").html('');
    $("#t1").html('举办方');
    $('#text').html(function(i, oldHTML) {
        return oldHTML.replace(word, '<span style="color: red;" id="trigger">' + word + '</span>');
    })
    word = '';
    //console.log($("#t1").html());
    $("#event4_person").click(function() { //点击“举办方”按钮
        $("#w1").html(word);
        $('#text').html(function(i, oldHTML) {
                return oldHTML.replace(word, '<span style="color: green;" id="holder">' + word + '</span>');
            })
            //console.log($("#t1").html());
        word = '';
    })
    $("#t2").html('活动名称');
    $("#event4_name").click(function() { //点击“活动名称”按钮
        $("#w2").html(word);
        $('#text').html(function(i, oldHTML) {
            return oldHTML.replace(word, '<span style="color: orange;" id="name">' + word + '</span>');
        })
        word = '';
    })
    $("#t3").html('活动地点');
    $("#event4_place").click(function() { //点击“活动地点”按钮
        $("#w3").html(word);
        $('#text').html(function(i, oldHTML) {
            return oldHTML.replace(word, '<span style="color: blue;" id="place">' + word + '</span>');
        })
        word = '';
    })
    $("#t4").html('活动时间');
    $("#event4_date").click(function() { //点击“活动时间”按钮
        $("#w4").html(word);
        $('#text').html(function(i, oldHTML) {
            return oldHTML.replace(word, '<span style="color: purple;" id="time">' + word + '</span>');
        })
        word = '';
    })
})