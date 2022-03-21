FROM openjdk:8-jdk-alpine
#RUN addgroup -S paymentchain && adduser -S admin -G paymentchain
#USER admin:paymentchain
copy "./target/desafio-0.0.1-SNAPSHOP.jar" "app.jar"
expose 8080
ENTRYPOINT [ "java", "-jar", "app.jar" ]

#VOLUME /tmp
#ARG JAR_FILE=target/*.jar
#ADD target/desafio-0.0.1-SNAPSHOP.jar app.jar
#ENV JAVA_OPTS=""
#ENTRYPOINT [ "java", "-jar", "java $JAVA_OPTS /app.jar" ]