$(function(){
    _ylybcx.init();

    //主页面查询表格
    $("#query_btn").click(function(){
        flag = true;
        $("#mainDataGrid").bootstrapTable("destroy");
        _ylybcx.mainDataGrid();
    });

    //单位全称细单查询按钮
    $("#query-ylybzfDetail-btn").click(function(){
        detailFlag = true;
        $("#ylybzfDetailDataGrid").bootstrapTable("destroy");
        _ylybcx.ylybzfDetailDataGrid();
    });



});

function getMyDate(str){
    var oDate = new Date(str),
        oYear = oDate.getFullYear(),
        oMonth = oDate.getMonth()+1,
        oDay = oDate.getDate(),
        oTime = oYear +'-'+ getzf(oMonth) +'-'+ getzf(oDay);//最后拼接时间
    return oTime;
};
//补0操作
function getzf(num){
    if(parseInt(num) < 10){
        num = '0'+num;
    }
    return num;
}

//总合计金额
var totalAmount = 1800;
//本页合计金额
var currentAmount = 150;
//查询标记
var flag = false;
//细单标记
var detailFlag = false;


_ylybcx = {
    init: function () {
        this.mainDataGrid();
    },
    //点击单位全称查询 - 职工养老月报外支付人员明细
    ylybzfDetail:function(index) {
        $("#ylybzfDetail").modal('show');
        $('#ylybzfDetailDataGrid').bootstrapTable('destroy');
        //获取行号
        var record =  $('#mainDataGrid').bootstrapTable("getData")[index];
        _ylybcx.ylybzfDetailDataGrid(record);
    },

    mainDataGrid:function () {
        $('#mainDataGrid').bootstrapTable({
            method: 'post',
            url: "/ylybcx/search",                  // 请求路径
            striped: true,                          // 是否显示行间隔色
            pagination: true,                       // 是否分页
            sidePagination: 'server',               // server:服务器端分页|client：前端分页
            pageSize: 5,                            // 单页记录数
            contentType:"application/x-www-form-urlencoded",
            cache: false,
            pageList: [5, 25, 50, 100],
            showFooter:true,                        //合计
            queryParams: function(params) {
                if(flag){
                    flag =false;
                    params.offset = 0;
                    params.limit  = 5;
                }
                var formdata = $("#mainform").serialize();
                var paging =formdata+"&offset="+params.offset+"&"+"limit="+params.limit;
                return paging;
            },

            columns: [
                [{"title": "职工养老月报外支付", "halign": "center", "align": "center", "colspan": 24}],
                [{checkbox: true},
                    {title: 'dwid', field: 'dwid', visible: false},
                    {title: 'ywxh', field: 'ywxh', visible: false},
                    {title: '序号', align: 'center', valign: 'middle', width: 100, sortable: true,
                        formatter: function(value, row, index) {
                            var pageSize    = $('#mainDataGrid').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
                            var pageNumber  = $('#mainDataGrid').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
                            return pageSize * (pageNumber - 1) + index + 1;                                 // 返回每条的序号： 每页条数 *（当前页 - 1 ）+ 序号
                        }
                    },
                    {title: '单位全称', field: 'dwmc', align: 'center', valign: 'middle', sortable: true, width: 300,formatter: function(value , record , index) {
                            return '<span id="hoverspan" onclick="_ylybcx.ylybzfDetail('+index+')">' + value + '</span>';
                        },footerFormatter: function (value) {
                            return '总合计金额';
                        }},
                    {title: '统一社区信用代码', field: 'dwdm', align: 'center', valign: 'middle', sortable: true,  width: '500',footerFormatter: function (value) {
                        var count = totalAmount;
                        return count.toFixed(2);
                    }},
                    {title: '报表日期', field: 'bbrq', align: 'center', valign: 'middle', sortable: true, width: 100},

                    {title: '经办人', field: 'jbr', align: 'center', valign: 'middle', sortable: true,  width: '100',footerFormatter: function (value) {
                            return '本页合计金额';
                        }},
                    {title: '业务办理日期', field: 'blrq', align: 'center', valign: 'middle', sortable: true, width: '100',footerFormatter: function (value) {
                            var count = currentAmount;
                            return count.toFixed(2);
                        }},
                    {title: '月报生成日期', field: 'scrq', align: 'center', valign: 'middle', sortable: true,  width: '100'},
                    {title: '补支人数', field: 'bzrs', align: 'center', valign: 'middle', sortable: true,  width: '100'},
                    {title: '检查人数', field: 'jcrs', align: 'center', valign: 'middle', sortable: true,  width: '100'},
                    {title: '合计金额', field: 'hjje', align: 'center', valign: 'middle', sortable: true,  width: '100'},
                    {title: '支付次数', field: 'zfcs', align: 'center', valign: 'middle'},
                    {title: '附言', field: 'fuyan', align: 'center', valign: 'middle',  sortable: true, width: '100'},
                    {title: '发放地点', field: 'fffs', align: 'center', valign: 'middle',  sortable: true,  width: '100'},
                    {title: '经（代）办机构', field: 'dbjg', align: 'center', valign: 'middle', sortable: true,  width: '100'}
                ]
            ]

        })
    },



    ylybzfDetailDataGrid:function (record) {
        debugger
        //身份证号码
        //办理日期
        $("#blrqDetail").val( getMyDate(record.blrq));
        $('#ylybzfDetailDataGrid').bootstrapTable({
            method: 'post',
            url: "/ylybcx/ylybzfDetail",
            striped: true,                      // 是否显示行间隔色
            pagination: true,                   // 是否分页
            sidePagination: 'server',           // server:服务器端分页|client：前端分页
            pageSize: 5,                        // 单页记录数
            // contentType:"application/x-www-form-urlencoded",
            // contentType:"application/json charset=utf-8",
            cache: false,
            pageList: [5, 25, 50, 100],
            queryParams: function(params) {
                debugger;
                // if(detailFlag){
                //     detailFlag =false;
                //     params.offset = 0;
                //     params.limit  = 5;
                // }else {
                    var temp = {
                        offset: params.offset,
                        limit: params.limit,
                        ywxh:record.ywxh
                    };
                    params = JSON.parse((JSON.stringify($("#ylybzfDetailForm").serializeJSON()) + JSON.stringify(temp)).replace(/}{/, ','));
                // }
                return params;
            },

            columns: [
                [{"title": "职工养老月报外支付人员明细", "halign": "center", "align": "center", "colspan": 14}],
                [
                    {title: 'id', field: 'id', visible: false},
                    {title: '序号', align: 'center', valign: 'middle', width: '5%', sortable: true,
                        formatter: function(value, row, index) {
                            var pageSize    = $('#ylybzfDetailDataGrid').bootstrapTable('getOptions').pageSize;
                            var pageNumber  = $('#ylybzfDetailDataGrid').bootstrapTable('getOptions').pageNumber;
                            return pageSize * (pageNumber - 1) + index + 1;
                        }
                    },
                    {title: '单位全称', field: 'dwmc', align: 'center', valign: 'middle', sortable: true, width: '8%'},
                    {title: '统一社区信用代码', field: 'dwdm', align: 'center', valign: 'middle', sortable: true, width: '8%'},
                    {title: '姓名', field: 'name', align: 'center', valign: 'middle', sortable: true, width: '8%'},
                    {title: '公民身份证号码', field: 'bzhm', align: 'center', valign: 'middle', sortable: true, width: '8%'},
                    {title: '附言', field: 'fuyan', align: 'center', valign: 'middle', sortable: true, width: '8%'},
                    {title: '补发原因', field: 'bfyy', align: 'center', valign: 'middle', sortable: true, width: '8%'},
                    {title: '发放地点', field: 'fffs', align: 'center', valign: 'middle', sortable: true, width: '8%'},
                    {title: '经办人', field: 'jbr', align: 'center', valign: 'middle', sortable: true, width: '8%'},
                    {title: '审核人', field: 'shr', align: 'center', valign: 'middle', sortable: true, width: '8%'},
                    {title: '检查日期', field: 'jcrq', align: 'center', valign: 'middle', sortable: true, width: '8%'},
                    {title: '检查', field: 'jc', align: 'center', valign: 'middle', sortable: true, width: '8%'},
                    {title: '重新检查', field: 'cxjc', align: 'center', valign: 'middle', sortable: true, width: '8%'},
                    {title: '检查结果', field: 'jcjg', align: 'center', valign: 'middle', sortable: true, width: '8%'},
                    {title: '备注', field: 'memo', align: 'center', valign: 'middle', sortable: true, width: '8%'}

                ]
            ]

        });
    },


}

