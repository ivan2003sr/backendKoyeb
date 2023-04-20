FROM eclipse-temurin:17
MAINTAINER ivan200sr
COPY target/ivan200sr.jar Backend.jar
ENTRYPOINT ["java","-jar","/Backend.jar"]