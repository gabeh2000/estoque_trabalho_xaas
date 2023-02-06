FROM adoptopenjdk/openjdk11:alpine-jre
ADD /target/central-0.0.1-SNAPSHOT.jar central-0.0.1-SNAPSHOT.jar
EXPOSE 9193
ENTRYPOINT ["java","-jar","central-0.0.1-SNAPSHOT.jar"]
