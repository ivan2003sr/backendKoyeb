FROM amazoncorretto:17
MAINTAINER ivan200sr
COPY out/artifacts/Backend_jar/Backend.jar Backend.jar
ENTRYPOINT ["java","-jar",/Backend.jar]