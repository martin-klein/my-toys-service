FROM openjdk:11
VOLUME /tmp
ADD target/my-toys-service-0.0.1.jar my-toys-service-0.0.1.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","my-toys-service-0.0.1.jar"]