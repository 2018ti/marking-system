var leader;

window.onload = function() {
    $("#select").hide();
    $("#loadtext").hide();
}


jQuery(function($) {

    // Dropdown menu
    $(".sidebar-dropdown > a").click(function() {
        $(".sidebar-submenu").slideUp(200);
        if ($(this).parent().hasClass("active")) {
            $(".sidebar-dropdown").removeClass("active");
            $(this).parent().removeClass("active");
        } else {
            $(".sidebar-dropdown").removeClass("active");
            $(this).next(".sidebar-submenu").slideDown(200);
            $(this).parent().addClass("active");
        }

    });

    //toggle sidebar
    $("#toggle-sidebar").click(function() {
        $(".page-wrapper").toggleClass("toggled");
    });
    //Pin sidebar
    $("#pin-sidebar").click(function() {
        if ($(".page-wrapper").hasClass("pinned")) {
            // unpin sidebar when hovered
            $(".page-wrapper").removeClass("pinned");
            $("#sidebar").unbind("hover");
        } else {
            $(".page-wrapper").addClass("pinned");
            $("#sidebar").hover(
                function() {
                    console.log("mouseenter");
                    $(".page-wrapper").addClass("sidebar-hovered");
                },
                function() {
                    console.log("mouseout");
                    $(".page-wrapper").removeClass("sidebar-hovered");
                }
            )

        }
    });


    //toggle sidebar overlay
    $("#overlay").click(function() {
        $(".page-wrapper").toggleClass("toggled");
    });

    //switch between themes 
    var themes = "default-theme legacy-theme chiller-theme ice-theme cool-theme light-theme";
    $('[data-theme]').click(function() {
        $('[data-theme]').removeClass("selected");
        $(this).addClass("selected");
        $('.page-wrapper').removeClass(themes);
        $('.page-wrapper').addClass($(this).attr('data-theme'));
    });

    // switch between background images
    var bgs = "bg1 bg2 bg3 bg4";
    $('[data-bg]').click(function() {
        $('[data-bg]').removeClass("selected");
        $(this).addClass("selected");
        $('.page-wrapper').removeClass(bgs);
        $('.page-wrapper').addClass($(this).attr('data-bg'));
    });

    // toggle background image
    $("#toggle-bg").change(function(e) {
        e.preventDefault();
        $('.page-wrapper').toggleClass("sidebar-bg");
    });

    // toggle border radius
    $("#toggle-border-radius").change(function(e) {
        e.preventDefault();
        $('.page-wrapper').toggleClass("border-radius-on");
    });

    //custom scroll bar is only used on desktop
    if (!/Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent)) {
        $(".sidebar-content").mCustomScrollbar({
            axis: "y",
            autoHideScrollbar: true,
            scrollInertia: 300
        });
        $(".sidebar-content").addClass("desktop");

    }
});

$(document).ready(function() {

    $("#createGroup").hide();
    $.ajax({
        url: "/getLeader",
        dataType: "JSON",
        method: "get",
        success: function(data) {
            leader = data;
            $("#Leadername").text(leader['name'])
            if (leader['groupId'] == null) {
                $("#havingGroup").hide();
            } else {
                $("#noGroup").hide();
            }
        }
    })

})
var ListGroupMember = function() {

    $('#tabAgree').bootstrapTable('destroy');
    $('#tabGroup').bootstrapTable('destroy');
    $('#tabText').bootstrapTable('destroy');
    $('#markedText').bootstrapTable('destroy');

    $('#select').hide();
    $('#loadtext').hide();
    $("#createGroup").hide();
    $('#tabGroup').bootstrapTable({
        url: "/listMember",
        method: 'get',
        queryParams: {
            groupId: leader['groupId'],
        },

        toolbar: "#toolbar",
        sidePagination: "true",
        striped: true, // 是否显示行间隔色
        //search : "true",
        uniqueId: "ID",
        pageSize: "4",

        pagination: true, // 是否分页
        sortable: true, // 是否启用排序

        columns: [{
            field: 'name',
            title: '组员名'
        }, {
            field: 'textId',
            title: '操作',
            width: 120,
            align: 'center',
            valign: 'middle',
            formatter: actionFormatter,
        }, ]
    });



    function actionFormatter(value, row, index) {
        var userId = row.id;
        var result = "";
        result += "<div class='btn-group btn-group-xs'>"
        result += "<button class='btn btn-primary' style='margin-right: 20px;' onclick=\"DeleteMember('" + userId + "')\" title='操作'>删除</button>";
        result += "</div>"
        return result;
    }

}
var ListMyText = function() {
    $('#tabAgree').bootstrapTable('destroy');
    $('#tabGroup').bootstrapTable('destroy');
    $('#tabText').bootstrapTable('destroy');
    $('#markedText').bootstrapTable('destroy');
    $('#select').hide();
    $('#loadtext').hide();
    $("#createGroup").hide();
    $('#tabText').bootstrapTable({
        url: "/listText",
        method: 'get',
        queryParams: {
            leaderName: leader['name'],
        },

        toolbar: "#toolbar",
        sidePagination: "true",
        striped: true, // 是否显示行间隔色
        //search : "true",
        uniqueId: "ID",
        pageSize: "4",

        pagination: true, // 是否分页
        sortable: true, // 是否启用排序

        columns: [{
                field: 'loadTime',
                title: '上传日期'
            }, {
                field: 'content',
                title: '文章内容'
            },
            {
                field: 'agreeRate',
                title: '标注一致性',
                width: 120,
                formatter: function(value, row, index) {
                    if (value == null) {
                        value = '此文章还未被所有人标记';
                        return value;
                    } else return value;
                }
            }, {
                field: 'textId',
                title: '操作',
                width: 120,
                align: 'center',
                valign: 'middle',
                formatter: actionFormatter,
            }

        ]
    });


    function actionFormatter(value, row, index) {
        var textId = row.textId;
        var result = "";
        result += "<div class='btn-group btn-group-xs'>"
        result += "<button class='btn btn-primary' style='margin-right: 20px;' onclick=\"download('" + row.title + "','" + row.content + "')\" title='操作'>下载</button>";
        result += "</div>"
        return result;
    }

}

