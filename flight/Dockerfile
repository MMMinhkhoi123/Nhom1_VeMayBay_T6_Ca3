FROM openjdk:22-ea-19
#COPY .mvn/ .mvn
#COPY mvnw pom.xml ./
ADD target/hello.jar hello.jar
#RUN ./mvnw dependency:go-offline
#COPY src ./src
#CMD [ "./mvnw", "spring-boot:run" ]
ENTRYPOINT ["java","-jar","/hello.jar"]