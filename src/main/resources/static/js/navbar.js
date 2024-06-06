const toggleBtn = document.querySelector('.nav__bar');
    const main = document.querySelector('.nav__main');
    const icon = document.querySelector('.icon'); // 추가: icon 변수를 정의해야 합니다.

    toggleBtn.addEventListener('click', () => {
        main.classList.toggle('active');
        icon.classList.toggle('active');
    });
