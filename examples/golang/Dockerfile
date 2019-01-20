FROM golang:1.11 as builder
WORKDIR /build
COPY helloworld.go .
RUN go build -o app helloworld.go

FROM jerverless:latest
WORKDIR /usr/src/app
COPY --from=builder /build/app .
COPY jerverless.yml .
EXPOSE 8080