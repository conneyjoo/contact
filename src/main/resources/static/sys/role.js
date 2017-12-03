var mainpanel = $('.mainpanel');
var editpanel = $('.editpanel');
var searchform = $('#searchform');
var editform = $('#editform');

var rolegrid = $('#rolegrid').grid({
    method: 'GET',
    url: '/role/findRole',
    autoload: true
}).data('grid');

rolegrid.on('rowdoubleclick', function(event) {
    showEditPanel();
});

$('#searchbtn').click(function() {
    var data = searchform.serializeObject();
    rolegrid.load(data);
});

$('#add').click(function() {
    rolegrid.unSelected();
    showEditPanel();
    editform[0].reset();
    menugrid.load({id: null});
    actiongrid.load({id: null});
});

$('#removeRow').click(function() {
    rolegrid.unSelected();
    showEditPanel();
    editform[0].reset();
});

$('#back').click(function() {
    togglePanel();
});

$('.dropdown').each(function() {
    var el = $(this);
    el.grid({
        method: 'GET',
        url: el.data('url'),
        autoload: true
    });
});

$('#save').click(function() {
    var el = $(this);
    var text = el.html();
    el.html(el.data('loading'));

    var data = editform.serializeObject();
    data.menuIds = $('#menuIds').val();
    /*data.actionIds = $('#actionIds').val();*/

    if (!data.name) {
        el.html(text);
        alert('角色名不能为空');
        return;
    }

    if (!data.menuIds) {
        el.html(text);
        alert('请选择菜单');
        return;
    }

    /*if (!data.actionIds) {
        el.html(text);
        alert('请选择接口');
        return;
    }*/

    $.ajax({
        type: 'POST',
        url: '/role/save',
        data: data,
        success: function(msg) {
            el.html(text);
            editform.loadForm(msg);
            togglePanel();
        },
        error: function(msg) {
            el.html(text);
            alert('保存失败');
        }
    });
});

menugrid = $('#menuIds').grid({
    method: 'GET',
    url: '/role/getMenus',
    autoload: false,
    setData: function(data) {
        data.selected = data.menuId ? 'selected' : '';
    }
}).data('grid');

actiongrid = $('#actionIds').grid({
    method: 'GET',
    url: '/role/getActions',
    autoload: false,
    setData: function(data) {
        data.selected = data.actionId ? 'selected' : '';
    }
}).data('grid');

togglePanel = function() {
    editpanel.toggle();
    mainpanel.toggle();

    if (editpanel.is(':hidden')) {
        rolegrid.load();
    }
}

showEditPanel = function() {
    togglePanel();
    var row = rolegrid.getSelected();

    if (row) {
        editform.loadForm(row);
        menugrid.load(row);
        actiongrid.load(row);
    }
}

removeRow = function() {
    if (confirm('是否删除')) {
        var row = rolegrid.getSelected();

        if (row) {
            $.ajax({
                type: 'POST',
                url: '/role/remove',
                data: row,
                success: function(msg) {
                    rolegrid.load();
                },
                error: function(msg) {
                }
            });
        }
    }
}