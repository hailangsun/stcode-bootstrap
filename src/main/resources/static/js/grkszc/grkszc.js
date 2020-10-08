$(function(){
    _grkszc.init();

    //主页面查询表格
    $("#query_btn").click(function(){
        $("#mainDataGrid").bootstrapTable("destroy");
        _grkszc.mainDataGrid();
    });


    //点击检查
    $("#check-btn").click(function () {
        //判断是否有值
        var isRows = $('#mainDataGrid').bootstrapTable('getData',false);
        if(isRows.length <= 0){
            bootbox.alert("没有检查记录!");
            return;
        }

        var rows =  $("#mainDataGrid").bootstrapTable('getSelections');
        if(rows.length <= 0){
            bootbox.confirm("是否对本次查询结果中【未检查】记录全部检查?",
                function(result) {
                    if (result) {
                        // 查询检查项 modalID:模态框ID,formID表单id,isCheck:检查和从新检查标记
                        getCheckResultTable("grkszcDetailCheck","grkszcDetailForm","/grkszc/checkResultTable",1);
                    }
                });
        }else {
            if(alreadyCheck(rows)){
                getCheckResultTable("grkszcDetailCheck","grkszcDetailForm","/grkszc/checkResultTable",2);
            }
        }
    });

    //点击重新检查
    $("#restart-check-btn").click(function () {
        if(cxcheck()){
            getCheckResultTable("grkszcDetailCheck","grkszcDetailForm","/grkszc/checkResultTable",3);
        }
    });


    //检查确定
    $("#check-enter-btn").click(function () {
        //获取选项检查结果
        var temp = getOptionCheckResult("grkszcDetailForm");

        if(checkFlag == 1 || checkFlag == 2){ //检查

            var rows =  $("#mainDataGrid").bootstrapTable('getSelections');
            if(rows.length <= 0){
                //获取当前全部
                rows = $('#mainDataGrid').bootstrapTable('getData',false);
            }
            if(rows.length <=0){
                bootbox.alert("没有数据，不能检查！");
                return false;
            }
            var jcjgtemp = getJCJGS(rows);
            var params = JSON.stringify({formIds:temp.formIds,memo:temp.memo,jcjgs:jcjgtemp.jcjgs,checkFlag:checkFlag});
            params =(JSON.stringify($("#mainform").serializeJSON()) + params).replace(/}{/, ',');

            $.ajax({
                type: "post",
                contentType:"application/json;charset=utf-8",
                dataType: "json",
                url: "/grkszc/insertJcjg",
                data: params,
                success: function(msg){
                    if (msg.code == 0) {
                        $("#grkszcDetailCheck").modal('hide');
                        bootbox.alert({
                            message: "检查完成,成功检查【"+msg.num+"】条。",
                            size: 'small'
                        });
                        $("#mainDataGrid").bootstrapTable('refresh');
                    }else {
                        bootbox.alert(msg.error);
                    }
                }
            });


        }else {//重新检查

            var rows =  $("#mainDataGrid").bootstrapTable('getSelections');
            var jcjgtemp = getJCJGS(rows);
            var params = JSON.stringify({formIds:temp.formIds,memo:temp.memo,jcjgs:jcjgtemp.jcjgs,checkFlag:checkFlag});

            $.ajax({
                type: "post",
                contentType:"application/json;charset=utf-8",
                dataType: "json",
                url: "/grkszc/updateJcjg",
                data: params,
                success: function(msg){
                    if (msg.code == 0) {
                        bootbox.alert({
                            message: "重新检查完成",
                            size: 'small'
                        });
                        $("#grkszcDetailCheck").modal('hide');
                        $("#mainDataGrid").bootstrapTable('refresh');
                    }else {
                        bootbox.alert(msg.error);
                    }
                }
            });
        }



    });


    //检查审核
    $("#examine-btn").click(function(){
        if(cxcheck()){
            getCheckResultTable("examine-modal","examineModalForm","/grkszc/getjcshTable",4);
        }

    });

    //审核检查确定
    $("#examine-enter-btn").click(function(){
        //获取选项检查结果
        var temp = getOptionCheckResult("examineModalForm");

        var rows =  $("#mainDataGrid").bootstrapTable('getSelections');
        var jcjgtemp = getJCJGS(rows);
        var params = JSON.stringify({formIds:temp.formIds,memo:temp.memo,jcjgs:jcjgtemp.jcjgs,checkFlag:checkFlag});

        $.ajax({
            type: "post",
            contentType:"application/json;charset=utf-8",
            dataType: "json",
            url: "/grkszc/examineCheckJcjg",
            data: params,
            success: function(msg){
                if (msg.code == 0) {
                    bootbox.alert({
                        message: "检查审核完成",
                        size: 'small'
                    });
                    $("#examine-modal").modal('hide');
                    $("#mainDataGrid").bootstrapTable('refresh');
                }else {
                    bootbox.alert(msg.error);
                }
            }
        });
    });

    //设置下拉框宽度
    $("#dbjg").selectpicker({
        "width":250,
        noneSelectedText:'请选择'
    });

});

