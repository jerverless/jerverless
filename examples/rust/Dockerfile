FROM rust:1.31 as builder
WORKDIR /build
COPY helloworld.rs .
RUN rustc helloworld.rc

FROM jerverless/jerverless:latest
WORKDIR /app
COPY --from=builder /build/helloworld .
EXPOSE 8080
