FROM openjdk:17
ADD target/rest-web-services.jar rest-web-services.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","rest-web-services.jar"]