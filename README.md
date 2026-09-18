# MÉTODOS:

Os principais metodo do projeto são:

- ListarTodos() -> Lista todas as tarefas -> @GetMapping
- BuscarPorId() -> Busca Tarefa com base no Id -> @GetMapping
- CriarTarefa() -> Cria uma nova tarefa -> PostMapping
- AtualizarStatus() -> Atualiza somente o status da tarefa -> @PatchMapping
- AtualizarTarefa() -> Atualiza todos os dados da tarefa -> @PutMapping
- Deletar() -> Remove tarefa -> @DeleteMapping


# JUSTIFICATIVAS TÉCNICAS:

Utilizamos ArrayList por que na atividade não tivemos conexão com um banco de dados para realizar as consultas.
Então utlizamos ArrayList para armazenar os dados de forma mais simples e pratica.

Fizemos a documentação de forma padrão, simples e explicando a função de método de forma objetiva e clara,
para que qualquer pessoa consiga entender o que o código realiza.

Utilizamos os métodos HTTP para realizar as requisições, no "banco de dados", no nosso ArrayList, onde realizamos os 
testes no POSTMAN, os métodos que utilizamos foram -> (GET, POST, PUT, PATCH, DELETE).

Utilizamos a arquitetura MVC para realizar esse projeto, para a facilitação na organização do projeto, e para futuras
atualizações, sendo assim:
---
-controller
-dto
-entity
-mapper
-repository
-service
-main
---

# EXECUÇÃO DO CÓDIGO

1 - Clonar ou realizar download do projeto no gitHub.
2 - Já com o Intellij aberto, vai em Open ou Clone Repository
3 - Caso você tenha escolhido o download vai em Open e seleciona a pasta do projeto descompactada.
4 - Caso seja o Clone, cole a URL e Clone, e teste o código.
5 - Antes de fazer qualquer coisa no código, verifique se está na versão de acordo v21 do Java, e veja se todas as 
dependências foram instaladas corretamente.
6 - Verifique se está rodando corretamente.
7 - Caso gere erro de server.port, apenas troque a porta no application.propries.
8 - Faça as requisições no POSTMAN ou no INSOMINIA.