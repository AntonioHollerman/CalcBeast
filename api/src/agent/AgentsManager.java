package agent;
import com.google.adk.agents.LlmAgent;
import com.google.genai.Client;

import java.util.ResourceBundle;


public class AgentsManager {
    public static Client genaiApi;

    public static LlmAgent root;
    private static LlmAgent questionsAgent;
    private static LlmAgent jsonAgent;

    public static final String MODEL = "gemini-2.5-flash";
    static {
        var rb = ResourceBundle.getBundle("key");
        genaiApi = Client.builder()
                .apiKey(rb.getString("apiKey"))
                .build();

        questionsAgent = LlmAgent.builder()
                .model(MODEL)
                .name("questions_agent")
                .description("Explain AP calculus BC mcq/frq answers and create new questions")
                .instruction("""
                        You are a helpful math tutor who aims to help the user with AP calculus BC topics.
                        - When given the question and list of answers, for each answer state why it is wrong or right
                        - When demanded to generate a question reference the database to make similar but different questions
                        - Help grade frq by comparing user image(s) to answer image(s)""")
                .build();
    }
}
