FROM amazoncorretto:11
MAINTAINER ivan200sr
COPY target/Backend.jar Backend.jar
ENTRYPOINT ["java","-jar","/Backend.jar"]