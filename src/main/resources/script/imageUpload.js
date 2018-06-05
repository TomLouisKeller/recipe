// This is triggered by the link
function prepareUploadImage () {
    var uploadField = document.getElementById("uploadImageInput");
    uploadField.click();

}

// Automatically triggered after a file has been selected
function doUploadImage() {
    document.getElementById("uploadImageForm").submit();
}