//全局变量 1:检查,全部检查，2:检查，本页检查，3:重新检查，
var checkFlag     = 0;

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



// 查询检查项 modalID:模态框ID,formID表单id,isCheck:检查和从新检查标记
function getCheckResultTable(modalID,formID,url,isCheck){
    checkFlag = isCheck;
    $.ajax({
        type: "post",
        url: url,
        data: "",
        success: function(msg){
            if (msg.code == 0) {
                $("#"+formID).find("tbody").empty();
                var datalist = msg.data;
                $.each(datalist,function(index,item){
                    $("#"+formID).find("tbody").append("<tr><td><div class=\"checkbox\"  style=\"text-align: center;margin:auto;\"><input type=\"checkbox\" name='"+item.mxdm+"' value='"+item.dmmxid+"'/></div></td><td>"+item.mxmc+"</td></tr>");
                });
                $("#"+formID).find("tbody").append("<tr><td><div style=\"text-align: center;margin:auto;\">备注</div></td><td><textarea style='resize: none' class=\"form-control\" type=\"text\" name=\"memo\"></textarea></td></tr>");
            } else {
                bootbox.alert(msg.error);
            }
            $("#"+modalID).modal('show');
        }
    });

}

//检查前判断
function alreadyCheck() {
    var rows =  $("#mainDataGrid").bootstrapTable('getSelections');
    if(rows.length <= 0){
        bootbox.alert("请选择要检查的记录!");
        return false;
    }

    var flag = false;
    //1、看是否已经检查了
    $.each(rows,function(index,item){
        if(item.jc == "true"){
            flag =true;
            return false;
        }
    });
    if(flag){
        flag = false;
        bootbox.alert("您选择的记录是已经检查的!");
        return false;
    }
    return true;
}

//获取选择检查结果
function getOptionCheckResult(id){
    var formIds         = new Array();
    var memo;
    //取提交的
    var formdata    =  $("#"+id).serializeArray();
    $.each(formdata,function(index,item){
        if(item.name == "memo"){
            memo = item.value;
        } else {
            formIds.push(item.value)
        }
    });

    var result = {
        "formIds":formIds,
        "memo":memo
    }
    return result;
}

function getJCJGS(rows){
    //存放jcjg集合
    var jcjgs           = new Array();
    for(var i=0;i<rows.length;i++){
        var jcjg = {};
        jcjg.grid = rows[i].grid;
        jcjg.dwid = rows[i].dwid;
        jcjg.jcid = rows[i].jcid;
        jcjgs.push(jcjg);
    }

    var result = {
        "jcjgs":jcjgs
    }
    return result;
}

