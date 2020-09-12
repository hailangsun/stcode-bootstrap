$(function(){

    _dwxxcx.init();

    //险种
    // _common.renderDmmxSel("#xz","0122");
    //单位类型
    // _common.renderDmmxSel("#dwlx","9015");

    //增加方式
    // _common.renderDmmxSel("#zjfs","0173");
    //缴费类型
    // _common.renderDmmxSel("#jflx","0330");
    //隶属关系
    // _common.renderDmmxSel("#lsgx","0104");
    //行业性质
    // _common.renderDmmxSel("#hyxz","1108");

    //重点关注
    // _common.renderDmmxSel("#zdgz","1");
    // 检查类型
    // _common.renderDmmxSel("#jclx","2");


    //查询表格
    // $("#query_btn").click(function(){
    //    // var formdata = JSON.stringify($("form").serialize());
    //    var formdata = $("form").serialize();
    //
    //     $.ajax({
    //         type: "post",
    //         url: "/dwxxcx/search",
    //         data: formdata,
    //         success: function(msg){
    //             var code = msg.code;
    //             if (code == 0) {
    //                 $("#queryResult").show();
    //                 $('#cbdw').bootstrapTable("load",msg.data);
    //             } else {
    //                 bootbox.alert(msg.error);
    //             }
    //         }
    //     });
    // });


    $("#query_btn").click(function(){
        // var formdata = JSON.stringify($("form").serialize());
        // var formdata = $("form").serialize();

        debugger
        flag = true;
        $("#cbdw").bootstrapTable('refresh');
        $("#queryResult").show();
        _dwxxcx.initDataGrid();
    });



    //检查确定
    $("#check-enter-btn").click(function(){
        var ids     = new Array();
        var formIds = new Array();
        var memo;
        //取提交的
        var formdata =  $("#resultForm").serializeArray();
        $.each(formdata,function(index,item){
            if(item.name == "memo"){
                memo = item.value;
            } else {
                formIds.push(item.value)
            }
        });

        var rows =  $("#cbdw").bootstrapTable('getSelections');
        if(rows.length > 0){
            rows =  $("#cbdw").bootstrapTable('getSelections');
        }else {
            //获取当前全部
            rows = $('#cbdw').bootstrapTable('getData',false);
        }
        for(var i=0;i<rows.length;i++){
            ids[i] = rows[i].dwid;
        }
        var params = $.param({'dmids' : ids,"formIds":formIds,"memo":memo},true);

        $.ajax({
            type: "post",
            url: "/dwxxcx/checkResult",
            data: params,
            success: function(msg){
                if (msg.code == 0) {
                    bootbox.alert({
                        message: "检查完成",
                        size: 'small'
                    });
                    $("#myModal").modal('hide');
                    $("#query_btn").click();
                }else {
                    bootbox.alert(msg.error);
                }
            }
        });


    });

    //重新检查
    $("#again-check-btn").click(function(){
        var rowTemp = $('#cbdw').bootstrapTable('getData',false);
        var jcflag = false;
        $.each(rowTemp,function(index,item){
            if(item.jc == "false" || item.jc == null){
                jcflag = true;
                return;
            }
        });
        if(jcflag){
            jcflag = false;
            bootbox.confirm("您的选择包含未检查的记录，是否继续，继续则全部进行检查！",
                function(result) {
                    if (result) {
                        $("#check-btn").click();
                    }
                });
        }else {
            $("#check-btn").click();
        }
    });

    //检查
    $("#check-btn").click(function(){
        //判断是否有值
        var rowTemp = $('#cbdw').bootstrapTable('getData',false);
        if(rowTemp.length <= 0){
            bootbox.alert("请选择记录【后续改不弹框，设置不可用】");
            return;
        }

        var rows =  $("#cbdw").bootstrapTable('getSelections');
        if(rows.length <= 0){
            bootbox.confirm("是否对本次查询结果中【未检查】记录全部检查?",
                function(result) {
                    if (result) {
                        $.ajax({
                            type: "post",
                            url: "/dwxxcx/checkResultTable",
                            data: "",
                            success: function(msg){
                                if (msg.code == 0) {
                                    $("#addP").find("tbody").empty();
                                    var datalist = msg.data;
                                    $.each(datalist,function(index,item){
                                        $("#addP").find("tbody").append("<tr><td><div class=\"checkbox\"  style=\"text-align: center;margin:auto;\"><input type=\"checkbox\" name='checkbox' value='"+item.dmmxid+"'/></div></td><td>"+item.mxmc+"</td></tr>");
                                    });
                                    $("#addP").find("tbody").append("<tr><td><div style=\"text-align: center;margin:auto;\">备注</div></td><td><textarea style='resize: none' class=\"form-control\" type=\"text\" name=\"memo\"></textarea></td></tr>");
                                    $("#queryResult").show();
                                } else {
                                    bootbox.alert(msg.error);
                                }
                            }
                        });
                        $("#myModal").modal('show');
                    }
            });
        }else {
            $.ajax({
                type: "get",
                url: "/dwxxcx/checkResultTable",
                data: "",
                success: function(msg){
                    if (msg.code == 0) {
                        $("#addP").find("tbody").empty();
                        var datalist = msg.data;
                        $.each(datalist,function(index,item){
                            $("#addP").find("tbody").append("<tr><td><div class=\"checkbox\"  style=\"text-align: center;margin:auto;\"><input type=\"checkbox\" name='checkbox' value='"+item.dmmxid+"'/></div></td><td>"+item.mxmc+"</td></tr>");
                        });
                        $("#addP").find("tbody").append("<tr><td><div style=\"text-align: center;margin:auto;\">备注</div></td><td><textarea style='resize: none' class=\"form-control\" type=\"text\" name=\"memo\"></textarea></td></tr>");
                        $("#queryResult").show();

                    } else {
                        bootbox.alert(msg.error);
                    }
                }
            });
            $("#myModal").modal('show');
        }
    });


    //导出
    $("#export-btn").on('click', function() {
        //模态框
        $("#exportModal").modal('show');
    });

    $("#currentExprot").click(function(){
        var data = $("#mainform").serializeArray();
        postExcelFile(data, "/dwxxcx/download");
        $("#exportModal").modal('hide');
    });

    //全部导出
    $("#allExprot").click(function(){
        $("#currentExprot").click();
    });

});



