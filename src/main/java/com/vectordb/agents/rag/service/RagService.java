package com.vectordb.agents.rag.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class RagService {

	private ChatClient chatClient;
	private VectorStore vectorStore;

	@Value("classpath:/prompts/rag-prompt.st")
	private Resource ragPromptTemplate;

	public RagService(ChatClient.Builder chatClientBuilder, VectorStore vectorStore) {
		this.chatClient = chatClientBuilder.build();
		this.vectorStore = vectorStore;
	}

	public String retrieveAndGenerate(String message) {
		List<Document> similarDocs = vectorStore
				.similaritySearch(SearchRequest.builder().query(message).topK(4).build());
		System.out.println("Similar documents: " + similarDocs);
		String information = similarDocs.stream().map(Document::getText).collect(Collectors.joining("\n"));
		SystemPromptTemplate promptTemplate = new SystemPromptTemplate(ragPromptTemplate);
		Prompt prompt = new Prompt(
				List.of(promptTemplate.createMessage(Map.of("information", information)), new UserMessage(message)));
		System.out.println("System Generatd Prompt: " + prompt.getContents());
		return chatClient.prompt(prompt).call().content();
	}

}
