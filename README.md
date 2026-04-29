
## 1. Pré-requisitos
- Acesso a uma VM com Docker instalado (ex: Azure VM com Ubuntu ou Alma Linux).


- Portas 8080 (API) e 3306 (MySQL) liberadas no grupo de segurança da rede (NSG).


## 2. Preparação do Ambiente
- Clone o repositório da aplicação: git clone [https://github.com/Mariinoue/treino-api.git](https://github.com/Mariinoue/treino-api.git).


- Acesse a pasta do projeto: cd treino-api.


## 3. Execução dos Containers
- Rede e Volume: Execute os comandos de criação da rede treino-net e do volume mysql-treino-vol para garantir que os containers possam se comunicar e que os dados não sejam perdidos ao reiniciar o container.


- Banco de Dados: Inicie o container do MySQL utilizando a imagem mysql:8.0. Certifique-se de definir as variáveis de ambiente para o banco treinodb e o usuário treino_user.


- API: Inicie o container da API utilizando a imagem maven. O comando montará o código fonte local dentro do container, compilará o projeto e iniciará o servidor na porta 8080.


* Importante: A variável SPRING_DATASOURCE_URL deve apontar para o nome do container do banco (mysql-RM565834) e não para localhost.


## 4. Verificação
- Verifique se os containers estão rodando com docker ps.


- Acompanhe a subida da aplicação com docker logs -f api-RM565834.


## 5. Testando o CRUD
Utilize o comando curl diretamente no terminal da VM para validar as operações de INSERT, UPDATE, DELETE e GET no endpoint http://localhost:8080/api/treinos.


