FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/blockchain-core.jar /app/blockchain.jar
COPY config/ /app/config/
EXPOSE 8080 8888
ENTRYPOINT ["java", "-jar", "blockchain.jar"]
HEALTHCHECK --interval=10s --timeout=3s CMD curl -f http://localhost:8080/chain || exit 1
