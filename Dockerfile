FROM registry-vpc.cn-qingdao.aliyuncs.com/hotel-manger-backend/jdk
VOLUME /tmp 
ADD target/hotel_manager-0.0.1-SNAPSHOT.jar app.jar 
EXPOSE 10086 
ENTRYPOINT ["java","-jar","app.jar"]
