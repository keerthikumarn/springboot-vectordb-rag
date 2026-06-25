package com.vectordb.agents.rag.loader;

import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DocumentLoader implements CommandLineRunner {

	private final VectorStore vectorStore;
	private final JdbcTemplate jdbcTemplate;

	public DocumentLoader(VectorStore vectorStore, JdbcTemplate jdbcTemplate) {
		this.vectorStore = vectorStore;
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void run(String... args) throws Exception {
		// Clear any existing rows so re-runs don't accumulate stale/duplicate data.
		jdbcTemplate.update("DELETE FROM vector_store");
		List<Document> documents = List.of(
				new Document(
						"The Eiffel Tower is a wrought-iron lattice tower on the Champ de Mars in Paris, France. It was designed by the engineer Gustave Eiffel and completed in 1889 as the entrance arch for the 1889 World's Fair."),
				new Document(
						"Standing about 330 metres (1,083 feet) tall, the Eiffel Tower was the tallest man-made structure in the world for 41 years, until the Chrysler Building in New York City was finished in 1930."),
				new Document(
						"The Great Wall of China is a series of fortifications built across the historical northern borders of China to protect against nomadic invasions. Construction spanned many centuries, with major sections built during the Ming dynasty (1368-1644)."),
				new Document(
						"Mount Everest, located in the Himalayas on the border between Nepal and the Tibet Autonomous Region of China, is the highest mountain above sea level at approximately 8,849 metres (29,032 feet)."),
				new Document(
						"The Amazon rainforest is the world's largest tropical rainforest, covering much of the Amazon basin in South America. It spans roughly 5.5 million square kilometres and is home to about 10 percent of the known species on Earth."),
				new Document(
						"The Sun is the star at the centre of the Solar System. It is a nearly perfect sphere of hot plasma, and its energy, produced by nuclear fusion of hydrogen into helium in its core, supports almost all life on Earth."),
				new Document(
						"Water is a chemical compound with the formula H2O, consisting of two hydrogen atoms bonded to one oxygen atom. It covers about 71 percent of the Earth's surface and is essential for all known forms of life."),
				new Document(
						"The human heart is a muscular organ that pumps blood through the circulatory system. An adult heart beats around 60 to 100 times per minute, circulating blood that delivers oxygen and nutrients to the body's tissues."),
				new Document(
						"Photosynthesis is the process by which green plants, algae, and some bacteria convert light energy, usually from the Sun, into chemical energy stored in glucose, releasing oxygen as a by-product."),
				new Document(
						"The Great Barrier Reef, off the coast of Queensland, Australia, is the world's largest coral reef system. It stretches over 2,300 kilometres and is composed of nearly 3,000 individual reefs and 900 islands."));
		vectorStore.add(documents);
		Integer count = jdbcTemplate.queryForObject("SELECT count(*) FROM vector_store", Integer.class);
		System.out.println("Documents loaded into VectorStore. Total rows now: " + count);
	}

}
