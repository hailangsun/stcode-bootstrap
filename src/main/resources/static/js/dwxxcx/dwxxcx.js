$(function(){

    _dwxxcx.init();

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


    //主页面查询表格
    $("#query_btn").click(function(){
        $("#mainDataGrid").bootstrapTable("destroy");
        _dwxxcx.mainDataGrid();
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
                        getCheckResultTable("dwxxcxDetailCheck","dwxxcxDetailForm","/dwxxcx/checkResultTable",1);
                    }
                });
        }else {
            if(alreadyCheck("mainDataGrid")){
                getCheckResultTable("dwxxcxDetailCheck","dwxxcxDetailForm","/dwxxcx/checkResultTable",2);
            }
        }
    });

    //点击重新检查
    $("#restart-check-btn").click(function () {
        if(cxcheck("mainDataGrid")){
            getCheckResultTable("dwxxcxDetailCheck","dwxxcxDetailForm","/dwxxcx/checkResultTable",3);
        }
    });

    //检查确定
    $("#check-enter-btn").click(function () {
        //获取选项检查结果
        var temp = getOptionCheckResult("dwxxcxDetailForm");

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
                url: "/dwxxcx/insertJcjg",
                data: params,
                success: function(msg){
                    if (msg.code == 0) {
                        $("#dwxxcxDetailCheck").modal('hide');
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
                url: "/dwxxcx/updateJcjg",
                data: params,
                success: function(msg){
                    if (msg.code == 0) {
                        bootbox.alert({
                            message: "重新检查成功!",
                            size: 'small'
                        });
                        $("#dwxxcxDetailCheck").modal('hide');
                        $("#mainDataGrid").bootstrapTable('refresh');
                    }else {
                        bootbox.alert(msg.error);
                    }
                }
            });
        }

    });


    //导出
    $("#export-btn").on('click', function() {
        exportflag = 1;
        //模态框
        $("#exportModal").modal('show');
    });

    //二级页面 增减导出
    $("#zj-export-btn").on('click', function() {
        exportflag = 2;
        //模态框
        $("#exportModal").modal('show');
    });

    //本页导出
    $("#currentExprot").click(function(){
        var data = $("#mainform").serializeArray();
        if(exportflag == 1){//一级页面导出
            postExcelFile(data, "/dwxxcx/download",1);
        }else if(exportflag == 2){//二级页面导出
            postExcelFile(data, "/dwxxcx/zjdownload",1);
        }
        $("#exportModal").modal('hide');
    });


    //全部导出
    $("#allExprot").click(function(){
        var data = $("#mainform").serializeArray();
        if(exportflag == 1){//一级页面导出
            postExcelFile(data, "/dwxxcx/download",2);
        }else if(exportflag == 2){//二级页面导出
            postExcelFile(data, "/dwxxcx/zjdownload",2);
        }
        $("#exportModal").modal('hide');
    });




});


// 格式化转换
function formatIsFT(value, row, index) {
    return value == 'true' ? "是" : "否";
}

// MX 1 是检查 2是重新检查
function formatXM(value, row, index) {
    return value == 1 ? "检查" : "重新检查";
}

function company(index) {
    $("#zjInfo").modal('show');
    $('#zjDataGrid').bootstrapTable('destroy');
    //获取行号
    var record =  $('#mainDataGrid').bootstrapTable("getData")[index];
    _dwxxcx.zjDataGrid(record);
}

//单元格查询结果【cxjg】click事件
function cxjgclick(index){
    $("#cxjgModal").modal('show');
    $('#cxjgDataGrid').bootstrapTable('destroy');
    //获取行号
    var cxjgRecord =  $('#mainDataGrid').bootstrapTable("getData")[index];
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
    }else if(exportflag == 2){//二级页面 增减导出
        $.each(params,function(i,item){
            var input   = document.createElement("input");
            input.type  = "hidden";
            input.name  = item.name;
            input.value = item.value;
            form.appendChild(input);
        });
    }


    //是本页导出还是全部导出
    if(isAllExprot == 2){
        var input   = document.createElement("input");
        input.type  = "hidden";
        input.name  = "isAllExprot";
        input.value = "2";
        form.appendChild(input);
    }

    form.submit();
    form.remove();
}

