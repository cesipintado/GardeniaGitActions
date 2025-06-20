# Usa una imagen oficial de Java 17
FROM eclipse-temurin:17-jdk-jammy

# Configuración del directorio de la app
ENV APP_HOME=/app
RUN mkdir -p $APP_HOME
WORKDIR $APP_HOME

# Puerto usado por Spring Boot
EXPOSE 8084

# Copiar el JAR (usando patrón para evitar dependencia del nombre exacto)
COPY target/Gardenia-*.jar app.jar

# Crear un usuario no root por seguridad
RUN useradd -m appuser && chown -R appuser:appuser $APP_HOME
USER appuser

# Health check para monitoreo del contenedor
HEALTHCHECK --interval=30s --timeout=3s \
  CMD curl -f http://localhost:8084/actuator/health || exit 1

# Comando para correr la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
