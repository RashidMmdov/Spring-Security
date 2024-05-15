FROM alpine
COPY build/libs/Users-0.0.1-SNAPSHOT.jar /app/Users-0.0.1-SNAPSHOT.jar
RUN apk add --no-cache openjdk17
WORKDIR "/app"
ENTRYPOINT ["java"]
CMD ["-jar","Users-0.0.1-SNAPSHOT.jar"]