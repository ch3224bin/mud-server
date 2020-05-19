# Websocket을 사용한 Mud 게임 서버

현재는 서버에서 handlebars를 사용하여 view를 처리하는 부분이 있으나,  
클라이언트를 만들면 온전히 json만 송신할 예정.

## 실행방법

아래는 test profile로 H2서버를 사용하여 실행한다

```bash
gradlew clean bootRun -Pprofile=test
```

1. http://localhost:8080 접속
2. user : user, password : 1234

profile local로 실행시 mysql 설치 필요.

[schema-mysql.sql](/src/main/resources/schema-mysql.sql)로 스키마 생성  

```
port : 3306
user : user1
password : 1234
database : mud
```

## 게임 도움말

### 정보

#### 봐

#### 상태

### 이동

### 전투

### 스킬

## 사용 기술

- Websocket
- JPA
- Security