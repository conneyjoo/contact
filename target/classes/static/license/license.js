var curPage = $.util.urlParam('curPage');
var pageSize = $.util.urlParam('pageSize');
var selectedIndex = $.util.urlParam('selectedIndex');

var mainpanel = $('.mainpanel');
var editpanel = $('.editpanel');
var searchform = $('#searchform');
var editform = $('#editform');
$("#relyPriceWarning").hide();

$("#name_succ").val(localStorage.getItem("name_succ"));
$("#area_succ").val(localStorage.getItem("area_succ"));
$("#concludeTimeBegin").val(localStorage.getItem("concludeTimeBegin_succ"));
$("#concludeTimeEnd").val(localStorage.getItem("concludeTimeEnd_succ"));

var licenseGrid = $('#licenseGrid').grid({
    method: 'GET',
    url: '/license/findLicenses',
    params: {curPage: curPage || 0, pageSize: pageSize || $.fn.grid.defaults.pageMax},
    autoload: true,
    paginationRender: 'pagination',
    setData: function (data, i) {
        data.rowNo = i + 1 + (this.params.curPage  * this.params.pageSize);
        if(data.warning){
            data.statusColor='#055bf5';
        }else{
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
            licenseGrid.rows[el.data('index') - 1].click();
            var curPage = licenseGrid.params.curPage;
            var pageSize = licenseGrid.params.pageSize;
            var selectedIndex = licenseGrid.getSelected() ? licenseGrid.getSelected().rowIndex - 1 : '';
            window.location.href = el.data('url') + '&curPage=' + curPage + '&pageSize=' + pageSize + '&selectedIndex=' + selectedIndex;
        });

        if (selectedIndex) {
            this.rows[selectedIndex].click();
        }
    }
}).data('grid');

licenseGrid.on('rowdoubleclick', function(event) {
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
    licenseGrid.load(data);

    for(var key in data){
        localStorage.setItem(key+"_succ",data[key])
    }
});

$("#resetButton").click(function(){
    $('#searchform')[0].reset();
    var data = $('#searchform').serializeObject();
    licenseGrid.load(data);
    for(var key in data){
        localStorage.setItem(key+"_succ",data[key])
    }
})

$('#add').click(function() {
    licenseGrid.unSelected();
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
    licenseGrid.unSelected();
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
        url: '/license/saveOrUpdate',
        data: data,
        success: function(msg) {
            if (msg.success) {
                editform.loadForm(msg);
                togglePanel();
                licenseGrid.load();
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

    var row = licenseGrid.getSelected();

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
        var row = licenseGrid.getSelected();

        if (row) {
            $.ajax({
                type: 'POST',
                url: '/license/remove',
                data: row,
                success: function(msg) {
                    licenseGrid.load();
                },
                error: function(msg) {
                }
            });
        }
    }
}

entrystorage = function() {
    if (confirm('是否入库')) {
        var row = licenseGrid.getSelected();

        if (row) {
            $.ajax({
                type: 'POST',
                url: '/successbid/updateInWarehouse',
                data: {id: row.id, inWarehouse: 1},
                success: function(msg) {
                    licenseGrid.load();
                },
                error: function(msg) {
                }
            });
        }
    }
}

    //数字校验
    $("#relyPrice").keyup(function(){
        var c=$(this);
        if(/[^\d]/.test(c.val()) && !/^\d+\.\d+$/.test(c.val())){//替换非数字字符
            $("#relyPriceWarning").show();
            $("#bidPriceDiv").attr("class","col-md-8 col-sm-10 has-error");
        }else{
            $("#relyPriceWarning").hide();
            $("#bidPriceDiv").attr("class","col-md-8 col-sm-10");
        };
    })

$('#editouter').height(window.screen.availHeight - 175);