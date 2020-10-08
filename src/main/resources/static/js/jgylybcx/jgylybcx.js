$(function(){
    _jgylybcx.init();

    //附言
    _common.renderDmmxSel("#fuyan","0294");

    //主页面查询表格
    $("#query_btn").click(function(){
        $("#mainDataGrid").bootstrapTable("destroy");
        _jgylybcx.mainDataGrid();
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
                        getCheckResultTable(1);
                    }
                });
        }else {
            if(alreadyCheck(rows)){
                getCheckResultTable(2);
            }
        }
    });

    //点击重新检查
    $("#restart-check-btn").click(function () {
        if(cxcheck()){
            getCheckResultTable(3);
        }
    });


    //检查确定
    $("#check-enter-btn").click(function () {
        //获取选项检查结果
        var temp = getOptionCheckResult();
        if(checkFlag == 1 || checkFlag == 2){//检查

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
                url: "/jgylybcx/insertJcjg",
                contentType:"application/json;charset=utf-8",
                dataType: "json",
                data: params,
                success: function(msg){
                    if (msg.code == 0) {
                        $("#jgylybzfDetailCheck").modal('hide');
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

        }else {
            //重新检查
            var rows =  $("#mainDataGrid").bootstrapTable('getSelections');
            var jcjgtemp = getJCJGS(rows);
            var params = JSON.stringify({formIds:temp.formIds,memo:temp.memo,jcjgs:jcjgtemp.jcjgs,checkFlag:checkFlag});
            $.ajax({
                type: "post",
                url: "/jgylybcx/updateJcjg",
                contentType:"application/json;charset=utf-8",
                dataType: "json",
                data: params,
                success: function(msg){
                    if (msg.code == 0) {
                        bootbox.alert({
                            message: "重新检查完成!",
                            size: 'small'
                        });
                        $("#jgylybzfDetailCheck").modal('hide');
                        $("#mainDataGrid").bootstrapTable('refresh');
                    }else {
                        bootbox.alert(msg.error);
                    }
                }
            });
        }

    });

    //审核
    $("#examine-btn").click(function () {
        if(cxcheck()){
            $("#examineModal").modal('show');
        }
    });
    //审核确定
    $("#sh-enter-btn").click(function () {
        var rows =  $("#mainDataGrid").bootstrapTable('getSelections');
        var jcjgtemp = getJCJGS(rows);
        var params = JSON.stringify({shyj:$("#shyj").val(),shjg:$("#shjg").val(),jcjgs:jcjgtemp.jcjgs});
        $.ajax({
            type: "post",
            url: "/jgylybcx/shJcjg",
            contentType:"application/json;charset=utf-8",
            dataType: "json",
            data: params,
            success: function(msg){
                if (msg.code == 0) {
                    bootbox.alert({
                        message: "审核完成!",
                        size: 'small'
                    });
                    $("#examineModal").modal('hide');
                    $("#mainDataGrid").bootstrapTable('refresh');
                }else {
                    bootbox.alert(msg.error);
                }
            }
        });
    });

    //导出
    $("#export-btn").on('click', function() {
        exportflag = 1;
        //模态框
        $("#exportModal").modal('show');
    });
    //二级页面导出
    $("#detail-export-btn").on('click', function() {
        exportflag = 2;
        //模态框
        $("#exportModal").modal('show');
    });

    //本页导出
    $("#currentExprot").click(function(){
        if(exportflag == 1){//一级页面导出
            var data = $("#mainform").serializeArray();
            postExcelFile(data, "/jgylybcx/download","1");

        }else if(exportflag == 2){//二级页面导出
            postExcelFile(null, "/jgylybcx/detaildownload","1");
        }
        $("#exportModal").modal('hide');
    });

    //全部导出
    $("#allExprot").click(function(){
        if(exportflag == 1){//一级页面导出
            var data = $("#mainform").serializeArray();
            postExcelFile(data, "/jgylybcx/download","2");

        }else if(exportflag == 2){//二级页面导出
            postExcelFile(null, "/jgylybcx/detaildownload","2");
        }
        $("#exportModal").modal('hide');
    });

    //设置下拉框宽度
    $("#dbjg").selectpicker({
        "width":250,
        noneSelectedText:'请选择'
    });
    //设置下拉框宽度
    $("#jclx").selectpicker({
        "width":250,
        noneSelectedText:'请选择'
    });
});

