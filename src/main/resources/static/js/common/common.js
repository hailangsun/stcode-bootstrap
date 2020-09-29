// var fxkzBasePath = '/vspn30-fxkz/';
var fxkzBasePath = '/';
var dm_qx = '9007';     // 所属区县代码
var dm_fxshzt = '0601'; // 风险状态编码
var dm_fxly = '0101'; // 风险来源编码
var dm_ssbm = '0201'; // 所属部门编码
var dm_ywhj = '0602'; // 所属部门编码
var dm_pgzt = '0602'; // 所属部门编码
var dm_fxzt = '0603'; // 所属部门编码
var dm_yzzt = '0604'; // 阈值状态
var dm_fxpgzt = '1105'; //风险评估状态
var dm_fxdj = '1106'; //风险等级
var _common = {
    init: function () {
        this.bindEvent();
    },
    data: {

    },
    bindEvent: function(){
        var _this = this;
        $(".select-area").on("click","select-area-item",function(){
            if ($(this).children('input:checkbox').prop("checked")) {
                $(this).children('input:checkbox').prop("checked",false);
            }else {
                $(this).children('input:checkbox').prop("checked",true);
            }
        });
        $('.tt-flex-card-title').on('click',function(){
            _this.expend($(this));
        });
        $('#accordion').on('click',".panel-title",function(){
            $(this).parents(".panel-heading")
                .children(".panel-title-icon")
                .addClass("down");
        });
    },
    refreshClick: function() {
        var _this = this;
        $('.tt-flex-card-title').each(function(){
            $(this).click(function(){
                _this.expend($(this));
            });
        })
    },
    expend: function(el){
        el.parents('div.tt-flex-card-container').removeClass('flexible-panel');
        if (el.hasClass("open")) {
            el.removeClass('open');
            el.parents('div.tt-flex-card-container').addClass('flexible-panel');
        }else {
            el.addClass('open');
        }
    },
    renderNavi: function (data, el) {
        var result = Hogan.compile(_temp.tt_navi).render({
            //指定渲染的数据对象，指定渲染的数据对象后，下面的数据都可以直接用 标签名=数据名
            list: data
        });
        // 最后添加到指定标签
        this.renderHtml(result,el)
    },
    renderNewNavi: function (data, el) {
        var result = Hogan.compile(_temp.tt_navi).render({
            //指定渲染的数据对象，指定渲染的数据对象后，下面的数据都可以直接用 标签名=数据名
            list: data
        });
        $(el).empty();
        if ($(el).parents('div.navi').hasClass("tt-navi-bar")) {
            // 最后添加到指定标签
            $(el).parents('div.navi').addClass("tt-navi-new");
        } else {
            $(el).parents('div.navi').addClass("tt-navi-bar");
            $(el).parents('div.navi').addClass("tt-navi-new");
        }
        this.renderHtml(result,el)
    },
    renderCard: function (data, el) {
        var result = Hogan.compile(_temp.tt_card).render({
            //指定渲染的数据对象，指定渲染的数据对象后，下面的数据都可以直接用 标签名=数据名
            list: data
        });
        this.renderHtml(result,el);
    },
    renderHtml: function (html,el) {
        $(el).empty();
        $(el).html(html);
    },
    /**
     * 模板渲染
     * @param temp 模板
     * @param data 数据
     * @param el 节点
     */
    renderTemp: function (temp,data,el) {
        var result = Hogan.compile(temp).render(data);
        this.renderHtml(result,el);
    },
    /**
     * 成功提示
     * @param data  显示消息
     */
    success: function (data) {
        alert(data);

    },
    /**
     * 失败提示
     * @param errMsg 失败消息
     */
    fail: function(errMsg){
        alert(errMsg);
    },
    /**
     * @param data : 格式: {list: [{name: '今日', value: '1', checked: true},{name: '本月', value: '2'}] }
     * @param el
     */
    renderDateBar: function (data,el) {
        var params = {list: data};
        this.renderTemp(_temp.date_bar,params,el);
    },
    /**
     * _common.renderDmmxSel()
     * 获取代码
     * @param el   对应select的id属性，如 '#test'
     * 0@param dmm  获取dm_mx表里的dmlbid
     */
    renderDmmxSel: function (el,dmm) {
        // 获取对应el的select标签的data-dmm里的值
        dmm = dmm == null ? $(el).data("dmm") : dmm;
        var _this = this;
        _commonService.getDmmxList(dmm,function (res) {
            _this.renderTemp(_temp.dmmOpt,{list:res},el);
        },function (errMsg) {
            _this.fail(errMsg);
        });
    },
    /**
     * _common.renderDmmxSel()
     * 获取代码
     * @param el   对应select的id属性，如 '#test'
     * 0@param dmm  获取dm_mx表里的dmlbid
     * 0@param def  默认选项
     */
    renderDmmxSelDef: function (el,dmm,def) {
        // 获取对应el的select标签的data-dmm里的值
        dmm = dmm == null ? $(el).data("dmm") : dmm;
        var _this = this;
        _commonService.getDmmxList(dmm,function (res) {
            for (var i = 0; i < res.length; i++) {
                var temp = res[i];
                if (temp.dmmxid==def) {
                    res[i].status=false;
                }else{
                    res[i].status=true;
                }
            }
            _this.renderTemp(_temp.dmmOptDef,{list:res},el);
        },function (errMsg) {
            _this.fail(errMsg);
        });
    },
    isNull: function (a) {
        if (a == null || a == undefined || (typeof a == 'string' && a == '')) {
            return true;
        }
        return false;
    },
    sleep: function (callback, time) {
        if (typeof callback == "function") {
            setTimeout(callback, time);
        }
    },
    confirm: function (msg) {
        return confirm(msg)
    }

}

$(function(){
    _common.init();
});