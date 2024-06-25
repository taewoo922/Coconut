$(document).ready(function() {
    function handleScrollButtons(cardList) {
            var scrollLeft = cardList.scrollLeft();
            var maxScrollLeft = cardList.get(0).scrollWidth - cardList.get(0).clientWidth;

            if (scrollLeft > 0) {
                cardList.siblings('.scroll-button-left').fadeIn();
            } else {
                cardList.siblings('.scroll-button-left').fadeOut();
            }

            if (scrollLeft < maxScrollLeft) {
                cardList.siblings('.scroll-button-right').fadeIn();
            } else {
                cardList.siblings('.scroll-button-right').fadeOut();
            }
        }

        $('.card-list').each(function() {
            var cardList = $(this);

            handleScrollButtons(cardList);

            cardList.on('scroll', function() {
                handleScrollButtons(cardList);
            });

            cardList.siblings('.scroll-button-left').on('click', function() {
                cardList.animate({ scrollLeft: '-=200' }, 300);
            });

            cardList.siblings('.scroll-button-right').on('click', function() {
                cardList.animate({ scrollLeft: '+=200' }, 300);
            });
        });
    });
