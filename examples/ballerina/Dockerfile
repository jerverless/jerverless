FROM jerverless/jerverless:latest
WORKDIR /app
COPY . .
RUN curl --ssl -L https://product-dist.ballerina.io/downloads/0.983.0/ballerina-linux-installer-x64-0.983.0.deb > ballerina.deb\
    && dpkg -i ballerina.deb \
    && apt install -f
EXPOSE 8080
