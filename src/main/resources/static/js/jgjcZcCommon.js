
//设置表单值
$.fn.setForm = function(jsonValue){
    var obj = this;
    $.each(jsonValue,function(name,ival){
        obj.find("input[name="+name+"]").val(ival)
        obj.find("[name="+name+"]").text(ival);
    })
}

function isNull(a) {
    if (a == null || a == undefined || (typeof a == 'string' && a == '')) {
        return true;
    }
    return false;
}

