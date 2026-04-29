# Treino API

API REST para registro de treinos físicos, desenvolvida com Java 17 e Spring Boot. Permite criar, listar, atualizar e deletar registros de treino, com suporte a busca por modalidade e faixa de duração. O banco de dados MySQL e a API rodam em containers Docker isolados em rede bridge, com persistência via volume nomeado.

---

## Pré-requisitos

- [Docker](https://www.docker.com/) instalado e em execução
- Git

---

## Como executar

### 1. Clone o repositório

```bash
git clone https://github.com/Mariinoue/treino-api.git
cd treino-api
```


### MySQL Local - Porta 3306 em uso

Se você tiver o MySQL instalado localmente, a porta 3306 pode estar ocupada. Verifique com:

```bash
netstat -ano | findstr :3306
```

Se aparecer algum processo, pare o serviço MySQL local antes de subir os containers:

```bash
# Windows (PowerShell como Administrador)
net stop MySQL80

# Para reativar depois
net start MySQL80
```



### 2. Suba os containers (Script Bash)

```bash
# Rede e volume
docker network create treino-net
docker volume create mysql-treino-vol

# Banco de dados (MySQL 8.0)
docker run --name mysql-RM565834 -d \
  --network treino-net \
  -p 3306:3306 \
  -v mysql-treino-vol:/var/lib/mysql \
  -e MYSQL_DATABASE=treinodb \
  -e MYSQL_USER=treino_user \
  -e MYSQL_PASSWORD=treino_pass \
  -e MYSQL_ROOT_PASSWORD=root_pass \
  mysql:8.0

# API
docker run --name api-RM565834 -d \
  --network treino-net \
  -p 8080:8080 \
  -v $(pwd):/app \
  -w /app \
  -e SPRING_DATASOURCE_URL=jdbc:mysql://mysql-RM565834:3306/treinodb \
  -e SPRING_DATASOURCE_USERNAME=treino_user \
  -e SPRING_DATASOURCE_PASSWORD=treino_pass \
  maven:3.9-eclipse-temurin-17 \
  bash -c "mvn clean package -DskipTests -Dproject.build.sourceEncoding=UTF-8 && java -jar target/*.jar"
```

### 3. Aguarde a API inicializar

A primeira execução baixa as dependências Maven — pode levar alguns minutos. Acompanhe o log:

```bash
docker logs -f api-RM565834
```

Aguarde a mensagem `Started TreinoApiApplication in X seconds`.

---




