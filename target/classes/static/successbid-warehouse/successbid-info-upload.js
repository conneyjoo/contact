var id = $.util.urlParam('id');
var type = $.util.urlParam('type');
var curPage = $.util.urlParam('curPage');
var pageSize = $.util.urlParam('pageSize');
var selectedIndex = $.util.urlParam('selectedIndex');

$.ajax({
    type: 'POST',
    url: '/file/getFiles',
    data: {successBidId: id, type: type},
    success: function(files) {
        var file;

        for (var i = 0, len = files.length; i < len; i++) {
            file = files[i];
            if (file.businessType < 9) {
                $('#uploadPicture' + file.businessType).data('zui.uploader').loadFile(file.id, file.name, file.path, '/../images/' + file.path);
            }
        }

        $('.btn-delete-file').hide();
    },
    error: function(msg) {
        alert('上传失败');
    }
});

var fileIds = [1,2,3,4,5,6,7,8,100,101,102,103]
for (var i = 0; i <= fileIds.length; i++) {
    $('#uploadPicture' + fileIds[i]).uploader({
        autoUpload: true,
        chunk_size: 1048576000,
        url: '/uploadTmp',
        deleteActionOnDone: function(file, doRemoveFile) {
            $.ajax({
                type: 'POST',
                url: '/file/remove',
                data: {id: file.remoteData.id, path: file.remoteData.tempFile},
                success: function(msg) {
                    doRemoveFile();
                },
                error: function(msg) {
                    alert('删除失败');
                }
            });
        },
    }).on('onBeforeUpload', function(event, file) {
    }).on('onFileUploaded', function(event, file) {
        save(file, $(event.currentTarget).data('business'));
    }).data('zui.uploader');
}

save = function(file, businessType) {
    var data = {};
    data.type = type;
    data.successBidId = id;
    data.businessType = businessType;

    $('#uploadPicture' + businessType).data('zui.uploader').mergeData(data);
    data.name = data.originalFilenames;
    data.path = data.tempFiles;

    $.ajax({
        type: 'POST',
        url: '/file/save',
        data: data,
        success: function(msg) {
            file.remoteData.id = msg.data.id;
        },
        error: function(msg) {
            alert('上传失败');
        }
    });
}

$('#back').click(function() {
    window.location.href = 'successbid.html?curPage=' + curPage + '&pageSize=' + pageSize + '&selectedIndex=' + selectedIndex;
});

$('#mainouter').height(window.screen.availHeight - 175);