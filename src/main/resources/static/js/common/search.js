const scrapSection = document.querySelector('.search_scrap_section');
const scrapList = document.querySelectorAll('.search_scrap_Info');
const myListSection_freedcs = document.querySelector('.search_myList_section_freedcs');
const myListList_freedcs = document.querySelectorAll('.search_myList_Info_freedcs');
const reportSection = document.querySelector('.search_report_section');
const reportList = document.querySelectorAll('.search_report_Info');
const myListSection_debate = document.querySelector('.search_myList_section_debate');
const myListList_debate = document.querySelectorAll('.search_myList_Info_debate');

let index1 = 0;
let index2 = 0;
let index3 = 0;
let index7 = 0;

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
    myListList_freedcs.forEach((item, i) => {
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

function showItems7() {
    myListList_debate.forEach((item, i) => {
        if (i >= index7 && i < index7 + 4) {
            item.style.display = 'block';
        } else {
            item.style.display = 'none';
        }
    });
}



showItems1();
showItems2();
showItems3();
showItems7();

document.querySelector('.nextBtn1').addEventListener('click', () => {
    index1 = Math.min(index1 + 4, scrapList.length - 4);
    showItems1();
});

document.querySelector('.prevBtn1').addEventListener('click', () => {
    index1 = Math.max(index1 - 4, 0);
    showItems1();
});

document.querySelector('.nextBtn2').addEventListener('click', () => {
    index2 = Math.min(index2 + 4, myListList_freedcs.length - 4);
    showItems2();
});

document.querySelector('.prevBtn2').addEventListener('click', () => {
    index2 = Math.max(index2 - 4, 0);
    showItems2();
});

document.querySelector('.nextBtn3').addEventListener('click', () => {
    index3 = Math.min(index3 + 4, reportList.length - 4);
    showItems3();
});

document.querySelector('.prevBtn3').addEventListener('click', () => {
    index3 = Math.max(index3 - 4, 0);
    showItems3();
});

document.querySelector('.nextBtn7').addEventListener('click', () => {
    index7 = Math.min(index7 + 4, myListList_debate.length - 4);
    showItems7();
});

document.querySelector('.prevBtn7').addEventListener('click', () => {
    index7 = Math.max(index7 - 4, 0);
    showItems7();
});