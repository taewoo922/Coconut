const latestSection = document.querySelector('.search_myList_section');
const mainLatest1List = document.querySelectorAll('.search_myList_Info');
const latestSection2 = document.querySelector('.search_report_section');
const mainLatest1List2 = document.querySelectorAll('.search_report_Info');

let index = 0;
let index2 = 0;

function showItems() {
    mainLatest1List.forEach((item, i) => {
        if (i >= index && i < index + 4) {
            item.style.display = 'block';
        } else {
            item.style.display = 'none';
        }
    });
}

function showItems2() {
    mainLatest1List2.forEach((item2, i) => {
        if (i >= index2 && i < index2 + 4) {
            item2.style.display = 'block';
        } else {
            item2.style.display = 'none';
        }
    });
}

showItems();
showItems2();

document.querySelector('.nextBtn1').addEventListener('click', () => {
    index = Math.min(index + 4, mainLatest1List.length - 4);
    showItems();
});

document.querySelector('.prevBtn1').addEventListener('click', () => {
    index = Math.max(index - 4, 0);
    showItems();
});

document.querySelector('.nextBtn2').addEventListener('click', () => {
    index2 = Math.min(index2 + 4, mainLatest1List2.length - 4);
    showItems2();
});

document.querySelector('.prevBtn2').addEventListener('click', () => {
    index2 = Math.max(index2 - 4, 0);
    showItems2();
});
