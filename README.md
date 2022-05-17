# spring-boot-mongodb-demo
The demonstration of simple rest-api using spring boot and embedded mongo-db.
This rest-api does CRUD operation in embedded mongodb and returns a response with appropriate http status code.

### Prerequisite
Should have Java 1.8 or higher.

### Run the app
Navigate to the pom.xml directory and run ```./mvnw spring-boot:run``` command.

## REST API
Note that there are 2 users.
1. A user with `user` username and `user` password who can only get the list of all books or a book by id.
2. An admin user with `admin` username and `admin` password who is authorized to perform all operations like get/add/update and delete.

An authorized access will end up in the response with the status code 401 (Unauthorized).
E.g

``` sh
* Mark bundle as not supporting multiuse
< HTTP/1.1 401 
* Authentication problem. Ignoring this.
< WWW-Authenticate: Basic realm="Realm"
< X-Content-Type-Options: nosniff
< X-XSS-Protection: 1; mode=block
< Cache-Control: no-cache, no-store, max-age=0, must-revalidate
< Pragma: no-cache
< Expires: 0
< X-Frame-Options: DENY
< Content-Type: application/json
< Transfer-Encoding: chunked
< Date: Tue, 17 May 2022 15:44:53 GMT
```

The REST API documentation to describe as below:

### Get list of Books
`GET /book/`

``` sh
curl --user 'user:user' --verbose localhost:8080/book
```
#### Response
```
*   Trying 127.0.0.1:8080...
* Connected to localhost (127.0.0.1) port 8080 (#0)
* Server auth using Basic with user 'user'
> GET /book HTTP/1.1
> Host: localhost:8080
> Authorization: Basic dXNlcjp1c2Vy
> User-Agent: curl/7.79.1
> Accept: */*
> 
* Mark bundle as not supporting multiuse
< HTTP/1.1 200 
< Set-Cookie: JSESSIONID=5641B535052E119F60B78B6E46FF6E09; Path=/; HttpOnly
< X-Content-Type-Options: nosniff
< X-XSS-Protection: 1; mode=block
< Cache-Control: no-cache, no-store, max-age=0, must-revalidate
< Pragma: no-cache
< Expires: 0
< X-Frame-Options: DENY
< Content-Type: application/json
< Transfer-Encoding: chunked
< Date: Tue, 17 May 2022 15:21:27 GMT
< 
* Connection #0 to host localhost left intact
[{"id":"6283bacd4f486129eef56baf","name":"Book1"},{"id":"6283bacd4f486129eef56bb0","name":"Book2"}]%  
```

### Get a book by ID
`GET /book/id`

``` sh
curl --user 'user:user' --verbose localhost:8080/book/6283bacd4f486129eef56baf
```

#### Response
```
*   Trying 127.0.0.1:8080...
* Connected to localhost (127.0.0.1) port 8080 (#0)
* Server auth using Basic with user 'user'
> GET /book/6283bacd4f486129eef56baf HTTP/1.1
> Host: localhost:8080
> Authorization: Basic dXNlcjp1c2Vy
> User-Agent: curl/7.79.1
> Accept: */*
> 
* Mark bundle as not supporting multiuse
< HTTP/1.1 200 
< Set-Cookie: JSESSIONID=E98A6341FCEFC635CAE18FE5F14329CC; Path=/; HttpOnly
< X-Content-Type-Options: nosniff
< X-XSS-Protection: 1; mode=block
< Cache-Control: no-cache, no-store, max-age=0, must-revalidate
< Pragma: no-cache
< Expires: 0
< X-Frame-Options: DENY
< Content-Type: application/json
< Transfer-Encoding: chunked
< Date: Tue, 17 May 2022 15:24:54 GMT
< 
* Connection #0 to host localhost left intact
{"id":"6283bacd4f486129eef56baf","name":"Book1"}%  
```

### Get a book by ID that does not exist
`GET /book/id`

