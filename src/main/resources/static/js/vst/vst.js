$(function(){
    _vst.init();

    //查询表格
    $("#query_btn").click(function(){
       // var formdata = JSON.stringify($("form").serialize());
       var formdata = $("form").serialize();

        $.ajax({
            type: "post",
            url: "/user/search",
            data: formdata,
            success: function(msg){
                var code = msg.code;
                if (code == 0) {
                    $("#queryResult").show();
                    $('#cbdw').bootstrapTable("load",msg.data);
                } else {
                    bootbox.alert(msg.error);
                }
            }
        });
    });

    $("#ceshia").click(function(){
        var fomd =  $("#testMt").serializeArray();
        $.each(fomd,function(index,item){
            debugger
            console.log(item.name);
            console.log(item.value)
        });

    });

    //检查
    $("#check-btn").click(function(){
        debugger
        $.ajax({
            type: "post",
            url: "/user/checkResult",
            data: "",
            success: function(msg){
                if (msg.code == 0) {
                    $("#addP").find("tbody").empty();
                    var dataMap = msg.data;
                    // $.each(dataMap,function(key,values){
                    //     $("#addP").find("tbody").append("<tr><td><div class=\"checkbox\"  style=\"text-align: center;margin:auto;\"><input type=\"checkbox\" name='"+key+"'/></div></td><td>"+values+"</td></tr>");
                    // });

                    $.each(dataMap,function(index,item){
                        $("#addP").find("tbody").append("<tr><td><div class=\"checkbox\"  style=\"text-align: center;margin:auto;\"><input type=\"checkbox\" name='"+item.name+"' value='"+item.vl+"'/></div></td><td>"+item.mc+"</td></tr>");
                    });

                    $("#addP").find("tbody").append("<tr><td><div style=\"text-align: center;margin:auto;\">备注</div></td><td><textarea style='resize: none' class=\"form-control\" type=\"text\" name=\"memo\"></textarea></td></tr>");

                    $("#queryResult").show();

                } else {
                    bootbox.alert(msg.error);
                }
            }
        });


        $("#myModal").modal('show');
    });


    //导出
    $("#export-btn").on('click',
        function() {
            var data= JSON.stringify($('#cbdw').bootstrapTable('getData',false));
            // var param = data;
            // var param = {"name":"list"};
            postDownLoadFile({
                url:"/user/download",
                data:data,
                method:'post'
            });
        });


    // $("#export-btn").click(function(){
    //
    //     var formdata = $("form").serialize();
    //     $.ajax({
    //         type: "post",
    //         url: "/user/downloadTest",
    //         data: formdata,
    //         success: function(msg){
    //             if (msg.code == 0) {
    //
    //                 alert("11111")
    //             } else {
    //                alert("222222")
    //             }
    //         }
    //     });
    //
    // });


});



var postDownLoadFile = function (options) {
    var config = $.extend(true, { method: 'post' }, options);
    var $iframe = $('<iframe id="down-file-iframe" />');
    var $form = $('<form target="down-file-iframe" method="' + config.method + '" />');
    $form.attr('action', config.url);
    for (var key in config.data) {
        $form.append('<input type="hidden" name="' + key + '" value="' + config.data[key] + '" />');
    }
    $iframe.append($form);
    $(document.body).append($iframe);
    $form[0].submit();
    $iframe.remove();
}

function company(value) {
    $("#companyInfo").modal('show');
    _vst.companyDataGrid(value);
}

function operateEvents(value , record , index) {

    return '<span onclick="company(\''+record.companyName+'\')">' + value + '</span>';
}

