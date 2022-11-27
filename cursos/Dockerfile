FROM openjdk:17-jdk-alpine3.14 as builder
WORKDIR /app/cursos
COPY ./pom.xml /app
COPY ./cursos/.mvn ./.mvn
COPY ./cursos/mvnw .
COPY ./cursos/pom.xml .
#compila solo para bajar las dependencias ahorra tiempo por que cachea las capas
RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/
#RUN ./mvnw dependency:go-offline alternativa si no va lo de arriba
COPY ./cursos/src ./src
RUN ./mvnw clean package -DskipTests

FROM openjdk:17-jdk-alpine3.14
WORKDIR /app
RUN mkdir ./logs
COPY --from=builder /app/cursos/target/cursos-0.0.1-SNAPSHOT.jar /app/cursos.jar
EXPOSE 8002
#ENTRYPOINT ["java", "-jar", "cursos.jar"] es mas estricto que CMD
CMD ["java", "-jar", "cursos.jar"]
