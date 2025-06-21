# Spring AI MCP Examples

## Overview

to be defined

## Projects

to be defined

## How It Works 

to be defined

## Running the Examples

### Prerequisites

* Java 21
* SpringBoot 3.5.3
* Spring AI 1.0.0
* MongoDB
* Postgresql
* Docker
* OpenAI API key

### Step 1: Run Docker Compose to set up MongoDB and PostgreSQL with init data

```sh
docker compose up -d
```
### Step 2: Update application.yaml with OpenAI API Key:
* [**Open API Key**](https://github.com/duongminhhieu/springboot-ai-mcp-example/blob/master/mcp-client/src/main/resources/application.yml#L9)
* Get OpenAI API key [here] (https://platform.openai.com/api-keys)

### Step 3: Run MCP Server
```bash
cd mcp-server
./mvnw spring-boot:run
```
### Step 4: Run MCP Client
```bash
cd mcp-client
./mvnw spring-boot:run
```
### Step 5: Test in Postman 

* Test with **PostgreSQL**
```curl
curl --location 'localhost:8081/chat/ask' \
--header 'Content-Type: text/plain' \
--data 'Requirement: [Postgres] In the table orders, retrieve all orders that have status COMPLETED

Rules:
1. Respond strictly in JSON format, conforming to RFC 8259.
2. The JSON structure must align with the java.util.HashMap format.
3. Do not include explanations, comments, or any labels like ```json```.'
```

* Test with **MongoDB**
```curl
curl --location 'localhost:8081/chat/ask' \
--header 'Content-Type: text/plain' \
--data 'Requirement: Retrieve all documents from the product-service collection, products table, where the price > 100

Rules:
1. Respond strictly in JSON format, conforming to RFC 8259.
2. The JSON structure must align with the java.util.HashMap format.
3. Do not include explanations, comments, or any labels like ```json```.'
```

## References
* https://docs.spring.io/spring-ai/reference/api/mcp/mcp-overview.html

