FROM eclipse-temurin:19

copy target/apiportfolio-0.0.1-SNAPSHOT.jar api.jar

entrypoint ["java","-jar","/api.jar"]

