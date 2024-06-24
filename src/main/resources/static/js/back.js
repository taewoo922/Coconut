document.addEventListener('DOMContentLoaded', function() {
    const backButton = document.getElementById('back-btn');
    if (backButton) {
        backButton.addEventListener('click', function() {
            history.back();
        });
    }
});