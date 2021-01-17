# Rate Limited Service

## Setup and Build
This project is built with IntelliJ IDEA 2020.1.1, Maven 3.6.3, Spring Boot 1.5.9.RELEASE, JDK 1.8.
- JDK 1.8
- Spring Boot 1.5.9.RELEASE
- Maven 3.6.3

It can be built by mvn or an IDE like IDEA
- Build with Maven
```
cd <ProjectFolder>
mvn package
```
- Build with IntelliJ IDEA, import project, run Maven Build with package lifecycle. Note that lombok used, please install and enable lombok plugin in Settings\Plugins.

## Configuration
- In order to provide flexibility, Several config items are created in application.properties. We will see some config usage in Test section.
```
# The period of rate limiter, in milliseconds unit
service.limiter.period=60000
# Max count of api requests in a single period
service.limiter.count=100
```
- There is also a RESTful hot config for `limiter.count` described below. Hot config for `limiter.period` is not supported in current version.

RESTful url:
```
http://host:port/config/limiter/count
```

## Test
- Test with postman

The application provides a RESTful api /v1/orders/, you can use postman or any tools else to post Order with json format to this api. For example this is the api url from localhost
```
http://host:port/v1/apis/test
```

- Run unit test case `RateLimitedServiceApplicationTests.mockRequest` in IDEA , with JUnit (Recommend). This test case issue 10 requests concurrently, which works with config `limiter.count` less than 10, such as 5.

## Log
Below table shows the main log of rate limit function

|Log|Level|Module|Description|
|:---|:---|:---|:---|
|Intercept request: &lt;uri&gt;|Info|ApiInterceptor.preHandle|The request &lt;uri&gt; has been intercepted|
|Continue request: &lt;uri&gt;|Info|ApiInterceptor.preHandle|There are free api request quota, the request &lt;uri&gt; continue to process normally|
|Receive request: &lt;uri&gt;|Info|&lt;api controllers&gt;|The request &lt;uri&gt; has been received by normal api controllers|
|Discard request: &lt;uri&gt;|Warn|DiscardLimitHandler.handle|Api request quota is used up, `DiscardLimitHandler` decides to discard the request &lt;uri&gt;|
|Abort request: &lt;uri&gt;|Warn|ApiInterceptor.preHandle|Api request quota is used up, `ApiInterceptor` aborts the request &lt;uri&gt; as `LimitHandler.handle` returned false |

## Document
Api docs are provided in the directory **/doc/**, main entry is **/doc/index.html**.