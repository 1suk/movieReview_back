# React + Spring Boot로 영화 리뷰 시스템 구축하기

## 1. 프로젝트 개요

### 프로젝트 소개
영화 정보를 제공하고 사용자들이 리뷰를 작성할 수 있는 웹 애플리케이션을 개발했습니다. 사용자는 영화 목록을 조회하고, 상세 정보를 확인하며, 평점과 리뷰를 남길 수 있습니다.

### 기술 스택
- **Frontend**: React (Vite)
- **Backend**: Spring Boot 
- **ORM**: Spring Data JPA
- **Database**: H2
- **기타**: Lombok, Jakarta Persistence
  
## 2. 프로젝트 구조
```text
src/main/java/com/spring/project/
│
├── Controller/
│   ├── MovieController.java
│   ├── ReviewController.java
│   ├── UserController.java
│   └── dto/
│       ├── ReviewDTO.java
│       ├── request/
│       │   ├── AuthRequest.java
│       │   
│       └── response/
│           ├── AuthResponse.java
│           ├── MovieResponse.java
│          
├── Service/
│   ├── MovieService.java
│   ├── MovieServiceImpl.java
│   ├── ReviewService.java
│   ├── ReviewServiceImpl.java
│   ├── UserService.java
│   └── UserServiceImpl.java
│
├── Repository/
│   ├── MovieRepository.java
│   ├── ReviewRepository.java
│   ├── ReviewRepositoryImpl.java
│   └── UserRepository.java
│
├── Entity/
│   ├── Movie.java
│   ├── Review.java
│   └── User.java
│
└── Exception/
    ├── InvalidPasswordException.java
    ├── MovieNotFoundException.java
    └── UserNotFoundException.java
```
## 3. 시스템 아키텍처 및 데이터 흐름

### RESTful API 설계

| HTTP Method | Endpoint | 설명 |
|------------|----------|------|
| GET | `/api/movie` | 전체 영화 목록 조회 |
| GET | `/api/movie/{id}` | 특정 영화 상세 조회 |
| POST | `/api/user/signup` | 회원가입 |
| POST | `/api/user/login` | 로그인 |
| POST | `/api/review` | 리뷰 작성 |
| GET | `/api/review/movie/{movieNo}` | 특정 영화의 리뷰 목록 조회 |
| PUT | `/api/review/{reviewNo}` | 리뷰 수정 |
| DELETE | `/api/review/{reviewNo}` | 리뷰 삭제 |

### 데이터 모델링
**User (회원)**
- 회원가입
- 한 명의 사용자가 여러 리뷰 작성 가능 (1:N)

**Movie (영화)**
- 영화 기본 정보 (제목, 장르, 감독, 출연진 등)
- 하나의 영화에 여러 리뷰 존재 가능 (1:N)

**Review (리뷰)**
- 한 사용자는 한 영화에 하나의 리뷰만 작성 가능 
- 평점(rating)과 내용(content) 포함

```
User (1) ----< (N) Review (N) >---- (1) Movie
```
## 4. 주요 API 응답 

#### 특정 영화 상세 조회 (`GET /api/movie/{id}`)
```json
{
    "movieNo": 3,
    "title": "Interstellar",
    "titleKo": "인터스텔라",
    "year": 2014,
    "genre": "SF,드라마,어드벤처",
    "rating": 4.6,
    "posterUrl": "https://...",
    "director": "크리스토퍼 놀란",
    "cast": "매튜 매코너헤이,앤 해서웨이,제시카 차스테인",
    "synopsis": "세계 각국의 정부와 경제가 완전히 붕괴된 미래가 다가온다..."
}
```
#### 사용자가 작성한 리뷰 목록 (`GET /api/review/user/{userId}`)
```json
[
    {
        "reviewNo": 1,
        "userId": 1,
        "userName": "이동진",
        "rating": 5.0,
        "content": "역시 봉준호.. 각본, 연출 미쳤습니다",
        "createdAt": "2025-12-21",
        "updatedAt": "2025-12-21",
        "movieNo": 5,
        "movieTitleKo": "기생충",
        "director": "봉준호",
        "posterUrl": "https://..."
    },
    {
        "reviewNo": 2,
        "userId": 1,
        "userName": "이동진",
        "rating": 4.0,
        "content": "너무 기대를 많이 했나요.. 아쉽습니다",
        "createdAt": "2025-12-21",
        "movieNo": 1,
        "movieTitleKo": "어쩔수가없다"
        "director": "박찬욱",
        "posterUrl": "https://..."
    }
]
```
#### 특정 영화의 리뷰 목록 조회 (`GET /api/review/movie/{movieNo}`)
```json
[
    {
        "reviewNo": 3,
        "userId": 1,
        "movieNo": 4,
        "userName": "이동진",
        "movieTitle": "괴물",
        "rating": 5.0,
        "content": "영화 진짜 재밌어요..각본, 연출 미쳤습니다",
        "createdAt": "2025-12-21",
        "updatedAt": null
    },
    {
        "reviewNo": 4,
        "userId": 3,
        "movieNo": 4,
        "userName": "장원석",
        "movieTitle": "괴물",
        "rating": 5.0,
        "content": "저는 이거 3번 봤어요..",
        "createdAt": "2025-12-21",
        "updatedAt": "2025-12-21"
    }
]
```
## 5. 소감
이번 프로젝트를 통해 React와 Spring Boot를 연동한 풀스택 애플리케이션 개발 경험을 쌓을 수 있었습니다. 특히 JPA를 사용하면서 객체 지향적으로 데이터베이스를 다루는 방법을 배웠고, DTO 패턴을 통한 계층 분리의 중요성도 체감할 수 있었습니다.

하지만 현재 로그인 기능은 최소한의 기능만 구현되어 있어 비밀번호가 평문으로 저장되고, 인증 상태 유지도 되지 않습니다. JWT 토큰 기반 인증 시스템을 구축하여 보안을 강화하고 싶습니다.

또한 영화 검색 및 필터링 기능, 페이징 처리, 사용자 리뷰 평점 평균 계산 등 기본적인 기능들이 많이 부족합니다. 추후 보완하여 완성도를 높이고 싶습니다.
