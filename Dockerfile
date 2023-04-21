FROM eclipse-temurin:17-jdk-jammy

copy target/ivan200sr.jar api.jar

entrypoint ["java","-jar","/api.jar"]