# sample-zuul-spring-swagger

This repository is for demonstrate the capability swagger on zuul + spring boot without eureka service.

But even with eureka service it will still work all the same, as shown in this tutorial [Microservices API Documentation with Swagger2](https://piotrminkowski.wordpress.com/2017/04/14/microservices-api-documentation-with-swagger2/).

## Here is how to
1. Adding swagger dependency to micro service's `pom.xml` as you do.

```xml
<dependency>
  <groupId>io.springfox</groupId>
  <artifactId>springfox-swagger2</artifactId>
  <version>2.7.0</version>
</dependency>
<dependency>
  <groupId>io.springfox</groupId>
  <artifactId>springfox-swagger-ui</artifactId>
  <version>2.7.0</version>
</dependency>
```

2. Adding swagger configuration to each micro service

```java
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Autowired
    private BuildProperties buildProperties;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.torishere.goodbyeservice"))
                .paths(PathSelectors.any())
                .build().apiInfo(new ApiInfo("Goodbye Service Api Documentation", "Automatically generated swagger document", buildProperties.getVersion(), "TOS", new Contact("Kittikorn Ariyasuk", "torishere.github.io", "kittikorn.a@gmail.com"), "", "", Collections.emptyList()));
    }
}
```

3. Now back in zuul project; we add configuration for swagger to gather `swaggerResource` from it's microservice.

```java
@Component
@Primary
@EnableAutoConfiguration
public class DocumentationController implements SwaggerResourcesProvider {

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        // resources.add(swaggerResource("service name", "service path", "swagger version 1.0/2.0"));
        resources.add(swaggerResource("goodbye-service", "/goodbye/v2/api-docs", "2.0"));
        resources.add(swaggerResource("hello-service", "/hello/v2/api-docs", "2.0"));
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }
}
```

4. Lastly, we put route path in zuul configuration as usual.
```yml
zuul:
  routes:
    hello:
      path: /hello/**
      url: http://localhost:7788/
    goodbye:
      path: /goodbye/**
      url: http://localhost:7799/
```
