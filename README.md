# blog-microservices-v1 [![Quality Gate Status](http://ec2-52-221-207-86.ap-southeast-1.compute.amazonaws.com:9000/api/project_badges/measure?project=Blog-Microservices-Ehancement&metric=alert_status)](http://ec2-52-221-207-86.ap-southeast-1.compute.amazonaws.com:9000/dashboard?id=Blog-Microservices-Ehancement)
This microservices project used Swagger-ui to manage API list. 
Json web token used to handle authentication process in gateway application and will also be used in all microservices using 
secret key sharing between gateway application and its microservices. 

## Architecture

<img width="446" alt="Architecture" src="https://user-images.githubusercontent.com/18225438/64321242-62e54400-cfea-11e9-9673-e75681ee877a.PNG">

## The Stacks:
1. Springboot 2.1.6
2. MySQL
3. Swagger-ui
4. MockMVC for Integration testing
5. Cucumber & JUnit for High Level's Like Integration Testing
6. Cucumber for Frontend High Level Testing (Frontend: https://github.com/syarbeats/blog-frontend-application.git)
7. Chrome Webdriver


### Postman Screenshot Example (Get All Posts for certain category)

Gateway: /services/blog

Microservices: /api/posts/category

<img width="740" alt="GetBlog" src="https://user-images.githubusercontent.com/18225438/64406820-fa12d000-d0ac-11e9-940e-4f5c8ad52d7a.PNG">

### Swagger-ui Screenshoot

<img width="355" alt="swager blog" src="https://user-images.githubusercontent.com/18225438/64321511-ddae5f00-cfea-11e9-8dd6-0aaf178c981a.PNG">



