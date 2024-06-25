const latestSection = document.querySelector('.Latest_section');
const latestList = document.querySelectorAll('.main_Latest_Info');
const hotTopicSection_freedcs = document.querySelector('.Hot_Topic_section_freedcs');
const hotTopicList_freedcs = document.querySelectorAll('.main_Hot_Topic_Info_freedcs');
const hotTopicSection_debate = document.querySelector('.Hot_Topic_section_debate');
const hotTopicList_debate = document.querySelectorAll('.main_Hot_Topic_Info_debate');

    let index4 = 0;
    let index5 = 0;
    let index6 = 0;

    function showItems4() {
    latestList.forEach((item, i) => {
        if (i >= index4 && i < index4 + 4) {
            item.style.display = 'block';
        } else {
            item.style.display = 'none';
        }
    });
}

function showItems5() {
    hotTopicList_freedcs.forEach((item, i) => {
        if (i >= index5 && i < index5 + 4) {
            item.style.display = 'block';
        } else {
            item.style.display = 'none';
        }
    });
}

function showItems6() {
    hotTopicList_debate.forEach((item, i) => {
        if (i >= index6 && i < index6 + 4) {
            item.style.display = 'block';
        } else {
            item.style.display = 'none';
        }
    });
}

    showItems4();
    showItems5();
    showItems6();

document.querySelector('.nextBtn4').addEventListener('click', () => {
    index4 = Math.min(index4 + 4, latestList.length - 4);
    showItems4();
});

document.querySelector('.prevBtn4').addEventListener('click', () => {
    index4 = Math.max(index4 - 4, 0);
    showItems4();
});

document.querySelector('.nextBtn5').addEventListener('click', () => {
    index5 = Math.min(index5 + 4, hotTopicList_freedcs.length - 4);
    showItems5();
});

document.querySelector('.prevBtn5').addEventListener('click', () => {
    index5 = Math.max(index5 - 4, 0);
    showItems5();
});

document.querySelector('.nextBtn6').addEventListener('click', () => {
    index6 = Math.min(index6 + 4, hotTopicList_debate.length - 4);
    showItems6();
});

document.querySelector('.prevBtn6').addEventListener('click', () => {
    index6 = Math.max(index6 - 4, 0);
    showItems6();
});


let free_Sec_Title = document.querySelector(".free_Sec_Title");
let free_Sec_Content = document.querySelector(".free_Sec_Content");
let pcd_Sec_Title = document.querySelector(".pcd_Sec_Title");
let pcd_Sec_Content = document.querySelector(".pcd_Sec_Content");
let nav_human = document.querySelector(".human");

window.addEventListener('scroll', function(){
    let value = this.window.scrollY
    console.log("scrollY", value);

    if (value > 1300) {
            nav_human.style.backgroundImage = "url('/images/main_human2.png')";
        } else {
            nav_human.style.backgroundImage = "";
        }

    if(value>2100 || value < 1250){
        free_Sec_Title.style.animation= "disappear 1s ease-out forwards";
    }else if(value>1200){
        free_Sec_Title.style.animation= "slide 1s ease-out";
    }

     if(value>2350 || value < 1600){
        free_Sec_Content.style.animation= "disappear 1s ease-out forwards";
    }else if(value>1600){
        free_Sec_Content.style.animation= "slide 1s ease-out";
    }

    if(value>3100 || value < 2250){
        pcd_Sec_Title.style.animation= "disappear2 1s ease-out forwards";
    }else if(value>2200){
        pcd_Sec_Title.style.animation= "slide2 1s ease-out";
    }

     if(value>3350 || value < 2400){
        pcd_Sec_Content.style.animation= "disappear2 1s ease-out forwards";
    }else if(value>2400){
        pcd_Sec_Content.style.animation= "slide2 1s ease-out";
    }
})

