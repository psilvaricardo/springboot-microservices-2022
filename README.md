# Microservices in Springboot Workshop 2022

By the time of writing (last quarter of 2022), this is intended to be an updated version of the Microservices in Springboot Workshop offered online by JavaBrains.


## SpringBoot Microservices Level 1: Communication and Discovery

- Hands-on workshop, this is a **sample application** that consist of 4 microservices that work together, they are interdependent.
- We will be creating a few microservices from scratch and start with small parts of functionality to make them work well together.
- We will break out the concept of monolithic applications and introduce the microservice concepts.
- Having them communicate them with each other using service discovery with Eureka, Spring Cloud and the discovery pattern. Using libraries and frameworks to solve common problems.
- As a general rule, when creating a microservices as dependencies, we must use only what's required, the bare minimum.
- For this workshop, each microservice will be a SpringBoot application.


## SpringBoot Microservices Level 2: Fault Tolerance and Resilience

- The very first change as part of level 2, we will start using themoviedb.org API to get movie information, this is live movie information, this is an external existing API, this is a proof of concept on how points of failure can be introduced to the system by making an external call. The more external interactions you have, the more failures you may have.
- **Scenario 1: Service goes down**
  - Solution: You don't want your application go down if one microservice goes down. Run multiple instances of the same microservice. As a workaround, you can run multiple instances on a different machines (or same machine on different port --not recommended, only for dev environment setup). When you create multiple instances of the same service with Eureka, it does round-robin as part of discovery service to handle equal distribution of the load.
- **Scenario 2: Service is slow:** 
  - This is a bigger problem, we can try to add Timeouts and Threads (multi-threading) support can be handy to fix this issue.
  - Solution (in fault tolerance, this is the circuit breaker pattern):
    - Its basic function is to interrupt current flow after a fault is detected:
    - 1. The microservice which is calling/orchestrating the other mss should detect when something is wrong.
    - 2. Take temporary steps to avoid the situation getting worse, like take a break and stop calling it.
    - 3. Deactivate the "problem" component so that it does not affect downstream components.
  - When the Circuit breaker be turned ON ?
    - Last N requests to consider for the decision
    - How many of those should fail?
    - Timeout duration
  - When the Circuit breaker be turned OFF ?
    - How long after the circuit trip to try again?


## SpringBoot Microservices Level 3: Microservice configuration

- To be updated...


## Movie Catalog API application

- For the initial version of this application we will assume we have a requirement from a JS developer which is building a UI, and they need an API from you.
- There will be a website + Id (something like mymovie.catalog.com/john) and is going to pull up all the movies that this person has watched and rated.
- The website shall display a list-view with the movie name, description and the rating, so you need to create an API for it.
  - **Movie Catalog Service**: This is responsible for providing the payload, given a UserId, it will return a list of movies with details. It will call the two services described below to retrieve that information.
  - **Movie Info Service**: This is responsible for providing **movie information**. It takes a MovieId and returns some information about that movie.
  - **Movie Data Service**: This is responsible for storing a **particular rating** a user has given for a particular movie. Given a UserId, it will return a list of MovieIDs and Ratings.


## Information reference
- **OUTDATED (2019)** https://github.com/koushikkothagal/spring-boot-microservices-workshop
- Communication and Discovery: https://www.youtube.com/playlist?list=PLqq-6Pq4lTTZSKAFG6aCDVDP86Qx4lNas
- Fault Tolerance and Resilience: https://www.youtube.com/playlist?list=PLqq-6Pq4lTTbXZY_elyGv7IkKrfkSrX5e
- Microservice configuration: https://www.youtube.com/playlist?list=PLqq-6Pq4lTTaoaVoQVfRJPqvNTCjcTvJB
- An introduction to resilience4j https://github.com/greenlearner01/resilience4j
  - Below are the details about circuit breaker, bulkhead, retry and rate limiter:
  - Circuit Breaker Part 1 of 3 - https://youtu.be/Q1KlqAD8-6s
  - Circuit Breaker Part 2 of 3 - https://youtu.be/rZWV23nzGdk
  - Circuit Breaker Part 3 of 3 - https://youtu.be/VPvmP64VlMo
  - Bulkhead https://youtu.be/ib_cL26zOB8
  - Retry https://youtu.be/X7X2FXqPRaI
  - Ratelimiter https://youtu.be/GvcKYTZvrMM
  - Application Monitoring:
  - Part 1 of 2 - https://youtu.be/hOhHmnE9uXs
  - Part 2 of 2 - https://youtu.be/eVIeYE5lYMs

