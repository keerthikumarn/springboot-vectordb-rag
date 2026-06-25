<h1 align="center" id="title">RAG With Springboot + PostgreSQL (VectorDB) + ChatModel</h1>

<p align="center"><img src="https://socialify.git.ci/keerthikumarn/springboot-vectordb-rag/image?description=1&amp;font=KoHo&amp;forks=1&amp;issues=1&amp;language=1&amp;name=1&amp;owner=1&amp;pattern=Diagonal+Stripes&amp;pulls=1&amp;stargazers=1&amp;theme=Light" alt="project-image"></p>

  
  
<h2>🧐 Features</h2>

Here're some of the project's best features:

*   RAG Pipeline with Springboot
*   PostgreSQL as VectorDB for storing embeddings
*   ChatModel for Request/Response Model
*   Locally run embeddings model

<h2>🛠️ Installation Steps:</h2>

<p>1. Install pgvector</p>

```
https://medium.com/@adarsh.ajay/setting-up-postgresql-with-pgvector-in-docker-a-step-by-step-guide-d4203f6456bd
```

<p>2. Create vector extension</p>

```
CREATE EXTENSION IF NOT EXISTS vector;
```

<p>3. Create hstore extension</p>

```
CREATE EXTENSION IF NOT EXISTS hstore;
```

<p>4. Include uuid-ossp</p>

```
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
```

<p>5. Create table named vector_store</p>

```
CREATE  TABLE IF NOT EXISTS vector_store( id uuid default uuid_generate_v4() PRIMARY KEY content TEXT metadata jsonembedding vector(768));
```

<p>6. Create index on vector_store table</p>

```
CREATE INDEX ON vector_store USING HNSW (embedding vector_cosine_ops);
```

<p>7. Clone the project</p>

```
git clone https://github.com/keerthikumarn/springboot-vectordb-rag.git
```

<p>8. Compile the project</p>

```
mvn clean install
```

<p>9. Run the project</p>

```
mvn spring-boot:run
```

<p>10. Enter the postman POST request</p>

```
curl -X POST -H "Content-type: application/json" http://localhost:8099/ai/rag -d '{     "message":"does mount everest happens to be in the region of Japan?" }'
```

  
  
<h2>💻 Built with</h2>

Technologies used in the project:

*   Springboot
*   PostgreSQL
*   VectorDB
*   ChatModel
*   Embeddings
*   OpenRouter API
*   Java 21
