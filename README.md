# Padn't

Padn't is a [Dontpad](https://dontpad.com/) clone implemented with Java Spring Boot.

<img src="https://github.com/user-attachments/assets/835e5fd3-dc49-4073-bd58-dcc4af63c3b9" alt="padnt-index" />

## Starting the server

Running it locally:

```sh
$ mvn spring-boot:run
```

Or, in order to build (and deploy from) a JAR file:

```sh
$ mvn clean package
$ java -jar target/padnt-*.jar
```

### Environment variables

| Name | Value |
| --- | --- |
| `SERVER_PORT` | `8080` |
| `LOGGING_LEVEL_ROOT` | `INFO` |
| `LOGGING_LEVEL_BAIOC_PADNT` | `${LOGGING_LEVEL_ROOT}` |
| `PADNT_INDEX_TITLE` | `"padn't"` |
| `PADNT_INDEX_DESCRIPTION` | `"Notepad but it's an online multiplayer app"` |
| `PADNT_INDEX_GITHUB` | `"https://github.com/baioc/padnt"` |
| `JAVA_OPTS` | `''` |

NOTE: `JAVA_OPTS` can be used to override anything else on [application.properties](src/main/resources/application.properties).
