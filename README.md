# Padn't

Padn't is a [Dontpad](https://dontpad.com/) clone implemented with Java Spring Boot.

<br/>
<picture>
  <source srcset="https://user-images.githubusercontent.com/27034173/221334819-a09ff5c2-8df0-400e-895c-eee96c95dd9c.png" media="(prefers-color-scheme: dark)" alt="padnt-index-transparent-darkmode" />
  <img src="https://user-images.githubusercontent.com/27034173/221334818-390a15a5-1b76-4d0c-be84-8093290e9b1e.png" alt="padnt-index-transparent" />
</picture>
<br/>
<br/>


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
