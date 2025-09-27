package agent;
import com.google.genai.Client;

import java.util.ResourceBundle;


public class AgentsManager {
    public static Client client;
    static {
        var rb = ResourceBundle.getBundle("key");
        client = Client.builder()
                .apiKey(rb.getString("apiKey"))
                .build();
    }
}