var _vst = {
    init:function () {
        this.initDataGrid();
    },
    initDataGrid:function () {
            $('#cbdw').bootstrapTable({
                method: 'post',
                url: "", // 请求路径
                striped: true, // 是否显示行间隔色
                pageNumber: 1, // 初始化加载第一页
                pagination: true, // 是否分页
                sidePagination: 'client', // server:服务器端分页|client：前端分页
                pageSize: 5, // 单页记录数
                // contentType:"application/x-www-form-urlencoded",
                cache: false,
                pageList: [5, 20, 30],
                // showRefresh : true,// 刷新按钮
                queryParams: function(params) { // 上传服务器的参数
                    var temp = {
                        // offset: params.offset,
                        // limit: params.limit,
                        name:"name1"
                        // viewReason: $("#viewReason").val(),

                    };
                    return temp;

                },

                columns: [

                    [{"title": "参保单位信息", "halign": "center", "align": "center", "colspan": 24}],
                    [{checkbox: true, colspan: 1, rowspan: 2},
                        {title: 'id', field: 'id', visible: false, colspan: 1, rowspan: 2},
                        {title: '序号', align: 'center', valign: 'middle', width: '5%', sortable: true, colspan: 1, rowspan: 2,
                            formatter: function(value, row, index) {
                                return index + 1;
                            }
                        },
                        {title: '单位名称', field: 'companyName', align: 'center', valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: '8%',formatter: operateEvents},
                        {title: '经代办机构', field: 'jdbjg', align: 'center', valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: '8%'},
                        {title: '统一社区信用代码', field: 'jdbjg', align: 'center', valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: '8%'},
                        {title: '单位类型', field: 'jdbjg', align: 'center', valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: '8%'},
                        {title: '隶属关系', field: 'jdbjg', align: 'center', valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: '8%'},
                        {title: '行业类型', field: 'jdbjg', align: 'center', valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: '8%'},
                        {title: '经办人', field: 'jbr', align: 'center', valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: '8%'},
                        {title: '增减原因', field: 'jdbjg', align: 'center', valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: '8%'},
                        {title: '增减时间', field: 'jdbjg', align: 'center', valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: '8%'},
                        {title: '险种状态', field: 'name', align: 'center', valign: 'middle',  colspan: 6, rowspan: 1},
                        {title: '检查人', field: 'sex', align: 'center', valign: 'middle', formatter: formatSex, sortable: true, colspan: 1, rowspan: 2, width: '8%'},
                        {title: '检查日期', field: 'sex', align: 'center', valign: 'middle', formatter: formatSex, sortable: true, colspan: 1, rowspan: 2, width: '8%'},
                        {title: '检查', field: 'sex', align: 'center', valign: 'middle', formatter: formatSex, sortable: true, colspan: 1, rowspan: 2, width: '8%',},
                        {title: '重新检查', field: 'sex', align: 'center', valign: 'middle', formatter: formatSex, sortable: true, colspan: 1, rowspan: 2, width: '8%'},
                        {title: '检查结果', field: 'sex', align: 'center', valign: 'middle', formatter: formatSex, sortable: true, colspan: 1, rowspan: 2, width: '8%'},
                        {title: '备注', field: 'sex', align: 'center', valign: 'middle', formatter: formatSex, sortable: true, colspan: 1, rowspan: 2, width: '8%'}
                        // {title: '操作', field: 'id', formatter: option, colspan: 1, rowspan: 2, width: '8%',}
                    ],
                    [{field: 'yl', title: '养老', align: 'center', valign: 'middle', width: '6%', sortable: true, cellStyle: formatTableUnit, formatter: paramsMatter},
                        {field: 'phone', title: '失业', align: 'center', valign: 'middle', width: '6%', sortable: true},
                        {field: 'viewPeople', title: '工伤', align: 'center', valign: 'middle', width: '6%', sortable: true},
                        {field: 'viewPeople', title: '机关养老', align: 'center', valign: 'middle', width: '6%', sortable: true},
                        {field: 'viewPeople', title: '城乡居民', align: 'center', valign: 'middle', width: '6%', sortable: true},
                        {field: 'viewPeople', title: '老年保障', align: 'center', valign: 'middle', width: '6%', sortable: true}
                    ]
                ]

            })
    },


    companyDataGrid:function (value) {
        $('#companyDataGrid').bootstrapTable({
            method: 'post',
            url: "/user/companyInfo", // 请求路径
            striped: true, // 是否显示行间隔色
            pageNumber: 1, // 初始化加载第一页
            pagination: true, // 是否分页
            sidePagination: 'client', // server:服务器端分页|client：前端分页
            pageSize: 5, // 单页记录数
            contentType:"application/x-www-form-urlencoded",
            cache: false,
            pageList: [5, 20, 30],
            // showRefresh : true,// 刷新按钮
            queryParams: function(params) { // 上传服务器的参数
                var temp = {
                    // offset: params.offset,
                    // limit: params.limit,
                    name:value
                    // viewReason: $("#viewReason").val(),

                };
                return temp;

            },


            columns: [

                [{"title": "三险单位增减信息", "halign": "center", "align": "center", "colspan": 14}],
                [
                    {title: 'id', field: 'id', visible: false, colspan: 1, rowspan: 2},
                    {title: '序号', align: 'center', valign: 'middle', width: '5%', sortable: true, colspan: 1, rowspan: 2,
                        formatter: function(value, row, index) {
                            return index + 1;
                        }
                    },
                    {title: '单位全称', field: 'companyName', align: 'center', valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: '8%'},
                    {title: '组件机构代码', field: 'jdbjg', align: 'center', valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: '8%'},
                    {title: '缴费经(代)办机构', field: 'jdbjg', align: 'center', valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: '8%'},
                    {title: '社保登记证号', field: 'jdbjg', align: 'center', valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: '8%'},
                    {title: '险种状态', field: 'name', align: 'center', valign: 'middle',  colspan: 4, rowspan: 1},
                    {title: '增减日期', field: 'sex', align: 'center', valign: 'middle', formatter: formatSex, sortable: true, colspan: 1, rowspan: 2, width: '8%'},
                    {title: '增减原因', field: 'sex', align: 'center', valign: 'middle', formatter: formatSex, sortable: true, colspan: 1, rowspan: 2, width: '8%'},
                    {title: '经办人', field: 'sex', align: 'center', valign: 'middle', formatter: formatSex, sortable: true, colspan: 1, rowspan: 2, width: '8%'},
                    {title: '经办机构', field: 'sex', align: 'center', valign: 'middle', formatter: formatSex, sortable: true, colspan: 1, rowspan: 2, width: '8%'}
                ],
                [{field: 'yl', title: '养老', align: 'center', valign: 'middle', width: '6%', sortable: true, cellStyle: formatTableUnit, formatter: paramsMatter},
                    {field: 'phone', title: '失业', align: 'center', valign: 'middle', width: '6%', sortable: true},
                    {field: 'viewPeople', title: '工伤', align: 'center', valign: 'middle', width: '6%', sortable: true},
                    {field: 'viewPeople', title: '机关养老', align: 'center', valign: 'middle', width: '6%', sortable: true}
                ]
            ]

        });
    }

}