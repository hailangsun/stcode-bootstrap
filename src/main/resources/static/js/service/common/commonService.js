var _commonService = {
    getYwid: function(param,resolve,reject){
        _mm.request({
            url       : _mm.getServerUrl(fxkzBasePath + 'common/getBh?ywlx=' +param.ywlx + "&ywhjdm=" + param.ywhjdm ),
            success   : resolve,
            error     : reject
        });
    },
    getFxMkList: function (mk,resolve,reject) {
        _mm.request({
            url       : _mm.getServerUrl(fxkzBasePath + 'common/getFxMkList1?mkmc=' + mk  ),
            success   : resolve,
            error     : reject
        });
    },
    getDmmxList: function (dmm,resolve,reject) {
        _mm.request({
            url       : _mm.getServerUrl(fxkzBasePath + 'common/getDmmxList?dmm=' + dmm  ),
            success   : resolve,
            error     : reject
        });
    },
}