# sparta-scheduler

## LV1.

## LV2.

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
