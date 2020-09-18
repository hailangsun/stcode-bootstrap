$(function(){
    _grkszc.init();
});


//参保人员基本信息
function grPayDetail(value , record , index) {
    return '<span id="hoverspan" onclick="_grkszc.grDetailInfo('+index+')">' + value + '</span>';
}

_grkszc = {
    init: function () {
        this.mainDataGrid();
    },

    //点击姓名，显示个人详细信息
    grDetailInfo:function (index) {
        $("#payDetail").modal('show');
        //获取行号
        var record =  $('#mainDataGrid').bootstrapTable("getData")[index];

    },

    mainDataGrid:function () {
        $('#mainDataGrid').bootstrapTable({
            method: 'post',
            url: "/grkszc/search",
            striped: true,
            pagination: true,
            sidePagination: 'server',
            pageSize: 5,
            contentType:"application/x-www-form-urlencoded",
            cache: false,
            pageList: [5, 25, 50, 100],
            queryParams: function(params) {

                var formdata    = $("#mainform").serialize();
                var paging      = formdata+"&offset="+params.offset+"&"+"limit="+params.limit;
                return paging;
            },




            columns: [

                [{"title": "基本养老保险转出情况", "halign": "center", "align": "center", "colspan": 32}],
                [
                    {title: 'id', field: 'id', visible: false, colspan: 1, rowspan: 2},
                    {title: '序号', align: 'center', valign: 'middle', width: 100, sortable: true,colspan: 1, rowspan: 2,
                        formatter: function(value, row, index) {
                            var pageSize    = $('#mainDataGrid').bootstrapTable('getOptions').pageSize;
                            var pageNumber  = $('#mainDataGrid').bootstrapTable('getOptions').pageNumber;
                            return pageSize * (pageNumber - 1) + index + 1;
                        }
                    },
                    {title: '统一社区信用代码',    field: 'dwdm',      align: 'center',    valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: 100},
                    {title: '单位名称',            field: 'dwmc',      align: 'center',    valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: 100},
                    {title: '社会保障号码',        field: 'djzh',      align: 'center',    valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: 100},
                    {title: '姓名',                field: 'grname',     align: 'center',    valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: 100},
                    {title: '性别',                field: 'grname',     align: 'center',    valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: 100},
                    {title: '账户类型',            field: 'grname',     align: 'center',    valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: 100},
                    {title: '转出地(原参保地址)',   field: 'grname',     align: 'center',    valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: 100},
                    {title: '转入地(新参保地)',     field: 'grname',     align: 'center',    valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: 100},
                    {title: '缴费凭证',             field: 'grname',     align: 'center',    valign: 'middle',  colspan: 2, rowspan: 1, width: 100},
                    {title: '联系函',               field: 'grname',     align: 'center',    valign: 'middle',  colspan: 2, rowspan: 1, width: 100},
                    {title: '信息表',               field: 'grname',     align: 'center',    valign: 'middle',  colspan: 3, rowspan: 1, width: 100},
                    {title: '养老资金财务支付情况',  field: 'grname',     align: 'center',    valign: 'middle',  colspan: 5, rowspan: 1, width: 100},
                    {title: '检查人',               field: 'jcr',        align: 'center',    valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: 100},
                    {title: '检查日期',             field: 'jcrq',       align: 'center',    valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: 100},
                    {title: '检查',                 field: 'jc',         align: 'center',    valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: 100},
                    {title: '重新检查',             field: 'cxjc',       align: 'center',    valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: 100},
                    {title: '备注',                 field: 'memo',       align: 'center',    valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: 100},
                    {title: '检查审核人',           field: 'jcshr',       align: 'center',    valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: 100},
                    {title: '检查审核日期',         field: 'jcshrq',       align: 'center',    valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: 100},
                    {title: '检查审核',             field: 'jcshrq',       align: 'center',    valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: 100},
                    {title: '检查审核结果',         field: 'jcshrq',       align: 'center',    valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: 100},

                ],
                [
                    {field: 'dwmc', title: '打印日期',         align: 'center', valign: 'middle',width: 100, sortable: true,formatter: grPayDetail},
                    {field: 'sy',   title: '经办人',           align: 'center', valign: 'middle',width: 100, sortable: true},
                    {field: 'gs',   title: '办理日期',         align: 'center', valign: 'middle',width: 100, sortable: true},
                    {field: 'jgyl', title: '经办人',           align: 'center', valign: 'middle',width: 100, sortable: true},
                    {field: 'jgyl', title: '生成日期',         align: 'center', valign: 'middle',width: 100, sortable: true},
                    {field: 'jgyl', title: '经办人',           align: 'center', valign: 'middle',width: 100, sortable: true},
                    {field: 'jgyl', title: '是否上传网络',      align: 'center', valign: 'middle',width: 100, sortable: true},
                    {field: 'jgyl', title: '转移支出总额',      align: 'center', valign: 'middle',width: 100, sortable: true},
                    {field: 'jgyl', title: '统筹基金转移额',    align: 'center', valign: 'middle',width: 100, sortable: true},
                    {field: 'jgyl', title: '个人账户转移额',    align: 'center', valign: 'middle',width: 100, sortable: true},
                    {field: 'jgyl', title: '财务付款日期',      align: 'center', valign: 'middle',width: 100, sortable: true},
                    {field: 'jgyl', title: '财务付款经办人',    align: 'center', valign: 'middle',width: 100, sortable: true}
                ]
            ]

        });
    },

}