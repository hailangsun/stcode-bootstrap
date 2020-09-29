$(function(){
    _ylybcx.init();


    //主页面查询表格
    $("#query_btn").click(function(){
        $("#mainDataGrid").bootstrapTable("destroy");
        _ylybcx.mainDataGrid();
    });

    //单位全称细单查询按钮
    $("#query-ylybzfDetail-btn").click(function(){
        $("#ylybzfDetailDataGrid").bootstrapTable("destroy");
        _ylybcx.ylybzfDetailDataGrid();
    });


    //单位全称细单查询按钮 - 检查
    $("#check-btn").click(function () {
        //判断是否有值
        var isRows = $('#ylybzfDetailDataGrid').bootstrapTable('getData',false);
        if(isRows.length <= 0){
            bootbox.alert("请选择记录!");
            return;
        }

        var rows =  $("#ylybzfDetailDataGrid").bootstrapTable('getSelections');
        if(rows.length <= 0){
            bootbox.confirm("是否对本次查询结果中【未检查】记录全部检查?",
                function(result) {
                    if (result) {
                        getCheckResultTable();
                    }
                });
        }else {
            getCheckResultTable();
        }
    });

    //检查确定
    $("#check-enter-btn").click(function () {
        var grIds           = new Array();
        var formIds         = new Array();
        var memo;
        //取提交的
        var formdata    =  $("#resultDetailForm").serializeArray();
        $.each(formdata,function(index,item){
            if(item.name == "memo"){
                memo = item.value;
            } else {
                formIds.push(item.value)
            }
        });

        var rows =  $("#ylybzfDetailDataGrid").bootstrapTable('getSelections');
        if(rows.length > 0){
            rows =  $("#ylybzfDetailDataGrid").bootstrapTable('getSelections');
        }else {
            //获取当前全部
            rows = $('#ylybzfDetailDataGrid').bootstrapTable('getData',false);
        }

        for(var i=0;i<rows.length;i++){
            grIds[i] = rows[i].grid;
        }
        var params = $.param({'grIds' : grIds,"formIds":formIds,"memo":memo,"dwid":$("#dwidDetail").val()},true);
        $.ajax({
            type: "post",
            url: "/ylybcx/insertJcjg",
            data: params,
            success: function(msg){
                if (msg.code == 0) {
                    bootbox.alert({
                        message: "检查完成",
                        size: 'small'
                    });
                    $("#ylybzfDetailCheck").modal('hide');
                    $("#ylybzfDetailDataGrid").bootstrapTable('refresh');
                }else {
                    bootbox.alert(msg.error);
                }
            }
        });

    });

    //解决modal第二个不无滚动条
    $('#grDetailInfo').on('hidden.bs.modal', function() {
        $('#ylybzfDetail').css({'overflow-y':'scroll'});
    });

    $('#ylybzfDetailCheck').on('hidden.bs.modal', function() {
        $('#ylybzfDetail').css({'overflow-y':'scroll'});
    });



    //导出
    $("#export-btn").on('click', function() {
        //模态框
        $("#exportModal").modal('show');
    });
    $("#currentExprot").click(function(){
        var data = $("#mainform").serializeArray();
        postExcelFile(data, "/ylybcx/download");
        $("#exportModal").modal('hide');
    });

    //随机抽取
    $("#random-btn").on('click',function () {
        bootbox.prompt({
            title: "请输入随机抽取的数量,范围应在1~XXX之间",
            locale: 'custom',
            callback: function (result) {
                console.log('This was logged in the callback: ' + result);
            }
        });
    })

    //全部检查
    $("#all-check-btn").on('click',function () {

        bootbox.confirm({
            title: "全部检查?",
            message: "是否对本次查询结果中“未检查”的记录进行全部检查?",
            buttons: {
                cancel: {
                    label: '<i class="fa fa-times"></i> 取消'
                },
                confirm: {
                    label: '<i class="fa fa-check"></i> 确定'
                }
            },
            callback: function (result) {
                console.log('This was logged in the callback: ' + result);
            }
        });

    })

    //设置下拉框宽度
    $("#dbjg").selectpicker({
        "width":250,
        noneSelectedText:'请选择'
    });

});


var locale = {
    OK: '确定',
    CONFIRM: '确定',
    CANCEL: '取消'
};
bootbox.addLocale('custom', locale);




