$.ajax({
    type: 'POST',
    url: '/user/get',
    success: function(user) {
        for (var p in user) {
            $('#' + p).html(user[p]);
        }
    },
    error: function(msg) {
    }
});