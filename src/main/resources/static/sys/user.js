var grid = $('.grid').grid({
    method: 'GET',
    url: '/user/findUser',
    autoload: true,
    paginationRender: 'pagination',
    setData: function(data) {
    }
}).data('grid');