let nav_container = document.querySelector(".nav_container");

const toggleBtn = document.querySelector('.nav__bar');
    const main = document.querySelector('.nav__main');
    const icon = document.querySelector('.icon'); // 추가: icon 변수를 정의해야 합니다.

    toggleBtn.addEventListener('click', () => {
        main.classList.toggle('active');
        icon.classList.toggle('active');
    });

window.addEventListener('scroll', function() {
    let value = this.window.scrollY;

    // 스크롤 값에 따라 배경색 조절
    if (value > 100) {
        nav_container.style.transition = 'background-color 0.3s ease';
        nav_container.style.backgroundColor = '#FFFFE0';
    } else {
        nav_container.style.transition = 'background-color 0.3s ease';
        nav_container.style.backgroundColor = '';
        nav__link.style.color = '';
    }
});