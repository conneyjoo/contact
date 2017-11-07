$.fn.grid.Constructor.prototype.createPagination = function(pager) {
	if (pager.totalPage === 0)
		return;
	
	var cp = pager.curPage - 1, tp = pager.totalPage, ps = pager.pageSize, pm = this.pageMax;
	var pb = 0, pe = Math.floor(cp / pm + 1) * pm;
	pe = (tp - pe < 0) ? pe - (pe - tp) : pe;
	pb = pe - pm;
	pb = pb < 1 ? 0 : pb;
			
	this.params.curPage = this.params.curPage || 1;
	this.params.pageSize = ps;
	var pagination = $(this.template.pagination);
	
	this.createPrePageButton(pagination, pager.totalRow, ps, this);
	this.createPageButton(pagination, cp === 0, 'icon-step-backward', '首页', pb < 0 ? 0 : 1, this);
	this.createPageButton(pagination, cp === 0, 'icon-play icon-rotate-180', '上一页', this.params.curPage - 1, this);
	this.createPageButton(pagination, (cp + 1) === tp, 'icon-play', '下一页', this.params.curPage + 1, this);
	this.createPageButton(pagination, (cp + 1) === tp, 'icon-step-forward', '尾页', pe + 1 > tp ? tp : pe + 1, this);
	
	return pagination;
}

$.fn.grid.Constructor.prototype.createPageButton = function(parent, disabled, icon, text, cp, grid) {
	if (disabled) {
		$('<i class="' + icon + '" title="' + text + '"></i>').appendTo(parent);
	} else {
		$('<a style="text-decoration: none; cursor: pointer;"></a>').appendTo(parent).append(
			$('<i class="' + icon + '" title="' + text + '"></i>').bind('click', function() {
				grid.params.curPage = cp;
				grid.params.start = (cp - 1) * grid.params.pageSize;
				grid.load();
			})
		);
	}
}

$.fn.grid.Constructor.prototype.createPrePageButton = function(pagination, tr, ps, grid) {
	$('<span>共 <strong>' + tr + '</strong> 条记录，</span>').appendTo(pagination);
	
	var prePage = $('<div class="dropdown dropup"><a data-toggle="dropdown" style="text-decoration: none; cursor: pointer;">每页 <strong>' + ps + '</strong> 条<span class="caret"></span></a></div>').appendTo(pagination);
	var ul = $('<ul class="dropdown-menu">').appendTo(prePage);
	var num = 0;
	for (var i = 0; i < this.prepagenum.length; i++) {
		num = this.prepagenum[i];
		var li = $('<li class="' + (num === ps ? 'active' : '') + '" data-value="' + num + '"><a>' + num + '</a></li>').appendTo(ul).bind('click', function() {
			grid.params.pageSize = $(this).data('value');
			grid.load();
		});
	}
}

$.fn.grid.defaults.template.pagination = '<div style="float: right; clear: none;" class="pager form-inline">';
$.fn.grid.defaults.prepagenum = [5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 100, 200, 500, 1000, 20000];