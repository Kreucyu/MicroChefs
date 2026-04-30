FROM cgr.dev/chainguard/jdk:latest
WORKDIR /home/nonroot/app
COPY target/pedidos-0.0.1-SNAPSHOT.jar /home/nonroot/app/app.jar
EXPOSE 8090
CMD ["java", "-jar", "app.jar"]