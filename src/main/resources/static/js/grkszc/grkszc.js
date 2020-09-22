$(function(){
    _grkszc.init();

    //主页面查询表格
    $("#query_btn").click(function(){
        $("#mainDataGrid").bootstrapTable("destroy");
        _grkszc.mainDataGrid();
    });

    //点击检查
    $("#check-btn").click(function () {
        checkflag = '2';
        //判断是否有值
        var isRows = $('#mainDataGrid').bootstrapTable('getData',false);
        if(isRows.length <= 0){
            bootbox.alert("没有可选择的记录!");
            return;
        }

        var rows =  $("#mainDataGrid").bootstrapTable('getSelections');
        if(rows.length <= 0){
            bootbox.confirm("是否对本次查询结果中【未检查】记录全部检查?",
                function(result) {
                    if (result) {
                        $("#grkszcDetailCheck").modal('show');
                    }
                });
        }else {
            $("#grkszcDetailCheck").modal('show');
        }
    });


    //检查确定
    $("#check-enter-btn").click(function () {
        //重新检查
        if(checkflag == '1'){
            var params = checkCommon();
            $.ajax({
                type: "post",
                contentType:"application/json;charset=utf-8",
                dataType: "json",
                url: "/grkszc/restartCheckJcjg",
                data: params,
                success: function(msg){
                    if (msg.code == 0) {
                        bootbox.alert({
                            message: "重新检查完成",
                            size: 'small'
                        });
                        //todo 需要清空checkbox
                        $("#grkszcDetailCheck").modal('hide');
                        $("#mainDataGrid").bootstrapTable('refresh');
                    }else {
                        bootbox.alert(msg.error);
                    }
                }
            });

        }else {//检查
            var params = checkCommon();
            $.ajax({
                type: "post",
                contentType:"application/json;charset=utf-8",
                dataType: "json",
                url: "/grkszc/insertJcjg",
                data: params,
                success: function(msg){
                    if (msg.code == 0) {
                        bootbox.alert({
                            message: "检查完成",
                            size: 'small'
                        });
                        //todo 需要清空checkbox
                        $("#grkszcDetailCheck").modal('hide');
                        $("#mainDataGrid").bootstrapTable('refresh');
                    }else {
                        bootbox.alert(msg.error);
                    }
                }
            });
        }



    });

    //重新检查restart-check-btn
    $("#restart-check-btn").click(function(){
        checkflag = 1;
        //1、选择含有未检查的给予提示
       var rows =  $("#mainDataGrid").bootstrapTable('getSelections');
        if(rows.length <= 0){
            bootbox.alert("请选择已检查的记录");
            return
        }
        var flag = false;
        //2、选有检查过的，按钮可用
        $.each(rows,function(index,item){
            if(item.jc == "false" || item.jc == null){
                flag =true;
                return;
            }
        });
        if(flag){
            flag = false;
            bootbox.alert("您选择的记录包含未检查的记录，不能重新对未检查记录进行检查！");
            return;
        }

        // todo 清空form表单 按照以前方式写

        $("#grkszcDetailCheck").modal('show');

    });
});
//检查和重新检查标记
var checkflag = '0';

//检查和重新检查共用
function checkCommon() {
    var formIds         = new Array();
    var jcjgs           = new Array();
    var memo;
    //取提交的
    var formdata    =  $("#grkszcDetailForm").serializeArray();
    $.each(formdata,function(index,item){
        if(item.name == "memo"){
            memo = item.value;
        } else {
            formIds.push(item.value)
        }
    });
    //获取选择的记录
    var rows =  $("#mainDataGrid").bootstrapTable('getSelections');
    if(rows.length > 0){
        rows =  $("#mainDataGrid").bootstrapTable('getSelections');
    }else {
        //获取当前全部
        rows = $('#mainDataGrid').bootstrapTable('getData',false);
    }

    for(var i=0;i<rows.length;i++){
        var jcjg = {};
        jcjg.grid = rows[i].grid;
        jcjg.dwid = rows[i].dwid;
        if(!_grkszc.isNull(rows[i].jcid)){
            jcjg.jcid = rows[i].jcid;
        }
        jcjgs.push(jcjg);
    }
    var params = JSON.stringify({formIds:formIds,memo:memo,jcjgs:jcjgs});
    return params;
}


//参保人员基本信息
function grPayDetail(value , record , index) {
    return '<span id="hoverspan" onclick="_grkszc.grDetailInfo('+index+')">' + value + '</span>';
}
//基本养老保险缴费账户转移联系函 详情
function grlxhDetail(value , record , index) {
    return '<span id="hoverspan" onclick="_grkszc.grlxhInfo('+index+')">' + value + '</span>';
}
//信息表
function grxxbDetail(value , record , index) {
    return '<span id="hoverspan" onclick="_grkszc.grxxbInfo('+index+')">' + value + '</span>';
}

