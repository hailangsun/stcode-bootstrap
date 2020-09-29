$(function(){
    _grjgzc.init();

    //转出险种
    _common.renderDmmxSel("#xzlx","0122");

    //主页面查询表格
    $("#query_btn").click(function(){
        $("#mainDataGrid").bootstrapTable("destroy");
        _grjgzc.mainDataGrid();
    });

});

//参保人员基本信息
function grPayDetail(value , record , index) {
    return '<span id="hoverspan" onclick="_grjgzc.grDetailInfo('+index+')">' + value + '</span>';
}

//机关养老保险转出联系函 详情
function grlxhDetail(value , record , index) {
    return '<span id="hoverspan" onclick="_grjgzc.grlxhInfo('+index+')">' + value + '</span>';
}

//信息表
function grxxbDetail(value , record , index) {
    return '<span id="hoverspan" onclick="_grjgzc.grxxbInfo('+index+')">' + value + '</span>';
}

//转换检查
function formatIsJC(value, row, index) {
    return value == 'true' ? "是" : "否";
}

//是否重新检查
function formatIsCXJC(value, row, index) {
    return value == 'true' ? "是" : "否";
}

_grjgzc = {
    init: function () {
        this.mainDataGrid();
    },

    //缴费凭证 显示个人信息
    grDetailInfo:function (index) {
        //获取行号
        var record =  $('#mainDataGrid').bootstrapTable("getData")[index];
        $.ajax({
            type: "post",
            url: "/grjgzc/grDetailInfo",
            data: {grid: record.grid,dwid:record.dwid},
            success: function(msg){
                $("#payDetailForm").setForm(msg.data);
                $("#payDetail").modal('show');
            }
        });

    },
    //点击姓名获取，联系函详情
    grlxhInfo:function (index) {
        //获取行号
        var record =  $('#mainDataGrid').bootstrapTable("getData")[index];

        if(record.xzlx == '06' || record.xzlx == '120'){//养老
            $.ajax({
                type: "post",
                url: "/grjgzc/grlxhInfo",
                data: {grid: record.grid,dwid:record.dwid},
                success: function(msg){
                    $("#lxhForm").setForm(msg.data);
                    $("#lxhModal").modal('show');
                }
            });
        }else {//年金
            $.ajax({
                type: "post",
                url: "/grjgzc/grlxhnjInfo",
                data: {grid: record.grid,dwid:record.dwid},
                success: function(msg){
                    $("#lxhqynjForm").setForm(msg.data);
                    $("#lxhqynjModal").modal('show');
                }
            });
        }



    },

    //点击信息表，姓名
    grxxbInfo:function (index) {
        //获取行号
        var record =  $('#mainDataGrid').bootstrapTable("getData")[index];
        if(record.xzlx == '06' || record.xzlx == '120') {//养老
            $.ajax({
                type: "post",
                url: "/grjgzc/grxxbInfo",
                data: {grid: record.grid,dwid:record.dwid},
                success: function(msg){
                    $("#xxbForm").setForm(msg.data);
                    $("#xxbgrcx").modal('show');
                    $("#xxbDataGrid").bootstrapTable("destroy");
                    _grjgzc.xxbDataGrid(record);
                    //查询标签页，点击时候就加载出来
                    $("#xxbylDataGrid").bootstrapTable("destroy");
                    _grjgzc.xxbylDataGrid(record);
                }
            });
        }else {
            $.ajax({
                type: "post",
                url: "/grjgzc/grxxbnjInfo",
                data: {grid: record.grid,dwid:record.dwid},
                success: function(msg){
                    $("#xxbnjForm").setForm(msg.data);
                    $("#xxbnjDetail").modal('show');
                }
            });

        }

    },

    mainDataGrid:function () {
        $('#mainDataGrid').bootstrapTable({
            method: 'post',
            url: "/grjgzc/search",
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

                [{"title": "机关事业单位转出人员信息查询", "halign": "center", "align": "center", "colspan": 37}],
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
                    {title: '统一社区信用代码',    field: 'dwdm',         align: 'center',    valign: 'middle',  colspan: 1, rowspan: 2, width: 100},
                    {title: '单位名称',            field: 'dwmc',         align: 'center',    valign: 'middle',  colspan: 1, rowspan: 2, width: 100},
                    {title: '社会保障号码',        field: 'bzhm',         align: 'center',    valign: 'middle',  colspan: 1, rowspan: 2, width: 100},
                    {title: '姓名',                field: 'grname',       align: 'center',    valign: 'middle',  colspan: 1, rowspan: 2, width: 100},
                    {title: '性别',                field: 'xb',           align: 'center',    valign: 'middle',  colspan: 1, rowspan: 2, width: 100},
                    {title: '险种',                field: 'xzlx',           align: 'center',    valign: 'middle',  colspan: 1, rowspan: 2, width: 100},
                    {title: '转出地(原参保地址)',   field: 'zcsbmc',      align: 'center',    valign: 'middle',  colspan: 1, rowspan: 2, width: 100},
                    {title: '转入地(新参保地)',     field: 'zrsbmc',      align: 'center',    valign: 'middle',  colspan: 1, rowspan: 2, width: 100},

                    {title: '缴费凭证',            field: 'jfpz',        align: 'center',    valign: 'middle',  colspan: 2, rowspan: 1, width: 100},
                    {title: '联系函',              field: 'lxh',           align: 'center',    valign: 'middle',  colspan: 2, rowspan: 1, width: 100},
                    {title: '信息表',              field: 'xxb',          align: 'center',    valign: 'middle',  colspan: 3, rowspan: 1, width: 100},
                    {title: '养老资金财务支付情况', field: 'ylzjzf',          align: 'center',    valign: 'middle',  colspan: 5, rowspan: 1, width: 100},
                    {title: '职业年金支付情况',     field: 'zynjzf',          align: 'center',    valign: 'middle',  colspan: 4, rowspan: 1, width: 100},


                    {title: '检查人',               field: 'jcr',          align: 'center',    valign: 'middle',  colspan: 1, rowspan: 2, width: 100},
                    {title: '检查日期',             field: 'jcrq',        align: 'center',    valign: 'middle',  colspan: 1, rowspan: 2, width: 100},
                    {title: '检查',                 field: 'jc',          align: 'center',    valign: 'middle',  colspan: 1, rowspan: 2, width: 100,formatter: formatIsJC},
                    {title: '重新检查',             field: 'cxjc',        align: 'center',    valign: 'middle',  colspan: 1, rowspan: 2, width: 100,formatter: formatIsCXJC},
                    {title: '备注',                 field: 'memo',        align: 'center',    valign: 'middle',  colspan: 1, rowspan: 2, width: 100},
                    {title: '检查审核人',           field: 'jcshr',       align: 'center',    valign: 'middle',  colspan: 1, rowspan: 2, width: 100},
                    {title: '检查审核日期',         field: 'jcshrq',      align: 'center',    valign: 'middle',  colspan: 1, rowspan: 2, width: 100},
                    {title: '检查审核',             field: 'jcsh',       align: 'center',    valign: 'middle',  colspan: 1, rowspan: 2, width: 100},
                    {title: '检查审核结果',         field: 'jcshjg',      align: 'center',    valign: 'middle',  colspan: 1, rowspan: 2, width: 100},

                ],
                [
                    {field: 'dyrq',         title: '打印日期',              align: 'center', valign: 'middle',width: 100, formatter: grPayDetail},
                    {field: 'jfjbr',        title: '经办人',                align: 'center', valign: 'middle',width: 100, },
                    {field: 'lxhblrq',      title: '办理日期',              align: 'center', valign: 'middle',width: 100, },
                    {field: 'lxhjbr',       title: '经办人',                align: 'center', valign: 'middle',width: 100, formatter: grlxhDetail},
                    {field: 'xxbscrq',      title: '生成日期',              align: 'center', valign: 'middle',width: 100, },
                    {field: 'xxbjbr',       title: '经办人',                align: 'center', valign: 'middle',width: 100, formatter:grxxbDetail},
                    {field: 'zt',           title: '是否上传部网',          align: 'center', valign: 'middle',width: 100, },
                    {field: 'zcje',         title: '转移支出总额',          align: 'center', valign: 'middle',width: 100, },
                    {field: 'tcjjzyze',     title: '统筹基金转移额',        align: 'center', valign: 'middle',width: 100, },
                    {field: 'GRZHZYZE',     title: '个人账户转移额',        align: 'center', valign: 'middle',width: 100, },
                    {field: 'todo',         title: '财务付款日期',          align: 'center', valign: 'middle',width: 100, },
                    {field: 'todo',         title: '财务付款经办人',        align: 'center', valign: 'middle',width: 100, },

                    {field: 'sqyzfje',      title: '职业年金支出总额',       align: 'center', valign: 'middle',width: 100, },
                    {field: 'bpscrq',       title: '职业年金报盘日期',       align: 'center', valign: 'middle',width: 100, },
                    {field: 'hpblrq',       title: '职业年金回盘日期',       align: 'center', valign: 'middle',width: 100, },
                    {field: 'todo',         title: '回盘经办人',             align: 'center', valign: 'middle',width: 100, },
                ]
            ]

        });
    },

    xxbDataGrid:function (data) {
        $('#xxbDataGrid').bootstrapTable({
            method: 'post',
            url: "/grjgzc/searchXxb",
            striped: true,
            pagination: true,
            sidePagination: 'server',
            pageSize: 5,
            contentType:"application/x-www-form-urlencoded",
            cache: false,
            pageList: [5, 25, 50, 100],
            queryParams: function(params) {
                var paging = {
                    offset: params.offset,
                    limit: params.limit,
                    grid:data.grid,
                }
                return paging;
            },

            columns: [

                [
                    {title: 'grid', field: 'grid', visible: false, colspan: 1, rowspan: 2},
                    // {title: '序号', align: 'center', valign: 'middle', width: 100, sortable: true,colspan: 1, rowspan: 2,
                    //     formatter: function(value, row, index) {
                    //         var pageSize    = $('#xxbDataGrid').bootstrapTable('getOptions').pageSize;
                    //         var pageNumber  = $('#xxbDataGrid').bootstrapTable('getOptions').pageNumber;
                    //         return pageSize * (pageNumber - 1) + index + 1;
                    //     }
                    // },
                    {title: '参保地区',                field: 'cbdq',        align: 'center',    valign: 'middle',  colspan: 2, rowspan: 1,width:100},
                    {title: '年份',                    field: 'nian',        align: 'center',    valign: 'middle',  colspan: 1, rowspan: 2,width:60},
                    {title: '缴费起止时间',            field: 'qrq',         align: 'center',    valign: 'middle',  colspan: 1, rowspan: 2,width:100},
                    {title: '缴费月数',                field: 'ys',          align: 'center',    valign: 'middle',  colspan: 1, rowspan: 2,width:50},
                    {title: '月缴费基数',              field: 'yjfjs',       align: 'center',    valign: 'middle',  colspan: 1, rowspan: 2,width:100},
                    {title: '缴费比例',                field: 'jfbl',        align: 'center',    valign: 'middle',  colspan: 3, rowspan: 1,width:100},
                    {title: '当年记账金额',            field: 'dnjzje',      align: 'center',    valign: 'middle',  colspan: 2, rowspan: 1,width:100},
                    {title: '当年记账利息',            field: 'dnjzlx',      align: 'center',    valign: 'middle',  colspan: 2, rowspan: 1,width:100},
                    {title: '至本年末账户累计存储额',   field: 'zbnmzh',      align: 'center',   valign: 'middle',  colspan: 2, rowspan: 1,width:100},
                    {title: '备注',                    field: 'memo',        align: 'center',   valign: 'middle',  colspan: 1, rowspan: 2,width:50},
                ],
                [
                    {field: 'xzqh',         title: '行政区划代码',        align: 'center', valign: 'middle', width:100},
                    {field: 'xzmc',         title: '名称',                align: 'center', valign: 'middle',width:200},
                    {field: 'dwjfbl',       title: '单位',                align: 'center', valign: 'middle',width:100},
                    {field: 'dwhzbl',       title: '划入个人账户比例',     align: 'center', valign: 'middle', width:100},
                    {field: 'grjfbl',       title: '个人',                align: 'center', valign: 'middle',width:100},
                    {field: 'dnjzje',       title: '小计',                align: 'center', valign: 'middle', width:100},
                    {field: 'dngrjf',       title: '个人缴费',            align: 'center', valign: 'middle',width:100},
                    {field: 'dnjzlx',       title: '小计',                align: 'center', valign: 'middle',width:100},
                    {field: 'dngrlx',       title: '个人缴费',            align: 'center', valign: 'middle',width:100},
                    {field: 'zbnmljzhje',   title: '小计',                align: 'center', valign: 'middle',width:100},
                    {field: 'zbnmljgrjf',   title: '个人缴费',            align: 'center', valign: 'middle',width:100},
                ]
            ]

        });
    },

    //点击信息表 标签页养老信息表
    xxbylDataGrid:function (data) {
        $('#xxbylDataGrid').bootstrapTable({
            method: 'post',
            url: "/grjgzc/xxbyl",
            pagination: false,
            sidePagination: 'server',
            pageSize: 5,
            contentType:"application/x-www-form-urlencoded",
            cache: false,
            pageList: [5, 25, 50, 100],
            queryParams: function(params) {
                var paging = {
                    grid:data.grid,
                }
                return paging;
            },

            columns: [
                [
                    {title: 'grid', field: 'grid', visible: false},
                    {title: '年份',                    field: 'nian',        align: 'center',    valign: 'middle', },
                    {title: '上年度在离职工月平均工资', field: 'zgyjgz',      align: 'center',    valign: 'middle', },
                    {title: '月缴费基数',              field: 'yjfjs',       align: 'center',    valign: 'middle', },
                ]
            ]
        });
    },
}