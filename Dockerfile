FROM maven:3-jdk-11 as builder

# create app folder for sources
RUN mkdir -p /build
WORKDIR /build
COPY pom.xml /build

#Download all required dependencies into one layer
#RUN mvn -B -q dependency:resolve dependency:resolve-plugins

#Copy source code
COPY src /build/src

# Build application (TODO: rm target)
RUN mvn install

FROM openjdk:11 as runtime
VOLUME /tmp
COPY --from=builder build/target/*.jar my-toys-service.jar
EXPOSE 80
ENTRYPOINT ["java","-jar","my-toys-service.jar"]
