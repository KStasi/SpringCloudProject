
FROM gradle:jdk13 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM openjdk:13-jdk-alpine
EXPOSE 8761

RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/app.jar
COPY src/main/resources/* ./

RUN apk add --no-cache bash
ADD https://raw.githubusercontent.com/vishnubob/wait-for-it/master/wait-for-it.sh wait-for-it.sh
RUN chmod +x wait-for-it.sh

CMD ./wait-for-it.sh client:8081 --timeout=480 --strict -- java -jar /app/app.jar
