FROM java:8
WORKDIR /app
COPY . .
RUN curl --ssl -L https://github.com/jerverless/jerverless/releases/download/v0.1.0/org.jerverless-0.1.0.jar > jerverless.jar \
    && chmod +x jerverless.jar \
    && apt-get update \
    && apt-get -y install curl apt-transport-https \
    && curl https://packages.microsoft.com/keys/microsoft.asc | apt-key add - \
    && sh -c 'echo "deb [arch=amd64] https://packages.microsoft.com/repos/microsoft-debian-jessie-prod jessie main" > /etc/apt/sources.list.d/microsoft.list' \
    && apt-get update \
    && apt-get -y install powershell
ENTRYPOINT ["java", "-jar", "jerverless.jar"]
