const delete_elements = document.getElementsByClassName("delete");
    Array.from(delete_elements).forEach(function(element) {
        element.addEventListener('click', function() {
            if(confirm("정말로 삭제하시겠습니까?")) {
                location.href = this.dataset.uri;
            };
        });
    });