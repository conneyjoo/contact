var id = $.util.urlParam('id');
var type = $.util.urlParam('type');

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

        $('.btn-delete-file').addClass('role').attr('data-permission', '65536');
        loadPermission();
    },
    error: function(msg) {
        alert('上传失败');
    }
});

for (var i = 1; i <= 8; i++) {
    $('#uploadPicture' + i).uploader({
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

$('#mainouter').height(window.screen.availHeight - 175);