# sparta-scheduler

## API 명세서 작성
https://documenter.getpostman.com/view/39376424/2sAY4vhi4F

## ERD 작성
<img width="325" alt="스크린샷 2024-11-04 오전 11 08 17" src="https://github.com/user-attachments/assets/b2b8debe-321b-4566-b13f-7cba26f2ca91">


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
