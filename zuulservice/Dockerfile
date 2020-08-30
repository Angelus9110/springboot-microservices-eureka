FROM openjdk:12
VOLUME /tmp
EXPOSE 8080
ADD ./target/zuulserver-0.0.1-SNAPSHOT.jar zuul-server.jar
ENTRYPOINT ["java", "-jar", "/zuul-server.jar"]