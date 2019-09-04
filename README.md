# DrController-backend

 Backend do controlador de emissão de desenhos CAD,consome e envia requisições no formato JSON.

## Requerimentos para build do projeto:
* jdk 1.8
* Maven 3

Executando o comando "mvn install" pelo terminal no diretorio do projeto, onde está o arquivo pom.xml
as dependencias necessaria para aplicação: Spring Boot, Spring Security,Spring DataJPA,Hibernate, Liquibase(opcional), MySQL Connector serão baixadas pelo maven.

## Requerimentos para o Banco de Dados
* Mysql ou outro banco de dados SQL.

Se preferir usar outro banco SQL, vera substituir a dependencia MySQL Connector no pom.xml pela dependencia do banco preferido.
