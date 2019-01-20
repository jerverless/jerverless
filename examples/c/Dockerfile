FROM ubuntu:18.04 as builder
WORKDIR /app
COPY hello.c .
RUN apt-get update \
    && apt-get -y install build-essential \
    && gcc hello.c -o hello

FROM jerverless/jerverless:latest
WORKDIR /app
COPY --from=builder /app/hello .
COPY jerverless.yml .
EXPOSE 8080