function download(filename, text) {
    filename += '.txt';
    var pom = document.createElement('a');
    pom.setAttribute('href', 'data:text/plain;charset=utf-8,' + encodeURIComponent(text));
    pom.setAttribute('download', filename);

    if (document.createEvent) {
        var event = document.createEvent('MouseEvents');
        event.initEvent('click', true, true);
        pom.dispatchEvent(event);
    } else {
        pom.click();
    }
}

var selectByAgree = function() {
    $('#tabAgree').bootstrapTable('destroy');
    $('#tabGroup').bootstrapTable('destroy');
    $('#tabText').bootstrapTable('destroy');
    $('#markedText').bootstrapTable('destroy');
    $('#select').show();
    $('#loadtext').hide();
    $("#createGroup").hide();

    $("#selectByAgreeButton").click(function() {

        $('#tabAgree').bootstrapTable({
            url: "/getTextByK",
            method: 'get',
            queryParams: {
                K: $("#K").val(),
            },

            toolbar: "#toolbar",
            sidePagination: "true",
            striped: true, // 是否显示行间隔色
            //search : "true",
            uniqueId: "ID",
            pageSize: "4",

            pagination: true, // 是否分页
            sortable: true, // 是否启用排序

            columns: [{
                    field: 'loadTime',
                    title: '上传日期'
                }, {
                    field: 'content',
                    title: '文章内容'
                },
                {
                    field: 'agreeRate',
                    title: '标注一致性',

                }, {
                    field: 'textId',
                    title: '操作',
                    width: 120,
                    align: 'center',
                    valign: 'middle',
                    formatter: function(value, row, index) {
                        var result = "";
                        result += "<div class='btn-group btn-group-xs'>"
                        result += "<button class='btn btn-primary' onclick=\"edit('" + row.textId + "','" + row.title + "')\" >编辑</button>";
                        result += "</div>"
                        return result;
                    }
                }

            ]
        });

    })
}
var edit = function(textId, title) {
    window.location.href = "/leaderEdit.html?textId=" + textId + "&title=" + title + "";
}
var ListMarkedText = function() {
    $('#tabAgree').bootstrapTable('destroy');
    $('#tabGroup').bootstrapTable('destroy');
    $('#tabText').bootstrapTable('destroy');
    $('#markedText').bootstrapTable('destroy');
    $('#select').hide();
    $('#loadtext').hide();
    $("#createGroup").hide();
    $('#markedText').bootstrapTable({
        url: "/listMarkedText",
        queryParams: "queryParams",
        toolbar: "#toolbar",
        sidePagination: "true",
        striped: true, // 是否显示行间隔色
        //search : "true",
        uniqueId: "ID",
        pageSize: "3",
        pagination: true, // 是否分页
        sortable: true, // 是否启用排序
        columns: [{
            field: 'title',
            title: '文件名'
        }, {
            field: 'content',
            title: '文本内容',
            formatter: function(value, row, index) {
                var marking1 = row.marking1;
                var marking2 = row.marking2;
                var marking3 = row.marking3;
                var marking4 = row.marking4;
                var trigger = row.trigger;
                var eventType = row.eventType;
                value = value.replace(trigger, '<span style="color: red;">' + trigger + '</span>');
                if (eventType == '会见会谈') {
                    value = value.replace(marking1, '<span style="color: orange;">' + marking1 + '</span>');
                    value = value.replace(marking2, '<span style="color: green;">' + marking2 + '</span>');
                    value = value.replace(marking3, '<span style="color: purple;">' + marking3 + '</span>');
                    value = value.replace(marking4, '<span style="color: blue;">' + marking4 + '</span>');
                    return value;
                } else if (eventType == '签署文件') {
                    value = value.replace(marking1, '<span style="color: orange;">' + marking1 + '</span>');
                    value = value.replace(marking2, '<span style="color: purple;">' + marking2 + '</span>');
                    value = value.replace(marking3, '<span style="color: green;">' + marking3 + '</span>');
                    value = value.replace(marking4, '<span style="color: blue;">' + marking4 + '</span>');
                    return value;
                } else if (eventType == '设施启用') {
                    value = value.replace(marking1, '<span style="color: green;">' + marking1 + '</span>');
                    value = value.replace(marking2, '<span style="color: orange;">' + marking2 + '</span>');
                    value = value.replace(marking3, '<span style="color: blue;">' + marking3 + '</span>');
                    value = value.replace(marking4, '<span style="color: purple;">' + marking4 + '</span>');
                    return value;
                } else {
                    value = value.replace(marking1, '<span style="color: green;">' + marking1 + '</span>');
                    value = value.replace(marking2, '<span style="color: orange;">' + marking2 + '</span>');
                    value = value.replace(marking3, '<span style="color: blue;">' + marking3 + '</span>');
                    value = value.replace(marking4, '<span style="color: purple;">' + marking4 + '</span>');
                    return value;
                }
            }
        }, {
            field: 'eventType',
            title: '事件类型',
        }, {
            field: 'textId',
            title: '操作',
            width: 120,
            align: 'center',
            valign: 'middle',
            formatter: actionFormatter,
        }, ]
    });

    function actionFormatter(value, row, index) {
        var markingId = row.markingId;
        var result = "";
        result += "<div class='btn-group btn-group-xs'>"
        result += "<a class='btn btn-warning' href='/download?eventType=" + row.eventType + "&trigger=" + row.trigger + "&marking1=" + row.marking1 + "&marking2=" + row.marking2 + "&marking3=" + row.marking3 + "&marking4=" + row.marking4 + "&textId=" + row.textId + "&markingId=" + row.markingId + "&content=" + row.content + "&title=" + row.title + "'>导出</a>";
        result += "</div>"
        return result;
    }
}
var loadnewtext = function() {
    $('#tabAgree').bootstrapTable('destroy');
    $('#tabGroup').bootstrapTable('destroy');
    $('#tabText').bootstrapTable('destroy');
    $('#markedText').bootstrapTable('destroy');
    $('#select').hide();
    $('#loadtext').show();
    $("#createGroup").hide();
    $("#btn-submit").click(function() {
        if ($("#text").text() == '') {
            Swal.fire({
                icon: 'error',
                text: '文本为空请先选择文本',
            })

        } else {
            $.ajax({
                method: "POST",
                url: "/loadText",
                dataType: "text",
                data: {
                    content: $("#text").text(),
                    title: $("#filename").text(),
                },
                success: function(data) {
                    Swal.fire({
                        icon: 'success',
                        text: '上传成功',
                    })
                },
            })

        }
    })
}

function upload(input) {
    //支持chrome IE10
    if (window.FileReader) {
        var file = input.files[0];
        filename = file.name.split(".")[0];
        var reader = new FileReader();
        reader.onload = function() {
                $("#text").text(this.result);
                $("#filename").text(filename);
            }
            //      reader.readAsText(file,"UTF-8");
        reader.readAsText(file, "UTF-8");
    }
}

function createGroup() {
    $('#tabAgree').bootstrapTable('destroy');
    $('#tabGroup').bootstrapTable('destroy');
    $('#tabText').bootstrapTable('destroy');
    $('#markedText').bootstrapTable('destroy');
    $("#createGroup").show();


    $("#create").click(function() {
        $.ajax({
            url: "/createGroup",
            dataType: 'text',
            method: 'post',
            data: {
                groupName: $("#groupName").val()
            },
            success: function(data) {
                Swal.fire({
                    icon: 'success',
                    text: '创建成功',
                }).then(function() {
                    window.location.reload();
                })
            }
        })
    })
}