package com.vectordb.agents.rag.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.vectordb.agents.rag.dto.MessageRequest;
import com.vectordb.agents.rag.service.RagService;

@RestController
public class RagController {
	
	private final RagService ragService;
	
	public RagController(RagService ragService) {
        this.ragService = ragService;
    }
	
	@PostMapping("/ai/rag")
    public String generate(@RequestBody MessageRequest request) {
        return ragService.retrieveAndGenerate(request.message());
    }

}
