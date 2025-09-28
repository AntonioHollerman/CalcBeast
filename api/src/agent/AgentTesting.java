package agent;

import java.util.Scanner;

public class AgentTesting {
    private static final Scanner _scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true){
            System.out.println("Prompt: ");
            String prompt = _scanner.nextLine();

            if (prompt.equals("exit")){
                break;
            }

            var response =
                    AgentsManager.genaiApi.models.generateContent(
                            AgentsManager.MODEL,
                            prompt,
                            null);
            System.out.println(response.text());

        }
    }
}
