$(function(){

    _lwpqdw.init();
    //险种
    _common.renderDmmxSel("#xz","0122");
    //单位类型
    _common.renderDmmxSel("#dwlx","9015");

    //增加方式
    _common.renderDmmxSel("#zjfs","0173");
    //缴费类型
    _common.renderDmmxSel("#jflx","0330");
    //隶属关系
    _common.renderDmmxSel("#lsgx","0104");
    //行业性质
    _common.renderDmmxSel("#hyxz","1108");

    //重点关注
    // _common.renderDmmxSel("#zdgz","1");
    // 检查类型
    _common.renderDmmxSel("#jclx","10101010");

    //设置下拉框宽度
    $("#dbjg").selectpicker({
        "width":250,
        noneSelectedText:'请选择'
    });

});

//点击单位名称
function operateEvents(value , record , index) {
    return '<span id="hoverspan" onclick="dwInfo('+index+')">' + value + '</span>';
}

//查询结果【cxjg】 事件
function cxjgEvents(value , record , index) {
    return '<span id="hoverspan" onclick="cxjgclick('+index+')">' + value + '</span>';
}

// 格式化转换
function formatIsFT(value, row, index) {
    return value == 'true' ? "是" : "否";
}

//单元格查询结果【cxjg】click事件
function cxjgclick(index){
    $("#cxjgModal").modal('show');
    $('#cxjgDataGrid').bootstrapTable('destroy');
    //获取行号
    var cxjgRecord =  $('#mainDataGrid').bootstrapTable("getData")[index];
    _lwpqdw.cxjgDataGrid(cxjgRecord);

}

function dwInfo(index) {
    var currtRecord =  $('#mainDataGrid').bootstrapTable("getData")[index];
    $.ajax({
        type: "post",
        url: "/lwpqdw/dwDetailInfo",
        data: {dwid:currtRecord.dwid},
        success: function(msg){
            $("#dwDetailInfo").setForm(msg.data);
            $("#dwDetailInfo").modal('show');
        }
    });
}

