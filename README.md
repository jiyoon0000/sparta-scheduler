# sparta-scheduler
## 일정관리 앱 만들기

## LV1.일정 생성 및 조회

### 1. 일정 생성(일정 작성하기)
#### -일정 생성 시 포함되어야할 데이터 : 할일, 작성자명, 비밀번호, 작성일/수정일(날짜와 시간을 모두 포함한 형태, 최초 입력시 수정일=작성일)
#### -각 일정의 고유 식별자(ID)를 자동으로 생성하여 관리

### 2. 전체일정 조회
#### -수정일(YYYY-MM-DD) 또는 작성자명을 조건으로 등록된 일정 전체 조회
#### -수정일 기준 내림차순으로 정렬하여 조회

### 3. 선택 일정 조회
#### -일정의 고유 식별자를 사용하여 조회


## LV2.일정 수정 및 삭제

### 1. 선택한 일정 수정
#### -선택한 일정 내용 중 할일, 작성자명 만 수정 가능
##### -서버에 수정을 요청할 때 비밀번호를 함께 전달 / 작성일은 변경할 수 없고, 수정일은 수정 완료시 수정한 시점으로 변경

### 2. 선택한 일정 삭제
#### -선택한 일정을 삭제할 수 있다. / 서버에 일정 수정을 요청할 때 비밀번호를 함께 전달한다.

----

## API 명세서 작성
https://documenter.getpostman.com/view/39376424/2sAY4vhi4F

## ERD 작성
<img width="325" alt="스크린샷 2024-11-04 오전 11 08 17" src="https://github.com/user-attachments/assets/0f926cd4-89c2-4f56-840e-8d43e7d425ee">



## SQL 작성
```
//Schedule 테이블 생성
CREATE TABLE `Schedule` (
  `id` int NOT NULL,
  `name` varchar(20) NOT NULL,
  `password` int NOT NULL,
  `title` varchar(45) NOT NULL,
  `content` varchar(200),
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
);
```
