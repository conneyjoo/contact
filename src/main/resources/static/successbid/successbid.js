var curPage = $.util.urlParam('curPage');
var pageSize = $.util.urlParam('pageSize');
var selectedIndex = $.util.urlParam('selectedIndex');

var mainpanel = $('.mainpanel');
var editpanel = $('.editpanel');
var searchform = $('#searchform');
var editform = $('#editform');

$("#name_succ").val(localStorage.getItem("name_succ"));
$("#area_succ").val(localStorage.getItem("area_succ"));
$("#concludeTimeBegin").val(localStorage.getItem("concludeTimeBegin_succ"));
$("#concludeTimeEnd").val(localStorage.getItem("concludeTimeEnd_succ"));

var successbidgrid = $('#successbidgrid').grid({
    method: 'GET',
    url: '/successbid/findSuccessBid',
    params: {inWarehouse: 0, curPage: curPage || 0, pageSize: pageSize || $.fn.grid.defaults.pageMax},
    autoload: true,
    paginationRender: 'pagination',
    setData: function (data, i) {
        data.rowNo = i + 1 + (this.params.curPage  * this.params.pageSize);
        if(data.status==0){
            data.statusView='进行中';
            data.statusColor='#055bf5';
        }else if(data.status==1){
            data.statusView='已结束';
            data.statusColor='#ff0800';
        }
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
            totalData[p] = '<b style="color: red;">' + totalData[p].toFixed(6) + '</b>';
        }

        var children = this.append(totalData).children();
        children.eq(0).html('<span style="color:red;font-weight: bold">合计</span>');
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

successbidgrid.on('rowdoubleclick', function(event) {
    showEditPanel();
});

changeStatus = function (id) {
    $.ajax({
        type: 'POST',
        url: '/successbid/changeStatus',
        data: {"id":id},
        async:false,
        success : function(msg) {
            if(msg.data=="f"){
                alert("您没有更改此条状态的权限！！！")
            }else{
                successbidgrid.load();
            }
        }
    });
}

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

    for(var key in data){
        localStorage.setItem(key+"_succ",data[key])
    }
});

$("#resetButton").click(function(){
    $('#searchform')[0].reset();
    var data = $('#searchform').serializeObject();
    successbidgrid.load(data);
    for(var key in data){
        localStorage.setItem(key+"_succ",data[key])
    }
})

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
                successbidgrid.load();
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