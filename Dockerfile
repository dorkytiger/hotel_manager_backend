FROM registry.cn-qingdao.aliyuncs.com/hotel-manger-backend/maven:latest AS builder

ADD ./pom.xml pom.xml
RUN  mvn install

ADD . .

RUN mvn clean package

FROM registry.cn-qingdao.aliyuncs.com/hotel-manger-backend/jdk:latest

COPY --from=builder target/hotel_manager-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 10086 
ENTRYPOINT ["java","-jar","app.jar"]
