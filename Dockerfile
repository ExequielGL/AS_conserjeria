FROM gradle:8.3-jdk17 as build
LABEL authors="ExequielG"

WORKDIR /home/gradle/src

COPY build.gradle settings.gradle ./
COPY . .

RUN gradle build --no-daemon

FROM openjdk:17-jdk

EXPOSE 7070

RUN mkdir /app

COPY --from=build /home/gradle/src/build/libs/*.jar /app/conserjeria.jar

ENTRYPOINT ["java", "-jar", "/app/conserjeria.jar"]