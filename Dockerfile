FROM eclipse-temurin:17

copy target/ivan200sr.jar api.jar

entrypoint ["java","-jar","/api.jar"]