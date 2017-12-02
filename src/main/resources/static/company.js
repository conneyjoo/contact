var grid = $('#grid').grid({
    method: 'GET',
    url: '/company/findAll',
    autoload: true
}).data('grid');

selectCompany = function() {
    var row = grid.getSelected();

    if (row) {
        $.ajax({
            type: 'POST',
            url: '/company/select',
            data: row,
            success: function(msg) {
                localStorage.rowType = row.type;
                window.parent.location.reload();
            },
            error: function(msg) {
            }
        });
    }
}