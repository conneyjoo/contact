var successBidId = $.util.urlParam('successBidId');
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
            addedTax: 0,
            constructionCost: 0,
            constructionIncomeTax: 0,
            printingTax: 0,
            accountInvoice: 0,
            insurancePremium: 0,
            deductibleTax: 0,
            paiedTax: 0,
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
        $("input[name='incomeTax']").val((ticketOpenAmount/100).toFixed(6));//扣：企业所得税 (4=(1)*1%)
        $("input[name='addedTax']").val((ticketOpenAmount/1.11*0.11).toFixed(6));//扣：应交增值税 (5=(1)/1.11*0.11)
        $("input[name='constructionIncomeTax']").val((ticketOpenAmount/200).toFixed(6));//扣：个所税 (7=(1)*0.5%)
        $("input[name='printingTax']").val((ticketOpenAmount/100*0.03).toFixed(6));//扣：印花税 (8=(1)*0.03%)
    }

    //其他金额计算
    var collectedAmount = $("input[name='collectedAmount']").val(); //(2)
    var addedTax = $("input[name='addedTax']").val();   // (5)
    var constructionIncomeTax = $("input[name='constructionIncomeTax']").val(); //(7)
    var deductibleTax = $("input[name='deductibleTax']").val();   // (11)
    if(isNotPirce(collectedAmount)&&isNotPirce(addedTax)&&isNotPirce(deductibleTax)){
        alert("保存失败，收款金额和应交增值税以及抵扣增值税必须是数字！！！");
        return;
    }else{
        $("input[name='constructionCost']").val(((addedTax-deductibleTax)/100*12).toFixed(6));//扣：城建及教费 （6=（5-11）*12%）
    }

    //应付项目工程款计算
    var managementCost = $("input[name='managementCost']").val(); //(3)
    var incomeTax = $("input[name='incomeTax']").val() //(4)
    var constructionCost = $("input[name='constructionCost']").val() //(6)
    var printingTax = $("input[name='printingTax']").val() //(8)
    var accountInvoice = $("input[name='accountInvoice']").val(); //(9)
    var insurancePremium = $("input[name='insurancePremium']").val();   // (10)
    var paiedTax = $("input[name='paiedTax']").val();   // (12)
    if(isNotPirce(accountInvoice)&&isNotPirce(insurancePremium)&&isNotPirce(paiedTax)&&isNotPirce(managementCost)){
        alert("保存失败，暂扣做账发票和保险费以及已交增值税 管理费用必须是数字！！！");
        return;
    }else{
        $("input[name='projectPayment']").val(collectedAmount-managementCost-incomeTax-addedTax-constructionCost-constructionIncomeTax-printingTax
        -accountInvoice-insurancePremium+(deductibleTax/1+paiedTax/1));//应付项目工程款 （14=2-3-4-5-6-7-8-9-10+11+12）
    }

    var receivableInvoice = $("input[name='receivableInvoice']").val(); //(15)
    var receiptInvoice = $("input[name='receiptInvoice']").val() //(16)
    if(isNotPirce(receivableInvoice)&&isNotPirce(receiptInvoice)){
        alert("保存失败，暂扣做账发票和保险费以及已交增值税 管理费用必须是数字！！！");
        return;
    }else{
        $("input[name='lackInvoice']").val(receivableInvoice-receiptInvoice);//尚缺材料发票 （17=15-16）
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