function postExcelFile(params, url) { //params是post请求需要的参数，url是请求url地址
    var form = document.createElement("form");
    form.style.display = 'none';
    form.action = url;
    form.method = "post";
    document.body.appendChild(form);

    var moreselet = "";
    $.each(params,function(i,item){
        debugger
        if(item.name == 'dbjg'){
            if(item.value != '' && typeof(item.value) != "undefined"){
                moreselet+=item.value+",";
            }
        }else {
            var input = document.createElement("input");
            input.type = "hidden";
            input.name = item.name;
            input.value = item.value;
            form.appendChild(input);
        }

    });
    var input = document.createElement("input");
    input.type = "hidden";
    input.name = "dbjg";
    input.value = moreselet;
    form.appendChild(input);
    form.submit();
    form.remove();
}


// 格式化转换
function formatIsFT(value, row, index) {
    return value == 'true' ? "是" : "否";
}

// MX 1 是检查 2是重新检查
function formatXM(value, row, index) {
    return value == 1 ? "检查" : "重新检查";
}

function company(index) {
    $("#companyInfo").modal('show');
    $('#companyDataGrid').bootstrapTable('destroy');
    //获取行号
    var record =  $('#cbdw').bootstrapTable("getData")[index];
    _dwxxcx.companyDataGrid(record);
}

//单元格查询结果【cxjg】click事件
function cxjgclick(index){
    $("#cxjgModal").modal('show');
    $('#cxjgDataGrid').bootstrapTable('destroy');
    //获取行号
    var cxjgRecord =  $('#cbdw').bootstrapTable("getData")[index];

    _dwxxcx.cxjgDataGrid(cxjgRecord);

}

//点击单位名称
function operateEvents(value , record , index) {
    return '<span id="hoverspan" onclick="company('+index+')">' + value + '</span>';
}

