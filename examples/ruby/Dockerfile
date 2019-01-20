FROM jerverless/jerverless:latest
WORKDIR /app
COPY . .
RUN apt-get update \
    && apt install -y ruby
EXPOSE 8080
