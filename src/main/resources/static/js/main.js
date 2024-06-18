const latestSection = document.querySelector('.Latest_section');
const latestList = document.querySelectorAll('.main_Latest_Info');
const hotTopicSection = document.querySelector('.Hot_Topic_section');
const hotTopicList = document.querySelectorAll('.main_Hot_Topic_Info');
    let index4 = 0;
    let index5 = 0;
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
    hotTopicList.forEach((item, i) => {
        if (i >= index5 && i < index5 + 4) {
            item.style.display = 'block';
        } else {
            item.style.display = 'none';
        }
    });
}
    showItems4();
    showItems5();

    document.querySelector('.nextBtn4').addEventListener('click', () => {
    index4 = Math.min(index4 + 4, latestList.length - 4);
    showItems4();
});

document.querySelector('.prevBtn4').addEventListener('click', () => {
    index4 = Math.max(index4 - 4, 0);
    showItems4();
});

    document.querySelector('.nextBtn5').addEventListener('click', () => {
    index5 = Math.min(index5 + 4, latestList.length - 4);
    showItems5();
});

document.querySelector('.prevBtn5').addEventListener('click', () => {
    index5 = Math.max(index5 - 4, 0);
    showItems5();
});



let free_Sec_Title = document.querySelector(".free_Sec_Title");
let free_Sec_Content = document.querySelector(".free_Sec_Content");
let pcd_Sec_Title = document.querySelector(".pcd_Sec_Title");
let pcd_Sec_Content = document.querySelector(".pcd_Sec_Content");

window.addEventListener('scroll', function(){
    let value = this.window.scrollY
    console.log("scrollY", value);


    if(value>1700 || value < 850){
        free_Sec_Title.style.animation= "disappear 1s ease-out forwards";
    }else if(value>800){
        free_Sec_Title.style.animation= "slide 1s ease-out";
    }

     if(value>1800 || value < 1200){
        free_Sec_Content.style.animation= "disappear 1s ease-out forwards";
    }else if(value>1200){
        free_Sec_Content.style.animation= "slide 1s ease-out";
    }

    if(value>2700 || value < 1850){
        pcd_Sec_Title.style.animation= "disappear2 1s ease-out forwards";
    }else if(value>1800){
        pcd_Sec_Title.style.animation= "slide2 1s ease-out";
    }

     if(value>2800 || value < 2000){
        pcd_Sec_Content.style.animation= "disappear2 1s ease-out forwards";
    }else if(value>2000){
        pcd_Sec_Content.style.animation= "slide2 1s ease-out";
    }
})

