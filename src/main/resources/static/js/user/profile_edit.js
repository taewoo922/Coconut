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

// 비밀번호 확인 필드와 메시지 요소 가져오기
const passwordField = document.getElementById('password');
const confirmPasswordField = document.getElementById('password-confirm');
const passwordMessage = document.getElementById('password-message');

// 비밀번호 확인 필드 입력 감지 및 일치 여부 확인
confirmPasswordField.addEventListener('input', function() {
    const password = passwordField.value;
    const confirmPassword = confirmPasswordField.value;

    if (password === confirmPassword) {
        passwordMessage.textContent = '비밀번호가 일치합니다.';
        passwordMessage.style.color = 'green';
        passwordMessage.style.background = 'white';
        passwordMessage.style.fontSize = '0.8em';
        passwordMessage.style.padding = '0.5em';
    } else {
        passwordMessage.textContent = '비밀번호가 일치하지 않습니다.';
        passwordMessage.style.color = 'red';
        passwordMessage.style.background = 'white';
        passwordMessage.style.fontSize = '0.8em';
        passwordMessage.style.padding = '0.5em';
    }
});

document.addEventListener('DOMContentLoaded', function() {
    var editSubmitBtn = document.querySelector('#edit-submit-btn');
    var backButton = document.querySelector('#back-btn');

    if (editSubmitBtn) {
        editSubmitBtn.addEventListener('click', function(event) {
            // 비밀번호 확인
            const password = document.getElementById('password').value;
            const confirmPassword = document.getElementById('password-confirm').value;

            if (password !== confirmPassword) {
                alert('비밀번호가 일치하지 않습니다.');
                return;
            }

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