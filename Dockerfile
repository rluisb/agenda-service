FROM openjdk:11-jre-slim
EXPOSE 8080
ENV JAVA_OPTS -Xmx1024m -Xms1024m -Djava.security.egd=file:/dev/./urandom -Duser.timezone=America/Sao_Paulo
ADD build/docker/agenda-service-0.0.1-SNAPSHOT.tar /
ENTRYPOINT ["/agenda-service-0.0.1-SNAPSHOT/bin/agenda-service"]
