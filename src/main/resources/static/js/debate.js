document.addEventListener('DOMContentLoaded', function() {
    const paginationLinks = document.querySelectorAll('.debate_page_link');

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
    const debate_delete_elements = document.getElementsByClassName("debateDetail_content_delete_btn");
    Array.from(debate_delete_elements).forEach(function(element) {
        element.addEventListener('click', function() {
            if(confirm("정말로 삭제하시겠습니까?")) {
                location.href = this.dataset.uri;
            }
        });
    });

    // 추천 버튼
    const debate_recommend_elements = document.getElementsByClassName("debateDetail_vote");
    Array.from(debate_recommend_elements).forEach(function(element) {
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

document.addEventListener('DOMContentLoaded', function() {
    // 찬성과 반대 댓글 수를 가져옵니다
    const supportCount = parseInt(document.querySelector('.debateDetail_chart_support span').textContent);
    const oppositionCount = parseInt(document.querySelector('.debateDetail_chart_opposition span').textContent);

    // 전체 댓글 수
    const totalCount = supportCount + oppositionCount;

    // 찬성 바 너비 계산
    const supportBar = document.querySelector('.debateDetail_chart_support');
    let supportWidth = (supportCount / totalCount) * 100;

    // 반대 바 너비 계산 및 표시 여부 설정
    const oppositionBar = document.querySelector('.debateDetail_chart_opposition');
    let oppositionWidth = (oppositionCount / totalCount) * 100;

    // 반대 댓글이 0개인 경우 숨김
    if (oppositionCount === 0) {
        oppositionBar.style.display = 'none';
    } else {
        oppositionBar.style.display = 'block';
        oppositionBar.style.width = oppositionWidth + '%';
    }

    // 찬성 댓글이 0개인 경우 숨김
    if (supportCount === 0) {
        supportBar.style.display = 'none';
    } else {
        supportBar.style.display = 'block';
        supportBar.style.width = supportWidth + '%';
    }

    // 찬성과 반대 댓글이 모두 0인 경우 처음 상태 그대로 유지
    if (supportCount === 0 && oppositionCount === 0) {
        supportBar.style.display = 'block';
        oppositionBar.style.display = 'block';
    }
});

    // 스크랩 버튼
        const debate_scrap_elements = document.getElementsByClassName("debateDetail_scrap");
            Array.from(debate_scrap_elements).forEach(function(element) {
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
                            };
                        });



