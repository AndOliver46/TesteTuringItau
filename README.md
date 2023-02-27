## Rodar projeto : </br>

### 1° Rodar projeto Java/Spring: </br>
$ cd backend</br>
$ mvn spring-boot:run</br>

### 2° Abrir outro terminal</br>

### 3° Rodar projeto ReactJS:</br>
$ cd frontend</br>
$ yarn install </br>
$ yarn start</br>

### 4° Executar Frontend no endereço:
http://localhost:3000/login


### Usuários previamente cadastrados
```
Numero da conta | Senha

21554 | Senha12$
21675 | Senha12$
21564 | Senha12$
21567 | Senha12$
26156 | Senha12$
```

### application-test.properties
```
# DATASOURCE
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.username=sa
spring.datasource.password=

# H2 CLIENT
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# JPA, SQL
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.defer-datasource-initialization=true
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

### Requerimentos
Java > 17 </br>
Maven > 3.0 </br>
Spring > 3.0.1.RELEASE pom.xml </br>

</br>
</br>

Java
ReactJS
JWT
Spring Security
H2
IntelliJ
