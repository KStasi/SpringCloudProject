
FROM gradle:jdk13 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM openjdk:13-jdk-alpine
EXPOSE 8888

RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/app.jar
COPY src/main/resources/* ./

ENTRYPOINT ["java", "-jar","/app/app.jar"]
