FROM openjdk:8-alpine
MAINTAINER Your Name <you@example.com>

ADD target/dex-api-0.0.1-SNAPSHOT-standalone.jar /dex-api/app.jar

EXPOSE 8080

CMD ["java", "-jar", "/dex-api/app.jar"]
