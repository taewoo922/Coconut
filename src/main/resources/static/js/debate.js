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