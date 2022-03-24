FROM openjdk:11.0-jdk-slim-stretch
run apt-get install -y tzdata
env TZ America/Lima
volume [ "/home" ]
entrypoint [ "java", "-jar", "-Dspring.profile.active=release", "/home/banca-0.0.1-SNAPSHOT.jar" ]

#CMD ["/bin/sh"]