//总合计金额
var totalAmount     = 1800;
//本页合计金额
var currentAmount   = 150;


// 查询检查项
function getCheckResultTable(){
    $.ajax({
        type: "post",
        url: "/ylybcx/checkResultTable",
        data: "",
        success: function(msg){
            if (msg.code == 0) {
                $("#ylybzfDetailCheckTable").find("tbody").empty();
                var datalist = msg.data;
                $.each(datalist,function(index,item){
                    $("#ylybzfDetailCheckTable").find("tbody").append("<tr><td><div class=\"checkbox\"  style=\"text-align: center;margin:auto;\"><input type=\"checkbox\" name='"+item.mxdm+"' value='"+item.dmmxid+"'/></div></td><td>"+item.mxmc+"</td></tr>");
                });
                $("#ylybzfDetailCheckTable").find("tbody").append("<tr><td><div style=\"text-align: center;margin:auto;\">备注</div></td><td><textarea style='resize: none' class=\"form-control\" type=\"text\" name=\"memo\"></textarea></td></tr>");
            } else {
                bootbox.alert(msg.error);
            }
        }
    });
    $("#ylybzfDetailCheck").modal('show');
}



function grDetail(value , record , index) {
    return '<span id="hoverspan" onclick="_ylybcx.grDetailInfo('+index+')">' + value + '</span>';
}

$.fn.setForm = function(jsonValue){
    var obj = this;
    $.each(jsonValue,function(name,ival){
        obj.find("[name="+name+"]").text(ival);
    })
}