var _dwxxcx = {
    init:function () {
        this.mainDataGrid();
    },
    mainDataGrid:function () {
            $('#mainDataGrid').bootstrapTable({
                method: 'post',
                url: "/dwxxcx/search",
                striped: true,                  // 是否显示行间隔色
                pagination: true,               // 是否分页
                sidePagination: 'server',       // server:服务器端分页|client：前端分页
                pageSize: 5,                   // 单页记录数
                contentType:"application/x-www-form-urlencoded",
                cache: false,
                pageList: [5, 25, 50, 100],
                queryParams: function(params) { // 上传服务器的参数
                    //导出使用
                    $("#mainoffset").val(params.offset);
                    $("#mainlimit").val(params.limit);

                    var formdata = $("form").serialize();
                    var paging =formdata+"&offset="+params.offset+"&"+"limit="+params.limit;
                    return paging;
                },

                columns: [

                    [{"title": "参保单位信息", "halign": "center", "align": "center", "colspan": 25}],
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
                        {title: '单位名称',           field: 'dwmc', align: 'center', valign: 'middle', colspan: 1, rowspan: 2, width: 100,formatter: operateEvents},
                        {title: '经代办机构',         field: 'ssqx', align: 'center', valign: 'middle', colspan: 1, rowspan: 2, width: 100},
                        {title: '统一社区信用代码',   field: 'dwdm', align: 'center', valign: 'middle', colspan: 1, rowspan: 2, width: 100},
                        {title: '单位类型',           field: 'dwlx', align: 'center', valign: 'middle', colspan: 1, rowspan: 2, width: 100},
                        {title: '隶属关系',           field: 'lsgx', align: 'center', valign: 'middle', colspan: 1, rowspan: 2, width: 100},
                        {title: '行业类型',           field: 'hydm', align: 'center', valign: 'middle', colspan: 1, rowspan: 2, width: 100},
                        {title: '经办人',             field: 'jbr', align: 'center', valign: 'middle',  colspan: 1, rowspan: 2, width: 100},
                        {title: '增减原因',           field: 'zjyy', align: 'center', valign: 'middle', colspan: 1, rowspan: 2, width: 100},
                        {title: '增减时间',           field: 'zjrq', align: 'center', valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: 100},
                        {title: '险种状态',           field: 'xzzt', align: 'center', valign: 'middle',  colspan: 6, rowspan: 1},
                        {title: '检查人',             field: 'jcr', align: 'center', valign: 'middle',   colspan: 1, rowspan: 2, width: 100},
                        {title: '检查日期',           field: 'jcrq', align: 'center', valign: 'middle',  colspan: 1, rowspan: 2, width: 100},
                        {title: '检查',               field: 'jc', align: 'center', valign: 'middle', formatter: formatIsFT,  colspan: 1, rowspan: 2, width: 100},
                        {title: '重新检查',           field: 'cxjc', align: 'center', valign: 'middle', formatter: formatIsFT,  colspan: 1, rowspan: 2, width: 100},
                        {title: '检查结果',           field: 'jcjg', align: 'center', valign: 'middle', sortable: true, colspan: 1, rowspan: 2, width: 100,formatter: cxjgEvents},
                        {title: '备注',               field: 'memo', align: 'center', valign: 'middle',sortable: true, colspan: 1, rowspan: 2, width: 100}
                    ],
                    [{field: 'yl', title: '养老', align: 'center', valign: 'middle', width: 100},
                        {field: 'sy', title: '失业', align: 'center', valign: 'middle', width: 100},
                        {field: 'gs', title: '工伤', align: 'center', valign: 'middle', width: 100},
                        {field: 'jgyl', title: '机关养老', align: 'center', valign: 'middle', width: 100},
                        {field: 'cxjm', title: '城乡居民', align: 'center', valign: 'middle', width: 100},
                        {field: 'lnbz', title: '老年保障', align: 'center', valign: 'middle', width: 100}
                    ]
                ]

            })
    },


    zjDataGrid:function (record) {
        $('#zjDataGrid').bootstrapTable({
            method: 'post',
            url: "/dwxxcx/zjInfo",
            striped: true,
            pagination: true,
            sidePagination: 'server',
            pageSize: 5,
            contentType:"application/x-www-form-urlencoded",
            cache: false,
            pageList: [5, 20, 30],
            queryParams: function(params) {
                //二级页面增减导出使用
                $("#zjdwid").val(record.dwid);
                //导出使用
                $("#detailoffset").val(params.offset);
                $("#detaillimit").val(params.limit);

                var paging = {
                    offset: params.offset,
                    limit: params.limit,
                    dwid:record.dwid
                }
                return paging;
            },
            columns: [

                [{"title": "三险单位增减信息", "halign": "center", "align": "center", "colspan": 14}],
                [
                    {title: 'id', field: 'id', visible: false, colspan: 1, rowspan: 2},
                    {title: '序号', align: 'center', valign: 'middle', width: 100, colspan: 1, rowspan: 2,
                        formatter: function(value, row, index) {
                            return index + 1;
                        }
                    },
                    {title: '单位全称',             field: 'dwmc', align: 'center', valign: 'middle',  colspan: 1, rowspan: 2, width: 100},
                    {title: '组织机构代码',         field: 'dwdm', align: 'center', valign: 'middle',  colspan: 1, rowspan: 2, width: 100},
                    {title: '缴费经(代)办机构',     field: 'jfqx', align: 'center', valign: 'middle',  colspan: 1, rowspan: 2, width: 100},
                    {title: '社保登记证号',         field: 'djzh', align: 'center', valign: 'middle',  colspan: 1, rowspan: 2, width: 100},
                    {title: '险种状态',             field: 'xzzt', align: 'center', valign: 'middle',  colspan: 4, rowspan: 1},
                    {title: '增减日期',             field: 'zjrq', align: 'center', valign: 'middle',   colspan: 1, rowspan: 2, width: 100},
                    {title: '增减原因',             field: 'memo', align: 'center', valign: 'middle',   colspan: 1, rowspan: 2, width: 100},
                    {title: '经办人',               field: 'jbr', align: 'center', valign: 'middle',   colspan: 1, rowspan: 2, width: 100},
                    {title: '经办机构',             field: 'zfqx', align: 'center', valign: 'middle',   colspan: 1, rowspan: 2, width: 100}
                ],
                [
                    {field: 'yl',   title: '养老',        align: 'center', valign: 'middle', width: '6%', },
                    {field: 'sy',   title: '失业',        align: 'center', valign: 'middle', width: '6%', },
                    {field: 'gs',   title: '工伤',        align: 'center', valign: 'middle', width: '6%', },
                    {field: 'jgyl', title: '机关养老',    align: 'center', valign: 'middle', width: '6%', }
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
                    {title: 'id',         field: 'id', visible: false},
                    {title: '项目',       field: 'xm', align: 'center', valign: 'middle',  width: 100, formatter: formatXM,},
                    {title: '检查人',     field: 'jcr', align: 'center', valign: 'middle',  width: 100},
                    {title: '检查日期',   field: 'jcrq', align: 'center', valign: 'middle', width: 100},
                    {title: '检查结果',   field: 'jcjg', align: 'center', valign: 'middle',  width: 100},
                    {title: '备注',       field: 'memo', align: 'center', valign: 'middle', width: 100}
                ]
            ]

        });
    }

}