$('#submit').click(function() {
    var data = $('#loginform').serializeObject();

    $.ajax({
        type: 'POST',
        url: '/user/login',
        data: data,
        success: function(msg) {
            if (msg.status == 1) {
                localStorage.setItem('name',msg['data']['name']);
                localStorage.setItem('level', msg.data.role.level);
                var companyName = msg.data.companyName;
                localStorage.setItem('companyName',companyName);
                localStorage.rowType = 0;
                if(companyName == "金舜借出投标" || companyName == "亚升借出投标"){
                    localStorage.rowType = 1;
                }
                location.href = 'index.html';
            } else if (msg.status == -1) {
                alert('用户名不正确');
                $('#username').focus();
            } else if (msg.status == -2) {
                alert('密码不正确');
                $('#password').focus();
            } else {
                alert('登录失败');
            }
        },
        error: function(msg) {
            alert('登录失败');
        }
    });
});

document.onkeydown = function(event) {
    var e = event || window.event || arguments.callee.caller.arguments[0];
    if (e && e.keyCode == 13) {
        $('#submit').click();
    }
};