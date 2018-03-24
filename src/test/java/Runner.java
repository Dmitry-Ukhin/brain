import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Event:");
        String event = scanner.nextLine();
        String answer;

        Brain brain = Brain.getBrain();
        brain.build();

        System.out.println("Memories:");
        System.out.printf(brain.getMemories(event));
    }
}
