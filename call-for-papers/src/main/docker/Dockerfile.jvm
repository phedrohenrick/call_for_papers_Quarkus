# === Stage 1: Build ===
FROM maven:3.8.6-openjdk-21 AS build
WORKDIR /app

# Copia apenas o pom.xml da pasta "call-for-papers"
COPY call-for-papers/pom.xml ./
# Copia o diretório src da pasta "call-for-papers"
COPY call-for-papers/src ./src

# Baixa as dependências e compila o projeto (gera o jar "-runner.jar")
RUN mvn clean package -DskipTests

# === Stage 2: Runtime ===
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copia o jar gerado na etapa de build para a imagem final
COPY --from=build /app/target/*-runner.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
