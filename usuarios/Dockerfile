ARG MSVC_NAME=usuarios
FROM openjdk:17-jdk-alpine3.14 as builder
ARG MSVC_NAME
WORKDIR /app/$MSVC_NAME
COPY ./pom.xml /app
COPY ./$MSVC_NAME/.mvn ./.mvn
COPY ./$MSVC_NAME/mvnw .
COPY ./$MSVC_NAME/pom.xml .
#compila solo para bajar las dependencias ahorra tiempo por que cachea las capas
RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/
#RUN ./mvnw dependency:go-offline alternativa si no va lo de arriba
COPY ./$MSVC_NAME/src ./src
RUN ./mvnw clean package -DskipTests

FROM openjdk:17-jdk-alpine3.14
ARG MSVC_NAME
WORKDIR /app
RUN mkdir ./logs
COPY --from=builder /app/$MSVC_NAME/target/$MSVC_NAME-0.0.1-SNAPSHOT.jar /app/$MSVC_NAME.jar
ENV PORT 8001
#variable de ambiente se establece tambien al application.properties, variable de ambiente
EXPOSE $PORT
#ENTRYPOINT ["java", "-jar", "usuarios.jar"] es mas estricto que CMD
CMD ["java", "-jar", "usuarios.jar"]