``` sh
curl --user 'user:user' --verbose localhost:8080/book/111
```
#### Response
```
*   Trying 127.0.0.1:8080...
* Connected to localhost (127.0.0.1) port 8080 (#0)
* Server auth using Basic with user 'user'
> GET /book/111 HTTP/1.1
> Host: localhost:8080
> Authorization: Basic dXNlcjp1c2Vy
> User-Agent: curl/7.79.1
> Accept: */*
> 
* Mark bundle as not supporting multiuse
< HTTP/1.1 404 
< Set-Cookie: JSESSIONID=F0530C88C47A171703877CBB1AAD6C99; Path=/; HttpOnly
< X-Content-Type-Options: nosniff
< X-XSS-Protection: 1; mode=block
< Cache-Control: no-cache, no-store, max-age=0, must-revalidate
< Pragma: no-cache
< Expires: 0
< X-Frame-Options: DENY
< Content-Length: 0
< Date: Tue, 17 May 2022 15:28:19 GMT
```

### Create a book
`POST /book`

``` sh
curl --verbose POST 'localhost:8080/book' \
--user 'admin:admin' \
--header 'Content-Type: application/json' \
--data-raw '{ "name": "book5"}'
```
#### Response
```
*   Trying 127.0.0.1:8080...
* Connected to localhost (127.0.0.1) port 8080 (#1)
> POST /book HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.79.1
> Accept: */*
> Authorization: Basic YWRtaW46YWRtaW4=
> Content-Type: application/json
> Content-Length: 18
> 
* Mark bundle as not supporting multiuse
< HTTP/1.1 201 
< Set-Cookie: JSESSIONID=30E9779DF9BE197BB2B2E662A33636D2; Path=/; HttpOnly
< X-Content-Type-Options: nosniff
< X-XSS-Protection: 1; mode=block
< Cache-Control: no-cache, no-store, max-age=0, must-revalidate
< Pragma: no-cache
< Expires: 0
< X-Frame-Options: DENY
< Content-Type: application/json
< Transfer-Encoding: chunked
< Date: Tue, 17 May 2022 15:34:38 GMT
< 
* Connection #1 to host localhost left intact
{"id":"6283c08e4f486129eef56bb4","name":"book5"}% 
```

### Update a book
`PUT /book/id`

``` sh
curl --verbose --request PUT 'localhost:8080/book/6283c08e4f486129eef56bb4' \
--user 'admin:admin' \
--header 'Content-Type: application/json' \
--data-raw '{ "name": "book5_updated"}'
```
#### Response
```
*   Trying 127.0.0.1:8080...
* Connected to localhost (127.0.0.1) port 8080 (#0)
* Server auth using Basic with user 'admin'
> PUT /book/6283c08e4f486129eef56bb4 HTTP/1.1
> Host: localhost:8080
> Authorization: Basic YWRtaW46YWRtaW4=
> User-Agent: curl/7.79.1
> Accept: */*
> Content-Type: application/json
> Content-Length: 26
> 
* Mark bundle as not supporting multiuse
< HTTP/1.1 200 
< Set-Cookie: JSESSIONID=2FD2C68D7B24BF406BA7D1506E3135E1; Path=/; HttpOnly
< X-Content-Type-Options: nosniff
< X-XSS-Protection: 1; mode=block
< Cache-Control: no-cache, no-store, max-age=0, must-revalidate
< Pragma: no-cache
< Expires: 0
< X-Frame-Options: DENY
< Content-Type: application/json
< Transfer-Encoding: chunked
< Date: Tue, 17 May 2022 15:40:05 GMT
< 
* Connection #0 to host localhost left intact
{"id":"6283c08e4f486129eef56bb4","name":"book5_updated"}%
```

### Delete a book
`DELETE /book/id`

``` sh
curl --verbose --request DELETE 'localhost:8080/book/6283c08e4f486129eef56bb4' --user 'admin:admin'
```
#### Response
```
*   Trying 127.0.0.1:8080...
* Connected to localhost (127.0.0.1) port 8080 (#0)
* Server auth using Basic with user 'admin'
> DELETE /book/6283c08e4f486129eef56bb4 HTTP/1.1
> Host: localhost:8080
> Authorization: Basic YWRtaW46YWRtaW4=
> User-Agent: curl/7.79.1
> Accept: */*
> 
* Mark bundle as not supporting multiuse
< HTTP/1.1 204 
< Set-Cookie: JSESSIONID=2E63F66D6C2D349183D956BA4A3CD521; Path=/; HttpOnly
< X-Content-Type-Options: nosniff
< X-XSS-Protection: 1; mode=block
< Cache-Control: no-cache, no-store, max-age=0, must-revalidate
< Pragma: no-cache
< Expires: 0
< X-Frame-Options: DENY
< Date: Tue, 17 May 2022 15:42:01 GMT
```