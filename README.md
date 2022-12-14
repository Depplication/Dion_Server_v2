# Depplication_Server-2-

### 사용 언어 및 프레임워크
- Java
- Spring Boot
- JPA
- MySQL

### 디렉토리 구조
- 도메인형 
- global : 전체
- advertising : 광고
- admin : 관리자
- point : 포인트
- owner : 업주
- user : 사용자
- report : 문의

### application.yml
```
spring:
  datasource:
    url: jdbc:mysql://{DB_HOST}:{DB_PORT}/{DB_DATABASE}?useSSL=false&characterEncoding=UTF-8&serverTimezone=UTC
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: {DB_USERNAME}
    password: {DB_PASSWORD}

  jpa:
    show-sql: false
    generate-ddl: true
    hibernate:
      ddl-auto: update

  mvc:
    pathmatch:
      matching-strategy: ant_path_matcher

server:
  port: {SERVER_PORT}
  error:
    whitelabel:
      enabled: false

jwt:
  secret-key: {JWT_SECRET_KEY}
  access-exp: {JWT_ACCESS_EXP}
  refresh-exp: {JWT_REFRESH_EXP}
```
