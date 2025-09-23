# gira_turnos_backend — Como rodar localmente

Este guia mostra, passo a passo, como executar o projeto gira_turnos_backend na sua máquina local. Ele segue o fluxo que você indicou: instalar dependências, clonar o repositório, configurar variáveis de ambiente em runtime (no IDE) e criar a database no pgAdmin.

Pré-requisitos
--------------
Instale os itens abaixo antes de executar o projeto:

- Git
- PostgreSQL (servidor)
- pgAdmin (para gerenciar o banco)
- JDK (recomendado: Java 17+)
- JRE compatível com a versão do JDK
- IDE: IntelliJ IDEA (Community ou Ultimate)

Passo 1 — Instalar dependências do sistema
-----------------------------------------
1. Instale PostgreSQL (siga o instalador para seu SO).
2. Instale pgAdmin para facilitar a criação/administracão do banco.
3. Instale JDK (Java Development Kit) e verifique:
   - java -version
   - javac -version
4. Instale IntelliJ IDEA.

Passo 2 — Clonar o repositório
------------------------------
Abra um terminal e execute:

git clone https://github.com/GustavoHenrique-hub/gira_turnos_backend.git
cd gira_turnos_backend

Passo 3 — Configurar variáveis de ambiente em runtime (no IntelliJ)
-------------------------------------------------------------------
Abra o projeto no IntelliJ e configure as variáveis de ambiente da run configuration antes de executar a aplicação.

Como criar as variáveis (passo a passo):
1. Abra o projeto no IntelliJ.
2. No canto superior direito da janela do IntelliJ, abra a Run/Debug configuration da aplicação (ícone da run > Edit Configurations).
   - Em algumas versões há um ícone com 3 bolinhas/reticências próximo ao campo "Environment variables". Clique nele.
3. No campo "Environment variables" clique no botão para editar / adicionar.
4. Crie as variáveis necessárias (nome -> valor). Para este projeto, crie as chaves abaixo:
   - SEND_GRID_APIKEY=<sua_chave_sendgrid_aqui>
   - spring.datasource.url=jdbc:postgresql://localhost:5432/giraturnos
   - spring.datasource.username=postgres
   - spring.datasource.password=root
5. Salve as alterações na configuração de execução.

Observações:
- Ajuste os valores conforme o seu ambiente (host, porta, nome do banco, usuário e senha).
- Se preferir executar pela linha de comando, exporte as variáveis no terminal (Linux/Mac):
  export SEND_GRID_APIKEY="sua_chave"
  export SPRING_DATASOURCE_URL="jdbc:postgresql://localhost:5432/giraturnos"
  export SPRING_DATASOURCE_USERNAME="seu_usuario"
  export SPRING_DATASOURCE_PASSWORD="sua_senha"
  (ou adapte os nomes conforme a forma como a aplicação lê as variáveis — se a aplicação espera spring.datasource.url diretamente, use esse nome.)

Passo 4 — Criar a database no pgAdmin
------------------------------------
1. Abra o pgAdmin e conecte-se ao servidor PostgreSQL local.
2. Clique com o botão direito em "Databases" -> Create -> Database...
3. Preencha:
   - Database: giraturnos
   - Owner: (selecione o usuário, ex.: postgres)  
4. Salve. A database "giraturnos" deve aparecer na lista.

Alternativa via psql (terminal):
- Caso prefira, no terminal/psql execute:
  createdb -U <seu_usuario> -h localhost giraturnos
  (ou use: psql -U <seu_usuario> -c "CREATE DATABASE giraturnos;")

Passo 5 — Executar a aplicação
------------------------------
Existem duas formas comuns:

1) Pelo IntelliJ
- Com as variáveis de ambiente configuradas na Run Configuration, clique em Run (play). A aplicação Spring Boot iniciará.

2) Pela linha de comando
- Se o projeto usa Maven wrapper:
  ./mvnw spring-boot:run
  ou, no Windows:
  mvnw.cmd spring-boot:run
- Se preferir, gere o jar e execute:
  ./mvnw package
  java -jar target/<nome-do-jar>.jar

Verificação
-----------
- Verifique os logs da aplicação para confirmar que:
  - Conexão com o PostgreSQL foi estabelecida (sem erros de autenticação).
  - A aplicação iniciou e está escutando em uma porta (ex.: 8080).
- Acesse endpoints conhecidos (por exemplo, http://localhost:8080/health ou a raiz da API) para confirmar que está funcionando.

Dicas e resolução de problemas
------------------------------
- Erro de conexão com o banco:
  - Verifique spring.datasource.url, username e password.
  - Confirme que o servidor PostgreSQL está em execução e que a porta (por padrão 5432) está correta.
- Variáveis não sendo lidas:
  - Confirme o nome exato esperado pela aplicação (spring.datasource.url vs SPRING_DATASOURCE_URL).
  - No IntelliJ, verifique se a Run Configuration selecionada é a que você editou.
- Porta em uso:
  - Mude a porta via propriedade (ex.: --server.port=8081) ou no application.properties/application.yml.

Resumo rápido (comandos)
-----------------------
git clone https://github.com/GustavoHenrique-hub/gira_turnos_backend.git
cd gira_turnos_backend
# configurar variáveis no IntelliJ ou export no terminal
# criar database "giraturnos" no pgAdmin ou psql
./mvnw spring-boot:run   # ou ./gradlew bootRun

Contato
-------
Para dúvidas sobre o projeto, entre em contato com o mantenedor do repositório: https://github.com/GustavoHenrique-hub
