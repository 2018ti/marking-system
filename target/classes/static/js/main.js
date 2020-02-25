var leader;
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
    $.ajax({
        url: "/getLeader",
        dataType: "JSON",
        method: "get",
        success: function(data) {
            leader = data;
            alert(leader['groupId']);
        }
    })
})
var ListGroupMember = function() {

    $('#tabAgree').bootstrapTable('destroy');
    $('#tabGroup').bootstrapTable('destroy');
    $('#tabText').bootstrapTable('destroy');
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
        result += "<button class='btn btn-primary' style='margin-right: 20px;' onclick=\"DeleteMember('" + textId + "')\" title='操作'>下载</button>";
        result += "</div>"
        return result;
    }

}

var selectByAgree = function() {

    $("#selectByAgreeButton").click(function() {
        $('#tabAgree').bootstrapTable('destroy');
        $('#tabGroup').bootstrapTable('destroy');
        $('#tabText').bootstrapTable('destroy');
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
                }

            ]
        });

    })


}