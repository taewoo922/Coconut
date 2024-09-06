##  💬 서비스명

🥥Coconut🥥
### 📝 서비스 설명
Coconut은 특정 주제나 분야에 관심이 있는 사람들이 모여 이야기를 하며 자유롭게 소통할 수 있도록 도움을 주는 서비스입니다. 친근하고 일상적인 주제부터 심도 있는 주제까지 사용자는 각자의 관심사에 맞는 주제를 찾아볼 수 있으며 또 새로운 주제를 만들어내어 토론하고 지식을 넓힐 수 있습니다. 이와 같이 Coconut은 사용자에게 자유로운 소통 장을 제공합니다.  


## 👨‍💻 개발 기간
개발기간: 2024.05 ~ 2024.06

## 🛠 개발 환경
- 운영체제 : Windows 10/11
- 통합개발환경(IDE) : IntelliJ
- JDK버전 : JDK21
- DB : MariaDB
- 관리 툴 : GitHub

## 📚 Stacks

### Version Control
<div>
<img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">
<img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">
</div>

### Backend Technologies
<div>
<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white">
<img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
</div>

### Frontend Technologies
<div>
<img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white">
<img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white">
<img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black">
</div>

### Databases
<div>
<img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">
<img src="https://img.shields.io/badge/mariaDB-003545?style=for-the-badge&logo=mariaDB&logoColor=white">
</div>

## 웹개발팀 소개

