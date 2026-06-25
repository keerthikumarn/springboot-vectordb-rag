# RAG Pipeline with Springboot + Postgres (Vector Store)

## Overview

This project demonstrates a simple Retrieval Augmented Generation (RAG) pipeline using Spring AI, PGVector as the vector store, and OpenAI for embedding and chat models.

---
## Components

-   **Spring Boot Application:** The main application that coordinates the entire **Retrieval-Augmented Generation (RAG)** workflow.
-   **PGVector:** A PostgreSQL extension that stores document embeddings and helps find the most relevant documents using similarity search.
-   **OpenAI Embedding Model:** Converts documents and user queries into vector embeddings so they can be compared based on semantic meaning.
-   **OpenAI Chat Model:** Generates a response by using the user's question along with the relevant information retrieved from the vector database.
-   **DocumentLoader:** A Spring component that loads sample documents into the PGVector database automatically when the application starts.
-   **RagService:** The core service that implements the RAG workflow:
    -   Accepts the user's query.
    -   Converts the query into an embedding.
    -   Searches the PGVector database for the most relevant documents.
    -   Builds a prompt by combining the user's question with the retrieved document content.
    -   Sends the prompt to the OpenAI Chat Model and returns the generated response.
-   **RagController:** A REST API controller that exposes the `/ai/rag` endpoint, allowing users to interact with the RAG service.
-   **rag-prompt.st:** A prompt template used by the `RagService` to structure the input sent to the AI model, ensuring the response is based on the retrieved document content.

---

## Steps to create Vector Store in Postgres

Install the **pgvector** extension in the **PostgreSQL** - using the respective docker images available for the same.

```sql
CREATE EXTENSION IF NOT EXISTS vector;
CREATE EXTENSION IF NOT EXISTS hstore;
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE  TABLE IF NOT EXISTS vector_store(
id uuid default uuid_generate_v4() PRIMARY KEY ,content TEXT,
metadata json,embedding vector(768));

CREATE INDEX ON vector_store USING HNSW (embedding vector_cosine_ops);
```
---
## RAG Pipeline Flow

<img width="2026" height="830" alt="image" src="https://github.com/user-attachments/assets/0a993744-4d8f-4896-a2e7-7c0e721c8889" />
