# 🚀 Desafio: Conectando Microserviços na Nuvem 🚀

Bem-vindos, desenvolvedores intrépidos, ao nosso desafio de programação! Preparem-se para uma jornada emocionante onde vocês irão explorar os confins do Springboot, criar microserviços interconectados e levá-los para a nuvem!

## 🛠️ Missão: Criar duas aplicações em Springboot, cada uma desempenhando um papel crucial em nosso ecossistema de microsserviços.

TEMA: Cadastro de clientes do banco JAVER
Informações do cliente (Para CRUD):

- nome (String)

- telefone (Long)

- correntista (Boolean)
- score_credito (Float)

- saldo_cc (Float)

1. Primeira Aplicação: Esta aplicação será nossa ponte para a segunda. Ela deve realizar requisições REST para a segunda aplicação. Além disso, é responsável por expor os quatro endpoints CRUD (Create, Read, Update, Delete) e um endpoint adicional que realizará um cálculo simples de score de credito (saldo_cc * 0,1) com as informações obtidas da base de dados da segunda aplicação.

2. Segunda Aplicação: Esta aplicação é o coração do nosso sistema de armazenamento. Ela será responsável por realizar operações CRUD em uma base de dados local H2.

🧪 Requisitos de Testes: Ambas as aplicações devem possuir 100% de cobertura de testes unitários. Garantir que suas implementações estejam robustamente testadas para garantir a qualidade e a confiabilidade do código.

🔍 Desafio Extra: Para os bravos que desejam ir além, o desafio extra aguarda! Você pode hospedar ambas as aplicações em instâncias EC2 do tipo t2.micro na AWS e expor seus endpoints publicamente na nuvem. Além disso, a base de dados pode residir em um RDS gratuito. Desafie-se a explorar os limites da computação em nuvem!

🚨 Importante: Lembre-se de documentar adequadamente suas implementações, fornecendo instruções claras sobre como executar, testar e acessar suas aplicações. Ao final da trilha, cada um deverá apresentar o que foi desenvolvido! Portanto treine no espelho sua apresentação.

🏆 Premiação: Além da satisfação pessoal de conquistar este desafio, os participantes terão a oportunidade de aprimorar suas habilidades em desenvolvimento de microserviços, testes unitários e computação em nuvem.

Então, estão prontos para embarcar nesta jornada desafiadora? Que os códigos estejam a seu favor e que a nuvem seja sua aliada nesta aventura! Boa sorte, programadores! 🚀🔥

## 📝 Controle de atividades 📝

- [x] criar projeto spring
- [x] criar as entidades e o banco de dados
- [x] criar operações read
- [x] criar operações create
- [x] criar operações update
- [x] criar operações delete
- [x] utilizar o create para cadastrar o usuário
- [x] criar testes unitários
- [x] garantir cobertura de 100% dos testes
- [x] usar o docker na api
- [x] migrar para o banco de dados postgresql
- [ ] desafio ec2
