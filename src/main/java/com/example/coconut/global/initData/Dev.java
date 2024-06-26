package com.example.coconut.global.initData;

import com.example.coconut.domain.category.entity.Category;
import com.example.coconut.domain.category.service.CategoryService;
import com.example.coconut.domain.discussion_Type.service.DebateService;
import com.example.coconut.domain.discussion_Type.service.FreedcsService;
import com.example.coconut.domain.scrap.service.ScrapService;
import com.example.coconut.domain.user.entity.User;
import com.example.coconut.domain.user.entity.UserRole;
import com.example.coconut.domain.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Profile("dev")
public class Dev {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    public ApplicationRunner init(UserService userService, CategoryService categoryService, FreedcsService freedcsService, DebateService debateService) {
        return args -> {
            userService.signup("test", "1234", "testnickname", "test@test.com", "01012341234", UserRole.USER);
            userService.signup("user", "1234", "usernickname", "user@test.com", "01012341234", UserRole.USER);
            userService.signup("esong", "1234", "esongnickname", "esong@test.com", "01012341234", UserRole.USER);
            userService.signup("admin", "1234", "admin", "admin@test.com", "01012341234", UserRole.ADMIN);


            categoryService.addCategory("사회");
            categoryService.addCategory("연애");
            categoryService.addCategory("스포츠");
            categoryService.addCategory("음식");
            categoryService.addCategory("유머");
            categoryService.addCategory("기타");

            User user = userService.getUserByUsername("user");
            Category category1 = categoryService.getCategoryById((long)1);
            Category category2 = categoryService.getCategoryById((long)2);
            Category category3 = categoryService.getCategoryById((long)3);
            Category category4 = categoryService.getCategoryById((long)4);
            Category category5 = categoryService.getCategoryById((long)5);
            Category category6 = categoryService.getCategoryById((long)6);

            freedcsService.create("딱딱 복숭아 vs 물렁 복숭아","여름제철과일 복숭아 다들 딱복이신가요 물복파이신가요?","freedcs/" + "복숭아" + ".jpg", user, category4);
            freedcsService.create("닭이 먼저인가/ 알이 먼저인가","닭은 알을낳고 알에서는 닭이 태어나는데 누가 먼저일까요?","freedcs/" + "닭알" + ".jpg", user, category6);
            freedcsService.create("콩국수에 소금 vs 콩국수에 설탕","더운날씨 시원한 음식중 하나인 콩국수 소금뿌려드시나요? 설탕뿌려드시나요?","freedcs/" + "콩국수" + ".jpg", user, category4);
            freedcsService.create("5년 동안 1명 만난 사람 vs 1년에 5명 만난 사람","썸에서 이제 연인으로 된 사람이 있는데 5년 장기연애가 낫다? 아니면 1년에 2~3달에 한번씩 5명 만난사람이 낫다? 다들 어느쪽이신가요?","freedcs/" + "장기연애단기연애" + ".jpg", user, category2);
            freedcsService.create("호불호 음료논란 - 솔의 눈, 데자와, 실론티, 맥콜, 아침햇살","호불호 많이 갈리는 대표적인 음료인 솔의 눈, 데자와, 실론티, 맥콜, 아침햇살 이 중에 다들 싫어하시는 음료 있으신가요?\n" +
                    "저는 아침햇살빼고 다 싫어합니다ㅎ","freedcs/" + "호불호음료" + ".jpg", user, category4);
            freedcsService.create("득점왕 손흥민 vs 리그우승,챔스우승, 트레블까지해본 박지성","리그우승은 할 수 없지만 챔스는 가끔 나가고 득점왕을 하는 손흥민 vs 리그우승,챔스우승,트레블까지 할 수 있는 박지성 \n" +
                    "둘 중 누구의 삶을 더 살고싶은가요??","freedcs/" + "손흥민박지성" + ".jpg", user, category3);
            freedcsService.create("바퀴벌레와 동거 vs 귀신과 동거","바퀴벌레의 징그러움과 귀신의 으스함중 당신의 선택은?","freedcs/" + "바퀴벌레" + ".jpg", user, category6);
            freedcsService.create("시력 vs 청력","1가지에 몰빵할 수 있다면 당신의 선택은??","freedcs/" + "시력청력" + ".jpg", user, category5);
            freedcsService.create("남자친구가 내 친구의 패딩지퍼를 올려준다면?","기분 나쁘지만 그럴 수 있다 vs 미친거아니야? 그럴 순 없다","freedcs/" + "패딩" + ".jpg", user, category2);
            freedcsService.create("평생 1가지만 볼 수 있다면?","드라마 vs 영화","freedcs/" + "드라마영화" + ".jpg", user, category5);
            freedcsService.create("내 동생이면 좋을거같은 캐릭터는?","짱구 vs 노진구 ","freedcs/" + "노진구" + ".jpg", user, category5);
            freedcsService.create("로또 조작","로또 1등 나도 될 수 있을까? 로또는 조작인가 아닌가!","freedcs/" + "로또" + ".jpg", user, category1);
            freedcsService.create("잠수이별/ 환승이별","잠수이별과 환승이별 중 뭐가 더 나쁘고 슬플까?","freedcs/" + "이별" + ".jpg", user, category2);
            freedcsService.create("바나나 색깔","바나나는 노란색일까요 흰색일까요?","freedcs/" + "바나나" + ".jpg", user, category4);
            freedcsService.create("기프티콘 결제","친구들과 같이 카페에 가거나 음식점에 가서 선물 받은 기프티콘으로 결제한다면!\n" +
                    "기프티콘 결제는 더치페이 한다? 안한다?","freedcs/" + "기프티콘" + ".jpg", user, category5);
            freedcsService.create("초능력이 생긴다면?","초능력을 가질 수 있다면 어떤 초능력을 가지고 싶으신가요?\n" +
                    "이유도 적어주세요!","freedcs/" + "초능력" + ".jpg", user, category5);

            debateService.create("한화는 올해도 꼴찌하고 내년에 가을야구 도전하는게 낫다","그동안 모은 유망주 + FA영입(채은성,안치홍) + 류현진(170억) 까지 가지고도 현재성적 7위이면 차라리\n" +
                    "올시즌도 꼴찌해서 유망주나 더 모으고 내년에 가을야구를 도전하는게 낫다 <- 찬성\n" +
                    "그래도 열심히 끝까지 다해서 경험을 많이 쌓고 1경기 하고 떨어지더라도 올해 가을야구를 도전해봐야한다 <- 반대","debate/" + "한화이글스" + ".jpg", user, category3);
            debateService.create("환경을 위해 플라스틱 사용을 전면 금지해야한다?","지구온난화로 인한 환경운동중 가장 우리생활에 근접한 플라스틱 사용량 줄이기. 그럼에도 실질적으로 피부에 와닿는 변화는 못느끼는데요\n" +
                    "그럼에도 환경을 위해 플라스틱 사용을 줄이고 나아가 사용 전면 금지를 해야한다 <- 찬성\n" +
                    "우리나라만 열심히 분리수거와 플라스틱 사용을 줄이고 있다 다른나라는 우리나라 처럼 하지않는다 그러므로 우리도 그냥 플라스틱을 사용하자 <- 반대","debate/" + "플라스틱" + ".jpg", user, category1);
            debateService.create("연애중인 혹은 결혼전 연인의 동거 유무 괜찮나요??","현재 사귄지 얼마 안된 연인과의 대화중 우연히 알게된 과거 X와의 동거생활 괜찮으면 찬성 안괜찮으면 반대","debate/" + "동거" + ".jpg", user, category2);
            debateService.create("무한도전 시즌2 하는게 좋을까? 실일까 득일까?","재미있는 예능이 없어진 요즘, 레전드 예능 무한도전이 시즌2로 돌아오는 사람은 찬성 아닌사람은 반대. \n" +
                    "만약 돌아온다면 멤버는 누가 좋을까?","debate/" + "무도" + ".jpg", user, category5);
            debateService.create("모두가 천재인 팀에서 좌절감 느끼기 vs 모두를 책임지는 에이스 역할 맡기","좌절감느끼고 버스타는게 낫다 <- 찬성,\n" +
                    "스트레스는 받지만 성취감을 느끼고 에이스가 되는게 낫다 <- 반대","debate/" + "좌절 에이스" + ".jpg", user, category5);
            debateService.create("동물원을 운영하는 것은 바람직한가","동물원은 인간의 추악함을 담아낸곳이라고 한다 '동물원 운영' 계속 해야하나?","debate/" + "동물원" + ".jpg", user, category1);
            debateService.create("남북통일은 꼭 이루어져야하는가","최근 북한과 더욱 불편해지고 있다 당장 통일이 가능하다면 통일을 해야하나?","debate/" + "남북" + ".jpg", user, category1);
            debateService.create("사형제도 부활해야하는가","많은 사람들이 원하고 있는 사형제도 부활하는게 바람직한가?","debate/" + "사형제도" + ".jpg", user, category1);
            debateService.create("수술실 CCTV를 활성화해야하는가","의사: 잠재적 범죄자 취급이다. <- 반대\n" +
                    "환자: 대리 수술 방지할 수 있다. <- 찬성 안괜찮으면 반대","debate/" + "수술실" + ".jpg", user, category1);
            debateService.create("최저임금 인상 찬성/반대","최저임금을 인상하여야 한다. <- 찬성\n" +
                    "이미 많이 올랐다 <- 반대","debate/" + "최저임금" + ".jpg", user, category1);
            debateService.create("주 4일 근로제 도입","주 7일중 4일을 일하고 3일을 쉬는 제도\n" +
                    "최근 몇몇 기업에서는 자체적으로 주 4일 근로제를 도입하고 시행하고 있다.","debate/" + "주4일제" + ".jpg", user, category1);
            debateService.create("길고양이 밥","길고양이에게 밥을 주는 행동 옳은 것일까?","debate/" + "길고양이" + ".jpg", user, category6);
            debateService.create("노인 운전자 면허 반납해야한다?","노인 운전자 사고가 많아 지고있는 현재 노인들의 운전자 면허 갱신 규제도 강화되는데 반납하는게 맞을까요?","debate/" + "노인운전" + ".jpg", user, category1);
            debateService.create("식당에서 일화용 물티슈 사용 금지","식당에서 흔히 볼 수 있는 일회용 물티슈에도 플라스틱이 포함되어 있다는 사실을 알고 있었나요?\n" +
                    " 2025년 말부터는 플라스틱이 포함된 물티슈를 식당에서 찾아보기 힘들 것예상됩니다.\n" +
                    "일회용 물티슈 사용을 금지하는 것에 찬성합니다.(찬성)\n" +
                    "일회용 물티슈 사용을 금지하는 것에 반대합니다.(반대)","debate/" + "일회용품" + ".jpg", user, category6);

        };
    }
}
