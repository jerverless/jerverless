FROM jerverless/jerverless:latest
WORKDIR /app
COPY . .
RUN apt-get update \
    && apt-get install python
EXPOSE 8080
