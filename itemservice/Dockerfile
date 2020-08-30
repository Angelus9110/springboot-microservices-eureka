FROM openjdk:12
VOLUME /tmp
ADD ./target/itemservice-0.0.1-SNAPSHOT.jar item-service.jar
ENTRYPOINT ["java", "-jar", "/item-service.jar"]