var mainpanel = $('.mainpanel');
var editpanel = $('.editpanel');
var searchform = $('#searchform');
var editform = $('#editform');

$("#print").click(function(){
    window.print();
})

var successbidgrid = $('#successbidgrid').grid({
    method: 'GET',
    url: '/successbid/findSuccessBid',
    params: {inWarehouse: 0},
    autoload: true,
    paginationRender: 'pagination',
    setData: function (data) {
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

$('#add').click(function() {
    successbidgrid.unSelected();
    showEditPanel();
    $("#save").show();
    editform[0].reset();
    editform.find(':input').removeAttr('readonly');
    $(".form-date", editform).datetimepicker({
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
    togglePanel();

    var row = successbidgrid.getSelected();

    if (row && row.id) {
        editform.loadForm(row);

        if (localStorage.getItem('level') != 69905) {
            var flag = false;

            editform.find(':input').each(function() {
                if (!$(this).val()) {
                    flag = true;
                }
            });

            if (flag) $('#save').show();
            else $('#save').hide();

            for (var p in row) {
                var input = $('input[name=' + p + ']', editform);

                if (row[p]) {
                    input.attr('readonly', '');
                    if (input.hasClass('form-date')) {
                        input.datetimepicker('remove');
                    }
                } else {
                    input.removeAttr('readonly');
                    if (input.hasClass('form-date')) {
                        input.datetimepicker({
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
                    }
                }
            }
        }
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

$('#editouter').height(window.screen.availHeight - 175);