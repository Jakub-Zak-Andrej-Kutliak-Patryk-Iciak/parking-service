FROM openjdk:17-oracle
ADD build/libs/parkingservice-0.0.1-SNAPSHOT.jar parkingservice-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "parkingservice-0.0.1-SNAPSHOT.jar"]
