
# 🛠️ Sistema Distribuído com RabbitMQ - PostService e TextProcessorService

## 📋 Descrição

Desenvolva um sistema distribuído composto por **dois microsserviços** que se comunicam de forma **assíncrona utilizando RabbitMQ**.  
O sistema será responsável por **receber publicações de texto (posts)**, **processar o conteúdo em segundo plano** e **armazenar os resultados**.

---

## 🚀 Funcionalidades do Sistema

- ✅ Criar um novo post
- ✅ Consultar os detalhes de um post
- ✅ Listar posts com paginação
- ✅ Processar posts em background:
  - Contar palavras
  - Calcular valor estimado baseado na quantidade de palavras

---

## 🧩 Microsserviço 1: **PostService**

### ✨ Responsabilidades

- Expor uma **API REST** para criar e consultar posts
- **Enviar** novos posts para a fila `text-processor-service.post-processing.v1.q`
- **Consumir** resultados da fila `post-service.post-processing-result.v1.q`
- **Armazenar** posts e resultados no banco de dados H2

### 🛠️ Tecnologias

- Banco de dados: **H2**
- ID dos posts: **UUID**
- Mensageria: **RabbitMQ**

### 📚 Endpoints

#### ➡️ `POST /api/posts`

- **Função**: Criar um novo post e enviar para processamento
- **Resposta**: `201 Created` + `Input` no corpo

#### ➡️ `GET /api/posts/{postId}`

- **Função**: Buscar detalhes de um post
- **Resposta**: 
  - `200 OK` (se encontrado)
  - `404 Not Found` (se não encontrado)

#### ➡️ `GET /api/posts`

- **Função**: Listar posts com **paginaçã**o
- **Parâmetros**: `page`, `size`
- **Resposta**: 
  ```json
  {
    "page": 0,
    "size": 10,
    "totalElements": 45,
    "totalPages": 5,
    "content": [ /* lista de PostSummaryOutput */ ]
  }
  ```

---

### 📦 DTOs Usados no PostService

#### 📝 `Input` (criação)

```json
{
  "title": "string",
  "body": "string",
  "author": "string"
}
```

#### 📋 `Output` (exibição detalhada)

```json
{
  "id": "string",
  "title": "string",
  "body": "string",
  "author": "string",
  "wordCount": 123,
  "calculatedValue": 12.3
}
```

#### 📋 `SummaryOutput` (listagem simplificada)

```json
{
  "id": "string",
  "title": "string",
  "summary": "string",
  "author": "string"
}
```

---

### 🔒 Validações no PostService

- `title`, `body` e `author` são **obrigatórios**.
- `body` deve conter **texto não vazio**.
- `id` deve ser **UUID** e **único**.
- `summary` deve conter **as 3 primeiras linhas do body**.

---

## 🧩 Microsserviço 2: **TextProcessorService**

### ✨ Responsabilidades

- **Consumir** posts da fila `text-processor-service.post-processing.v1.q`
- **Processar**:
  - Contagem de palavras
  - Cálculo do valor estimado (`palavras * $0,10`)
- **Enviar** o resultado para a fila `post-service.post-processing-result.v1.q`

---

### 📦 Formato das Mensagens

#### 📥 Mensagem Consumida (da fila `text-processor-service.post-processing.v1.q`)

```json
{
  "id": "string", 
  "body": "string"
}
```

#### 📤 Mensagem Produzida (para a fila `post-service.post-processing-result.v1.q`)

```json
{
  "id": "string",
  "wordCount": 123,
  "calculatedValue": 12.3
}
```

---

## 🛠️ Regras Gerais

- Criar **Exchange** apropriado.
- Configurar **Dead Letter Queue (DLQ)** para cada fila.
- Configurar **filas e exchanges via código Java**.
- Sempre usar **DTOs** na comunicação (REST e RabbitMQ).
- Serializar mensagens com **Jackson JSON**.
- Para enviar mensagens diretamente para uma fila sem Exchange nomeada:
  ```java
  rabbitTemplate.convertAndSend(nomeDaQueue, payloadDaMensagem);
  ```

---

## 📋 Tarefas do Desafio

- ✅ Implementar **PostService** com API REST + envio/consumo de filas
- ✅ Implementar **TextProcessorService** com lógica de processamento
- ✅ Persistir dados corretamente no PostService (incluindo campos processados)
- ✅ Definir todos os **DTOs** necessários
- ✅ Garantir respostas HTTP corretas: `200`, `404`, `204`
- ✅ Testar todo o fluxo com RabbitMQ em execução

---

## 💡 Dicas

- 🐞 Use **logs** para acompanhar a troca de mensagens
- 🗂️ Organize seu projeto com o modelo de pacotes que preferir
- 🛎️ Crie **Services** se desejar modularizar
