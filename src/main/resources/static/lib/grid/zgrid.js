$.fn.grid.Constructor.prototype.createPagination = function() {
	this.createSort();

    this.params.curPage = this.params.curPage || 0;
    this.params.pageSize = this.params.pageSize || 10;
	var pagination = $(this.template.pagination);
	
	this.createPrePageButton(pagination, this);
	this.createPageButton(pagination, this.params.curPage === 0, 'icon-play icon-rotate-180', '上一页', this.params.curPage == 0 ? 0 : this.params.curPage - 1, this);
	this.createPageButton(pagination, false, 'icon-play', '下一页', this.params.curPage + 1, this);

	return pagination;
}

$.fn.grid.Constructor.prototype.createPageButton = function(parent, disabled, icon, text, cp, grid) {
	if (disabled) {
		$('<i class="' + icon + '" title="' + text + '"></i>').appendTo(parent);
	} else {
		$('<a style="text-decoration: none; cursor: pointer;"></a>').appendTo(parent).append(
			$('<i class="' + icon + '" title="' + text + '"></i>').bind('click', function() {
				grid.params.curPage = cp;
				grid.load();
			})
		);
	}
}

$.fn.grid.Constructor.prototype.createPrePageButton = function(pagination, grid) {
	$('<span>第 <strong>' + (parseInt(grid.params.curPage) + 1) + '</strong> 页，</span>').appendTo(pagination);
    $('<span>记录数 <strong>' + grid.rows.length + '</strong> ，</span>').appendTo(pagination);
	
	var prePage = $('<div class="dropdown dropup"><a data-toggle="dropdown" style="text-decoration: none; cursor: pointer;">每页 <strong>' + grid.params.pageSize + '</strong> 条<span class="caret"></span></a></div>').appendTo(pagination);
	var ul = $('<ul class="dropdown-menu">').appendTo(prePage);
	var num = 0;
	for (var i = 0; i < this.prepagenum.length; i++) {
		num = this.prepagenum[i];
		var li = $('<li class="' + (num === grid.params.pageSize ? 'active' : '') + '" data-value="' + num + '"><a>' + num + '</a></li>').appendTo(ul).bind('click', function() {
			grid.params.pageSize = $(this).data('value');
			grid.load();
		});
	}
}

$.fn.grid.defaults.template.pagination = '<div style="float: right; clear: none;" class="pager form-inline">';
$.fn.grid.defaults.prepagenum = [5, 10, 15, 20, 25, 30];

$.fn.grid.Constructor.prototype.createSort = function() {
	var self = this;

	if (!this.sorted) {
        this.grid.prev().children().eq(0).children().each(function() {
            var el = $(this);
            var sort = el.data('sort');

            if (sort) {
                el.bind('click', function() {
                    var sort = el.data('sort'), direction = el.hasClass('sort-up') && el.hasClass('sort-up')

                    if (direction) {
                        el.removeClass('sort-up').addClass('sort-down');
                    } else {
                        el.removeClass('sort-down').addClass('sort-up');
                    }

                    self.load({sort: sort, direction: direction ? 'desc' : 'asc'});
                }).addClass('sort');
            }
        });

        this.sorted = true;
	}
}