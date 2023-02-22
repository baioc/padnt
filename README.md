# Padn't

A [Dontpad](https://dontpad.com/) clone implemented with Java Spring Boot.


## Starting the server

```sh
[dev]$ mvn spring-boot:run
```

```sh
[prod]$ java -jar target/dontpad-VERSION.jar
```

### Environment variables

variable | default
---|---
`SERVER_PORT` | `8080` |
`LOGGING_LEVEL_ROOT` | `INFO` |


## TODO list

1. Change default `/error` page
2. Add `modified` (and `created`?) timestamp columns
3. Deploy to Azure (and test app through HTTPS)
4. Check out Spring Boot Actuator
   * [Reference](https://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/html/actuator.html#actuator)
   * [How-to Guide](https://spring.io/guides/gs/actuator-service/)
5. Add password-protected notepads with header-based auth
