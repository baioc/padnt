# Padn't

[Padn't](https://padnt.azurewebsites.net/) is a [Dontpad](https://dontpad.com/) clone implemented with Java Spring Boot.

![padnt-index-mo](https://user-images.githubusercontent.com/27034173/221325954-b8131c34-6171-4fcd-ba20-73404346bc6a.png)


## Starting the server

```sh
$ mvn spring-boot:run
```

### Environment variables

Name | Variable
---|---
`SERVER_PORT` | `8080` |
`LOGGING_LEVEL_ROOT` | `INFO` |
`JAVA_OPTS` | `''` |


## TODOs

1. Check out Spring Boot Actuator
   * [Reference](https://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/html/actuator.html#actuator)
   * [How-to Guide](https://spring.io/guides/gs/actuator-service/)
2. Add password-protected notepads with header-based auth
