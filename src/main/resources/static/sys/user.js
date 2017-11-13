var mainpanel = $('.mainpanel');
var editpanel = $('.editpanel');
var searchform = $('#searchform');
var editform = $('#editform');

var usergrid = $('#usergrid').grid({
    method: 'GET',
    url: '/user/findUser',
    autoload: true,
    paginationRender: 'pagination',
    setData: function(data) {
        data.sexName = data.sex == 'M' ? '男' : '女';
    }
}).data('grid');

usergrid.on('rowdoubleclick', function(event) {
    showEditPanel();
});

$('#searchbtn').click(function() {
    var data = searchform.serializeObject();
    usergrid.load(data);
});

$('#add').click(function() {
    usergrid.unSelected();
    showEditPanel();
    editform[0].reset();
});

$('#removeRow').click(function() {
    usergrid.unSelected();
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

    if (!data.username) {
        el.html(text);
        alert('用户名不能为空');
        return;
    }

    if (!data.password) {
        el.html(text);
        alert('密码不能为空');
        return;
    }

    if (!data.name) {
        el.html(text);
        alert('姓名不能为空');
        return;
    }

    if (!data.phone) {
        el.html(text);
        alert('电话不能为空');
        return;
    }

    if (!data.sex) {
        el.html(text);
        alert('性别不能为空');
        return;
    }

    if (!data.roleId) {
        el.html(text);
        alert('角色不能为空');
        return;
    }

    data['role.id'] = data.roleId;

    $.ajax({
        type: 'POST',
        url: '/user/save',
        data: data,
        success : function(msg) {
            if (msg.success) {
                el.html(text);
                editform.loadForm(msg);
                togglePanel();
            } else if (msg.status == -1) {
                alert('用户名已存在');
            } else {
                alert('保存失败');
            }
        },
        error : function(msg) {
            el.html(text);
            alert('保存失败');
        }
    });
});

togglePanel = function() {
    editpanel.toggle();
    mainpanel.toggle();

    if (editpanel.is(':hidden')) {
        usergrid.load();
        $('#username').removeAttr('readonly');
    }
}

showEditPanel = function() {
    togglePanel();
    var row = usergrid.getSelected();

    if (row) {
        editform.loadForm(row);
        $('#username').attr('readonly', 'readonly');
    }
}

removeRow = function() {
    if (confirm('是否删除')) {
        var row = usergrid.getSelected();

        if (row) {
            $.ajax({
                type: 'POST',
                url: '/user/remove',
                data: row,
                success : function(msg) {
                    usergrid.load();
                },
                error : function(msg) {
                }
            });
        }
    }
}