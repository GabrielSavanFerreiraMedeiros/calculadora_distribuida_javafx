# calculadora_distribuida_javafx

Este projeto é um pequeno sistema distribuído de servidores utilizando sockets (Calculadora).
O projeto é composto de algumas funções que foram divididas. 

O sistema é composto de: um cliente que utiliza uma interface gráfica para encaminhará as operações via sockets 
para servidores especialistas. São eles 

--> ServCalc1 > ClientHandler1: responsável pelas operações de soma, subtração, multiplicação e divisão.
--> ServCalc2 > ClientHandler2: responsável pelas operações de exponenciação, raiz quadrada e porcentagem.

Foi efetuada a transmissão do protocolo através da separação de valores através do reconhecimento do espaço
onde se recebe uma string que é demonstrada entre parênteses:
-->No ClientHandler1 ( primeiro valor + espaço + operador + espaço + segundo valor );
-->No ClientHandler2 ( primeiro valor + espaço + operador ).

	Em cada um dos servidores, a string correspondente é tratada, inserindo os valores e o op num vetor, e assim é
	possível manipular os valores e devolver o resultado. 

Foi utilizado o local host, com as portas 9999 para o ServCalc1 e a 10000 para o ServCalc2. 

