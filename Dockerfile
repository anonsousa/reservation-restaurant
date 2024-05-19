FROM eclipse-temurin:21-alpine

VOLUME /tmp
EXPOSE 8080

# Definindo variáveis de ambiente com valores padrão
ENV DB_URL=jdbc:postgresql://localhost:5432/restaurant-spot-service
ENV DB_USERNAME=postgres
ENV DB_PASSWORD=banco123

ARG JAR_FILE=target/restaurant-reserve-api.jar
ADD ${JAR_FILE} app.jar

# Configurar o entrypoint para usar as variáveis de ambiente
ENTRYPOINT ["java", "-jar", "/app.jar"]