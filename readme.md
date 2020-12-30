# Development projects

Group Rest Api project realised with two colleagues from Java Web Developer Course.

    Karol Uracz:
    https://github.com/KarolUracz
    Łukasz Jędrzejewski:
    https://github.com/Lukasz-Jedrzejewski
Main goal of this project is to create Back-end for developer's social network which may simplify
finding colleagues and work together on future non-commerial projects.
Current functionality include provide basic Jwt Authentication, User and Project Entity CRUD with proper Database relations and basic 
administrative functions.


Setup & Installation

    Create new PostgreSQL database and configure application.properties file accordingly.

    Database connection section 

    spring.jpa.hibernate.ddl-auto=
    spring.datasource.url=
    spring.datasource.username=
    spring.datasource.password=
    server.port=

    User Profile-photo section

    spring.servlet.multipart.enabled=true
    spring.servlet.multipart.file-size-threshold=2KB
    spring.servlet.multipart.max-file-size=50MB
    spring.servlet.multipart.max-request-size=50MB
    images.path=

    Admin account credentials:
    
    sa.pw.setup=
    sa.email.setup=

    JWT section:

    jwt.token.secret=
    jwt.token.expired=

    Email confirmation section:

    spring.mail.host=
    spring.mail.port=
    spring.mail.username=
    spring.mail.password=

Technologies used:

    Java 8
    Spring Boot 
    Spring Security
    Spring Validation
    JavaMailSender
    Json Web Token
    PostgreSQL database
    Hibernate
    Lombok
    Swagger-ui

