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

    const page_elements = document.getElementsByClassName("freedcs_page_link");
    Array.from(page_elements).forEach(function(element) {
        element.addEventListener('click', function() {
            document.getElementById('page').value = this.dataset.page;
            document.getElementById('searchForm').submit();
        });
    });
    const btn_search = document.getElementById("btn_search");
    btn_search.addEventListener('click', function() {
        document.getElementById('kw').value = document.getElementById('search_kw').value;
        document.getElementById('page').value = 0;  // 검색버튼을 클릭할 경우 0페이지부터 조회한다.
        document.getElementById('searchForm').submit();
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

     // 스크랩 버튼
    const free_scrap_elements = document.getElementsByClassName("freedcsDetail_scrap");
        Array.from(free_scrap_elements).forEach(function(element) {
            element.addEventListener('click', function() {
                var uri = element.getAttribute("data-uri");
                fetch(uri, {
                    method: "POST"
                })
                .then(response => {
                    if (response.ok) {
                        alert("스크랩이 완료되었습니다.");
                    } else {
                        alert("스크랩에 실패했습니다.");
                    }
                });
            });
        });

    //alert 창 닫기
 function closeAlert_list() {
            document.getElementById('errorMessage').style.display = 'none';
        }
  function closeAlert_form() {
             document.getElementById('error').style.display = 'none';
         }