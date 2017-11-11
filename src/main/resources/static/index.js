content = $('#content');
mainmenu = $('#mainmenu');

menus = [
    {name: '合同管理', uri: 'contact/list.html', permission: 0x0001},
    {name: '合同仓库', uri: '', permission: 0x0010},
    {name: '招标管理', uri: '', permission: 0x0001},
    {name: '招标仓库', uri: '', permission: 0x0010},
    {name: '用户管理', uri: '', permission: 0x0100}
]

initMenu = function (p) {
    var html = [];
    html.push('<ul class="nav">');
    for (var i = 0, len = menus.length; i < len; i++) {
        if ((menus[i].permission & p) === menus[i].permission) {
            html.push($.util.fullProperty('<li class=""><a href="javascript:;" class="menu" data-url="{uri}">{name}</a></li>', menus[i]));
        }
    }
    html.push('</ul>');
    mainmenu.html(html.join(''));

    $('.menu').click(function() {
        var el = $(this);
        var url = el.data('url');

        $('.menu').each(function() { $(this).parent().removeClass('active'); });
        el.parent().addClass("active");
        content.attr('src', url);
    });
}(0x1111);

content.load(function() {
    content.height($(content[0].contentDocument.body).height());
});

$('#logout').click(function() {
    $.ajax({
        type: 'POST',
        url: root + '/user/logout',
        success : function(msg) {
            location.href = 'login.html';
        },
        error : function(msg) {
            alert('注销失败');
        }
    });
});

