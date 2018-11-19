FROM java:8
WORKDIR /app
COPY . .
RUN curl --ssl -L https://github.com/jerverless/jerverless/releases/download/v0.0.1/org.jerverless-0.0.1.jar > jerverless.jar \
    && chmod +x jerverless.jar \
    && curl --ssl -L https://product-dist.ballerina.io/downloads/0.983.0/ballerina-linux-installer-x64-0.983.0.deb > ballerina.deb\
    && dpkg -i ballerina.deb \
    && apt install -f
ENTRYPOINT ["java", "-jar", "jerverless.jar"]
