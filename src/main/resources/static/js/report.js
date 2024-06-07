// 질문 게시판 삭제 버튼
    const delete_elements = document.getElementsByClassName("reportDetail_content_delete_btn");
    Array.from(delete_elements).forEach(function(element) {
        element.addEventListener('click', function() {
            if(confirm("정말로 삭제하시겠습니까?")) {
                location.href = this.dataset.uri;
            }
        });
    });

    // 질문 게시판 추천 버튼
    const recommend_elements = document.getElementsByClassName("reportDetail_vote");
    Array.from(recommend_elements).forEach(function(element) {
    element.addEventListener('click', function() {
        if(confirm("정말로 추천하시겠습니까?")) {
            location.href = this.dataset.uri;
        };
    });
});

        // 질문 게시판 검색 기능
        const page_elements = document.getElementsByClassName("page-link");
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