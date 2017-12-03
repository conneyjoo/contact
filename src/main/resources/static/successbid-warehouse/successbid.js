var mainpanel = $('.mainpanel');
var editpanel = $('.editpanel');
var searchform = $('#searchform');
var editform = $('#editform');

var successbidgrid = $('#successbidgrid').grid({
    method: 'GET',
    url: '/successbid/findSuccessBid',
    params: {inWarehouse: 1},
    autoload: true,
    paginationRender: 'pagination',
    setData: function (data) {
    },
    afterLoad: function(data) {
        if (data.length > 0) {
            var totalData = {contactPrice: 0, judgementPrice: 0, managementCost: 0, premiumCost: 0};

            for (var i = 0, len = data.length; i < len; i++) {
                totalData.contactPrice += data[i].contactPrice;
                totalData.judgementPrice += data[i].judgementPrice;
                totalData.managementCost += data[i].managementCost;
                totalData.premiumCost += data[i].premiumCost;
            }

            totalData.contactPrice = '<b style="color: red;">' + totalData.contactPrice + '</b>';
            totalData.judgementPrice = '<b style="color: red;">' + totalData.judgementPrice + '</b>';
            totalData.managementCost = '<b style="color: red;">' + totalData.managementCost + '</b>';
            totalData.premiumCost = '<b style="color: red;">' + totalData.premiumCost + '</b>';

            var children = this.append(totalData).children();
            children.eq(0).html('<span style="color:red;font-weight: bold">金额合计</span>');
            children.eq(10).html('');
            children.eq(10).html('');
            children.eq(13).html('');
            children.eq(14).html('');
            children.eq(16).html('');

            loadPermission();
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