//查询结果【cxjg】 事件
function cxjgEvents(value , record , index) {
    return '<span id="hoverspan" onclick="cxjgclick('+index+')">' + value + '</span>';
}

// 格式化时间 预留
function formatTime(value, row, index) {
    var date = new Date();
    date.setTime(value);
    var month = date.getMonth() + 1;
    var hours = date.getHours();
    if (hours < 10)
        hours = "0" + hours;
    var minutes = date.getMinutes();
    if (minutes < 10)
        minutes = "0" + minutes;
    var time = date.getFullYear() + "-" + month + "-" + date.getDate() +
        " " + hours + ":" + minutes;
    return time;
}

var flag = false;
var _dwxxcx = {
    init:function () {
        // this.initDataGrid();
    },
    initDataGrid:function () {
            $('#cbdw').bootstrapTable({
                method: 'post',
                url: "/dwxxcx/search", // 请求路径
                striped: true, // 是否显示行间隔色
                // pageNumber: 1, // 初始化加载第一页
                pagination: true, // 是否分页
                sidePagination: 'server', // server:服务器端分页|client：前端分页
                pageSize: 10, // 单页记录数
                contentType:"application/x-www-form-urlencoded",
                // contentType:"application/json;charset=UTF-8",
                // datatype: 'json',
                cache: false,
                pageList: [10, 25, 50, 100],
                // showRefresh : true,// 刷新按钮
                queryParams: function(params) { // 上传服务器的参数
                    if(flag){
                        flag =false;
                        params.offset = 0;
                        params.limit  = 10;
                    }
                    var formdata = $("form").serialize();
                    var paging =formdata+"&offset="+params.offset+"&"+"limit="+params.limit;
                    return paging;
                },

                columns: [

                    [{"title": "参保单位信息", "halign": "center", "align": "center", "colspan": 24}],
                    [{checkbox: true, colspan: 1, rowspan: 2},
                        {title: 'dwid', field: 'dwid', visible: false, colspan: 1, rowspan: 2},
                        {title: '序号', align: 'center', valign: 'middle', width: 100, sortable: true, colspan: 1, rowspan: 2,
                            formatter: function(value, row, index) {
                                var pageSize = $('#cbdw').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
                                var pageNumber = $('#cbdw').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
                                return pageSize * (pageNumber - 1) + index + 1;    // 返回每条的序号： 每页条数 *（当前页 - 1 ）+ 序号
                            }
                        },
                        {title: '单位名称', field: 'dwmc', align: 'center', valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: 300,formatter: operateEvents},
                        {title: '经代办机构', field: 'ssqx', align: 'center', valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: 100},
                        {title: '统一社区信用代码', field: 'dwdm', align: 'center', valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: '500'},
                        {title: '单位类型', field: 'dwlx', align: 'center', valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: '100'},
                        {title: '隶属关系', field: 'lsgx', align: 'center', valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: '100'},
                        {title: '行业类型', field: 'hydm', align: 'center', valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: '100'},
                        {title: '经办人', field: 'jbr', align: 'center', valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: '100'},
                        {title: '增减原因', field: 'zjyy', align: 'center', valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: '100'},
                        {title: '增减时间', field: 'zjrq', align: 'center', valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: '100'},
                        {title: '险种状态', field: 'xzzt', align: 'center', valign: 'middle',  colspan: 6, rowspan: 1},
                        {title: '检查人', field: 'jcr', align: 'center', valign: 'middle',  sortable: true, colspan: 1, rowspan: 2, width: '100'},
                        {title: '检查日期', field: 'jcrq', align: 'center', valign: 'middle',  sortable: true, colspan: 1, rowspan: 2, width: '100'},
                        {title: '检查', field: 'jc', align: 'center', valign: 'middle', formatter: formatIsFT, sortable: true, colspan: 1, rowspan: 2, width: '100'},
                        {title: '重新检查', field: 'cxjc', align: 'center', valign: 'middle', formatter: formatIsFT, sortable: true, colspan: 1, rowspan: 2, width: '100'},
                        {title: '检查结果', field: 'jcjg', align: 'center', valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: '100',formatter: cxjgEvents},
                        {title: '备注', field: 'memo', align: 'center', valign: 'middle',sortable: true, colspan: 1, rowspan: 2, width: '100'}
                        // {title: '操作', field: 'id', formatter: option, colspan: 1, rowspan: 2, width: '8%',}
                    ],
                    [{field: 'yl', title: '养老', align: 'center', valign: 'middle', width: '100', sortable: true},
                        {field: 'sy', title: '失业', align: 'center', valign: 'middle', width: '100', sortable: true},
                        {field: 'gs', title: '工伤', align: 'center', valign: 'middle', width: '100', sortable: true},
                        {field: 'jgyl', title: '机关养老', align: 'center', valign: 'middle', width: '100', sortable: true},
                        {field: 'cxjm', title: '城乡居民', align: 'center', valign: 'middle', width: '100', sortable: true},
                        {field: 'lnbz', title: '老年保障', align: 'center', valign: 'middle', width: '100', sortable: true}
                    ]
                ]

            })
    },


    companyDataGrid:function (record) {
        $('#companyDataGrid').bootstrapTable({
            method: 'post',
            url: "/dwxxcx/companyInfo", // 请求路径
            striped: true, // 是否显示行间隔色
            pageNumber: 1, // 初始化加载第一页
            pagination: true, // 是否分页
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

                [{"title": "三险单位增减信息", "halign": "center", "align": "center", "colspan": 14}],
                [
                    {title: 'id', field: 'id', visible: false, colspan: 1, rowspan: 2},
                    {title: '序号', align: 'center', valign: 'middle', width: '5%', sortable: true, colspan: 1, rowspan: 2,
                        formatter: function(value, row, index) {
                            return index + 1;
                        }
                    },
                    {title: '单位全称', field: 'dwmc', align: 'center', valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: '8%'},
                    {title: '组织机构代码', field: 'dwdm', align: 'center', valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: '8%'},
                    {title: '缴费经(代)办机构', field: 'ssqx', align: 'center', valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: '8%'},
                    {title: '社保登记证号', field: 'djzh', align: 'center', valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: '8%'},
                    {title: '险种状态', field: 'xzzt', align: 'center', valign: 'middle',  colspan: 4, rowspan: 1},
                    {title: '增减日期', field: 'zjrq', align: 'center', valign: 'middle',  sortable: true, colspan: 1, rowspan: 2, width: '8%'},
                    {title: '增减原因', field: 'memo', align: 'center', valign: 'middle',  sortable: true, colspan: 1, rowspan: 2, width: '8%'},
                    {title: '经办人', field: 'jbr', align: 'center', valign: 'middle',  sortable: true, colspan: 1, rowspan: 2, width: '8%'},
                    {title: '经办机构', field: 'dbjg', align: 'center', valign: 'middle',  sortable: true, colspan: 1, rowspan: 2, width: '8%'}
                ],
                [{field: 'yl', title: '养老', align: 'center', valign: 'middle', width: '6%', sortable: true},
                    {field: 'sy', title: '失业', align: 'center', valign: 'middle', width: '6%', sortable: true},
                    {field: 'gs', title: '工伤', align: 'center', valign: 'middle', width: '6%', sortable: true},
                    {field: 'jgyl', title: '机关养老', align: 'center', valign: 'middle', width: '6%', sortable: true}
                ]
            ]

        });
    },


    cxjgDataGrid:function (record) {
        $('#cxjgDataGrid').bootstrapTable({
            method: 'post',
            url: "/dwxxcx/cxjg", // 请求路径
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
                    {title: 'id', field: 'id', visible: false},
                    {title: '项目', field: 'xm', align: 'center', valign: 'middle',  width: '8%', formatter: formatXM,},
                    {title: '检查人', field: 'jcr', align: 'center', valign: 'middle',  width: '8%'},
                    {title: '检查日期', field: 'jcrq', align: 'center', valign: 'middle', width: '8%'},
                    {title: '检查结果', field: 'jcjg', align: 'center', valign: 'middle',  width: '8%'},
                    {title: '备注', field: 'memo', align: 'center', valign: 'middle', width: '8%'}
                ]
            ]

        });
    }

}