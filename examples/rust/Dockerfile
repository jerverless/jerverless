FROM rust:1.31 as builder
WORKDIR /build
COPY helloworld.rs .
RUN rustc helloworld.rs

FROM jerverless/jerverless:latest
WORKDIR /app
COPY --from=builder /build/helloworld .
COPY jerverless.yml .
EXPOSE 8080
