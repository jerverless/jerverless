FROM openjdk:8-jdk as builder
WORKDIR /app
COPY . .
RUN chmod +x gradlew \
    && ./gradlew assemble \
    && ./gradlew installDist

FROM openjdk:8-jre-slim
WORKDIR /usr
COPY --from=builder /app/build/install/jerverless/* .
RUN ls -l
ENTRYPOINT ["/usr/bin/jerverless"]
