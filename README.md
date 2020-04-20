# Book Search Application

## Run Application
```
$ cd [project_dir]
$ ./gradlew bootRun
or
Run Application (Intellij)
```
## Project Structure
```
── src 
    ├── main
    │   ├── java
    │   ├── resources
    │   └── webapp
    └── test
        └── java
```
## Library
```
spring-cloud-starter-netflix-hystrix
- Circuit Breaker
```
## Test
#### Sign Up
```
$ curl -X POST -d 'id=[userId]&password=[password]' 'http://localhost:8080/user/signup'
```
* response
```
{"code":"SUCCESS","data":true}
```
#### Sign In
```
$ curl -X POST -d 'id=[userId]&password=[password]' 'http://localhost:8080/user/signin'
```
* response
```
{"code":"SUCCESS","data":"[token]"}
```
#### Refresh Token
```
curl -X POST -H 'Authorization:Bearer [token]' 'http://localhost:8080/user/refresh'
```
* response
```
{"code":"SUCCESS","data":"[token]"}
```
#### Search Books
```
curl -X GET -H 'Authorization:Bearer [token]' 'http://localhost:8080/api/search/book?query=[searchText]&page=1&size=10'
```
* response
```
{
	"code": "SUCCESS",
	"data": {
		"books": [{
			"authors": ["조앤 K. 롤링"],
			"contents": "선과 악의 대립 속에서 평범한 어린 소년이 한 사람의 영웅으로 성장해나가는 보편적인 테마를 바탕으로 빈틈없는 소설적 구성과 생생하게 살아 있는 캐릭터, 정교하게 만들어낸 환상의 세계를 접목시킨 21세기의 고전 「해리포터 시리즈」의 제2편『해리포터와 비밀의 방』. 더즐리 이모부네 집에서 끔찍한 방학을 보내던 해리에게 도비라는 집요정이 나타나 학교로 돌아가지 말라고 경고한다. 그 말을 무시하고 호그와트로 돌아간 해리는 머글 출신 아이들을 대상으로 한 의문",
			"datetime": "2016-09-15T00:00:00.000+09:00",
			"isbn": "8983925566 9788983925565",
			"price": "20000",
			"publisher": "문학수첩",
			"salePrice": "18000",
			"status": "정상판매",
			"thumbnail": "https://search1.kakaocdn.net/thumb/R120x174.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flbook%2Fimage%2F1168779%3Ftimestamp%3D20200216125013",
			"title": "해리포터(Harry Potter): 비밀의 방(양장본 HardCover)",
			"translators": ["김혜원"],
			"url": "https://search.daum.net/search?w=bookpage&bookId=1168779&q=%ED%95%B4%EB%A6%AC%ED%8F%AC%ED%84%B0%28Harry+Potter%29%3A+%EB%B9%84%EB%B0%80%EC%9D%98+%EB%B0%A9%28%EC%96%91%EC%9E%A5%EB%B3%B8+HardCover%29"
		}],
		"total": 288,
		"page": 1,
		"searchSize": 10
	}
}
```
#### Top 10 Query
```
curl -X GET -H 'Authorization:Bearer [token]' 'http://localhost:8080/api/top10/query'
```
* response
```
{
	"code": "SUCCESS",
	"data": [{
		"keyword": "해리포터",
		"searchCount": 1,
		"regDate": "2020-04-17T22:47:54.825",
		"lastDate": "2020-04-17T22:47:54.825"
	}]
}
```
#### My Search Histories
```
curl -X GET -H 'Authorization:Bearer [token]' 'http://localhost:8080/api/my/history?page=1&size=10'
```
* response
```
{
	"code": "SUCCESS",
	"data": [{
		"id": 3,
		"userId": "aaa",
		"searchText": "해리포터",
		"regDate": "2020-04-17T22:47:54.849",
		"lastDate": "2020-04-17T22:47:54.849"
	}]
}
```
