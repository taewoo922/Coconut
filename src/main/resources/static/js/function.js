const toggleBtn = document.querySelector('.nav__bar')
const main = document.querySelector('.nav__main')

toggleBtn.addEventListener('click', () =>
{
    main.classList.toggle('active');
    icon.classList.toggle('active');
});
