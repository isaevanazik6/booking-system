FROM openjdk:21
ARG JAR_FILE=target/*.jar
RUN echo ${JAR_FILE} booking-system.jar
ENTRYPOINT ["java","-jar","./booking-system.jar"]