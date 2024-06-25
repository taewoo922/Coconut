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


document.addEventListener('DOMContentLoaded', function() {
    var editSubmitBtn = document.querySelector('#edit-submit-btn');
    var backButton = document.querySelector('#back-btn');

    if (editSubmitBtn) {
        editSubmitBtn.addEventListener('click', function(event) {
            if (confirm('정말 수정하시겠습니까?')) {
                document.querySelector('#edit-profile-form').submit();
            }
        });
    }

    if (backButton) {
        backButton.addEventListener('click', function(event) {
            history.back(); // 뒤로가기
        });
    }

    var deleteBtn = document.querySelector('.delete-btn');
    if (deleteBtn) {
        deleteBtn.addEventListener('click', function(event) {
            if (!confirm('정말로 회원 탈퇴를 하시겠습니까?')) {
                event.preventDefault();
            }
        });
    }
});