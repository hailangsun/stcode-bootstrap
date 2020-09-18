$(function(){
    _jgylybcx.init();

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
            bootbox.alert("请选择记录!");
            return;
        }

        var rows =  $("#mainDataGrid").bootstrapTable('getSelections');
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

});

//个人信息详情
function grDetail(value , record , index) {
    return '<span id="hoverspan" onclick="_jgylybcx.grDetailInfo('+index+')">' + value + '</span>';
}

//总合计金额
var totalAmount     = 1800;
//本页合计金额
var currentAmount   = 150;

// 查询检查项
function getCheckResultTable(){
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


_jgylybcx = {
    init: function () {
        this.mainDataGrid();
    },

    //点击姓名，显示个人详细信息
    grDetailInfo:function (index) {
        $("#jgylycxfDetail").modal('show');
        //获取行号
        var record =  $('#mainDataGrid').bootstrapTable("getData")[index];
        _jgylybcx.jgylybzfDetailDataGrid(record);
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
               // $("#mainoffset").val(params.offset);
               // $("#mainlimit").val(params.limit);

                var formdata = $("#mainform").serialize();
                var paging =formdata+"&offset="+params.offset+"&"+"limit="+params.limit;
                return paging;
            },

            columns: [
                [{"title": "机关事业养老保险月报外支付明细", "halign": "center", "align": "center", "colspan": 30}],
                [{checkbox: true},
                    {title: 'dwid', field: 'dwid', visible: false},
                    {title: 'ywxh', field: 'ywxh', visible: false},
                    {title: '序号', align: 'center', valign: 'middle', width: 100, sortable: true,
                        formatter: function(value, row, index) {
                            var pageSize    = $('#mainDataGrid').bootstrapTable('getOptions').pageSize;
                            var pageNumber  = $('#mainDataGrid').bootstrapTable('getOptions').pageNumber;
                            return pageSize * (pageNumber - 1) + index + 1;
                        }
                    },
                    {title: '单位名称'          ,field: 'dwmc',     align: 'center', valign: 'middle', sortable: true, width: 300,footerFormatter: function (value) {
                            return '总合计金额';
                        }},
                    {title: '统一社区信用代码'  ,field: 'dwdm',     align: 'center', valign: 'middle', sortable: true,  width: 500,footerFormatter: function (value) {
                            var count = totalAmount;
                            return count.toFixed(2);
                        }},
                    {title: '姓名'            ,field: 'grname',     align: 'center', valign: 'middle', sortable: true,     width: 100,formatter: grDetail},
                    {title: '公民身份证号'    ,field: 'bzhm',       align: 'center', valign: 'middle', sortable: true,     width: 100},
                    {title: '报表日期'        ,field: 'bbrq',       align: 'center', valign: 'middle', sortable: true,     width: 100},
                    {title: '附言'            ,field: 'fuyan',      align: 'center', valign: 'middle', sortable: true,     width: 100},
                    {title: '补支月数'        ,field: 'bzys',       align: 'center', valign: 'middle', sortable: true,     width: 100},
                    {title: '补支金额'        ,field: 'bzje',       align: 'center', valign: 'middle', sortable: true,     width: 100},
                    {title: '补支原因'        ,field: 'bzyy',       align: 'center', valign: 'middle', sortable: true,     width: 100},
                    {title: '经（代）办机构'  ,field: 'dbjg',       align: 'center', valign: 'middle', sortable: true,     width: 100},
                    {title: '经办人'          ,field: 'jbr',        align: 'center', valign: 'middle', sortable: true,     width: 100},
                    {title: '办理日期'        ,field: 'blrq',       align: 'center', valign: 'middle', sortable: true,     width: 100},
                    {title: '检查人'          ,field: 'jcr',        align: 'center', valign: 'middle', sortable: true,     width: 100},
                    {title: '检查日期'        ,field: 'jcrq',       align: 'center', valign: 'middle', sortable: true,     width: 100},
                    {title: '检查'            ,field: 'jc',         align: 'center', valign: 'middle', sortable: true,     width: 100},
                    {title: '重新检查'        ,field: 'cxjc',       align: 'center', valign: 'middle', sortable: true,     width: 100},
                    {title: '检查结果'        ,field: 'jcjg',       align: 'center', valign: 'middle', sortable: true,     width: 100},
                    {title: '备注'            ,field: 'memo',       align: 'center', valign: 'middle', sortable: true,     width: 100},
                    {title: '检查审核人'      ,field: 'jcshr',      align: 'center', valign: 'middle', sortable: true,     width: 100},
                    {title: '检查审核日期'    ,field: 'jcshrq',     align: 'center', valign: 'middle', sortable: true,     width: 100},
                    {title: '检查审核'        ,field: 'jcsh',       align: 'center', valign: 'middle', sortable: true,     width: 100},
                    {title: '检查审核结果'    ,field: 'jcshjc',     align: 'center', valign: 'middle', sortable: true,     width: 100}

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
            pageSize: 5,
            //:"application/x-www-form-urlencoded",
            cache: false,
            pageList: [5, 25, 50, 100],
            showFooter:true,
            queryParams: function(params) {
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
                    {title: '序号', align: 'center', valign: 'middle', width: 100, sortable: true,
                        formatter: function(value, row, index) {
                            var pageSize    = $('#jgylybzfDetailDataGrid').bootstrapTable('getOptions').pageSize;
                            var pageNumber  = $('#jgylybzfDetailDataGrid').bootstrapTable('getOptions').pageNumber;
                            return pageSize * (pageNumber - 1) + index + 1;
                        }
                    },
                    {title: '单位全称'			,field: 'dwmc', 	align: 'center', valign: 'middle', sortable: true, 	width: 100},
                    {title: '统一社区信用代码'	,field: 'dwdm', 	align: 'center', valign: 'middle', sortable: true, 	width: 100},
                    {title: '报表日期'          ,field: 'bbrq',       align: 'center', valign: 'middle', sortable: true, width: 100},
                    {title: '姓名'				,field: 'grname', 	align: 'center', valign: 'middle', sortable: true, 	width: 100,formatter: grDetail},
                    {title: '公民身份证号码'	    ,field: 'bzhm', 	align: 'center', valign: 'middle', sortable: true, 	width: 100},
                    {title: '附言'				,field: 'fuyan', 	align: 'center', valign: 'middle', sortable: true,	width: 100},
                    {title: '补支原因'          ,field: 'bzyy',       align: 'center', valign: 'middle', sortable: true,     width: 100},
                    {title: '补发原因'			,field: 'bfyy', 	align: 'center', valign: 'middle', sortable: true,	width: 100},


                    {title: '支付金额'			,field: 'zfje', 	align: 'center', valign: 'middle', sortable: true,	width: 100},
                    {title: '基础养老金'		,field: 'jcylj', 	align: 'center', valign: 'middle', sortable: true,	width: 100},
                    {title: '个人账户'		    ,field: 'grzh', 	align: 'center', valign: 'middle', sortable: true,	width: 100},
                    {title: '过渡养老金'		,field: 'grzh', 	align: 'center', valign: 'middle', sortable: true,	width: 100},
                    {title: '基础补贴'		    ,field: 'grzh', 	align: 'center', valign: 'middle', sortable: true,	width: 100},
                    {title: '建国前补贴'		,field: 'grzh', 	align: 'center', valign: 'middle', sortable: true,	width: 100},
                    {title: '调整机制'		    ,field: 'grzh', 	align: 'center', valign: 'middle', sortable: true,	width: 100},
                    {title: '护理费'		    ,field: 'grzh', 	align: 'center', valign: 'middle', sortable: true,	width: 100},
                    {title: '统筹负担'		    ,field: 'grzh', 	align: 'center', valign: 'middle', sortable: true,	width: 100},
                    {title: '发放方式'		    ,field: 'grzh', 	align: 'center', valign: 'middle', sortable: true,	width: 100},
                    {title: '发放方式'		    ,field: 'grzh', 	align: 'center', valign: 'middle', sortable: true,	width: 100},


                    {title: '发放地点'			,field: 'ffdd', 	align: 'center', valign: 'middle', sortable: true, 	width: 100},
                    {title: '经办人'			,field: 'jbr', 		align: 'center', valign: 'middle', sortable: true,	width: 100},
                    {title: '经（代）办机构'  ,field: 'dbjg',     align: 'center', valign: 'middle', sortable: true,  width: 100},
                    {title: '办理日期'        ,field: 'blrq',       align: 'center', valign: 'middle', sortable: true,     width: 100}
                ]
            ]

        })
    },
}