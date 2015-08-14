# work-01
Amil v0 spring boot application with H2 database, json and spring rest services


Introdução

A aplicação criada foi baseada do seguintes frameworks:

- Spring Boot
- Spring Data
- Spring JPA
- Hibernate
- JDBC
- REST

Basicamente são oferecidos como serviços as operações disponíveis no servidor. 

As operações são as seguintes:

1) Lê o arquivo de Log

http://localhost:8080/lerarquivolog

2) Recupera a arma preferida utilizada pelos matadores no jogo:

http://localhost:8080/armapreferida

3) Lista os jogadores com bônus: Identificar a maior sequência de assassinatos efetuadas por 
um jogador (streak) sem morrer, dentro da partida; 

http://localhost:8080/maiorsequencia

4) Lista os jogadores com bônus: Jogadores que vencerem uma partida sem morrerem devem ganhar 
um "award";

http://localhost:8080/vencedorsemmorrer

5) Lista os jogadores com bônus: Jogadores que matarem 5 vezes em 1 minuto devem ganhar um "award". 

http://localhost:8080/matadoremumminuto
 

Algumas premissas e restrições devem ser consideradas para o programa desenvolvido:

- O arquivo de log a ser lido deve sempre manter o formato referido

- O local de leitura do arquivo deverá ser 

<aplicação>/src/main/resources/input 

- Não foram implementadas validaçõa de codificações de arquivo (UTF-8, ISO8856, etc)

- Não foram implementadas validações de capacidade de leitura e tamanho de arquivo.

- Não foram implementadas validações no arquivo.

- Não foram realizados testes de desempenho nem de capacidade.

- Não foram implementados tratamento de exceções

Obs: No programa podem haver possíveis erros e necessidades pois a intenção foi
demonstrar minhas habilidade em conhecer e propor novas coisas. 


Requisitos para execução da aplicação

- Java 1.8.x (CONFIGURAR VARIÁVEIS DE AMBIENTE)
- Maven 3.x 
- Banco de dados H2

- Instalação e execução do banco de dados H2

	- Downlçoad: http://www.h2database.com/html/download.html
	- Instalação: http://www.h2database.com/html/installation.html
	- Início: <H2_HOME>/bin/h2.bat (Windows) e <H2_HOME>/bin/h2.sh (Linux) 
	- Mais informações: http://www.h2database.com/html/quickstart.html
	
	Obs: é possível somente uma conexão ao banco de dados H2 (default), então ao iniciar a aplicação,
	deve-se desconectar do console web ou vice-versa.

- Arquivo de log 
	- Colocar arquivo de log com noem de "log.log" no caminho
	
		<aplicação>/src/main/resources/input
		
- Compilar 

	<aplicação> mvn package -U
	 
- Executar
	
	

	
OBSERVAÇÕES

Tentei abordar um pouco de novas tecnologias com os frameworks Spring 4, Spring Boot e Spring Data, 
abordando também o conceito de "Microservices", onde o Spring Boot é excelente para sua implementação.
Também aborda a questão de TDD, onde os testes unitários foram criados previamente a implementação das 
camadas superiores e o uso de design patterns (State) e conceito de orientação a objetos. 


Referencias

Microservices
http://www.infoq.com/br/articles/boot-microservices 

Spring
http://projects.spring.io/spring-boot/

H2 Database
http://www.h2database.com/html/main.html


Obrigado,

vinicius.global@gmail.com