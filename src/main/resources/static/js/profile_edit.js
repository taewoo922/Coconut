// 이미지 미리보기 함수
function previewImage(event) {
    var reader = new FileReader();
    reader.onload = function(){
        var profileImgPreview = document.querySelector('.profile-img-preview img');
        if (profileImgPreview) {
            profileImgPreview.src = reader.result;
        }
    };
    reader.readAsDataURL(event.target.files[0]);
}
