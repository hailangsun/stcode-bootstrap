//导出标记 1:一级页面导出，2：二级页面导出 .....
var exportflag    = 0
//全局变量 1:检查,全部检查，2:检查，本页检查，3:重新检查，
var checkFlag     = 0;

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
function alreadyCheck(id) {
    var rows =  $("#"+id).bootstrapTable('getSelections');
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
function cxcheck(id) {
    //1、选择含有未检查的给予提示
    var rows =  $("#"+id).bootstrapTable('getSelections');
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