|      김태우       |          송현지         |       김민섭         |                                                                                                               
| :------------------------------------------------------------------------------: | :---------------------------------------------------------------------------------------------------------------------------------------------------: | :---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------: | 
|   <img width="160px" src="https://orgthumb.mt.co.kr/06/2024/02/2024020118023374242_1.jpg" />    |                      <img width="160px" src="https://media.bunjang.co.kr/product/274166908_1_1718981909_w%7Bres%7D.jpg" />    |                   <img width="160px" src="https://mblogthumb-phinf.pstatic.net/MjAyMDA5MTJfODkg/MDAxNTk5OTA0NzY5ODA4.vzOYyQGLs27lTkhPEziGYjLT-TlNrrWWSuolakxwTaEg.g7U60IhKW7_4s7yCp7lPutvlAEmHRGSnMkH7j8KqBAog.JPEG.kn010123/IMG_1507.JPG?type=w800"/>   |
|   [@taewoo](https://github.com/taewoo922)   |    [@hyunji](https://github.com/hyunji1205)  | [@minseop](https://github.com/kimminseop99)  |

## ☁️ ERD

 <img src="src/main/resources/static/images/projectFunctionImage/coconut er다이어그램.png"> 

</br>
</br>

## 프로젝트 구조


```
└─src
    ├─main
    │  ├─generated
    │  ├─java
    │  │  └─com
    │  │      └─example
    │  │          └─coconut
    │  │              ├─domain
    │  │              │  ├─admin
    │  │              │  │  ├─controller
    │  │              │  │  ├─entity
    │  │              │  │  ├─repository
    │  │              │  │  └─service
    │  │              │  ├─answer
    │  │              │  │  ├─controller
    │  │              │  │  ├─entity
    │  │              │  │  ├─repository
    │  │              │  │  └─service
    │  │              │  ├─category
    │  │              │  │  ├─controller
    │  │              │  │  ├─entity
    │  │              │  │  ├─repository
    │  │              │  │  └─service
    │  │              │  ├─discussion_Type
    │  │              │  │  ├─controller
    │  │              │  │  ├─entity
    │  │              │  │  ├─repository
    │  │              │  │  └─service
    │  │              │  ├─home
    │  │              │  │  └─controller
    │  │              │  ├─report
    │  │              │  │  ├─controller
    │  │              │  │  ├─entity
    │  │              │  │  ├─form
    │  │              │  │  ├─repository
    │  │              │  │  └─service
    │  │              │  ├─reportReply
    │  │              │  │  ├─controller
    │  │              │  │  ├─entity
    │  │              │  │  ├─form
    │  │              │  │  ├─repository
    │  │              │  │  └─service
    │  │              │  ├─scrap
    │  │              │  │  ├─controller
    │  │              │  │  ├─entity
    │  │              │  │  ├─repository
    │  │              │  │  └─service
    │  │              │  └─user
    │  │              │      ├─controller
    │  │              │      ├─entity
    │  │              │      ├─repository
    │  │              │      └─service
    │  │              └─global
    │  │                  ├─config
    │  │                  ├─initData
    │  │                  ├─jpa
    │  │                  └─security
    │  └─resources
    │      ├─static
    │      │  ├─css
    │      │  ├─images
    │      │  └─js
    │      └─templates
    │          ├─answer
    │          ├─category
    │          ├─common
    │          ├─discussion
    │          ├─home
    │          ├─manager
    │          ├─report
    │          └─user
    └─test
        └─java
            └─com
                └─example
                    └─coconut
```

## ⚙️ 페이지별 기능

### [회원가입]

#### 1. 회원가입
- 아이디, 닉네임, 비밀번호, 비밀번호 확인, 전화번호, 이메일을 모두 작성하면 회원가입이 가능합니다.
- 비밀번호와 비밀번호 확인란의 문자열이 같지 않을 시에 회원가입이 불가능합니다.
- 뒤로가기가 가능합니다.
</br>

### [로그인]

#### 1. 로그인
- 아이디와 비밀번호 입력시 해당 유저가 가입되어있으면 로그인 됩니다.
- 뒤로가기가 가능합니다.

#### 2. 아이디 찾기/비밀번호 찾기
- 아이디 찾기는 해당 사용자의 등록된 이메일을 입력하면 찾을 수 있습니다.
- 비밀번호 찾기는 아이디와 이메일을 작성하며 검증단계를 거친 후 찾을 수 있습니다.

#### 3. 소셜 로그인
- 카카오, 구글, 네이버의 소셜로그인 진행이 가능합니다.

| 로그인 |
|----------|
| <img src="src/main/resources/static/images/projectFunctionImage/로그인 페이지.png"> |
</br>

### [토론]

#### 1. 게시글 작성
- 자유토론 페이지에서 원하는 주제의 게시물을 작성할 수 있습니다.
- 이미지 파일을 업로드 하지 않을 시에 work - freedcs - coconut.png파일이 업로드 됩니다.
- 게시글 하단에 조회수가 표시됩니다.
- 비속어 입력시나 카테고리, 제목, 내용을 작성하지 않을 시 작성되지 않습니다. 

#### 2. 게시글 수정 및 삭제
- 자신의 게시글인 경우 수정과 삭제가 가능합니다.
- 타인의 게시글에는 수정과 삭제 버튼이 활성화되지 않습니다.

#### 3. 게시글 추천, 스크랩, 조회수
- 타인또는 나의 게시글을 추천 또는 스크랩 할 수 있습니다.
- 스크랩한 게시글은 마이 페이지 - 나의 활동에서 확인 할 수 있습니다.
- 조회수가 많은 게시글은 메인 페이지에서 확인할 수 있습니다.

| 자유토론 게시글 |
|----------|
| <img src="src/main/resources/static/images/projectFunctionImage/자유 토론 페이지.png"> |

| 찬반토론 게시글 |
|----------|
| <img src="src/main/resources/static/images/projectFunctionImage/찬반 토론 페이지.png"> |
</br>

### [메인화면] 

#### 1. 트렌드 확인
- 인기 자유/찬반토론과 최신토론을 확일 할 수 있습니다.
- 주제와 관련된 슬라이드를 볼 수 있습니다.

#### 2. 게시글 이동
- 게시글을 클릭하면 해당 게시글의 상세 내용을 확인 할 수 있습니다.

| 메인화면 |
|----------|
| <img src="src/main/resources/static/images/projectFunctionImage/메인 페이지.png"> |
</br>

### [문의하기]

#### 1. 자주 묻는 질문
- 가장 많이 찾는 질문을 확인 할 수 있습니다.
- 찾고싶은 주제를 검색할 수 있습니다.

#### 2. 유저의 소리
- 다른 유저의 궁금한 점이나 건의사항을 확인 할 수 있습니다.
- 나의 건의사항을 작성할 수 있습니다.
- 질문유형, 제목, 내용을 작성하면 질문글이 등록됩니다. 
- 비속어 입력시나 질문유형, 제목, 내용을 작성하지 않을 시 작성되지 않습니다.
- 찾고싶은 주제를 검색할 수 있습니다.

| 문의하기 |
|----------|
| <img src="src/main/resources/static/images/projectFunctionImage/문의하기 페이지.png"> |
</br>

### [나의 활동]

#### 1. 활동 확인
- 나의 스크랩 게시글, 내가 작성한 자유 또는 찬반 게시글, 나의 질문글을 확인 할 수 있습니다.

| 나의 활동 |
|----------|
| <img src="src/main/resources/static/images/projectFunctionImage/나의 활동 페이지.png"> |
</br>

### [검색]

#### 1. 검색 기능
- navbar의 검색창을 통해서 검색할 수 있습니다.
- 검색어를 입력하면 그에 해당하는 자유/찬반 게시글들을 확인 할 수 있습니다.

#### 2. 게시글 이동
- 게시글을 클릭하면 해당 게시글의 상세 내용을 확인 할 수 있습니다.

| 검색 |
|----------|
| <img src="src/main/resources/static/images/projectFunctionImage/검색 페이지.png"> |
</br>

### [관리자]

#### 1. 관리자 페이지
- 관리자는 로그인시에 관리자 페이지로 이동할 수 있습니다.
- 관리자 페이지에서 회원, 자유/찬반 게시글, 질문글을 확인하고 삭제 할 수 있습니다.

| 관리자 |
|----------|
| <img src="src/main/resources/static/images/projectFunctionImage/관리자 페이지.png"> |
</br>

## 🔥 트러블 슈팅

### 🚨 이슈
문의하기 페이지와 관리자 페이지에서 보고자 하는 게시글의 버튼을 눌렀을때 기능오류가 발생, js로 태그를 가져와 클릭한 요소에 active 클래스를 추가하였지만 이상하게도 active 클래스가 아주 잠시 바뀌었다 초기상태로 돌아가 버튼의 기능이 제대로 작동하지 않았음  
<br>
<br>

## 🛑 원인
- event.preventDefault()로 가본 동작도 막아봤지만 버튼의 기능이 활성화되지는 않았고 html파일을 확인하던 중 모든 페이지에서 active 클래스가 같은 태그에 지정되어있는 사실을 발견
<br>
<br>

## 🚥 해결
- 이 후 active 클래스를 페이지 별로 다르게 주어 페이지 이동이 일어날때 해당 페이지와 연관 되어있는 태그의 클래스에 active 클래스가 잘 추가 되는 것을 확인

## 개선 목표

#### 카테고리
- 카테고리를 추가될수록 게시글 페이지가 지저분해지는 상황일 발생하기 때문에 드롭다운으로 좀 더 깔끔하게 만들어야 한다.
</br>

#### 비밀번호 찾기
- 등록된 아이디와 이메일을 작성해 해당 정보와 일치하는 유저를 찾으면 비밀번호 찾기가 가능한데 이 방식은 보안에 취약하기 때문에 등록된 이메일에 암호 코드를 주어 제한시간 안에 코드를 입력하면 비밀번호 재설정 페이지로 안내해주는 방식을 추가해야 한다. 
</br>

#### 실시간 대화 기능
- 토론 페이지이지만 아직 실시간으로 업데이트 되지는 않는다. 웹 소켓을 이용해서 클라이언트와 서버 간에 양방향 통신을 가능하게 만들어야 한다.
</br>

#### 반응형 웹페이지
- 아쉽게 반응형 웹페이지를 구현하지는 못했다 미디어 쿼리와 너비조정으로 레이아웃 조정이 필요하다.
</br>

#### 비속어 필터링
- 현재 아주 단순히 배열에 비속어 값을 넣고 사용자의 작성 내용에 비속어 값이 속해있으면 필터링 가능하게 구현해 놓았지만 이 방식은 관리하기 힘들고 비효율 적이므로 비속어 API나 더 섬세한 필터링 기능을 구현해야한다. 
</br>

## 프로젝트 후기

### ⭐ 김태우
한달정도의 시간이 주어져서 프로젝트를 진행하는데 여유있지는 않아도 무리는 없을거라 생각했는데
진행하면서 다양한 문제가 발생하여 마지막에는 시간에 쫓기는 상황도 있었고 팀원들과 더 많은 소통을 시도하며
협업을 했어야했지만 그러지 못해 아쉬웠습니다. 
</br>

### 🏃‍♂️ 김민섭
지난 한달을 생각해보면 진전이 없어 아쉽게 하루를 보낸날도 많았고 기능을 마무리하며 좋아했던 날도 많았던 것 같아 프로젝트가 끝난 지금도 시원섭섭한 마음이 남아 있는 것 같습니다. 
처음 협업 프로젝트를 진행하면서 어디서부터 어떻게 구현해나가야할지 막막했지만 주제를 정하고 역할 분담을 하며 각자 만든 부분을 합쳐 홈페이지의 일부분이 완성되어가는 모습을 보며 뿌듯함도 많이 느꼈던것 같습니다. 협업에 익숙해지기는 힘들었지만 요번 기회로 몰랐던 기능도 많이 알아가고 해결책도 생긴것 같아 의미있는 시간이 되었던것 같습니다.     
또 팀원분들이 열심히 해주셔서 프로젝트도 무사히 끝낼 수 있었던 것 같아 감사했습니다.
</br>

### 🐰 송현지
처음에 시작할 때는 만들어야 할 기능들이 많아서 걱정이 많이 되었습니다. 하지만 프로젝트를 진행하면서 점차 배우는 것들이 많아지고 그 과정에서 흥미를 느끼게 되어 즐겁게 마무리할 수 있었습니다. 좋은 팀원들과 함께 협력하며 다양한 문제를 해결해 나가는 과정이 큰 도움이 되었고, 많은 것을 배울 수 있는 기회가 되었으며 협업의 중요성도 깨달았습니다.
다만, 시간이 조금 더 있었더라면 기능들을 더 세부적으로 구현하고 완성도를 높일 수 있었을 텐데 하는 아쉬움이 남습니다. 그래도 최선을 다해 좋은 결과를 만들어낼 수 있어서 좋았습니다!



