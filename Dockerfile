# Используем официальный образ с JDK 17
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
EXPOSE 8081

# Копируем JAR файл с правильным именем, полученным в результате сборки Maven
COPY target/walletapi-1.0.0.jar app.jar

# Запуск приложения
ENTRYPOINT ["java", "-jar", "app.jar"]

