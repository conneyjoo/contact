var curPage = $.util.urlParam('curPage');
var pageSize = $.util.urlParam('pageSize');
var selectedIndex = $.util.urlParam('selectedIndex');

var mainpanel = $('.mainpanel');
var editpanel = $('.editpanel');
var searchform = $('#searchform');
var editform = $('#editform');

var successbidgrid = $('#successbidgrid').grid({
    method: 'GET',
    url: '/successbid/findSuccessBid',
    params: {inWarehouse: 1, curPage: curPage || 0, pageSize: pageSize || $.fn.grid.defaults.pageMax},
    autoload: true,
    paginationRender: 'pagination',
    setData: function (data, i) {
        data.rowNo = i + 1 + (this.params.curPage  * this.params.pageSize);
    },
    afterLoad: function(data) {
        if (!data || data.length == 0) return;

        var totalData = {contactPrice: 0, judgementPrice: 0, premiumCost: 0};

        for (var i = 0, len = data.length; i < len; i++) {
            for (var p in totalData) {
                totalData[p] += data[i][p] || 0;
            }
        }

        for (var p in totalData) {
            totalData[p] = '<b style="color: red;">' + totalData[p].toFixed(4) + '</b>';
        }

        var children = this.append(totalData).children();
        children.eq(0).html('<span style="color:red;font-weight: bold">金额合计</span>');
        children.eq(10).html('');
        children.eq(11).html('');
        children.eq(13).html('');
        children.eq(14).html('');
        children.eq(16).html('');
        children.eq(17).html('');

        loadPermission();

        $('.forword').click(function() {
            var el = $(this);
            successbidgrid.rows[el.data('index') - 1].click();
            var curPage = successbidgrid.params.curPage;
            var pageSize = successbidgrid.params.pageSize;
            var selectedIndex = successbidgrid.getSelected() ? successbidgrid.getSelected().rowIndex - 1 : '';
            window.location.href = el.data('url') + '&curPage=' + curPage + '&pageSize=' + pageSize + '&selectedIndex=' + selectedIndex;
        });

        if (selectedIndex) {
            this.rows[selectedIndex].click();
        }
    }
}).data('grid');

$(".form-date").datetimepicker({
    language: 'zh-cn',
    weekStart: 1,
    todayBtn: 1,
    autoclose: 1,
    todayHighlight: 1,
    startView: 2,
    minView: 2,
    forceParse: 0,
    format: 'yyyy-mm-dd'
});

$(".numberField").numberField({});

$('#searchbtn').click(function() {
    var data = searchform.serializeObject();
    successbidgrid.load(data);
});

$("#resetButton").click(function(){
    $('#searchform')[0].reset();
    var data = $('#searchform').serializeObject();
    successbidgrid.load(data);
})

backstorage = function() {
    if (confirm('是否撤回')) {
        var row = successbidgrid.getSelected();

        if (row) {
            $.ajax({
                type: 'POST',
                url: '/successbid/updateInWarehouse',
                data: {id: row.id, inWarehouse: 0},
                success: function(msg) {
                    successbidgrid.load();
                },
                error: function(msg) {
                }
            });
        }
    }
}