function postExcelFile(params, url) { //params是post请求需要的参数，url是请求url地址
    var form            = document.createElement("form");
    form.style.display  = 'none';
    form.action         = url;
    form.method         = "post";
    document.body.appendChild(form);

    var moreselet       = "";
    $.each(params,function(i,item){
        if(item.name == 'dbjg'){
            if(!isNull(item.value)){
                moreselet+=item.value+",";
            }
        }else {
            var input   = document.createElement("input");
            input.type  = "hidden";
            input.name  = item.name;
            input.value = item.value;
            form.appendChild(input);
        }

    });
    var input   = document.createElement("input");
    input.type  = "hidden";
    input.name  = "dbjg";
    input.value = moreselet;

    form.appendChild(input);
    form.submit();
    form.remove();
}

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
    //点击姓名，显示个人详细信息
    grDetailInfo:function (index) {
        var grRecord =  $('#ylybzfDetailDataGrid').bootstrapTable("getData")[index];

        $.ajax({
            type: "post",
            url: "/ylybcx/grDetailInfo",
            data: {grid: grRecord.grid,dwid:grRecord.dwid},
            success: function(msg){
                $("#grDetailInfo").setForm(msg.data);
                $("#grDetailInfo").modal('show');
            }
        });
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
                $("#mainoffset").val(params.offset);
                $("#mainlimit").val(params.limit);

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
                    {title: '统一社区信用代码', field: 'dwdm', align: 'center', valign: 'middle', sortable: true,  width: 500,footerFormatter: function (value) {
                        var count = totalAmount;
                        return count.toFixed(2);
                    }},
                    {title: '报表日期', field: 'bbrq', align: 'center', valign: 'middle', sortable: true, width: 100},

                    {title: '经办人', field: 'jbr', align: 'center', valign: 'middle', sortable: true,  width: 100,footerFormatter: function (value) {
                            return '本页合计金额';
                        }},
                    {title: '业务办理日期', field: 'blrq', align: 'center', valign: 'middle', sortable: true, width: 100,footerFormatter: function (value) {
                            var count = currentAmount;
                            return count.toFixed(2);
                        }},
                    {title: '月报生成日期'    ,field: 'scrq',     align: 'center', valign: 'middle', sortable: true,  width: 100},
                    {title: '补支人数'        ,field: 'bzrs',     align: 'center', valign: 'middle', sortable: true,  width: 100},
                    {title: '检查人数'        ,field: 'jcrs',     align: 'center', valign: 'middle', sortable: true,  width: 100},
                    {title: '合计金额'        ,field: 'hjje',     align: 'center', valign: 'middle', sortable: true,  width: 100},
                    {title: '支付次数'        ,field: 'zfcs',     align: 'center', valign: 'middle'},
                    {title: '附言'            ,field: 'fuyan',    align: 'center', valign: 'middle',  sortable: true, width: 100},
                    {title: '发放地点'        ,field: 'ffdd',     align: 'center', valign: 'middle',  sortable: true,  width: 100},
                    {title: '经（代）办机构'  ,field: 'dbjg',     align: 'center', valign: 'middle', sortable: true,  width: 100}
                ]
            ]

        })
    },

    //查询 - 职工养老月报外支付人员明细
    ylybzfDetailDataGrid:function (record) {
        if(!isNull(record)){
            //办理日期
            $("#blrqDetail").val(getMyDate(record.blrq));
            //经（代）办机构
            $("#dbjgDetail").val(record.dbjg);
            //报表日期
            $("#bbrqDetail").val(record.bbrq);
            //单位名称
            $("#dwmcDetail").val(record.dwmc);
            //业务序号
            $("#ywxhDetail").val(record.ywxh);
            //单位id
            $("#dwidDetail").val(record.dwid);

        }

        $('#ylybzfDetailDataGrid').bootstrapTable({
            method: 'post',
            url: "/ylybcx/ylybzfDetail",            // 请求路径
            striped: true,                          // 是否显示行间隔色
            pagination: true,                       // 是否分页
            sidePagination: 'server',               // server:服务器端分页|client：前端分页
            pageSize: 5,                            // 单页记录数
            //:"application/x-www-form-urlencoded",
            cache: false,
            pageList: [5, 25, 50, 100],
            showFooter:true,                        //合计
            queryParams: function(params) {
                var page = {
                    offset: params.offset,
                    limit: params.limit,
                };
                params = JSON.parse((JSON.stringify($("#ylybzfDetailForm").serializeJSON()) + JSON.stringify(page)).replace(/}{/, ','));
                return params;
            },

            columns: [
                [{"title": "职工养老月报外支付人员明细", "halign": "center", "align": "center", "colspan": 24}],
                [{checkbox: true},
                    {title: 'ywxh', field: 'ywxh', visible: false},
                    {title: 'grid', field: 'grid', visible: false},
                    {title: '序号', align: 'center', valign: 'middle', width: 100, sortable: true,
                        formatter: function(value, row, index) {
                            var pageSize    = $('#ylybzfDetailDataGrid').bootstrapTable('getOptions').pageSize;
                            var pageNumber  = $('#ylybzfDetailDataGrid').bootstrapTable('getOptions').pageNumber;
                            return pageSize * (pageNumber - 1) + index + 1;
                        }
                    },
                    {title: '单位全称'			,field: 'dwmc', 	align: 'center', valign: 'middle', sortable: true, 	width: 100},
                    {title: '统一社区信用代码'	,field: 'dwdm', 	align: 'center', valign: 'middle', sortable: true, 	width: 100},
                    {title: '姓名'				,field: 'grname', 	align: 'center', valign: 'middle', sortable: true, 	width: 100,formatter: grDetail},
                    {title: '公民身份证号码'	    ,field: 'bzhm', 	align: 'center', valign: 'middle', sortable: true, 	width: 100},
                    {title: '附言'				,field: 'fuyan', 	align: 'center', valign: 'middle', sortable: true,	width: 100},
                    {title: '补发原因'			,field: 'bfyy', 	align: 'center', valign: 'middle', sortable: true,	width: 100},
                    {title: '发放地点'			,field: 'ffdd', 	align: 'center', valign: 'middle', sortable: true, 	width: 100},
                    {title: '经办人'			,field: 'jbr', 		align: 'center', valign: 'middle', sortable: true,	width: 100},
                    {title: '审核人'			,field: 'shr', 		align: 'center', valign: 'middle', sortable: true, 	width: 100},
                    {title: '检查日期'			,field: 'jcrq', 	align: 'center', valign: 'middle', sortable: true, 	width: 100},
                    {title: '检查'				,field: 'jc', 		align: 'center', valign: 'middle', sortable: true,	width: 100},
                    {title: '重新检查'			,field: 'cxjc', 	align: 'center', valign: 'middle', sortable: true, 	width: 100},
                    {title: '检查结果'			,field: 'jcjg', 	align: 'center', valign: 'middle', sortable: true,	width: 100},
                    {title: '备注'				,field: 'memo', 	align: 'center', valign: 'middle', sortable: true,	width: 100}
                ]
            ]

        })
    },




}

function isNull(a) {
    if (a == null || a == undefined || (typeof a == 'string' && a == '')) {
        return true;
    }
    return false;
}

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