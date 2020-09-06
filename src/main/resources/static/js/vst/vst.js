$(function(){
    _vst.init();


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

    $("#check-btn").click(function(){
        $("#myModal").modal('show');
    });


});

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
                        name:"name"
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
                        {title: '单位名称', field: 'companyName', align: 'center', valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: '8%',},
                        {title: '经代办机构', field: 'jdbjg', align: 'center', valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: '8%',},
                        {title: '统一社区信用代码', field: 'jdbjg', align: 'center', valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: '8%',},
                        {title: '单位类型', field: 'jdbjg', align: 'center', valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: '8%',},
                        {title: '隶属关系', field: 'jdbjg', align: 'center', valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: '8%',},
                        {title: '行业类型', field: 'jdbjg', align: 'center', valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: '8%',},
                        {title: '经办人', field: 'jbr', align: 'center', valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: '8%',},
                        {title: '增减原因', field: 'jdbjg', align: 'center', valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: '8%',},
                        {title: '增减时间', field: 'jdbjg', align: 'center', valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: '8%',},
                        {title: '险种状态', field: 'name', align: 'center', valign: 'middle',  colspan: 6, rowspan: 1},
                        {title: '检查人', field: 'sex', align: 'center', valign: 'middle', formatter: formatSex, sortable: true, colspan: 1, rowspan: 2, width: '8%',},
                        {title: '检查日期', field: 'sex', align: 'center', valign: 'middle', formatter: formatSex, sortable: true, colspan: 1, rowspan: 2, width: '8%',},
                        {title: '检查', field: 'sex', align: 'center', valign: 'middle', formatter: formatSex, sortable: true, colspan: 1, rowspan: 2, width: '8%',},
                        {title: '重新检查', field: 'sex', align: 'center', valign: 'middle', formatter: formatSex, sortable: true, colspan: 1, rowspan: 2, width: '8%',},
                        {title: '检查结果', field: 'sex', align: 'center', valign: 'middle', formatter: formatSex, sortable: true, colspan: 1, rowspan: 2, width: '8%',},
                        {title: '备注', field: 'sex', align: 'center', valign: 'middle', formatter: formatSex, sortable: true, colspan: 1, rowspan: 2, width: '8%',}
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


    }
}