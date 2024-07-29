FROM maven:3.8.5-openjdk-17 AS build

COPY src /app/src
COPY pom.xml /app

WORKDIR /app
RUN mvn clean install

FROM openjdk:17-alpine

COPY --from=build /app/target/javer-1.0.0.jar /app/app.jar

EXPOSE 8080

CMD ["java", "-jar", "app/app.jar"]