//重新检查验证
function cxcheck() {
    //1、选择含有未检查的给予提示
    var rows =  $("#mainDataGrid").bootstrapTable('getSelections');
    if(rows.length <= 0){
        bootbox.alert("请选择已检查的记录");
        return false;
    }
    var flag = false;
    //2、选有检查过的，按钮可用
    $.each(rows,function(index,item){
        if(item.jc == "false" || item.jc == null){
            flag =true;
            return false;
        }
    });
    if(flag){
        flag = false;
        bootbox.alert("您选择的记录包含未检查的记录！");
        return false;
    }
    return true;
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
                $("#xxbDataGrid").bootstrapTable("destroy");
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
                    {title: '账户类型',            field: 'zhlb',         align: 'center',    valign: 'middle',  colspan: 1, rowspan: 2, width: 100},
                    {title: '转出地(原参保地址)',   field: 'zcsbmc',      align: 'center',    valign: 'middle',  colspan: 1, rowspan: 2, width: 100},
                    {title: '转入地(新参保地)',     field: 'zrsbmc',      align: 'center',    valign: 'middle',  colspan: 1, rowspan: 2, width: 100},
                    {title: '缴费凭证',             field: 'jfpz',        align: 'center',    valign: 'middle',  colspan: 2, rowspan: 1, width: 100},
                    {title: '联系函',               field: 'lxh',         align: 'center',    valign: 'middle',  colspan: 2, rowspan: 1, width: 100},
                    {title: '信息表',               field: 'xxb',         align: 'center',    valign: 'middle',  colspan: 3, rowspan: 1, width: 100},
                    {title: '养老资金财务支付情况',  field: 'ylzjzfqk',    align: 'center',    valign: 'middle',  colspan: 5, rowspan: 1, width: 100},
                    {title: '检查人',               field: 'jcr',          align: 'center',    valign: 'middle',  colspan: 1, rowspan: 2, width: 100},
                    {title: '检查日期',             field: 'jcrq',        align: 'center',    valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: 100},
                    {title: '检查',                 field: 'jc',          align: 'center',    valign: 'middle',  colspan: 1, rowspan: 2, width: 100,formatter: formatIsJC},
                    {title: '重新检查',             field: 'cxjc',        align: 'center',    valign: 'middle',  colspan: 1, rowspan: 2, width: 100,formatter: formatIsCXJC},
                    {title: '备注',                 field: 'memo',        align: 'center',    valign: 'middle',  colspan: 1, rowspan: 2, width: 100},
                    {title: '检查审核人',           field: 'jcshr',       align: 'center',    valign: 'middle',  colspan: 1, rowspan: 2, width: 100},
                    {title: '检查审核日期',         field: 'jcshrq',      align: 'center',    valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: 100},
                    {title: '检查审核',             field: 'jcsh',      align: 'center',    valign: 'middle',  colspan: 1, rowspan: 2, width: 100},
                    {title: '检查审核结果',         field: 'jcshjg',      align: 'center',    valign: 'middle',  colspan: 1, rowspan: 2, width: 100},

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
                    {field: 'xzqh',         title: '行政区划代码',        align: 'center', valign: 'middle', formatter: grPayDetail,width:100},
                    {field: 'xzmc',         title: '名称',                align: 'center', valign: 'middle',width:200},
                    {field: 'dwjfbl',       title: '单位',                align: 'center', valign: 'middle',width:100},
                    {field: 'dwhzbl',       title: '划入个人账户比例',     align: 'center', valign: 'middle', formatter: grlxhDetail,width:100},
                    {field: 'grjfbl',       title: '个人',                align: 'center', valign: 'middle',width:100},
                    {field: 'dnjzje',       title: '小计',                align: 'center', valign: 'middle', formatter:grxxbDetail,width:100},
                    {field: 'dngrjf',       title: '个人缴费',            align: 'center', valign: 'middle',width:100},
                    {field: 'dnjzlx',       title: '小计',                align: 'center', valign: 'middle',width:100},
                    {field: 'dngrlx',       title: '个人缴费',            align: 'center', valign: 'middle',width:100},
                    {field: 'zbnmljzhje',   title: '小计',                align: 'center', valign: 'middle',width:100},
                    {field: 'zbnmljgrjf',   title: '个人缴费',            align: 'center', valign: 'middle',width:100},
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

