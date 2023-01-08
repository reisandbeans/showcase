FROM gradle:jdk17 AS build-env

USER root

WORKDIR /opt

COPY . .

RUN gradle :app:shadowJar --stacktrace --info

FROM amazoncorretto:17.0.3-alpine

RUN apk -U upgrade
RUN mkdir /usr/test-server

COPY --from=build-env /opt/app/build/libs/app.jar /usr/test-server

WORKDIR /usr/test-server
ENTRYPOINT ["sh", "-c"]
CMD ["java -Dvertx.logger-delegate-factory-class-name=io.vertx.core.logging.Log4j2LogDelegateFactory -jar app.jar"]
