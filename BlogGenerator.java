package com.blog.ai;

import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionRequest;

public class BlogGenerator {

    private final OpenAiService openAiService;

    public BlogGenerator(String apiKey) {
        if (apiKey == null || apiKey.isEmpty()) {
            throw new IllegalArgumentException("OPENAI_API_KEY is not set.");
        }
        this.openAiService = new OpenAiService(apiKey);
    }

    public String generateBlog(String topic, int wordCount) {
        String prompt = "Write a " + wordCount + "-word engaging blog post on the topic: " + topic + ".";

        CompletionRequest request = CompletionRequest.builder()
                .model("text-davinci-003")
                .prompt(prompt)
                .maxTokens(Math.min(wordCount * 4, 3500))
                .temperature(0.7)
                .build();

        return openAiService.createCompletion(request).getChoices().get(0).getText().trim();
    }

    public static int countWords(String text) {
        if (text == null || text.isEmpty()) return 0;
        return text.trim().split("\\s+").length;
    }
}
