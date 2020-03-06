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

function ListSysMember() {
    $('#sysMember').bootstrapTable('destroy');
    $('#applyList').bootstrapTable('destroy');
    $("#sysMember").bootstrapTable({
        url: "/getsysmember",
        method: 'get',
        toolbar: "#toolbar",
        sidePagination: "true",
        striped: true, // 是否显示行间隔色
        //search : "true",
        uniqueId: "ID",
        pageSize: "6",

        pagination: true, // 是否分页
        sortable: true, // 是否启用排序

        columns: [{
                field: 'name',
                title: '用户名'
            }, {
                field: 'role',
                title: '角色'
            },
            {
                field: 'userId',
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
}

function Leaderapply() {

    $('#sysMember').bootstrapTable('destroy');
    $('#applyList').bootstrapTable('destroy');
    $("#applyList").bootstrapTable({
        url: "/getapplyList",
        method: 'get',
        toolbar: "#toolbar",
        sidePagination: "true",
        striped: true, // 是否显示行间隔色
        //search : "true",
        uniqueId: "ID",
        pageSize: "5",

        pagination: true, // 是否分页
        sortable: true, // 是否启用排序

        columns: [{
                field: 'userName',
                title: '用户名'
            }, {
                field: 'applyRole',
                title: '角色'
            },
            {
                field: 'status',
                title: '状态'
            },
            {
                field: 'applyId',
                title: '操作',
                width: 120,
                align: 'center',
                valign: 'middle',
                formatter: function(value, row, index) {
                    var result = "";
                    result += "<div class='btn-group btn-group-xs'>"
                    result += "<button class='btn btn-primary' onclick=\"apply('" + row.applyId + "','" + row.userName + "')\" >批准</button>";
                    result += "</div>"
                    return result;
                }
            }
        ]
    });
}

function apply(applyId, username) {
    Swal.fire({
        icon: "question",
        title: '确认批准吗',
        showCancelButton: true,
        confirmButtonText: '确认',
        closeOnConfirm: false
    }).then((result) => {
        if (result.value) {
            $.ajax({
                url: "/applyByadmin",
                data: {
                    applyId: applyId,
                    username: username
                },
                dataType: 'text',
                method: "post",
                success: function(data) {
                    Swal.fire({
                        title: "YES",
                        text: "操作成功",
                        type: "success",
                    }.then(function() {
                        $('#applyList').bootstrapTable('refresh');
                    }));
                }
            })
        }
    })
}