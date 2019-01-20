FROM jerverless/jerverless:latest
WORKDIR /app
COPY . .
RUN apt-get update \
    && apt-get -y install curl apt-transport-https \
    && curl https://packages.microsoft.com/keys/microsoft.asc | apt-key add - \
    && sh -c 'echo "deb [arch=amd64] https://packages.microsoft.com/repos/microsoft-debian-jessie-prod jessie main" > /etc/apt/sources.list.d/microsoft.list' \
    && apt-get update \
    && apt-get -y install powershell
EXPOSE 8080
