content = $('#content');
mainmenu = $('#mainmenu');

initMenu = function () {
    $.ajax({
        type: 'POST',
        url: '/menu/getMenus',
        success: function(msg) {
            var menus = msg.data;
            var html = [];
            html.push('<ul class="nav">');
            for (var i = 0, len = menus.length; i < len; i++) {
                html.push($.util.fullProperty('<li class=""><a href="javascript:;" class="menu" data-url="{url}">{name}</a></li>', menus[i]));
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
        },
        error: function(msg) {
            alert('注销失败');
        }
    });
}();

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