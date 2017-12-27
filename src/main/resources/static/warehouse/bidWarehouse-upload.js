var id = $.util.urlParam('id');
var type = $.util.urlParam('type');
var curPage = $.util.urlParam('curPage');
var pageSize = $.util.urlParam('pageSize');
var selectedIndex = $.util.urlParam('selectedIndex');

$.ajax({
    type: 'POST',
    url: '/file/getFiles',
    data: {bidId: id, type: type},
    success: function(files) {
        var file;

        for (var i = 0, len = files.length; i < len; i++) {
            file = files[i];
            uploader.loadFile(file.id, file.name, file.path, '/../images/' + file.path);
        }
    },
    error: function(msg) {
        alert('上传失败');
    }
});

var uploader = $('#uploadPicture').uploader({
    autoUpload: true,
    chunk_size: 1048576000,
    url: '/uploadTmp',
    deleteActionOnDone: function(file, doRemoveFile) {
    },
}).on('onBeforeUpload', function(event, file) {
}).on('onFileUploaded', function(event, file) {
}).data('zui.uploader');

$('#back').click(function() {
    window.location.href = 'bidWarehouse.html?curPage=' + curPage + '&pageSize=' + pageSize + '&selectedIndex=' + selectedIndex;
});