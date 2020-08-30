FROM openjdk:12
VOLUME /tmp
#This Dockerfile doesn't have EXPOSE value bacause product-service use a random port, in docker
#run uses -P instead -p "port-number-internal":"port-number-external"
ADD ./target/product-service-0.0.1-SNAPSHOT.jar product-service.jar
ENTRYPOINT ["java", "-jar", "/product-service.jar"]