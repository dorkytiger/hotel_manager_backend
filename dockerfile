FROM openjdk:17-jdk-alpine
VOLUME /tmp 
ADD target/hotel_manager-0.0.1-SNAPSHOT.jar app.jar 
EXPOSE 10086 
ENTRYPOINT ["Bash","-DBash.security.egd=file:/dev/./urandom","-jar","/app.jar","--spring.profiles.active=prd"]
