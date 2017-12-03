var mainpanel = $('.mainpanel');
var editpanel = $('.editpanel');
var searchform = $('#searchform');
var editform = $('#editform');

var successbidgrid = $('#successbidgrid').grid({
    method: 'GET',
    url: '/successbid/findSuccessBid',
    params: {inWarehouse: 0},
    autoload: true,
    paginationRender: 'pagination',
    setData: function (data) {
    },
    afterLoad: function(data) {
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
    }
}).data('grid');

successbidgrid.on('rowdoubleclick', function(event) {
    showEditPanel();
});

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

$('#removeRow').click(function() {
    successbidgrid.unSelected();
    showEditPanel();
    editform[0].reset();
});

$('#back').click(function() {
    togglePanel();
});

$('#save').click(function() {
    var data = editform.serializeObject();

    for (var p in data) {
        if (!data[p]) {
            var input = $('input[name="' + p + '"]');

            if (input.hasClass('empty')) continue;

            alert(input.attr('placeholder') + '不能为空');
            input.focus();
            return;
        }
    }

    $.ajax({
        type: 'POST',
        url: '/successbid/save',
        data: data,
        success: function(msg) {
            if (msg.success) {
                editform.loadForm(msg);
                togglePanel();
            } else {
                alert('保存失败');
            }
        },
        error: function(msg) {
            alert('保存失败');
        }
    });
});

togglePanel = function() {
    editpanel.toggle();
    mainpanel.toggle();

    if (editpanel.is(':hidden')) {
        successbidgrid.load();
    }
}

showEditPanel = function() {
    var row = successbidgrid.getSelected();

    if (row && row.id) {
        togglePanel();
        editform.loadForm(row);
    }
}

removeRow = function() {
    if (confirm('是否删除')) {
        var row = successbidgrid.getSelected();

        if (row) {
            $.ajax({
                type: 'POST',
                url: '/successbid/remove',
                data: row,
                success: function(msg) {
                    successbidgrid.load();
                },
                error: function(msg) {
                }
            });
        }
    }
}

entrystorage = function() {
    if (confirm('是否入库')) {
        var row = successbidgrid.getSelected();

        if (row) {
            $.ajax({
                type: 'POST',
                url: '/successbid/updateInWarehouse',
                data: {id: row.id, inWarehouse: 1},
                success: function(msg) {
                    successbidgrid.load();
                },
                error: function(msg) {
                }
            });
        }
    }
}