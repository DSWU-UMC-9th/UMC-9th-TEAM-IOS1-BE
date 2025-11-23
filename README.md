<div align="center">

![fog](https://capsule-render.vercel.app/api?type=blur&color=auto&text=fog&height=auto&fontSize=auto&fontAlign=50&fontColor=auto)
  
<p align="center">
  <b>향수 리뷰 & 추천 서비스</b>
</p>

<br><br>

</div>
<br>

## 🙋🏻‍♀️ BE Developer를 소개합니다!

**덕성여자대학교 UMC 9th iOS 1팀**

| <a href="https://github.com/HeejuKo"><img src="https://avatars.githubusercontent.com/u/142784710?v=4" width="120px" alt="고희주"/></a> | <a href="https://github.com/gcongK"><img src="https://avatars.githubusercontent.com/u/181479630?v=4" width="120px" alt="김가빈"/></a> | <a href="https://github.com/egauni"><img src="https://avatars.githubusercontent.com/u/163407321?v=4" width="120px" alt="이가은"/></a> | <a href="https://github.com/Suhyeon7"><img src="https://avatars.githubusercontent.com/u/157273486?v=4" width="120px" alt="장수현"/></a> | <a href="https://github.com/Suhyeon7"><img src="https://avatars.githubusercontent.com/u/210017222?v=4" width="120px" alt="장지인"/></a> |
| :---: | :---: | :---: | :---: | :---: |
| **텐마/고희주** | **푸짐바오/김가빈** | **우니/이가은** | **해피/장수현** | **짱/장지인** |


<br>


## 🛠 기술 스택
| **역할**     | **종류**                                                                                                                                                                                                                 | **선정 이유**                     |
| ------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | -------------------------------- |
| Framework    | ![Spring Boot](https://img.shields.io/badge/SpringBoot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)                                                                                                       | 생산성 높은 Java 기반 웹 프레임워크 |
| Language     | ![Java17](https://img.shields.io/badge/Java%2017-007396?style=for-the-badge&logo=openjdk&logoColor=white)                                                                                                                | 안정적이고 대규모 프로젝트에 적합   |
| ORM          | ![JPA](https://img.shields.io/badge/JPA%20(Hibernate)-59666C?style=for-the-badge&logo=hibernate&logoColor=white)                                                                                                        | 객체지향적인 DB 접근                |
| DB           | ![MySQL](https://img.shields.io/badge/MySQL%208-4479A1?style=for-the-badge&logo=mysql&logoColor=white)                                                                                                                   | 안정적이고 널리 사용되는 RDBMS      |
| Deployment   | ![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white) ![AWS EC2](https://img.shields.io/badge/AWS%20EC2-FF9900?style=for-the-badge&logo=amazonec2&logoColor=white)        | 컨테이너 기반 배포, 클라우드 확장성  |

<br>

## ✅ Build & Run

- **port 번호**: `8080`
- **빌드/실행 명령어 예시**
  
🔧 **Gradle**
```
./gradlew clean build    # 프로젝트 빌드
./gradlew bootRun        # 로컬 실행
```

🐳 **Docker 기반 실행**
```
docker compose up --build -d  # 빌드 + 백그라운드 실행
docker compose down           # 컨테이너 종료
```

<br>

## 🔗 Git Convention

### 💫 Git Flow

```
main ← feat
```

- main : 배포 및 전체 개발 브랜치 (feat이 merge되는 브랜치) -> 1차 배포 브랜치
- develop : 1차 배포 이후, 2차 배포를 위한 작업이 merge되는 브랜치 -> 2차 배포 브랜치
- feat : 페이지/기능 별 브랜치
- refactor : 리펙토링/수정 별 브랜치

### 🔥 Commit Message Convention

- **커밋 유형**

  - ✨ Feat: 새로운 기능 추가
  - 🐛 Fix : 버그 수정
  - 🔧 Chore : 빌드 설정, 의존성 업데이트 등 작업
  - ✏️ Typing Error : 오타 수정
  - 🚚 Mod : 폴더 구조 이동 및 파일 이름 수정
  - 💡 Add : 파일 추가 (ex- 이미지 추가)
  - 🔥 Del : 파일 삭제
  - ♻️ Refactor : 코드 리펙토링
  - 🎉 Init: 프로젝트 세팅

- **형식**: `커밋유형: 상세설명`
- **예시**:
  - 🎉 Init: 프로젝트 초기 세팅
  - ✨ Feat: 로그인 기능 구현

### 🌿 Branch Convention

**Branch Naming 규칙**

- **브랜치 종류**
  - `Feat`: 새로운 기능 추가
  - `Fix` : 버그 수정
  - `Refactor` : 코드 리펙토링
  - `Init`: 프로젝트 세팅
- **형식**: `브랜치종류/상세기능`
- **예시**:
  - Init/project-setting
  - fix/login

### 📋 Issue Convention

**Issue Title 규칙**

- **태그 목록**:
  - `Feat`: 새로운 기능 추가
  - `Fix` : 버그 수정
  - `Refactor` : 코드 리펙토링
  - `Init`: 프로젝트 세팅
- **예시**:
  - [Feat] 로그인 기능 구현
  - [Init] 프로젝트 초기 세팅

### Issue Template

- **제목**: [Feat] 간단한 요약
- **내용**:

```
## 📄 About

<!-- 해당 이슈에서 작업할 내용을 작성해주세요. -->

## ✅ To Do

<!-- 해당 이슈와 관련된 할 일을 작성해주세요. -->
<!-- 할 일을 완료했다면 체크 표시로 기록해주세요. -->

- [ ] todo
- [ ] todo

```

## 🔄 Pull Request (PR) Convention

**PR Title 규칙**

- **형식**: `태그: 제목`
- **태그 목록**:
  - `Feat`: 새로운 기능 추가
  - `Fix` : 버그 수정
  - `Refactor` : 코드 리펙토링
  - `Init`: 프로젝트 세팅
- **예시**:
  - Feat: 로그인 기능 구현
  - Fix: 로그인 버그 수정

### PR Template

- **PR 작성 규칙**:

```
<!-- PR 제목은 '[Feat] 작업 내용' 과 같은 형태로 작성해주세요.  -->

### 📑 이슈 번호

<!-- 이슈 번호를 작성해주세요. 해당 PR이 Merge되면 자동으로 이슈가 close됩니다. ex) #1 -->

- close #

<br>

### ✨️ 작업 내용

<!-- 작업 내용을 간략히 설명해주세요 -->

<br>

### 💭 코멘트

<!-- 코드 리뷰가 필요한 부분이나 궁금한 점을 자유롭게 남겨주세요! -->

```

## 📂 프로젝트 구조
```
📦 fog-app
├── 📁 src.main.java
│   └── com.example.fog
│       ├── 📁 code             # 공통 응답 코드
│       ├── 📁 config           # Swagger, Security 등 환경 설정
│       ├── 📁 controller       # REST API 컨트롤러
│       ├── 📁 dto              # 요청·응답 DTO
│       ├── 📁 entity           # JPA 엔티티
│       ├── 📁 exception        # 커스텀 예외 및 예외 핸들러
│       ├── 📁 jwt              # JWT 토큰 발급/검증 관련 로직
│       ├── 📁 repository       # JPA Repository 인터페이스
│       ├── 📁 service          # 핵심 비즈니스 로직
│       └── 📄 FogApplication   # 메인 클래스
├── 📁 src.main.resources
│   ├── 📄 application.yml
│   ├── 📄 application-prod.yml
│   └── 📁 static.images        # 정적 이미지
├── 📄 build.gradle
├── 📄 Dockerfile
├── 📄 .gitignore
└── 📄 README.md
```

 ## 📄 API Docs
	•	Swagger UI : https://www.tenma.store/swagger-ui/index.html
  
  <!-- prettier-ignore-end -->