//个人信息详情
function grDetail(value , record , index) {
    return '<span id="hoverspan" onclick="_jgylybcx.grDetailInfo('+index+')">' + value + '</span>';
}

//总合计金额
var totalAmount     = 1800;
//本页合计金额
var currentAmount   = 150;
//全局变量 1:检查,全部检查，2:检查，本页检查，3:重新检查，
var checkFlag     = 0;
//导出标记 1:一级页面导出，2：二级页面导出
var exportflag    = 0

//获取选择检查结果
function getOptionCheckResult(){
    var formIds         = new Array();
    var memo;
    //取提交的
    var formdata    =  $("#jgresultDetailForm").serializeArray();
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


// 查询检查项
function getCheckResultTable(isCheck){
    checkFlag = isCheck;
    $.ajax({
        type: "post",
        url: "/jgylybcx/checkResultTable",
        data: "",
        success: function(msg){
            if (msg.code == 0) {
                $("#jgylybzfDetailCheckTable").find("tbody").empty();
                var datalist = msg.data;
                $.each(datalist,function(index,item){
                    $("#jgylybzfDetailCheckTable").find("tbody").append("<tr><td><div class=\"checkbox\"  style=\"text-align: center;margin:auto;\"><input type=\"checkbox\" name='"+item.mxdm+"' value='"+item.dmmxid+"'/></div></td><td>"+item.mxmc+"</td></tr>");
                });
                $("#jgylybzfDetailCheckTable").find("tbody").append("<tr><td><div style=\"text-align: center;margin:auto;\">备注</div></td><td><textarea style='resize: none' class=\"form-control\" type=\"text\" name=\"memo\"></textarea></td></tr>");
            } else {
                bootbox.alert(msg.error);
            }
        }
    });
    $("#jgylybzfDetailCheck").modal('show');
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


function postExcelFile(params, url,isAllExprot) { //params是post请求需要的参数，url是请求url地址
    var form            = document.createElement("form");
    form.style.display  = 'none';
    form.action         = url;
    form.method         = "post";
    document.body.appendChild(form);

    if(exportflag == 1){//一级页面导出
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
    }else if(exportflag == 2){//二级页面导出
        var input   = document.createElement("input");
        input.type  = "hidden";
        input.name  = "grid";
        input.value = $("#detailgrid").val();
        form.appendChild(input);

        var input   = document.createElement("input");
        input.type  = "hidden";
        input.name  = "dwid";
        input.value = $("#detaildwid").val();
        form.appendChild(input);
    }


    //是本页导出还是全部导出
    if(isAllExprot == "2"){
        var input   = document.createElement("input");
        input.type  = "hidden";
        input.name  = "isAllExprot";
        input.value = "2";
        form.appendChild(input);
    }

    form.submit();
    form.remove();
}

_jgylybcx = {
    init: function () {
        this.mainDataGrid();
    },

    //点击姓名，显示个人详细信息
    grDetailInfo:function (index) {
        $("#jgylybzfDetailDataGrid").bootstrapTable("destroy");
        //获取行号
        var record =  $('#mainDataGrid').bootstrapTable("getData")[index];
        //二级页面导出查询
        $("#detailgrid").val(record.grid);
        $("#detaildwid").val(record.dwid);

        _jgylybcx.jgylybzfDetailDataGrid(record);
        $("#jgylycxfDetail").modal('show');
    },

    mainDataGrid:function () {
        $('#mainDataGrid').bootstrapTable({
            method: 'post',
            url: "/jgylybcx/search",                  // 请求路径
            striped: true,                          // 是否显示行间隔色
            pagination: true,                       // 是否分页
            sidePagination: 'server',               // server:服务器端分页|client：前端分页
            pageSize: 5,                            // 单页记录数
            contentType:"application/x-www-form-urlencoded",
            cache: false,
            pageList: [5, 25, 50, 100],
            showFooter:true,                        //合计
            queryParams: function(params) {
                //导出使用
               $("#mainoffset").val(params.offset);
               $("#mainlimit").val(params.limit);

                var formdata    = $("#mainform").serialize();
                var paging      = formdata+"&offset="+params.offset+"&"+"limit="+params.limit;
                return paging;
            },

            columns: [
                [{"title": "机关事业养老保险月报外支付明细", "halign": "center", "align": "center", "colspan": 31}],
                [{checkbox: true},
                    {title: 'dwid', field: 'dwid', visible: false},
                    {title: 'ywxh', field: 'ywxh', visible: false},
                    {title: 'jcid', field: 'jcid', visible: false},
                    {title: '序号', align: 'center', valign: 'middle', width: 100,
                        formatter: function(value, row, index) {
                            var pageSize    = $('#mainDataGrid').bootstrapTable('getOptions').pageSize;
                            var pageNumber  = $('#mainDataGrid').bootstrapTable('getOptions').pageNumber;
                            return pageSize * (pageNumber - 1) + index + 1;
                        }
                    },
                    {title: '单位名称'          ,field: 'dwmc',     align: 'center', valign: 'middle',  width: 150,footerFormatter: function (value) {
                            return '补支金额合计'                       }},
                    {title: '统一社区信用代码'  ,field: 'dwdm',     align: 'center', valign: 'middle',   width: 100,footerFormatter: function (value) {
                            var count = totalAmount;
                            return count.toFixed(2);
                        }},
                    {title: '姓名'            ,field: 'grname',     align: 'center', valign: 'middle',      width: 100,formatter: grDetail},
                    {title: '公民身份证号'    ,field: 'bzhm',       align: 'center', valign: 'middle',      width: 100},
                    {title: '报表日期'        ,field: 'bbrq',       align: 'center', valign: 'middle', sortable: true,     width: 100},
                    {title: '附言'            ,field: 'fuyan',      align: 'center', valign: 'middle',      width: 100},
                    {title: '补支月数'        ,field: 'bzys',       align: 'center', valign: 'middle',      width: 150,footerFormatter: function (value) {
                            return '本页合计金额'                       }},
                    {title: '补支金额'        ,field: 'bzje',       align: 'center', valign: 'middle',      width: 100,footerFormatter: function (value) {
                            var count = totalAmount;
                            return count.toFixed(2);
                        }},
                    {title: '补支原因'        ,field: 'bzyy',       align: 'center', valign: 'middle',      width: 100},
                    {title: '经（代）办机构'  ,field: 'dbjg',       align: 'center', valign: 'middle',      width: 100},
                    {title: '经办人'          ,field: 'jbr',        align: 'center', valign: 'middle',      width: 100},
                    {title: '办理日期'        ,field: 'blrq',       align: 'center', valign: 'middle', sortable: true,     width: 100},
                    {title: '检查人'          ,field: 'jcr',        align: 'center', valign: 'middle',      width: 100},
                    {title: '检查日期'        ,field: 'jcrqtostr',       align: 'center', valign: 'middle', sortable: true,     width: 100},
                    {title: '检查'            ,field: 'jc',         align: 'center', valign: 'middle',      width: 100},
                    {title: '重新检查'        ,field: 'cxjc',       align: 'center', valign: 'middle',      width: 100},
                    {title: '检查结果'        ,field: 'jcjg',       align: 'center', valign: 'middle',      width: 100},
                    {title: '备注'            ,field: 'memo',       align: 'center', valign: 'middle',      width: 100},
                    {title: '检查审核人'      ,field: 'jcshr',      align: 'center', valign: 'middle',      width: 100},
                    {title: '检查审核日期'    ,field: 'jcshrqtostr',     align: 'center', valign: 'middle', sortable: true,     width: 100},
                    {title: '检查审核'        ,field: 'jcsh',       align: 'center', valign: 'middle',      width: 100},
                    {title: '检查审核结果'    ,field: 'jcshjc',     align: 'center', valign: 'middle',      width: 100}

                ]
            ]

        })
    },

    //查询 - 退休人员待遇明细页面 - 机关事业养老保险月报外支付明细
    jgylybzfDetailDataGrid:function (record) {
        $('#jgylybzfDetailDataGrid').bootstrapTable({
            method: 'post',
            url: "/jgylybcx/jgylybzfDetail",
            striped: true,
            pagination: true,
            sidePagination: 'server',
            contentType:"application/x-www-form-urlencoded",
            pageSize: 5,
            cache: false,
            pageList: [5, 25, 50, 100],
            showFooter:false,
            queryParams: function(params) {
                //导出使用
                $("#detailoffset").val(params.offset);
                $("#detaillimit").val(params.limit);

                var temp = {
                    offset: params.offset,
                    limit: params.limit,
                    dwid:record.dwid,
                    grid: record.grid,
                };
                return temp;
            },

            columns: [
                [{"title": "职工养老月报外支付人员明细", "halign": "center", "align": "center", "colspan": 30}],
                [{checkbox: true},
                    {title: 'ywxh', field: 'ywxh', visible: false},
                    {title: 'grid', field: 'grid', visible: false},
                    {title: '序号', align: 'center', valign: 'middle', width: 100,
                        formatter: function(value, row, index) {
                            var pageSize    = $('#jgylybzfDetailDataGrid').bootstrapTable('getOptions').pageSize;
                            var pageNumber  = $('#jgylybzfDetailDataGrid').bootstrapTable('getOptions').pageNumber;
                            return pageSize * (pageNumber - 1) + index + 1;
                        }
                    },
                    {title: '单位全称'			,field: 'dwmc', 	align: 'center', valign: 'middle',  width: 100},
                    {title: '统一社区信用代码'	,field: 'dwdm', 	align: 'center', valign: 'middle', 	width: 100},
                    {title: '报表日期'          ,field: 'bbrq',       align: 'center', valign: 'middle', sortable: true, width: 100},
                    {title: '姓名'				,field: 'grname', 	align: 'center', valign: 'middle',  width: 100},
                    {title: '公民身份证号码'	    ,field: 'bzhm', 	align: 'center', valign: 'middle',  width: 100},
                    {title: '补支原因'          ,field: 'bzyy',       align: 'center', valign: 'middle', width: 100},
                    {title: '补支月数'			,field: 'bfyy', 	align: 'center', valign: 'middle',	width: 100},
                    {title: '支付金额'			,field: 'zfje', 	align: 'center', valign: 'middle',	width: 100},
                    {title: '基础养老金'		    ,field: 'zfjc', 	align: 'center', valign: 'middle', 	width: 100},
                    {title: '个人账户'		    ,field: 'zfzh1', 	align: 'center', valign: 'middle',	width: 100},
                    {title: '过渡养老金'		    ,field: 'zfgd', 	align: 'center', valign: 'middle',	width: 100},
                    {title: '基础补贴'		    ,field: 'ylj3', 	align: 'center', valign: 'middle', 	width: 100},
                    {title: '建国前补贴'		    ,field: 'ylj4', 	align: 'center', valign: 'middle', 	width: 100},
                    {title: '调整机制'		    ,field: 'ylj5', 	align: 'center', valign: 'middle', 	width: 100},
                    {title: '护理费'		        ,field: 'hlf', 	    align: 'center', valign: 'middle', 	width: 100},
                    {title: '统筹负担'		    ,field: 'tcfd', 	align: 'center', valign: 'middle', 	width: 100},
                    {title: '发放方式'		    ,field: 'fffs', 	align: 'center', valign: 'middle', 	width: 100},
                    {title: '发放地点'			,field: 'ffdd', 	align: 'center', valign: 'middle',  width: 100},
                    {title: '经办人'		        ,field: 'jbr', 		align: 'center', valign: 'middle',  width: 100},
                    {title: '经（代）办机构'     ,field: 'zfqx',      align: 'center', valign: 'middle',  width: 100},
                    {title: '办理日期'          ,field: 'blrq',     align: 'center', valign: 'middle', sortable: true,  width: 100}
                ]
            ]

        })
    },
}