var successBidId = $.util.urlParam('successBidId');
var name = $.util.urlParam('name');
var curPage = $.util.urlParam('curPage');
var pageSize = $.util.urlParam('pageSize');
var selectedIndex = $.util.urlParam('selectedIndex');

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
            ticketOpenAmount: 0,
            collectedAmount: 0,
            managementCost: 0,
            incomeTax: 0,
            accountInvoice: 0,
            insurancePremium: 0,
            addedTax: 0,
            deductibleTax: 0,
            temporaryPayment: 0,
            projectPayment: 0,
            receivableInvoice: 0,
            receiptInvoice: 0,
            lackInvoice: 0,
            surplusTax: 0,
        };

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
        children.eq(35).html('');

        loadPermission();
    }
}).data('grid');

successbidmoneygrid.on('rowdoubleclick', function(event) {
    showEditPanel();
});

$("#autoCalculate").click(function () {
    //以开票金额为基数计算的数值
    var ticketOpenAmount = $("input[name='ticketOpenAmount']").val();//(1)
    if(isNotPirce(ticketOpenAmount)){
        alert("保存失败，开票金额必须是数字！！！");
        return;
    }else{
        $("input[name='incomeTax']").val((ticketOpenAmount*0.125).toFixed(6));//扣：税金 (4=(1)*12.5%)
        $("input[name='receivableInvoice']").val((ticketOpenAmount*0.7).toFixed(6));//应收材料发票 (11=(1)*70%)
    }

    //其他金额计算
    var collectedAmount = $("input[name='collectedAmount']").val(); //(2)
    var managementCost = $("input[name='managementCost']").val(); //(3)
    var incomeTax = $("input[name='incomeTax']").val() //(4)
    var accountInvoice = $("input[name='accountInvoice']").val(); // (5)
    var insurancePremium = $("input[name='insurancePremium']").val();   // (6)
    var deductibleTax = $("input[name='deductibleTax']").val();   // (7)
    var addedTax = $("input[name='addedTax']").val();   // (8)
    var temporaryPayment = $("input[name='temporaryPayment']").val(); //(9)
    /*if(isNotPirce(collectedAmount)&&isNotPirce(addedTax)&&isNotPirce(deductibleTax)){
        alert("保存失败，收款金额和应交增值税以及抵扣增值税必须是数字！！！");
        return;
    }else{
        $("input[name='constructionCost']").val(((addedTax-deductibleTax)/100*12).toFixed(6));//扣：城建及教费 （6=（5-11）*12%）
    }*/

    //应付项目工程款计算
    if(isNotPirce(collectedAmount)||isNotPirce(managementCost)||isNotPirce(incomeTax)||isNotPirce(accountInvoice)
        ||isNotPirce(insurancePremium)||isNotPirce(deductibleTax)||isNotPirce(addedTax)){
        alert("保存失败，金额必须是数字！！！");
        return;
    }else{
        $("input[name='projectPayment']").val(collectedAmount-managementCost-incomeTax-accountInvoice-insurancePremium+deductibleTax/1
        +addedTax/1-temporaryPayment);//应付项目工程款 （10=2-3-4-5-6+7+8-9）
    }

    var receivableInvoice = $("input[name='receivableInvoice']").val(); //(11)
    var receiptInvoice = $("input[name='receiptInvoice']").val() //(12)
    if(isNotPirce(receivableInvoice)&&isNotPirce(receiptInvoice)){
        alert("保存失败，暂扣做账发票和保险费以及已交增值税 管理费用必须是数字！！！");
        return;
    }else{
        $("input[name='lackInvoice']").val(receivableInvoice-receiptInvoice);//尚缺材料发票 （13=11-12）
    }

})

function isNotPirce(s){
    return /[^\d]/.test(s) && !/^\d+\.\d+$/.test(s);
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
    successbidmoneygrid.load(data);
});

$('#add').click(function() {
    successbidmoneygrid.unSelected();
    showEditPanel();
    editform[0].reset();
    $('#successBidId').val(successBidId);
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
    successbidmoneygrid.unSelected();
    showEditPanel();
    editform[0].reset();
});

$('#backButton').click(function() {
    togglePanel();
    //window.location.href = 'successbid.html?curPage=' + curPage + '&pageSize=' + pageSize + '&selectedIndex=' + selectedIndex;
});

$("#back").click(function(){
    window.location.href = 'successbid.html?curPage=' + curPage + '&pageSize=' + pageSize + '&selectedIndex=' + selectedIndex;
    })

$('#save').click(function() {
    var data = editform.serializeObject();

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

$('#print').click(function() {
    doPrint();
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

//将URL中的UTF-8字符串转成中文字符串
function getCharFromUtf8(str) {
    var cstr = "";
    var nOffset = 0;
    if (str == "")
        return "";
    str = str.toLowerCase();
    nOffset = str.indexOf("%e");
    if (nOffset == -1)
        return str;
    while (nOffset != -1) {
        cstr += str.substr(0, nOffset);
        str = str.substr(nOffset, str.length - nOffset);
        if (str == "" || str.length < 9)
            return cstr;
        cstr += utf8ToChar(str.substr(0, 9));
        str = str.substr(9, str.length - 9);
        nOffset = str.indexOf("%e");
    }
    return cstr + str;
}

function utf8ToChar(str) {
    var iCode, iCode1, iCode2;
    iCode = parseInt("0x" + str.substr(1, 2));
    iCode1 = parseInt("0x" + str.substr(4, 2));
    iCode2 = parseInt("0x" + str.substr(7, 2));
    return String.fromCharCode(((iCode & 0x0F) << 12) | ((iCode1 & 0x3F) << 6) | (iCode2 & 0x3F));
}

function doPrint() {
    document.title = getCharFromUtf8(name) + "("+localStorage.getItem("companyName")+"工程款申请单)";
    var body = $(document.body), wrap = $('#wrap'), table = $($('.datatable').parent().html());
    wrap.hide();
    table.width('100%');
    body.append(table);
    window.print();
    table.remove();
    wrap.show();
    return false;
}