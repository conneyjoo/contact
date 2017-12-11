var successBidId = $.util.urlParam('successBidId');

var mainpanel = $('.mainpanel');
var editpanel = $('.editpanel');
var searchform = $('#searchform');
var editform = $('#editform');

var successbidmoneygrid = $('#successbidmoneygrid').grid({
    method: 'GET',
    url: '/successbidmoney/findSuccessBidMoney',
    params: {'successBid.id': successBidId},
    autoload: true,
    setData: function (data) {
        data.successBid = {id: data.successBidId};
    },
    afterLoad: function(data) {
        if (!data || data.length == 0) return;

        var totalData = {
            profit: 0,
            ticketOpenNinTax: 0,
            ticketOpenTax: 0,
            ticketOpenAmount: 0,
            collectedAmount: 0,
            paymentAmount: 0,
            taxBillNinTax: 0,
            taxBillTax: 0,
            paymentAmount: 0,
            taxBillAmount: 0,
            normalBillAmount: 0,
            payrollAmount: 0
        };

        for (var i = 0, len = data.length; i < len; i++) {
            for (var p in totalData) {
                totalData[p] += data[i][p];
            }
        }

        for (var p in totalData) {
            totalData[p] = '<b style="color: red;">' + totalData[p] + '</b>';
        }

        var children = this.append(totalData).children();
        children.eq(0).html('<span style="color:red;font-weight: bold">合计</span>');
        children.eq(35).html('');

        loadPermission();
    }
}).data('grid');

successbidmoneygrid.on('rowdoubleclick', function(event) {
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
    successbidmoneygrid.load(data);
});

$('#add').click(function() {
    successbidmoneygrid.unSelected();
    showEditPanel();
    editform[0].reset();
    $('#successBidId').val(successBidId);
});

$('#removeRow').click(function() {
    successbidmoneygrid.unSelected();
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
        url: '/successbidmoney/save',
        data: data,
        success: function(msg) {
            if (msg.success) {
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
        successbidmoneygrid.load();
    }
}

showEditPanel = function() {
    togglePanel();
    var row = successbidmoneygrid.getSelected();

    if (row) {
        editform.loadForm(row);
        $('#successBidId').val(successBidId);
    }
}

removeRow = function() {
    if (confirm('是否删除')) {
        var row = successbidmoneygrid.getSelected();

        if (row) {
            $.ajax({
                type: 'POST',
                url: '/successbidmoney/remove',
                data: {id: row.id},
                success: function(msg) {
                    successbidmoneygrid.load();
                },
                error: function(msg) {
                }
            });
        }
    }
}

$('#editouter').height(window.screen.availHeight - 175);