document.addEventListener('DOMContentLoaded', function() {
    const paginationLinks = document.querySelectorAll('.freedcs_page_link');

    paginationLinks.forEach(link => {
        link.addEventListener('click', function() {
            const page = this.getAttribute('data-page');
            if (page !== null) {
                document.getElementById('page').value = page;
                document.getElementById('searchForm').submit();
            }
        });
    });

    // 검색 버튼 클릭 시 검색어를 폼에 설정하고 제출
    document.getElementById('btn_search').addEventListener('click', function() {
        const kw = document.getElementById('search_kw').value;
        document.getElementById('kw').value = kw;
        document.getElementById('searchForm').submit();
    });

    // 검색창에서 Enter 키 눌렀을 때 폼 제출
    document.getElementById('search_kw').addEventListener('keypress', function(event) {
        if (event.key === 'Enter') {
            event.preventDefault();
            document.getElementById('btn_search').click();
        }
    });

    // 경고창 닫기
    window.closeAlert_list = function() {
        document.getElementById('errorMessage').style.display = 'none';
    };
});


// 삭제 버튼
    const free_delete_elements = document.getElementsByClassName("freedcsDetail_content_delete_btn");
    Array.from(free_delete_elements).forEach(function(element) {
        element.addEventListener('click', function() {
            if(confirm("정말로 삭제하시겠습니까?")) {
                location.href = this.dataset.uri;
            }
        });
    });

    // 추천 버튼
    const free_recommend_elements = document.getElementsByClassName("freedcsDetail_vote");
    Array.from(free_recommend_elements).forEach(function(element) {
    element.addEventListener('click', function() {
        if(confirm("정말로 추천하시겠습니까?")) {
            location.href = this.dataset.uri;
        };
    });
});

    //alert 창 닫기
 function closeAlert_list() {
            document.getElementById('errorMessage').style.display = 'none';
        }
  function closeAlert_form() {
             document.getElementById('error').style.display = 'none';
         }