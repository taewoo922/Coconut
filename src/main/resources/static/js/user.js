const scrapSection = document.querySelector('.search_scrap_section');
const scrapList = document.querySelectorAll('.search_scrap_Info');
const myListSection = document.querySelector('.search_myList_section');
const myListList = document.querySelectorAll('.search_myList_Info');
const reportSection = document.querySelector('.search_report_section');
const reportList = document.querySelectorAll('.search_report_Info');

let index1 = 0;
let index2 = 0;
let index3 = 0;

function showItems1() {
    scrapList.forEach((item, i) => {
        if (i >= index1 && i < index1 + 4) {
            item.style.display = 'block';
        } else {
            item.style.display = 'none';
        }
    });
}

function showItems2() {
    myListList.forEach((item, i) => {
        if (i >= index2 && i < index2 + 4) {
            item.style.display = 'block';
        } else {
            item.style.display = 'none';
        }
    });
}

function showItems3() {
    reportList.forEach((item, i) => {
        if (i >= index3 && i < index3 + 4) {
            item.style.display = 'block';
        } else {
            item.style.display = 'none';
        }
    });
}

showItems1();
showItems2();
showItems3();

document.querySelector('.nextBtn1').addEventListener('click', () => {
    index1 = Math.min(index1 + 4, myListList.length - 4);
    showItems1();
});

document.querySelector('.prevBtn1').addEventListener('click', () => {
    index1 = Math.max(index1 - 4, 0);
    showItems1();
});

document.querySelector('.nextBtn2').addEventListener('click', () => {
    index2 = Math.min(index2 + 4, reportList.length - 4);
    showItems2();
});

document.querySelector('.prevBtn2').addEventListener('click', () => {
    index2 = Math.max(index2 - 4, 0);
    showItems2();
});

document.querySelector('.nextBtn3').addEventListener('click', () => {
    index3 = Math.min(index3 + 4, scrapList.length - 4);
    showItems3();
});

document.querySelector('.prevBtn3').addEventListener('click', () => {
    index3 = Math.max(index3 - 4, 0);
    showItems3();
});
