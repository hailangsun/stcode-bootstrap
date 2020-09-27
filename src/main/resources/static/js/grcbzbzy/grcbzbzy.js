$(function(){
    _grcbzbzy.init();

    //主页面查询表格
    $("#query_btn").click(function(){
        $("#mainDataGrid").bootstrapTable("destroy");
        _grcbzbzy.mainDataGrid();
    });
});

function grzyDetail(value , record , index) {
    return '<span id="hoverspan" onclick="_grcbzbzy.grDetailInfo('+index+')">' + value + '</span>';
}

_grcbzbzy = {

    init: function () {
        this.mainDataGrid();
    },

    //点击姓名 显示居保职保间转移详细信息
    grDetailInfo:function (index) {
        //获取行号
        var record =  $('#mainDataGrid').bootstrapTable("getData")[index];
        $.ajax({
            type: "post",
            url: "/grcbzbzy/grDetailInfo",
            data: {grid: record.grid,dwid:record.dwid},
            success: function(msg){
                $("#grcbzbzyForm").setForm(msg.data);
                $("#grcbzbzyModal").modal('show');
            }
        });

    },
    mainDataGrid:function () {
        $('#mainDataGrid').bootstrapTable({
            method: 'post',
            url: "/grcbzbzy/search",
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

                [{"title": "居民养老保险和职工养老保险转序情况", "halign": "center", "align": "center", "colspan": 37}],
                [{checkbox: true,colspan: 1, rowspan: 2},
                    {title: 'grid', field: 'grid', visible: false, colspan: 1, rowspan: 2},
                    {title: 'jcid', field: 'jcid', visible: false, colspan: 1, rowspan: 2},
                    {title: '序号', align: 'center', valign: 'middle', width: 100, colspan: 1, rowspan: 2,
                        formatter: function(value, row, index) {
                            var pageSize    = $('#mainDataGrid').bootstrapTable('getOptions').pageSize;
                            var pageNumber  = $('#mainDataGrid').bootstrapTable('getOptions').pageNumber;
                            return pageSize * (pageNumber - 1) + index + 1;
                        }
                    },
                    {title: '社会保障号码',         field: 'bzhm',        align: 'center',    valign: 'middle',  colspan: 1, rowspan: 2, width: 100},
                    {title: '姓名',                 field: 'grname',      align: 'center',    valign: 'middle',  colspan: 1, rowspan: 2, width: 100,formatter: grzyDetail},
                    {title: '性别',                 field: 'xb',          align: 'center',    valign: 'middle',  colspan: 1, rowspan: 2, width: 100},
                    {title: '联系电话',             field: 'lxdh',        align: 'center',    valign: 'middle',  colspan: 1, rowspan: 2, width: 100},
                    {title: '户籍性质',             field: 'aac009',      align: 'center',    valign: 'middle',  colspan: 1, rowspan: 2, width: 100},
                    {title: '转移方向',             field: 'zrsbmc',      align: 'center',    valign: 'middle',  colspan: 1, rowspan: 2, width: 100},

                    {title: '居民养老保险转职工养老保险',    field: 'jfpz',   align: 'center',    valign: 'middle',  colspan: 6, rowspan: 1, width: 100},
                    {title: '职工养老保险转居民养老保险',    field: 'lxh',    align: 'center',    valign: 'middle',  colspan: 6, rowspan: 1, width: 100},


                    {title: '清退个人账户金额',      field: 'jcr',        align: 'center',    valign: 'middle',  colspan: 1, rowspan: 2, width: 100},
                    {title: '检查人',               field: 'jcr',         align: 'center',    valign: 'middle',  colspan: 1, rowspan: 2, width: 100},
                    {title: '检查日期',             field: 'jcrq',        align: 'center',    valign: 'middle',  colspan: 1, rowspan: 2, width: 100},
                    {title: '检查',                 field: 'jc',          align: 'center',    valign: 'middle',  colspan: 1, rowspan: 2, width: 100},
                    {title: '重新检查',             field: 'cxjc',        align: 'center',    valign: 'middle',  colspan: 1, rowspan: 2, width: 100},
                    {title: '备注',                 field: 'memo',        align: 'center',    valign: 'middle',  colspan: 1, rowspan: 2, width: 100},
                    {title: '检查审核人',           field: 'jcshr',       align: 'center',    valign: 'middle',  colspan: 1, rowspan: 2, width: 100},
                    {title: '检查审核日期',         field: 'jcshrq',      align: 'center',    valign: 'middle',  colspan: 1, rowspan: 2, width: 100},
                    {title: '检查审核',             field: 'jcsh',       align: 'center',    valign: 'middle',  colspan: 1, rowspan: 2, width: 100},
                    {title: '检查审核结果',         field: 'jcshjg',      align: 'center',    valign: 'middle',  colspan: 1, rowspan: 2, width: 100},

                ],
                [
                    {field: 'zsnx',         title: '可折算职工养老保险年限',        align: 'center', valign: 'middle',width: 100, },
                    {field: 'jfjbr',        title: '折算后职工养老保险缴费年限',     align: 'center', valign: 'middle',width: 100, },
                    {field: 'lxhblrq',      title: '重复缴费清退金额',              align: 'center', valign: 'middle',width: 100, },
                    {field: 'bxhj',         title: '转移个人账户存储额',            align: 'center', valign: 'middle',width: 100, },
                    {field: 'jbr',          title: '经办人',                        align: 'center', valign: 'middle',width: 100, },
                    {field: 'xxbjbr',       title: '办理日期',                      align: 'center', valign: 'middle',width: 100,},
                    {field: 'cnv005',       title: '可折算居民养老保险年限',         align: 'center', valign: 'middle',width: 100, },
                    {field: 'zcje',         title: '折算后居民养老保险缴费年限',     align: 'center', valign: 'middle',width: 100, },
                    {field: 'tcjjzyze',     title: '重复缴费清退金额',              align: 'center', valign: 'middle',width: 100, },
                    {field: 'aic083',       title: '转移个人账户存储额',            align: 'center', valign: 'middle',width: 100, },
                    {field: 'aae011',       title: '经办人',                        align: 'center', valign: 'middle',width: 100, },
                    {field: 'aae035',       title: '办理日期',                      align: 'center', valign: 'middle',width: 100, },
                ]
            ]

        });
    },
}