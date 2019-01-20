FROM java:8 as builder
WORKDIR /app
COPY . .
RUN javac HelloWorld.java

FROM jerverless/jerverless:latest
WORKDIR /app
COPY --from=builder /app/HelloWorld.class .
COPY jerverless.yml .
EXPOSE 8080
