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
document.querySelector('.delete-btn').addEventListener('click', function(event) {
   if (!confirm('정말로 회원 탈퇴를 하시겠습니까?')) {
        event.preventDefault();
   }
});