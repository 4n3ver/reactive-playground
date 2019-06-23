# https://spring.io/guides/topicals/spring-boot-docker

### build ###
FROM openjdk:8-jdk as BUILDER

ARG APP_PATH=/usr/app

WORKDIR ${APP_PATH}

COPY gradlew .
COPY gradle ./gradle

RUN ./gradlew wrapper

COPY settings.gradle.kts .
COPY build.gradle.kts .

# download dependencies
RUN ./gradlew build -x :bootJar -x test --continue

COPY src ./src
RUN ./gradlew build unpack

### package ###
FROM openjdk:8-jre

ARG APP_PATH=/usr/app

WORKDIR ${APP_PATH}

COPY --from=BUILDER ${APP_PATH}/build/unpacked/BOOT-INF/lib ./lib
COPY --from=BUILDER ${APP_PATH}/build/unpacked/META-INF ./META-INF
COPY --from=BUILDER ${APP_PATH}/build/unpacked/BOOT-INF/classes ./

EXPOSE 8080

CMD ["java", "-classpath", ".:lib/*", "space.yoelivan.ApplicationKt"]
