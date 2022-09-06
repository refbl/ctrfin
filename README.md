
# CtrFin
Projeto Referente ao Challenge Agosto-2022 da Alura - Controle pessoal de Finanças

### Comando para Rodar o Jar da Aplicação (profile prod)
java -jar -Dspring.profiles.active=prod -DCTRFIN_DATASOURCE_URL=jdbc:mysql://localhost:3306/ctrfin -DCTRFIN_DATASOURCE_USERNAME=root -DCTRFIN_DATASOURCE_PASSWORD=teste -DCTRFIN_JWT_EXPIRATION=86400000 -DCTRFIN_JWT_SECRET=123456 ctrfin.jar

### Build da Imagem do Projeto (no diretório raiz do projeto)
docker build -t refbl/ctrfin .

### Build da Imagem do banco de dados (no diretório docker-database)
docker build -t refbl/mysql .

### Rodar somenter a aplicação com Docker e banco de dados local
docker run -p 8080:8080 -e CTRFIN_DATASOURCE_URL='jdbc:mysql://localhost:3306/ctrfin' -e CTRFIN_DATASOURCE_USERNAME='root' -e CTRFIN_DATASOURCE_PASSWORD='teste' -e CTRFIN_JWT_EXPIRATION='86400000' -e CTRFIN_JWT_SECRET='123456' -e SPRING_PROFILES_ACTIVE='prod' refbl/ctrfin

### Rodar aplicação e banco de dados em container (no diretório raiz do projeto)
docker compose up
