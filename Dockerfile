FROM openjdk:17-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "-DCTRFIN_DATASOURCE_URL=jdbc:mysql://meu_mysql:3306/ctrfin", "-DCTRFIN_DATASOURCE_USERNAME=root", "-DCTRFIN_DATASOURCE_PASSWORD=senha", "-DCTRFIN_JWT_EXPIRATION=86400000", "-DCTRFIN_JWT_SECRET=123456","/app.jar"]