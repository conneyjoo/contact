/*
 * util.js
 * 
 * @author Conney Joo
 * @version 1.0
 */
$(document).ready(function() {
});

$(document).bind('pagebeforecreate', function(e) {
});

$.fn.extend({
	
	loadForm: function(data) {
    	this.find(':input').each(function() {
    		var name = $(this).attr('name');
    		if (!name) return;
    		if (this.type == 'radio' || this.type == 'checkbox') {
				if (this.value === $.util.property(data, $(this).attr('name')))
					this.checked = true
    		} else {
				$(this).val($.util.property(data, name));
				$(this).change && $(this).change();
	        	$(this).removeClass("textblur")
    		}
    	})
	},
	
	serializeObject: function() {
	    var obj = {};
	    $.each(this.serializeArray(), function(index, param) {
	        if (!(param.name in obj)) {
                obj[param.name] = param.value
	        } else {
                if (obj[param.name] && obj[param.name] instanceof Array) {
                    obj[param.name].push(param.value);
                } else if (obj[param.name]) {
                    obj[param.name] = [param.value, obj[param.name]]
                }
			}
	    });
	    return obj
	},
	
	formData: function() {
	},
	
	numberField: function() {
		this.keydown(function(event) { 
			var keyCode = event.which;
			if (keyCode == 46 || keyCode == 8 || keyCode == 37
							|| keyCode == 39
							|| (keyCode >= 48 && keyCode <= 57)
							|| (keyCode >= 96 && keyCode <= 105)) { 
				return true 
			} else { 
				return false 
			} 
		}).focus(function() { 
			this.style.imeMode = 'disabled' 
		})
	}
});

$.util = {};

$.util.urlParam = function(name) {
	if (name) {
		var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
		var r = window.location.search.substr(1).match(reg);
		if (r != null) return r[2];
	  	//if (r != null) return unescape(r[2]) 
	  	return null
	} else {
		var s = window.location.search.substr(1);
		var array = s.split('&'), tmp, params = {};
		for (var i = 0, len = array.length; i < len; i++) {
			tmp = array[i].split('=');
			params[tmp[0]] = tmp[1];
		}
		return params;
	}
};

$.util.property = function(o, p) {
	var i = p.indexOf('.');
    if (i > -1) {
    	var name = p.substring(0, i > -1 ? i : p.length);
    	return $.util.property(o[name], p.substring(i + 1))
    }
	return o[p]
};

$.util.fullProperty = function(s, data) {
	return s.replace(/\{([\w\-.]+.)\}/g, function(m, name) {
		var val = $.util.property(data, name)
		return val !== undefined && val !== null ? $.util.property(data, name) : ''
	})
};

Date.prototype.format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S": this.getMilliseconds()
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

/** ========================= Macro ========================= **/
var message = function(text) {
	$.scojs_message(text, $.scojs_message.TYPE_OK)
};

var error = function(text) {
	$.scojs_message(text, $.scojs_message.TYPE_ERROR)
};

var pathname = window.document.location.pathname;
var root = pathname && pathname.substring(0, pathname.substr(1).indexOf('/') + 1);

var unitarea = {
	'330102': '上城',
	'330103': '下城',
	'330104': '江干',
	'330105': '拱墅',
	'330106': '西湖',
	'330118': '下沙',
	'330108': '滨江',
	'330109': '萧山',
	'330110': '余杭',
	'330183': '富阳',
	'330185': '临安',
	'330122': '桐庐',
	'330182': '建德',
	'330127': '淳安',
	'330119': '景区',
	'330111': '大江东'
};