var _lwpqdw = {
    init:function () {
        this.mainDataGrid();
    },

    mainDataGrid:function () {
        $('#mainDataGrid').bootstrapTable({
            method: 'post',
            url: "/lwpqdw/search",
            striped: true,
            pagination: true,
            sidePagination: 'server',
            pageSize: 5,
            contentType:"application/x-www-form-urlencoded",
            cache: false,
            pageList: [5, 25, 50, 100],
            queryParams: function(params) {
                //导出使用
                $("#mainoffset").val(params.offset);
                $("#mainlimit").val(params.limit);

                var formdata    = $("#mainform").serialize();
                var paging      = formdata+"&offset="+params.offset+"&"+"limit="+params.limit;
                return paging;
            },
            columns: [
                [{"title": "劳务派遣单位查询", "halign": "center", "align": "center", "colspan": 25}],
                [{checkbox: true, colspan: 1, rowspan: 2},
                    {title: 'dwid', field: 'dwid', visible: false, colspan: 1, rowspan: 2},
                    {title: 'jcid', field: 'jcid', visible: false, colspan: 1, rowspan: 2},
                    {title: '序号', align: 'center', valign: 'middle', width: 100,  colspan: 1, rowspan: 2,
                        formatter: function(value, row, index) {
                            var pageSize = $('#mainDataGrid').bootstrapTable('getOptions').pageSize;
                            var pageNumber = $('#mainDataGrid').bootstrapTable('getOptions').pageNumber;
                            return pageSize * (pageNumber - 1) + index + 1;
                        }
                    },
                    {title: '统一社区信用代码',   field: 'dwdm',            align: 'center', valign: 'middle', colspan: 1, rowspan: 2, width: 100},
                    {title: '单位名称',           field: 'dwmc',            align: 'center', valign: 'middle', colspan: 1, rowspan: 2, width: 100,formatter: operateEvents},
                    {title: '单位类型',           field: 'dwlx',            align: 'center', valign: 'middle', colspan: 1, rowspan: 2, width: 100},
                    {title: '行业类型',           field: 'tradeproperty',   align: 'center', valign: 'middle', colspan: 1, rowspan: 2, width: 100},
                    {title: '增减原因',           field: 'zjyy',            align: 'center', valign: 'middle', colspan: 1, rowspan: 2, width: 100},
                    {title: '增减时间',           field: 'zjrq',            align: 'center', valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: 100},
                    {title: '经办人',             field: 'jbr',             align: 'center', valign: 'middle',  colspan: 1, rowspan: 2, width: 100},
                    {title: '经代办机构',         field: 'jfqx',            align: 'center', valign: 'middle', colspan: 1, rowspan: 2, width: 100},
                    {title: '险种状态',           field: 'xzzt',            align: 'center', valign: 'middle',  colspan: 3, rowspan: 1},
                    {title: '企业类型',           field: 'lsgx',            align: 'center', valign: 'middle', colspan: 1, rowspan: 2, width: 100},
                    {title: '检查人',             field: 'jcr',             align: 'center', valign: 'middle',   colspan: 1, rowspan: 2, width: 100},
                    {title: '检查日期',           field: 'jcrq',            align: 'center', valign: 'middle',  colspan: 1, rowspan: 2, width: 100},
                    {title: '检查',               field: 'jc',              align: 'center', valign: 'middle', formatter: formatIsFT,  colspan: 1, rowspan: 2, width: 100},
                    {title: '重新检查',           field: 'cxjc',            align: 'center', valign: 'middle', formatter: formatIsFT,  colspan: 1, rowspan: 2, width: 100},
                    {title: '检查结果',           field: 'jcjg',            align: 'center', valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: 100,formatter: cxjgEvents},
                    {title: '审核',               field: 'jcsh',            align: 'center',    valign: 'middle',  colspan: 1, rowspan: 2, width: 100},
                    {title: '审核人',             field: 'jcshr',           align: 'center',    valign: 'middle',  colspan: 1, rowspan: 2, width: 100},
                    {title: '审核日期',           field: 'jcshrqtostr',     align: 'center',    valign: 'middle',  colspan: 1, rowspan: 2, width: 100},
                    {title: '审核结果',           field: 'jcshjg',          align: 'center',    valign: 'middle',  colspan: 1, rowspan: 2, width: 100},

                ],
                [
                    {field: 'yl', title: '养老', align: 'center', valign: 'middle', width: 100},
                    {field: 'sy', title: '失业', align: 'center', valign: 'middle', width: 100},
                    {field: 'gs', title: '工伤', align: 'center', valign: 'middle', width: 100},
                ]
            ]

        });
    },


    cxjgDataGrid:function (record) {
        $('#cxjgDataGrid').bootstrapTable({
            method: 'post',
            url: "lwpqdw/cxjg", // 请求路径
            striped: true, // 是否显示行间隔色
            pageNumber: 1, // 初始化加载第一页
            pagination: true, // 是否分页
            fitColumns : true,		//使表格列宽自适应
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
                    dwid:record.dwid
                    // viewReason: $("#viewReason").val(),

                };
                return temp;

            },

            columns: [
                [
                    {title: 'id',         field: 'id', visible: false},
                    {title: '项目',       field: 'xm', align: 'center', valign: 'middle',  width: 100, formatter: formatXM,},
                    {title: '检查人',     field: 'jcr', align: 'center', valign: 'middle',  width: 100},
                    {title: '检查日期',   field: 'jcrq', align: 'center', valign: 'middle', width: 100},
                    {title: '检查结果',   field: 'jcjg', align: 'center', valign: 'middle',  width: 100},
                    {title: '备注',       field: 'memo', align: 'center', valign: 'middle', width: 100}
                ]
            ]

        });
    },

}