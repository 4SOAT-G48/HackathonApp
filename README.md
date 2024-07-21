# HackathonApp

Aplicação do projeto Hackathon 4SOAT grupo 48.
Este projeto contem a aplicação de backend do projeto Hackathon 4SOAT grupo 48, modulos cadastro e consultas.

Para a parte técnica veja as sessões abaixo, para demais documentações acessar
a [wiki deste projeto](https://github.com/4SOAT-G48/HackathonDocs/wiki).

## Pré requisitos

Tanto para executar como para desenvolver são necessários os seguintes itens:

1. Instalar o docker;
2. Instalar o docker-compose;
3. Instalar o git.

## Executar aplicação via docker compose

1. Clonar o projeto git;
2. Crie o arquivo **.env** no mesmo diretório que o arquivo **docker-compose.yml** com o a estrutura do arquivo *
   *.env.example**
    - _Iremos passar as informacoes contidas no .env via email e/ou discord._
3. Via terminal entrar na pasta do projeto;
4. Executar o comando;
    ``` sh
    docker-compose up --build -d
    ```

## Desenvolvimento

### Pré requisitos adicionais

1. Instalar o java versão 21;
    1. Link para download e instruções https://adoptium.net/installation/;
2. Maven versão 3.9.5 ou superior;
3. Instalar a IDE IntelliJ;
    1. https://www.jetbrains.com/idea/;
    2. Baixe a versão _*IntelliJ IDEA Community Edition*_;
4. dbeaver como cliente de conexão de banco;

### Para executar a aplicação direto na máquina

1. Na primeira vez que executar o projeto, será necessário criar o arquivo _.env_ na raiz do projeto, com a estrutura
   do arquivo _.env.example_;
2. Para rodar o postgres na máquina terá duas opções:
    1. Opção 1, usando o docker-compose do projeto (recomendado):
        1. Crie o arquivo **.env** no mesmo diretório que o arquivo **docker-compose.yml** com o a estrutura do
           arquivo *
           *.env.example**
        2. Via terminal entrar na pasta do projeto;
        3. Executar o comando;
            ``` sh
            docker-compose up db --build -d
            ``` 
    2. Opção 2, diretamente via docker:
        1. construa o container de banco de dados com o seguinte comando:
        ``` sh
        docker run --rm -d --name postgres --network fiap -e 
       POSTGRES_PASSWORD={SENHA} -e POSTGRES_USER={USUARIO} -e POSTGRES_DB={BANCO_DE_DADOS} 
       -p 5432:5432 -v ./pgdata:/var/lib/postgresql/data postgres
        ```
        2. para parar o container
        ``` sh
        docker stop postgres
        ```
        3. para executar o container novamente
        ``` sh
        docker start postgres
        ```
3. Na primeira vez que executar a base é necessário criar o schema:
    1. para saber o nome da base a ser criada consulte o arquivo _.env_ na variável _POSTGRES_DB_;
4. Para executar a aplicação a partir da IDE IntelliJ:
    1. Pela primeira vez:
        1. procure na árvore do projeto o arquivo
           _src/main/java/br/com/fiap/soat4/grupo48/telemed/Grupo48Application.java_;
        2. clique com o botão direito do mouse sobre ele e selecione a opção _Modify Run Configuration..._;
        3. na janela que abrir selecione a opção _Enable EnvFile_, depois clique no _+_, selecione a opção _.env file_ e
           selecione o arquivo _.env_ que foi criado;
        4. acione os botões _Apply_ e depois _Ok_;
    2. Após a primeira vez:
        1. clique no botão de Run ou Debug da IDE ao lado da opção _Grupo48Application_.;
        2. a aplicação será executada e estará disponível no endereço _http://localhost:8081_;
            1. a porta vai depender do que foi configurado no arquivo _.env_ na variável _SERVER_PORT_;
            2. Para acessar a documentação da API, acesse o endereço _http://localhost:8081/swagger-ui.html_.

