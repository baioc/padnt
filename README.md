# Padn't

[Padn't](https://padnt.azurewebsites.net/) is a [Dontpad](https://dontpad.com/) clone implemented with Java Spring Boot.

![padnt-index-mo](https://user-images.githubusercontent.com/27034173/221325954-b8131c34-6171-4fcd-ba20-73404346bc6a.png)


## Starting the server

```sh
$ mvn spring-boot:run
```

### Environment variables

Name | Value
---|---
`SERVER_PORT` | `8080` |
`LOGGING_LEVEL_ROOT` | `INFO` |
`LOGGING_LEVEL_BAIOC_PADNT` | `${LOGGING_LEVEL_ROOT}` |
`JAVA_OPTS` | `''` |

NOTE: `JAVA_OPTS` can be used to override anything else on [application.properties](src/main/resources/application.properties), for instance.