//转换检查
function formatIsJC(value, row, index) {
    return value == 'true' ? "是" : "否";
}

//是否重新检查
function formatIsCXJC(value, row, index) {
    return value == 'true' ? "是" : "否";
}

//设置表单值
$.fn.setForm = function(jsonValue){
    var obj = this;
    $.each(jsonValue,function(name,ival){
        obj.find("input[name="+name+"]").val(ival)
        obj.find("[name="+name+"]").text(ival);
    })
}


_grkszc = {
    init: function () {
        this.mainDataGrid();
    },

    //缴费凭证 显示个人信息
    grDetailInfo:function (index) {
        //获取行号
        var record =  $('#mainDataGrid').bootstrapTable("getData")[index];
        $.ajax({
            type: "post",
            url: "/grkszc/grDetailInfo",
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
        $.ajax({
            type: "post",
            url: "/grkszc/grlxhInfo",
            data: {grid: record.grid,dwid:record.dwid},
            success: function(msg){
                $("#lxhForm").setForm(msg.data);
                $("#lxhModal").modal('show');
            }
        });

    },
    //点击信息表，姓名
    grxxbInfo:function (index) {
        //获取行号
        var record =  $('#mainDataGrid').bootstrapTable("getData")[index];
        $.ajax({
            type: "post",
            url: "/grkszc/grxxbInfo",
            data: {grid: record.grid,dwid:record.dwid},
            success: function(msg){
                $("#xxbForm").setForm(msg.data);
                $("#xxbgrcx").modal('show');
                _grkszc.xxbDataGrid(record);
            }
        });
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

                [{"title": "基本养老保险转出情况", "halign": "center", "align": "center", "colspan": 33}],
                [{checkbox: true,colspan: 1, rowspan: 2},
                    {title: 'grid', field: 'grid', visible: false, colspan: 1, rowspan: 2},
                    {title: 'jcid', field: 'jcid', visible: false, colspan: 1, rowspan: 2},
                    {title: '序号', align: 'center', valign: 'middle', width: 100, sortable: true,colspan: 1, rowspan: 2,
                        formatter: function(value, row, index) {
                            var pageSize    = $('#mainDataGrid').bootstrapTable('getOptions').pageSize;
                            var pageNumber  = $('#mainDataGrid').bootstrapTable('getOptions').pageNumber;
                            return pageSize * (pageNumber - 1) + index + 1;
                        }
                    },
                    {title: '统一社区信用代码',    field: 'dwdm',         align: 'center',    valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: 100},
                    {title: '单位名称',            field: 'dwmc',         align: 'center',    valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: 100},
                    {title: '社会保障号码',        field: 'bzhm',         align: 'center',    valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: 100},
                    {title: '姓名',                field: 'grname',       align: 'center',    valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: 100},
                    {title: '性别',                field: 'xb',           align: 'center',    valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: 100},
                    {title: '账户类型',            field: 'zhlb',         align: 'center',    valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: 100},
                    {title: '转出地(原参保地址)',   field: 'zcsbmc',      align: 'center',    valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: 100},
                    {title: '转入地(新参保地)',     field: 'zrsbmc',      align: 'center',    valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: 100},
                    {title: '缴费凭证',             field: 'jfpz',        align: 'center',    valign: 'middle',  colspan: 2, rowspan: 1, width: 100},
                    {title: '联系函',               field: 'lxh',         align: 'center',    valign: 'middle',  colspan: 2, rowspan: 1, width: 100},
                    {title: '信息表',               field: 'xxb',         align: 'center',    valign: 'middle',  colspan: 3, rowspan: 1, width: 100},
                    {title: '养老资金财务支付情况',  field: 'ylzjzfqk',    align: 'center',    valign: 'middle',  colspan: 5, rowspan: 1, width: 100},
                    {title: '检查人',               field: 'jcr',          align: 'center',    valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: 100},
                    {title: '检查日期',             field: 'jcrq',        align: 'center',    valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: 100},
                    {title: '检查',                 field: 'jc',          align: 'center',    valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: 100,formatter: formatIsJC},
                    {title: '重新检查',             field: 'cxjc',        align: 'center',    valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: 100,formatter: formatIsCXJC},
                    {title: '备注',                 field: 'memo',        align: 'center',    valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: 100},
                    {title: '检查审核人',           field: 'jcshr',       align: 'center',    valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: 100},
                    {title: '检查审核日期',         field: 'jcshrq',      align: 'center',    valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: 100},
                    {title: '检查审核',             field: 'jcshrq',      align: 'center',    valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: 100},
                    {title: '检查审核结果',         field: 'jcshrq',      align: 'center',    valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: 100},

                ],
                [
                    {field: 'dyrq', title: '打印日期',            align: 'center', valign: 'middle',width: 100, sortable: true,formatter: grPayDetail},
                    {field: 'jbr',   title: '经办人',             align: 'center', valign: 'middle',width: 100, sortable: true},
                    {field: 'lxhblrq',   title: '办理日期',       align: 'center', valign: 'middle',width: 100, sortable: true},
                    {field: 'lxhjbr', title: '经办人',            align: 'center', valign: 'middle',width: 100, sortable: true,formatter: grlxhDetail},
                    {field: 'xxbscrq', title: '生成日期',         align: 'center', valign: 'middle',width: 100, sortable: true},
                    {field: 'xxbjbr', title: '经办人',            align: 'center', valign: 'middle',width: 100, sortable: true,formatter:grxxbDetail},
                    {field: 'zt', title: '是否上传网络',          align: 'center', valign: 'middle',width: 100, sortable: true},
                    {field: 'zyyyyy', title: '转移支出总额',      align: 'center', valign: 'middle',width: 100, sortable: true},
                    {field: 'tcjjzyze', title: '统筹基金转移额',  align: 'center', valign: 'middle',width: 100, sortable: true},
                    {field: 'grzhzyze', title: '个人账户转移额',  align: 'center', valign: 'middle',width: 100, sortable: true},
                    {field: 'cwfkrq', title: '财务付款日期',      align: 'center', valign: 'middle',width: 100, sortable: true},
                    {field: 'cwfkjbr', title: '财务付款经办人',   align: 'center', valign: 'middle',width: 100, sortable: true}
                ]
            ]

        });
    },

    xxbDataGrid:function (data) {
        $('#xxbDataGrid').bootstrapTable({
            method: 'post',
            url: "/grkszc/searchXxb",
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
                    {title: '参保地区',                field: 'cbdq',         align: 'center',    valign: 'middle',  colspan: 2, rowspan: 1,width:100},
                    {title: '年份',                    field: 'dwmc',         align: 'center',    valign: 'middle',  colspan: 1, rowspan: 2,width:60},
                    {title: '缴费起止时间',            field: 'bzhm',         align: 'center',    valign: 'middle',  colspan: 1, rowspan: 2,width:100},
                    {title: '缴费月数',                field: 'grname',       align: 'center',    valign: 'middle',  colspan: 1, rowspan: 2,width:50},
                    {title: '月缴费基数',              field: 'xb',           align: 'center',    valign: 'middle',  colspan: 1, rowspan: 2,width:100},
                    {title: '缴费比例',                field: 'zhlb',         align: 'center',    valign: 'middle',  colspan: 3, rowspan: 1,width:100},
                    {title: '当年记账金额',            field: 'zhlb',         align: 'center',    valign: 'middle',  colspan: 2, rowspan: 1,width:100},
                    {title: '当年记账利息',            field: 'zhlb',         align: 'center',    valign: 'middle',  colspan: 2, rowspan: 1,width:100},
                    {title: '至本年末账户累计存储额',  field: 'zhlb',         align: 'center',    valign: 'middle',  colspan: 2, rowspan: 1,width:100},
                    {title: '备注',                   field: 'zhlb',         align: 'center',    valign: 'middle',  colspan: 1, rowspan: 2,width:50},
                ],
                [
                    {field: 'dyrq',         title: '行政区划代码',        align: 'center', valign: 'middle', formatter: grPayDetail},
                    {field: 'jbr',          title: '名称',                align: 'center', valign: 'middle'},
                    {field: 'lxhblrq',      title: '单位',                align: 'center', valign: 'middle'},
                    {field: 'lxhjbr',       title: '划入个人账户比例',     align: 'center', valign: 'middle', formatter: grlxhDetail},
                    {field: 'xxbscrq',      title: '个人',                align: 'center', valign: 'middle'},
                    {field: 'xxbjbr',       title: '小计',                align: 'center', valign: 'middle', formatter:grxxbDetail},
                    {field: 'zt',           title: '个人缴费',            align: 'center', valign: 'middle'},
                    {field: 'zyyyyy',       title: '小计',                align: 'center', valign: 'middle'},
                    {field: 'tcjjzyze',     title: '个人缴费',            align: 'center', valign: 'middle'},
                    {field: 'grzhzyze',     title: '小计',                align: 'center', valign: 'middle'},
                    {field: 'cwfkrq',       title: '个人缴费',            align: 'center', valign: 'middle'},
                ]
            ]

        });
    },

    isNull:function(a) {
        if (a == null || a == undefined || (typeof a == 'string' && a == '')) {
            return true;
        }
        return false;
    },

}

