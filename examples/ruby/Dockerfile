FROM java:8
WORKDIR /app
COPY . .
RUN curl --ssl -L https://github.com/jerverless/jerverless/releases/download/v0.1.0/org.jerverless-0.1.0.jar > jerverless.jar \
    && chmod +x jerverless.jar \
    && apt-get update \
    && apt install -y ruby

ENTRYPOINT ["java", "-jar", "jerverless.jar"]
