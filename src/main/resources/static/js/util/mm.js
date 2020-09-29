

var conf = {
    serverHost : ''
}

var _mm = {
    //网络请求
    request : function(param){
        var _this = this;
        $.ajax({
            type         : param.method || 'get',
            url         : param.url    || '',
            dataType    : param.type   || 'json',
            contentType:  param.contentType || "application/x-www-form-urlencoded",
            data        : param.data   || '',
            success     : function(res){
                //请求成功
                if(0 === res.status){
                    typeof param.success === 'function' && param.success(res.data,res.msg);
                } else if(10 === res.status){
                    //没有登陆状态，需要强制登陆
                    _this.doLogin();
                } else if(1 === res.status){
                    //请求失败
                    typeof param.success === 'function' && param.error(res.msg);
                }
            },
            error       : function(err){
                typeof param.success === 'function' && param.error(err.statusText);
            }
        });
    },
    //获取服务器地址
    getServerUrl: function(path){
        // return "http://140.143.158.169:8080"+path;
        return conf.serverHost + path;
    },
    //获取地址参数
    getUrlParam : function(name) {
        var reg     = new RegExp('(^|&)' + name + '=([^&]*)(&|$)');
        var result  = window.location.search.substr(1).match(reg);
        return result ? decodeURIComponent(result[2]) : null;
    },
    //渲染html模板
    renderHtml : function(htmlTemplate,data){
        var template    = Hogan.compile(htmlTemplate),
            result      = template.render(data);
        return result;
    },
    //统一提示
    //成功
    successTips : function(msg){
        alert(msg || '操作成功！');
    },
    //错误
    errorTips : function(msg){
        alert(msg || '操作失败！');
    },
    //字段验证 支持飞控、手机、邮箱
    validate : function(value,type){
        var value = $.trim(value);
        //非空
        if ('require' === type){
            return !!value;
        }
        //手机
        if ('phone' === type) {
            return /^1\d{10}$/.test(value);
        }
        //邮箱格式
        if ('email'=== type) {
            return  /^(\w)+(\.\w+)*@(\w)+((\.\w{2,3}){1,3})$/.test(value);
        }
    },
    //统一登陆处理
    doLogin : function(){
        window.location.href = './user-login.html?redirect=' + encodeURIComponent(window.location.href);
    },

    goHome : function(){
        window.location.href = './index.html';
    }
};
