FROM adoptopenjdk/openjdk11:alpine-jre
ADD /target/reserva-0.0.1-SNAPSHOT.jar reserva-0.0.1-SNAPSHOT.jar
EXPOSE 9192
ENTRYPOINT ["java","-jar","reserva-0.0.1-SNAPSHOT.jar"]
