FROM eclipse-temurin:19

copy target/ivan200sr api.jar

entrypoint ["java","-jar","/api.jar"]

