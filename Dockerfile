FROM openjdk:20

ENV ENVIRONMENT=prod

LABEL maintainer="team-project-1"

EXPOSE 8080

ADD backend/target/animalapp.jar app.jar

CMD [ "sh", "-c", "java -jar /app.jar" ]